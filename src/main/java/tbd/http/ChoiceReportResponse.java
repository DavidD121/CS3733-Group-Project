package tbd.http;

import java.util.ArrayList;
import java.util.List;

import tbd.model.Choice;

public class ChoiceReportResponse {
	public final List<Choice> choiceList;
	public final int statusCode;
	public final String error;
	
	public ChoiceReportResponse (List<Choice> list, int code) {
		this.choiceList = list;
		this.statusCode = code;
		this.error = "";
	}
	
	public ChoiceReportResponse (int code, String errorMessage) {
		this.choiceList = new ArrayList<Choice>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (choiceList == null) { return "EmptyConstants"; }
		return "AllConstants(" + choiceList.size() + ")";
	}
}
