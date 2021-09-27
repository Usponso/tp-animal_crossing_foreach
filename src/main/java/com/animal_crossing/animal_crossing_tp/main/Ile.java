package com.animal_crossing.animal_crossing_tp.main;

public class Ile {
    //ATTRIBUTS
    private int idIle;
    private String nomIle;
    private String localisationIle;
    private int idArchipel;

    //CONSTRUCTEURS
    public Ile(int idIle, String nomIle, String localisationIle, int idArchipel){
        this.idIle = idIle;
        this.nomIle = nomIle;
        this.localisationIle = localisationIle;
        this.idArchipel = idArchipel;
    }

    //GETTER ET SETTER
    public int getIdIle() {
        return idIle;
    }

    public void setIdIle(int idIle) {
        this.idIle = idIle;
    }

    public String getNomIle() {
        return nomIle;
    }

    public void setNomIle(String nomIle) {
        this.nomIle = nomIle;
    }

    public String getLocalisationIle() {
        return localisationIle;
    }

    public void setLocalisationIle(String localisationIle) {
        this.localisationIle = localisationIle;
    }

    public int getIdArchipel() {
        return idArchipel;
    }

    public void setIdArchipel(int idArchipel) {
        this.idArchipel = idArchipel;
    }
}
