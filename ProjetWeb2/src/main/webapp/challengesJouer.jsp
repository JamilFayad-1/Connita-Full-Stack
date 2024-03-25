<%-- 
    Document   : challengesJouer
    Created on : Mar. 24, 2024, 11:22:00 p.m.
    Author     : Gwuliano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css"/>
        <title>Challenge</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"/>
        
        <div class="challenge-Wrapper">
            <div class="challenge-container">
                <h1>Introduction to Spanish</h1>
                <div class="quiz">
                    <h2 id="question">Question goes here</h2>
                    <div id="answer-buttons">
                        <button class="btnAnswer">Answer 1</button>
                        <button class="btnAnswer">Answer 2</button>
                        <button class="btnAnswer">Answer 3</button>
                        <button class="btnAnswer">Answer 4</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
