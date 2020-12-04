function initialize(){
    const likeBtn1 = document.getElementById("alt1-like");
    likeBtn1.addEventListener("click", function() { like(1); dislike(1)});

    const disBtn1 = document.getElementById("alt1-dislike");
    disBtn1.addEventListener("click", function() { dislike(1); like(1); });

    const likeBtn2 = document.getElementById("alt2-like");
    likeBtn2.addEventListener("click", function() { like(2); dislike(2)});

    const disBtn2 = document.getElementById("alt2-dislike");
    disBtn2.addEventListener("click", function() { dislike(2); like(2); });

    const likeBtn3 = document.getElementById("alt3-like");
    likeBtn3.addEventListener("click", function() {like(3); dislike(3)} );

    const disBtn3 = document.getElementById("alt3-dislike");
    disBtn3.addEventListener("click", function() {dislike(3); like(3);});

    const likeBtn4 = document.getElementById("alt4-like");
    likeBtn4.addEventListener("click", function() {like(4); dislike(4) });

    const disBtn4 = document.getElementById("alt4-dislike");
    disBtn4.addEventListener("click", function() {dislike(4); like(4);});

    const likeBtn5 = document.getElementById("alt5-like");
    likeBtn5.addEventListener("click", function() {like(5); dislike(5) });

    const disBtn5 = document.getElementById("alt5-dislike");
    disBtn5.addEventListener("click", function() {dislike(5); like(5);});
}

function like(x) {
    let data = {};
    data["userID"] = userUUID;
    data = JSON.stringify(data);
    let xhr = new XMLHttpRequest();
    xhr.open("POST", choice_and_alternative_url + "/" + choiceUUID + "/" + x
        + "/Like", true);
    xhr.setRequestHeader('Content-Type','application/json');
    xhr.send(data);
    xhr.onloadend = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            let info = JSON.parse(xhr.responseText);
            if(info["statusCode"] == 200){
                console.log(info);
                switch(info["likeChange"]){
                    case 1:
                        let likesUP = document.getElementById("alt" + x + "-likes");
                        let n1 = parseInt(likesUP.innerHTML,10) + 1;
                        console.log(n1);
                        console.log(likesUP);
                        likesUP.innerHTML = n1;
                        break;
                    case 0:
                        break;
                    case -1:
                        let likesDOWN = document.getElementById("alt" + x + "-likes");
                        let n2 = (likesDOWN.innerHTML) - 1;
                        likesDOWN.innerHTML = n2;
                        break;
                    default:
                        break;
                }
            }
            console.log(info);
            console.log(x);
        }
    }
    console.log(x);
}
function dislike(x){
    console.log(x);
    let data = {};
    data["userID"] = userUUID;
    console.log(userUUID);
    data = JSON.stringify(data);
    let xhr = new XMLHttpRequest();
    xhr.open("POST", choice_and_alternative_url + "/" + choiceUUID + "/" + x
        + "/Dislike", true);
    xhr.setRequestHeader('Content-Type','application/json');
    xhr.send(data);
    xhr.onloadend = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            let info = JSON.parse(xhr.responseText);
            console.log(info);
            if(info["statusCode"] == 200){
                switch(info["dislikeChange"]){
                    case 1:
                        let disUP = document.getElementById("alt" + x + "-dislikes");
                        let n1 = parseInt(disUP.innerHTML,10) + 1;
                        disUP.innerHTML = n1;
                        break;
                    case 0:
                        break;
                    case -1:
                        let disDOWN = document.getElementById("alt" + x + "-dislikes");
                        let n2 = (disDOWN.innerHTML) - 1;
                        disDOWN.innerHTML = n2;
                        break;
                    default:
                        break;
                }
            }
            console.log(info);
            console.log(x);
        }
    }
}


document.addEventListener("DOMContentLoaded", function() {
    initialize();
});