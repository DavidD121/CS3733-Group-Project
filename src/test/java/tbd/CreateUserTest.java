package tbd;

import java.io.IOException;
import java.util.UUID;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

import tbd.http.LoginUserRequest;
import tbd.http.LoginUserResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class CreateUserTest {

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
        CreateUser handler = new CreateUser();
        Context ctx = createContext();
        
        UUID uuid = UUID.randomUUID();
		String name = uuid.toString().substring(0, 10);
		
        LoginUserRequest request = new LoginUserRequest("c7d7c", name, "");
        LoginUserResponse output = handler.handleRequest(request, ctx);

        Assert.assertEquals(200, output.statusCode);
        Assert.assertEquals(5, output.result.length());
    }
    
    @Test
    public void testCreateExistingUser() {
        CreateUser handler = new CreateUser();
        Context ctx = createContext();
		
        LoginUserRequest request = new LoginUserRequest("f3f69", "test", "");
        LoginUserResponse output = handler.handleRequest(request, ctx);

        Assert.assertEquals(400, output.statusCode);
    }
}
