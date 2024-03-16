document.addEventListener('DOMContentLoaded', function () {
    var loginFormDisplay = document.getElementById('logInForm');
    var btnLogin = document.getElementsByClassName('btnLogin');
    var closeLoginForm = document.getElementById('closeLoginForm');
    var signupFormDisplay = document.getElementById('signUpForm');
    var btnSignUp = document.getElementsByClassName('btnSignup');
    var closeSignupForm = document.getElementById('closeSignupForm');
    
    function handleLoginClick() {
        signupFormDisplay.style.display = 'none';
        loginFormDisplay.style.display = 'flex';
        setTimeout(function () {
            loginFormDisplay.classList.add('active-form');
        }, 10);
    }
    
    function handleSignupClick() {
        loginFormDisplay.style.display = 'none';
        signupFormDisplay.style.display = 'flex';
        setTimeout(function () {
            signupFormDisplay.classList.add('active-form');
        }, 10);
    }
    
    btnLogin[0].addEventListener('click', handleLoginClick);
    btnLogin[1].addEventListener('click', handleLoginClick);
    
    btnSignUp[0].addEventListener('click', handleSignupClick);
    btnSignUp[1].addEventListener('click', handleSignupClick);
    
    

    closeLoginForm.addEventListener('click', function () {
        event.preventDefault();
        
        loginFormDisplay.classList.remove('active-form');

        setTimeout(function () {
            loginFormDisplay.style.display = 'none';
        }, 1500);
    });
    
    closeSignupForm.addEventListener('click', function () {
        event.preventDefault();
        
        signupFormDisplay.classList.remove('active-form');

        setTimeout(function () {
            signupFormDisplay.style.display = 'none';
        }, 1500);
    });
    
});
