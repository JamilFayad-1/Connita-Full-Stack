package com.jfayad.projetweb2_springboot.services;

import com.jfayad.projetweb2_springboot.entities.Amitier;
import com.jfayad.projetweb2_springboot.entities.Membre;
import com.jfayad.projetweb2_springboot.repos.AmitierRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmitierService {

    private static final Logger logger = LoggerFactory.getLogger(ChallengesJouerService.class);

    @Autowired
    private AmitierRepository amitierRepository;

    @Transactional
    public void createAmitier(Membre idMembre, Membre idAmie) {

        Amitier newAmitier = new Amitier();
        newAmitier.setMembre(idMembre);
        newAmitier.setAmie(idAmie);
        newAmitier.setStatus("accepter");
        amitierRepository.save(newAmitier);
    }

    public List<Amitier> getAllAmitiers() {
        return amitierRepository.findAll();
    }

    public void deleteAmitierByIdMembre(Membre membre) {
        amitierRepository.deleteAllByAmieOrByMembre(membre);
    }

}
