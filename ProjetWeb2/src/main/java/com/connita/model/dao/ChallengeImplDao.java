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
    private static final String SQL_SELECT_CHALLENGE_STATUS = "SELECT challenge_name, firstSetComplete, secondSetComplete, thirdSetComplete FROM mydb.challenges WHERE idMembre = ?";
    private static final String SQL_AJOUTER_CHALLENGE = "INSERT INTO Challenges (idMembre, challenge_name, firstSetComplete, secondSetComplete, thirdSetComplete) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_VERIFY_ROW_EXISTS = "SELECT COUNT(*) FROM challenges WHERE idMembre = ? AND challenge_name = ?";
    
    @Override
    public boolean updateChallenge(int idMembre, String columnName, String challengeName) {
        Logger.getLogger(ChallengeImplDao.class.getName()).log(Level.INFO, "idMembre: {0}", idMembre);
        Logger.getLogger(ChallengeImplDao.class.getName()).log(Level.INFO, "columnName: {0}", columnName);
        Logger.getLogger(ChallengeImplDao.class.getName()).log(Level.INFO, "challengeName: {0}", challengeName);
        boolean validationChallenge = false;
        PreparedStatement ps = null;
        String SQL_UPDATE_CHALLENGE;

        try {
            SQL_UPDATE_CHALLENGE = "UPDATE challenges SET " + columnName + " = 1 WHERE idMembre = ? AND challenge_name = ?";
            ps = ConnexionDB.getConnection().prepareStatement(SQL_UPDATE_CHALLENGE);

            ps.setInt(1, idMembre);
            ps.setString(2, challengeName);

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
    public Map<String, Map<String, Boolean>> getStatus(int idMembre) {
        Map<String, Map<String, Boolean>> allChallengesCompletionStatus = new HashMap<>();
        PreparedStatement ps = null;

        try {
            ps = ConnexionDB.getConnection().prepareStatement(SQL_SELECT_CHALLENGE_STATUS);
            ps.setInt(1, idMembre);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Logger.getLogger(ChallengeImplDao.class.getName()).log(Level.INFO, "le challenge name dans le get status method: {0}", rs.getString("challenge_name"));
                String challengeName = rs.getString("challenge_name");
                boolean firstSetComplete = rs.getInt("firstSetComplete") == 1;
                boolean secondSetComplete = rs.getInt("secondSetComplete") == 1;
                boolean thirdSetComplete = rs.getInt("thirdSetComplete") == 1;

                Map<String, Boolean> challengeCompletionStatus = new HashMap<>();
                challengeCompletionStatus.put("firstSetComplete", firstSetComplete);
                challengeCompletionStatus.put("secondSetComplete", secondSetComplete);
                challengeCompletionStatus.put("thirdSetComplete", thirdSetComplete);

                allChallengesCompletionStatus.put(challengeName, challengeCompletionStatus);
            }
        } catch (SQLException e) {
            Logger.getLogger(ChallengeImplDao.class.getName()).log(Level.SEVERE, "Une erreur est survenue lors de la récupération du statut des défis", e);
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

        return allChallengesCompletionStatus;
    }

    @Override
    public boolean createChallengeRow(int memberId, String challengeName) {
        boolean validationInsertRow = false;
        PreparedStatement ps = null;

        try {
                        
            ps = ConnexionDB.getConnection().prepareStatement(SQL_AJOUTER_CHALLENGE);
            ps.setInt(1, memberId);
            ps.setString(2, challengeName);
            ps.setInt(3, 0);
            ps.setInt(4, 0);
            ps.setInt(5, 0);

            int nbLigne = ps.executeUpdate();
            validationInsertRow = nbLigne > 0;

        } catch (SQLException e) {
            Logger.getLogger(ChallengeImplDao.class.getName()).log(Level.SEVERE, "Une erreur est survenue lors de l'insertion d'un row dans le challenges table", e);
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
        return validationInsertRow;
    }

    @Override
    public boolean verifyChallengeRowExists(int memberId, String challengeName) {
        boolean validationInsertRow = false;
        PreparedStatement ps = null;

        try {
            
            ps = ConnexionDB.getConnection().prepareStatement(SQL_VERIFY_ROW_EXISTS);
            ps.setInt(1, memberId);
            ps.setString(2, challengeName);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int rowCount = rs.getInt(1);
                validationInsertRow = rowCount > 0;
            }

        } catch (SQLException e) {
            Logger.getLogger(ChallengeImplDao.class.getName()).log(Level.SEVERE, "Une erreur est survenue lors de l'insertion d'un row dans le challenges table", e);
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
        return validationInsertRow;
    }

    
}
