package com.animal_crossing.animal_crossing_tp.map;

import com.animal_crossing.animal_crossing_tp.main.Cinema;
import com.animal_crossing.animal_crossing_tp.main.Film;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CinemaMapper implements RowMapper<Cinema> {
    public static final String BASE_SQL = //
            "SELECT c.id_cinema, c.nom_cinema, c.nb_places_cinema, c.id_ile, f.id_film as FILMID, f.nom_film AS FILMNOM, affiche_film.nb_restant AS NBPLACES " +
            "FROM cinema c join affiche_film on c.id_cinema = affiche_film.id_cinema " +
            "join film f on affiche_film.id_film = f.id_film";

    public Cinema mapRow(ResultSet rs, int rowNum) throws SQLException {
        int idCinema = rs.getInt("id_cinema");
        String nomCinema = rs.getString("nom_cinema");
        int nbPlaces = rs.getInt("nb_places_cinema");
        int idIle = rs.getInt("id_ile");

//        List<Film> films = new ArrayList<>();
//        if(rs.getInt("FILMID") != 0){
//            films.add(new Film(rs.getInt("FILMID"),rs.getString("FILMNOM"), rs.getInt("NBPLACES")));
//        }
//        cinema.setFilms(films);
        //cinema.addFilm(new Film(rs.getInt("f.id_film"), rs.getString("f.nom_film")));

        return new Cinema(idCinema,nomCinema,nbPlaces,idIle);
    }
}
