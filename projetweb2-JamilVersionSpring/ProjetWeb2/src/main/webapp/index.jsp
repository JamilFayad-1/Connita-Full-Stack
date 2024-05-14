<%-- 
    Document   : index
    Created on : Mar. 17, 2024, 5:26:46 p.m.
    Author     : Gwuliano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
   String messageInscrReussite = (String)request.getAttribute("messageInscrReussite");
   String messageInscrEchoue = (String)request.getAttribute("messageInscrEchoue");
   String messageConnEchoue = (String)request.getAttribute("messageConnEchoue");
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Page d'accueil</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>

        <jsp:include page="menu.jsp"/>
        
        <div class="messagePopUps">
            <% if(request.getAttribute("messageInscrReussite")!=null){ %>
                <p class="messageTextReussie"><%= messageInscrReussite%></p>
            <%} else if(request.getAttribute("messageInscrEchoue")!=null) {%>
                <p class="messageTextEchoue"><%= messageInscrEchoue%></p>
            <%} else if(request.getAttribute("messageConnEchoue") != null) {%>
                <p class="messageTextEchoue"><%= messageConnEchoue%></p>
            <%}%>
        </div>
      
        <main class="main-box">
            <div class="box">
                <div class="close-form-join">
                    <div class="close-icon-container"></div>
                </div>
                <div class="inner-box">
                    <div class="forms-wrap">
                        <form action="ConnexionController" autocomplete="off" class="sign-in-form" method="post">
                            <div class="logo">
                                <img src="images/My first design.png" alt="connita" />
                                <h4>Connita</h4>
                            </div>

                            <div class="heading">
                                <h2>Welcome Back</h2>
                                <h6>Not registred yet?</h6>
                                <a href="#" class="toggle">Sign up</a>
                            </div>

                            <div class="actual-form">
                                <div class="input-wrap">
                                    <input
                                        type="text"
                                        name="email"
                                        minlength="4"
                                        class="input-field"
                                        autocomplete="off"
                                        required
                                        />
                                    <label>Email</label>
                                </div>

                                <div class="input-wrap">
                                    <input
                                        type="password"
                                        name="password"
                                        minlength="4"
                                        class="input-field"
                                        autocomplete="off"
                                        required
                                        />
                                    <label>Password</label>
                                </div>

                                <input type="submit" value="Sign In" class="sign-btn" />

                                <p class="text-form-footer">
                                    Forgotten your password or you login datails?
                                    <a href="#">Get help</a> signing in
                                </p>
                            </div>
                        </form>
                        <form action="InscriptionController" autocomplete="off" class="sign-up-form" method="post">
                            <div class="logo">
                                <img src="images/My first design.png" alt="Connita" />
                                <h4>Connita</h4>
                            </div>

                            <div class="heading">
                                <h2>Get Started</h2>
                                <h6>Already have an account?</h6>
                                <a href="#" class="toggle">Sign in</a>
                            </div>

                            <div class="actual-form">
                                <div class="input-wrap">
                                    <input
                                        type="text"
                                        name="firstName"
                                        minlength="4"
                                        class="input-field"
                                        autocomplete="off"
                                        required
                                        />
                                    <label>First name</label>
                                </div>
                                
                                <div class="input-wrap">
                                    <input
                                        type="text"
                                        name="lastName"
                                        minlength="4"
                                        class="input-field"
                                        autocomplete="off"
                                        required
                                        />
                                    <label>Last name</label>
                                </div>

                                <div class="input-wrap">
                                    <input
                                        type="email"
                                        name="email"
                                        class="input-field"
                                        autocomplete="off"
                                        required
                                        />
                                    <label>Email</label>
                                </div>

                                <div class="input-wrap">
                                    <input
                                        type="password"
                                        name="password"
                                        minlength="4"
                                        class="input-field"
                                        autocomplete="off"
                                        required
                                        />
                                    <label>Password</label>
                                </div>

                                <input type="submit" value="Sign Up" class="sign-btn" />

                                <p class="text-form-footer">
                                    By signing up, I agree to the
                                    <a href="#">Terms of Services</a> and
                                    <a href="#">Privacy Policy</a>
                                </p>
                            </div>
                        </form>
                    </div>

                    <div class="carousel">
                        <div class="images-wrapper">
                            <img src="images/image1Carousel.png" class="image img-1 show" alt="" />
                            <img src="images/image2Carousel.png" class="image img-2" alt="" />
                            <img src="images/image3Carousel.png" class="image img-3" alt="" />
                        </div>

                        <div class="text-slider">
                            <div class="text-wrap">
                                <div class="text-group">
                                    <h2>Learning is meant to be fun</h2>
                                    <h2>Discover new languages and cultures!</h2>
                                    <h2>Interact with learners worldwide!</h2>
                                </div>
                            </div>

                            <div class="bullets">
                                <span class="active" data-value="1"></span>
                                <span data-value="2"></span>
                                <span data-value="3"></span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        
        <main>       
            <!-- Left section -->
            <jsp:include page="navbarGauche.jsp"/>
            <div id="main-section-home-page">
                <h1>Learning made fun!</h1>
                <div id="main-section-first-info">
                    <img id="first-image-animate" src="images/web-3120321_1920.png" alt="alt"/>
                    <h2>Connect with experts and learners worldwide to enhance your language and cultural understanding!</h2>
                </div>
                <div id="bubble-thoughts1">
                    <img class="footprints" id="circle1" src="images/footprint-left.png" alt="alt"/>
                    <img class="footprints" id="circle2" src="images/footprint-right.png" alt="alt"/>
                    <img class="footprints" id="circle3" src="images/footprint-left.png" alt="alt"/>
                    <img class="footprints" id="circle4" src="images/footprint-right.png" alt="alt"/>
                    <img class="footprints" id="circle5" src="images/footprint-left.png" alt="alt"/>
                    <img class="footprints" id="circle6" src="images/footprint-right.png" alt="alt"/>
                </div>
                <div id="main-section-second-info">
                    <img src="images/icons8-plane-64.png" alt="alt"/>
                    <h2>Discover new places straight from the locals!</h2>
                </div>
                <div id="bubble-thoughts2">
                    <img id="circle7" src="images/footprint-right.png" alt="alt"/>
                    <img id="circle8" src="images/footprint-left.png" alt="alt"/>
                    <img id="circle9" src="images/footprint-right.png" alt="alt"/>
                    <img id="circle10" src="images/footprint-left.png" alt="alt"/>
                    <img id="circle11" src="images/footprint-right.png" alt="alt"/>
                    <img id="circle12" src="images/footprint-left.png" alt="alt"/>
                </div>
                <div id="main-section-third-info">
                    <h2>Open your eyes to the world and learn about different cultures!</h2>
                    <img src="images/kP4DK6APAlkuNxqzn3HzJ-transformed-min.png" alt="alt"/>
                </div>
                
            </div>
        </main>
        <script src="javascript/javascript.js"></script>
    </body>
</html>
