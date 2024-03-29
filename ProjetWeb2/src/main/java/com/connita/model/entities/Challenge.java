/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.connita.model.entities;

/**
 *
 * @author Jamil
 */
public class Challenge {
    private int idChallenge;
    private int idMembre;
    private boolean firstSetComplete;
    private boolean secondSetComplete;
    private boolean thirdSetComplete;

    // Constructor
    public Challenge(int idMembre, boolean firstSetComplete, boolean secondSetComplete, boolean thirdSetComplete) {
        this.idMembre = idMembre;
        this.firstSetComplete = firstSetComplete;
        this.secondSetComplete = secondSetComplete;
        this.thirdSetComplete = thirdSetComplete;
    }

    // Getters and setters
    public int getIdChallenge() {
        return idChallenge;
    }

    public void setIdChallenge(int idChallenge) {
        this.idChallenge = idChallenge;
    }

    public int getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(int idMembre) {
        this.idMembre = idMembre;
    }

    public boolean isFirstSetComplete() {
        return firstSetComplete;
    }

    public void setFirstSetComplete(boolean firstSetComplete) {
        this.firstSetComplete = firstSetComplete;
    }

    public boolean isSecondSetComplete() {
        return secondSetComplete;
    }

    public void setSecondSetComplete(boolean secondSetComplete) {
        this.secondSetComplete = secondSetComplete;
    }

    public boolean isThirdSetComplete() {
        return thirdSetComplete;
    }

    public void setThirdSetComplete(boolean thirdSetComplete) {
        this.thirdSetComplete = thirdSetComplete;
    }
}
