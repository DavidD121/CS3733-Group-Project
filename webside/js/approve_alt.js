function init4(){
    for(let i = 1; i < 5; i++) {
        let approveBtn = document.getElementById("approveBtn" + i);
        approveBtn.addEventListener("click", function() { 
            if(!isLocked)
                approveAlt(i);
        });
    }
}

function approveAlt(alt){
    let data = {};
    data["index"] = alt;

    console.log(data);
    data = JSON.stringify(data);
    let xhr = new XMLHttpRequest();



    xhr.open("POST", choice_complete_url + "/" + choiceUUID, true);
    xhr.setRequestHeader('Content-Type','application/json');
    xhr.send(data);
    console.log(data);
    xhr.onloadend = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            let info = JSON.parse(xhr.responseText);
            if (info["statusCode"] == 200) {
                console.log(info);
                setLocked(alt);
            }

        }
    }
}


document.addEventListener("DOMContentLoaded", function() {
    init4();
});