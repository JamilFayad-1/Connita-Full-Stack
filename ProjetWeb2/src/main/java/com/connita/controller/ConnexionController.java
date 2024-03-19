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
import java.io.IOException;

/**
 *
 * @author Gwuliano
 */
public class ConnexionController extends HttpServlet {
    private MembreDao membreDao;

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
            // User exists, set session attributes and redirect to home page
            request.getSession().setAttribute("loggedInUser", membre);
            response.sendRedirect("pageAccueilUtilisateur.jsp");
        } else {
            // User does not exist or invalid credentials, display error message
            response.setContentType("text/html");
            response.getWriter().println("<html><body>");
            response.getWriter().println("<h2>Login Failed. Invalid username or password.</h2>");
            response.getWriter().println("</body></html>");
        }
    }
}
