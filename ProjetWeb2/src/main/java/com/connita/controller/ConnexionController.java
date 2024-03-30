/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.connita.controller;

import com.connita.model.dao.ChallengeDao;
import com.connita.model.dao.ChallengeImplDao;
import com.connita.model.dao.MembreDao;
import com.connita.model.dao.MembreImplDao;
import com.connita.model.entities.Membre;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
//import java.util.logging.Level;
//import java.util.logging.Logger;

/**
 *
 * @author Gwuliano
 */
public class ConnexionController extends HttpServlet {
    //private static final Logger logger = Logger.getLogger(ChallengeController.class.getName());
    private MembreDao membreDao;
    private ChallengeDao challengeDao;
    private String message;
    private HttpSession session;

    @Override
    public void init() throws ServletException {
        super.init();
        membreDao = new MembreImplDao();
        challengeDao = new ChallengeImplDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve login credentials from the request parameters
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Check if the user exists in the database
        Membre membre = membreDao.existsByEmailAndPassword(email, password);
        Map<String, Boolean> challengeCompletionStatus = challengeDao.getStatus(membre.getIdMembre());

        if (membre != null) {
            session = request.getSession(true);
            session.setAttribute("userId", membre.getIdMembre());
            session.setAttribute("user", email);
            if ("".equals(membre.getPhotoProfil())){
                session.setAttribute("photoProfil", "Default-profile-pic.png");
            }else {
                session.setAttribute("photoProfil", membre.getPhotoProfil());
            }
            session.setAttribute("firstName", membre.getPrenom());
            session.setAttribute("lastName", membre.getNom());
            session.setAttribute("username", membre.getUsername());
            session.setAttribute("bio", membre.getBio());
            session.setAttribute("region", membre.getRegion());
            
            request.setAttribute("challengeCompletionStatus", challengeCompletionStatus);
            response.sendRedirect("pageAccueilUtilisateur.jsp");
        } else {
            message = "Password or email are invalid..";
            request.setAttribute("messageConnEchoue", message);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
