package tbd.http;


public class AdminLoginResponse {
	public int statusCode;
	public String error;
	
	public AdminLoginResponse (int statusCode) {
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public AdminLoginResponse (int statusCode, String errorMessage) {
		this.statusCode = statusCode;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (statusCode / 100 == 2) {  // too cute?
			return "Result(200)";
		} else {
			return "ErrorResult(" + statusCode + ", err=" + error + ")";
		}
	}

}
