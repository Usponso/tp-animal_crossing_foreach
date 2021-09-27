package com.animal_crossing.animal_crossing_tp.map;

import com.animal_crossing.animal_crossing_tp.main.Joueur;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JoueurMapper implements RowMapper<Joueur> {
    public static final String BASE_SQL //
            = "SELECT id_joueur, nom_joueur, prenom_joueur, mail_joueur FROM joueur";

    public Joueur mapRow(ResultSet rs, int rowNum) throws SQLException {
        int idJoueur = rs.getInt("id_joueur");
        String nomJoueur = rs.getString("nom_joueur");
        String prenomJoueur = rs.getString("prenom_joueur");
        String mailJoueur = rs.getString("mail_joueur");

        return new Joueur(idJoueur,nomJoueur,prenomJoueur,mailJoueur);
    }
}
