package tbd.http;


public class LoginUserResponse {
	public String result;
	public int statusCode;
	public String error;
	
	public LoginUserResponse (String response, int statusCode) {
		this.result = response;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public LoginUserResponse (int statusCode, String errorMessage) {
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
