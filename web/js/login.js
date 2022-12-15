window.onload = function()
{
    document.querySelector("#loginBtn").addEventListener("click", loginUser);
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