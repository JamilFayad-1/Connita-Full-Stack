package com.jfayad.projetweb2_springboot.controller;

import com.jfayad.projetweb2_springboot.entities.Amitier;
import com.jfayad.projetweb2_springboot.entities.Membre;
import com.jfayad.projetweb2_springboot.services.AmitierService;
import com.jfayad.projetweb2_springboot.services.MembreService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    private MembreService membreService;

    @Autowired
    private AmitierService amitierService;

    @GetMapping("/index")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/pageAccueilUtilisateur")
    public String getPageAccueilUtilisateur(Model model,
                                            HttpServletRequest request) {

        HttpSession session = request.getSession();
        Membre MembreTrouver = (Membre) session.getAttribute("loggedInUser");
        int idMembretrouver = MembreTrouver.getIdMembre();

        List<Amitier> listeIdAmitier = amitierService.getAllAmitiers();

        List<Integer> AmieActuelle = new ArrayList<>();

        for (Amitier amitier : listeIdAmitier) {
            if (amitier.getMembre().getIdMembre() == idMembretrouver) {
                AmieActuelle.add(amitier.getAmie().getIdMembre());
            }
            if(amitier.getAmie().getIdMembre() == idMembretrouver){
                AmieActuelle.add(amitier.getMembre().getIdMembre());
            }
        }

        List<Membre> listeUtilisateur = membreService.getAllUtilisateurs();
        List<Membre> listeAmitier = new ArrayList<>();
        for(Membre membre : listeUtilisateur){
            if(AmieActuelle.contains(membre.getIdMembre())){
                listeAmitier.add(membre);
            }
        }

        model.addAttribute("listeAmitier", listeAmitier);

        return "pageAccueilUtilisateur";
    }

    @GetMapping("/pageUtilisateurReglage")
    public String getPageUtilisateurReglage() { return "pageUtilisateurReglage"; }

    @GetMapping("/challengesJouer")
    public String getPageChallengesJouer() {  return "challengesJouer"; }

    @GetMapping("/pageCountries")
    public String getPageCountries() { return "countries"; }

    @GetMapping("/countryInfo")
    public String getPageCountryInfo() { return "countryInfo"; }

    @GetMapping("/pageLanguages")
    public String getPageLanguages() { return "languages"; }

}
