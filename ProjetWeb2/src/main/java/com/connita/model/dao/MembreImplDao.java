package com.connita.model.dao;

import com.connita.model.entities.Membre;
import com.connita.model.singleton.ConnexionDB;
<<<<<<< HEAD
import static com.mysql.cj.conf.PropertyKey.logger;
import com.mysql.cj.xdevapi.Result;
import java.io.InputStream;
=======
>>>>>>> ca74659b1a1103595166f83dd29633474ac0ec57
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MembreImplDao implements MembreDao {

    // Requêtes SQL
<<<<<<< HEAD
    private static final String SQL_AJOUTER_MEMBRE = "INSERT INTO membre (nom, prenom, email, password) VALUES (?, ?, ?, ?)";
    private static final String SQL_CONNEXION_PAR_EMAIL_AND_PASSWORD = "SELECT idMembre, photoProfilPath, nom, prenom, username, bio, region FROM membre WHERE email = ? AND password = ?";
    private static final String SQL_VERIFIER_MOT_DE_PASSE = "SELECT password FROM membre WHERE email = ? AND password = ?";
    private static final String SQL_CHANGER_MOT_DE_PASSE = "UPDATE membre SET password = ? WHERE email = ? AND password = ?";
=======
>>>>>>> ca74659b1a1103595166f83dd29633474ac0ec57
    
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
                
                membre.setIdMembre(result.getInt("idMembre"));
                membre.setPhotoProfil(result.getString("photoProfilPath"));
                membre.setPrenom(result.getString("prenom"));
                membre.setNom(result.getString("nom"));
            }
            
        } catch (SQLException e) {
            Logger.getLogger(MembreImplDao.class.getName()).log(Level.SEVERE, "Une erreur est survenue lors de la connexion de l'utilisateur", e);
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
    
<<<<<<< HEAD
    @Override
    public boolean updateProfile(Membre membre) {
        
        if (" ".equals(membre.getUsername()) && "NADA".equals(membre.getPhotoProfil()) && " ".equals(membre.getBio()) && " ".equals(membre.getRegion())) {
            // No changes to be made, return true indicating success
            return true;
        }

        StringBuilder SQL_UPDATE_PROFILE = new StringBuilder();
        SQL_UPDATE_PROFILE.append("UPDATE membre SET ");
        boolean validation = false;
        PreparedStatement ps = null;
        boolean isFirstField = true;

        if (!" ".equals(membre.getUsername())) {
            SQL_UPDATE_PROFILE.append("username=?");
            isFirstField = false;
        }
        
        if (!"NADA".equals(membre.getPhotoProfil())) {
            Logger.getLogger(MembreImplDao.class.getName()).log(Level.SEVERE, "SI CEST AFFICHER CEST UNE ERREUR1");
            if (!isFirstField) {
                SQL_UPDATE_PROFILE.append(", ");
            }
            SQL_UPDATE_PROFILE.append("photoProfilPath=?");
            isFirstField = false;
        }
        if (!" ".equals(membre.getBio())) {
            if (!isFirstField) {
                SQL_UPDATE_PROFILE.append(", ");
            }
            SQL_UPDATE_PROFILE.append("bio=?");
            isFirstField = false;
        }
        if (!" ".equals(membre.getRegion())) {
            if (!isFirstField) {
                SQL_UPDATE_PROFILE.append(", ");
            }
            SQL_UPDATE_PROFILE.append("region=?");
        }

        SQL_UPDATE_PROFILE.append(" WHERE email=?");
        Logger.getLogger(MembreImplDao.class.getName()).log(Level.SEVERE, "La requete complete: ", SQL_UPDATE_PROFILE);
        try {
            ps = ConnexionDB.getConnection().prepareStatement(SQL_UPDATE_PROFILE.toString());

            int index = 1;
            if(!" ".equals(membre.getUsername())){
                ps.setString(index++, membre.getUsername());
            }
            if(!"NADA".equals(membre.getPhotoProfil())){
                Logger.getLogger(MembreImplDao.class.getName()).log(Level.SEVERE, "SI CEST AFFICHER CEST UNE ERREUR2");
                ps.setString(index++, membre.getPhotoProfil());
            }
            if(!" ".equals(membre.getBio())){
                ps.setString(index++, membre.getBio());
            }
            if(!" ".equals(membre.getRegion())){
                ps.setString(index++, membre.getRegion());
            }
            ps.setString(index, membre.getEmail());
            
            int nbLigne = ps.executeUpdate();
            validation = nbLigne > 0;

        } catch (SQLException e) {
            Logger.getLogger(MembreImplDao.class.getName()).log(Level.SEVERE, "Une erreur est survenue lors de la mise à jour du profil de l'utilisateur", e);
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
    public boolean verifierPassword(String email, String motDePasse) {
        boolean verificationValider = false;
        PreparedStatement ps = null;
        ResultSet nbrLigne = null;

        try {
            // Prepare the statement
            ps = ConnexionDB.getConnection().prepareStatement(SQL_VERIFIER_MOT_DE_PASSE);

            // Set parameters
            ps.setString(1, email);
            ps.setString(2, motDePasse);

            // Execute the query
            nbrLigne = ps.executeQuery();

            // Check if any rows are returned
            if (nbrLigne.next()) {
                verificationValider = true;
            }

        } catch (SQLException e) {
            Logger.getLogger(MembreImplDao.class.getName()).log(Level.SEVERE, "Une erreur est survenue lors de la vérification de mot de passe", e);
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
        return verificationValider;
    }

    @Override
    public boolean updatePassword(String email, String motDePasse, String motDePasseNvx) {
        boolean changementValider = false;
        PreparedStatement ps = null;
        
        try {
            ps = ConnexionDB.getConnection().prepareStatement(SQL_CHANGER_MOT_DE_PASSE);
            
            ps.setString(1, motDePasseNvx);
            ps.setString(2, email);
            ps.setString(3, motDePasse);
            
            int nbrLigne = ps.executeUpdate();
            changementValider = nbrLigne > 0;
            
        } catch (SQLException e) {
            Logger.getLogger(MembreImplDao.class.getName()).log(Level.SEVERE, "Une erreur est survenue lors de la vérification de mot de passe", e);
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
        return changementValider;
    }
    
=======
>>>>>>> ca74659b1a1103595166f83dd29633474ac0ec57
}
