package com.jfayad.projetweb2_springboot.controller;

import com.jfayad.projetweb2_springboot.entities.Challenges;
import com.jfayad.projetweb2_springboot.entities.Membre;
import com.jfayad.projetweb2_springboot.services.ChallengesJouerService;
import com.jfayad.projetweb2_springboot.services.ChallengesService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ChallengesController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private ChallengesService challengesService;

    @Autowired
    private ChallengesJouerService challengesJouerService;

    @GetMapping("/challenges")
    public String getAllChallenges(HttpSession session) {
        List<Challenges> challenges = challengesService.findAllChallenges();
        session.setAttribute("challenges", challenges);
        return "challenges";
    }


    @GetMapping("/createChallengeRow")
    @ResponseBody
    public void createChallengeRow(@RequestParam("userId") Integer memberId,
                                     @RequestParam("challengeName") String challengeName,
                                     HttpSession session) {

        boolean success = challengesJouerService.createChallengeRow(memberId, challengeName);

        List<Map<String, Object>> challengesStatus = challengesJouerService.getChallengeStatus(memberId);

        for (int i = 0; i < challengesStatus.size(); i++) {
            Map<String, Object> challengeStatus = challengesStatus.get(i);
            int j = 0;
            for (Map.Entry<String, Object> entry : challengeStatus.entrySet()) {
                if(entry.getValue().equals(challengeName)){
                    for (Map.Entry<String, Object> entry2 : challengeStatus.entrySet()){
                        if(entry2.getKey().equals("first_set_complete")){
                            String value = entry2.getValue().toString();
                            int valueNbr = Integer.valueOf(value);
                            if(valueNbr == 1){
                                session.setAttribute("isFirstSetComplete", true);
                            }else{
                                session.setAttribute("isFirstSetComplete", false);
                            }
                        }else if(entry2.getKey().equals("second_set_complete")){
                            String value = entry2.getValue().toString();
                            int valueNbr = Integer.valueOf(value);
                            if(valueNbr == 1){
                                session.setAttribute("isSecondSetComplete", true);
                            }else{
                                session.setAttribute("isSecondSetComplete", false);
                            }
                        }
                    }
                }
            }
        }

        if(success){
            session.setAttribute("challengeName", challengeName);
        }
    }

    @GetMapping("/updateChallengeRow")
    @ResponseBody
    public void updateChallengeRow(@RequestParam("column_name") String column_name,
                                   @RequestParam("challenge_name") String challenge_name,
                                   HttpSession session,
                                   HttpServletRequest request) {

        session = request.getSession();
        Membre membreEnSession = (Membre) session.getAttribute("loggedInUser");
        Integer id_membre = membreEnSession.getIdMembre();

        boolean success = challengesJouerService.updateChallengeRowValidation(id_membre, challenge_name, column_name);

        List<Map<String, Object>> challengesStatus = challengesJouerService.getChallengeStatus(id_membre);

        for (int i = 0; i < challengesStatus.size(); i++) {
            Map<String, Object> challengeStatus = challengesStatus.get(i);
            int j = 0;
            for (Map.Entry<String, Object> entry : challengeStatus.entrySet()) {
                if(entry.getValue().equals(challenge_name)){
                    for (Map.Entry<String, Object> entry2 : challengeStatus.entrySet()){
                        if(entry2.getKey().equals("first_set_complete")){
                            String value = entry2.getValue().toString();
                            int valueNbr = Integer.valueOf(value);
                            if(valueNbr == 1){
                                session.setAttribute("isFirstSetComplete", true);
                            }else{
                                session.setAttribute("isFirstSetComplete", false);
                            }
                        }else if(entry2.getKey().equals("second_set_complete")){
                            String value = entry2.getValue().toString();
                            int valueNbr = Integer.valueOf(value);
                            if(valueNbr == 1){
                                session.setAttribute("isSecondSetComplete", true);
                            }else{
                                session.setAttribute("isSecondSetComplete", false);
                            }
                        }
                    }
                }
            }
        }

        }

    }
