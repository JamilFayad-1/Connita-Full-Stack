/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.connita.model.dao;

import com.connita.model.singleton.ConnexionDB;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jamil
 */
public class ChallengeImplDao implements ChallengeDao{

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
    public boolean isChallengeCompleted(int idMembre, int setNumber) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
