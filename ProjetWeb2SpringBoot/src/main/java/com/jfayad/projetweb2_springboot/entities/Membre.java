package com.jfayad.projetweb2_springboot.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "membre")
public class Membre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMembre;

    @Column(length = 25, nullable = false)
    private String nom;

    @Column(length = 25, nullable = false)
    private String prenom;

    @Column(length = 45, nullable = false)
    private String email;

    @Column(length = 15, nullable = false)
    private String password;

    @Column(length = 25)
    private String region;

    @Column(length = 25)
    private String langue;

    @Column(length = 255)
    private String photoProfilPath;

    @Column(columnDefinition = "LONGTEXT")
    private String bio;

    @Column(length = 45)
    private String username;

    @Column(nullable = false)
    private int privilege;

    // Getters and Setters

    public Membre(){

    }

    public Membre(String nom, String prenom, String email, String password, String region, String langue, String photoProfilPath, String bio, String username, int privilege) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.region = region;
        this.langue = langue;
        this.photoProfilPath = photoProfilPath;
        this.bio = bio;
        this.username = username;
        this.privilege = privilege;
    }

    public Membre(String nom, String prenom, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
    }

    public Integer getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(Integer idMembre) {
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

    public String getPhotoProfilPath() {
        return photoProfilPath;
    }

    public void setPhotoProfilPath(String photoProfilPath) {
        this.photoProfilPath = photoProfilPath;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }
}
