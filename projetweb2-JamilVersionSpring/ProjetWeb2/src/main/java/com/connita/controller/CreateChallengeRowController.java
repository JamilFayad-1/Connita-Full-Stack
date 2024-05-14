/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.connita.controller;

import com.connita.model.dao.ChallengeDao;
import com.connita.model.dao.ChallengeImplDao;
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
public class CreateChallengeRowController extends HttpServlet{
    private ChallengeDao challengeDao;
    HttpSession session;    
    boolean success = false;

    @Override
    public void init() throws ServletException {
        super.init();
        challengeDao = new ChallengeImplDao();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession(false);
        int userId = (int) session.getAttribute("userId");
        String challengeName = request.getParameter("challengeName");
        
        boolean verifyRow = challengeDao.verifyChallengeRowExists(userId, challengeName);

        if(verifyRow){
            success = true;
        }else{
            success = challengeDao.createChallengeRow(userId, challengeName);
        }

        // Set response content type and send success status
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{ \"success\": " + success + " }");
    }
}
