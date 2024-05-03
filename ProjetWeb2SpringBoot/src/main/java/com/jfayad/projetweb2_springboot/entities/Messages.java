package com.jfayad.projetweb2_springboot.entities;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="messages")
public class Messages {
    @Id
    @Column(name = "id_message")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_message;
    @ManyToOne
    @JoinColumn(name="id_membre")
    private Membre membre;
    @ManyToOne
    @JoinColumn(name="id_ami")
    private Membre ami;
    @Column(name="message", length = 1000, nullable=false)
    private String message;
    @Column(name="created_at")
    private Date created_at;

    public Messages(int id_message, Membre membre, Membre ami, String message, Date created_at) {
        this.id_message = id_message;
        this.membre = membre;
        this.ami = ami;
        this.message = message;
        this.created_at = created_at;
    }

    public Messages() {

    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Membre getMembre() {
        return membre;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Membre getAmi() {
        return ami;
    }

    public void setAmi(Membre ami) {
        this.ami = ami;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public int getId_message() {
        return id_message;
    }

    public void setId_message(int id_message) {
        this.id_message = id_message;
    }
}
