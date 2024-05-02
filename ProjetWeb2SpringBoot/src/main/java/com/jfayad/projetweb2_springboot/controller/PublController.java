package com.jfayad.projetweb2_springboot.controller;

import com.jfayad.projetweb2_springboot.entities.Amitier;
import com.jfayad.projetweb2_springboot.entities.Membre;
import com.jfayad.projetweb2_springboot.services.AmitierService;
import com.jfayad.projetweb2_springboot.services.MembreService;
import com.jfayad.projetweb2_springboot.services.PublicationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.jfayad.projetweb2_springboot.entities.Publication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PublController {
    @Autowired
    private MembreService membreService;

    @Autowired
    private AmitierService amitierService;
    @Autowired
    private PublicationService publicationService;

    @PostMapping("/publier")
    public String publier(Model model, HttpServletRequest request, Publication publication, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "fyp";
        }
        else{
            try {
                String fileName = file.getOriginalFilename();
                publication.setImage(fileName);
                Path path = Paths.get("imageUtilisateur/" + fileName);
                Files.copy(file.getInputStream(), path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            HttpSession session = request.getSession();
            publication.setMembre((Membre) session.getAttribute("loggedInUser"));
            publicationService.save(publication);
            List<Amitier> listeIdAmitier = amitierService.getAllAmitiers();

            List<Integer> AmieActuelle = new ArrayList<>();
            Membre MembreTrouver = (Membre) session.getAttribute("loggedInUser");
            int idMembretrouver = MembreTrouver.getIdMembre();

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
            List<Publication> publications = publicationService.findAllByMembre(MembreTrouver);
            model.addAttribute("publications", publications);
            return "fyp";
        }
    }
    }

