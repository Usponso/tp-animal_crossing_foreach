package com.animal_crossing.animal_crossing_tp.dao;

import com.animal_crossing.animal_crossing_tp.main.Batiment;
import com.animal_crossing.animal_crossing_tp.map.BatimentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class BatimentDAO extends JdbcDaoSupport {
    @Autowired
    public BatimentDAO(DataSource dataSource){ this.setDataSource(dataSource); }

    public List<Batiment> getBatimentsByIdIle(int idIle){
        String sql = //
                "SELECT id_batiment, nom_batiment, id_type_batiment, id_ile " +
                "FROM batiment " +
                "WHERE id_ile = ? ";

        Object[] params = new Object[] { idIle }; //Paramètres de la requête qui remplaceront les ?
        BatimentMapper mapper = new BatimentMapper();

        try{
            List<Batiment> listeBatiments = this.getJdbcTemplate().query(sql, params, mapper); //On récupère l'objet User correspondant à la requête
            return listeBatiments;
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public int creerBatiment(String nomBatiment, int typeBatiment, int idIle){
        String sql = //
            "INSERT INTO batiment(nom_batiment,id_type_batiment,id_ile) VALUES(?,?,?)";

        Object[] params = new Object[] { nomBatiment, typeBatiment, idIle }; //Paramètres de la requête qui remplaceront les ?

        try{
            int resultat = this.getJdbcTemplate().update(sql, params); //On récupère l'objet User correspondant à la requête
            return resultat;
        }
        catch (EmptyResultDataAccessException e){
            return 0;
        }
    }
}
