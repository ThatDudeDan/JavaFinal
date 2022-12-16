let startTime = new Date();
let endtime;
let quiz;
let CurrentQuestion = 0;
let answers = [];
let obj;
const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
window.onload = function()
{
    document.querySelector("#prevBtn").addEventListener("click", prevBtn);
    document.querySelector("#nextBtn").addEventListener("click", nextBtn);
    document.querySelector("#QuizSubmit").addEventListener("click", quizSubmit);
}
function makeQuiz()
{
    let quizID = urlParams.get('quizid');
    //So do "QuizService/items" to get them all or QuizService/items/{id} to get one.
    let url = "QuizService/Items/" + quizID; // REST-style: URL refers to an entity or collection, not an action
    let xmlhttp = new XMLHttpRequest();
    let method = "GET";
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            obj = xmlhttp.responseText;
            buildQuiz();
        }
    }
    xmlhttp.open(method, url, true); // method is either POST or PUT
    xmlhttp.send();
}
function getTimeStamp(date)
{
    let year = date.getFullYear();
    let month = date.getMonth();
    let day = date.getUTCDate();
    if (day < 10)
    {
        day = "0" + day;
    }
    let hours = date.getHours();
    if (hours < 10)
    {
        hours = "0" + hours;
    }
    let minutes = date.getMinutes();
    if (minutes < 10)
    {
        minutes = "0" + minutes;
    }
    let seconds = date.getSeconds();
    if (seconds < 10)
    {
        seconds = "0" + seconds;
    }
    let dateString = year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
    return dateString;
}

function prevBtn()
{
if (CurrentQuestion > 0)
{
if (document.querySelector("input[name=" + "Q" + CurrentQuestion + "]:checked") !== null)
{
answers[CurrentQuestion] = document.querySelector("input[name=" + "Q" + CurrentQuestion + "]:checked").value;
console.log(answers);
}
CurrentQuestion--;  
buildQuiz();
document.querySelectorAll("input[name=" + "Q" + CurrentQuestion + "]")[Number(answers[CurrentQuestion])].checked = true;

}
}

function nextBtn()
{
    quiz = JSON.parse(obj);
if (CurrentQuestion < quiz["quizQuestions"].length-1)
{
if (document.querySelector("input[name=" + "Q" + CurrentQuestion + "]:checked") !== null)
{
answers[CurrentQuestion] = document.querySelector("input[name=" + "Q" + CurrentQuestion + "]:checked").value;

}
 CurrentQuestion++;  
  buildQuiz(); 
document.querySelectorAll("input[name=" + "Q" + CurrentQuestion + "]")[Number(answers[CurrentQuestion])].checked = true;

}
}
function buildQuiz()
{
    quiz = JSON.parse(obj);
    let body = "";
    console.log(quiz);
    let quizQuestions = quiz["quizQuestions"];
    let length = quiz["quizQuestions"].length;
    console.log(length);
    body += "<div class='question'>" + QuizQuestion(quizQuestions[CurrentQuestion], CurrentQuestion) + "</div>";
    document.querySelector("#quizContent").innerHTML = body;

}

function QuizQuestion(obj, j)
{

    let list = "";
    let html = "";
    let choice1 = obj["choices"];
    let choices = choice1.split("|");
    let choicesLength = choices.length;
    for (i = 0; i < choicesLength; i++)
    {
        list += `<li><input type="radio" id="quizChoice${j}" name="Q${j}" value="${i}">${choices[i]}</input></li>`;
    }
    html = `<p>Question ${j + 1}: ${obj["questionText"]}</p><div>` + list + "</div>";
    return html;
}


function quizSubmit()
{
    console.log(startTime, endtime);
    let userAnswers = [];
    let correct = 0;
    console.log("Test");
    let length = quiz["quizQuestions"].length;
    console.log(length);
    for (i = 0; i < length; i++)
    {
        let item = `input[name="Q${i}"]:checked`;
        let choice = document.querySelector(item);
        if (choice === null)
        {
            console.log("Answer the quizQuestions")
            return;
        }
    }
    for (i = 0; i < length; i++)
    {
        let item = `input[name="Q${i}"]:checked`;
        let choice = document.querySelector(item);
        console.log(choice);
        userAnswers.push(Number(choice.value));
        if (Number(choice.value) === quiz["quizQuestions"][i]["answer"])
        {
            correct += 1;
            console.log("Correct");
        } else
        {
            console.log(choice.value);
            console.log(quiz["quizQuestions"][i]["answer"]);
            //console.log("Holy fuck you got it wrong.");
        }
    }
    console.log(userAnswers);
    console.log(correct);
    endtime = new Date();
    //Bunch of code, have fun.
    let quizID = urlParams.get('quizID');
    let user = document.querySelector("#user").innerHTML;
    obj = {
        "resultsID": 5325,
        "quizID": quizID,
        "username": user,
        "userAnswers": userAnswers.join("|"),
        "startTime": getTimeStamp(startTime),
        "endTime": getTimeStamp(endtime),
        "scoreNumerator": correct,
        "scoreDenominator": length
    };
    console.log(obj);
    pushResults(obj);
}
function loginUser() {
    let user = document.querySelector("#usernameInput");
    let password = document.querySelector("#passwordInput");
    if (password.value === "")
    {
        alert("Please enter a password.");
        password.focus();
        return;
    }
    let url = "userService/Items/" + "?user=" + user.value + "&?pass=" + password.value;
    console.log(user, password);
    let xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            let resp = xmlhttp.responseText;
            console.log(resp);
            if (resp.includes("Invalid")) {
                alert("Please enter a valid username or password.");
                user.value = "";
                password.value = "";
                user.focus();
            } else {
                window.location.assign("mainMenu.jsp");
            }
        }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}
//FUCK

//FUCK
makeQuiz();
	