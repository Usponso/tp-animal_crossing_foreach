package com.animal_crossing.animal_crossing_tp.main;

import java.lang.reflect.Type;

public class TypeEspace {
    //ATTRIBUTS
    private int idTypeEspace;
    private String nomTypeEspace;

    //CONSTRUCTEURS
    public TypeEspace(int idTypeEspace, String nomTypeEspace){
        this.idTypeEspace = idTypeEspace;
        this.nomTypeEspace = nomTypeEspace;
    }

    //GETTER ET SETTER
    public int getIdTypeEspace() {
        return idTypeEspace;
    }

    public void setIdTypeEspace(int idTypeEspace) {
        this.idTypeEspace = idTypeEspace;
    }

    public String getNomTypeEspace() {
        return nomTypeEspace;
    }

    public void setNomTypeEspace(String nomTypeEspace) {
        this.nomTypeEspace = nomTypeEspace;
    }
}
