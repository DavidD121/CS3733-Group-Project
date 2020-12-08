package tbd;

import java.io.IOException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import edu.wpi.cs.heineman.demo.TestContext;
import tbd.db.ConstantsDAO;
import tbd.http.LoginUserRequest;
import tbd.http.LoginUserResponse;
import tbd.model.User;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class UserLoginTest {

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
    public void testUserLogin() {
        UserLogin handler = new UserLogin();
        Context ctx = createContext();

        LoginUserRequest request = new LoginUserRequest("f3f69", "test", "");
        LoginUserResponse output = handler.handleRequest(request, ctx);

        // TODO: validate output here if needed.
        Assert.assertEquals(200, output.statusCode);
        Assert.assertEquals("1f4c8", output.result);
    }
    
    @Test
    public void testWrongPasswordUserLogin() {
    	UserLogin handler = new UserLogin();
        Context ctx = createContext();

        LoginUserRequest request = new LoginUserRequest("c7d7c", "renojdnasjn", "buh");
        LoginUserResponse output = handler.handleRequest(request, ctx);

        // TODO: validate output here if needed.
        Assert.assertEquals(400, output.statusCode);
    }
    
    @Test
    public void testNonExistingUserLogin() {
    	UserLogin handler = new UserLogin();
        Context ctx = createContext();

        LoginUserRequest request = new LoginUserRequest("f3f69", "jeremy", "");
        LoginUserResponse output = handler.handleRequest(request, ctx);

        // TODO: validate output here if needed.
        Assert.assertEquals(400, output.statusCode);
    }
}
