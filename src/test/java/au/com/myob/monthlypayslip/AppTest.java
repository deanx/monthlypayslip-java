package au.com.myob.monthlypayslip;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import au.com.myob.monthlypayslip.domain.Payslip;
import au.com.myob.monthlypayslip.domain.PayslipCSV;

/**
 * Unit test for simple App.
 */
public class AppTest {

	@Mock
	private final Payslip PAYSLIP1 = buildPayslip();
	@Mock
	private final Payslip PAYSLIP2 = buildPayslip();
	
	@Mock
 	private PayslipCSV payslipCSV;
	
	@InjectMocks
	private App app;
	
	@BeforeMethod
	public void tearUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test(dataProvider = "failedInputs")
	public void shouldReturnUsageMessage(String[] args) throws IOException {

		String response = app.execute(args);
		assertEquals(response, "Usage: java -jar MontlypaySlip.jar <CSV_INPUT_FILE>");
		verify(payslipCSV, times(0)).read(anyString());
	}
	
	@Test
	public void shouldReturnSuccess() throws IOException {
		
		String response = app.execute(new String[]{ "MyInputCSV.csv" });
		assertEquals(response, App.OUTPUT_CSV_FILENAME + " file generated");
		verify(payslipCSV, times(1)).read("MyInputCSV.csv");
	}
	
	@Test
	public void shouldReadCsv() throws IOException {
		
		app.execute(new String[]{ "MyInputCSV.csv" });
		verify(payslipCSV, times(1)).read("MyInputCSV.csv");
	}
	
	@Test
	public void shouldCalculateEachPayslip() throws IOException {
		
		List<Payslip> payslips = Arrays.asList(PAYSLIP1, PAYSLIP2);
		when(payslipCSV.read("MyInputCSV.csv")).thenReturn(payslips);
		
		app.execute(new String[]{ "MyInputCSV.csv" });
		verify(PAYSLIP1, times(1)).calculate();
		verify(PAYSLIP2, times(1)).calculate();
	}
	
	@Test
	public void shouldWriteCsv() throws IOException {
		
		List<Payslip> payslips = Arrays.asList(PAYSLIP1, PAYSLIP2);
		when(payslipCSV.read("MyInputCSV.csv")).thenReturn(payslips);
		
		app.execute(new String[]{ "MyInputCSV.csv" });
		verify(payslipCSV, times(1)).write(payslips, App.OUTPUT_CSV_FILENAME);
	}
	
	/**
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
	
	/**
	 * Builders
	 */
	private static Payslip buildPayslip() {
		return new Payslip();
	}
}
