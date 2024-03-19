package com.connita.controller;

import com.connita.model.dao.MembreDao;
import com.connita.model.dao.MembreImplDao;
import com.connita.model.entities.Membre;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


public class InscriptionController extends HttpServlet {
    private MembreDao membreDao;
    private String messageInscrReussite;
    private String messageInscrEchoue;
    

    @Override
    public void init() throws ServletException {
        super.init();
        membreDao = new MembreImplDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Membre membre = new Membre();
        membre.setNom(firstName);
        membre.setPrenom(lastName);
        membre.setEmail(email);
        membre.setPassword(password);
        boolean valider = membreDao.ajouterMembre(membre);
        if(valider) {
            messageInscrReussite = "Inscription réussite!";
            request.setAttribute("messageInscrReussite", messageInscrReussite);
        } else {
            messageInscrEchoue = "Inscription echoué, réessayer plus tard..";
            request.setAttribute("messageInscrEchoue", messageInscrEchoue);
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}

