package com.jfayad.projetweb2_springboot.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "forum_reply")
public class ForumReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "forum_id", nullable = false)
    private Forum forumReply;

    @ManyToOne
    @JoinColumn(name = "membre_id", nullable = false)
    private Membre membreReply;

    @Column(name = "reply_content", columnDefinition = "LONGTEXT", nullable = false)
    private String replyContent;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "reply_date", nullable = false)
    private LocalDateTime replyDatePosted;

    @PrePersist
    public void prePersist() {
        replyDatePosted = LocalDateTime.now();
    }

    public ForumReply() {

    }

    public ForumReply(Forum forum, Membre membre, String replyContent) {
        this();
        this.forumReply = forum;
        this.membreReply = membre;
        this.replyContent = replyContent;
    }

    public Forum getForumReply() {
        return forumReply;
    }

    public void setForumReply(Forum forumReply) {
        this.forumReply = forumReply;
    }

    public Membre getMembreReply() {
        return membreReply;
    }

    public void setMembreReply(Membre membreReply) {
        this.membreReply = membreReply;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public LocalDateTime getReplyDatePosted() {
        return replyDatePosted;
    }

    public void setReplyDatePosted(LocalDateTime replyDatePosted) {
        this.replyDatePosted = replyDatePosted;
    }

    @Override
    public String toString() {
        return "ForumReply{" +
                "id=" + id +
                ", forumReply=" + forumReply +
                ", membreReply=" + membreReply +
                ", replyContent='" + replyContent + '\'' +
                ", replyDatePosted=" + replyDatePosted +
                '}';
    }
}
