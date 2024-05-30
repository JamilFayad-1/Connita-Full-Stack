package com.jfayad.projetweb2_springboot.services;

import com.jfayad.projetweb2_springboot.entities.Forum;
import com.jfayad.projetweb2_springboot.entities.ForumReply;
import com.jfayad.projetweb2_springboot.entities.Membre;
import com.jfayad.projetweb2_springboot.repos.ForumReplyRepository;
import com.jfayad.projetweb2_springboot.repos.ForumRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForumReplyService {

    @Autowired
    private ForumReplyRepository forumReplyRepository;

    public List<ForumReply> findAllForumPostReplies() {
        return forumReplyRepository.findAll();
    }

    public ForumReply saveForumReply(ForumReply reply) {
        return forumReplyRepository.save(reply);
    }

    public List<ForumReply> findAllForumRepliesById(Forum forumReplied) {
        return forumReplyRepository.findAllByForumReply(forumReplied);
    }

    @Transactional
    public void deleteAllForumRepliesByMember(Membre membre) {
        forumReplyRepository.deleteAllByMembreReply(membre);
    }

}
