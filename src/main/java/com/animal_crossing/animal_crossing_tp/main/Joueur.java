package com.animal_crossing.animal_crossing_tp.main;

public class Joueur {
    //ATTRIBUTS
    private int idJoueur;
    private String nomJoueur;
    private String prenomJoueur;
    private String mailJoueur;

    //CONSTRUCTEURS
    public Joueur(int idJoueur, String nomJoueur, String prenomJoueur, String mailJoueur){
        this.idJoueur = idJoueur;
        this.nomJoueur = nomJoueur;
        this.prenomJoueur = prenomJoueur;
        this.mailJoueur = mailJoueur;
    }

    //GETTER ET SETTER
    public int getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    public String getNomJoueur() {
        return nomJoueur;
    }

    public void setNomJoueur(String nomJoueur) {
        this.nomJoueur = nomJoueur;
    }

    public String getPrenomJoueur() {
        return prenomJoueur;
    }

    public void setPrenomJoueur(String prenomJoueur) {
        this.prenomJoueur = prenomJoueur;
    }

    public String getMailJoueur() {
        return mailJoueur;
    }

    public void setMailJoueur(String mailJoueur) {
        this.mailJoueur = mailJoueur;
    }
}
