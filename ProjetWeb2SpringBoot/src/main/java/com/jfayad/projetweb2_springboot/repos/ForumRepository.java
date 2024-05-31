package com.jfayad.projetweb2_springboot.repos;

import com.jfayad.projetweb2_springboot.entities.Forum;
import com.jfayad.projetweb2_springboot.entities.Membre;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumRepository extends JpaRepository<Forum, Integer> {

    Forum findByForumTitle(String title);

    Forum findByIdForum(int forumId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Publication p WHERE p.membre = :membre")
    void deleteAllByMembre(Membre membre);
}
