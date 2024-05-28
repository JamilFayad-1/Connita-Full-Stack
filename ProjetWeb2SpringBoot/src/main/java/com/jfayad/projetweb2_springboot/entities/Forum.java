package com.jfayad.projetweb2_springboot.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "forum")
public class Forum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("idForum")
    private Integer idForum;

    @ManyToOne
    @JoinColumn(name = "membre_id", nullable = false)
    private Membre membre;

    @Column(name = "forum_title", length = 150, nullable = false)
    private String forumTitle;

    @Column(name = "forum_content" ,columnDefinition = "LONGTEXT", nullable = false)
    private String forumContent;

    @Column(name = "forum_replies_number", nullable = false)
    private Integer forumRepliesNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "forum_date" ,nullable = false)
    private LocalDateTime forumDatePosted;

    @PrePersist
    public void prePersist() {
        forumDatePosted = LocalDateTime.now();
    }

    public Forum(){

    }

    public Forum(Membre membre, String forumTitle, String forumContent) {
        this();
        this.membre = membre;
        this.forumTitle = forumTitle;
        this.forumContent = forumContent;
        this.forumRepliesNumber = 0;
    }

    public Integer getIdForum() {
        return idForum;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public String getForumTitle() {
        return forumTitle;
    }

    public void setForumTitle(String forumTitle) {
        this.forumTitle = forumTitle;
    }

    public String getForumContent() {
        return forumContent;
    }

    public void setForumContent(String forumContent) {
        this.forumContent = forumContent;
    }

    public LocalDateTime getForumDatePosted() {
        return forumDatePosted;
    }

    public void setForumDatePosted(LocalDateTime forumDatePosted) {
        this.forumDatePosted = forumDatePosted;
    }

    public Integer getForumRepliesNumber() {
        return forumRepliesNumber;
    }

    public void setForumRepliesNumber(Integer forumRepliesNumber) {
        this.forumRepliesNumber = forumRepliesNumber;
    }

    @Override
    public String toString() {
        return "Forum{" +
                "idForum=" + idForum +
                ", membre=" + membre +
                ", forumTitle='" + forumTitle + '\'' +
                ", forumContent='" + forumContent + '\'' +
                ", forumDatePosted=" + forumDatePosted +
                '}';
    }
}