package com.animal_crossing.animal_crossing_tp.dao;

import com.animal_crossing.animal_crossing_tp.main.TypeEspace;
import com.animal_crossing.animal_crossing_tp.main.TypeEspace;
import com.animal_crossing.animal_crossing_tp.map.TypeEspaceMapper;
import com.animal_crossing.animal_crossing_tp.map.TypeEspaceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class TypeEspaceDAO extends JdbcDaoSupport {
    @Autowired
    public TypeEspaceDAO(DataSource dataSource){ this.setDataSource(dataSource); }

    public List<TypeEspace> getAllTypeEspaces(){
        String sql = //
                "SELECT id_type_espace, nom_type_espace " +
                "FROM type_espace ";

        Object[] params = new Object[] { }; //Paramètres de la requête qui remplaceront les ?
        TypeEspaceMapper mapper = new TypeEspaceMapper();

        try{
            List<TypeEspace> listeTypeEspace = this.getJdbcTemplate().query(sql, params, mapper); //On récupère l'objet User correspondant à la requête
            return listeTypeEspace;
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public TypeEspace getTypeEspacesById(int idTypeEspace){
        String sql = //
                "SELECT id_type_espace, nom_type_espace " +
                "FROM type_espace " +
                "WHERE id_type_espace = ? ";

        Object[] params = new Object[] { idTypeEspace }; //Paramètres de la requête qui remplaceront les ?
        TypeEspaceMapper mapper = new TypeEspaceMapper();

        try{
            TypeEspace typeEspace = this.getJdbcTemplate().queryForObject(sql, params, mapper); //On récupère l'objet User correspondant à la requête
            return typeEspace;
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }
}
