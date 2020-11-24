// all access driven through BASE. Must end with a SLASH
// be sure you change to accommodate your specific API Gateway entry point
var base_url = "";

var choice_add_url = base_url + "Choice";   // POST
var choice_get_url = base_url + "Choice";    // GET with {ChoiceUUID}
var choice_complete_url = base_url + "Choice";    // POST with {ChoiceUUID} 