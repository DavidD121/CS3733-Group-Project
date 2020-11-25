function onAddChoice(e) {
    let data = {};
    data["description"] = document.getElementById("choiceDescription").value;
    data["teamMembers"] = parseInt(document.getElementById("choiceTeamMembers").value);
    if(isNaN(data["teamMembers"])) {
        data["teamMembers"] = 0;
    }
    
    for(let i = 1; i <= 5; i++) {
        let alternative = document.getElementById("choiceAlt" + i).value;
        
        if(!!alternative) {
            data["alternative" + i] = alternative;
        } else {
            break;
        }
    }
    
    if(isDataValid(data)) { 
        var js = JSON.stringify(data);

        var xhr = new XMLHttpRequest();
        xhr.open("POST", choice_add_url, true);
        xhr.send(js);

        xhr.onloadend = function () {

        if (xhr.readyState == XMLHttpRequest.DONE) {
             if (xhr.status == 200) {
                 let response = JSON.parse(xhr.responseText);
                 onGetChoice(response.uuid);
             } else {
                 var js = JSON.parse(xhr.responseText);
                 var err = js["response"];
                 alert (err);
             }
        }
      };
    } else {
        alert("Please fill out all required fields!");
    }
}

function isDataValid(data) {
    let teamMembers = data["teamMembers"];
    let description = data["description"];
    let alt1 = data["alternative1"];
    let alt2 = data["alternative2"];
    
    return (teamMembers != undefined && teamMembers > 0) && (description != "") && alt1 != undefined && alt2 != undefined;
}

