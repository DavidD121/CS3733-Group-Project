package tbd.http;

public class ApproveAlternativeRequest {
	String choiceID;
	int index;
	
	public ApproveAlternativeRequest(String c, int i) {
		this.choiceID = c;
		this.index = i;
	}
	
	public ApproveAlternativeRequest() {
	}
	
	public String getChoiceID() { return this.choiceID; }	
	public void setChoiceID(String n) { this.choiceID = n; }
	
	public int getIndex() { return this.index; }
	public void setIndex(int n) { this.index = n; }
	
	public String toString() {
		return "(Approve Alternative request) - choiceUUID: " + choiceID + "   index: " + index;
	}
}
