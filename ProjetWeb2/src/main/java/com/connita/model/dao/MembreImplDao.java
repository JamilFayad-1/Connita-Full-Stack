package com.connita.model.dao;

import com.connita.model.entities.Membre;
import com.connita.model.singleton.ConnexionDB;
import com.mysql.cj.xdevapi.Result;
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
    public void ajouterMembre(Membre membre) {
        int nbLigne = 0;
        PreparedStatement ps = null;
        
        try {
            ps = ConnexionDB.getConnection().prepareStatement(SQL_AJOUTER_MEMBRE);

            ps.setString(1, membre.getNom());
            ps.setString(2, membre.getPrenom());
            ps.setString(3, membre.getEmail());
            ps.setString(4, membre.getPassword());

            nbLigne = ps.executeUpdate();

        } catch (SQLException e) {
            // En cas d'erreur lors de l'exécution de la requête SQL, une exception est attrapée
            // Un message d'erreur est enregistré dans les logs avec une indication du problème
            Logger.getLogger(MembreImplDao.class.getName()).log(Level.SEVERE, "Une erreur est survenue lors de la création de l'utilisateur", e);
        } finally {
            // Ferme la connexion après l'exécution de la requête
            ConnexionDB.closeConnection();
            // Ferme la déclaration de requête pour libérer les ressources
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    // En cas d'erreur lors de la fermeture de la déclaration de requête, une exception est attrapée
                    // Un message d'erreur est enregistré dans les logs avec une indication du problème
                    Logger.getLogger(MembreImplDao.class.getName()).log(Level.SEVERE, "Erreur lors de la fermeture de la déclaration de requête", e);
                }
            }
        }
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
            // En cas d'erreur lors de l'exécution de la requête SQL, une exception est attrapée
            // Un message d'erreur est enregistré dans les logs avec une indication du problème
            Logger.getLogger(MembreImplDao.class.getName()).log(Level.SEVERE, "Une erreur est survenue lors de la création de l'utilisateur", e);
        } finally {
            // Ferme la connexion après l'exécution de la requête
            ConnexionDB.closeConnection();
            // Ferme la déclaration de requête pour libérer les ressources
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    // En cas d'erreur lors de la fermeture de la déclaration de requête, une exception est attrapée
                    // Un message d'erreur est enregistré dans les logs avec une indication du problème
                    Logger.getLogger(MembreImplDao.class.getName()).log(Level.SEVERE, "Erreur lors de la fermeture de la déclaration de requête", e);
                }
            }
        }
        return membre;
    }
    
}
