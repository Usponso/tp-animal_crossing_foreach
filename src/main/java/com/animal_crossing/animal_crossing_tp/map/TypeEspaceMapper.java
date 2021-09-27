package com.animal_crossing.animal_crossing_tp.map;

import com.animal_crossing.animal_crossing_tp.main.TypeEspace;
import com.animal_crossing.animal_crossing_tp.main.TypeEspace;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TypeEspaceMapper implements RowMapper<TypeEspace> {
    public static final String BASE_SQL //
            = "SELECT id_type_espace, nom_type_espace FROM type_espace";

    public TypeEspace mapRow(ResultSet rs, int rowNum) throws SQLException {
        int idTypeEspace = rs.getInt("id_type_espace");
        String nomTypeEspace = rs.getString("nom_type_espace");

        return new TypeEspace(idTypeEspace,nomTypeEspace);
    }
}
