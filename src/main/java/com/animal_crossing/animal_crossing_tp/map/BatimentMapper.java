package com.animal_crossing.animal_crossing_tp.map;

import com.animal_crossing.animal_crossing_tp.main.Batiment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BatimentMapper implements RowMapper<Batiment> {
    public static final String BASE_SQL //
            = "SELECT id_batiment, nom_batiment, id_type_batiment, id_ile FROM batiment";

    public Batiment mapRow(ResultSet rs, int rowNum) throws SQLException {
        int idBatiment = rs.getInt("id_batiment");
        String nomBatiment = rs.getString("nom_batiment");
        int idTypeBatiment = rs.getInt("id_type_batiment");
        int idIle = rs.getInt("id_ile");

        return new Batiment(idBatiment,nomBatiment,idTypeBatiment,idIle);
    }
}
