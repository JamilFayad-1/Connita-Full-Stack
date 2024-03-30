<%-- 
    Document   : challengesJouer
    Created on : Mar. 24, 2024, 11:22:00 p.m.
    Author     : Gwuliano
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.connita.model.dao.ChallengeImplDao" %>
<%@ page import="java.util.Map;" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css"/>
        <title>Challenge</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>
    <body>
        <jsp:include page="menu.jsp"/>
        <%
            Integer userId = (Integer) session.getAttribute("userId");

            ChallengeImplDao challengeDao = new ChallengeImplDao();
            Map<String, Boolean> challengeCompletionStatus = challengeDao.getStatus(userId);
        %>
        <div class="challenge-set-nav-wrapper">
            <div class="challenge-set-nav-container">
                <ul id="challenge-set-nav-list">
                    <button class="btnSet" onclick="loadChallengeSet(questions1); setTableNameIndex(1)" <%= challengeCompletionStatus.get("firstSetComplete") ? "" : "disabled" %>>Challenge 1</button>
                    <button class="btnSet" onclick="loadChallengeSet(questions2); setTableNameIndex(2)" <%= challengeCompletionStatus.get("secondSetComplete") ? "" : "disabled" %>>Challenge 2</button>
                    <button class="btnSet" onclick="loadChallengeSet(questions3); setTableNameIndex(3)" <%= challengeCompletionStatus.get("thirdSetComplete") ? "" : "disabled" %>>Challenge 3</button>
                </ul>
            </div>
        </div>
        
        <div class="challenge-Wrapper">
            <div class="challenge-container">
                <h1>Introduction to Italian</h1>
                <div class="quiz">
                    <h2 id="question">Question goes here</h2>
                    <div id="answer-buttons">
                        <button class="btnAnswer">Answer 1</button>
                        <button class="btnAnswer">Answer 2</button>
                        <button class="btnAnswer">Answer 3</button>
                        <button class="btnAnswer">Answer 4</button>
                    </div>
                    <button id="btnNext">Next</button>
                </div>
            </div>
        </div>
        <script src="javascript/javascriptChallenges.js"></script>
    </body>
</html>
