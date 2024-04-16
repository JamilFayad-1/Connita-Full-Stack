package com.jfayad.projetweb2_springboot.services;

import com.jfayad.projetweb2_springboot.controller.AuthController;
import com.jfayad.projetweb2_springboot.entities.Membre;
import com.jfayad.projetweb2_springboot.repos.MembreRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Optional;

@Service
public class MembreService {

    private static final Logger logger = LoggerFactory.getLogger(MembreService.class);

    @Autowired
    private MembreRepository membreRepository;

    @Transactional
    public Membre connexionMembre(String email, String password) {

        try {
            return membreRepository.findMembreByEmailAndPassword(email, password);
        } catch (Exception e) {
            logger.error("Error occurred while attempting to find member by email and password: {}", e.getMessage());
            return null;
        }
    }

    @Transactional
    public Membre inscriptionMembre(String firstName, String lastName, String email, String password) {

        try {
            Membre membre = new Membre();
            membre.setPrenom(firstName);
            membre.setNom(lastName);
            membre.setEmail(email);
            membre.setPassword(password);
            membreRepository.save(membre);
            return membre;
        } catch (Exception e) {
            logger.error("Error occurred while attempting to create a new member: {}", e.getMessage());
            return null;
        }
    }

    public boolean updateProfile(Membre membre) {
        if (" ".equals(membre.getUsername()) && (membre.getPhotoProfilPath() != null) && " ".equals(membre.getPhotoProfilPath()) && " ".equals(membre.getBio()) && " ".equals(membre.getRegion())) {
            // No changes to be made, return true indicating success
            return true;
        }

        try {
            Optional<Membre> existingMembreOptional = membreRepository.findByEmail(membre.getEmail());
            if (existingMembreOptional.isPresent()) {
                Membre existingMembre = existingMembreOptional.get();
                // Update only non-empty fields
                if (!"".equals(membre.getUsername())) {
                    logger.info("New Username: {}", membre.getUsername());
                    existingMembre.setUsername(membre.getUsername());
                }
                if (membre.getPhotoProfilPath() != "" && membre.getPhotoProfilPath() != null) {
                    logger.info("New Photo: {}", membre.getPhotoProfilPath());
                    existingMembre.setPhotoProfilPath(membre.getPhotoProfilPath());
                }
                if (!"".equals(membre.getBio())) {
                    logger.info("New Bio: {}", membre.getBio());
                    existingMembre.setBio(membre.getBio());
                }
                if (!"".equals(membre.getRegion())) {
                    logger.info("New Region: {}", membre.getRegion());
                    existingMembre.setRegion(membre.getRegion());
                }
                membreRepository.save(existingMembre);
                return true; // Update successful
            } else {
                return false; // User not found
            }
        } catch (Exception e) {
            // Handle exception
            return false; // Update failed
        }
    }

    public boolean updatePassword(Membre membre, String newPassword) {
        Optional<Membre> membreRecuOptionnel = membreRepository.findByEmail(membre.getEmail());


        if(membreRecuOptionnel.isPresent()){
            Membre membreRecu =membreRecuOptionnel.get();
            if(Objects.equals(membreRecu.getPassword(), membre.getPassword())){
                membreRecu.setPassword(newPassword);
                membreRepository.save(membreRecu);
                return true;
            }
        }
        return false;
    }

}
