var choiceUUID;

function init() {
    selectChoice();
}

function selectChoice() {
    choiceUUID = document.location.search.replace(/^.*?\=/, "");
    getChoiceInfo();
}

function getChoiceInfo() {
   var xhr = new XMLHttpRequest();
   xhr.open("GET", choice_get_url + "/" + choiceUUID, true);
   xhr.send();
   
  // This will process results and update HTML as appropriate. 
  xhr.onloadend = function () {
    if (xhr.readyState == XMLHttpRequest.DONE) {
        console.log ("XHR:" + xhr.responseText);
        //console.log(xhr.status);
      processChoiceResponse(xhr.responseText);
    } else {
        console.log("NO INFORMATION RECIEVED!!!");
        window.location = "./404.html"; 
    }
  };
}

function processChoiceResponse(result) {
    let choice = JSON.parse(result);
    
    document.getElementById("choiceUUID").innerHTML = "UUID: " + choiceUUID;
    document.getElementById("choiceDescription").innerHTML = choice["description"];
    
    for(let i = 1; i < 6; i++) {
        let alternative = choice["alternative" + i];
        if(alternative != undefined) {
            document.getElementById("alt" + i + "-name").innerHTML = alternative["name"];
        } else {
            document.getElementById("alternative" + i).remove();
        }
    }
}

document.addEventListener("DOMContentLoaded", function() {
    init();
});