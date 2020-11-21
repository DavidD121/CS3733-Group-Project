package tbd;

import java.util.Scanner;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import tbd.http.AddChoiceResponse;
import tbd.http.GetChoiceRequest;
import tbd.http.AddResponse;
import tbd.db.ConstantsDAO;
import tbd.model.Choice;
import tbd.model.Constant;

public class GetChoice implements RequestHandler<GetChoiceRequest,AddChoiceResponse> {

	LambdaLogger logger;
	
	private AmazonS3 s3 = null;
	

	/** Load from RDS, if it exists
	 * 
	 * @throws Exception 
	 */
	double loadValueFromRDS(String arg) throws Exception {
		if (logger != null) { logger.log("in loadValue"); }
		ConstantsDAO dao = new ConstantsDAO();
		System.out.println("You connected!");
		Constant constant = dao.getConstant(arg);
		//dao.addConstant(new Constant("internetblake", 12));
		dao.addChoice(new Choice("whoiswho", "you decide", 1, 25, "", "", "", "", ""));
		return constant.value;
	}
	
	Choice getChoice(String choiceName) throws Exception{
		try {
		if (logger != null) { logger.log("in createChoice"); }
		ConstantsDAO dao = new ConstantsDAO();
		System.out.println("You connected!");
		//dao.addConstant(new Constant("internetblake", 12));
		Choice choice = dao.getChoice(choiceName);
		System.out.println(choice.description);
		System.out.println(choice.alternative1);
		System.out.println(choice.alternative2);
		System.out.println(choice.alternative3);
		System.out.println(choice.alternative4);
		System.out.println(choice.alternative5);

		return choice;
		}
		catch (Exception e) {
			System.out.println("createChoice failed!");
			return null;
		}
		
	}
	
	@Override
	public AddChoiceResponse handleRequest(GetChoiceRequest req, Context context) {
		GetChoiceRequest req1 = req;
		//GetChoiceRequest req1 = new GetChoiceRequest("20", "30");
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of RequestHandler");
		logger.log(req1.toString());
		boolean fail = false;
		String failMessage = "";
		double val1 = 0.0;
		double val2 = 0.0;
		Choice choice = null;
		try {
			//loadValueFromRDS("e");
			choice = getChoice(req1.getchoiceName());
		}
		catch (Exception e) {
			fail = true;
			failMessage = "Failed to read database!";
		}
		if(choice == null) {
			fail = true;
			failMessage = "Filed to read database";
		}
		// compute proper response and return. Note that the status code is internal to the HTTP response
		// and has to be processed specifically by the client code.
		AddChoiceResponse response;
		if (fail) {
			response = new AddChoiceResponse(400, failMessage);
		} else {
			response = new AddChoiceResponse(200, choice);  // success
		}

		System.out.println(response);
		return response; 
	}
}