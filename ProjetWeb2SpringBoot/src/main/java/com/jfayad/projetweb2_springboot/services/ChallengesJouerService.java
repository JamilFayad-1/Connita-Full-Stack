package com.jfayad.projetweb2_springboot.services;

import com.jfayad.projetweb2_springboot.entities.ChallengesJouer;
import com.jfayad.projetweb2_springboot.repos.ChallengesJouerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ChallengesJouerService {

    private static final Logger logger = LoggerFactory.getLogger(ChallengesJouerService.class);

    @Autowired
    private ChallengesJouerRepository challengesJouerRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public boolean createChallengeRow(int memberId, String challengeName){

        try {

            if (challengesJouerRepository.existsChallengesJouerByIdMembreAndChallengeName(memberId, challengeName)) {
                logger.info("Challenge row already exists for memberId {} and challengeName {}", memberId, challengeName);
                return true;
            }

            ChallengesJouer challengesJouer = new ChallengesJouer(memberId, challengeName);
            challengesJouerRepository.save(challengesJouer);
            return true;
        }catch (Exception e){
            logger.info("Error occured during the creation of a challenge row: {}", e.getMessage());
        }
        return false;
    }

    @Transactional
    public boolean updateChallengeRowValidation(int memberId, String challengeName, String columnName){

        try{
            String sql = "UPDATE challenges SET " + columnName + " = 1 WHERE id_membre = " + memberId + " AND challenge_name = '" + challengeName + "'";
            // Execute the query
            logger.info("IN THE SERVICE QUERY: {}", sql);
            entityManager.createNativeQuery(sql)
                            .executeUpdate();
            logger.info("IN THE SERVICE QUERY: {}", sql);
            return true;
        }catch (PersistenceException e){
            logger.info("Error occured during the creation of a challenge row: {}", e.getMessage());
            return false;
        }
    }

    public List<Map<String, Object>> getChallengeStatus(int memberId){
        try{
            return challengesJouerRepository.getChallengeStatus(memberId);
        }catch(Exception e) {
            logger.info("Error occured during the creation of a challenge row: {}", e.getMessage());
        }
        return null;
    }

    public void deleteChallengesByIdMembre(int idMembre) {
        challengesJouerRepository.deleteAllByIdMembre(idMembre);
    }

}
