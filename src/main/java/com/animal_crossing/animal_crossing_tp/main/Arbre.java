package com.animal_crossing.animal_crossing_tp.main;

public class Arbre {
    //ATTRIBUTS
    private int idArbre;
    private String nomArbre;

    //CONSTRUCTEURS
    public Arbre(int idArbre, String nomArbre){
        this.idArbre = idArbre;
        this.nomArbre = nomArbre;
    }

    //GETTER ET SETTER
    public int getIdArbre() {
        return idArbre;
    }

    public void setIdArbre(int idArbre) {
        this.idArbre = idArbre;
    }

    public String getNomArbre() {
        return nomArbre;
    }

    public void setNomArbre(String nomArbre) {
        this.nomArbre = nomArbre;
    }
}
