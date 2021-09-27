package com.animal_crossing.animal_crossing_tp.dao;

import com.animal_crossing.animal_crossing_tp.main.Foret;
import com.animal_crossing.animal_crossing_tp.map.ForetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class ForetDAO extends JdbcDaoSupport {
    @Autowired
    public ForetDAO(DataSource dataSource){ this.setDataSource(dataSource); }

    public List<Foret> getForetsByIdIle(int idIle){
        String sql = //
                "SELECT id_foret, nom_foret, superficie_foret, id_ile " +
                "FROM foret " +
                "WHERE id_ile = ? ";

        Object[] params = new Object[] { idIle }; //Paramètres de la requête qui remplaceront les ?
        ForetMapper mapper = new ForetMapper();

        try{
            List<Foret> listeForets = this.getJdbcTemplate().query(sql, params, mapper); //On récupère l'objet User correspondant à la requête
            return listeForets;
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public int creerForet(String nomForet, float superficieForet, int idIle){
        String sql = //
                "INSERT INTO foret(nom_foret,superficie_foret,id_ile) VALUES(?,?,?)";

        Object[] params = new Object[]{ nomForet, superficieForet, idIle };

        try{
            int resultat = this.getJdbcTemplate().update(sql,params);

            return resultat;
        }
        catch(Exception e){
            return 0;
        }
    }
}
