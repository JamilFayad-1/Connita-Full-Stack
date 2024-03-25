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

/**
 *
 * @author Gwuliano
 */
public class ConnexionController extends HttpServlet {
    private MembreDao membreDao;
    private String message;
    private HttpSession session;

    @Override
    public void init() throws ServletException {
        super.init();
        membreDao = new MembreImplDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve login credentials from the request parameters
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Check if the user exists in the database
        Membre membre = membreDao.existsByEmailAndPassword(email, password);

        if (membre != null) {
            session = request.getSession(true);
            session.setAttribute("user", email);
            session.setAttribute("firstName", membre.getPrenom());
            session.setAttribute("lastName", membre.getNom());
            session.setAttribute("username", membre.getUsername());
            session.setAttribute("bio", membre.getBio());
            session.setAttribute("region", membre.getRegion());
            
            response.sendRedirect("pageAccueilUtilisateur.jsp");
        } else {
            message = "L'email ou le mot de passe est invalide.";
            request.setAttribute("message", message);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
