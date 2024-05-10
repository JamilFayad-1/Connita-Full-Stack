const ItalianPlayground = [
    {
        question: "What does 'ciao' mean?",
        answers: [
            {text: "Goodbye", correct: false},
            {text: "Hello", correct: true},
            {text: "Thank you", correct: false},
            {text: "Excuse me", correct: false}
        ]
    },
    {
        question: "How do you say 'thank you' in Italian?",
        answers: [
            {text: "Ciao", correct: false},
            {text: "Grazie", correct: true},
            {text: "Per favore", correct: false},
            {text: "Scusa", correct: false}
        ]
    },
    {
        question: "What is the color 'rosso' in English?",
        answers: [
            {text: "Blue", correct: false},
            {text: "Red", correct: true},
            {text: "Yellow", correct: false},
            {text: "Green", correct: false}
        ]
    },
    {
        question: "How do you say 'dog' in Italian?",
        answers: [
            {text: "Gatto", correct: false},
            {text: "Cane", correct: true},
            {text: "Pesce", correct: false},
            {text: "Uccello", correct: false}
        ]
    }
];

const FrenchPlayground = [
    {
        question: "Comment t'appelles-tu ?",
        answers: [
            {text: "Je m'appelle...", correct: true},
            {text: "Comment ça va ?", correct: false},
            {text: "Où habites-tu ?", correct: false},
            {text: "Quel âge as-tu ?", correct: false}
        ]
    },
    {
        question: "Où habites-tu ?",
        answers: [
            {text: "Je viens de...", correct: false},
            {text: "J'habite à...", correct: true},
            {text: "Je suis...", correct: false},
            {text: "Comment ça va ?", correct: false}
        ]
    },
    {
        question: "Quel âge as-tu ?",
        answers: [
            {text: "Je suis...", correct: false},
            {text: "J'ai... ans.", correct: true},
            {text: "Comment tu t'appelles ?", correct: false},
            {text: "Où habites-tu ?", correct: false}
        ]
    },
    {
        question: "Comment ça va ?",
        answers: [
            {text: "Bien, merci.", correct: true},
            {text: "Je m'appelle...", correct: false},
            {text: "Quel âge as-tu ?", correct: false},
            {text: "Où habites-tu ?", correct: false}
        ]
    }
];

const SpanishPlayground = [
    {
        question: "¿Cómo te llamas?",
        answers: [
            {text: "Me llamo...", correct: true},
            {text: "¿Qué tal?", correct: false},
            {text: "¿Dónde vives?", correct: false},
            {text: "¿Cuántos años tienes?", correct: false}
        ]
    },
    {
        question: "¿Dónde vives?",
        answers: [
            {text: "Soy de...", correct: false},
            {text: "Vivo en...", correct: true},
            {text: "Estoy...", correct: false},
            {text: "¿Qué tal?", correct: false}
        ]
    },
    {
        question: "¿Cuántos años tienes?",
        answers: [
            {text: "Soy...", correct: false},
            {text: "Tengo... años.", correct: true},
            {text: "¿Cómo te llamas?", correct: false},
            {text: "¿Dónde vives?", correct: false}
        ]
    },
    {
        question: "¿Qué tal?",
        answers: [
            {text: "Bien, gracias.", correct: true},
            {text: "Me llamo...", correct: false},
            {text: "¿Cuántos años tienes?", correct: false},
            {text: "¿Dónde vives?", correct: false}
        ]
    }
];

const JapanesePlayground = [
    {
        question: "あなたの名前は何ですか？",
        answers: [
            {text: "私の名前は...", correct: true},
            {text: "元気ですか？", correct: false},
            {text: "どこに住んでいますか？", correct: false},
            {text: "年をとりましたか？", correct: false}
        ]
    },
    {
        question: "どこに住んでいますか？",
        answers: [
            {text: "私は...から来ました。", correct: false},
            {text: "私は...に住んでいます。", correct: true},
            {text: "私は...です。", correct: false},
            {text: "元気ですか？", correct: false}
        ]
    },
    {
        question: "年をとりましたか？",
        answers: [
            {text: "私は...です。", correct: false},
            {text: "...歳です。", correct: true},
            {text: "あなたの名前は何ですか？", correct: false},
            {text: "どこに住んでいますか？", correct: false}
        ]
    },
    {
        question: "元気ですか？",
        answers: [
            {text: "はい、ありがとう。", correct: true},
            {text: "私の名前は...です。", correct: false},
            {text: "年をとりましたか？", correct: false},
            {text: "どこに住んでいますか？", correct: false}
        ]
    }
];

