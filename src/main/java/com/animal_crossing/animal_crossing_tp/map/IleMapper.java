package com.animal_crossing.animal_crossing_tp.map;

import com.animal_crossing.animal_crossing_tp.main.Archipel;
import com.animal_crossing.animal_crossing_tp.main.Ile;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IleMapper implements RowMapper<Ile> {
    public static final String BASE_SQL //
            = "SELECT id_ile, nom_ile, localisation_ile, id_archipel FROM ile";

    public Ile mapRow(ResultSet rs, int rowNum) throws SQLException {
        int idIle = rs.getInt("id_ile");
        String nomIle = rs.getString("nom_ile");
        String localisationIle = rs.getString("localisation_ile");
        int idArchipel = rs.getInt("id_archipel");

        return new Ile(idIle, nomIle, localisationIle, idArchipel);
    }
}
