function load_choice_report() {
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

var TestEnum = {
    TEST: "hello",
    TEST2: "howdy"
};