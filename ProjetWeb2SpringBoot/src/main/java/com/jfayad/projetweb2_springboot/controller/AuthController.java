package com.jfayad.projetweb2_springboot.controller;

import com.jfayad.projetweb2_springboot.entities.Challenges;
import com.jfayad.projetweb2_springboot.entities.Membre;
import com.jfayad.projetweb2_springboot.services.ChallengesJouerService;
import com.jfayad.projetweb2_springboot.services.ChallengesService;
import com.jfayad.projetweb2_springboot.services.MembreService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Controller
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private MembreService membreService;

    @Autowired
    private ChallengesService challengesService;

    @Autowired
    private ChallengesJouerService challengesJouerService;
    @Autowired
    private HttpSession httpSession;

    @PostMapping("/membre/connexion")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpSession session,
                        RedirectAttributes redirectAttributes) {
        // Process login form
        //        logger.info("Attempting to log in with email: {}", email);
        //        logger.info("Attempting to log in with password: {}", password);

        Membre membre = membreService.connexionMembre(email, password);

        if(membre != null){
            if(membre.getPhotoProfilPath() == null || membre.getPhotoProfilPath().isEmpty()){
                logger.info("THIS SHOULD BE DISPLAYED IF ZERO PHOTO: {}", membre.getPhotoProfilPath());
                membre.setPhotoProfilPath("imageUtilisateur/Default-profile-pic.png");
            }

            List<Challenges> challenges = challengesService.findAllChallenges();
            session.setAttribute("challenges", challenges);

            session.setAttribute("isFirstSetComplete", false);
            session.setAttribute("isSecondSetComplete", false);


            setLanguagesSpokenSessionAttributes(membre.getLangue(), session);

            session.setAttribute("loggedInUser", membre);
            redirectAttributes.addFlashAttribute("messageConnReussite", "Successful login!");
            return "redirect:/pageAccueilUtilisateur";
        }
        else {
            redirectAttributes.addFlashAttribute("messageConnEchoue", "Connexion failed. Check your credentials");
            return "redirect:/index";
        }
    }

    @PostMapping("/membre/inscription")
    public String register(@RequestParam("firstName") String firstName,
                        @RequestParam("lastName") String lastName,
                        @RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpSession session,
                        RedirectAttributes redirectAttributes) {
        boolean validation = false;
        // Process login form
        //logger.info("Attempting to sign up with first name: {}", firstName);
        //logger.info("Attempting to sign up with last name: {}", lastName);
        //logger.info("Attempting to sign up with email: {}", email);
        //logger.info("Attempting to sign up with password: {}", password);

        Membre membre = membreService.inscriptionMembre(firstName, lastName, email, password);

        List<Challenges> listeAllChallenges = challengesService.findAllChallenges();
        for(Challenges challenge : listeAllChallenges){
            challengesJouerService.createChallengeRow(membre.getIdMembre(), challenge.getChallengeName());
            session.setAttribute("isFirstSetComplete", false);
            session.setAttribute("isSecondSetComplete", false);
        }

        if (membre != null) {
            validation = true;
            session.setAttribute("validation", validation);
            redirectAttributes.addFlashAttribute("messageInscrReussite", "Successful registration");
            return "redirect:/index";
        } else {
            session.setAttribute("validation", validation);
            redirectAttributes.addFlashAttribute("messageInscrEchoue", "Failed to register..");
            return "redirect:/index";

        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session,
                         RedirectAttributes redirectAttributes) {
        // Process logout
        session.invalidate();
        redirectAttributes.addFlashAttribute("messageDeconnexion", "Successful logout!");
        return "redirect:/index";
    }

    @PostMapping("/updateProfile")
    public String updateProfile(@ModelAttribute Membre membre,
                                @RequestParam("file") MultipartFile file,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {
        logger.info("Membre photo recu par le forme: {}", membre.getPhotoProfilPath());

        if (!file.isEmpty()) {
            try {
                // Obtain the file name
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                logger.info("THE FILE NAME: {}", fileName);
                String saveDirectory = "imageUtilisateur/";
                Path directory = Paths.get(saveDirectory);
                if (!Files.exists(directory)) {
                    Files.createDirectories(directory);
                }
                // Save the file
                Path filePath = directory.resolve(fileName);
                if (Files.exists(filePath)) {
                    logger.error("File already exists in the directory: {}", fileName);
                    membre.setPhotoProfilPath("imageUtilisateur/" + fileName);
                } else {
                    try {
                        // Save the file
                        Files.copy(file.getInputStream(), filePath);
                        System.out.println("File copied successfully.");
                        membre.setPhotoProfilPath("imageUtilisateur/" + fileName);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        boolean success = membreService.updateProfile(membre);
        if (success) {
            Membre membre2 = membreService.connexionMembre(membre.getEmail(), membre.getPassword());
            session.setAttribute("loggedInUser", membre2);
            redirectAttributes.addFlashAttribute("messageModifieReussite", "Account modified with success!");
        } else {
            redirectAttributes.addFlashAttribute("messageModifieEchoue", "Failed to modify account. Try again later..");
        }
        return "redirect:/pageUtilisateurReglage";
    }

    @GetMapping("/imageUtilisateur/{fileName}")
    public void telechargerFichier(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        String directoryPath = "imageUtilisateur/";
        Path filePath = Paths.get(directoryPath, fileName);

        if (Files.exists(filePath)) {
            response.setContentType("image/jpeg");

            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

            OutputStream outputStream = response.getOutputStream();

            Files.copy(filePath, outputStream);

            outputStream.flush();
            outputStream.close();
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }


    @PostMapping("/updatePassword")
    public String updatePassword(@RequestParam("passwordOld") String oldPassword,
                                 @RequestParam("passwordNew") String newPassword,
                                 @RequestParam("email") String email,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes){

        Membre membre = new Membre();
        membre.setEmail(email);
        membre.setPassword(oldPassword);

        logger.info("OLD PASSWORD RECEIVED: {}", oldPassword);
        logger.info("NEW PASSWORD RECEIVED: {}", newPassword);
        logger.info("EMAIL RECEIVED: {}", email);

        boolean success = membreService.updatePassword(membre, newPassword);
        logger.info("IS IT A SUCCESS: {}", success);

        if (success) {
            Membre membre2 = membreService.connexionMembre(membre.getEmail(), newPassword);
            session.setAttribute("loggedInUser", membre2);
            redirectAttributes.addFlashAttribute("messageChangePasswordReussi", "Password changed successfuly");
        } else {
            redirectAttributes.addFlashAttribute("messageChangePasswordEchoue", "Problem occured. Try again later..");
        }
        return "redirect:/pageUtilisateurReglage";

    }

    @PostMapping("/updateLanguages")
    public String updateLanguages(@RequestParam("password") String password,
                                 @RequestParam("email") String email,
                                  @RequestParam("languages") List<String> languages,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes){

        boolean success = false;

        Membre membre = new Membre();
        membre.setEmail(email);
        membre.setPassword(password);

        String languagesSpoken = String.join(",", languages);
        success = membreService.updateLanguages(membre, languagesSpoken);
        setLanguagesSpokenSessionAttributes(languagesSpoken, session);

        if (success) {
            Membre membre2 = membreService.connexionMembre(membre.getEmail(), membre.getPassword());
            session.setAttribute("loggedInUser", membre2);
            redirectAttributes.addFlashAttribute("messageModifieLanguagesReussite", "Languages spoken modified with success!");
        } else {
            redirectAttributes.addFlashAttribute("messageModifieLanguagesEchoue", "Failed to modify languages spoken. Try again later..");
        }
        return "redirect:/pageUtilisateurReglage";

    }

    public void setLanguagesSpokenSessionAttributes(String languagesSpoken,
                                                    HttpSession session){

        session.setAttribute("japaneseSpoken", false);
        session.setAttribute("indianSpoken", false);
        session.setAttribute("frenchSpoken", false);
        session.setAttribute("russianSpoken", false);
        session.setAttribute("italianSpoken", false);
        session.setAttribute("spanishSpoken", false);

        String[] languageArray = languagesSpoken.split(",");

        for(String language : languageArray){
            switch(language){
                case "Japanese":
                    session.setAttribute("japaneseSpoken", true);
                    break;
                case "Indian":
                    session.setAttribute("indianSpoken", true);
                    break;
                case "French":
                    session.setAttribute("frenchSpoken", true);
                    break;
                case "Russian":
                    session.setAttribute("russianSpoken", true);
                    break;
                case "Italian":
                    session.setAttribute("italianSpoken", true);
                    break;
                case "Spanish":
                    session.setAttribute("spanishSpoken", true);
                    break;
                default:
                    break;
            }
        }
    }

}

