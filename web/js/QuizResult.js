//Get Latest Results
const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
let results;
let quiz;
let resultID = "";
window.onload = function() 
{
      resultID = urlParams.get('resultsID');
      getResults();
}

//Get Result via ID
//Grab quizID out of Results

function getResults()
{

    //So do "QuizResultsService/items" to get them all or QuizService/items/{id} to get one.
    let url = "quizResultService/Items/" + resultID; // REST-style: URL refers to an entity or collection, not an action
    let xmlhttp = new XMLHttpRequest();
    let method = "GET";
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            let resp = xmlhttp.responseText;
            console.log(resp);
            results = JSON.parse(resp);
            getQuiz();
        }
    }
    xmlhttp.open(method, url, true); // method is either POST or PUT
    xmlhttp.send();
}
//Get a quiz via ID
function getQuiz()
{
    //So do "QuizService/items" to get them all or QuizService/items/{id} to get one.
    let url = "QuizService/Items/" + results["quizID"]; // REST-style: URL refers to an entity or collection, not an action
    let xmlhttp = new XMLHttpRequest();
    let method = "GET";
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            let resp = xmlhttp.responseText;
            quiz = JSON.parse(resp);
            console.log(resp);
            buildQuiz(resp);
        }
    }
    xmlhttp.open(method, url, true); // method is either POST or PUT
    xmlhttp.send();
}
function buildQuiz()
{
    console.log("------------")
    console.log(quiz);
    console.log(results);
    //Place boxes of each quizQuestions.
    let box = document.querySelector("#ResultScreen");
    console.log(quiz["quizQuestions"].length);
    box.innerHTML += "<p>" + "User: " + results["username"] + "||Results ID: " + results["resultId"] + "||Quiz Title: " + quiz["quizTitle"] + "</p>";
    for (let i = 0; i < quiz["quizQuestions"].length; i++)
    {
        let userAnswers = results["userAnswers"];
        let userAnswerCurrent = userAnswers[i];
        let answer =  Number(quiz["quizQuestions"][i]["answer"]);
        box.innerHTML += "<p>" + quiz["quizQuestions"][i]["questionText"] + "</p>" + "<br>";
        box.innerHTML += `<div id="choices${i}"></div>`;
        let id = `#choices${i}`;
        let lake = document.querySelector(`#choices${i}`);
        let choices = quiz["quizQuestions"][i]["choices"];
        console.log(choices);
        let ch = choices.split("|");
        console.log(ch);
        lake.innerHTML += "<ul class=`choicesSnack`>" + addChoices(ch,id) + "</ul>";
        box.innerHTML += "<p>The answer is: " + ch[answer] + "</p>";
        box.innerHTML += "<p>The user answered: " + ch[userAnswerCurrent] + "</p>";
        if (ch[answer] === ch[userAnswerCurrent])
        {
            box.innerHTML += "The user got the right answer!";
        }
        else
        {
          box.innerHTML += "The user got the wrong answer.";
        }
        
    }
    box.innerHTML += "<br>" +"<div id='ResultsScore'>" + "The total score of the quiz: " + results["scoreNumerator"] + "/" + results["scoreDenumerator"] + "</div>";
}

function addChoices(obj)
{

 let stringCat = "";
 for (i = 0; i < obj.length; i++)   
 {
   stringCat += "<li>" + obj[i] + "</li>";
 }
 return stringCat;
}
//Create the results page
