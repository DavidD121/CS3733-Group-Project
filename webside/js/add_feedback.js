function init3(){
    const feedBtn1 = document.getElementById("alt1-feedbackBtn");
    feedBtn1.addEventListener("click", function() { addFeedback(1); });

    const feedBtn2 = document.getElementById("alt2-feedbackBtn");
    feedBtn2.addEventListener("click", function() { addFeedback(2);});

    const feedBtn3 = document.getElementById("alt3-feedbackBtn");
    feedBtn3.addEventListener("click", function() {addFeedback(3);} );

    const feedBtn4 = document.getElementById("alt4-feedbackBtn");
    feedBtn4.addEventListener("click", function() {addFeedback(4); });

    const feedBtn5 = document.getElementById("alt5-feedbackBtn");
    feedBtn5.addEventListener("click", function() {addFeedback(5); });
}

function addFeedback(alt){
    let data = {};
    data["userID"] = userUUID;
    data["feedback"] = document.getElementById("alt" + alt + "-feedback").innerText;
    let feedback = data["feedback"];

    console.log(data);
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
            }

        }
    }
}


document.addEventListener("DOMContentLoaded", function() {
    init3();
});