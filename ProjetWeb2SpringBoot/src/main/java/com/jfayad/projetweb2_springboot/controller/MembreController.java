package com.jfayad.projetweb2_springboot.controller;

import com.jfayad.projetweb2_springboot.entities.Membre;
import com.jfayad.projetweb2_springboot.services.MembreService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class MembreController {

    @Autowired
    private MembreService membreService;

    @GetMapping("/pageAjoutUtilisateur")
    public String getUsers(Model model) {
        List<Membre> listeUtilisateur = membreService.getAllUtilisateurs();
        model.addAttribute("listeUtilisateur", listeUtilisateur);
        return "pageAjoutUtilisateur";
    }

}
