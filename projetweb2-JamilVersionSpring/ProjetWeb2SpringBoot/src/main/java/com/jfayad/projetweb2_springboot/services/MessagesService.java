package com.jfayad.projetweb2_springboot.services;

import com.jfayad.projetweb2_springboot.entities.Messages;
import com.jfayad.projetweb2_springboot.repos.MessagesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessagesService {
    @Autowired
    MessagesRepo repo;
    public List<Messages> getAllMessages() {
        return repo.findAll();
    }
    public List<Messages> findConversationById(int id_membre, int id_ami){
        return repo.findConversation(id_membre, id_ami);
    }

    public void save(Messages messages) {
        repo.save(messages);
    }
}
