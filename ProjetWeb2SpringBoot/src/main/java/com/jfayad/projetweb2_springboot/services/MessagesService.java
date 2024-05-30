package com.jfayad.projetweb2_springboot.services;

import com.jfayad.projetweb2_springboot.entities.Membre;
import com.jfayad.projetweb2_springboot.entities.Messages;
import com.jfayad.projetweb2_springboot.repos.MessagesRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessagesService {
    @Autowired
    MessagesRepo repo;

    public List<Messages> getAllMessages() {
        return repo.findAll();
    }

    public List<Messages> findConversationById(int id_membre, int id_ami) {
        return repo.findConversation(id_membre, id_ami);
    }

    public void save(Messages messages) {
        repo.save(messages);
    }

    @Transactional
    public void deleteAllMessagesByMembre(Membre membre) {
        repo.deleteAllByMembre(membre);
    }
}
