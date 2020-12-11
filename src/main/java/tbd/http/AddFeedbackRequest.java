package tbd.http;

public class AddFeedbackRequest {
	int alternative;
	String uuid;
	String userID;
	String feedback;

	public String getuuid() { return uuid; }
	public void setuuid(String uuid) { this.uuid = uuid; }
	
	public int getalternative() { return alternative; }
	public void setalternative(int alternative) { this.alternative = alternative; }
	
	public String getfeedback() { return feedback; }
	public void setfeedback(String feedback) { this.feedback = feedback; }
	
	public String getuserID() { return userID; }
	public void setuserID(String userID) { this.userID = userID; }
	
	public String toString() {
		return "(CHOICE) - Name: " + alternative + " " + uuid + " " + feedback + " " + userID;
	}
	
	public AddFeedbackRequest(int alternative, String uuid, String feedback, String userID) {
		this.uuid = uuid;
		this.alternative = alternative;
		this.userID = userID;
		this.feedback = feedback;
	}
	
	public AddFeedbackRequest() {
	}
}
