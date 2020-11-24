package tbd.http;

public class LoginUserRequest {

	String choiceID;
	String name;
	String password;
	
	public LoginUserRequest(String c, String n, String p) {
		this.choiceID = c;
		this.name = n;
		this.password = p;
	}
	
	public LoginUserRequest() {
	}
	
	public String getChoiceID() { return this.choiceID; }	
	public void setChoiceID(String n) { this.choiceID = n; }
	
	public String getName() { return this.name; }
	public void setName(String n) { this.name = n; }
	
	public String getPassword() { return this.password; }
	public void setPassword(String p) { this.password = p; }
	
	public String toString() {
		return "(User request) - choiceID: " + choiceID + "   name: " + name + "   password: " + password;
	}
}
