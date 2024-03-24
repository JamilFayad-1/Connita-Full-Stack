/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.connita.controller;

import com.connita.model.dao.MembreDao;
import com.connita.model.dao.MembreImplDao;
import com.connita.model.entities.Membre;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Gwuliano
 */
public class ModifierUtilisateurController extends HttpServlet{
    private static final Logger logger = Logger.getLogger(ModifierUtilisateurController.class.getName());
    private MembreDao membreDao;
    private String messageInscrReussite;
    private String messageInscrEchoue;
    HttpSession session;
    

    @Override
    public void init() throws ServletException {
        super.init();
        membreDao = new MembreImplDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession(false);
        
        String username;
        String bio;
        String region;
        String userEmail; 
        
        if ("".equals(request.getParameter("username"))) {
            username = " ";
        }else {
            username = request.getParameter("username");
        }
        
        if ("".equals(request.getParameter("bio"))) {
            bio = " ";
        }else {
            bio = request.getParameter("bio");
        }
        
        if ("".equals(request.getParameter("region"))) {
            region = " ";
        }else {
            region = request.getParameter("region");
        }
        
        userEmail = (String) session.getAttribute("user"); 
        
        logger.log(Level.INFO, "Received request to update profile for user with email: {0}", userEmail);
        logger.log(Level.INFO, "Received username: {0}", username);
        logger.log(Level.INFO, "Received bio: {0}", bio);
        logger.log(Level.INFO, "Received region: {0}", region);

        Membre membre = new Membre();
        membre.setUsername(username);
        membre.setBio(bio);
        membre.setRegion(region);
        membre.setEmail(userEmail);
        boolean valider = membreDao.updateProfile(membre);
        if(valider) {
            messageInscrReussite = "Modifier avec succés!";
            request.setAttribute("messageInscrReussite", messageInscrReussite);
            request.getRequestDispatcher("pageUtilisateur.jsp").forward(request, response);
        } else {
            messageInscrEchoue = "Erreur, réessayer plus tard..";
            request.setAttribute("messageInscrEchoue", messageInscrEchoue);
        }
        
        if (valider) {
            logger.log(Level.INFO, "Profile update successful for user with email: {0}", userEmail);
        } else {
            logger.log(Level.WARNING, "Profile update failed for user with email: {0}", userEmail);
        }
        
    }
}
