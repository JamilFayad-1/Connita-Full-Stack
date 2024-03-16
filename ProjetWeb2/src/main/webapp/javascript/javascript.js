document.addEventListener('DOMContentLoaded', function () {
    var loginFormDisplay = document.getElementById('logInForm');
    var btnLogin = document.getElementById('btnLogin');
    var closeLoginForm = document.getElementById('closeLoginForm');
    
    btnLogin.addEventListener('click', function () {
        loginFormDisplay.style.display = 'flex';
        setTimeout(function () {
            loginFormDisplay.classList.add('active-form');
        }, 10);
    });

    closeLoginForm.addEventListener('click', function () {
        event.preventDefault();
        
        loginFormDisplay.classList.remove('active-form');

        setTimeout(function () {
            loginFormDisplay.style.display = 'none';
        }, 1500);
    });
});
