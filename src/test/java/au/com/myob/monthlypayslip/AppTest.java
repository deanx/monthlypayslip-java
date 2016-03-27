package au.com.myob.monthlypayslip;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest {
	
	@Test(dataProvider = "failedInputs")
	public void shouldReturnUsageMessage(String[] args) throws IOException {

		String response = new App().execute(args);
		assertEquals(response, "Usage: java -jar MontlypaySlip.jar <CSV_INPUT_FILE>");
	}
	
	@Test
	public void shouldReturnSuccess() throws IOException {
		
		String response = new App().execute(new String[]{ "MyInputCSV.csv" });
		assertEquals(response, "Hello World!");
	}
	
	/*
	 * Data providers
	 */
	
	@DataProvider(name = "failedInputs")
	public Object[][] provide() throws Exception {
		return new Object[][] {
				{ null },
				{ new String[] {} },
				{ new String[] { null } },
				{ new String[] { "" } },
				{ new String[] { "Param1", "Param2" } 
			}
		};
	}
}
