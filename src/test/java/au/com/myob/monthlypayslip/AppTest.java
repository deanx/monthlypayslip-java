package au.com.myob.monthlypayslip;

import java.io.IOException;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

import au.com.myob.monthlypayslip.App;

/**
 * Unit test for simple App.
 */
public class AppTest {
	
	@Test
	public void shouldReturnSuccess() throws IOException {
		
		String response = new App().execute();
		
		assertEquals(response, "Hello World!");
	}
}
