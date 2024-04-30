package com.jfayad.projetweb2_springboot.controller;


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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MessagerieController {

    @Autowired
    private MembreService membreService;

    @Autowired
    private AmitierService amitierService;

    @GetMapping("/pageMessagerie")
    public String pageMessagerie(HttpServletRequest request,
                                 Model model) {

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

        return "Messagerie";
    }

}
