package com.animal_crossing.animal_crossing_tp.map;

import com.animal_crossing.animal_crossing_tp.main.Foret;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ForetMapper implements RowMapper<Foret> {
    public static final String BASE_SQL //
            = "SELECT id_foret, nom_foret, superficie_foret, id_ile FROM foret";

    public Foret mapRow(ResultSet rs, int rowNum) throws SQLException {
        int idForet = rs.getInt("id_foret");
        String nomForet = rs.getString("nom_foret");
        float superficieForet = rs.getFloat("superficie_foret");
        int idIle = rs.getInt("id_ile");

        return new Foret(idForet, nomForet, superficieForet,idIle);
    }
}
