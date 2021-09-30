package com.animal_crossing.animal_crossing_tp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Repository
@Transactional
public class FilmDAO extends JdbcDaoSupport {
    @Autowired
    public FilmDAO(DataSource dataSource){ this.setDataSource(dataSource); }

    public int reserverSeance(int idCinema, int idFilm, int nbPlaces){
        String sql = //
                "UPDATE affiche_film SET nb_restant = ? WHERE id_cinema = ? AND id_film = ?";

        Object[] params = new Object[] { nbPlaces-1, idCinema, idFilm };

        try{
            int resultat = this.getJdbcTemplate().update(sql,params);

            return resultat;
        }
        catch(Exception e){
            return 0;
        }
    }
}
