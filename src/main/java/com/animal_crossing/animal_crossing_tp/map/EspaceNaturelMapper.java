package com.animal_crossing.animal_crossing_tp.map;

import com.animal_crossing.animal_crossing_tp.main.EspaceNaturel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EspaceNaturelMapper implements RowMapper<EspaceNaturel> {
    public static final String BASE_SQL //
            = "SELECT id_batiment, nom_batiment, id_type_batiment, id_ile FROM batiment";

    public EspaceNaturel mapRow(ResultSet rs, int rowNum) throws SQLException {
        int idEspace = rs.getInt("id_espace");
        String nomEspace = rs.getString("nom_espace");
        int idTypeEspace = rs.getInt("id_type_espace");
        int idIle = rs.getInt("id_ile");

        return new EspaceNaturel(idEspace,nomEspace,idTypeEspace,idIle);
    }
}
