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
   
   console.log("sent");

  // This will process results and update HTML as appropriate. 
  xhr.onloadend = function () {
    if (xhr.readyState == XMLHttpRequest.DONE) {
      console.log ("XHR:" + xhr.responseText);
        console.log(xhr.status);
      processChoiceResponse(xhr.responseText);
    } else {
        console.log("NO INFORMATION RECIEVED!!!");
        window.location = "./404.html"; 

    }
  };
}

function processChoiceResponse(result) {
    let choice = JSON.parse(result);
    console.log("JSON: " + choice);
    document.getElementById("choiceUUID").innerHTML = "UUID: " + choiceUUID;
    document.getElementById("choiceDescription").innerHTML = choice["description"];
    
    for(let i = 1; i < 6; i++) {
        let alternativeName = choice["alternative" + i]["name"];
        console.log(alternativeName);
        if(alternativeName != null) {//alt1-name
            document.getElementById("alt" + i + "-name").innerHTML = alternativeName;
        } else {
            document.getElementById("alternative" + i).style.visibility = "hidden";
        }
    }
}

document.addEventListener("DOMContentLoaded", function() {
    init();
});