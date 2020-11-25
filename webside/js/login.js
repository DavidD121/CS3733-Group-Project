function init(){
    const btn = document.getElementById("loginBtn");
    btn.addEventListener("click", login);

    const accBtn = document.getElementById("createAccBtn");
    accBtn.addEventListener("click", createAccount);
}

//get account info from form
function getAccountInfo(){
    let data = {};
    data["username"] = document.getElementById("username").value;
    data["password"] = parseInt(document.getElementById("password").value);
    return data;
}

//login to existing account
function login(){
    let data = getAccountInfo();

    /*
    let js = JSON.stringify(data);
    console.log("JS:" + js); not sure if this is needed?*/

    let xhr = new XMLHttpRequest();
    xhr.open("POST", choice_complete_url + "/" + choiceUUID, true);
    xhr.send();

    xhr.onloadend = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            console.log ("XHR:" + xhr.responseText);
            let json = JSON.stringify(xhr.responseText);
            let info = JSON.parse(json );
            console.log(json);
            console.log(info);
            if(info["response"]=="200"){
                document.getElementById("choices").classList.toggle('blur');
                document.getElementById("login").remove();
            }
            else {
                console.log("Invalid Username or Password");
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

    /*
    let js = JSON.stringify(data);
    console.log("JS:" + js); */

    let xhr = new XMLHttpRequest();
    xhr.open("POST", choice_complete_url + "/" + choiceUUID, true);
    xhr.send();

    xhr.onloadend = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            console.log ("XHR:" + xhr.responseText);
            console.log(xhr.status);
            let json = JSON.stringify(xhr.responseText);
            let info = JSON.parse(json);
            console.log(json);
            console.log(info);
            if(info["response"]=="200"){
                document.getElementById("choices").classList.remove('blur');
                document.getElementById("login").remove();
            }
            else {
                console.log("Fail");
            }

        }
        else {
            console.log("NO INFORMATION RECIEVED!!!");
            window.location = "./404.html";

        }
    };

}


document.addEventListener("DOMContentLoaded", function() {
    init();
});

