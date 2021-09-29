package com.animal_crossing.animal_crossing_tp.map;

import com.animal_crossing.animal_crossing_tp.main.Cinema;
import com.animal_crossing.animal_crossing_tp.main.Film;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmMapper implements RowMapper<Film> {
    public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
        int idFilm = rs.getInt("film.id_film");
        String nomFilm = rs.getString("film.nom_film");
        int nbPlaces = rs.getInt("NBRESTANT");

        return new Film(idFilm,nomFilm,nbPlaces);
    }
}
