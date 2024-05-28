package com.jfayad.projetweb2_springboot.repos;

import com.jfayad.projetweb2_springboot.entities.Forum;
import com.jfayad.projetweb2_springboot.entities.ForumReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumReplyRepository extends JpaRepository<ForumReply, Long> {

    public List<ForumReply> findAllByForumReply(Forum forumReply);

}
