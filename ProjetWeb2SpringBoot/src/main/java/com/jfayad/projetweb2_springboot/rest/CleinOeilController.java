package com.jfayad.projetweb2_springboot.rest;

import com.jfayad.projetweb2_springboot.controller.AuthController;
import com.jfayad.projetweb2_springboot.entities.Membre;
import com.jfayad.projetweb2_springboot.services.CleinOeilService;
import com.jfayad.projetweb2_springboot.services.DemandeAmieService;
import com.jfayad.projetweb2_springboot.services.MembreService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
public class CleinOeilController {

    private static final Logger logger = LoggerFactory.getLogger(CleinOeilController.class);

    @Autowired
    private CleinOeilService cleinOeilService;

    @Autowired
    private MembreService membreService;

    @PostMapping("/envoyerCLeinOeil")
    @ResponseBody
    public ResponseEntity<String> envoyerCleinOeil(@RequestParam("idRecevant") Membre idRecevant,
                                                   HttpServletRequest request) {

        HttpSession session = request.getSession();
        Membre membreEnvoyant = (Membre) session.getAttribute("loggedInUser");

        try {
            cleinOeilService.sendCleinOeil(membreEnvoyant, idRecevant);
            return ResponseEntity.ok("Clein d'oeil sent successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Clein d'oeil error");
        }
    }

    @PostMapping("/deleteCleinOeil")
    @ResponseBody
    public ResponseEntity<String> deleteCleinOeil(@RequestParam("membreEnvoyant") int membreEnvoyantRecu,
                                                  HttpServletRequest request) {

        HttpSession session = request.getSession();
        Membre membreRecevant = (Membre) session.getAttribute("loggedInUser");
        Membre membreEnvoyant = membreService.findById(membreEnvoyantRecu);

        try {
            cleinOeilService.deleteCleinOeil(membreEnvoyant, membreRecevant);
            logger.info("LE CLEIN DOEIL DELETED: {}" , membreEnvoyant.getPrenom());
            return ResponseEntity.ok("Clein d'oeil deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Clein d'oeil removal error");
        }

    }

}