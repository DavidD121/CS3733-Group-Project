var choiceUUID;

function init() {
    selectChoice();
}

function selectChoice() {
    choiceUUID = document.location.search.replace(/^.*?\=/, "");
    processChoiceResponse(null);
}

function getChoiceInfo() {
   var xhr = new XMLHttpRequest();
   xhr.open("GET", choice_get_url + choiceUUID, true);
   xhr.send();
   
   console.log("sent");

  // This will process results and update HTML as appropriate. 
  xhr.onloadend = function () {
    if (xhr.readyState == XMLHttpRequest.DONE) {
      console.log ("XHR:" + xhr.responseText);
      processListResponse(xhr.responseText);
    } else {
      console.log("NO INFORMATION RECIEVED!!!");
    }
  };
}

function processChoiceResponse(result) {
    //let choice = JSON.parse(result);
   // let choice = {};
    //choice["description"] = "TEST";
    //choice["alternative1.name"] = "TEST2"
    document.getElementById("choiceDescription").innerHTML = choice["description"];
    
    /*for(let i = 1; i < 6; i++) {
        let alternativeName = choice["alternative" + i + ".name"];

        if(alternativeName != null) {//alt1-name
            document.getElementById("alt" + i + "-name") = "alternativeName";
        } else {
            document.getElementById("alternative" + i).style.visibility = "hidden";
        }
    }*/
    
    document.getElementById("choiceUUID").innerHTML = "UUID: " + choiceUUID;
    
}

document.addEventListener("DOMContentLoaded", function() {
    init();
});