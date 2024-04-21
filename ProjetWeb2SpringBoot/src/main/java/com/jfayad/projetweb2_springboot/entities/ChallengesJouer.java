package com.jfayad.projetweb2_springboot.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "challenges")
public class ChallengesJouer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_challenge")
    private Integer idChallenge;

    @Column(name = "id_membre")
    private Integer idMembre;

    @Column(name = "challenge_name", length = 100, nullable = false)
    private String challengeName;

    @Column(name = "first_set_complete", nullable = false)
    private byte firstSetComplete;

    @Column(name = "second_set_complete", nullable = false)
    private byte secondSetComplete;

    @Column(name = "third_set_complete", nullable = false)
    private byte thirdSetComplete;

    // Constructors
    public ChallengesJouer() {
    }

    public ChallengesJouer(Integer idMembre, String challengeName) {
        this.idMembre = idMembre;
        this.challengeName = challengeName;
        // Initialize completion status to false by default
        this.firstSetComplete = 0;
        this.secondSetComplete = 0;
        this.thirdSetComplete = 0;
    }

    // Getters and Setters

    public Integer getIdChallenge() {
        return idChallenge;
    }

    public void setIdChallenge(Integer idChallenge) {
        this.idChallenge = idChallenge;
    }

    public Integer getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(Integer idMembre) {
        this.idMembre = idMembre;
    }

    public String getChallengeName() {
        return challengeName;
    }

    public void setChallengeName(String challengeName) {
        this.challengeName = challengeName;
    }

    public byte isFirstSetComplete() {
        return firstSetComplete;
    }

    public void setFirstSetComplete(byte firstSetComplete) {
        this.firstSetComplete = firstSetComplete;
    }

    public byte isSecondSetComplete() {
        return secondSetComplete;
    }

    public void setSecondSetComplete(byte secondSetComplete) {
        this.secondSetComplete = secondSetComplete;
    }

    public byte isThirdSetComplete() {
        return thirdSetComplete;
    }

    public void setThirdSetComplete(byte thirdSetComplete) {
        this.thirdSetComplete = thirdSetComplete;
    }

    // Override toString method for debugging or logging purposes
    @Override
    public String toString() {
        return "Challenge{" +
                "idChallenge=" + idChallenge +
                ", idMembre=" + idMembre +
                ", challengeName='" + challengeName + '\'' +
                ", firstSetComplete=" + firstSetComplete +
                ", secondSetComplete=" + secondSetComplete +
                ", thirdSetComplete=" + thirdSetComplete +
                '}';
    }
}
