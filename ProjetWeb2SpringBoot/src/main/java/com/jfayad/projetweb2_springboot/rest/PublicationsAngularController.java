package com.jfayad.projetweb2_springboot.rest;

import com.jfayad.projetweb2_springboot.entities.Publication;
import com.jfayad.projetweb2_springboot.services.CommentaireService;
import com.jfayad.projetweb2_springboot.services.LikesService;
import com.jfayad.projetweb2_springboot.services.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PublicationsAngularController {
    @Autowired
    PublicationService publicationService;
    @Autowired
    CommentaireService commentaireService;
    @Autowired
    LikesService likesService;
    @RequestMapping("/getPostsAdmin")
    public ResponseEntity<List<Publication>> getAllPublications() {
        List<Publication> publications = publicationService.findAll();
        return ResponseEntity.ok(publications);
    }
    @DeleteMapping("/deletePostAdmin/{id}")
    public ResponseEntity<String> deletePublication(@PathVariable Integer id) {
        commentaireService.deleteCommentaireByIdPublication(publicationService.findById(id));
        likesService.deleteByPublication(publicationService.findById(id));
        publicationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
