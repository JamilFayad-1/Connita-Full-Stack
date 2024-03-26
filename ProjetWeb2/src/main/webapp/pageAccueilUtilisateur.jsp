<%-- 
    Document   : pageAccueilUtilisateur
    Created on : Mar. 17, 2024, 5:42:47 p.m.
    Author     : Gwuliano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Page d'accueil utilisateur</title>
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
                <div id="middle-section-recent-work">
                    <h1>Recent work</h1>
                    <div id="recent-work-container">
                        <div>

                        </div>
                    </div>
                </div>
                
                <div id="middle-section-recent-posts">
                    <h1>Recent posts</h1>
                
                     <c:forEach var="publication" items="${requestScope.publications}">
                         <div id="recent-post-container">
                        <div>
                            <div class="titre-container-pub">${publication.getTitre()}</div>
                            <image class="image-container-pub" src="images/${publication.getImage()}">
                        </div>
                    </div>
                </div>
                
                
                <div id="middle-section-achievements">
                    <h1>Achievement badges</h1>
                    <div id="achievements-container">
                        <ul>
                            <li><img src="https://img.icons8.com/dusk/64/army-star.png" alt="army-star"/></li>
                            <li><img src="https://img.icons8.com/dusk/64/world-map.png" alt="world-map"/></li>
                            <li><img src="https://img.icons8.com/dusk/64/saving-book.png" alt="saving-book"/></li>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- Right section -->
            <jsp:include page="listeAmieDroite.jsp"/>
        </main>
        <script src="javascript/javascript.js"></script>
    </body>
</html>
