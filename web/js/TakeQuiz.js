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
else
{
    alert("You can't go back.");
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
else
{
        alert("You can't go forward more.");
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
    if (CurrentQuestion === length-1)
    {
     if (!document.querySelector("input[name=" + "Q" + (length-1) + "]:checked"))
     {
         alert("All questions must be answered");
         return;
     }
     else{
    answers[length-1] = document.querySelector("input[name=" + "Q" + (length-1) + "]:checked").value;
    document.querySelectorAll("input[name=" + "Q" + CurrentQuestion + "]")[Number(answers[length-1])].checked = true;
     }
    }
    if (answers.length < quiz["quizQuestions"].length)
    {
        alert("All questions must be answered");
        return;
    }
    

    for (i = 0; i < length; i++)
    {
        if (Number(answers[i]) === quiz["quizQuestions"][i]["answer"])
        {
            correct += 1;
            console.log("Correct");
        } else
        {
            console.log(quiz["quizQuestions"][i]["answer"]);
            //console.log("Holy fuck you got it wrong.");
        }
    }
    console.log(userAnswers);
    console.log(correct);
    endtime = new Date();
    //Bunch of code, have fun.
    let quizID = urlParams.get('quizID');
    let user = document.querySelector("#User").innerHTML;
    obj = {
        "resultID": "QR-2142",
        "quizID": quiz["quizId"],
        "username": user,
        "userAnswers": answers,
        "startTime": getTimeStamp(startTime),
        "endTime": getTimeStamp(endtime),
        "scoreNumerator": correct,
        "scoreDenumerator": length
    };
    console.log(obj);
    pushResults(obj);
}

function pushResults(obj)
{
    let url = "quizResultService/Items";
    let xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            let resp = xmlhttp.responseText;
            console.log(resp);
            if (resp.includes("QR")) {
                id = resp.split("-");
                console.log(id[1]);
                window.location.assign("ResultTake.jsp?resultsID=" + "QR-"+ (Number(id[1]) -1));
            }
        }
    }
    xmlhttp.open("POST", url, true); // method is either POST or PUT
    xmlhttp.send(JSON.stringify(obj));
    }
//function getResultID(resp)
//{
//    console.log("Got here");
//    let data = JSON.parse(resp);
//    id = data[0]["resultsID"];
//    console.log(id);
//    sendResult(id);
//}


makeQuiz();
	