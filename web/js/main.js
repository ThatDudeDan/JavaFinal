/*
 * This UI does not make any assumptions about the back end, 
 * except that JSON is the data exchange format.
 * Therefore, any back end will do - Java, PHP, etc.
 */
let addOrUpdate;

window.onload = function () {

    // add event handlers for buttons
    // add event handler for selections on the table
    document.querySelector("#resultsShow").addEventListener("click", loadQuizResults);
};

function clearSelections() {
    let trs = document.querySelectorAll("tr");
    for (let i = 0; i < trs.length; i++) {
        trs[i].classList.remove("highlighted");
    }
}

function handleRowClick(e) {
    //add style to parent of clicked cell
    clearSelections();
    e.target.parentElement.classList.add("highlighted");

    // enable Delete and Update buttons
    document.querySelector("#DeleteButton").removeAttribute("disabled");
    document.querySelector("#UpdateButton").removeAttribute("disabled");
}
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
// this function handles adds and updates


function populateUpdatePanelWithSelectedItem() {
    let tds = document.querySelector(".highlighted").querySelectorAll("td");
    document.querySelector("#itemIDInput").value = tds[0].innerHTML;
    document.querySelector("#titleInput").value = tds[1].innerHTML;
    document.querySelector("#artistInput").value = tds[2].innerHTML;
    document.querySelector("#priceInput").value = tds[3].innerHTML;
    document.querySelector("#isSoldInput").checked = "true" === tds[4].innerHTML;
}


function buildTable(text) {
    let data = JSON.parse(text);
    console.log(data);
    let theTable = document.querySelector("table");
    let html = theTable.querySelector("tr").innerHTML;
    for (let i = 0; i < data.length; i++) {
        let temp = data[i];
        console.log(temp);
        html += "<tr>";
        html += "<td>" + temp.id + "</td>";
        html += "<td>" + temp.title + "</td>";
        html += "<td>" + temp.artist + "</td>";
        html += "<td>" + temp.price + "</td>";
        html += "<td>" + temp.issold + "</td>";
        html += "</tr>";
    }
    theTable.innerHTML = html;
}
