package tbd;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import tbd.db.ChoiceDAO;
import tbd.http.AdminLoginRequest;
import tbd.http.DeleteOldChoicesRequest;
import tbd.http.GenericResponse;

public class DeleteOldChoices implements RequestHandler<DeleteOldChoicesRequest, GenericResponse> {

	LambdaLogger logger;

	void deleteChoices(float days) throws Exception{
		try {
			ChoiceDAO dao = new ChoiceDAO();
			dao.deleteChoices(days);
		} catch (Exception e) {
			System.out.println("deleteChoices Failed");
		}
	}
	
    @Override
    public GenericResponse handleRequest(DeleteOldChoicesRequest req, Context context) {
    	
    	DeleteOldChoicesRequest req1 = req;
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of RequestHandler");
		logger.log(req1.toString());
		boolean fail = false;
		String failMessage = "";
		
		try {
			deleteChoices(req.getDays());
		} catch (Exception e) {
			fail = true;
			failMessage = "failed to read database"; 
		}
    	
    	GenericResponse response;
		if (fail) {
			response = new GenericResponse(400, failMessage);
		} else {
			response = new GenericResponse(200);  // success
		}

		System.out.println(response);
		//System.out.println(fail);
		return response; 
    }

}
