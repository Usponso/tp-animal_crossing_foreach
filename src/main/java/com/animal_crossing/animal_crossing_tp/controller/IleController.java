package com.animal_crossing.animal_crossing_tp.controller;

import com.animal_crossing.animal_crossing_tp.dao.*;
import com.animal_crossing.animal_crossing_tp.main.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class IleController {
    @Autowired
    private ArchipelDAO archipelDAO;
    @Autowired
    private JoueurDAO joueurDAO;
    @Autowired
    private IleDAO ileDAO;
    @Autowired
    private CinemaDAO cinemaDAO;
    @Autowired
    private BatimentDAO batimentDAO;
    @Autowired
    private ForetDAO foretDAO;
    @Autowired
    private EspaceNaturelDAO espaceNaturelDAO;
    @Autowired
    private TypeBatimentDAO typeBatimentDAO;
    @Autowired
    private TypeEspaceDAO typeEspaceDAO;

    @PostMapping("/creerIle")
    public ModelAndView creerIle(@RequestParam("idJoueur") int idJoueur,
                                 @RequestParam("idArchipel") int idArchipel,
                                 @RequestParam("nomIle") String nomIle,
                                 @RequestParam("localisationIle") String localisationIle,
                                 ModelMap model){

        Joueur joueur = joueurDAO.getJoueurById(idJoueur);
        int resultat = 0;

        if(joueur != null){
            resultat = ileDAO.creerIle(nomIle, localisationIle, idArchipel);

            model.addAttribute("joueur", joueur);
            Archipel archipel = archipelDAO.getArchipelByIdJoueur(idJoueur);
            model.addAttribute("archipel", archipel);
            List<Ile> listeIles = ileDAO.getIlesByIdArchipel(idArchipel);
            model.addAttribute("iles", listeIles);

            if(resultat == 1){
                model.addAttribute("success","L'ile a bien été créée.");

            }
            else{
                model.addAttribute("erreur","Une erreur est survenue lors de la création de l'ile.");
            }

            return new ModelAndView("accueil", model);
        }
        else{
            model.addAttribute("erreur", "Une erreur est survenue lors de la création de l'ile.");
            return new ModelAndView("login", model);
        }
    }

    @PostMapping("/updateIle")
    public ModelAndView updateIle(@RequestParam("idJoueur") int idJoueur,
                                  @RequestParam("idArchipel") int idArchipel,
                                  @RequestParam("idIle") int idIle,
                                  @RequestParam("nomIle") String nomIle,
                                  @RequestParam("localisationIle") String localisationIle,
                                  ModelMap model){

        Joueur joueur = joueurDAO.getJoueurById(idJoueur);

        if(joueur != null){
            Archipel archipel = archipelDAO.getArchipelByIdJoueur(idJoueur);
            Ile ile = new Ile(idIle, nomIle, localisationIle, idArchipel);
            if(ileDAO.updateIle(ile) == 1){
                model.addAttribute("joueur",joueur);
                model.addAttribute("archipel",archipel);
                List<Ile> iles = ileDAO.getIlesByIdArchipel(archipel.getIdArchipel());
                model.addAttribute("iles",iles);
                model.addAttribute("success", "La modification a été effectuée.");
                return new ModelAndView("accueil", model);
            }
            else{
                System.out.println("erreur update");
                model.addAttribute("erreur", "Une erreur est survenue lors de la modification de vos informations.");
                return new ModelAndView("accueil", model);
            }
        }

        model.addAttribute("erreur", "Une erreur est survenue lors de la modification de vos informations.");
        return new ModelAndView("login", model);
    }

    @PostMapping("/deleteIle")
    public ModelAndView deleteIle(@RequestParam("idJoueur") int idJoueur,
                                  @RequestParam("idArchipel") int idArchipel,
                                  @RequestParam("idIle") int idIle,
                                  ModelMap model){

        Joueur joueur = joueurDAO.getJoueurById(idJoueur);

        if(joueur != null){
            if(ileDAO.deleteIle(idArchipel, idIle) == 1){
                model.addAttribute("joueur",joueur);
                Archipel archipel = archipelDAO.getArchipelByIdJoueur(idJoueur);
                model.addAttribute("archipel",archipel);
                List<Ile> iles = ileDAO.getIlesByIdArchipel(archipel.getIdArchipel());
                model.addAttribute("iles",iles);
                model.addAttribute("success", "La suppression a été effectuée.");
                return new ModelAndView("accueil", model);
            }
            else{
                model.addAttribute("joueur",joueur);
                Archipel archipel = archipelDAO.getArchipelByIdJoueur(idJoueur);
                model.addAttribute("archipel",archipel);
                List<Ile> iles = ileDAO.getIlesByIdArchipel(archipel.getIdArchipel());
                model.addAttribute("iles",iles);
                model.addAttribute("erreur", "Une erreur est survenue lors de la suppression.");
                return new ModelAndView("accueil", model);
            }
        }
        model.addAttribute("erreur", "Une erreur est survenue lors de la suppression.");
        return new ModelAndView("login", model);
    }

    @GetMapping("/ile/{idJoueur}/{idArchipel}/{idIle}")
    public ModelAndView afficherIle(@PathVariable("idJoueur") int idJoueur,
                                    @PathVariable("idArchipel") int idArchipel,
                                    @PathVariable("idIle") int idIle,
                                    ModelMap model){

        Joueur joueur = joueurDAO.getJoueurById(idJoueur);

        if(joueur != null){
            Archipel archipel = archipelDAO.getArchipelByIdJoueur(idJoueur);

            if(idArchipel == archipel.getIdArchipel()){ //On vérifie qu'on récup bien le bon archipel
                Ile ile = ileDAO.getIleById(idIle,idArchipel); //On récupère l'ile en question
                List<Cinema> listeCinemas = cinemaDAO.getCinemasByIdIle(ile.getIdIle());
                List<Batiment> listeBatiments = batimentDAO.getBatimentsByIdIle(ile.getIdIle());
                List<Foret> listeForets = foretDAO.getForetsByIdIle(ile.getIdIle());
                List<EspaceNaturel> listeEspaces = espaceNaturelDAO.getEspacesByIdIle(ile.getIdIle());
                List<TypeBatiment> listeTypeBatiment = typeBatimentDAO.getAllTypeBatiments();
                List<TypeEspace> listeTypeEspace = typeEspaceDAO.getAllTypeEspaces();

                model.addAttribute("joueur", joueur);
                model.addAttribute("archipel", archipel);
                model.addAttribute("ile", ile);
                model.addAttribute("cinemas", listeCinemas);
                model.addAttribute("batiments", listeBatiments);
                model.addAttribute("forets", listeForets);
                model.addAttribute("espaces", listeEspaces);
                model.addAttribute("typesBatiments",listeTypeBatiment);
                model.addAttribute("typesEspaces",listeTypeEspace);

                return new ModelAndView("ile", model);
            }
            else{
                model.addAttribute("joueur", joueur);
                model.addAttribute("archipel", archipel);
                model.addAttribute("iles",ileDAO.getIlesByIdArchipel(archipel.getIdArchipel()));

                return new ModelAndView("accueil", model);
            }
        }
        else{
            model.addAttribute("erreur", "Une erreur est survenue.");
             return new ModelAndView("login", model);
        }
    }

    @PostMapping("/creerBatiment")
    public ModelAndView creerBatiment(@RequestParam("idJoueur") int idJoueur,
                                      @RequestParam("idArchipel") int idArchipel,
                                      @RequestParam("idIle") int idIle,
                                      @RequestParam("nomBatiment") String nomBatiment,
                                      @RequestParam("idTypeBatiment") int idTypeBatiment,
                                      ModelMap model){

        Joueur joueur = joueurDAO.getJoueurById(idJoueur);
        int resultat = 0;

        if(joueur != null){
            Archipel archipel = archipelDAO.getArchipelByIdJoueur(idJoueur);

            if(idArchipel == archipel.getIdArchipel()){ //On vérifie qu'on récup bien le bon archipel

                resultat = batimentDAO.creerBatiment(nomBatiment,idTypeBatiment,idIle);
                Ile ile = ileDAO.getIleById(idIle,idArchipel); //On récupère l'ile en question
                List<Cinema> listeCinemas = cinemaDAO.getCinemasByIdIle(ile.getIdIle());
                List<Batiment> listeBatiments = batimentDAO.getBatimentsByIdIle(ile.getIdIle());
                List<Foret> listeForets = foretDAO.getForetsByIdIle(ile.getIdIle());
                List<EspaceNaturel> listeEspaces = espaceNaturelDAO.getEspacesByIdIle(ile.getIdIle());
                List<TypeBatiment> listeTypeBatiment = typeBatimentDAO.getAllTypeBatiments();
                List<TypeEspace> listeTypeEspace = typeEspaceDAO.getAllTypeEspaces();

                model.addAttribute("joueur", joueur);
                model.addAttribute("archipel", archipel);
                model.addAttribute("ile", ile);
                model.addAttribute("cinemas", listeCinemas);
                model.addAttribute("batiments", listeBatiments);
                model.addAttribute("forets", listeForets);
                model.addAttribute("espaces", listeEspaces);
                model.addAttribute("typesBatiments",listeTypeBatiment);
                model.addAttribute("typesEspaces",listeTypeEspace);

                return new ModelAndView("ile", model);
            }
            else{
                model.addAttribute("joueur", joueur);
                model.addAttribute("archipel", archipel);
                model.addAttribute("iles",ileDAO.getIlesByIdArchipel(archipel.getIdArchipel()));

                return new ModelAndView("accueil", model);
            }
        }
        else{
            model.addAttribute("erreur", "Une erreur est survenue.");
            return new ModelAndView("login", model);
        }
    }

    @PostMapping("/creerCinema")
    public ModelAndView creerCinema(@RequestParam("idJoueur") int idJoueur,
                                      @RequestParam("idArchipel") int idArchipel,
                                      @RequestParam("idIle") int idIle,
                                      @RequestParam("nomCinema") String nomCinema,
                                      ModelMap model){

        Joueur joueur = joueurDAO.getJoueurById(idJoueur);
        int resultat = 0;

        if(joueur != null){
            Archipel archipel = archipelDAO.getArchipelByIdJoueur(idJoueur);

            if(idArchipel == archipel.getIdArchipel()){ //On vérifie qu'on récup bien le bon archipel

                resultat = cinemaDAO.creerCinema(nomCinema,idIle);
                Ile ile = ileDAO.getIleById(idIle,idArchipel); //On récupère l'ile en question
                List<Cinema> listeCinemas = cinemaDAO.getCinemasByIdIle(ile.getIdIle());
                List<Batiment> listeBatiments = batimentDAO.getBatimentsByIdIle(ile.getIdIle());
                List<Foret> listeForets = foretDAO.getForetsByIdIle(ile.getIdIle());
                List<EspaceNaturel> listeEspaces = espaceNaturelDAO.getEspacesByIdIle(ile.getIdIle());
                List<TypeBatiment> listeTypeBatiment = typeBatimentDAO.getAllTypeBatiments();
                List<TypeEspace> listeTypeEspace = typeEspaceDAO.getAllTypeEspaces();

                model.addAttribute("joueur", joueur);
                model.addAttribute("archipel", archipel);
                model.addAttribute("ile", ile);
                model.addAttribute("cinemas", listeCinemas);
                model.addAttribute("batiments", listeBatiments);
                model.addAttribute("forets", listeForets);
                model.addAttribute("espaces", listeEspaces);
                model.addAttribute("typesBatiments",listeTypeBatiment);
                model.addAttribute("typesEspaces",listeTypeEspace);

                return new ModelAndView("ile", model);
            }
            else{
                model.addAttribute("joueur", joueur);
                model.addAttribute("archipel", archipel);
                model.addAttribute("iles",ileDAO.getIlesByIdArchipel(archipel.getIdArchipel()));

                return new ModelAndView("accueil", model);
            }
        }
        else{
            model.addAttribute("erreur", "Une erreur est survenue.");
            return new ModelAndView("login", model);
        }
    }

    @PostMapping("/creerForet")
    public ModelAndView creerForet(@RequestParam("idJoueur") int idJoueur,
                                   @RequestParam("idArchipel") int idArchipel,
                                   @RequestParam("idIle") int idIle,
                                   @RequestParam("nomForet") String nomForet,
                                   @RequestParam("superficieForet") float superficieForet,
                                   ModelMap model){

        Joueur joueur = joueurDAO.getJoueurById(idJoueur);
        int resultat = 0;

        if(joueur != null){
            Archipel archipel = archipelDAO.getArchipelByIdJoueur(idJoueur);

            if(idArchipel == archipel.getIdArchipel()){ //On vérifie qu'on récup bien le bon archipel
                resultat = foretDAO.creerForet(nomForet,superficieForet,idIle);
                Ile ile = ileDAO.getIleById(idIle,idArchipel); //On récupère l'ile en question
                List<Cinema> listeCinemas = cinemaDAO.getCinemasByIdIle(ile.getIdIle());
                List<Batiment> listeBatiments = batimentDAO.getBatimentsByIdIle(ile.getIdIle());
                List<Foret> listeForets = foretDAO.getForetsByIdIle(ile.getIdIle());
                List<EspaceNaturel> listeEspaces = espaceNaturelDAO.getEspacesByIdIle(ile.getIdIle());
                List<TypeBatiment> listeTypeBatiment = typeBatimentDAO.getAllTypeBatiments();
                List<TypeEspace> listeTypeEspace = typeEspaceDAO.getAllTypeEspaces();

                model.addAttribute("joueur", joueur);
                model.addAttribute("archipel", archipel);
                model.addAttribute("ile", ile);
                model.addAttribute("cinemas", listeCinemas);
                model.addAttribute("batiments", listeBatiments);
                model.addAttribute("forets", listeForets);
                model.addAttribute("espaces", listeEspaces);
                model.addAttribute("typesBatiments",listeTypeBatiment);
                model.addAttribute("typesEspaces",listeTypeEspace);

                return new ModelAndView("ile", model);
            }
            else{
                model.addAttribute("joueur", joueur);
                model.addAttribute("archipel", archipel);
                model.addAttribute("iles",ileDAO.getIlesByIdArchipel(archipel.getIdArchipel()));

                return new ModelAndView("accueil", model);
            }
        }
        else{
            model.addAttribute("erreur", "Une erreur est survenue.");
            return new ModelAndView("login", model);
        }
    }

    @PostMapping("/creerEspace")
    public ModelAndView creerEspace(@RequestParam("idJoueur") int idJoueur,
                                   @RequestParam("idArchipel") int idArchipel,
                                   @RequestParam("idIle") int idIle,
                                   @RequestParam("nomEspace") String nomEspace,
                                   @RequestParam("idTypeEspace") int idTypeEspace,
                                   ModelMap model){

        Joueur joueur = joueurDAO.getJoueurById(idJoueur);
        int resultat = 0;

        if(joueur != null){
            Archipel archipel = archipelDAO.getArchipelByIdJoueur(idJoueur);

            if(idArchipel == archipel.getIdArchipel()){ //On vérifie qu'on récup bien le bon archipel
                resultat = espaceNaturelDAO.creerEspace(nomEspace,idTypeEspace,idIle);
                Ile ile = ileDAO.getIleById(idIle,idArchipel); //On récupère l'ile en question
                List<Cinema> listeCinemas = cinemaDAO.getCinemasByIdIle(ile.getIdIle());
                List<Batiment> listeBatiments = batimentDAO.getBatimentsByIdIle(ile.getIdIle());
                List<Foret> listeForets = foretDAO.getForetsByIdIle(ile.getIdIle());
                List<EspaceNaturel> listeEspaces = espaceNaturelDAO.getEspacesByIdIle(ile.getIdIle());
                List<TypeBatiment> listeTypeBatiment = typeBatimentDAO.getAllTypeBatiments();
                List<TypeEspace> listeTypeEspace = typeEspaceDAO.getAllTypeEspaces();

                model.addAttribute("joueur", joueur);
                model.addAttribute("archipel", archipel);
                model.addAttribute("ile", ile);
                model.addAttribute("cinemas", listeCinemas);
                model.addAttribute("batiments", listeBatiments);
                model.addAttribute("forets", listeForets);
                model.addAttribute("espaces", listeEspaces);
                model.addAttribute("typesBatiments",listeTypeBatiment);
                model.addAttribute("typesEspaces",listeTypeEspace);

                return new ModelAndView("ile", model);
            }
            else{
                model.addAttribute("joueur", joueur);
                model.addAttribute("archipel", archipel);
                model.addAttribute("iles",ileDAO.getIlesByIdArchipel(archipel.getIdArchipel()));

                return new ModelAndView("accueil", model);
            }
        }
        else{
            model.addAttribute("erreur", "Une erreur est survenue.");
            return new ModelAndView("login", model);
        }
    }

    @GetMapping("/deleteCinema/{idJoueur}/{idIle}/{idCinema}")
    public ModelAndView deleteCinema(@PathVariable("idJoueur") int idJoueur,
                                     @PathVariable("idIle") int idIle,
                                     @PathVariable("idCinema") int idCinema,
                                     ModelMap model){

        Joueur joueur = joueurDAO.getJoueurById(idJoueur);
        if(joueur != null){
            model.addAttribute("joueur",joueur);
            Archipel archipel = archipelDAO.getArchipelByIdJoueur(idJoueur);
            if(archipel != null){
                model.addAttribute("archipel",archipel);
                Ile ile = ileDAO.getIleById(idIle,archipel.getIdArchipel());
                if(ile != null){
                    model.addAttribute("ile",ile);
                    List<Batiment> listeBatiments = batimentDAO.getBatimentsByIdIle(ile.getIdIle());
                    List<Foret> listeForets = foretDAO.getForetsByIdIle(ile.getIdIle());
                    List<EspaceNaturel> listeEspaces = espaceNaturelDAO.getEspacesByIdIle(ile.getIdIle());
                    List<TypeBatiment> listeTypeBatiment = typeBatimentDAO.getAllTypeBatiments();
                    List<TypeEspace> listeTypeEspace = typeEspaceDAO.getAllTypeEspaces();

                    //Verifier si le cinema existe vraiment avant de supprimer
                    int resultat = cinemaDAO.deleteCinema(idIle,idCinema);
                    if(resultat == 1){
                        model.addAttribute("success","Suppression réussie.");
                    }
                    else{
                        model.addAttribute("erreur","La suppression a échoué.");
                    }
                    List<Cinema> listeCinemas = cinemaDAO.getCinemasByIdIle(ile.getIdIle());

                    model.addAttribute("cinemas", listeCinemas);
                    model.addAttribute("batiments", listeBatiments);
                    model.addAttribute("forets", listeForets);
                    model.addAttribute("espaces", listeEspaces);
                    model.addAttribute("typesBatiments",listeTypeBatiment);
                    model.addAttribute("typesEspaces",listeTypeEspace);

                    return new ModelAndView("ile", model);
                } //GERER LES CAS D'ERREUR
            }
        }
        return new ModelAndView("login", model);
    }

    @GetMapping("/deleteBatiment/{idJoueur}/{idIle}/{idBatiment}")
    public ModelAndView deleteBatiment(@PathVariable("idJoueur") int idJoueur,
                                       @PathVariable("idIle") int idIle,
                                       @PathVariable("idBatiment") int idBatiment,
                                       ModelMap model){

        Joueur joueur = joueurDAO.getJoueurById(idJoueur);
        if(joueur != null){
            model.addAttribute("joueur",joueur);
            Archipel archipel = archipelDAO.getArchipelByIdJoueur(idJoueur);
            if(archipel != null){
                model.addAttribute("archipel",archipel);
                Ile ile = ileDAO.getIleById(idIle,archipel.getIdArchipel());
                if(ile != null){
                    model.addAttribute("ile",ile);

                    List<Cinema> listeCinemas = cinemaDAO.getCinemasByIdIle(ile.getIdIle());
                    List<Foret> listeForets = foretDAO.getForetsByIdIle(ile.getIdIle());
                    List<EspaceNaturel> listeEspaces = espaceNaturelDAO.getEspacesByIdIle(ile.getIdIle());
                    List<TypeBatiment> listeTypeBatiment = typeBatimentDAO.getAllTypeBatiments();
                    List<TypeEspace> listeTypeEspace = typeEspaceDAO.getAllTypeEspaces();

                    //Verifier si le batiment existe vraiment avant de supprimer
                    int resultat = batimentDAO.deleteBatiment(idIle,idBatiment);
                    if(resultat == 1){
                        model.addAttribute("success","Suppression réussie.");
                    }
                    else{
                        model.addAttribute("erreur","La suppression a échoué.");
                    }
                    List<Batiment> listeBatiments = batimentDAO.getBatimentsByIdIle(ile.getIdIle());

                    model.addAttribute("cinemas", listeCinemas);
                    model.addAttribute("batiments", listeBatiments);
                    model.addAttribute("forets", listeForets);
                    model.addAttribute("espaces", listeEspaces);
                    model.addAttribute("typesBatiments",listeTypeBatiment);
                    model.addAttribute("typesEspaces",listeTypeEspace);

                    return new ModelAndView("ile", model);
                } //GERER LES CAS D'ERREUR
            }
        }
        return new ModelAndView("login", model);
    }

    @GetMapping("/deleteForet/{idJoueur}/{idIle}/{idForet}")
    public ModelAndView deleteForet(@PathVariable("idJoueur") int idJoueur,
                                       @PathVariable("idIle") int idIle,
                                       @PathVariable("idForet") int idForet,
                                       ModelMap model){

        Joueur joueur = joueurDAO.getJoueurById(idJoueur);
        if(joueur != null){
            model.addAttribute("joueur",joueur);
            Archipel archipel = archipelDAO.getArchipelByIdJoueur(idJoueur);
            if(archipel != null){
                model.addAttribute("archipel",archipel);
                Ile ile = ileDAO.getIleById(idIle,archipel.getIdArchipel());
                if(ile != null){
                    model.addAttribute("ile",ile);

                    List<Batiment> listeBatiments = batimentDAO.getBatimentsByIdIle(ile.getIdIle());
                    List<Cinema> listeCinemas = cinemaDAO.getCinemasByIdIle(ile.getIdIle());
                    List<EspaceNaturel> listeEspaces = espaceNaturelDAO.getEspacesByIdIle(ile.getIdIle());
                    List<TypeBatiment> listeTypeBatiment = typeBatimentDAO.getAllTypeBatiments();
                    List<TypeEspace> listeTypeEspace = typeEspaceDAO.getAllTypeEspaces();

                    //Verifier si la foret existe vraiment avant de supprimer
                    int resultat = foretDAO.deleteForet(idIle,idForet);
                    if(resultat == 1){
                        model.addAttribute("success","Suppression réussie.");
                    }
                    else{
                        model.addAttribute("erreur","La suppression a échoué.");
                    }
                    List<Foret> listeForets = foretDAO.getForetsByIdIle(ile.getIdIle());

                    model.addAttribute("cinemas", listeCinemas);
                    model.addAttribute("batiments", listeBatiments);
                    model.addAttribute("forets", listeForets);
                    model.addAttribute("espaces", listeEspaces);
                    model.addAttribute("typesBatiments",listeTypeBatiment);
                    model.addAttribute("typesEspaces",listeTypeEspace);

                    return new ModelAndView("ile", model);
                } //GERER LES CAS D'ERREUR
            }
        }
        return new ModelAndView("login", model);
    }

    @GetMapping("/deleteEspace/{idJoueur}/{idIle}/{idEspace}")
    public ModelAndView deleteEspace(@PathVariable("idJoueur") int idJoueur,
                                    @PathVariable("idIle") int idIle,
                                    @PathVariable("idEspace") int idEspace,
                                    ModelMap model){

        Joueur joueur = joueurDAO.getJoueurById(idJoueur);
        if(joueur != null){
            model.addAttribute("joueur",joueur);
            Archipel archipel = archipelDAO.getArchipelByIdJoueur(idJoueur);
            if(archipel != null){
                model.addAttribute("archipel",archipel);
                Ile ile = ileDAO.getIleById(idIle,archipel.getIdArchipel());
                if(ile != null){
                    model.addAttribute("ile",ile);

                    List<Batiment> listeBatiments = batimentDAO.getBatimentsByIdIle(ile.getIdIle());
                    List<Cinema> listeCinemas = cinemaDAO.getCinemasByIdIle(ile.getIdIle());
                    List<Foret> listeForets = foretDAO.getForetsByIdIle(ile.getIdIle());
                    List<TypeBatiment> listeTypeBatiment = typeBatimentDAO.getAllTypeBatiments();
                    List<TypeEspace> listeTypeEspace = typeEspaceDAO.getAllTypeEspaces();

                    //Verifier si la foret existe vraiment avant de supprimer
                    int resultat = espaceNaturelDAO.deleteEspace(idIle,idEspace);
                    if(resultat == 1){
                        model.addAttribute("success","Suppression réussie.");
                    }
                    else{
                        model.addAttribute("erreur","La suppression a échoué.");
                    }
                    List<EspaceNaturel> listeEspaces = espaceNaturelDAO.getEspacesByIdIle(ile.getIdIle());

                    model.addAttribute("cinemas", listeCinemas);
                    model.addAttribute("batiments", listeBatiments);
                    model.addAttribute("forets", listeForets);
                    model.addAttribute("espaces", listeEspaces);
                    model.addAttribute("typesBatiments",listeTypeBatiment);
                    model.addAttribute("typesEspaces",listeTypeEspace);

                    return new ModelAndView("ile", model);
                } //GERER LES CAS D'ERREUR
            }
        }
        return new ModelAndView("login", model);
    }

    @PostMapping("/ajouterFilm")
    public ModelAndView ajouterFilm(@RequestParam("idJoueur") int idJoueur,
                                    @RequestParam("idArchipel") int idArchipel,
                                    @RequestParam("idIle") int idIle,
                                    @RequestParam("nomFilm") String nomFilm,
                                    ModelMap model){

        Joueur joueur = joueurDAO.getJoueurById(idJoueur);
        int resultat = 0;

        if(joueur != null){
            Archipel archipel = archipelDAO.getArchipelByIdJoueur(idJoueur);

            if(idArchipel == archipel.getIdArchipel()){ //On vérifie qu'on récup bien le bon archipel

                Ile ile = ileDAO.getIleById(idIle,idArchipel); //On récupère l'ile en question
                if(ile != null){
                    List<Cinema> listeCinemas = cinemaDAO.getCinemasByIdIle(ile.getIdIle());
                    List<Batiment> listeBatiments = batimentDAO.getBatimentsByIdIle(ile.getIdIle());
                    List<Foret> listeForets = foretDAO.getForetsByIdIle(ile.getIdIle());
                    List<EspaceNaturel> listeEspaces = espaceNaturelDAO.getEspacesByIdIle(ile.getIdIle());
                    List<TypeBatiment> listeTypeBatiment = typeBatimentDAO.getAllTypeBatiments();
                    List<TypeEspace> listeTypeEspace = typeEspaceDAO.getAllTypeEspaces();

                    model.addAttribute("joueur", joueur);
                    model.addAttribute("archipel", archipel);
                    model.addAttribute("ile", ile);
                    model.addAttribute("cinemas", listeCinemas);
                    model.addAttribute("batiments", listeBatiments);
                    model.addAttribute("forets", listeForets);
                    model.addAttribute("espaces", listeEspaces);
                    model.addAttribute("typesBatiments",listeTypeBatiment);
                    model.addAttribute("typesEspaces",listeTypeEspace);

                    return new ModelAndView("ile", model);
                }
            }
            else{
                model.addAttribute("joueur", joueur);
                model.addAttribute("archipel", archipel);
                model.addAttribute("iles",ileDAO.getIlesByIdArchipel(archipel.getIdArchipel()));

                return new ModelAndView("accueil", model);
            }
        }
        else{
            model.addAttribute("erreur", "Une erreur est survenue.");
            return new ModelAndView("login", model);
        }
    }
}
