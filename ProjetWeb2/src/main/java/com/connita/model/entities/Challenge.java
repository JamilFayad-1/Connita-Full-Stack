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
    private int challengeId;
    private String challengeName;
    private String challengeDescription;
    private String challengeImageUrl;

    // Constructors
    public Challenge() {
    }

    public Challenge(int challengeId, String challengeName, String challengeDescription, String challengeImageUrl) {
        this.challengeId = challengeId;
        this.challengeName = challengeName;
        this.challengeDescription = challengeDescription;
        this.challengeImageUrl = challengeImageUrl;
    }
    
    public Challenge(String challengeName, String challengeDescription, String challengeImageUrl) {
        this.challengeName = challengeName;
        this.challengeDescription = challengeDescription;
        this.challengeImageUrl = challengeImageUrl;
    }

    // Getters and setters
    public int getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(int challengeId) {
        this.challengeId = challengeId;
    }

    public String getChallengeName() {
        return challengeName;
    }

    public void setChallengeName(String challengeName) {
        this.challengeName = challengeName;
    }

    public String getChallengeDescription() {
        return challengeDescription;
    }

    public void setChallengeDescription(String challengeDescription) {
        this.challengeDescription = challengeDescription;
    }

    public String getChallengeImageUrl() {
        return challengeImageUrl;
    }

    public void setChallengeImageUrl(String challengeImageUrl) {
        this.challengeImageUrl = challengeImageUrl;
    }

    // Override toString method for debugging or logging purposes
    @Override
    public String toString() {
        return "Challenge{" +
                "challengeId=" + challengeId +
                ", challengeName='" + challengeName + '\'' +
                ", challengeDescription='" + challengeDescription + '\'' +
                ", challengeImageUrl='" + challengeImageUrl + '\'' +
                '}';
    }
}
