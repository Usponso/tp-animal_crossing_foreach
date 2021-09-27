package com.animal_crossing.animal_crossing_tp.map;

import com.animal_crossing.animal_crossing_tp.main.Cinema;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CinemaMapper implements RowMapper<Cinema> {
    public static final String BASE_SQL //
            = "SELECT id_cinema, nom_cinema, nb_places_cinema, id_ile FROM cinema";

    public Cinema mapRow(ResultSet rs, int rowNum) throws SQLException {
        int idCinema = rs.getInt("id_cinema");
        String nomCinema = rs.getString("nom_cinema");
        int nbPlaces = rs.getInt("nb_places_cinema");
        int idIle = rs.getInt("id_ile");

        return new Cinema(idCinema,nomCinema,nbPlaces,idIle);
    }
}
