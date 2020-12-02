function load_choice_report() {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", admin_get_choicereport, true);
    xhr.send();
   
  // This will process results and update HTML as appropriate. 
  xhr.onloadend = function () {
    if (xhr.readyState == XMLHttpRequest.DONE) {
      processChoiceReportResponse(xhr.responseText);
    } else {
        console.log("NO INFORMATION RECIEVED!!!");
        window.location = "./404.html"; 
    }
  };
}


function processChoiceReportResponse(result) {
    let choiceReport = JSON.parse(result);
    console.log(choiceReport);
    if(choiceReport["statusCode"] == 200){
        console.log("pass");
        for(let i = 0; i < choiceReport["choiceList"].length; i++) {
            let choice = choiceReport["choiceList"][i];
            add_choice_in_report(choice.name, choice.uuid, choice.dateCreated, choice.dateCompleted);
            
        }
    } else {
        console.log("ERROR LOADING CHOICE REPORT");
    }
}

function add_choice_in_report(name, uuid, timeCompleted, timeCreated) {
    let choices = document.getElementById("choices");
    let newChoice = document.createElement("div");
    newChoice.classList.add("choice");
    
    easy_element_add(newChoice, "h2", "Name:", name);
    
    easy_element_add(newChoice, "h3", "UUID:", uuid);
    easy_element_add(newChoice, "h3", "Completed On:", timeCompleted);
    easy_element_add(newChoice, "h3", "Time Created:", timeCreated);
    
    choices.appendChild(newChoice);    
}

function easy_element_add(baseElement, elementName, displayText, displayValue) {
    let element = document.createElement(elementName);
    element.innerHTML = displayText + " " + displayValue;
    baseElement.appendChild(element);
}

document.addEventListener("DOMContentLoaded", function() {
    load_choice_report();
});