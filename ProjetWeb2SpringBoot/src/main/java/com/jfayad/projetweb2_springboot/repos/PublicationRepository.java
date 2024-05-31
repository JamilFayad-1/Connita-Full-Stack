package com.jfayad.projetweb2_springboot.repos;

import com.jfayad.projetweb2_springboot.entities.Membre;
import com.jfayad.projetweb2_springboot.entities.Publication;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Integer> {
  List<Publication> findAll();

  @Query("SELECT p FROM Publication p WHERE p.membre=:membre ORDER BY p.id_publication DESC")
  List<Publication> findAllByMembre(Membre membre);

  @Query("SELECT p FROM Publication p WHERE p.id_publication=:id_publication")
  List<Publication> findAllByIdPublication(int id);

  @Transactional
  @Modifying
  void deleteAllByMembre(Membre membre);
}
