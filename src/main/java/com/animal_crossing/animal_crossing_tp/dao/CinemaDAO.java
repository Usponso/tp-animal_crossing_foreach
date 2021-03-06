package com.animal_crossing.animal_crossing_tp.dao;

import com.animal_crossing.animal_crossing_tp.main.Cinema;
import com.animal_crossing.animal_crossing_tp.main.Film;
import com.animal_crossing.animal_crossing_tp.map.CinemaMapper;
import com.animal_crossing.animal_crossing_tp.map.FilmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
@Transactional
public class CinemaDAO extends JdbcDaoSupport {
    @Autowired
    public CinemaDAO(DataSource dataSource){ this.setDataSource(dataSource); }

    public List<Cinema> getCinemasByIdIle(int idIle){
        String sql = "SELECT id_cinema, nom_cinema, nb_places_cinema, id_ile FROM cinema WHERE id_ile = ?";

        Object[] params = new Object[] { idIle }; //Paramètres de la requête qui remplaceront les ?
        CinemaMapper mapper = new CinemaMapper();

        try{
            List<Cinema> listeCinemas = this.getJdbcTemplate().query(sql, params, mapper); //On récupère l'objet User correspondant à la requête
            if(listeCinemas.size() > 0){
                sql = "SELECT film.id_film, film.nom_film, affiche_film.nb_restant AS NBRESTANT " +
                        "FROM film join affiche_film ON film.id_film = affiche_film.id_film " +
                        "WHERE affiche_film.id_cinema = ?";

                FilmMapper mapperF = new FilmMapper();
                for(int i=0;i<listeCinemas.size();i++){
                    params = new Object[] { listeCinemas.get(i).getIdCinema() };
                    List<Film> films = this.getJdbcTemplate().query(sql,params,mapperF);
                    listeCinemas.get(i).setFilms(films);
                }

            }
            return listeCinemas;
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public int creerCinema(String nomCinema, int idIle){
        String sql = //
                "INSERT INTO cinema(nom_cinema,id_ile) VALUES(?,?)";

        try{
            KeyHolder keyHolder = new GeneratedKeyHolder();
            PreparedStatement ps = null;

            int insert = this.getJdbcTemplate().update(connection -> {
                PreparedStatement ps1 = connection.prepareStatement(sql, new String[]{"id"});
                ps1.setString(1, nomCinema);
                ps1.setInt(2, idIle);
                return ps1;
            }, keyHolder);

            String sql2 = "INSERT INTO affiche_film(id_cinema,id_film) (SELECT id_cinema, id_film FROM cinema, film WHERE id_cinema = ?)";

            Object[] params = new Object[]{ keyHolder.getKey().intValue() };

            int resultat = this.getJdbcTemplate().update(sql2,params);

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
