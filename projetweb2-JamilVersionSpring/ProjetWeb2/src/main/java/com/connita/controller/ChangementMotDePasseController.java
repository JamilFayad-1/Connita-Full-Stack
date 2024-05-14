/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.connita.controller;

import com.connita.model.dao.MembreDao;
import com.connita.model.dao.MembreImplDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author Jamil
 */
public class ChangementMotDePasseController extends HttpServlet {
    String messageChangePasswordReussi;
    String messageChangePasswordEchoue;
    private MembreDao membreDao;
    HttpSession session;    

    @Override
    public void init() throws ServletException {
        super.init();
        membreDao = new MembreImplDao();
    }
    
    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession(false);
        String oldPassword = request.getParameter("passwordOld");
        String newPassword = request.getParameter("passwordNew");
        String email = (String) session.getAttribute("user");

        boolean ancienMotDePasseVerifier = membreDao.verifierPassword(email, oldPassword);
        boolean changementMotDePasseReussi = false;

        if (ancienMotDePasseVerifier) {
            changementMotDePasseReussi = membreDao.updatePassword(email, oldPassword, newPassword);
        }

        if (changementMotDePasseReussi) {
            messageChangePasswordReussi = "Password changed successfully!";
            request.setAttribute("messageChangePasswordReussi", messageChangePasswordReussi);
        } else {
            messageChangePasswordEchoue = "Failed to change password. Please check your old password.";
            request.setAttribute("messageChangePasswordEchoue", messageChangePasswordEchoue);
        }
        request.getRequestDispatcher("pageUtilisateur.jsp").forward(request, response);
    }
}
