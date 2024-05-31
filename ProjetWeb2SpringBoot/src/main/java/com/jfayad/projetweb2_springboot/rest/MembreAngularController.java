package com.jfayad.projetweb2_springboot.rest;

import com.jfayad.projetweb2_springboot.entities.Membre;
import com.jfayad.projetweb2_springboot.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MembreAngularController {


  private static final Logger log = LoggerFactory.getLogger(MembreAngularController.class);
  @Autowired
  MembreService membreService;

  @Autowired
  ChallengesJouerService challengesJouerService;

  @Autowired
  ExercicesService exercicesService;

  @Autowired
  AmitierService amitierService;

  @Autowired
  ForumService forumService;

  @Autowired
  ForumReplyService forumReplyService;

  @Autowired
  MessagesService messagesService;

  @Autowired
  private CommentaireService commentaireService;

  @Autowired
  private PublicationService publicationService;

  @Autowired
  private LikesService likesService;

  @RequestMapping("/getMembresAdmin")
  public ResponseEntity<List<Membre>> getAllMembres() {
    List<Membre> membres = membreService.findAll();
    return ResponseEntity.ok(membres);
  }

  @DeleteMapping("/deleteMembreAdmin/{idMembre}")
  public ResponseEntity<String> deleteMembre(@PathVariable Integer idMembre) {
    challengesJouerService.deleteChallengesByIdMembre(idMembre);
    exercicesService.deleteExercicesByIdMembre(idMembre);
    messagesService.deleteAllMessagesByMembre(membreService.findById(idMembre));
    amitierService.deleteAmitierByIdMembre(membreService.findById(idMembre));
    forumReplyService.deleteAllForumRepliesByMember(membreService.findById(idMembre));
    forumService.deleteAllForumTopicsByMember(membreService.findById(idMembre));
    commentaireService.deleteAllCommentaireByMembre(membreService.findById(idMembre));
    likesService.deleteAllByMembre(membreService.findById(idMembre));
    publicationService.deleteAllPublicationsByMembre(membreService.findById(idMembre));
    membreService.deleteMembreById(idMembre);
    return ResponseEntity.noContent().build();
  }

  @RequestMapping("/searchForMembre")
  public ResponseEntity<List<Membre>> searchForMembre(@RequestParam String search) {
    List<Membre> membres = membreService.findMembreByUsername(search);
    return ResponseEntity.ok(membres);
  }

}
