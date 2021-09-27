package com.animal_crossing.animal_crossing_tp.dao;

import com.animal_crossing.animal_crossing_tp.main.Joueur;
import com.animal_crossing.animal_crossing_tp.map.JoueurMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class JoueurDAO extends JdbcDaoSupport {
    @Autowired
    public JoueurDAO(DataSource dataSource){ this.setDataSource(dataSource); }

    public List<Joueur> getAllJoueurs(){
        String sql = JoueurMapper.BASE_SQL;

        JoueurMapper mapper = new JoueurMapper();

        try{
            List<Joueur> joueurs = this.getJdbcTemplate().query(sql, mapper); //On récupère l'objet User correspondant à la requête
            return joueurs;
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public Joueur getJoueurById(int idJoueur){
        String sql = //
                "SELECT id_joueur, nom_joueur, prenom_joueur, mail_joueur " +
                "FROM joueur " +
                "WHERE id_joueur = ? ";

        Object[] params = new Object[] { idJoueur }; //Paramètres de la requête qui remplaceront les ?
        JoueurMapper mapper = new JoueurMapper();

        try{
            Joueur joueur = this.getJdbcTemplate().queryForObject(sql, params, mapper); //On récupère l'objet User correspondant à la requête
            return joueur;
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public Joueur getJoueur(String nomJoueur, String prenomJoueur, String mailJoueur){
        String sql = //
            "SELECT id_joueur, nom_joueur, prenom_joueur, mail_joueur " +
            "FROM joueur " +
            "WHERE nom_joueur = ? AND prenom_joueur = ? AND mail_joueur =? ";

        Object[] params = new Object[] { nomJoueur, prenomJoueur, mailJoueur }; //Paramètres de la requête qui remplaceront les ?
        JoueurMapper mapper = new JoueurMapper();

        try{
            Joueur joueur = this.getJdbcTemplate().queryForObject(sql, params, mapper); //On récupère l'objet User correspondant à la requête
            return joueur;
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public int creerJoueur(String nomJoueur, String prenomJoueur, String mailJoueur){
        String sql = //
                "INSERT INTO joueur(nom_joueur, prenom_joueur, mail_joueur) " +
                "VALUES(?,?,?)";

        Object[] params = new Object[] { nomJoueur, prenomJoueur, mailJoueur }; //Paramètres de la requête qui remplaceront les ?
        //JoueurMapper mapper = new JoueurMapper();

        int resultat = 0;
        try{
            resultat = this.getJdbcTemplate().update(sql, params); //On récupère l'objet User correspondant à la requête
            return resultat;
        }
        catch (EmptyResultDataAccessException e){
            return resultat;
        }
    }

    public boolean verifMail(String mailJoueur){
        String sql = //
            "SELECT id_joueur,nom_joueur,prenom_joueur,mail_joueur FROM joueur WHERE mail_joueur = ? ";

        Object[] params = new Object[] { mailJoueur }; //Paramètres de la requête qui remplaceront les ?
        JoueurMapper mapper = new JoueurMapper();

        try{
            Joueur joueur = this.getJdbcTemplate().queryForObject(sql, params, mapper); //On récupère l'objet User correspondant à la requête

            if(joueur != null){
                return false;
            }
        }
        catch (EmptyResultDataAccessException e){
            return true;
        }
        return false;
    }

    public int updateJoueur(Joueur joueur){
        String sql = //
            "UPDATE joueur " +
            "SET nom_joueur = ?, prenom_joueur = ?, mail_joueur = ? " +
            "WHERE id_joueur = ?";

        Object[] params = new Object[] { joueur.getNomJoueur(), joueur.getPrenomJoueur(), joueur.getMailJoueur(), joueur.getIdJoueur() }; //Paramètres de la requête qui remplaceront les ?
        int resultat = 0;

        try{
            resultat = this.getJdbcTemplate().update(sql, params); //On récupère l'objet User correspondant à la requête
            return resultat;
        }
        catch (EmptyResultDataAccessException e){
            return resultat;
        }
    }
}
