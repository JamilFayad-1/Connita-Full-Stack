const btnStartLearning = document.querySelectorAll(".btnSignup");
const mainBox = document.querySelector(".main-box");
const closeForm = document.querySelectorAll(".close-icon-container");

// Function to open the main box
function openMainBox() {
    mainBox.classList.add("main-box-visible");
    centerForm();
}

// Function to close the main box
function closeMainBox() {
    mainBox.classList.remove("main-box-visible");
}

function centerForm() {
    let scrollTop = window.scrollY;
    scrollTop = scrollTop / 10000;
    mainBox.style.top = scrollTop + "px";
}

// Attach event listener to each "Start Learning" button
btnStartLearning.forEach((element) => {
    element.addEventListener("click", openMainBox);
});

// Attach event listener to each close button
closeForm.forEach((element) => {
    element.addEventListener("click", closeMainBox);
});

const inputs = document.querySelectorAll(".input-field");
const toggle_btn = document.querySelectorAll(".toggle");
const main = document.querySelector("main");
const bullets = document.querySelectorAll(".bullets span");
const images = document.querySelectorAll(".image");

inputs.forEach((inp) => {
    inp.addEventListener("focus", () => {
        inp.classList.add("active");
    });
    inp.addEventListener("blur", () => {
        if (inp.value != "") return;
        inp.classList.remove("active");
    });
});

toggle_btn.forEach((btn) => {
    btn.addEventListener("click", () => {
        main.classList.toggle("sign-up-mode");
    });
});

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
    carousel.addEventListener("mouseleave", startInterval);
    carousel.addEventListener("mouseenter", stopInterval);

    // Start interval initially
    startInterval();
}

// Call the functions to initialize event listeners and auto change
document.addEventListener("DOMContentLoaded", () => {
    handleBulletClick();
    startSliderAutoChange();

    //Validation du formulaire d'inscription
    const firstName = document.getElementById('firstName');
    const lastName = document.getElementById('lastName');
    const email = document.getElementById('email');
    const password = document.getElementById('password');

    password.addEventListener('blur', () => {
        validatePassword(password);
    });

    password.addEventListener('focus', () => {
        clearValidation(password);
    });
});

function validatePassword(input) {
    const password = input.value;

    const hasNumber = /\d/;
    const hasSymbol = /[!@#$%^&*(),.?":{}|<>]/;

    const isValid = password.length >= 8 && hasNumber.test(password) && hasSymbol.test(password);

    if (isValid) {
        input.classList.remove('input-field-error');
        input.classList.add('input-field-valid');
    } else {
        input.classList.remove('input-field-valid');
        input.classList.add('input-field-error');
    }
}

function clearValidation(input) {
    input.classList.remove('input-field-valid');
    input.classList.remove('input-field-error');
}

// The password validation works now. Need to add the effect to the label too and the info that says that you need 8 letters a number and a symbol. The rest of the inputs are not done.



