package tbd.http;

/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class GetChoiceRequest {
	String choiceName;

	public String getchoiceName() { return choiceName; }
	public void setchoiceName(String choiceName) { this.choiceName = choiceName; }
	
	public String toString() {
		return "(CHOICE) - Name: " + choiceName;
	}
	
	public GetChoiceRequest (String choiceName) {
		this.choiceName = choiceName;
	}
	
	public GetChoiceRequest() {
	}
}
