package com.jfayad.projetweb2_springboot.controller;

import com.jfayad.projetweb2_springboot.entities.Amitier;
import com.jfayad.projetweb2_springboot.entities.Forum;
import com.jfayad.projetweb2_springboot.entities.Membre;
import com.jfayad.projetweb2_springboot.services.AmitierService;
import com.jfayad.projetweb2_springboot.services.ForumService;
import com.jfayad.projetweb2_springboot.services.MembreService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
public class ForumController {

    @Autowired
    private ForumService forumService;

    @Autowired
    private AmitierService amitierService;

    @Autowired
    private MembreService membreService;

    @GetMapping("/pageForum")
    public String getPageForum(Model model,
                               HttpServletRequest request) {

        HttpSession session = request.getSession();
        Membre MembreTrouver = (Membre) session.getAttribute("loggedInUser");
        int idMembretrouver = MembreTrouver.getIdMembre();

        List<Amitier> listeIdAmitier = amitierService.getAllAmitiers();

        List<Integer> AmieActuelle = new ArrayList<>();

        for (Amitier amitier : listeIdAmitier) {
            if (amitier.getMembre().getIdMembre() == idMembretrouver) {
                AmieActuelle.add(amitier.getAmie().getIdMembre());
            }
            if (amitier.getAmie().getIdMembre() == idMembretrouver) {
                AmieActuelle.add(amitier.getMembre().getIdMembre());
            }
        }

        List<Membre> listeUtilisateur = membreService.getAllUtilisateurs();
        List<Membre> listeAmitier = new ArrayList<>();
        for (Membre membre : listeUtilisateur) {
            if (AmieActuelle.contains(membre.getIdMembre())) {
                listeAmitier.add(membre);
            }
        }

        List<Forum> listeForum = getAllForumPosts();

        model.addAttribute("listeAmitier", listeAmitier);
        model.addAttribute("listeForum", listeForum);

        return "forum";
    }

    @GetMapping("/getAllForumPosts")
    @ResponseBody
    public List<Forum> getAllForumPostsMapping() {
        return getAllForumPosts();
    }

    @GetMapping("/saveNewForum")
    @ResponseBody
    public ResponseEntity<String> saveForum(@RequestParam("forumTitle") String forumTitle,
                                            @RequestParam("forumContent") String forumContent,
                                            HttpServletRequest request){

        HttpSession session = request.getSession();
        Membre membreTrouver = (Membre) session.getAttribute("loggedInUser");

        if(membreTrouver != null){
            Forum nvxForum = new Forum(membreTrouver, forumTitle, forumContent);
            forumService.saveForum(nvxForum);
            return ResponseEntity.ok("Forum saved successfully");
        }
        return ResponseEntity.status(400).body("Forum error");
    }

    public List<Forum> getAllForumPosts(){
        List<Forum> forumList = forumService.findAllForumPosts();
        forumList.sort(Comparator.comparing(Forum::getForumDatePosted).reversed());
        return forumList;
    }

}
