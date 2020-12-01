function initialize(){
    const btn = document.getElementById("likeBtn");
    btn.addEventListener("click", like);

    const accBtn = document.getElementById("dislikeBtn");
    accBtn.addEventListener("click", dislike);
}

function like(){
    let xhr = new XMLHttpRequest();
    xhr.open("POST", choice_complete_url + "/" + choiceUUID + "/" + "/LoginParticipant", true);
}

function dislike(){

}


document.addEventListener("DOMContentLoaded", function() {
    initialize();
});