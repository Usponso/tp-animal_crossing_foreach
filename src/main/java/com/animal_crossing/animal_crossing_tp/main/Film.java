package com.animal_crossing.animal_crossing_tp.main;

public class Film {
    //ATTRIBUTS
    private int idFilm;
    private String nomFilm;
    private int nbPlaces;

    //CONSTRUCTEURS
    public Film(int idFilm, String nomFilm, int nbPlaces){
        this.idFilm = idFilm;
        this.nomFilm = nomFilm;
        this.nbPlaces = nbPlaces;
    }

    //GETTER ET SETTER
    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public String getNomFilm() {
        return nomFilm;
    }

    public void setNomFilm(String nomFilm) {
        this.nomFilm = nomFilm;
    }
}
