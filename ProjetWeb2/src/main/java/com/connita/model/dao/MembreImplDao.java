package com.connita.model.dao;

import com.connita.model.entities.Membre;
import com.connita.model.singleton.ConnexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MembreImplDao implements MembreDao {

    // Requêtes SQL
    
    private static final String SQL_AJOUTER_MEMBRE = "INSERT INTO membre (nom, prenom, email, password) VALUES (?, ?, ?, ?)";
    private static final String SQL_CONNEXION_PAR_EMAIL_AND_PASSWORD = "SELECT nom, prenom FROM membre WHERE email = ? AND password = ?";

    @Override
    public boolean ajouterMembre(Membre membre) {
        boolean validation = false;
        PreparedStatement ps = null;

        try {
            
            ps = ConnexionDB.getConnection().prepareStatement(SQL_AJOUTER_MEMBRE);

            ps.setString(1, membre.getNom());
            ps.setString(2, membre.getPrenom());
            ps.setString(3, membre.getEmail());
            ps.setString(4, membre.getPassword());

            int nbLigne = ps.executeUpdate();
            validation = nbLigne > 0;

        } catch (SQLException e) {
            Logger.getLogger(MembreImplDao.class.getName()).log(Level.SEVERE, "Une erreur est survenue lors de la création de l'utilisateur", e);
        } finally {
            ConnexionDB.closeConnection();
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    Logger.getLogger(MembreImplDao.class.getName()).log(Level.SEVERE, "Erreur lors de la fermeture de la déclaration de requête", e);
                }
            }
        }
        return validation;
    }
    
    
    @Override
    public Membre existsByEmailAndPassword(String email, String motDePasse) {
        Membre membre = null;
        PreparedStatement ps = null;
        
        try{
            
            ps = ConnexionDB.getConnection().prepareStatement(SQL_CONNEXION_PAR_EMAIL_AND_PASSWORD);
            
            ps.setString(1, email);
            ps.setString(2, motDePasse);
            
            ResultSet result = ps.executeQuery();
            
            while(result.next()){
                membre = new Membre();
                
                membre.setPrenom(result.getString("prenom"));
                membre.setNom(result.getString("nom"));
            }
            
        } catch (SQLException e) {
            Logger.getLogger(MembreImplDao.class.getName()).log(Level.SEVERE, "Une erreur est survenue lors de la création de l'utilisateur", e);
        } finally {
            ConnexionDB.closeConnection();
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    Logger.getLogger(MembreImplDao.class.getName()).log(Level.SEVERE, "Erreur lors de la fermeture de la déclaration de requête", e);
                }
            }
        }
        return membre;
    }
    
}
