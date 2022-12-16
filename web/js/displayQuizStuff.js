/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


window.onload = function () {
    let row = document.querySelectorAll("#doQuiz");
    for (i = 0; i < row.length; i++)
    {
        document.querySelectorAll("#doQuiz")[i].addEventListener("click", sendResult);
    }
    document.querySelector("#searchBtn").addEventListener("click", grabSearch);
    document.querySelector("#resetBtn").addEventListener("click", loadQuizes);
    document.querySelector("#logoutBtn").addEventListener("click", logoutUser);
    
};
function logoutUser()
{
    console.log("FOCAC");
    let url = "logout/";
    let xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            let resp = xmlhttp.responseText;
            console.log(resp);
            if (resp.includes("Out")) {
                alert("Thank you for logging out.");
                window.location.replace("index.html");

            } else {
                
            }
        }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}

function sendResult(evt)
{
    let row = evt.target.parentElement.parentElement;
    let id = row.querySelector("td").innerHTML;
    console.log(id);
    window.location.assign("QuizTake.jsp?quizid=" + id);
}

function grabSearch() {
    let type = document.querySelector("#searchChoice").value;

    if (type === "text") {
        searchText();
    } else if (type === "id") {
        searchID();
    } else {
        alert("no search type selected");
    }
}

function searchText() {
    let value = document.querySelector("#searchInput").value;
    let con = /\w+/;

    if (con.exec(value)) {
        let url = "SearchQuestionText/Items/" + value;
        let xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                let resp = xmlhttp.responseText;
                console.log(resp + "A");
                if (resp.search("ERROR") >= 0) {
                    alert("could not complete request");
                } else {
                    window.location.assign("DisplayQuizzes.jsp");
                }
            }
        };
        xmlhttp.open("GET", url, true);
        xmlhttp.send();
    } else {
        alert("Incorrect Text input");
    }
}

function searchID() {
    let value = document.querySelector("#searchInput").value;
    let con = /^QZ-\d{1,4}$/;

    if (con.exec(value)) {
        let url = "SearchQuizID/Items/" + value;
        let xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                let resp = xmlhttp.responseText;
                console.log(resp + "A");
                if (resp.search("ERROR") >= 0) {
                    alert("could not complete request");
                } else {
                    window.location.assign("DisplayQuizzes.jsp");
                }
            }
        };
        xmlhttp.open("GET", url, true);
        xmlhttp.send();
    } else {
        alert("Incorrect quiz ID, format as QZ-####");
    }
}

function loadQuizes() {
    let url = "QuizService/Items";
    let xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
            let resp = xmlhttp.responseText;
            console.log(resp + "A");
            if (resp.search("ERROR") >= 0) {
                alert("could not complete request");
            } else {
                window.location.assign("DisplayQuizzes.jsp");
            }
        }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();
}
