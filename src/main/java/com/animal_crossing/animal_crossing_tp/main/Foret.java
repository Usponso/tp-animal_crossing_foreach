package com.animal_crossing.animal_crossing_tp.main;

public class Foret {
    //ATTRIBUTS
    private int idForet;
    private String nomForet;
    private float superficieForet;
    private int idIle;

    //CONSTRUCTEURS
    public Foret(int idForet, String nomForet, float superficieForet, int idIle){
        this.idForet = idForet;
        this.nomForet = nomForet;
        this.superficieForet = superficieForet;
        this.idIle = idIle;
    }

    //GETTER ET SETTER
    public int getIdForet() {
        return idForet;
    }

    public void setIdForet(int idForet) {
        this.idForet = idForet;
    }

    public String getNomForet() {
        return nomForet;
    }

    public void setNomForet(String nomForet) {
        this.nomForet = nomForet;
    }

    public float getSuperficieForet() {
        return superficieForet;
    }

    public void setSuperficieForet(float superficieForet) {
        this.superficieForet = superficieForet;
    }

    public int getIdIle() {
        return idIle;
    }

    public void setIdIle(int idIle) {
        this.idIle = idIle;
    }
}
