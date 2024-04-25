package com.jfayad.projetweb2_springboot.controller;

import com.jfayad.projetweb2_springboot.entities.Membre;
import com.jfayad.projetweb2_springboot.repos.MembreRepository;
import com.jfayad.projetweb2_springboot.services.AmitierService;
import com.jfayad.projetweb2_springboot.services.DemandeAmieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AmitierController {

    private static final Logger logger = LoggerFactory.getLogger(AmitierController.class);

    @Autowired
    private AmitierService amitierService;

    @Autowired
    private MembreRepository membreRepository;

    @Autowired
    private DemandeAmieService demandeAmieService;

    @PostMapping("/AccepterAmitier")
    public String AccepterAmitier(@RequestParam("idMembre") int idMembre,
                                  @RequestParam("idAmie") int idAmitier,
                                  Model model) {

        try{
            Optional<Membre> membreMembre = membreRepository.findByIdMembre(idMembre);
            Optional<Membre> membreAmie = membreRepository.findByIdMembre(idAmitier);

            if(membreMembre.isPresent() && membreAmie.isPresent()){
                Membre membre = membreMembre.get();
                Membre amitier = membreAmie.get();
                amitierService.createAmitier(membre, amitier);
                demandeAmieService.supprimerDemandeAmieParEnvoyant(idMembre);
            }else{
                return "Friendship was not created successfully";
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return "Friendship was created successfully";

    }

    @PostMapping("/SupprimerAmitier")
    public String RefuserAmitier(@RequestParam int idMembre,
                                 Model model){

        try{
            demandeAmieService.supprimerDemandeAmieParEnvoyant(idMembre);
        }catch(Exception e){
            e.printStackTrace();
        }
        return "Friend request declined successfully";
    }

}