const imagesLanguages = document.querySelectorAll('#language-options-playground img');
const quizName = document.getElementById('quiz-name');

const questionElement = document.getElementById("question");
const answerButtons = document.getElementById("answer-buttons");
const nextButton = document.getElementById("btnNext");

let currentQuestionIndex = 0;
let score = 0;
let currentQuestions = [];
let challengeName;

imagesLanguages.forEach(imageLanguage => {
    imageLanguage.addEventListener('click', function () {
        const language = imageLanguage.alt;
        quizName.textContent = language;
        let questions;
        switch (language) {
            case "French":
                questions = FrenchPlayground;
                break;
            case "Italian":
                questions = ItalianPlayground;
                break;
            case "Spanish":
                questions = SpanishPlayground;
                break;
            case "Japanese":
                questions = JapanesePlayground;
                break;
            default:
                console.error("Invalid challenge name:", challengeName);
                return;
        }
        loadChallengeSet(questions);

        const challengeContainer = document.querySelector('.challenge-Wrapper-playground');
        gsap.to(challengeContainer, {
            scrub: 1,
            duration:0.5,
            ease: "power3.in",
            height: "55%"
        });

        const playgroundAnimations = document.querySelector('.animPlayground');
        playgroundAnimations.forEach(playgroundAnimation => {
            gsap.from(playgroundAnimation, {
                y: 30,
                opacity: 0,
                ease: "ease-in",
                stagger: 0.1,
                duration: 0.2
            });
        })
    });
});

function loadChallengeSet(questions) {
    challengeName = questions;
    currentQuestions = questions;
    currentQuestionIndex = 0;
    score = 0;
    nextButton.innerHtml = "Next";
    showQuestion();
}

function showQuestion() {
    resetState();
    let currentQuestion = currentQuestions[currentQuestionIndex];
    let questionNbr = currentQuestionIndex + 1;
    questionElement.innerHTML = questionNbr + ". " + currentQuestion.question;

    const timeline = gsap.timeline({ defaults: { duration: 0.15, ease: "power1.inOut" } });

    currentQuestion.answers.forEach((answer, index) => {
        const button = document.createElement("button");
        button.innerHTML = answer.text;
        button.classList.add("btnAnswer");

        timeline.from(button, {
            y: 80,
            opacity: 0,
        }, index * 0.15);

        answerButtons.appendChild(button);
        if (answer.correct) {
            button.dataset.correct = answer.correct;
        }
        button.addEventListener("click", selectAnswer);
    });
}


function resetState() {
    nextButton.style.display = "none";
    nextButton.innerHTML = "Next";
    while (answerButtons.firstChild) {
        answerButtons.removeChild(answerButtons.firstChild);
    }

    const challengeContainer = document.querySelector('.challenge-Wrapper-playground');
    gsap.to(challengeContainer, {
        scrub: 1,
        duration:0.5,
        ease: "power3.in",
        height: "55%"
    });
}

function selectAnswer(e) {
    const selectedBtn = e.target;
    const isCorrect = selectedBtn.dataset.correct === "true";
    if (isCorrect) {
        selectedBtn.classList.add("correct");
        score++;
    } else {
        selectedBtn.classList.add("incorrect");
    }
    Array.from(answerButtons.children).forEach(button => {
        if (button.dataset.correct === "true") {
            button.classList.add("correct");
        }
        button.disabled = true;
    });
    nextButton.style.display = "block";
}

function showScore() {
    resetState();
    if (score === currentQuestions.length) {
        questionElement.innerHTML = `You scored ${score} out of 4!`;
    } else {
        questionElement.innerHTML = `You scored ${score} out of 4!`;
    }

    const challengeContainer = document.querySelector('.challenge-Wrapper-playground');
    gsap.to(challengeContainer, {
        scrub: 1,
        duration:1,
        ease: "bounce.out",
        height: "20%"
    });

    nextButton.innerHTML = "Restart the challenge";
    nextButton.style.display = "block";
}

function handleNextButton() {
    currentQuestionIndex++;
    if (currentQuestionIndex < currentQuestions.length) {
        showQuestion();
    } else {
        showScore();
    }
}

nextButton.addEventListener("click", () => {
    if (currentQuestionIndex < ItalianPlayground.length) {
        handleNextButton();
    } else {
        loadChallengeSet(currentQuestions);
    }
});