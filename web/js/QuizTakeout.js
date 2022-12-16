/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */
window.onload = function()
{
    document.querySelector("#quizShow").addEventListener("click", goToQuiz)
}
function goToQuiz()
{
    console.log("Damn");
    let id = "QZ-1004";
    window.location.assign("QuizTake.jsp" + "?quizid=" + id);
}

