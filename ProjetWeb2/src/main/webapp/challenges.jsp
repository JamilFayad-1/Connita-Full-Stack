<%-- 
    Document   : challenges
    Created on : Mar. 18, 2024, 7:31:45 p.m.
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
        <title>Challenges</title>
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
                <div id="middle-section-challenges-layout">
                    <h1>Challenges</h1>
                    <div id="middle-section-search-bar">
                        <form class="formSearchBar" action="/search" method="POST">
                            <div>
                                <input type="text" id="searchQuery" name="challengeName" placeholder="Find a specific challenge" onkeydown="handleKeyPress(event)" required>
                                <img src="https://img.icons8.com/pastel-glyph/64/search--v1.png" alt="search--v1"/>
                            </div>
                        </form>
                    </div>
                    <div id="middle-section-challenges-container">
                        <div id="middle-section-challenges-element">
                            <div class="circle-container">
                                <div class="fraction">3/7</div>
                            </div>
                            <div id="subject-challenge-container">
                                <div id="subject-challenge">
                                    <img src="https://img.icons8.com/dusk/64/italy.png" alt="italy"/>
                                    <h3>Italian</h3>
                                </div>
                                <p>Italian Renaissance: Advanced Language and Cultural Immersion!</p>
                            </div>
                            <div id="challenge-complete">
                                <h3>Complete</h3>
                            </div>
                        </div>
                        <div id="middle-section-challenges-element">
                            <div class="circle-container">
                                <div class="fraction">5/7</div>
                            </div>
                            <div id="subject-challenge-container">
                                <div id="subject-challenge">
                                    <img src="https://img.icons8.com/dusk/64/spain.png" alt="spain"/>
                                    <h3>Spanish</h3>
                                </div>
                                <p>Spanish Mastery: Exploring Advanced Language Proficiency!</p>
                            </div>
                            <div id="challenge-complete">
                                <h3>Complete</h3>
                            </div>
                        </div>
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
        
        <script src="javascript.js" defer></script>
    </body>
</html>
