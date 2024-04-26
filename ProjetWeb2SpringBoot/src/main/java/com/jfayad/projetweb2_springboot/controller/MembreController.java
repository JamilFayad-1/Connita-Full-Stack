package com.jfayad.projetweb2_springboot.controller;

import com.jfayad.projetweb2_springboot.entities.DemandeAmie;
import com.jfayad.projetweb2_springboot.entities.Amitier;
import com.jfayad.projetweb2_springboot.entities.Membre;
import com.jfayad.projetweb2_springboot.repos.MembreRepository;
import com.jfayad.projetweb2_springboot.services.AmitierService;
import com.jfayad.projetweb2_springboot.services.DemandeAmieService;
import com.jfayad.projetweb2_springboot.services.MembreService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @Autowired
    private AmitierService amitierService;

    @GetMapping("/pageAjoutUtilisateur")
    public String getUsers(Model model,
                           HttpServletRequest request) {
        HttpSession session = request.getSession();
        Membre MembreTrouver = (Membre) session.getAttribute("loggedInUser");
        int idMembretrouver = MembreTrouver.getIdMembre();

        List<Membre> listeToutUtilisateur = membreService.getAllUtilisateurs();
        List<Amitier> listeAmitier = amitierService.getAllAmitiers();
        List<Membre> listeUtilisateur = new ArrayList<>();

        List<Integer> AmieActuelle = new ArrayList<>();
        for (Amitier amitier : listeAmitier) {
            if (amitier.getMembre().getIdMembre() == idMembretrouver) {
                AmieActuelle.add(amitier.getAmie().getIdMembre());
            }
            if(amitier.getAmie().getIdMembre() == idMembretrouver){
                AmieActuelle.add(amitier.getMembre().getIdMembre());
            }
        }

        for(Membre membre : listeToutUtilisateur){
            if(!AmieActuelle.contains(membre.getIdMembre())){
                listeUtilisateur.add(membre);
            }
        }

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

    @PostMapping("/reloadFriendsList")
    public String getUsersNonFriends(Model model,
                                           HttpServletRequest request) {

        HttpSession session = request.getSession();
        Membre MembreTrouver = (Membre) session.getAttribute("loggedInUser");
        int idMembretrouver = MembreTrouver.getIdMembre();

        List<Membre> listeToutUtilisateur = membreService.getAllUtilisateurs();
        List<Amitier> listeAmitier = amitierService.getAllAmitiers();
        List<Membre> listeUtilisateur = new ArrayList<>();

        List<Integer> AmieActuelle = new ArrayList<>();
        for (Amitier amitier : listeAmitier) {
            if (amitier.getMembre().getIdMembre() == idMembretrouver) {
                AmieActuelle.add(amitier.getAmie().getIdMembre());
            }
            if(amitier.getAmie().getIdMembre() == idMembretrouver){
                AmieActuelle.add(amitier.getMembre().getIdMembre());
            }
        }

        for(Membre membre : listeToutUtilisateur){
            if(!AmieActuelle.contains(membre.getIdMembre())){
                listeUtilisateur.add(membre);
            }
        }

        model.addAttribute("listeUtilisateur", listeUtilisateur);
        return "listeAmie :: friendsListFragment";
    }

}
