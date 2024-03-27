<%-- 
    Document   : pageUtilisateur
    Created on : Mar. 19, 2024, 1:34:08 a.m.
    Author     : Gwuliano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <title>Réglage de compte</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"/>
        <div class="reglage-nav-wrapper">
            <div class="reglage-nav-container">
                <ul>
                    <li><a href="pageAccueilUtilisateur.jsp">Feed</a></li>
                    <li><a onclick="showForm('general')">General</a></li>
                    <li><a onclick="showForm('security')">Security</a></li>
                    <li><a onclick="showForm('languages')">Languages</a></li>
                </ul>
            </div>
        </div>
        <div class="utilisateur-reglage-wrapper">
            <div class="utilisateur-reglage-container"  id="form-container">
                <form id="general-form" action="ModifierUtilisateurController" autocomplete="off" method="post" enctype="multipart/form-data">
                    <div class="utilisateur-header">
                        <div class="photo-profil">
                            <input type="file" id="photo-profil-up" name="profilPic">
                            <label for="photo-profil-up">
                                <img id="photoProfilDis" src="" alt="Profile Picture">
                                <span class="tooltip">Choose Picture</span>
                            </label>
                        </div>
                        <div class="utilisateur-username">
                            <h1 id="firstNameDis"></h1>
                            <p id="usernameDis"></p>
                            <p class="text-bio" id="bioDis"></p>
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
                <form id="security-form" method="post">
                    <div class="utilisateur-header">
                        <div class="photo-profil">
                            <input type="file" id="photo-profil-up" name="profilPic">
                            <label for="photo-profil-up">
                                <img src="images/Default-profile-pic.png" alt="Profile Picture">
                                <span class="tooltip">Choose Picture</span>
                            </label>
                        </div>
                        <div class="utilisateur-username">
                            <h1 id="firstNameDis"></h1>
                            <p id="usernameDis"></p>
                            <p class="text-bio" id="bioDis"></p>
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
                <form id="languages-form" method="post">
                    <div class="utilisateur-header">
                        <div class="photo-profil">
                            <input type="file" id="photo-profil-up" name="profilPic">
                            <label for="photo-profil-up">
                                <img src="images/Default-profile-pic.png" alt="Profile Picture">
                                <span class="tooltip">Choose Picture</span>
                            </label>
                        </div>
                        <div class="utilisateur-username">
                            <h1 id="firstNameDis"></h1>
                            <p id="usernameDis"></p>
                            <p class="text-bio" id="bioDis"></p>
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
                updateProfileInfo();
            });
            function updateProfileInfo() {
                var firstNameDis = "${sessionScope.firstName}";
                var lastNameDis = "${sessionScope.lastName}";
                var usernameDis = "${sessionScope.username}";
                var bioDis = "${sessionScope.bio}";

                document.getElementById('firstNameDis').textContent = firstNameDis + " " + lastNameDis;
                document.getElementById('usernameDis').textContent = "@" + usernameDis;
                document.getElementById('bioDis').textContent = bioDis;
            }

            function stretchImage() {
                var container = document.querySelector('.photo-profil');
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
            

            var fileInput = document.getElementById('photo-profil-up');
            var image = document.querySelector('.photo-profil img');

            fileInput.addEventListener('change', function(event) {
                var file = event.target.files[0];

                if (file) {
                    var reader = new FileReader();

                    reader.onload = function() {
                        var tempImage = new Image();

                        tempImage.src = reader.result;

                        tempImage.onload = function() {
                            if (tempImage.width === tempImage.height) {
                                image.src = reader.result;
                                stretchImage();
                            } else {
                                alert('Please select a square image.\n\A minimum of 150px by 150px is recommended');
                                event.target.value = '';
                            }
                        };
                    };

                    reader.readAsDataURL(file);
                    event.target.value = '';
                    event.target.form.reset();
                }
            });
        </script>
    </body>
</html>
