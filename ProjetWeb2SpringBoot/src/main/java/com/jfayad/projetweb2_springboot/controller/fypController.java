package com.jfayad.projetweb2_springboot.controller;
import com.jfayad.projetweb2_springboot.entities.Amitier;
import com.jfayad.projetweb2_springboot.entities.Membre;
import com.jfayad.projetweb2_springboot.entities.Publication;
import com.jfayad.projetweb2_springboot.services.AmitierService;
import com.jfayad.projetweb2_springboot.services.MembreService;
import com.jfayad.projetweb2_springboot.services.PublicationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class fypController {
    @Autowired
    private MembreService membreService;

    @Autowired
    private AmitierService amitierService;
    @Autowired
    private PublicationService publicationService;

    @GetMapping("/fyp")
    public String fyp(Model model,
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
                if (amitier.getAmie().getIdMembre() == idMembretrouver) {
                    AmieActuelle.add(amitier.getMembre().getIdMembre());
                }
            }
            List<Membre> listeUtilisateur = membreService.getAllUtilisateurs();
            List<Membre> listeAmitier = new ArrayList<>();
            for (Membre membre : listeUtilisateur) {
                if (AmieActuelle.contains(membre.getIdMembre())) {
                    listeAmitier.add(membre);
                }
            }
        List<Publication> publications = new ArrayList<>();
        for (Membre ami : listeAmitier) {
            for(Publication publication : publicationService.findAllByMembre(ami)){
                publications.add(publication);
            }
        }
            Publication publicationNvll = new Publication();
            model.addAttribute("publication", publicationNvll);
            model.addAttribute("publications", publications);
            model.addAttribute("listeAmitier", listeAmitier);
            return "fyp";
        }
        @GetMapping("/yourContent")
        public String fypYourWork(Model model, HttpServletRequest request) {
            HttpSession session = request.getSession();
            Membre MembreTrouver = (Membre) session.getAttribute("loggedInUser");
            int idMembretrouver = MembreTrouver.getIdMembre();

            List<Amitier> listeIdAmitier = amitierService.getAllAmitiers();

            List<Integer> AmieActuelle = new ArrayList<>();

            for (Amitier amitier : listeIdAmitier) {
                if (amitier.getMembre().getIdMembre() == idMembretrouver) {
                    AmieActuelle.add(amitier.getAmie().getIdMembre());
                }
                if (amitier.getAmie().getIdMembre() == idMembretrouver) {
                    AmieActuelle.add(amitier.getMembre().getIdMembre());
                }
            }
            List<Membre> listeUtilisateur = membreService.getAllUtilisateurs();
            List<Membre> listeAmitier = new ArrayList<>();
            for (Membre membre : listeUtilisateur) {
                if (AmieActuelle.contains(membre.getIdMembre())) {
                    listeAmitier.add(membre);
                }
            }
            Publication publicationNvll = new Publication();
            model.addAttribute("publication", publicationNvll);
            List<Publication> publications = publicationService.findAllByMembre(MembreTrouver);
            model.addAttribute("publications", publications);
            model.addAttribute("listeAmitier", listeAmitier);
            return "fyp";
        }
        }
