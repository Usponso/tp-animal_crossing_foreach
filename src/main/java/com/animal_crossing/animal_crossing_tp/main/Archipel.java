package com.animal_crossing.animal_crossing_tp.main;

public class Archipel {
    //ATTRIBUTS
    private int idArchipel;
    private String nomArchipel;
    private String localisationArchipel;
    private int idJoueur;

    //CONSTRUCTEURS
    public Archipel(int idArchipel, String nomArchipel, String localisationArchipel, int idJoueur){
        this.idArchipel = idArchipel;
        this.nomArchipel = nomArchipel;
        this.localisationArchipel = localisationArchipel;
        this.idJoueur = idJoueur;
    }

    //GETTER ET SETTER
    public int getIdArchipel() {
        return idArchipel;
    }

    public void setIdArchipel(int idArchipel) {
        this.idArchipel = idArchipel;
    }

    public String getNomArchipel() {
        return nomArchipel;
    }

    public void setNomArchipel(String nomArchipel) {
        this.nomArchipel = nomArchipel;
    }

    public String getLocalisationArchipel() {
        return localisationArchipel;
    }

    public void setLocalisationArchipel(String localisationArchipel) {
        this.localisationArchipel = localisationArchipel;
    }

    public int getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }
}
