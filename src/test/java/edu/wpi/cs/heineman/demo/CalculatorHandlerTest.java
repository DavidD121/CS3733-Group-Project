package edu.wpi.cs.heineman.demo;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;
import com.tobedecided.http.AddChoiceResponse;
import com.tobedecided.http.AddRequest;
import com.tobedecided.http.AddResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class CalculatorHandlerTest {

	/**
	 * Helper method that creates a context that supports logging so you can test lambda functions
	 * in JUnit without worrying about the logger anymore.
	 * 
	 * @param apiCall      An arbitrary string to identify which API is being called.
	 * @return
	 */
	Context createContext(String apiCall) {
		TestContext ctx = new TestContext();
		ctx.setFunctionName(apiCall);
		return ctx;
	}

	void testInput(String incoming, String outgoing) throws IOException {
		CalculatorHandler handler = new CalculatorHandler();
		GetChoice getChoice = new GetChoice();

		AddRequest req = new Gson().fromJson(incoming, AddRequest.class);
		AddChoiceResponse response = getChoice.handleRequest(req, createContext("compute"));

		Assert.assertEquals(outgoing, "test");
		Assert.assertEquals(200, response.statusCode);
	}

	void testFailInput(String incoming, String outgoing) throws IOException {
		CalculatorHandler handler = new CalculatorHandler();
		GetChoice getChoice = new GetChoice();
		AddRequest req = new Gson().fromJson(incoming, AddRequest.class);

		AddChoiceResponse response = getChoice.handleRequest(req, createContext("compute"));

		Assert.assertEquals(400, response.statusCode);
	}

	@Test
	public void testCalculatorSimple() {
		String SAMPLE_INPUT_STRING = "";
		String RESULT = "36.0";

		try {
			testInput(SAMPLE_INPUT_STRING, RESULT);
		} catch (IOException ioe) {
			Assert.fail("Invalid:" + ioe.getMessage());
		}
	}

	@Test
	public void testCalculatorRDSconstant() {
		String SAMPLE_INPUT_STRING = "{\"arg1\": \"e\", \"arg2\": \"19\"}";
		
		SAMPLE_INPUT_STRING = "{\"choiceName\": \"2Ice Cream?\",\"choiceDescription\": \"2What is your favorite ice cream?\", \"maxUsers\": 5,\"isLocked\": 1,\"alternative1\": \"fmiowfChocolate\",\"alternative2\": \"Vfenkanilla\",\"alternative3\": \"fwjoCoffee\",\"alternative4\": \"fmeklwBanana\",\"alternative5\": \"feklPisstachio\"}";
		String RESULT = "21.7";

		try {
			testInput(SAMPLE_INPUT_STRING, RESULT);
			
		} catch (IOException ioe) {
			Assert.fail("Invalid:" + ioe.getMessage());
		}
		
	}
	
	@Test
	public void testCalculatorConstant() {
		String SAMPLE_INPUT_STRING = "{\"arg1\": \"pi\", \"arg2\": \"19\"}";
		String RESULT = "22.1415926";

		try {
			testInput(SAMPLE_INPUT_STRING, RESULT);
		} catch (IOException ioe) {
			Assert.fail("Invalid:" + ioe.getMessage());
		}
	}

	@Test
	public void testFailInput() {
		String SAMPLE_INPUT_STRING = "{\"arg1\": \"- GARBAGE -\", \"arg2\": \"10\"}";
		String RESULT = "";

		try {
			testFailInput(SAMPLE_INPUT_STRING, RESULT);
		} catch (IOException ioe) {
			Assert.fail("Invalid:" + ioe.getMessage());
		}
	}
}
