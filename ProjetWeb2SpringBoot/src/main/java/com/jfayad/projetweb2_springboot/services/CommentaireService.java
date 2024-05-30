package com.jfayad.projetweb2_springboot.services;

import com.jfayad.projetweb2_springboot.entities.Commentaire;
import com.jfayad.projetweb2_springboot.entities.Membre;
import com.jfayad.projetweb2_springboot.entities.Publication;
import com.jfayad.projetweb2_springboot.repos.CommentaireRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentaireService {

    @Autowired
    private CommentaireRepository repo;

    public List<Commentaire> getAllCommentaire() {
        return repo.findAll();
    }

    public ArrayList<Commentaire> getAllByMembre(Membre membre) {
        return repo.findAllByMembre(membre);
    }

    public ArrayList<Commentaire> getAllByPublication(Publication publication) {
        return repo.findAllByPublication(publication);
    }

    public void saveCommentaire(Commentaire commentaire) {
        repo.save(commentaire);
    }

    public void deleteCommentaire(Commentaire commentaire) {
        repo.delete(commentaire);
    }

    @Transactional
    public void deleteCommentaireByIdPublication(Publication publication) {
        repo.deleteByPublicationId(publication);
    }
}
