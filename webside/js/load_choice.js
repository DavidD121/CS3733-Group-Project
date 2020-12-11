var choiceUUID;
var totalAlternatives = 5;
var isLocked = false;

function init() {
    selectChoice();
    reload();
}

function reload() {
   const btn = document.getElementById("pageReload");
    
    btn.addEventListener("click", function() {
        getChoiceInfo();
    });
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
      processChoiceResponse(xhr.responseText);
    } else {
        console.log("NO INFORMATION RECIEVED!!!");
        window.location = "./404.html"; 
    }
  };
}

function processChoiceResponse(result) {
    let choice = JSON.parse(result);
    let uuid = choice["uuid"];
    if(uuid == -1) {
        window.location = "./404.html"; 
    } else {
        isLocked = (choice["approvedAlternative"] != 0);
        if (isLocked)
            setLocked(choice["approvedAlternative"]);
        
        document.getElementById("choiceUUID").innerHTML = "UUID: " + uuid;
        document.getElementById("choiceDescription").innerHTML = choice["description"];
        console.log(choice);
        let t = totalAlternatives;
        for(let i = 1; i <= t; i++) {
            let alternative = choice["alternative" + i];
            if(alternative != undefined) {
                loadAlt(alternative, i);
                totalAlternatives = i;
            } else {
                document.getElementById("alternative" + i).remove();
            }
        }
    }
    
}

function setLocked(altIndex) {
    for(let i = 1; i <= totalAlternatives; i++) {
        if(i == altIndex)
            document.getElementById("alternative" + i).classList.add("approved");
        else
            document.getElementById("alternative" + i).classList.add("notapproved");
    }
}


document.addEventListener("DOMContentLoaded", function() {
    init();
});

