package com.jfayad.projetweb2_springboot.services;

import com.jfayad.projetweb2_springboot.controller.ChallengesController;
import com.jfayad.projetweb2_springboot.entities.Challenges;
import com.jfayad.projetweb2_springboot.repos.ChallengesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengesService {

    private static final Logger logger = LoggerFactory.getLogger(ChallengesController.class);

    @Autowired
    private ChallengesRepository challengesRepository;

    public List<Challenges> findAllChallenges(){

        try {
            logger.info("CHALLENGES IN THE SERVICE: {}", challengesRepository.findAll());
            return  challengesRepository.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while attempting to get all challenges: {}", e.getMessage());
            return null;
        }

    }

}
