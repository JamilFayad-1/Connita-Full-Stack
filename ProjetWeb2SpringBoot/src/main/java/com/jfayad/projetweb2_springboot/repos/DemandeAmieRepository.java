package com.jfayad.projetweb2_springboot.repos;

import com.jfayad.projetweb2_springboot.entities.DemandeAmie;
import com.jfayad.projetweb2_springboot.entities.Membre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeAmieRepository extends JpaRepository<DemandeAmie,Integer> {
}
