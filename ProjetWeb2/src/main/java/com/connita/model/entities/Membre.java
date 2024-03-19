package com.connita.model.entities;


public class Membre {
    private int idMembre;
    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String region;
    private String langue;
    private String photoProfil;
    private String bio;
    private int privilege;

    // Constructeur
    public Membre(int idMembre, String nom, String prenom, String email, String password, String region, String langue, String photoProfil, String bio, int privilege) {
        this.idMembre = idMembre;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.region = region;
        this.langue = langue;
        this.photoProfil = photoProfil;
        this.bio = bio;
        this.privilege = privilege;
    }

    public Membre() {
        this.nom = "";
        this.prenom = "";
        this.email = "";
        this.password = "";
        this.region = null;
        this.langue = null;
        this.photoProfil = null;
        this.bio = null;
        this.privilege = 0;
    }

    public Membre(String nom, String prenom, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.region = null;
        this.langue = null;
        this.photoProfil = null;
        this.bio = null;
        this.privilege = 0;
    }

    // Getters et Setters
    public int getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(int idMembre) {
        this.idMembre = idMembre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public String getPhotoProfil() {
        return photoProfil;
    }

    public void setPhotoProfil(String photoProfil) {
        this.photoProfil = photoProfil;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }
}
