package com.jfayad.projetweb2_springboot.services;

import com.jfayad.projetweb2_springboot.entities.Membre;
import com.jfayad.projetweb2_springboot.entities.Publication;
import com.jfayad.projetweb2_springboot.repos.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicationService {

    @Autowired
    PublicationRepository repo;

    public List<Publication> findAllById(int id) {
        List<Publication> publications = repo.findAllByIdPublication(id);
        return publications;
    }

    public Publication findById(int id) {
        Publication publication = repo.findById(id).orElse(null);
        return publication;
    }

    public List<Publication> findAllByMembre(Membre membre) {
        List<Publication> publications = repo.findAllByMembre(membre);
        return publications;
    }

    public List<Publication> findAllByIdPublication(int id) {
        List<Publication> publications = repo.findAll();
        return publications;
    }

    public void save(Publication publication) {
        repo.save(publication);
    }

    public List<Publication> findAll() {
        return repo.findAll();
    }

    public void addLike(int id) {
        Optional<Publication> optionalPublication = repo.findById(id);
        if (optionalPublication.isPresent()) {
            Publication publication = optionalPublication.get();
            publication.setLikes(publication.getLikes() + 1);
            repo.save(publication);
        }
    }

    public void removeLike(int id) {
        Optional<Publication> optionalPublication = repo.findById(id);
        if (optionalPublication.isPresent()) {
            Publication publication = optionalPublication.get();
            publication.setLikes(publication.getLikes() - 1);
            repo.save(publication);
        }
    }
}
