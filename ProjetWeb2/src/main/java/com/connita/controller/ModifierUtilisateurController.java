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
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Gwuliano
 */
@MultipartConfig
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
        
        Part filePart = request.getPart("profilPic");
        logger.log(Level.INFO, "Part received:", filePart);
        // Obtenir le nom de fichier
        String fileName = getFileName(filePart);
        // Spécifier le répertoire où tu veux enregistrer le fichier
        String saveDirectory = "/imageUtilisateur";
        String savePath = saveDirectory + File.separator + fileName;
        // Enregistrer le fichier dans le répertoire spécifié
        File file = new File(savePath);
        try (InputStream input = filePart.getInputStream(); OutputStream output = new FileOutputStream(file)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        String username;
        String bio;
        String region;
        String userEmail;  
        String photoProfilPath;
        
        if ("".equals(request.getPart("profilPic"))) {
            photoProfilPath = " ";
        }else {
            photoProfilPath = fileName;
        }
        
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
        membre.setPhotoProfil(fileName);
        membre.setUsername(username);
        membre.setBio(bio);
        membre.setRegion(region);
        membre.setEmail(userEmail);
        
        boolean valider = membreDao.updateProfile(membre);
        if(valider) {
            messageInscrReussite = "Modifier avec succés!";
            
            if (!"".equals(request.getPart("profilPic"))) {
                session.setAttribute("photoProfil", membre.getPhotoProfil());
            }
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
    
    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");

        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                String fileName = token.substring(token.indexOf("=") + 2, token.length() - 1);

                return fileName;
            }
        }
        return "";
    }
}

