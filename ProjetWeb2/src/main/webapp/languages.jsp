<%-- 
    Document   : languages
    Created on : Apr 1, 2024, 8:25:53 p.m.
    Author     : Jamil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Page d'accueil</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/style.css"/>
    </head>
    <body>
        <jsp:include page="menu.jsp"/>
        <main>       
            <!-- Left section -->
            <jsp:include page="navbarGauche.jsp"/>

            <!-- Middle section -->
            <div id="middle-section"> 
                <div id="middle-section-countries-layout">
                    <h1>Languages</h1>
                    <div id="middle-section-search-bar">
                        <form class="formSearchBar" action="/search" method="POST">
                            <div>
                                <input type="text" id="searchQuery" name="countryName" placeholder="Find a specific country" onkeydown="handleKeyPress(event)" required>
                                <img src="https://img.icons8.com/pastel-glyph/64/search--v1.png" alt="search--v1"/>
                            </div>
                        </form>
                    </div>
                    <div id="middle-section-grid-layout">
                        <div class="middle-section-grid-container">
                            <div class="grid-item-languages">
                                <img src="images/spain.png" alt="alt"/>
                                <div id="grid-item-title">
                                    <h3>Spanish</h3>
                                </div>
                            </div>
                            <div class="grid-item-languages">
                                <img src="images/russia.png" alt="alt"/>
                                <div id="grid-item-title">
                                    <h3>Russian</h3>
                                </div>
                            </div>
                            <div class="grid-item-languages">
                                <img src="images/france.png" alt="alt"/>
                                <div id="grid-item-title">
                                    <h3>French</h3>
                                </div>
                            </div>
                            <div class="grid-item-languages">
                                <img src="images/japan.png" alt="alt"/>
                                <div id="grid-item-title">
                                    <h3>Japanese</h3>
                                </div>
                            </div>
                            <div class="grid-item-languages">
                                <img src="images/india.png" alt="alt"/>
                                <div id="grid-item-title">
                                    <h3>Indian</h3>
                                </div>
                            </div>
                            <div class="grid-item-languages">
                                <img src="images/china.png" alt="alt"/>
                                <div id="grid-item-title">
                                    <h3>Chinese</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Right section -->
            <c:if test="${not empty sessionScope.user}">
                <jsp:include page="listeAmieDroite.jsp"/>
            </c:if>            
        </main>
        
    </body>
</html>
