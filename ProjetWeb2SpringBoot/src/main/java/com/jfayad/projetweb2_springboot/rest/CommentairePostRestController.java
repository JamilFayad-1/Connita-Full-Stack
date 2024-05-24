package com.jfayad.projetweb2_springboot.rest;

import com.jfayad.projetweb2_springboot.entities.Commentaire;
import com.jfayad.projetweb2_springboot.entities.Membre;
import com.jfayad.projetweb2_springboot.entities.Publication;
import com.jfayad.projetweb2_springboot.services.CommentaireService;
import com.jfayad.projetweb2_springboot.services.PublicationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@RestController
@RequestMapping("/commmentaires")
public class CommentairePostRestController {

    @Autowired
    private CommentaireService commentaireService;

    @Autowired
    private PublicationService publicationService;

    @GetMapping("/sendCommentaire")
    public String sendComment(@RequestParam("publicationId") int publicationId,
                              @RequestParam("commentaire") String commentaireText,
                              HttpServletRequest request) {
        HttpSession session = request.getSession();
        Membre membre = (Membre) session.getAttribute("loggedInUser");

        Commentaire commentaire = new Commentaire();
        Publication publication = publicationService.findById(publicationId);

        commentaire.setMembre(membre);
        commentaire.setPublication(publication);
        commentaire.setCommentaire(commentaireText);

        commentaireService.saveCommentaire(commentaire);

        return "comment sent successfully!";
    }

    @GetMapping("/pullCommentaires")
    public ArrayList<Commentaire> pullCommentaires(@RequestParam("publicationId") int publicationId) {
        Publication publication = publicationService.findById(publicationId);
        ArrayList<Commentaire> comments = commentaireService.getAllByPublication(publication);

        comments.sort(Comparator.comparing(Commentaire::getDate));
        Collections.reverse(comments);

        return comments;
    }



}
