package com.animal_crossing.animal_crossing_tp.map;

import com.animal_crossing.animal_crossing_tp.main.TypeBatiment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TypeBatimentMapper implements RowMapper<TypeBatiment> {
    public static final String BASE_SQL //
            = "SELECT id_type_batiment, nom_type_batiment FROM type_batiment";

    public TypeBatiment mapRow(ResultSet rs, int rowNum) throws SQLException {
        int idTypeBatiment = rs.getInt("id_type_batiment");
        String nomTypeBatiment = rs.getString("nom_type_batiment");

        return new TypeBatiment(idTypeBatiment,nomTypeBatiment);
    }
}
