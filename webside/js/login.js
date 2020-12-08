var userUUID;
var userName;

function init2(){
    const btn = document.getElementById("loginBtn");
    btn.addEventListener("click", login);

    const accBtn = document.getElementById("createAccBtn");
    accBtn.addEventListener("click", createAccount);
}

//get account info from form
function getAccountInfo(){
    let data = {};

    data["name"] = document.getElementById("username").value;
    data["password"] = document.getElementById("password").value;
    return data;
}

//login to existing account
function login(){
    let data = getAccountInfo();
    let name = data["name"];
    data = JSON.stringify(data);

    let xhr = new XMLHttpRequest();
    xhr.open("POST", choice_complete_url + "/" + choiceUUID + "/LoginParticipant", true);
    xhr.setRequestHeader('Content-Type','application/json');
    xhr.send(data);

    xhr.onloadend = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            let info = JSON.parse(xhr.responseText);
            if(info["statusCode"] == 200){
                updateUI(info["result"], name);
                load_user_selected_ratings();
            }
           else {
                console.log("Invalid Username or Password");
                document.getElementById("loginError").innerText = "Invalid Username or Password";
            }
        }
        else {
            console.log("NO INFORMATION RECIEVED!!!");
            window.location = "./404.html";
        }
    };
}

//create a new account
function createAccount(){
    let data = getAccountInfo();
    let name = data["name"];
    data = JSON.stringify(data);

    let xhr = new XMLHttpRequest();
    xhr.open("POST", choice_complete_url + "/" + choiceUUID + "/CreateParticipant", true);
    xhr.setRequestHeader('Content-Type','application/json');
    xhr.send(data);

    xhr.onloadend = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            let info = JSON.parse(xhr.responseText);
            if(info.statusCode === 200){
                updateUI(info["result"], name);
            }
            else {
                console.log(info["error"]);
                document.getElementById("loginError").innerText = "User already exists";
            }
        }
        else {
            console.log("NO INFORMATION RECIEVED!!!");
            window.location = "./404.html";
        }
    };
}

function updateUI(id, name){
    userUUID = id;
    userName = name;

    document.getElementById("header").classList.remove('blur');
    document.getElementById("choices").classList.remove('blur');
    document.getElementById("login").remove();
}

document.addEventListener("DOMContentLoaded", function() {
    init2();
});

