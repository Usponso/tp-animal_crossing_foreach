package com.animal_crossing.animal_crossing_tp.dao;

import com.animal_crossing.animal_crossing_tp.main.Ile;
import com.animal_crossing.animal_crossing_tp.map.IleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class IleDAO extends JdbcDaoSupport {
    @Autowired
    public IleDAO(DataSource dataSource){ this.setDataSource(dataSource); }

    public Ile getIleById(int idIle, int idArchipel){
        String sql = //
                "SELECT id_ile, nom_ile, localisation_ile, id_archipel " +
                "FROM ile " +
                "WHERE id_archipel = ? AND id_ile = ? ";

        Object[] params = new Object[] { idArchipel, idIle }; //Paramètres de la requête qui remplaceront les ?
        IleMapper mapper = new IleMapper();

        try{
            Ile ile = this.getJdbcTemplate().queryForObject(sql, params, mapper); //On récupère l'objet User correspondant à la requête
            return ile;
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<Ile> getIlesByIdArchipel(int idArchipel){
        String sql = //
                "SELECT id_ile, nom_ile, localisation_ile, id_archipel " +
                "FROM ile " +
                "WHERE id_archipel = ? ";

        Object[] params = new Object[] { idArchipel }; //Paramètres de la requête qui remplaceront les ?
        IleMapper mapper = new IleMapper();

        try{
            List<Ile> listeIles = this.getJdbcTemplate().query(sql, params, mapper); //On récupère l'objet User correspondant à la requête
            return listeIles;
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public int creerIle(String nomIle, String localisationIle, int idArchipel){
        String sql = //
                "INSERT INTO ile(nom_ile,localisation_ile,id_archipel) VALUES(?,?,?)";

        Object[] params = new Object[] { nomIle, localisationIle, idArchipel }; //Paramètres de la requête qui remplaceront les ?
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

    public int updateIle(Ile ile){
        String sql = //
                "UPDATE ile " +
                "SET nom_ile = ?, localisation_ile = ? " +
                "WHERE id_archipel = ? AND id_ile = ?";

        Object[] params = new Object[] { ile.getNomIle(), ile.getLocalisationIle(), ile.getIdArchipel(), ile.getIdIle() }; //Paramètres de la requête qui remplaceront les ?
        int resultat = 0;

        try{
            resultat = this.getJdbcTemplate().update(sql, params); //On récupère l'objet User correspondant à la requête
            return resultat;
        }
        catch (EmptyResultDataAccessException e){
            return resultat;
        }
    }

    public int deleteIle(int idArchipel, int idIle){
        String sql = //
                "DELETE FROM ile " +
                "WHERE id_archipel = ? AND id_ile = ?";

        Object[] params = new Object[] { idArchipel, idIle }; //Paramètres de la requête qui remplaceront les ?
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
