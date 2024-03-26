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
    private static final String UPLOAD_DIR = "imageUtilisateur";
    

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
        
        Part filePart = request.getPart("profilPic");
        if (filePart != null) {
            String fileName = getFileName(filePart);

            if (fileName != null && !fileName.isEmpty()) {
                InputStream fileContent = filePart.getInputStream();
                logger.log(Level.INFO, "Received fileContent: {0}", fileContent);
                saveFile(fileContent, fileName);
                // Assuming the file is saved successfully, set the profile picture path in the member object
                membre.setPhotoProfil("../imageUtilisateur/" + fileName);
            }
        }
        
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
    
    private String getFileName(final Part part) {
    final String partHeader = part.getHeader("content-disposition");
    logger.log(Level.INFO, "Received header: {0}", part.getHeader("content-disposition"));
    for (String content : partHeader.split(";")) {
        if (content.trim().startsWith("filename")) {
            // Extract and return the file name
            return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
        }
    }
    return null;
}

    // Method to save file
    private void saveFile(InputStream fileContent, String fileName) throws IOException {
    String uploadPath = getServletContext().getRealPath("../" + UPLOAD_DIR); // Corrected path
    File uploadDir = new File(uploadPath);
    if (!uploadDir.exists()) {
        uploadDir.mkdirs(); // Use mkdirs() to create parent directories if necessary
    }
    String savePath = uploadPath + File.separator + fileName;
    try (OutputStream out = new FileOutputStream(new File(savePath))) {
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = fileContent.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
    }
}
    
}

