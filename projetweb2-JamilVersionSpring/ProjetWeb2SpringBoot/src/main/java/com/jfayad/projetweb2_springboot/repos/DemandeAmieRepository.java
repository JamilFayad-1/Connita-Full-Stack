package com.jfayad.projetweb2_springboot.repos;

import com.jfayad.projetweb2_springboot.entities.DemandeAmie;
import com.jfayad.projetweb2_springboot.entities.Membre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeAmieRepository extends JpaRepository<DemandeAmie,Integer> {

    @Modifying
    @Query("DELETE FROM DemandeAmie d WHERE d.recevant = :membre")
    void deleteByRecevant(@Param("membre") Membre membre);

}
