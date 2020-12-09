package tbd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import edu.wpi.cs.heineman.demo.TestContext;
import tbd.http.CreateChoiceRequest;
import tbd.http.CreateChoiceResponse;
import tbd.model.Alternative;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class CreateChoiceTest {

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
    public void testCreateUser() {
        CreateChoice handler = new CreateChoice();
        Context ctx = createContext();
        
        UUID uuid = UUID.randomUUID();
        String subuuid = uuid.toString().substring(0,5);

        Alternative alternative1 = new Alternative("test1", subuuid, 0, 0, new ArrayList<String>(), "");
        Alternative alternative2 = new Alternative("test1", subuuid, 0, 0, new ArrayList<String>(), "");

        CreateChoiceRequest request = new CreateChoiceRequest(subuuid, "test choice", 2, 0, alternative1, alternative2);
    	//public CreateChoiceRequest (String uuid, String description, int teamMembers, int isLocked, 
    	//		Alternative alternative1, Alternative alternative2, Alternative alternative3, Alternative alternative4, Alternative alternative5)
        CreateChoiceResponse output = handler.handleRequest(request, ctx);

        Assert.assertEquals(5, output.uuid.length());
    }
    

}
