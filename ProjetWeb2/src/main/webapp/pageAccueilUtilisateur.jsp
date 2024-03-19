<%-- 
    Document   : pageAccueilUtilisateur
    Created on : Mar. 17, 2024, 5:42:47 p.m.
    Author     : Gwuliano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Page d'accueil</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/style.css"/>
    </head>
    <body>

        <header>
            <div id="logo-container">
                <a>
                    <img src="images/My first design.png" alt="Company logo"/>
                </a>
            </div>
            <nav id="horizontal-nav">
                <ul>
                    <li><a href=""><img src="https://img.icons8.com/ios-glyphs/30/add-user-group-woman-man.png" alt="add-user-group-woman-man"/></a></li>
                    <li><a href=""><img src="https://img.icons8.com/ios-glyphs/30/speech-bubble-with-dots.png" alt="speech-bubble-with-dots"/></a></li>
                    <li><a href=""><img src="https://img.icons8.com/ios-glyphs/30/filled-appointment-reminders.png" alt="filled-appointment-reminders"/></a></li>
                    <li><a href=""><img src="https://img.icons8.com/ios-glyphs/30/test-account.png" alt="test-account"/></a></li>
                </ul>  
            </nav>
        </header>
        
        <main>       
            <!-- Left section -->
            <div id="left-section">
                <div id="left-section-nav-bar">
                        <ul>
                            <li><a href="#">Feed</a></li>
                            <li><a href="#">Learning mates</a></li>
                            <li><a href="#">Languages</a></li>
                            <li><a href="#">Countries</a></li>
                            <li><a href="#">Travel</a></li>
                            <li><a href="#">Challenges</a></li>
                        </ul>
                </div>
                <hr>
                <div id="left-section-favorites">
                    <h2>Favorites</h2>
                    <nav id="vertical-nav-favorites">
                        <p>Languages</p>
                        <ul>
                            <li>
                                <a href="#">
                                    <div class="horizontal-tab-container">
                                        <img src="https://img.icons8.com/dusk/64/italy.png" alt="italy"/>
                                        <h3>Italian</h3>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <div class="horizontal-tab-container">
                                        <img src="https://img.icons8.com/dusk/64/spain.png" alt="spain"/>
                                        <h3>Spanish</h3>
                                    </div>
                                </a>
                            </li>
                        </ul>
                        <p>Countries</p>
                        <ul>
                            <li>
                                <a href="#">
                                    <div class="horizontal-tab-container">
                                        <img src="https://img.icons8.com/dusk/64/japan.png" alt="japan"/>
                                        <h3>Japan</h3>
                                    </div>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>

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
                    <div id="recent-post-container">
                        <div>

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
            <div id="right-section">
                <div id="right-section-friends-list">
                    <h2>Friends</h2>
                    <ul id="friends-list">
                        <li>
                            <a href="">
                                <div class="horizontal-tab-container-friends">
                                    <img src="https://img.icons8.com/ios-glyphs/30/test-account.png" alt="test-account"/>
                                    <h3>Jason</h3>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="">
                                <div class="horizontal-tab-container-friends">
                                    <img src="https://img.icons8.com/ios-glyphs/30/test-account.png" alt="test-account"/>
                                    <h3>Alex</h3>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="">
                                <div class="horizontal-tab-container-friends">
                                    <img src="https://img.icons8.com/ios-glyphs/30/test-account.png" alt="test-account"/>
                                    <h3>Sofia</h3>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="">
                                <div class="horizontal-tab-container-friends">
                                    <img src="https://img.icons8.com/ios-glyphs/30/test-account.png" alt="test-account"/>
                                    <h3>Matthew</h3>
                                </div>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>           
        </main>
        
    </body>
</html>
