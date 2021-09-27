package com.animal_crossing.animal_crossing_tp.main;

public class Cinema {
    //ATTRIBUTS
    private int idCinema;
    private String nomCinema;
    private int nbPlacesCinema;
    private int idIle;

    //CONSTRUCTEURS
    public Cinema(int idCinema, String nomCinema, int nbPlacesCinema, int idIle){
        this.idCinema = idCinema;
        this.nomCinema = nomCinema;
        this.nbPlacesCinema = nbPlacesCinema;
        this.idIle = idIle;
    }

    //GETTER ET SETTER
    public int getIdCinema() {
        return idCinema;
    }

    public void setIdCinema(int idCinema) {
        this.idCinema = idCinema;
    }

    public String getNomCinema() {
        return nomCinema;
    }

    public void setNomCinema(String nomCinema) {
        this.nomCinema = nomCinema;
    }

    public int getNbPlacesCinema() {
        return nbPlacesCinema;
    }

    public void setNbPlacesCinema(int nbPlacesCinema) {
        this.nbPlacesCinema = nbPlacesCinema;
    }

    public int getIdIle() {
        return idIle;
    }

    public void setIdIle(int idIle) {
        this.idIle = idIle;
    }
}
