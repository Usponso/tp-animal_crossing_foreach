package com.animal_crossing.animal_crossing_tp.main;

public class EspaceNaturel {
    //ATTRIBUTS
    private int idEspace;
    private String nomEspace;
    private int idTypeEspace;
    private int idIle;

    //CONSTRUCTEURS
    public EspaceNaturel(int idEspace, String nomEspace, int idTypeEspace, int idIle){
        this.idEspace = idEspace;
        this.nomEspace = nomEspace;
        this.idTypeEspace = idTypeEspace;
        this.idIle = idIle;
    }

    //GETTER ET SETTER
    public int getIdEspace() {
        return idEspace;
    }

    public void setIdEspace(int idEspace) {
        this.idEspace = idEspace;
    }

    public String getNomEspace() {
        return nomEspace;
    }

    public void setNomEspace(String nomEspace) {
        this.nomEspace = nomEspace;
    }

    public int getIdTypeEspace() {
        return idTypeEspace;
    }

    public void setIdTypeEspace(int idTypeEspace) {
        this.idTypeEspace = idTypeEspace;
    }

    public int getIdIle() {
        return idIle;
    }

    public void setIdIle(int idIle) {
        this.idIle = idIle;
    }
}
