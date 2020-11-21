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

import tbd.http.AddRequest;
import tbd.http.AddResponse;
import tbd.db.ConstantsDAO;
import tbd.model.Choice;
import tbd.model.Constant;

public class CreateChoice implements RequestHandler<AddRequest,AddResponse> {

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
	
	boolean createChoice(String choiceName, String choiceDescription, int maxUsers,
			String alternative1, String alternative2, String alternative3, String alternative4, String alternative5) throws Exception{
		try {
		if (logger != null) { logger.log("in createChoice"); }
		ConstantsDAO dao = new ConstantsDAO();
		System.out.println("You connected!");
		//dao.addConstant(new Constant("internetblake", 12));
		dao.addChoice(new Choice(choiceName, choiceDescription, 1, maxUsers, alternative1, alternative2, alternative3, alternative4, alternative5));

		return false;
		}
		catch (Exception e) {
			System.out.println("createChoice failed!");
			return true;
		}
		
	}
	
	@Override
	public AddResponse handleRequest(AddRequest req, Context context) {
		AddRequest req1 = req;
		//AddRequest req1 = new AddRequest("20", "30");
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of RequestHandler");
		logger.log(req1.toString());

		boolean fail = false;
		String failMessage = "";
		double val1 = 0.0;
		double val2 = 0.0;

		
		try {
			//loadValueFromRDS("e");
			fail = createChoice(req1.getchoiceName(), req1.getchoiceDescription(), req1.getmaxUsers(), req1.getalternative1(), req1.getalternative2(),
					req1.getalternative3(), req1.getalternative4(), req1.getalternative5());
		}
		catch (Exception e) {
			fail = true;
			failMessage = "Failed to add choice!";
		}

		// compute proper response and return. Note that the status code is internal to the HTTP response
		// and has to be processed specifically by the client code.
		AddResponse response;
		if (fail) {
			response = new AddResponse(400, failMessage);
		} else {
			response = new AddResponse("Success", 200);  // success
		}

		return response; 
	}
}