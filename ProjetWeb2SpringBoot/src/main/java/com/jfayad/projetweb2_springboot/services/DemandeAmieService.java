package com.jfayad.projetweb2_springboot.services;

import com.jfayad.projetweb2_springboot.Status.Status;
import com.jfayad.projetweb2_springboot.entities.DemandeAmie;
import com.jfayad.projetweb2_springboot.entities.Membre;
import com.jfayad.projetweb2_springboot.repos.DemandeAmieRepository;
import com.jfayad.projetweb2_springboot.repos.MembreRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemandeAmieService {

    @Autowired
    private DemandeAmieRepository demandeAmieRepository;

    @Autowired
    private MembreRepository membreRepository;

    @Transactional
    public void envoyerDemandeAmie(int idMembre, int idAmie) {

        Membre envoyant = membreRepository.findById(idMembre).orElseThrow(() -> new RuntimeException("Sender not found"));
        Membre recevant = membreRepository.findById(idAmie).orElseThrow(() -> new RuntimeException("Recipient not found"));

        DemandeAmie demandeAmie = new DemandeAmie();
        demandeAmie.setEnvoyant(envoyant);
        demandeAmie.setRecevant(recevant);
        demandeAmie.setStatus(Status.attente);

        demandeAmieRepository.save(demandeAmie);
    }

}
