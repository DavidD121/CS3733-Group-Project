function initialize(){
    const likeBtn1 = document.getElementById("alt1-like");
    likeBtn1.addEventListener("click", function() { like(1, "like"); });

    const disBtn1 = document.getElementById("alt1-dislike");
    disBtn1.addEventListener("click", function() {like(1, "dislike"); });

    const likeBtn2 = document.getElementById("alt2-like");
    likeBtn2.addEventListener("click", function() { like(2, "like");});

    const disBtn2 = document.getElementById("alt2-dislike");
    disBtn2.addEventListener("click", function() {  like(2, "dislike"); });

    const likeBtn3 = document.getElementById("alt3-like");
    likeBtn3.addEventListener("click", function() {like(3, "like");} );

    const disBtn3 = document.getElementById("alt3-dislike");
    disBtn3.addEventListener("click", function() {like(3,"dislike");});

    const likeBtn4 = document.getElementById("alt4-like");
    likeBtn4.addEventListener("click", function() {like(4, "like"); });

    const disBtn4 = document.getElementById("alt4-dislike");
    disBtn4.addEventListener("click", function() { like(4,"dislike");});

    const likeBtn5 = document.getElementById("alt5-like");
    likeBtn5.addEventListener("click", function() {like(5,"like"); });

    const disBtn5 = document.getElementById("alt5-dislike");
    disBtn5.addEventListener("click", function() { like(5,"dislike");});
}

function like(x, type) {
    let data = {};
    data["userID"] = userUUID;
    data["type"] = type;
    data = JSON.stringify(data);
    let xhr = new XMLHttpRequest();
    type = type.charAt(0).toUpperCase() + type.slice(1);
    xhr.open("POST", choice_and_alternative_url + "/" + choiceUUID + "/" + x
        + "/" + type, true);
    xhr.setRequestHeader('Content-Type','application/json');
    xhr.send(data);



    xhr.onloadend = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            let info = JSON.parse(xhr.responseText);
            if(info["statusCode"] == 200){
                let likeC = document.getElementById("alt" + x + "-likes");
                let n1 = parseInt(likeC.innerHTML,10) + parseInt(info["likeChange"],10);
                likeC.innerHTML = String(n1);
                updateRatingList(x, parseInt(info["likeChange"],10), "like");

                let disC= document.getElementById("alt" + x + "-dislikes");
                let n2 = parseInt(disC.innerHTML,10) + parseInt(info["dislikeChange"], 10);
                disC.innerHTML = String(n2);
                updateRatingList(x, parseInt(info["dislikeChange"],10), "dislike");

            }
        }
    }
}

function updateRatingList(alt, change, type) {
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