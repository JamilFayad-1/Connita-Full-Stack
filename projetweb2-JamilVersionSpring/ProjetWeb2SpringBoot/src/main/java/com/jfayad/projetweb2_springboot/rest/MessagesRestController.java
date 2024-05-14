package com.jfayad.projetweb2_springboot.rest;

import com.jfayad.projetweb2_springboot.entities.Membre;
import com.jfayad.projetweb2_springboot.entities.Messages;
import com.jfayad.projetweb2_springboot.services.MembreService;
import com.jfayad.projetweb2_springboot.services.MessagesService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class MessagesRestController {
    @Autowired
    private final MembreService membreService;
    @Autowired
    private MessagesService messagesService;

    public MessagesRestController(MembreService membreService) {
        this.membreService = membreService;
    }

    @GetMapping("/conversation/{id_ami}")
    public List<Messages> pullFullConv(Model model, HttpServletRequest request, @PathVariable("id_ami") int id_ami) {
        HttpSession session = request.getSession();
        Membre membre = (Membre) session.getAttribute("loggedInUser");
        List<Messages> messages =  messagesService.findConversationById(membre.getIdMembre(), id_ami);
        return messages;
    }
    @PostMapping("/conversation/{id_ami}/send")
    public Messages sendMessage(Model model, HttpServletRequest request, @PathVariable("id_ami") int id_ami, @RequestBody String message) {
        HttpSession session = request.getSession();
        Membre membre = (Membre) session.getAttribute("loggedInUser");
        Messages messages = new Messages();
        messages.setMembre(membre);
        messages.setAmi(membreService.findById(id_ami));
        JSONObject json = new JSONObject(message);
        String messageContent = json.getString("message");
        messages.setMessage(messageContent);
        messages.setCreated_at(new Date());
        messagesService.save(messages);
        return messages;

    }
}
