package com.animal_crossing.animal_crossing_tp.dao;

import com.animal_crossing.animal_crossing_tp.main.Archipel;
import com.animal_crossing.animal_crossing_tp.map.ArchipelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Repository
@Transactional
public class ArchipelDAO extends JdbcDaoSupport {
    @Autowired
    public ArchipelDAO(DataSource dataSource){ this.setDataSource(dataSource); }

    public Archipel getArchipelByIdJoueur(int idJoueur){
        String sql = //
            "SELECT id_archipel, nom_archipel, localisation_archipel, id_joueur " +
            "FROM archipel " +
            "WHERE id_joueur = ? ";

        Object[] params = new Object[] { idJoueur }; //Paramètres de la requête qui remplaceront les ?
        ArchipelMapper mapper = new ArchipelMapper();

        try{
            Archipel archipel = this.getJdbcTemplate().queryForObject(sql, params, mapper); //On récupère l'objet User correspondant à la requête
            return archipel;
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public int creerArchipel(String nomArchipel, String localisatonArchipel, int idJoueur){
        String sql = //
            "INSERT INTO archipel(nom_archipel,localisation_archipel,id_joueur) VALUES(?,?,?)";

        Object[] params = new Object[] { nomArchipel, localisatonArchipel, idJoueur }; //Paramètres de la requête qui remplaceront les ?
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

    public int updateArchipel(Archipel archipel){
        String sql = //
            "UPDATE archipel " +
            "SET nom_archipel = ?, localisation_archipel = ? " +
            "WHERE id_archipel = ? AND id_joueur = ?";

        Object[] params = new Object[] { archipel.getNomArchipel(), archipel.getLocalisationArchipel(), archipel.getIdArchipel(), archipel.getIdJoueur() }; //Paramètres de la requête qui remplaceront les ?
        int resultat = 0;

        try{
            resultat = this.getJdbcTemplate().update(sql, params); //On récupère l'objet User correspondant à la requête
            return resultat;
        }
        catch (EmptyResultDataAccessException e){
            return resultat;
        }
    }

    public int deleteArchipel(int idArchipel, int idJoueur){
        String sql = //
                "DELETE FROM archipel " +
                "WHERE id_archipel = ? AND id_joueur = ?";

        Object[] params = new Object[] { idArchipel, idJoueur }; //Paramètres de la requête qui remplaceront les ?
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
