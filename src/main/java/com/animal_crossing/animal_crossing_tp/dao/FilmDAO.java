package com.animal_crossing.animal_crossing_tp.dao;

import com.animal_crossing.animal_crossing_tp.main.Film;
import com.animal_crossing.animal_crossing_tp.map.FilmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
@Transactional
public class FilmDAO extends JdbcDaoSupport {
    @Autowired
    public FilmDAO(DataSource dataSource){ this.setDataSource(dataSource); }

    public int ajouterFilm(String nomFilm){
        String sql = //
                "INSERT INTO film(nom_film) VALUES(?)";

        try{
            KeyHolder keyHolder = new GeneratedKeyHolder();
            PreparedStatement ps = null;

            int insert = this.getJdbcTemplate().update(connection -> {
                PreparedStatement ps1 = connection.prepareStatement(sql, new String[]{"id"});
                ps1.setString(1, nomFilm);
                return ps1;
            }, keyHolder);

            String sql2 = "INSERT INTO affiche_film(id_cinema,id_film) (SELECT id_cinema, id_film FROM cinema, film WHERE id_film = ?)";

            Object[] params = new Object[]{ keyHolder.getKey().intValue() };

            int resultat = this.getJdbcTemplate().update(sql2,params);

            return resultat;

        }
        catch(Exception e){
            return 0;
        }
    }

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
