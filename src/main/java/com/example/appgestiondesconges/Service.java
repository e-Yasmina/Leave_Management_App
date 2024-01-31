package com.example.appgestiondesconges;

public class Service {

    private String id;
    private String nom;
    private String chef ;
    private int maxa;
    private String dept;

    public Service(String id, String nom, String chef, int maxa, String dept) {
        this.id = id;
        this.nom = nom;
        this.chef = chef;
        this.maxa = maxa;
        this.dept = dept;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getChef() {
        return chef;
    }

    public void setChef(String chef) {
        this.chef = chef;
    }

    public int getMaxa() {
        return maxa;
    }

    public void setMaxa(int maxa) {
        this.maxa = maxa;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }
}
