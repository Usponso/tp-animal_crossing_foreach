package com.animal_crossing.animal_crossing_tp.dao;

import com.animal_crossing.animal_crossing_tp.main.Cinema;
import com.animal_crossing.animal_crossing_tp.map.CinemaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class CinemaDAO extends JdbcDaoSupport {
    @Autowired
    public CinemaDAO(DataSource dataSource){ this.setDataSource(dataSource); }

    public List<Cinema> getCinemasByIdIle(int idIle){
        String sql = //
                "SELECT id_cinema, nom_cinema, nb_places_cinema, id_ile " +
                "FROM cinema " +
                "WHERE id_ile = ? ";

        Object[] params = new Object[] { idIle }; //Paramètres de la requête qui remplaceront les ?
        CinemaMapper mapper = new CinemaMapper();

        try{
            List<Cinema> listeCinemas = this.getJdbcTemplate().query(sql, params, mapper); //On récupère l'objet User correspondant à la requête
            return listeCinemas;
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public int creerCinema(String nomCinema, int idIle){
        String sql = //
                "INSERT INTO cinema(nom_cinema,id_ile) VALUES(?,?)";

        Object[] params = new Object[] { nomCinema,idIle }; //Paramètres de la requête qui remplaceront les ?

        try{
            int resultat = this.getJdbcTemplate().update(sql, params); //On récupère l'objet User correspondant à la requête
            return resultat;
        }
        catch (EmptyResultDataAccessException e){
            return 0;
        }
    }

    public int deleteCinema(int idIle, int idCinema){
        String sql = //
                "DELETE FROM cinema WHERE id_ile = ? AND id_cinema = ?";

        Object[] params = new Object[]{ idIle, idCinema };

        try{
            int resultat = this.getJdbcTemplate().update(sql, params);
            return resultat;
        }
        catch(Exception e){
            return 0;
        }
    }
}
