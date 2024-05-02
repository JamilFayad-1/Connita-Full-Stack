package com.jfayad.projetweb2_springboot.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "publication")
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_publication")
    private int id_publication;
    @Column(nullable = true)
    private String titre;
    @Column()
    private String description;
    @Column
    private String image;
    @Column
    private int likes;

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public int getId_publication() {
        return id_publication;
    }

    public void setId_publication(int id_publication) {
        this.id_publication = id_publication;
    }

    @ManyToOne
    @JoinColumn(name="id_membre")
    private Membre membre;
    public Publication(){}
    public Publication(int id, String titre, String description, String image, int likes, Membre membre) {
        this.id_publication = id;
        this.titre = titre;
        this.description = description;
        this.image = image;
        this.likes = likes;
        this.membre = membre;
    }

    public Publication(int id, String description, String image, int likes, Membre membre) {
        this.id_publication = id;
        this.titre = "";
        this.description = description;
        this.image = image;
        this.likes = likes;
        this.membre = membre;
    }

    public int getId() {
        return id_publication;
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

    public Membre getMembre() {
        return membre;
    }

    public void setId(int id) {
        this.id_publication = id;
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


}

