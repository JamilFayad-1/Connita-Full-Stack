package com.jfayad.projetweb2_springboot.repos;

import com.jfayad.projetweb2_springboot.entities.Commentaire;
import com.jfayad.projetweb2_springboot.entities.Membre;
import com.jfayad.projetweb2_springboot.entities.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentaireRepository extends JpaRepository<Commentaire, Integer> {
    @Query("SELECT c FROM Commentaire c WHERE c.membre = :membre")
    List<Commentaire> findAllByMembre(Membre membre);

    @Query("SELECT c FROM Commentaire c WHERE c.publication = :publication")
    List<Commentaire> findAllByPublication(Publication publication);
}
