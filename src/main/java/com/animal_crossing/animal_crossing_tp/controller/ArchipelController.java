package com.animal_crossing.animal_crossing_tp.controller;

import com.animal_crossing.animal_crossing_tp.dao.ArchipelDAO;
import com.animal_crossing.animal_crossing_tp.dao.JoueurDAO;
import com.animal_crossing.animal_crossing_tp.main.Archipel;
import com.animal_crossing.animal_crossing_tp.main.Joueur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ArchipelController {
    @Autowired
    private ArchipelDAO archipelDAO;
    @Autowired
    private JoueurDAO joueurDAO;

    @PostMapping("/archipel")
    public ModelAndView creerArchipel(@RequestParam("idJoueur") int idJoueur,
                                      @RequestParam("nomArchipel") String nomArchipel,
                                      @RequestParam("localisationArchipel") String localisationArchipel,
                                      ModelMap model){

        int resultat = archipelDAO.creerArchipel(nomArchipel, localisationArchipel, idJoueur);

        Joueur joueur = joueurDAO.getJoueurById(idJoueur);

        if(resultat == 1){
            if(joueur != null){
                model.addAttribute("joueur", joueur);
                Archipel archipel = archipelDAO.getArchipelByIdJoueur(idJoueur);
                model.addAttribute("archipel", archipel);
                model.addAttribute("success","L'archipel a bien été créé.");
                return new ModelAndView("accueil", model);
            }
            else{
                model.addAttribute("erreur", "Une erreur est survenue lors de la création.");
                return new ModelAndView("login", model);
            }
        }
        else{
            if(joueur != null){
                model.addAttribute("joueur", joueur);
                model.addAttribute("erreur", "Une erreur est survenue lors de la création.");
                return new ModelAndView("accueil", model);
            }
            else{
                model.addAttribute("erreur", "Une erreur est survenue lors de la création.");
                return new ModelAndView("login", model);
            }
        }
    }

    @PostMapping("/updateArchipel")
    public ModelAndView updateArchipel(@RequestParam("idJoueur") int idJoueur,
                                       @RequestParam("idArchipel") int idArchipel,
                                       @RequestParam("nomArchipel") String nomArchipel,
                                       @RequestParam("localisationArchipel") String localisationArchipel,
                                       ModelMap model){

        Joueur joueur = joueurDAO.getJoueurById(idJoueur);

        if(joueur != null){
            Archipel archipel = new Archipel(idArchipel, nomArchipel, localisationArchipel, idJoueur);
            if(archipelDAO.updateArchipel(archipel) == 1){
                model.addAttribute("joueur",joueur);
                model.addAttribute("archipel",archipel);
                model.addAttribute("success", "La modification a été effectuée.");
                return new ModelAndView("accueil", model);
            }
            else{
                model.addAttribute("erreur", "Une erreur est survenue lors de la modification de vos informations.");
                return new ModelAndView("accueil", model);
            }
        }

        model.addAttribute("erreur", "Une erreur est survenue lors de la modification de vos informations.");
        return new ModelAndView("login", model);
    }

    @PostMapping("/deleteArchipel")
    public ModelAndView deleteArchipel(@RequestParam("idJoueur") int idJoueur,
                                       @RequestParam("idArchipel") int idArchipel,
                                       ModelMap model){

        Joueur joueur = joueurDAO.getJoueurById(idJoueur);

        if(joueur != null){
            if(archipelDAO.deleteArchipel(idArchipel,idJoueur) == 1){
                model.addAttribute("joueur",joueur);
                model.addAttribute("success", "La suppression a été effectuée.");
                return new ModelAndView("accueil", model);
            }
            else{
                model.addAttribute("erreur", "Une erreur est survenue lors de la suppression.");
                return new ModelAndView("accueil", model);
            }
        }

        model.addAttribute("erreur", "Une erreur est survenue lors de la suppression.");
        return new ModelAndView("login", model);
    }
}
