package com.jfayad.projetweb2_springboot.repos;

import com.jfayad.projetweb2_springboot.entities.Commentaire;
import com.jfayad.projetweb2_springboot.entities.Membre;
import com.jfayad.projetweb2_springboot.entities.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire, Integer> {
  @Query("SELECT c FROM Commentaire c WHERE c.membre = :membre")
  ArrayList<Commentaire> findAllByMembre(Membre membre);

  @Query("SELECT c FROM Commentaire c WHERE c.publication = :publication")
  ArrayList<Commentaire> findAllByPublication(Publication publication);

  @Transactional
  @Modifying
  @Query("DELETE FROM Commentaire c WHERE c.publication = :publication")
  void deleteByPublicationId(Publication publication);

  @Transactional
  @Modifying
  void deleteAllByMembre(Membre membre);
}
