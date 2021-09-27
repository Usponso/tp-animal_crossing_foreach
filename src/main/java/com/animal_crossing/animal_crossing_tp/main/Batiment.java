package com.animal_crossing.animal_crossing_tp.main;

public class Batiment {
    //ATTRIBUTS
    private int idBatiment;
    private String nomBatiment;
    private int idTypeBatiment;
    private int idIle;

    //CONSTRUCTEURS
    public Batiment(int idBatiment, String nomBatiment, int idTypeBatiment, int idIle){
        this.idBatiment = idBatiment;
        this.nomBatiment = nomBatiment;
        this.idTypeBatiment = idTypeBatiment;
        this.idIle = idIle;
    }

    //GETTER ET SETTER
    public int getIdBatiment() {
        return idBatiment;
    }

    public void setIdBatiment(int idBatiment) {
        this.idBatiment = idBatiment;
    }

    public String getNomBatiment() {
        return nomBatiment;
    }

    public void setNomBatiment(String nomBatiment) {
        this.nomBatiment = nomBatiment;
    }

    public int getIdTypeBatiment() {
        return idTypeBatiment;
    }

    public void setIdTypeBatiment(int idTypeBatiment) {
        this.idTypeBatiment = idTypeBatiment;
    }

    public int getIdIle() {
        return idIle;
    }

    public void setIdIle(int idIle) {
        this.idIle = idIle;
    }
}
