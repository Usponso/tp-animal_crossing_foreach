package com.animal_crossing.animal_crossing_tp.controller;

import com.animal_crossing.animal_crossing_tp.dao.ArchipelDAO;
import com.animal_crossing.animal_crossing_tp.dao.IleDAO;
import com.animal_crossing.animal_crossing_tp.dao.JoueurDAO;
import com.animal_crossing.animal_crossing_tp.main.Archipel;
import com.animal_crossing.animal_crossing_tp.main.Ile;
import com.animal_crossing.animal_crossing_tp.main.Joueur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private JoueurDAO joueurDAO;
    @Autowired
    private ArchipelDAO archipelDAO;
    @Autowired
    private IleDAO ileDAO;

    @GetMapping("/")
    public ModelAndView formLogin(ModelMap model){
        return new ModelAndView("login", model);
    }

    @PostMapping("/accueil")
    public ModelAndView accueil(@RequestParam("id") String idJoueur,
                                @RequestParam("nom") String nom,
                                @RequestParam("prenom") String prenom,
                                @RequestParam("mail") String mail,
                                ModelMap model){
        try{
            model.addAttribute("joueur", new Joueur(Integer.parseInt(idJoueur), nom, prenom, mail));
            Archipel archipel = archipelDAO.getArchipelByIdJoueur(Integer.parseInt(idJoueur));
            List<Ile> listeIles = ileDAO.getIlesByIdArchipel(archipel.getIdArchipel());
            model.addAttribute("archipel", archipel);
            model.addAttribute("iles",listeIles);
            return new ModelAndView("accueil", model);
        }
        catch(Exception e){
            System.out.println(e);
            return new ModelAndView("accueil", model);
        }
    }

    @GetMapping("/inscription")
    public ModelAndView formInscription(ModelMap model){
        return new ModelAndView("inscription", model);
    }

    @PostMapping("/traitementInscription")
    public ModelAndView inscription(@RequestParam("nom") String nom_joueur, @RequestParam("prenom") String prenom_joueur, @RequestParam("mail") String mail_joueur, ModelMap model){
        System.out.println(joueurDAO.verifMail(mail_joueur));
        if(joueurDAO.verifMail(mail_joueur)){
            int inscription = joueurDAO.creerJoueur(nom_joueur, prenom_joueur, mail_joueur);
            if(inscription == 1){
                Joueur joueur = joueurDAO.getJoueur(nom_joueur, prenom_joueur, mail_joueur);
                if(joueur != null){
                    model.addAttribute("joueur", joueur);
                    return new ModelAndView("accueil", model);
                }
            }
            model.addAttribute("erreur", "Une erreur est survenue lors de l'inscription. Veuillez réessayer utlérieurement.");
        }
        else{
            model.addAttribute("erreur","L'adresse mail renseignée est déjà associée à un compte.");
        }

        return new ModelAndView("inscription", model);
    }

    @PostMapping("/traitementLogin")
    public ModelAndView login(@RequestParam("nom") String nom_joueur, @RequestParam("prenom") String prenom_joueur, @RequestParam("mail") String mail_joueur, ModelMap model){
        Joueur joueur = joueurDAO.getJoueur(nom_joueur,prenom_joueur,mail_joueur);

        if(joueur!=null){
            Archipel archipel = archipelDAO.getArchipelByIdJoueur(joueur.getIdJoueur());
            if(archipel != null){
                List<Ile> iles = ileDAO.getIlesByIdArchipel(archipel.getIdArchipel());
                model.addAttribute("iles", iles);
            }
            model.addAttribute("joueur", joueur);
            model.addAttribute("archipel", archipel);
            return new ModelAndView("accueil", model);
        }
        else{
            model.addAttribute("erreur", "Aucun compte ne correspond à ces identifiants.");
            return new ModelAndView("login", model);
        }
    }

    @GetMapping("/logout")
    public ModelAndView logout(ModelMap model){
        return new ModelAndView("login",model);
    }
}
