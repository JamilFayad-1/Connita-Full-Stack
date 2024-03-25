/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.connita.controller;

import com.connita.model.dao.MembreDao;
import com.connita.model.dao.MembreImplDao;
import com.connita.model.entities.Membre;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
//import java.util.logging.Logger;


/**
 *
 * @author Gwuliano
 */
@MultipartConfig
public class ModifierUtilisateurController extends HttpServlet{
    //private static final Logger logger = Logger.getLogger(ModifierUtilisateurController.class.getName());
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

        Membre membre = new Membre();
        membre.setUsername(username);
        membre.setBio(bio);
        membre.setRegion(region);
        membre.setEmail(userEmail);
        boolean valider = membreDao.updateProfile(membre);
        if(valider) {
            messageInscrReussite = "Modifier avec succés!";
            
            if (!"".equals(request.getParameter("username"))) {
                session.setAttribute("username", membre.getUsername());
            }
            if (!"".equals(request.getParameter("bio"))) {
                session.setAttribute("bio", membre.getBio());
            }
            if (!"".equals(request.getParameter("region"))) {
                session.setAttribute("region", membre.getRegion());
            }
            
            request.setAttribute("messageInscrReussite", messageInscrReussite);
            //logger.log(Level.INFO, "Received username: {0}", membre.getUsername());
            //logger.log(Level.INFO, "Received bio: {0}", membre.getBio());
            //logger.log(Level.INFO, "Received region: {0}", membre.getRegion());
            response.sendRedirect("pageUtilisateur.jsp");
        } else {
            messageInscrEchoue = "Erreur, réessayer plus tard..";
            request.setAttribute("messageInscrEchoue", messageInscrEchoue);
        }
    }
}
