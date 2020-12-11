package tbd;

import java.sql.Timestamp;
import java.util.Scanner;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import tbd.http.GetChoiceResponse;
import tbd.http.GetChoiceRequest;
import tbd.http.AddFeedbackRequest;
import tbd.http.AddFeedbackResponse;
import tbd.http.CreateChoiceResponse;
import tbd.db.ChoiceDAO;
import tbd.db.FeedbackDAO;
import tbd.db.GetChoiceDAO;
import tbd.model.Choice;
import tbd.model.Constant;

public class AddFeedback implements RequestHandler<AddFeedbackRequest,AddFeedbackResponse> {

	LambdaLogger logger;
	
	private AmazonS3 s3 = null;
	
	
	Timestamp addFeedback(String choiceUUID, int alternativeIndex, String userID, String feedback) throws Exception{
		try {
		if (logger != null) { logger.log("in createChoice"); }
		FeedbackDAO dao = new FeedbackDAO();
		Timestamp t = dao.addFeedback(choiceUUID, alternativeIndex, userID, feedback);
		System.out.println("You connected!");
		//dao.addConstant(new Constant("internetblake", 12));

		return t;
		}
		catch (Exception e) {
			System.out.println("createChoice failed!");
			return null;
		}
		
	}
	
	
	/*
	 * 
	 * handleRequest is takes the JSON input (in this case the UUID of a choice), 
	 * and gets that choice from o the RDS and returns a successful JSON with the choice or a fail JSON with no choice.
	 *
	 *
	 */
	
	@Override
	public AddFeedbackResponse handleRequest(AddFeedbackRequest req, Context context) {
		AddFeedbackRequest req1 = req;
		//GetChoiceRequest req1 = new GetChoiceRequest("20", "30");
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of RequestHandler");
		logger.log(req1.toString());
		boolean fail = false;
		String failMessage = "";
		double val1 = 0.0;
		double val2 = 0.0;
		Choice choice = null;
		Timestamp t = new Timestamp(0);
		try {
			//loadValueFromRDS("e");
			//int a = getChoice(req1.getuuid());
			t = addFeedback(req1.getuuid(), req1.getalternative(), req1.getuserID(), req1.getfeedback());
		}
		catch (Exception e) {
			fail = true;
			failMessage = "Failed to read database!";
		}

		// compute proper response and return. Note that the status code is internal to the HTTP response
		// and has to be processed specifically by the client code.
		AddFeedbackResponse response;
		if (fail) {
			response = new AddFeedbackResponse(400);
		} else {
			response = new AddFeedbackResponse(200, t);  // success
		}

		return response; 
	}
}