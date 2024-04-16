package com.jfayad.projetweb2_springboot.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "challengeselement")
public class Challenges {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "challenge_id")
    private Integer challengeId;

    @Column(name = "challenge_name", length = 100, nullable = false)
    private String challengeName;

    @Column(name = "challenge_description", length = 100, nullable = false)
    private String challengeDescription;

    @Column(name = "challenge_image_url", length = 255, nullable = false)
    private String challengeImageUrl;

    // Constructors
    public Challenges() {
    }

    public Challenges(String challengeName, String challengeDescription, String challengeImageUrl) {
        this.challengeName = challengeName;
        this.challengeDescription = challengeDescription;
        this.challengeImageUrl = challengeImageUrl;
    }

    // Getters and Setters

    public Integer getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(Integer challengeId) {
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
