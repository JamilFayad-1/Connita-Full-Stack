package com.jfayad.projetweb2_springboot.entities;

import jakarta.persistence.*;
import org.hibernate.engine.spi.Status;

@Entity
@Table(name = "amitier")
public class Amitier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_membre")
    private Membre membre;

    @ManyToOne
    @JoinColumn(name = "id_amie")
    private Membre amie;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    // Constructors
    public Amitier() {
    }

    public Amitier(Membre membre, Membre amie, Status status) {
        this.membre = membre;
        this.amie = amie;
        this.status = status;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public Membre getAmie() {
        return amie;
    }

    public void setAmie(Membre amie) {
        this.amie = amie;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

