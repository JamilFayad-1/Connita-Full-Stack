package com.connita.model.dao;

import com.connita.model.entities.Membre;

public interface MembreDao {
    
    boolean ajouterMembre(Membre membre);
    Membre existsByEmailAndPassword(String email, String motDePasse);
<<<<<<< HEAD
    boolean updateProfile(Membre membre);
    boolean verifierPassword(String email, String motDePasse);
    boolean updatePassword(String email, String motDePasse, String motDePasseNvx);
=======
    
>>>>>>> ca74659b1a1103595166f83dd29633474ac0ec57
}
