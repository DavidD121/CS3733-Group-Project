package tbd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import edu.wpi.cs.heineman.demo.TestContext;
import tbd.http.AddFeedbackRequest;
import tbd.http.AddFeedbackResponse;
import tbd.http.CreateChoiceRequest;
import tbd.http.CreateChoiceResponse;
import tbd.http.GetChoiceRateResponse;
import tbd.http.GetChoiceRequest;
import tbd.http.GetChoiceResponse;
import tbd.model.Alternative;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class GetChoiceTest {

    private static Object input;

    @BeforeClass
    public static void createInput() throws IOException {
        // TODO: set up your sample input object here.
        input = null;
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

        // TODO: customize your context here if needed.
        ctx.setFunctionName("Your Function Name");

        return ctx;
    }

    @Test
    public void testGetChoice() {
        GetChoice handler = new GetChoice();
        Context ctx = createContext();
        
        UUID uuid = UUID.randomUUID();
        String subuuid = uuid.toString().substring(0,5);

        GetChoiceRequest request = new GetChoiceRequest("3cdf9");
    	//public CreateChoiceRequest (String uuid, String description, int teamMembers, int isLocked, 
    	//		Alternative alternative1, Alternative alternative2, Alternative alternative3, Alternative alternative4, Alternative alternative5)
        GetChoiceResponse output = handler.handleRequest(request, ctx);

        Assert.assertEquals("TestCase", output.description);
        Assert.assertEquals(0, output.isLocked.intValue());
        Assert.assertEquals(2, output.teamMembers.intValue());
        
        Assert.assertEquals("Test1", output.alternative1.name);
        Assert.assertEquals(0, output.alternative1.likes.number);
        Assert.assertEquals(0, output.alternative1.likes.participants.size());
        Assert.assertEquals(0, output.alternative1.dislikes.number);
        Assert.assertEquals(0, output.alternative1.dislikes.participants.size());
        
        Assert.assertEquals("Test2", output.alternative2.name);
        Assert.assertEquals(0, output.alternative2.likes.number);
        Assert.assertEquals(0, output.alternative2.likes.participants.size());
        Assert.assertEquals(0, output.alternative2.dislikes.number);
        Assert.assertEquals(0, output.alternative2.dislikes.participants.size());
        
        Assert.assertEquals("Test3", output.alternative3.name);
        Assert.assertEquals(0, output.alternative3.likes.number);
        Assert.assertEquals(0, output.alternative3.likes.participants.size());
        Assert.assertEquals(0, output.alternative3.dislikes.number);
        Assert.assertEquals(0, output.alternative3.dislikes.participants.size());
        
        Assert.assertEquals("Test4", output.alternative4.name);
        Assert.assertEquals(0, output.alternative4.likes.number);
        Assert.assertEquals(0, output.alternative4.likes.participants.size());
        Assert.assertEquals(0, output.alternative4.dislikes.number);
        Assert.assertEquals(0, output.alternative4.dislikes.participants.size());

        Assert.assertEquals("Test5", output.alternative5.name);
        Assert.assertEquals(0, output.alternative5.likes.number);
        Assert.assertEquals(0, output.alternative5.likes.participants.size());
        Assert.assertEquals(0, output.alternative5.dislikes.number);
        Assert.assertEquals(0, output.alternative5.dislikes.participants.size());
    }
    

}
