package com.jfayad.projetweb2_springboot.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class CleinOeil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_clein_oeil")
    private Integer idCleinOeil;

    @ManyToOne
    @JoinColumn(name = "id_envoyant")
    private Membre idEnvoyant;

    @ManyToOne
    @JoinColumn(name = "id_recevant")
    private Membre idRecevant;

    @Column(name = "date_heure_envoyer")
    private LocalDateTime sentDateTime;

    @PrePersist
    public void prePersist() {
        sentDateTime = LocalDateTime.now();
    }

    public CleinOeil() {
    }

    public CleinOeil(Membre idEnvoyant, Membre idRecevant) {
        this.idEnvoyant = idEnvoyant;
        this.idRecevant = idRecevant;
    }

    public Integer getIdCleinOeil() {
        return idCleinOeil;
    }

    public void setIdCleinOeil(Integer idCleinOeil) {
        this.idCleinOeil = idCleinOeil;
    }

    public Membre getIdEnvoyant() {
        return idEnvoyant;
    }

    public void setIdEnvoyant(Membre idEnvoyant) {
        this.idEnvoyant = idEnvoyant;
    }

    public Membre getIdRecevant() {
        return idRecevant;
    }

    public void setIdRecevant(Membre idRecevant) {
        this.idRecevant = idRecevant;
    }

    public LocalDateTime getSentDateTime() {
        return sentDateTime;
    }

    public void setSentDateTime(LocalDateTime sentDateTime) {
        this.sentDateTime = sentDateTime;
    }

    @Override
    public String toString() {
        return "CleinOeil{" +
                "idCleinOeil=" + idCleinOeil +
                ", idEnvoyant=" + idEnvoyant +
                ", idRecevant=" + idRecevant +
                '}';
    }
}

