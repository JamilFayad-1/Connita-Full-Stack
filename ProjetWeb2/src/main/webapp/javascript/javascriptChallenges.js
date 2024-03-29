const questions1 = [
    {
        question: "Cosa significa 'ciao' in inglese?",
        answers: [
            {text: "Goodbye", correct: false},
            {text: "Hello", correct: true},
            {text: "Thank you", correct: false},
            {text: "Excuse me", correct: false}
        ]
    },
    {
        question: "Qual è il colore del sole?",
        answers: [
            {text: "Blu", correct: false},
            {text: "Rosso", correct: false},
            {text: "Giallo", correct: true},
            {text: "Verde", correct: false}
        ]
    },
    {
        question: "Quale animale fa 'bau'?",
        answers: [
            {text: "Gatto", correct: false},
            {text: "Cane", correct: true},
            {text: "Pesce", correct: false},
            {text: "Uccello", correct: false}
        ]
    },
    {
        question: "Come si dice 'grazie' in inglese?",
        answers: [
            {text: "Hello", correct: false},
            {text: "Goodbye", correct: false},
            {text: "Please", correct: false},
            {text: "Thank you", correct: true}
        ]
    }
];

const questions2 = [
    {
        question: "Qual è la capitale dell'Italia?",
        answers: [
            {text: "Milano", correct: false},
            {text: "Firenze", correct: false},
            {text: "Roma", correct: true},
            {text: "Napoli", correct: false}
        ]
    },
    {
        question: "Chi dipinse la 'Gioconda'?",
        answers: [
            {text: "Michelangelo", correct: false},
            {text: "Leonardo da Vinci", correct: true},
            {text: "Raffaello Sanzio", correct: false},
            {text: "Donatello", correct: false}
        ]
    },
    {
        question: "Qual è il fiume più lungo del mondo?",
        answers: [
            {text: "Nilo", correct: true},
            {text: "Mississippi", correct: false},
            {text: "Amazonas", correct: false},
            {text: "Danubio", correct: false}
        ]
    },
    {
        question: "Chi scrisse 'La Divina Commedia'?",
        answers: [
            {text: "Petrarca", correct: false},
            {text: "Boccaccio", correct: false},
            {text: "Dante Alighieri", correct: true},
            {text: "Machiavelli", correct: false}
        ]
    }
];

const questions3 = [
    {
        question: "Quale pianeta è conosciuto come il pianeta rosso?",
        answers: [
            {text: "Venere", correct: false},
            {text: "Marte", correct: true},
            {text: "Giove", correct: false},
            {text: "Saturno", correct: false}
        ]
    },
    {
        question: "Chi scrisse 'Romeo e Giulietta'?",
        answers: [
            {text: "William Shakespeare", correct: true},
            {text: "Jane Austen", correct: false},
            {text: "Charles Dickens", correct: false},
            {text: "Leo Tolstoy", correct: false}
        ]
    },
    {
        question: "Qual è l'animale nazionale dell'Australia?",
        answers: [
            {text: "Canguro", correct: true},
            {text: "Koala", correct: false},
            {text: "Emù", correct: false},
            {text: "Wombat", correct: false}
        ]
    },
    {
        question: "Chi dipinse 'La nascita di Venere'?",
        answers: [
            {text: "Sandro Botticelli", correct: true},
            {text: "Leonardo da Vinci", correct: false},
            {text: "Michelangelo", correct: false},
            {text: "Raffaello Sanzio", correct: false}
        ]
    }
];

const setTableNames = ["firstSetComplete", "secondSetComplete", "thirdSetComplete"];

const questionElement = document.getElementById("question");
const answerButtons = document.getElementById("answer-buttons");
const nextButton = document.getElementById("btnNext");

let currentQuestionIndex = 0;
let score = 0;
let currentQuestions = [];
let currentSetIndex = 0;
let currentSetTableName;

function setTableNameIndex(setTableNameNbr) {
    if ((setTableNameNbr - 1) === 0){
        currentSetTableName = setTableNames[setTableNameNbr - 1];
    } else if((setTableNameNbr - 1) === 1) {
        currentSetTableName = setTableNames[setTableNameNbr - 1];
    }else if((setTableNameNbr - 1) === 2) {
        currentSetTableName = setTableNames[setTableNameNbr - 1];
    }
}

function loadChallengeSet(questions) {
    currentQuestions = questions;
    currentQuestionIndex = 0;
    score = 0;
    nextButton.innerHtml = "Next";
    showQuestion();
}

function showQuestion(){
    resetState();
    let currentQuestion = currentQuestions[currentQuestionIndex];
    let questionNbr = currentQuestionIndex + 1;
    questionElement.innerHTML = questionNbr + ". " + currentQuestion.question;

    currentQuestion.answers.forEach(answer => {
        const button = document.createElement("button");
        button.innerHTML = answer.text;
        button.classList.add("btnAnswer");
        answerButtons.appendChild(button);
        if(answer.correct){
            button.dataset.correct = answer.correct;
        }
        button.addEventListener("click", selectAnswer);
    });
}

function resetState(){
    nextButton.style.display = "none";
    nextButton.innerHTML = "Next";
    while(answerButtons.firstChild){
        answerButtons.removeChild(answerButtons.firstChild);
    }
}

function selectAnswer(e){
    const selectedBtn = e.target;
    const isCorrect = selectedBtn.dataset.correct === "true";
    if(isCorrect){
        selectedBtn.classList.add("correct");
        score++;
    }else {
        selectedBtn.classList.add("incorrect");
    }
    Array.from(answerButtons.children).forEach(button => {
       if(button.dataset.correct === "true"){
           button.classList.add("correct");
       } 
       button.disabled = true;
    });
    nextButton.style.display = "block";
}

function showScore(){
    resetState();
    if(score === currentQuestions.length){
        questionElement.innerHTML = 'Perfect score! You can move to the next challenge';
    }else{
        questionElement.innerHTML = `You scored ${score} out of ${questions1.length}!`;
    }
    nextButton.innerHTML = "Restart the challenge";
    nextButton.style.display = "block";
}

function handleNextButton(){
    currentQuestionIndex++;
    if(currentQuestionIndex < currentQuestions.length){
        showQuestion();
    }else {
        console.log(currentSetTableName);
        $.ajax({
            url: 'ChallengeController',
            method: 'POST',
            data: { columnName: currentSetTableName },
            success: function(response) {
                if (response.success) {
                    showScore();
                } else {
                    alert('Failed to update challenge status');
                }
            },
            error: function(xhr, status, error) {
                console.error(xhr.responseText);
            }
        });
    }
}

nextButton.addEventListener("click", ()=>{
    if(currentQuestionIndex < questions1.length){
        handleNextButton();
    }else{
        loadChallengeSet(currentQuestions);
    }
});
setTableNameIndex(1);
loadChallengeSet(questions1);



