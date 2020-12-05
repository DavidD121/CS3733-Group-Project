package tbd.http;

import tbd.model.Alternative;

/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class CreateChoiceRequest {
	
	
	String description;
	int teamMembers;
	int isLocked;
	String alternative1;
	String alternative2;
	String alternative3;
	String alternative4;
	String alternative5;
	
	public String getdescription() { return description; }
	public void setdescription(String description) { this.description = description; }
	
	public int getteamMembers() { return teamMembers; }
	public void setteamMembers(int teamMembers) { this.teamMembers = teamMembers; }

	public int getisLocked() { return isLocked; }
	public void setisLocked(int isLocked) { this.isLocked = isLocked; }
	
	public String getalternative1() { return alternative1; }
	public void setalternative1(String alternative1) { this.alternative1 = alternative1; }
	
	public String getalternative2() { return alternative2; }
	public void setalternative2(String alternative2) { this.alternative2 = alternative2; }
	
	public String getalternative3() { return alternative3; }
	public void setalternative3(String alternative3) { this.alternative3 = alternative3; }
	
	public String getalternative4() { return alternative4; }
	public void setalternative4(String alternative4) { this.alternative4 = alternative4; }
	
	public String getalternative5() { return alternative5; }
	public void setalternative5(String alternative5) { this.alternative5 = alternative5; }

	public String toString() {
		return "choiceDescription: " + description + " - maxUsers: " + teamMembers + " - isLocked: " + isLocked +
				" - Alternative1: " + alternative1 + " - Alternative2: " + alternative2 + " - Alternative3: " + alternative3 +
				" - Alternative4: " + alternative4 + " - Alternative5: " + alternative5;
	}
	
	public CreateChoiceRequest (String uuid, String description, int teamMembers, int isLocked, 
			Alternative alternative1, Alternative alternative2, Alternative alternative3, Alternative alternative4, Alternative alternative5) {
		this.description = description;
		this.teamMembers = teamMembers;
		this.isLocked = isLocked;
		// Convert them all to JSON strings for returning the proper choice JSON format
		this.alternative1 = alternative1.toString();
		this.alternative2 = alternative2.toString();
		this.alternative3 = alternative3.toString();
		this.alternative4 = alternative4.toString();
		this.alternative5 = alternative5.toString();
	}
	
	public CreateChoiceRequest (String uuid, String description, int teamMembers, int isLocked, 
			Alternative alternative1, Alternative alternative2, Alternative alternative3, Alternative alternative4) {
		this.description = description;
		this.teamMembers = teamMembers;
		this.isLocked = isLocked;
		// Convert them all to JSON strings for returning the proper choice JSON format
		this.alternative1 = alternative1.toString();
		this.alternative2 = alternative2.toString();
		this.alternative3 = alternative3.toString();
		this.alternative4 = alternative4.toString();
		this.alternative5 = "";
	}
	
	public CreateChoiceRequest (String uuid, String description, int teamMembers, int isLocked, 
			Alternative alternative1, Alternative alternative2, Alternative alternative3) {
		this.description = description;
		this.teamMembers = teamMembers;
		this.isLocked = isLocked;
		// Convert them all to JSON strings for returning the proper choice JSON format
		this.alternative1 = alternative1.toString();
		this.alternative2 = alternative2.toString();
		this.alternative3 = alternative3.toString();
		this.alternative4 = "";
		this.alternative5 = "";

	}
	
	public CreateChoiceRequest (String uuid, String description, int teamMembers, int isLocked, 
			Alternative alternative1, Alternative alternative2) {
		this.description = description;
		this.teamMembers = teamMembers;
		this.isLocked = isLocked;
		// Convert them all to JSON strings for returning the proper choice JSON format
		this.alternative1 = alternative1.toString();
		this.alternative2 = alternative2.toString();
		this.alternative3 = "";
		this.alternative4 = "";
		this.alternative5 = "";
	}
	
	public CreateChoiceRequest() {
	}
}
