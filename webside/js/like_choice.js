function initialize(){
    for(let i = 1; i <= 5; i++) {
        let likeBtn = document.getElementById("alt" + i + "-like");
        likeBtn.addEventListener("click", function() { 
            if(!isLocked) {
                like(i, "like"); 
            }
        });
        
        let dislikeBtn = document.getElementById("alt" + i + "-dislike");
        dislikeBtn.addEventListener("click", function() { 
            if(!isLocked) {
                like(i, "dislike"); 
            }
        });
    }   
}

function like(x, type) {
    let data = {};
    data["userID"] = userUUID;
    data["type"] = type;
    data = JSON.stringify(data);
    let xhr = new XMLHttpRequest();
    let type_url = type.charAt(0).toUpperCase() + type.slice(1);
    xhr.open("POST", choice_and_alternative_url + "/" + choiceUUID + "/" + x
        + "/" + type_url, true);
    xhr.setRequestHeader('Content-Type','application/json');
    xhr.send(data);


    xhr.onloadend = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            let info = JSON.parse(xhr.responseText);
            if(info["statusCode"] == 200){
                updateRating(x, parseInt(info["likeChange"],10), "like");
                updateRating(x, parseInt(info["dislikeChange"],10), "dislike");
            } else if(info["statusCode"] == 300) {
                getChoiceInfo();        
            }
        }
    }
}
function updateRating(alt, change, type){
    let doc = document.getElementById("alt" + alt + "-"+ type + "s");
    let n1 = parseInt(doc.innerHTML,10) + change;
    doc.innerHTML = String(n1);

    if(change == 1) {
        create_user_li(document.getElementById("alt" + alt + "-" + type + "s-users"), userName);
        document.getElementById("alt" + alt + "-" + type).classList.add("ratingSelected");
    } else if(change == -1) {
        let usersList = document.getElementById("alt" + alt + "-" + type + "s-users").getElementsByTagName("li");
        for(let i = 0; i < usersList.length; i++) {
            if(usersList[i].innerHTML == userName) {
                usersList[i].remove();
            }
        }
        document.getElementById("alt" + alt + "-" + type).classList.remove("ratingSelected");
    }
}

document.addEventListener("DOMContentLoaded", function() {
    initialize();
});