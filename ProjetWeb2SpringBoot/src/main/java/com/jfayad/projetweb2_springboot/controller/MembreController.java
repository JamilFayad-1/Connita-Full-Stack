package com.jfayad.projetweb2_springboot.controller;

import com.jfayad.projetweb2_springboot.entities.DemandeAmie;
import com.jfayad.projetweb2_springboot.entities.Membre;
import com.jfayad.projetweb2_springboot.repos.MembreRepository;
import com.jfayad.projetweb2_springboot.services.DemandeAmieService;
import com.jfayad.projetweb2_springboot.services.MembreService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MembreController {

    @Autowired
    private MembreService membreService;

    @Autowired
    private MembreRepository membreRepository;

    @Autowired
    private DemandeAmieService demandeAmieService;

    @GetMapping("/pageAjoutUtilisateur")
    public String getUsers(Model model,
                           HttpServletRequest request) {
        HttpSession session = request.getSession();
        Membre MembreTrouver = (Membre) session.getAttribute("loggedInUser");
        int idMembretrouver = MembreTrouver.getIdMembre();

        List<Membre> listeUtilisateur = membreService.getAllUtilisateurs();
        model.addAttribute("listeUtilisateur", listeUtilisateur);

        List<DemandeAmie> listeDemandeAmie = demandeAmieService.getAllDemandeAmie();
        List<Membre> listeUtilisateurDemandeAmie = new ArrayList<>();
        for( DemandeAmie d : listeDemandeAmie) {
            if(d.getRecevant().getIdMembre().equals(idMembretrouver)){
                int idMembre = d.getEnvoyant().getIdMembre();
                Optional<Membre> membreTrouver = membreRepository.findByIdMembre(idMembre);
                membreTrouver.ifPresent(listeUtilisateurDemandeAmie::add);
            }
        }
        model.addAttribute("listeUtilisateurDemandeAmie", listeUtilisateurDemandeAmie);

        return "pageAjoutUtilisateur";
    }

}
