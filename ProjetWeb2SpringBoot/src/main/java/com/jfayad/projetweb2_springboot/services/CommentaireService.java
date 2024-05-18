package com.jfayad.projetweb2_springboot.services;

import com.jfayad.projetweb2_springboot.entities.Commentaire;
import com.jfayad.projetweb2_springboot.entities.Membre;
import com.jfayad.projetweb2_springboot.entities.Publication;
import com.jfayad.projetweb2_springboot.repos.CommentaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentaireService {
    @Autowired
    private CommentaireRepository repo;
    public List<Commentaire> getAllCommentaire() {
        return repo.findAll();
    }
    public List<Commentaire> getAllByMembre(Membre membre) {
        return repo.findAllByMembre(membre);
    }
    public List<Commentaire> getAllByPublication(Publication publication) {
        return repo.findAllByPublication(publication);
    }
    public void Commenter (Commentaire commentaire) {
        repo.save(commentaire);
    }
}
