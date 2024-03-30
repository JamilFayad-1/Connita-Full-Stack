/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.connita.model.dao;

import com.connita.model.entities.Challenge;
import com.connita.model.singleton.ConnexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jamil
 */
public class ChallengeImplDao implements ChallengeDao{
    private static final String SQL_SELECT_ALL_CHALLENGES = "SELECT * FROM mydb.challengeselement";
    private static final String SQL_SELECT_CHALLENGE_STATUS = "SELECT firstSetComplete, secondSetComplete, thirdSetComplete FROM mydb.challenges WHERE idMembre = ?";

    @Override
    public boolean updateChallenge(int idMembre, String columnName) {
        boolean validationChallenge = false;
        PreparedStatement ps = null;
        String SQL_UPDATE_CHALLENGE;

        try {
            SQL_UPDATE_CHALLENGE = "UPDATE challenges SET " + columnName + " = 1 WHERE idMembre = ?";
            ps = ConnexionDB.getConnection().prepareStatement(SQL_UPDATE_CHALLENGE);

            ps.setInt(1, idMembre);

            int nbLigne = ps.executeUpdate();
            validationChallenge = nbLigne > 0;

        } catch (SQLException e) {
            Logger.getLogger(ChallengeImplDao.class.getName()).log(Level.SEVERE, "Une erreur est survenue lors de la création de l'utilisateur", e);
        } finally {
            ConnexionDB.closeConnection();
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    Logger.getLogger(ChallengeImplDao.class.getName()).log(Level.SEVERE, "Erreur lors de la fermeture de la déclaration de requête", e);
                }
            }
        }
        return validationChallenge;
    }
    
    @Override
    public List<Challenge> getChallengesFromDatabase() {
        List<Challenge> challenges = new ArrayList<>();
        PreparedStatement ps = null;

        try {
            ps = ConnexionDB.getConnection().prepareStatement(SQL_SELECT_ALL_CHALLENGES);
            ResultSet nbLigne = ps.executeQuery();

            while (nbLigne.next()) {
                Challenge challenge = new Challenge();
                challenge.setChallengeName(nbLigne.getString("challenge_name"));
                challenge.setChallengeDescription(nbLigne.getString("challenge_description"));
                challenge.setChallengeImageUrl(nbLigne.getString("challenge_image_url"));
                challenges.add(challenge);
            }
        } catch (SQLException e) {
            Logger.getLogger(ChallengeImplDao.class.getName()).log(Level.SEVERE, "Une erreur est survenue lors de la création de l'utilisateur", e);
        } finally {
            ConnexionDB.closeConnection();
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    Logger.getLogger(ChallengeImplDao.class.getName()).log(Level.SEVERE, "Erreur lors de la fermeture de la déclaration de requête", e);
                }
            }
        }
        return challenges;
    }
    
    @Override
    public Map<String, Boolean> getStatus(int idMembre) {
        Map<String, Boolean> challengeCompletionStatus = new HashMap<>();
        PreparedStatement ps = null;
        boolean firstSetComplete = false;
        boolean secondSetComplete = false;
        boolean thirdSetComplete = false;

        try {
            ps = ConnexionDB.getConnection().prepareStatement(SQL_SELECT_CHALLENGE_STATUS);
            ps.setInt(1, idMembre);
            
            ResultSet nbLigne = ps.executeQuery();

            while (nbLigne.next()) {
                if(nbLigne.getInt("firstSetComplete") == 1){
                    firstSetComplete = true;
                }
                if(nbLigne.getInt("secondSetComplete") == 1){
                    secondSetComplete = true;
                }
                if(nbLigne.getInt("thirdSetComplete") == 1){
                    thirdSetComplete = true;
                }
                
                challengeCompletionStatus.put("firstSetComplete", firstSetComplete);
                challengeCompletionStatus.put("secondSetComplete", secondSetComplete);
                challengeCompletionStatus.put("thirdSetComplete", thirdSetComplete);
            }
        } catch (SQLException e) {
            Logger.getLogger(ChallengeImplDao.class.getName()).log(Level.SEVERE, "Une erreur est survenue lors de la création de l'utilisateur", e);
        } finally {
            ConnexionDB.closeConnection();
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    Logger.getLogger(ChallengeImplDao.class.getName()).log(Level.SEVERE, "Erreur lors de la fermeture de la déclaration de requête", e);
                }
            }
        }

        return challengeCompletionStatus;
    }
    
}
