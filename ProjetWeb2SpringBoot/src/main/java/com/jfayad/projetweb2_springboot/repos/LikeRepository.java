package com.jfayad.projetweb2_springboot.repos;

import com.jfayad.projetweb2_springboot.entities.Likes;
import com.jfayad.projetweb2_springboot.entities.Membre;
import com.jfayad.projetweb2_springboot.entities.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Likes, Integer> {
    @Query("SELECT l from Likes l where l.membre = :membre")
    List<Likes> findAllByMembre(Membre membre);

    @Query("SELECT l from Likes l where l.publication = :publication")
    List<Likes> findAllByPublication(Publication publication);

    @Query("Select l from Likes l where l.membre = :membre AND l.publication = :publication ")
    Likes findByMembreAndPublication(Membre membre, Publication publication);

    @Query("Select l from Likes l where l.membre = :membre")
    List<Likes> findByMembre(Membre membre);

    @Transactional
    @Modifying
    @Query("DELETE FROM Likes l WHERE l.publication = :publication")
    void deleteByPublication(Publication publication);

}

