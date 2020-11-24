package tbd.model;

public class User {

	String uuid;
	String choiceID;
	String name;
	String password;
	
	public User(String u, String c, String n) {
		this.uuid = u;
		this.choiceID = c;
		this.name = n;
		this.password = null;
	}

	public User(String u, String c, String n, String p) {
		this.uuid = u;
		this.choiceID = c;
		this.name = n;
		this.password = p;
	}
	
	public String getUUID() {
		return this.uuid;
	}
	
	public String getPassword() {
		return this.password;
	}
}
