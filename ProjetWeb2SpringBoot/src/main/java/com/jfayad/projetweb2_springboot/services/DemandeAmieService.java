package com.jfayad.projetweb2_springboot.services;

import com.jfayad.projetweb2_springboot.entities.DemandeAmie;
import com.jfayad.projetweb2_springboot.entities.Membre;
import com.jfayad.projetweb2_springboot.repos.DemandeAmieRepository;
import com.jfayad.projetweb2_springboot.repos.MembreRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        demandeAmie.setStatus("attente");

        demandeAmieRepository.save(demandeAmie);
    }

    @Transactional
    public void supprimerDemandeAmieParEnvoyant(int idMembre) {
        Membre envoyant = membreRepository.findById(idMembre)
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        demandeAmieRepository.deleteByRecevant(envoyant);
    }

    public List<DemandeAmie> getAllDemandeAmie(){
        return demandeAmieRepository.findAll();
    }

}
