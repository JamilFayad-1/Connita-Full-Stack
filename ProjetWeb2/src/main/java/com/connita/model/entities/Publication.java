package com.connita.model.entities;


public class Publication {
    private int id;
    private String titre;
    private String description;
    private String image;
    private int likes;
    private int idMembre;

    public Publication(int id, String titre, String description, String image, int likes, int idMembre) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.image = image;
        this.likes = likes;
        this.idMembre = idMembre;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public int getLikes() {
        return likes;
    }

    public int getIdMembre() {
        return idMembre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setIdMembre(int idMembre) {
        this.idMembre = idMembre;
    }
    
}
