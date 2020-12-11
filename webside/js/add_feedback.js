function init3(){ 
    for(let i = 1; i <= 5; i++) {
        let feedBtn = document.getElementById("alt" + i + "-feedbackBtn");
        feedBtn.addEventListener("click", function() { 
            if(!isLocked)
                addFeedback(i); 
        });
    }
}

function addFeedback(alt){
    let data = {};
    data["userID"] = userUUID;
    data["feedback"] = document.getElementById("alt" + alt + "-feedback").value.replace(/[^a-zA-Z0-9_ ]/g, "<br>");
    let feedback = data["feedback"];

    data = JSON.stringify(data);
    let xhr = new XMLHttpRequest();

    xhr.open("POST", choice_and_alternative_url + "/" + choiceUUID + "/" + alt
        + "/Feedback", true);
    xhr.setRequestHeader('Content-Type','application/json');
    xhr.send(data);

    xhr.onloadend = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            let info = JSON.parse(xhr.responseText);
            if (info["statusCode"] == 200) {
                console.log(info);
                let name = userName;
                let time = info["timeCreated"];
                let feedbackContainer = document.getElementById("alt" + alt + "-feedback-container");
    
                let feedbackElem = createFeedbackElement(name, time, feedback);
                feedbackContainer.appendChild(feedbackElem);
                document.getElementById("alt" + alt + "-feedback").value = "";
            }

        }
    }
}


document.addEventListener("DOMContentLoaded", function() {
    init3();
});