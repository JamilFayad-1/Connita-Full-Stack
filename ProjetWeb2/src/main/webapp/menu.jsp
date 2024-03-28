<%-- 
    Document   : menu
    Created on : Mar. 19, 2024, 1:19:17 a.m.
    Author     : Gwuliano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Menu Page</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${not empty sessionScope.user}">
                <header>
                    <div id="logo-container">
                        <a href="pageAccueilUtilisateur.jsp">
                            <img src="images/My first design.png" alt="Company logo"/>
                        </a>
                    </div>
                    <nav id="horizontal-nav">
                        <ul>
                            <li><a href=""><img src="https://img.icons8.com/ios-glyphs/30/add-user-group-woman-man.png" alt="add-user-group-woman-man"/></a></li>
                            <li><a href=""><img src="https://img.icons8.com/ios-glyphs/30/speech-bubble-with-dots.png" alt="speech-bubble-with-dots"/></a></li>
                            <li><a href=""><img src="https://img.icons8.com/ios-glyphs/30/filled-appointment-reminders.png" alt="filled-appointment-reminders"/></a></li>
                            <li id="dropdown-toggle">
                                <a><img id="profilePicMenu" src="imageUtilisateur/${sessionScope.photoProfil}" alt="test-account"/></a>
                                <div class="dropdown-menu" id="dropdownMenu">
                                    <a href="pageUtilisateur.jsp" class="dropdown-link">Settings</a>
                                    <a href="DeconnexionController" class="dropdown-link">Sign out</a>
                                </div>
                            </li>
                        </ul>  
                    </nav>
                </header>
            </c:when>
            <c:otherwise>
                <header>
                    <div id="logo-container">
                        <a href="index.jsp">
                            <img src="images/My first design.png" alt="Company logo"/>
                        </a>
                    </div>
                    <nav id="horizontal-nav">
                        <div>
                            <button class="btnSignup" type="button">Start learning</button>
                        </div>
                    </nav>
                </header>
            </c:otherwise>
        </c:choose>
        <script>
            const dropdownToggle = document.getElementById('dropdown-toggle');
            const dropdownMenu = document.getElementById('dropdownMenu');

            dropdownToggle.addEventListener('click', function (event) {
                dropdownMenu.style.display = dropdownMenu.style.display === 'block' ? 'none' : 'block';
                event.preventDefault();
            });
            
            dropdownMenu.addEventListener('click', function (event) {
                event.stopPropagation();
            });

            document.addEventListener('click', function (event) {
                if (!dropdownToggle.contains(event.target) && !dropdownMenu.contains(event.target)) {
                    dropdownMenu.style.display = 'none';
                }
            });
            
            function updateProfileInfo() {
                var photoProfilDis = "${sessionScope.photoProfil}";

                document.getElementById('profilePicMenu').src = "imageUtilisateur/" + photoProfilDis;
                
            }
        </script>
    </body>
</html>
