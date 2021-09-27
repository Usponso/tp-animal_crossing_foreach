package com.animal_crossing.animal_crossing_tp.main;

public class TypeBatiment {
    //ATTRIBUTS
    private int idTypeBatiment;
    private String nomTypeBatiment;

    //CONSTRUCTEURS
    public TypeBatiment(int idTypeBatiment, String nomTypeBatiment){
        this.idTypeBatiment = idTypeBatiment;
        this.nomTypeBatiment = nomTypeBatiment;
    }

    //GETTER ET SETTER
    public int getIdTypeBatiment() {
        return idTypeBatiment;
    }

    public void setIdTypeBatiment(int idTypeBatiment) {
        this.idTypeBatiment = idTypeBatiment;
    }

    public String getNomTypeBatiment() {
        return nomTypeBatiment;
    }

    public void setNomTypeBatiment(String nomTypeBatiment) {
        this.nomTypeBatiment = nomTypeBatiment;
    }
}
