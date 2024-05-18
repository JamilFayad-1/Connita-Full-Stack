package com.jfayad.projetweb2_springboot.rest;

import com.jfayad.projetweb2_springboot.entities.Likes;
import com.jfayad.projetweb2_springboot.entities.Membre;
import com.jfayad.projetweb2_springboot.entities.Publication;
import com.jfayad.projetweb2_springboot.services.LikesService;
import com.jfayad.projetweb2_springboot.services.PublicationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LikePostController {

    @Autowired
    private PublicationService publicationService;
    @Autowired
    private LikesService likesService;

    @PostMapping("/likePost")
    public String likePost(@RequestParam("idPost") int idPost, Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        Membre membre = (Membre) session.getAttribute("loggedInUser");
        Likes like = new Likes();
        like.setMembre(membre);
        like.setPublication(publicationService.findById(idPost));
        likesService.save(like);
        publicationService.addLike(idPost);
        return "Post liked";
    }

    @PostMapping("/removeLike")
    public String removeLike(@RequestParam("idPost") int idPost, Model model, HttpServletRequest request){
        publicationService.removeLike(idPost);
        HttpSession session = request.getSession();
        Membre membre = (Membre) session.getAttribute("loggedInUser");
        Publication publication = publicationService.findById(idPost);
        Likes like = likesService.findByMembreAndPublication(membre, publication);
        likesService.delete(like);
        return "Post unliked";
    }
    @GetMapping("/pullLikes")
    public List<Likes> pullLikes(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        Membre membre = (Membre) session.getAttribute("loggedInUser");
        List<Likes> likes = likesService.findByMembre(membre);
        return likes;
    }

}

