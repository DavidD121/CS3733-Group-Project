package tbd.http;

/** Arbitrary decision to make this a String and not a native double. */
public class AddResponse {
	public String result;
	public int statusCode;  // HTTP status code.
	public String error;
	
	public AddResponse (String response, int statusCode) {
		this.result = response; // doesn't matter since error
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public AddResponse (int statusCode, String errorMessage) {
		this.result = ""; // doesn't matter since error
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (statusCode / 100 == 2) {  // too cute?
			return "Result(" + result + ")";
		} else {
			return "ErrorResult(" + statusCode + ", err=" + error + ")";
		}
	}
}
