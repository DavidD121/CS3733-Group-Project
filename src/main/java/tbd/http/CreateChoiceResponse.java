package tbd.http;

/** Arbitrary decision to make this a String and not a native double. */
public class CreateChoiceResponse {
	public String uuid;
	
	public CreateChoiceResponse (String uuid) {
		this.uuid = uuid; // will be -1 if fail
	}


	public String toString() {
		return uuid;
	}
}
