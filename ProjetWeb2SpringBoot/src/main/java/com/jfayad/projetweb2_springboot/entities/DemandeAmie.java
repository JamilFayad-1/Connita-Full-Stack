package com.jfayad.projetweb2_springboot.entities;

import com.jfayad.projetweb2_springboot.Status.Status;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    // Constructors
    public DemandeAmie() {
    }

    public DemandeAmie(Membre envoyant, Membre recevant, Status status) {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}