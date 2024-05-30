package com.jfayad.projetweb2_springboot.repos;

import com.jfayad.projetweb2_springboot.entities.Forum;
import com.jfayad.projetweb2_springboot.entities.ForumReply;
import com.jfayad.projetweb2_springboot.entities.Membre;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumReplyRepository extends JpaRepository<ForumReply, Long> {

    List<ForumReply> findAllByForumReply(Forum forumReply);

    @Transactional
    @Modifying
    void deleteAllByMembreReply(Membre membreReply);

}
