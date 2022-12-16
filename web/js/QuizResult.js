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
      document.querySelector("#resultReturn").addEventListener("click", loadQuizResults);
}

//Get Result via ID
//Grab quizID out of Results
 function loadQuizResults() {
    let url = "quizResultService/Items";
    let xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            let resp = xmlhttp.responseText;
            console.log(resp + "A");
            if (resp.search("ERROR") >= 0) {
                alert("could not complete request");
            } else {
              window.location.assign("DisplayResults.jsp");
            }
        }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}
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
      let text=""
    //Place boxes of each quizQuestions.
    let box = document.querySelector("#ResultScreen");
    console.log(quiz["quizQuestions"].length);
    text += "<p>" + "User: " + results["username"] + "||Results ID: " + results["resultId"] + "||Quiz Title: " + quiz["quizTitle"] + "</p>";
    for (let i = 0; i < quiz["quizQuestions"].length; i++)
    {
          text+="<div class='data'>"
        let userAnswers = results["userAnswers"];
        let userAnswerCurrent = userAnswers[i];
        let answer =  Number(quiz["quizQuestions"][i]["answer"]);
        text += "<p>" + quiz["quizQuestions"][i]["questionText"] + "</p>" + "<br>";
        text += `<div id="choices${i}">`;
        let id = `#choices${i}`;
        let choices = quiz["quizQuestions"][i]["choices"];
        console.log(choices);
        let ch = choices.split("|");
        console.log(ch);
        text += "<ol class=`choicesSnack` type='a'>" + addChoices(ch,id) + "</ol></div>";
        text += "<p>The answer is: " + ch[answer] + "</p>";
        text += "<p>The user answered: " + ch[userAnswerCurrent] + "</p>";
        if (ch[answer] === ch[userAnswerCurrent])
        {
            text += "The user got the right answer!";
        }
        else
        {
          text += "The user got the wrong answer.";
        }
        text+="</div>
    }
    text += "<br>" +"<div id='ResultsScore'>" + "The total score of the quiz: " + results["scoreNumerator"] + "/" + results["scoreDenumerator"] + "</div>";
    text += "<br>" +"<div id='ResultsScore'>" + results["scoreNumerator"]  /  results["scoreDenumerator"] * 100 + "</div>";
      box.innerHTML=text
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
