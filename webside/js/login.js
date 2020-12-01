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
    data = JSON.stringify(data);

    let xhr = new XMLHttpRequest();
    xhr.open("POST", choice_complete_url + "/" + choiceUUID + "/LoginParticipant", true);
    xhr.send(data);

    xhr.onloadend = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            let info = JSON.parse(xhr.responseText);
            if(info["statusCode"] == 200){
                //document.getElementById("choices").classList.toggle('blur');
                document.getElementById("header").classList.remove('blur');
                document.getElementById("login").remove();
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
    data = JSON.stringify(data);
    console.log(data);

    let xhr = new XMLHttpRequest();
    xhr.open("POST", choice_complete_url + "/" + choiceUUID + "/CreateParticipant", true);
    xhr.send(data);

    xhr.onloadend = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            let info = JSON.parse(xhr.responseText);
            if(info.statusCode == 200){
                document.getElementById("choices").classList.remove('blur');
                document.getElementById("header").classList.remove('blur');
                document.getElementById("login").remove();
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


document.addEventListener("DOMContentLoaded", function() {
    init2();
});

