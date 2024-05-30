package com.jfayad.projetweb2_springboot.repos;

import com.jfayad.projetweb2_springboot.entities.Amitier;
import com.jfayad.projetweb2_springboot.entities.Membre;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AmitierRepository extends JpaRepository<Amitier, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Amitier a WHERE a.amie = :membre OR a.membre = :membre")
    void deleteAllByAmieOrByMembre(Membre membre);

}
