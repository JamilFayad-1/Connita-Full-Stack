package com.jfayad.projetweb2_springboot.controller;

import com.jfayad.projetweb2_springboot.entities.Membre;
import com.jfayad.projetweb2_springboot.services.MembreService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MembreController {

    @Autowired
    private MembreService membreService;

    @GetMapping("/allUsers")
    public void getAllUsers(HttpSession session) {

        List<Membre> listeCompleteMembres = membreService.getAllUtilisateurs();
        session.setAttribute("listeCompleteMembres", listeCompleteMembres);

        session.setAttribute("listeMembres", listeCompleteMembres);
    }

}
