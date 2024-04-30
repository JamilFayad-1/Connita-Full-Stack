package com.jfayad.projetweb2_springboot.controller;

import com.jfayad.projetweb2_springboot.services.DemandeAmieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemandeAmieController {

    @Autowired
    private DemandeAmieService demandeAmieService;

    @PostMapping("/envoyerDemandeAmie")
    public String envoyerDemandeAmie(@RequestParam("idMembre") int idMembre,
                                     @RequestParam("idAmie") int idAmie) {

        boolean requestFailed = true;

        try{
            demandeAmieService.envoyerDemandeAmie(idMembre, idAmie);
            requestFailed = false;
        }catch(Exception e){
            e.printStackTrace();
        }

        if (requestFailed) {
            return "Friend request failed";
        }
        return "Friend request sent successfully";
    }

}
