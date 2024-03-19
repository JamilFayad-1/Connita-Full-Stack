<%-- 
    Document   : navbarGauche
    Created on : Mar. 19, 2024, 5:00:33 a.m.
    Author     : Gwuliano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nav bar</title>
    </head>
    <body>
        <div id="left-section">
            <div id="left-section-nav-bar">
                <ul>
                    <c:choose>
                        <c:when test="${not empty sessionScope.user}">
                            <li><a href="pageAccueilUtilisateur.jsp">Feed</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="index.jsp">Home page</a></li>
                        </c:otherwise>
                    </c:choose>
                    <li><a href="#">Learning mates</a></li>
                    <li><a href="#">Languages</a></li>
                    <li><a href="#">Countries</a></li>
                    <li><a href="#">Travel</a></li>
                    <li><a href="challenges.jsp">Challenges</a></li>
                </ul>
            </div>
        </div>
    </body>
</html>
