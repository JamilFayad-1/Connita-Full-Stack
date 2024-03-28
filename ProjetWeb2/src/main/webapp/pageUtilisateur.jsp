<%-- 
    Document   : pageUtilisateur
    Created on : Mar. 19, 2024, 1:34:08 a.m.
    Author     : Gwuliano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
   String messageModifieReussite = (String)request.getAttribute("messageModifieReussite");
   String messageModifieEchoue = (String)request.getAttribute("messageModifieEchoue");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <title>Réglage de compte</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"/>
        
        <div class="messagePopUps">
            <% if (request.getAttribute("messageModifieReussite") != null) { %>
                <p class="messageTextReussie"><%= messageModifieReussite %></p>
            <% } else if (request.getAttribute("messageModifieEchoue") != null) { %>
                <p class="messageTextEchoue"><%= messageModifieEchoue %></p>
            <% } %>
        </div>
        
        <div class="reglage-nav-wrapper">
            <div class="reglage-nav-container">
                <ul>
                    <li><a href="pageAccueilUtilisateur.jsp">Feed</a></li>
                    <li><a id="general-link">General</a></li>
                    <li><a id="security-link">Security</a></li>
                    <li><a id="languages-link">Languages</a></li>
                </ul>
            </div>
        </div>
        <div id="general-form" class="utilisateur-reglage-wrapper">
            <div class="utilisateur-reglage-container"  id="form-container">
                <form action="ModifierUtilisateurController" autocomplete="off" method="post" enctype="multipart/form-data">
                    <div class="utilisateur-header">
                        <div class="photo-profil">
                            <input type="file" id="photo-profil-up1" name="profilPic">
                            <label for="photo-profil-up1">
                                <img id="photoProfilDis1" src="images/Default-profile-pic.png" alt="Profile Picture">
                                <span class="tooltip">Choose Picture</span>
                            </label>
                        </div>
                        <div class="utilisateur-username">
                            <h1 id="firstNameDis1"></h1>
                            <p id="usernameDis1"></p>
                            <p class="text-bio" id="bioDis1"></p>
                        </div>
                    </div>
                    <div class="utilisateur-body">
                        <div class="reglage-form-row">
                            <label for="username">Username</label>
                            <input type="text" id="username" name="username" placeholder="Enter your username">
                        </div>
                        <div class="reglage-form-row">
                            <label for="bio">Bio</label>
                            <textarea type="text" id="bio" row="4" name="bio" placeholder="Enter something interesting about you!"></textarea>
                        </div>
                        <div class="reglage-form-row">
                            <label for="region">Country</label>
                            <input type="text" id="region" name="region" placeholder="Enter your country">
                        </div>
                        <div class="reglage-form-button">
                            <input type="submit" value="Apply">
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div id="security-form" class="utilisateur-reglage-wrapper">
            <div class="utilisateur-reglage-container"  id="form-container">
                <form action="ChangementMotDePasseController" autocomplete="off" method="post">
                    <div class="utilisateur-header">
                        <div class="photo-profil">
                            <label for="photo-profil-up2">
                                <img id="photoProfilDis2" src="images/Default-profile-pic.png" alt="Profile Picture">
                            </label>
                        </div>
                        <div class="utilisateur-username">
                            <h1 id="firstNameDis2"></h1>
                            <p id="usernameDis2"></p>
                            <p class="text-bio" id="bioDis2"></p>
                        </div>
                    </div>
                    <div class="utilisateur-body">
                        <div class="reglage-form-row">
                            <label for="passwordOld">Old password</label>
                            <input type="password" id="passwordOld" name="passwordOld" placeholder="Enter your old password">
                        </div>
                        <div class="reglage-form-row">
                            <label for="passwordNew">New password</label>
                            <input type="password" id="passwordNew" name="passwordNew" placeholder="Enter your new password">
                        </div>
                        <div class="reglage-form-button">
                            <input type="submit" value="Apply">
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div id="languages-form" class="utilisateur-reglage-wrapper">
            <div class="utilisateur-reglage-container"  id="form-container">
                <form method="post">
                    <div class="utilisateur-header">
                        <div class="photo-profil">
                            <label for="photo-profil-up3">
                                <img id="photoProfilDis3" src="images/Default-profile-pic.png" alt="Profile Picture">
                            </label>
                        </div>
                        <div class="utilisateur-username">
                            <h1 id="firstNameDis3"></h1>
                            <p id="usernameDis3"></p>
                            <p class="text-bio" id="bioDis3"></p>
                        </div>
                    </div>
                    <div class="utilisateur-body">
                        <div class="reglage-form-row">
                            <label for="languages">Languages spoken</label>
                            <div class="rectangle-container">
                                <div class="rectangle">
                                    <input type="checkbox" id="japonais" name="languages" value="Japanese">
                                    <img class="imgChecked" width="50" height="50" src="https://img.icons8.com/ios-filled/50/checked--v1.png" alt="checked--v1"/>
                                    <label for="japonais"><img src="images/japan.png" alt="Japanese Flag"></label>
                                </div>
                                <div class="rectangle">
                                    <input type="checkbox" id="indien" name="languages" value="Indian">
                                    <img class="imgChecked" width="50" height="50" src="https://img.icons8.com/ios-filled/50/checked--v1.png" alt="checked--v1"/>
                                    <label for="indien"><img src="images/india.png" alt="Indian Flag"></label>
                                </div>
                                <div class="rectangle">
                                    <input type="checkbox" id="francais" name="languages" value="French">
                                    <img class="imgChecked" width="50" height="50" src="https://img.icons8.com/ios-filled/50/checked--v1.png" alt="checked--v1"/>
                                    <label for="francais"><img src="images/france.png" alt="French Flag"></label>
                                </div>
                                <div class="rectangle">
                                    <input type="checkbox" id="russe" name="languages" value="Russian">
                                    <img class="imgChecked" width="50" height="50" src="https://img.icons8.com/ios-filled/50/checked--v1.png" alt="checked--v1"/>
                                    <label for="russe"><img src="images/russia.png" alt="Russian Flag"></label>
                                </div>
                                <div class="rectangle">
                                    <input type="checkbox" id="chinois" name="languages" value="Chinese">
                                    <img class="imgChecked" width="50" height="50" src="https://img.icons8.com/ios-filled/50/checked--v1.png" alt="checked--v1"/>
                                    <label for="chinois"><img src="images/china.png" alt="Chinese Flag"></label>
                                </div>
                                <div class="rectangle">
                                    <input type="checkbox" id="espagnol" name="languages" value="Spanish">
                                    <img class="imgChecked" width="50" height="50" src="https://img.icons8.com/ios-filled/50/checked--v1.png" alt="checked--v1"/>
                                    <label for="espagnol"><img src="images/spain.png" alt="Spanish Flag"></label>
                                </div>
                            </div>
                        </div>
                        <div class="reglage-form-button">
                            <input type="submit" value="Apply">
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <script>
            document.addEventListener("DOMContentLoaded", function() {
                showForm('general');
                updateProfileInfo();
                
                document.getElementById('general-link').addEventListener('click', function(event) {
                    showForm('general');
                    event.preventDefault(); // Prevent default anchor behavior
                });

                document.getElementById('security-link').addEventListener('click', function(event) {
                    showForm('security');
                    event.preventDefault(); // Prevent default anchor behavior
                });

                document.getElementById('languages-link').addEventListener('click', function(event) {
                    showForm('languages');
                    event.preventDefault(); // Prevent default anchor behavior
                });
                
                function showForm(formName) {
                    var allForms = document.querySelectorAll('.utilisateur-reglage-wrapper');
                    allForms.forEach( e => {
                        e.style.display = 'none';
                    });
                    // Show the selected form
                    var selectedForm = document.getElementById(formName + '-form');
                    if (selectedForm) {
                        selectedForm.style.display = 'flex';
                    }
                }
            });
            
            function updateProfileInfo() {
                var photoProfilDis = "${sessionScope.photoProfil}";
                var firstNameDis = "${sessionScope.firstName}";
                var lastNameDis = "${sessionScope.lastName}";
                var usernameDis = "${sessionScope.username}";
                var bioDis = "${sessionScope.bio}";

                document.getElementById('photoProfilDis1').src = "imageUtilisateur/" + photoProfilDis;
                console.log("imageUtilisateur/" + photoProfilDis);
                console.log(photoProfilDis);
                document.getElementById('firstNameDis1').textContent = firstNameDis + " " + lastNameDis;
                document.getElementById('usernameDis1').textContent = "@" + usernameDis;
                document.getElementById('bioDis1').textContent = bioDis;
                
                document.getElementById('photoProfilDis2').src = "imageUtilisateur/" +  photoProfilDis;
                document.getElementById('firstNameDis2').textContent = firstNameDis + " " + lastNameDis;
                document.getElementById('usernameDis2').textContent = "@" + usernameDis;
                document.getElementById('bioDis2').textContent = bioDis;
                
                document.getElementById('photoProfilDis3').src = "imageUtilisateur/" +  photoProfilDis;
                document.getElementById('firstNameDis3').textContent = firstNameDis + " " + lastNameDis;
                document.getElementById('usernameDis3').textContent = "@" + usernameDis;
                document.getElementById('bioDis3').textContent = bioDis;
            }

            function stretchImage(container) {
                var image = container.querySelector('img'); // Get the <img> element inside the container

                if (image) { // Check if the image element exists
                    var containerWidth = container.offsetWidth;
                    var containerHeight = container.offsetHeight;
                    var minDimension = Math.min(containerWidth, containerHeight);

                    image.style.width = minDimension + 'px';
                    image.style.height = minDimension + 'px';
                } else {
                    console.error('Image element not found');
                }
            }
            

            var fileInput1 = document.getElementById('photo-profil-up1');
            var image = document.querySelectorAll('.photo-profil img');

            fileInput1.addEventListener('change', handleFileSelect);

            function handleFileSelect(event) {
                var file = event.target.files[0];

                if (file) {
                    var reader = new FileReader();

                    reader.onload = function() {
                        var tempImage = new Image();

                        tempImage.onload = function() {
                            if (tempImage.width === tempImage.height) {
                                image.forEach(img => {
                                    img.src = reader.result;
                                    stretchImage(img.parentNode);
                                });
                            } else {
                                alert('Please select a square image.\nA minimum of 150px by 150px is recommended');
                                event.target.value = ''; // Clear the selected file
                            }
                        };

                        tempImage.src = reader.result;
                    };

                    reader.readAsDataURL(file);
                }
            }
        </script>
    </body>
</html>
