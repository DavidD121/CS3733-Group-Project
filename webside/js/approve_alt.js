function init4(){
    const approveBtn1 = document.getElementById("approveBtn1");
    approveBtn1.addEventListener("click", function() { approveAlt(1); });

    const approveBtn2 = document.getElementById("approveBtn2");
    approveBtn2.addEventListener("click", function() { approveAlt(2);});

    const approveBtn3 = document.getElementById("approveBtn3");
    approveBtn3.addEventListener("click", function() {approveAlt(3);} );

    const approveBtn4 = document.getElementById("approveBtn4");
    approveBtn4.addEventListener("click", function() {approveAlt(4); });

    const approveBtn5 = document.getElementById("approveBtn5");
    approveBtn5.addEventListener("click", function() {approveAlt(5); });
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



            }

        }
    }
}


document.addEventListener("DOMContentLoaded", function() {
    init4();
});