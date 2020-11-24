package tbd.http;

/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class GetChoiceRequest {
	String uuid;

	public String getuuid() { return uuid; }
	public void setuuid(String uuid) { this.uuid = uuid; }
	
	public String toString() {
		return "(CHOICE) - Name: " + uuid;
	}
	
	public GetChoiceRequest (String uuid) {
		this.uuid = uuid;
	}
	
	public GetChoiceRequest() {
	}
}
