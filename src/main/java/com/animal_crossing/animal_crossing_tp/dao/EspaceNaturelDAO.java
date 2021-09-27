package com.animal_crossing.animal_crossing_tp.dao;

import com.animal_crossing.animal_crossing_tp.main.EspaceNaturel;
import com.animal_crossing.animal_crossing_tp.map.EspaceNaturelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional
public class EspaceNaturelDAO extends JdbcDaoSupport {
    @Autowired
    public EspaceNaturelDAO(DataSource dataSource){ this.setDataSource(dataSource); }

    public List<EspaceNaturel> getEspacesByIdIle(int idIle){
        String sql = //
                "SELECT id_espace, nom_espace, id_type_espace, id_ile " +
                "FROM espace_naturel " +
                "WHERE id_ile = ? ";

        Object[] params = new Object[] { idIle }; //Paramètres de la requête qui remplaceront les ?
        EspaceNaturelMapper mapper = new EspaceNaturelMapper();

        try{
            List<EspaceNaturel> listeEspaces = this.getJdbcTemplate().query(sql, params, mapper); //On récupère l'objet User correspondant à la requête
            return listeEspaces;
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public int creerEspace(String nomEspace, int idTypeEspace, int idIle){
        String sql = //
                "INSERT INTO espace_naturel(nom_espace,id_type_espace,id_ile) VALUES(?,?,?)";

        Object[] param = new Object[] { nomEspace, idTypeEspace, idIle };

        try{
            int resultat = this.getJdbcTemplate().update(sql, param);

            return resultat;
        }
        catch(Exception e){
            return 0;
        }
    }
}
