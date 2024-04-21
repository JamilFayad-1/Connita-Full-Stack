package com.jfayad.projetweb2_springboot.controller;

import com.jfayad.projetweb2_springboot.services.MembreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @Autowired
    private MembreService membreService;

    @GetMapping("/index")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/pageAccueilUtilisateur")
    public String getPageAccueilUtilisateur() {
        return "pageAccueilUtilisateur";
    }

    @GetMapping("/pageUtilisateurReglage")
    public String getPageUtilisateurReglage() { return "pageUtilisateurReglage"; }

    @GetMapping("/challengesJouer")
    public String getPageChallengesJouer() {  return "challengesJouer"; }

    @GetMapping("/pageAjoutUtilisateur")
    public String getPageAjoutUtilisateur() { return "pageAjoutUtilisateur"; }

}
