package com.animal_crossing.animal_crossing_tp.dao;

import com.animal_crossing.animal_crossing_tp.main.Batiment;
import com.animal_crossing.animal_crossing_tp.main.TypeBatiment;
import com.animal_crossing.animal_crossing_tp.map.BatimentMapper;
import com.animal_crossing.animal_crossing_tp.map.TypeBatimentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class TypeBatimentDAO extends JdbcDaoSupport {
    @Autowired
    public TypeBatimentDAO(DataSource dataSource){ this.setDataSource(dataSource); }

    public List<TypeBatiment> getAllTypeBatiments(){
        String sql = //
                "SELECT id_type_batiment, nom_type_batiment " +
                "FROM type_batiment ";

        Object[] params = new Object[] { }; //Paramètres de la requête qui remplaceront les ?
        TypeBatimentMapper mapper = new TypeBatimentMapper();

        try{
            List<TypeBatiment> listeTypeBatiments = this.getJdbcTemplate().query(sql, params, mapper); //On récupère l'objet User correspondant à la requête
            return listeTypeBatiments;
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public TypeBatiment getTypeBatimentsById(int idTypeBatiment){
        String sql = //
                "SELECT id_type_batiment, nom_type_batiment " +
                "FROM type_batiment " +
                "WHERE id_type_batiment = ? ";

        Object[] params = new Object[] { idTypeBatiment }; //Paramètres de la requête qui remplaceront les ?
        TypeBatimentMapper mapper = new TypeBatimentMapper();

        try{
            TypeBatiment typeBatiment = this.getJdbcTemplate().queryForObject(sql, params, mapper); //On récupère l'objet User correspondant à la requête
            return typeBatiment;
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }
}
