package tbd.http;

public class RateAlternativeResponse {
	public int likeChange;
	public int dislikeChange;
	public int statusCode;
	
	public RateAlternativeResponse(int likeChange, int dislikeChange, int statusCode) {
		this.likeChange = likeChange;
		this.dislikeChange = dislikeChange;
		this.statusCode = statusCode;
	}
	
	public String toString() {
		return "" + likeChange + " " + dislikeChange;
	}
}
