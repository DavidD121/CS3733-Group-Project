package tbd.http;

public class GenericResponse {

	public int statusCode;
	public String error;
	
	public GenericResponse(int statusCode) {
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public GenericResponse(int statusCode, String errorMessage) {
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
