package com.jfayad.projetweb2_springboot.services;

import com.jfayad.projetweb2_springboot.entities.Forum;
import com.jfayad.projetweb2_springboot.repos.ForumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForumService {

    @Autowired
    private ForumRepository forumRepository;

    public List<Forum> findAllForumPosts() {
        return forumRepository.findAll();
    }

    public Forum findForumsByTitle(String title) {
        return forumRepository.findByForumTitle(title);
    }

    public Forum saveForum(Forum post) {
        return forumRepository.save(post);
    }
}
