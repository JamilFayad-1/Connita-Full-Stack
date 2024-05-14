package com.jfayad.projetweb2_springboot.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "demandeamie")
public class DemandeAmie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_envoyant")
    private Membre envoyant;

    @ManyToOne
    @JoinColumn(name = "id_recevant")
    private Membre recevant;

    @Column(name = "status", nullable = false, length = 45)
    private String status;

    // Constructors
    public DemandeAmie() {
    }

    public DemandeAmie(Membre envoyant, Membre recevant, String status) {
        this.envoyant = envoyant;
        this.recevant = recevant;
        this.status = status;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Membre getEnvoyant() {
        return envoyant;
    }

    public void setEnvoyant(Membre envoyant) {
        this.envoyant = envoyant;
    }

    public Membre getRecevant() {
        return recevant;
    }

    public void setRecevant(Membre recevant) {
        this.recevant = recevant;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}