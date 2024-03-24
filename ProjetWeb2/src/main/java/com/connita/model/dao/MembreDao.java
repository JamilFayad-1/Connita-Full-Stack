package com.connita.model.dao;

import com.connita.model.entities.Membre;

public interface MembreDao {
    
    boolean ajouterMembre(Membre membre);
    Membre existsByEmailAndPassword(String email, String motDePasse);
    
}
