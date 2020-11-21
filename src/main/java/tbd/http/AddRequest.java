package tbd.http;

/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class AddRequest {
	String choiceName;
	String choiceDescription;
	int maxUsers;
	int isLocked;
	String alternative1;
	String alternative2;
	String alternative3;
	String alternative4;
	String alternative5;

	public String getchoiceName() { return choiceName; }
	public void setchoiceName(String choiceName) { this.choiceName = choiceName; }
	
	public String getchoiceDescription() { return choiceDescription; }
	public void setchoiceDescription(String choiceDescription) { this.choiceDescription = choiceDescription; }
	
	public int getmaxUsers() { return maxUsers; }
	public void setmaxUsers(int maxUsers) { this.maxUsers = maxUsers; }

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
		return "(CHOICE) - Name: " + choiceName + " - choiceDescription: " + choiceDescription + " - maxUsers: " + maxUsers + " - isLocked: " + isLocked +
				" - Alternative1: " + alternative1 + " - Alternative2: " + alternative2 + " - Alternative3: " + alternative3 +
				" - Alternative4: " + alternative4 + " - Alternative5: " + alternative5;
	}
	
	public AddRequest (String choiceName, String choiceDescription, int maxUsers, int isLocked, 
			String alternative1, String alternative2, String alternative3, String alternative4, String alternative5) {
		this.choiceName = choiceName;
		this.choiceDescription = choiceDescription;
		this.maxUsers = maxUsers;
		this.isLocked = isLocked;
		this.alternative1 = alternative1;
		this.alternative2 = alternative2;
		this.alternative3 = alternative3;
		this.alternative4 = alternative4;
		this.alternative5 = alternative5;
	}
	
	public AddRequest() {
	}
}
