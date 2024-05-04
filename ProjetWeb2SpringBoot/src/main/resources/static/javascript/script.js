const btnStartLearning = document.querySelectorAll(".btnSignup");
const mainBox = document.querySelector(".main-box");
const closeForm = document.querySelectorAll(".close-icon-container");

// Function to open the main box
function openMainBox() {
    mainBox.classList.add("main-box-visible");
}

// Function to close the main box
function closeMainBox() {
    mainBox.classList.remove("main-box-visible");
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

});
