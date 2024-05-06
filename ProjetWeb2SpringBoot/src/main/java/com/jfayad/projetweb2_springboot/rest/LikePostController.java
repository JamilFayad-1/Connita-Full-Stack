package com.jfayad.projetweb2_springboot.rest;

import com.jfayad.projetweb2_springboot.services.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikePostController {

    @Autowired
    private PublicationService publicationService;

    @PostMapping("/likePost")
    public String likePost(@RequestParam("idPost") int idPost){
        publicationService.addLike(idPost);
        return "Post liked";
    }

    @PostMapping("/removeLike")
    public String removeLike(@RequestParam("idPost") int idPost){
        publicationService.removeLike(idPost);
        return "Post unliked";
    }

}
