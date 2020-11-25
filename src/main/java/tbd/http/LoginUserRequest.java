package tbd.http;

public class LoginUserRequest {

	String choiceUUID;
	String name;
	String password;
	
	public LoginUserRequest(String c, String n, String p) {
		this.choiceUUID = c;
		this.name = n;
		this.password = p;
	}
	
	public LoginUserRequest() {
	}
	
	public String getChoiceUUID() { return this.choiceUUID; }	
	public void setChoiceUUID(String n) { this.choiceUUID = n; }
	
	public String getName() { return this.name; }
	public void setName(String n) { this.name = n; }
	
	public String getPassword() { return this.password; }
	public void setPassword(String p) { this.password = p; }
	
	public String toString() {
		return "(User request) - choiceUUID: " + choiceUUID + "   name: " + name + "   password: " + password;
	}
}
