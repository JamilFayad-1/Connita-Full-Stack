package com.jfayad.projetweb2_springboot.controller;

import com.jfayad.projetweb2_springboot.entities.Amitier;
import com.jfayad.projetweb2_springboot.entities.Forum;
import com.jfayad.projetweb2_springboot.entities.ForumReply;
import com.jfayad.projetweb2_springboot.entities.Membre;
import com.jfayad.projetweb2_springboot.services.AmitierService;
import com.jfayad.projetweb2_springboot.services.ForumReplyService;
import com.jfayad.projetweb2_springboot.services.ForumService;
import com.jfayad.projetweb2_springboot.services.MembreService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
public class ForumReplyController {

    @Autowired
    private ForumReplyService forumReplyService;

    @Autowired
    private AmitierService amitierService;

    @Autowired
    private MembreService membreService;

    @Autowired
    private ForumService forumService;

    @GetMapping("/pageForumActive/{forumId}")
    public String getPageForum(@PathVariable("forumId") Integer forumId,
                               Model model,
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

        Forum forumTrouver = forumService.findForumById(forumId);

        List<ForumReply> forumReplies = forumReplyService.findAllForumRepliesById(forumTrouver);

        LocalDateTime temps = forumTrouver.getForumDatePosted();
        LocalDateTime currentDateTime = LocalDateTime.now();
        String whenTheTopicWasPosted;
        Duration duration = Duration.between(temps, currentDateTime);

        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;

        if (hours >= 24) {
            int nbrOfDays = (int) (hours / 24);
            whenTheTopicWasPosted = ("Posted " + nbrOfDays + " days ago");
        } else if (hours > 0) {
            whenTheTopicWasPosted = ("Posted " + hours + " hours ago");
        } else if (minutes <= 1) {
            whenTheTopicWasPosted = ("Posted just now");
        } else {
            whenTheTopicWasPosted = ("Posted " + minutes + " minutes ago");
        }


        model.addAttribute("listeAmitier", listeAmitier);
        model.addAttribute("forumActive", forumTrouver);
        model.addAttribute("forumReplies", forumReplies);
        model.addAttribute("whenPosted", whenTheTopicWasPosted);

        return "forumActive";
    }

    @GetMapping("/getAllForumPostReplies")
    @ResponseBody
    public List<ForumReply> getAllForumPostRepliesMapping(@RequestParam("forumId") Integer forumId) {
        Forum forumTrouver = forumService.findForumById(forumId);
        List<ForumReply> replyList = forumReplyService.findAllForumRepliesById(forumTrouver);

        replyList.sort(Comparator.comparing(ForumReply::getReplyDatePosted).reversed());

        return replyList;
    }

    @GetMapping("/saveNewForumReply")
    @ResponseBody
    public ResponseEntity<String> saveForumReply(@RequestParam("forumReply") String forumReply,
                                                 @RequestParam("forumTopic") int forumTopicId,
                                                 HttpServletRequest request) {

        HttpSession session = request.getSession();
        Membre membreTrouver = (Membre) session.getAttribute("loggedInUser");

        Forum forumTrouver = forumService.findForumById(forumTopicId);

        if (membreTrouver != null) {
            ForumReply nvxForumReply = new ForumReply(forumTrouver, membreTrouver, forumReply);
            forumReplyService.saveForumReply(nvxForumReply);
            return ResponseEntity.ok("Forum saved successfully");
        }
        return ResponseEntity.status(400).body("Forum error");
    }

}

