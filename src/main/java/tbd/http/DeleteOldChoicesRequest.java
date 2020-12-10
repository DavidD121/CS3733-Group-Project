package tbd.http;

public class DeleteOldChoicesRequest {

	float days;
	
	public DeleteOldChoicesRequest(float d) {
		this.days = d;
	}
	
	public DeleteOldChoicesRequest() {
	}
	
	public float getDays() { return this.days; }	
	public void setDays(float d) { this.days = d; }
	
	public String toString() {
		return "(Delete Old Choices request) - days: " + this.days;
	}

}
