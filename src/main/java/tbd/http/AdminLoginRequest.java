package tbd.http;

public class AdminLoginRequest {
	String name;
	String password;
	
	public AdminLoginRequest(String n, String p) {
		this.name = n;
		this.password = p;
	}
	
	public AdminLoginRequest() {
	}
	
	
	public String getName() { return this.name; }
	public void setName(String n) { this.name = n; }
	
	public String getPassword() { return this.password; }
	public void setPassword(String p) { this.password = p; }
	
	public String toString() {
		return "(Admin Login request)   name: " + name + "   password: " + password;
	}
}
