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
        }
    }
    
    var js = JSON.stringify(data);
    console.log("JS:" + js);
    
    var xhr = new XMLHttpRequest();
    xhr.open("POST", choice_add_url, true);
    xhr.send(js);
    
    xhr.onloadend = function () {
    console.log(xhr);
    console.log(xhr.request);
    if (xhr.readyState == XMLHttpRequest.DONE) {
    	 if (xhr.status == 200) {
	      console.log ("XHR:" + xhr.responseText);
    	 } else {
    		 console.log("actual:" + xhr.responseText)
			  var js = JSON.parse(xhr.responseText);
			  var err = js["response"];
			  alert (err);
    	 }
    } else {
      processCreateResponse("N/A");
    }
  };
}