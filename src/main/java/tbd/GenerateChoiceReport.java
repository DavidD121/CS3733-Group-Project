package tbd;

import java.util.List; 

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import tbd.db.AdminDAO;
import tbd.db.ChoiceDAO;
import tbd.http.ChoiceReportResponse;
import tbd.model.Admin;
import tbd.model.Choice;
import tbd.model.ChoiceReportChoice;

public class GenerateChoiceReport implements RequestHandler<Object, ChoiceReportResponse> {

	LambdaLogger logger;
	
	private List<ChoiceReportChoice> getChoices() throws Exception {
		try {
			if (logger != null) { logger.log("in getChoices"); }
			ChoiceDAO dao = new ChoiceDAO();
			System.out.println("You connected!");
			List<ChoiceReportChoice> choices = dao.getAllChoices();
			
			return choices;
		}
		catch (Exception e) {
			System.out.println("getChoices failed!");
			return null;
		}
	}

    @Override
    public ChoiceReportResponse handleRequest(Object input, Context context) {
		logger = context.getLogger();
		logger.log("Input: " + input);
        
		boolean fail = false;
		String failMessage = "";
		List<ChoiceReportChoice> choices = null;
		
		try {
			choices = getChoices();
		} catch (Exception e) {
			fail = true;
			failMessage = "Failed to read database!";
		}
				
		if(choices == null) {
			fail = true;
			failMessage = "Error getting choices";
		}

		ChoiceReportResponse response;
		
        if (fail) {
			response = new ChoiceReportResponse(400, failMessage);
		} else {
			response = new ChoiceReportResponse(choices, 200);  // success
		}
        
        return response;
    }

}
