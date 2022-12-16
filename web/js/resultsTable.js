window.onload = function()
{
  let row = document.querySelectorAll("#displayResult");
  for (i = 0; i < row.length; i++)
  {
      document.querySelectorAll("#displayResult")[i].addEventListener("click", sendResult);
  }
}

function sendResult(evt)
{
  let row = evt.target.parentElement.parentElement;
  let id = row.querySelector("td").innerHTML;
  console.log();
  window.location.assign("ResultTake.jsp?resultsID=" + id)
}