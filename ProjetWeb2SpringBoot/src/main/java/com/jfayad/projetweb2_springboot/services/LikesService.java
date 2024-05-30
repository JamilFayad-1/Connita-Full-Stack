package com.jfayad.projetweb2_springboot.services;

import com.jfayad.projetweb2_springboot.entities.Likes;
import com.jfayad.projetweb2_springboot.entities.Membre;
import com.jfayad.projetweb2_springboot.entities.Publication;
import com.jfayad.projetweb2_springboot.repos.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikesService {
    @Autowired
    private LikeRepository repo;

    public List<Likes> getAllLikes() {
        return repo.findAll();
    }

    public List<Likes> getAllByMembre(Membre membre) {
        return repo.findAllByMembre(membre);
    }

    public List<Likes> getAllByPublication(Publication publication) {
        return repo.findAllByPublication(publication);
    }

    public void save(Likes likes) {
        repo.save(likes);
    }

    public void delete(Likes likes) {
        repo.delete(likes);
    }

    public Likes findByMembreAndPublication(Membre membre, Publication publication) {
        return repo.findByMembreAndPublication(membre, publication);
    }

    public List<Likes> findByMembre(Membre membre) {
        return repo.findByMembre(membre);
    }

    public void deleteByPublication(Publication publication) {
        repo.deleteByPublication(publication);
    }
}
