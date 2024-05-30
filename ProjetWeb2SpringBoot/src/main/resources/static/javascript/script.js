const btnStartLearning = document.querySelectorAll(".btnSignup");
const mainBox = document.querySelector(".main-box");
const closeForm = document.querySelectorAll(".close-icon-container");

function openMainBox() {
    mainBox.classList.add("main-box-visible");
    centerForm();
}

function closeMainBox() {
    mainBox.classList.remove("main-box-visible");
}

function centerForm() {
    let scrollTop = window.scrollY;
    scrollTop = scrollTop / 10000;
    mainBox.style.top = scrollTop + "px";
}

btnStartLearning.forEach((element) => {
    element.addEventListener("click", openMainBox);
});

closeForm.forEach((element) => {
    element.addEventListener("click", closeMainBox);
});

const inputs = document.querySelectorAll(".input-field");
const toggle_btn = document.querySelectorAll(".toggle");
const main = document.querySelector("main");
const bullets = document.querySelectorAll(".bullets span");
const images = document.querySelectorAll(".image");

if(inputs){
    inputs.forEach((inp) => {
        inp.addEventListener("focus", () => {
            inp.classList.add("active");
        });
        inp.addEventListener("blur", () => {
            if (inp.value != "") return;
            inp.classList.remove("active");
        });
    });
}

if(toggle_btn){
    toggle_btn.forEach((btn) => {
        btn.addEventListener("click", () => {
            main.classList.toggle("sign-up-mode");
        });
    });
}


function moveSlider(index) {
    let currentImage = document.querySelector(`.img-${index}`);
    images.forEach((img) => img.classList.remove("show"));
    currentImage.classList.add("show");

    const textSlider = document.querySelector(".text-group");
    textSlider.style.transform = `translateY(${-(index - 1) * 3.6}rem)`;

    bullets.forEach((bull) => bull.classList.remove("active"));
    bullets[index - 1].classList.add("active");
}

function handleBulletClick() {
    bullets.forEach((bullet, index) => {
        bullet.addEventListener("click", () => moveSlider(index + 1));
    });
}

function startSliderAutoChange() {
    let currentIndex = 0;
    let interval;

    function startInterval() {
        interval = setInterval(() => {
            currentIndex = (currentIndex + 1) % bullets.length;
            moveSlider(currentIndex + 1);
        }, 3000);
    }

    function stopInterval() {
        clearInterval(interval);
    }

    const carousel = document.querySelector(".carousel");
    if (carousel) {
        carousel.addEventListener("mouseleave", startInterval);
        carousel.addEventListener("mouseenter", stopInterval);
    }

    startInterval();
}

document.addEventListener("DOMContentLoaded", () => {
    handleBulletClick();
    startSliderAutoChange();

    // Validation du formulaire d'inscription
    const registrationForm = document.getElementById('registrationForm');
    const firstName = document.getElementById('firstName');
    const lastName = document.getElementById('lastName');
    const email = document.getElementById('email');
    const password = document.getElementById('password');
    const firstNameLabel = document.getElementById('firstNameLabel');
    const lastNameLabel = document.getElementById('lastNameLabel');
    const emailLabel = document.getElementById('emailLabel');
    const passwordLabel = document.getElementById('passwordLabel');
    const passwordRequirements = document.getElementById('passwordRequirements');
    const firstRequirements = document.getElementById('firstRequirement');
    const secondRequirements = document.getElementById('secondRequirement');
    const thirdRequirements = document.getElementById('thirdRequirement');

    firstName.addEventListener('focus', () => clearCustomValidity(firstName));
    lastName.addEventListener('focus', () => clearCustomValidity(lastName));
    email.addEventListener('focus', () => clearCustomValidity(email));
    password.addEventListener('focus', () => clearCustomValidity(password));

    password.addEventListener('input', () => {
        const hasNumber = /\d/;
        const hasSymbol = /[!@#$%^&*(),.?":{}|<>]/;

        firstRequirements.style.color = password.value.length >= 8 ? "rgba(71, 206, 3, 0.7)" : "#d0cece";
        secondRequirements.style.color = hasNumber.test(password.value) ? "rgba(71, 206, 3, 0.7)" : "#d0cece";
        thirdRequirements.style.color = hasSymbol.test(password.value) ? "rgba(71, 206, 3, 0.7)" : "#d0cece";
    });

    registrationForm.addEventListener('submit', function (event) {
        event.preventDefault(); // Prevent form submission to handle validation
        const firstNameValidation = validateName(firstName, firstNameLabel);
        const lastNameValidation = validateName(lastName, lastNameLabel);
        const emailValidation = validateEmail(email, emailLabel);
        const passwordValidation = validatePassword(password, passwordLabel, passwordRequirements);

        if (firstNameValidation && lastNameValidation && emailValidation && passwordValidation) {
            // If all validations pass, submit the form
            registrationForm.submit();
        }
        if (!passwordValidation) {
            passwordRequirements.style.display = "block";
            const lastInput = document.getElementById('lastInputWrap');
            lastInput.style.marginBottom = "0.2rem";
        }
    });

    const passwordToggleBtn = document.querySelectorAll('.password-toggle');
    const passwordInputs = document.querySelectorAll('.input-password');
    let isVisible = false;
    passwordToggleBtn.forEach((toggleBtn, index) => {
        toggleBtn.addEventListener('click', function (event) {
            const input = passwordInputs[index];
            const isVisible = input.getAttribute('data-visible') === 'true';

            if (!isVisible) {
                input.setAttribute('type', 'text');
                input.setAttribute('data-visible', 'true');
                toggleBtn.classList.remove('fa-eye-slash');
                toggleBtn.classList.add('fa-eye');
            } else {
                input.setAttribute('type', 'password');
                input.setAttribute('data-visible', 'false');
                toggleBtn.classList.remove('fa-eye');
                toggleBtn.classList.add('fa-eye-slash');
            }
        })
    });

});

function validateName(input, label) {
    const name = input.value;
    const nameRegex = /^[A-Za-z '-]+$/;
    const isValid = nameRegex.test(name);

    if (!isValid) {
        input.setCustomValidity("Invalid name format.");
    } else {
        input.setCustomValidity("");
    }

    toggleValidation(input, label, isValid);
    return isValid;
}

function validateEmail(input, label) {
    const email = input.value;
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const isValid = emailRegex.test(email);

    if (!isValid) {
        input.setCustomValidity("Invalid email format.");
    } else {
        input.setCustomValidity("");
    }

    toggleValidation(input, label, isValid);
    return isValid;
}

function validatePassword(input, label, passwordRequirements) {
    const password = input.value;
    const hasNumber = /\d/;
    const hasSymbol = /[!@#$%^&*(),.?":{}|<>]/;
    const isValid = password.length >= 8 && hasNumber.test(password) && hasSymbol.test(password);

    if (!isValid) {
        input.setCustomValidity("Password must be at least 8 characters long, contain a number, and a symbol.");
    } else {
        input.setCustomValidity("");
    }

    toggleValidation(input, label, isValid);
    return isValid;
}

function toggleValidation(input, label, isValid) {
    if (isValid) {
        input.classList.remove('input-field-error');
        input.classList.add('input-field-valid');
        label.classList.remove('label-error');
        label.classList.add('label-valid');
    } else {
        input.classList.remove('input-field-valid');
        input.classList.add('input-field-error');
        label.classList.remove('label-valid');
        label.classList.add('label-error');
    }
}

function clearCustomValidity(input) {
    input.setCustomValidity("");
    input.classList.remove('input-field-error');
    input.classList.remove('input-field-valid');
}




