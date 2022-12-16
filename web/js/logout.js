window.onload = function()
{
    document.querySelector("#logoutBtn").addEventListener("click", logoutUser);
}

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