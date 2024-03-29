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


/**
 *
 * @author Gwuliano
 */
@MultipartConfig
public class ModifierUtilisateurController extends HttpServlet{
    private MembreDao membreDao;
    private String messageModifieReussite;
    private String messageModifieEchoue;
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
        String photoProfilPath;
        Part filePart = request.getPart("profilPic");
        
        if (filePart == null || filePart.getSize() == 0) {
            // No input for profile picture, set default path
            photoProfilPath = "NADA";
        } else {
            // There's input for profile picture, proceed with handling the file
            // Obtain the file name
            String fileName = getFileName(filePart);
            // Specify the directory to save the file
            String saveDirectory = "C:/Users/Jamil/OneDrive/Documents/NetBeansProjects/projetweb2/ProjetWeb2/src/main/webapp/imageUtilisateur";
            String savePath = saveDirectory + File.separator + fileName;
            // Create a File object to represent the file
            File file = new File(savePath);
            if (file.exists()) {
                // File already exists, handle accordingly (e.g., show an error message)
                // You can choose to overwrite the existing file, rename the new file, or take other actions as needed
                System.out.println("File already exists in the directory.");
            } else {
                // File does not exist, proceed with copying
                try (InputStream input = filePart.getInputStream(); OutputStream output = new FileOutputStream(file)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = input.read(buffer)) != -1) {
                        output.write(buffer, 0, bytesRead);
                    }
                    System.out.println("File copied successfully.");
                } catch (IOException e) {
                    e.printStackTrace();
                    // Handle IOException (e.g., log error, show error message)
                }
            }
            // Set the profile picture path to the file name
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
        membre.setPhotoProfil(photoProfilPath);
        membre.setUsername(username);
        membre.setBio(bio);
        membre.setRegion(region);
        membre.setEmail(userEmail);
        
        boolean valider = membreDao.updateProfile(membre);
        if(valider) {
            if (!"NADA".equals(photoProfilPath)) {
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
            
            messageModifieReussite = "Changes saved!";
            request.setAttribute("messageModifieReussite", messageModifieReussite);
        } else {
            messageModifieEchoue = "Something went wrong, changes not saved..";
            request.setAttribute("messageModifieEchoue", messageModifieEchoue);
        }
        request.getRequestDispatcher("pageUtilisateur.jsp").forward(request, response);
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

