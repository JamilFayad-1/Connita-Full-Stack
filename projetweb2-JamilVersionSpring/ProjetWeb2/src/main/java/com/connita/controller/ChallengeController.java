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
import java.io.PrintWriter;
import java.util.Map;

/**
 *
 * @author Jamil
 */
public class ChallengeController extends HttpServlet{
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
        int memberId = (int) session.getAttribute("userId");
        String columnName = request.getParameter("columnName");
        String challengeName = request.getParameter("challenge_name");
        success = challengeDao.updateChallenge(memberId, columnName, challengeName);
        Map<String, Map<String, Boolean>> challengeCompletionStatus = challengeDao.getStatus(memberId);
        request.setAttribute("challengeCompletionStatus", challengeCompletionStatus);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print("{ \"success\": " + success + " }");
        out.flush();
    }
}
