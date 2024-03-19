package com.connita.model.dao;

import com.connita.model.entities.Membre;

public interface MembreDao {
    
    void ajouterMembre(Membre membre);
    
    Membre existsByEmailAndPassword(String email, String motDePasse);
}
