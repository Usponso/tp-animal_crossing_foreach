package com.animal_crossing.animal_crossing_tp.controller;

import com.animal_crossing.animal_crossing_tp.dao.JoueurDAO;
import com.animal_crossing.animal_crossing_tp.main.Joueur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class JoueurController {
    @Autowired
    private JoueurDAO joueurDAO;

    @GetMapping("allJoueurs")
    public ModelAndView home(ModelMap model){
        List<Joueur> joueurs = joueurDAO.getAllJoueurs();
        model.addAttribute("joueurs",joueurs);
        return new ModelAndView("allJoueurs", model);
    }

    @GetMapping("/profil/{id}")
    public ModelAndView profil(@PathVariable("id") int idJoueur, ModelMap model){
        model.addAttribute("joueur", joueurDAO.getJoueurById(idJoueur));
        return new ModelAndView("profil", model);
    }

    @PostMapping("/updateJoueur")
    public ModelAndView updateJoueur(@RequestParam("id") int id,
                                     @RequestParam("nom") String nom,
                                     @RequestParam("prenom") String prenom,
                                     @RequestParam("mail") String mail,
                                     ModelMap model){
        Joueur joueur = new Joueur(id,nom,prenom,mail);
        if(joueurDAO.updateJoueur(joueur) == 1){
            model.addAttribute("joueur",joueur);
            model.addAttribute("success", "La modification a été effectuée.");
            return new ModelAndView("profil", model);
        }
        model.addAttribute("erreur", "Une erreur est survenue lors de la modification de vos informations.");
        return new ModelAndView("accueil", model);
    }
}
