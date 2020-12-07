package tbd.model;

public class User {

	String uuid;
	String choiceUUID;
	String name;
	String password;

	public User(String u, String c, String n, String p) {
		this.uuid = u;
		this.choiceUUID = c;
		this.name = n;
		this.password = p;
	}
	
	public String getUUID() {
		return this.uuid;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getPassword() {
		return this.password;
	}
}
