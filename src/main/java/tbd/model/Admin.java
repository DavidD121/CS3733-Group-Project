package tbd.model;

public class Admin {


	String name;
	String password;
	
	public Admin(String n, String p) {
		this.name = n;
		this.password = p;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getPassword() {
		return this.password;
	}
}
