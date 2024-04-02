<%-- 
    Document   : challenges
    Created on : Mar. 18, 2024, 7:31:45 p.m.
    Author     : Gwuliano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.connita.model.entities.Challenge" %>
<%@ page import="com.connita.model.dao.ChallengeImplDao" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Page d'accueil</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/style.css"/>
        <title>Challenges</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>
    <body>

        <jsp:include page="menu.jsp"/>
        
        <main>       
            <!-- Left section -->
            <jsp:include page="navbarGauche.jsp"/>
            <!-- Middle section -->
            <div id="middle-section"> 
                <div id="middle-section-challenges-layout">
                    <h1>Challenges</h1>
                    <div id="middle-section-search-bar">
                        <form class="formSearchBar" action="/search" method="POST">
                            <div>
                                <input type="text" id="searchQuery" name="challengeName" placeholder="Find a specific challenge" onkeydown="handleKeyPress(event)" required>
                                <img src="https://img.icons8.com/pastel-glyph/64/search--v1.png" alt="search--v1"/>
                            </div>
                        </form>
                    </div>
                    <%
                        ChallengeImplDao challengeDao = new ChallengeImplDao();
                        // Assuming you have a method to retrieve challenge data from the database
                        List<Challenge> challenges = challengeDao.getChallengesFromDatabase();

                        // Loop through the list of challenges and display each one
                        for (Challenge challenge : challenges) {
                    %>
                        <div id="middle-section-challenges-element" class="<%= session.getAttribute("user") != null ? "logged-in" : "logged-out" %>">
                            <div class="circle-container">
                                <div class="fraction">1/3</div>
                            </div>
                            <div id="subject-challenge-container">
                                <div id="subject-challenge">
                                    <img src="<%= challenge.getChallengeImageUrl() %>" alt="<%= challenge.getChallengeName() %>"/>
                                    <h3><%= challenge.getChallengeName() %></h3>
                                </div>
                                <p><%= challenge.getChallengeDescription() %></p>
                            </div>
                            <div id="challenge-complete">
                                    <h3>Complete</h3>
                            </div>
                        </div>
                    <%
                        }
                    %>
                </div>
            </div>
            <!-- Right section -->
            <c:if test="${not empty sessionScope.user}">
                <jsp:include page="listeAmieDroite.jsp"/>
            </c:if>
        </main>
        
            <script>
                document.addEventListener('DOMContentLoaded', function() {
                    var challengeElements = document.querySelectorAll('#middle-section-challenges-element');

                    challengeElements.forEach(function(challengeElement) {
                        challengeElement.addEventListener('click', function() {
                            if (!challengeElement.classList.contains('logged-out')) {
                                
                                var challengeName = challengeElement.querySelector('h3').textContent;               
                                var url = 'challengesJouer.jsp?challengeName=' + encodeURIComponent(challengeName);
                
                                window.location.href = url;
                                
                                var userId = '<%= session.getAttribute("userId") %>';
                                createTableRow(userId, challengeName);
                            }
                        });
                    });
                    
                    function createTableRow(userId, challengeName) {
                        $.ajax({
                            url: 'CreateChallengeRowController',
                            method: 'POST',
                            data: { userId: userId, challengeName: challengeName },
                            success: function(response) {
                                console.log('Row created successfully');
                            },
                            error: function(xhr, status, error) {
                                console.error('Error creating row:', xhr.responseText);
                            }
                        });
                    }
                });
            </script>
    </body>
</html>
