package com.jfayad.projetweb2_springboot.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_chat")
    private Long idChat;

    @Column(name = "id_envoyant")
    private Long idEnvoyant;

    @Column(name = "id_recevant")
    private Long idRecevant;

    @Column(name = "message", length = 500)
    private String message;

    @Column(name = "temp_envoyer")
    private LocalDateTime tempEnvoyer;

    public Chat(Long idEnvoyant, Long idRecevant, String message, LocalDateTime tempEnvoyer) {
        this.idEnvoyant = idEnvoyant;
        this.idRecevant = idRecevant;
        this.message = message;
        this.tempEnvoyer = tempEnvoyer;
    }

    public Chat() {

    }

    public Long getIdChat() {
        return idChat;
    }

    public void setIdChat(Long idChat) {
        this.idChat = idChat;
    }

    public Long getIdEnvoyant() {
        return idEnvoyant;
    }

    public void setIdEnvoyant(Long idEnvoyant) {
        this.idEnvoyant = idEnvoyant;
    }

    public Long getIdRecevant() {
        return idRecevant;
    }

    public void setIdRecevant(Long idRecevant) {
        this.idRecevant = idRecevant;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTempEnvoyer() {
        return tempEnvoyer;
    }

    public void setTempEnvoyer(LocalDateTime tempEnvoyer) {
        this.tempEnvoyer = tempEnvoyer;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "idChat=" + idChat +
                ", idEnvoyant=" + idEnvoyant +
                ", idRecevant=" + idRecevant +
                ", message='" + message + '\'' +
                ", tempEnvoyer=" + tempEnvoyer +
                '}';
    }
}
