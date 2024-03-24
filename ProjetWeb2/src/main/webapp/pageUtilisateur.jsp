<%-- 
    Document   : pageUtilisateur
    Created on : Mar. 19, 2024, 1:34:08 a.m.
    Author     : Gwuliano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <title>Réglage de compte</title>
    </head>
    <body>
        <jsp:include page="menu.jsp"/>
        <jsp:include page="navbarGauche.jsp"/>
        <div class="utilisateur-reglage-wrapper">
            <div class="utilisateur-reglage-container">
                <form method="post">
                    <div class="utilisateur-header">
                        <div class="photo-profil">
                            <input type="file" id="photo-profil-up">
                            <label for="photo-profil-up">
                                <img src="images/Default-profile-pic.png" alt="Profile Picture">
                                <span class="tooltip">Choose Picture</span>
                            </label>
                        </div>
                        <div class="utilisateur-username">
                            <h1>Jamil Fayad</h1>
                            <p>@Gwuliano</p>
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
                <div class="utilisateur-footer">
                    
                </div>
            </div>
        </div>
        <script>
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

            function stretchImage() {
                var container = document.querySelector('.photo-profil');

                var containerWidth = container.offsetWidth;
                var containerHeight = container.offsetHeight;

                var minDimension = Math.min(containerWidth, containerHeight);

                image.style.width = minDimension + 'px';
                image.style.height = minDimension + 'px';
            }
        </script>
    </body>
</html>
