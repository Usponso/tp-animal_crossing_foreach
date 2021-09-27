package com.animal_crossing.animal_crossing_tp.map;

import com.animal_crossing.animal_crossing_tp.main.Archipel;
import com.animal_crossing.animal_crossing_tp.main.Joueur;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArchipelMapper implements RowMapper<Archipel> {
    public static final String BASE_SQL //
            = "SELECT id_archipel, nom_archipel, localisation_archipel, id_joueur FROM archipel";

    public Archipel mapRow(ResultSet rs, int rowNum) throws SQLException {
        int idArchipel = rs.getInt("id_archipel");
        String nomArchipel = rs.getString("nom_archipel");
        String localisationArchipel = rs.getString("localisation_archipel");
        int idJoueur = rs.getInt("id_joueur");

        return new Archipel(idArchipel,nomArchipel,localisationArchipel,idJoueur);
    }
}
