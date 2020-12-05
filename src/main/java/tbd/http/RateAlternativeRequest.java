package tbd.http;

public class RateAlternativeRequest {
	int alternative;
	String uuid;
	String userID;
	String type;

	public String getuuid() { return uuid; }
	public void setuuid(String uuid) { this.uuid = uuid; }
	
	public int getalternative() { return alternative; }
	public void setalternative(int alternative) { this.alternative = alternative; }
	
	public String gettype() { return type; }
	public void settype(String type) { this.type = type; }
	
	public String getuserID() { return userID; }
	public void setuserID(String userID) { this.userID = userID; }
	
	public String toString() {
		return "(CHOICE) - Name: " + alternative + " " + uuid + " " + type + " " + userID;
	}
	
	public RateAlternativeRequest(int alternative, String uuid, String type, String userID) {
		this.uuid = uuid;
		this.alternative = alternative;
		this.userID = userID;
	}
	
	public RateAlternativeRequest() {
	}
}
