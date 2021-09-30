package com.animal_crossing.animal_crossing_tp.controller;

import com.animal_crossing.animal_crossing_tp.dao.*;
import com.animal_crossing.animal_crossing_tp.main.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CinemaController {
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
    @Autowired
    private FilmDAO filmDAO;

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

                    //AJOUTER FILM A LA BASE ET COMPLETER TABLE AFFICHE_FILM

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
        return new ModelAndView("login", model);
    }

    @PostMapping("/reserverSeance")
    public ModelAndView reserverSeance(@RequestParam("idJoueur") int idJoueur,
                                       @RequestParam("idArchipel") int idArchipel,
                                       @RequestParam("idIle") int idIle,
                                       @RequestParam("idCinema") int idCinema,
                                       @RequestParam("idFilm") int idFilm,
                                       @RequestParam("nbPlaces") int nbPlaces,
                                       ModelMap model){

        System.out.println("JOUEUR : " + idJoueur + " / ARCHIPEL : " + idArchipel + " / ILE : " + idIle + " / CINEMA : " + idCinema + " / FILM : " + idFilm + " / PLACES : " + nbPlaces);

        Joueur joueur = joueurDAO.getJoueurById(idJoueur);
        int resultat = 0;

        if(joueur != null){
            Archipel archipel = archipelDAO.getArchipelByIdJoueur(idJoueur);

            if(idArchipel == archipel.getIdArchipel()){ //On vérifie qu'on récup bien le bon archipel

                Ile ile = ileDAO.getIleById(idIle,idArchipel); //On récupère l'ile en question
                if(ile != null){

                    resultat = filmDAO.reserverSeance(idCinema, idFilm, nbPlaces);

                    if(resultat == 0){
                        model.addAttribute("erreur", "Une erreur est survenue lors de la réservation.");
                    }
                    else{
                        model.addAttribute("success", "La réservation a réussi.");
                    }

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
        return new ModelAndView("login", model);
    }
}
