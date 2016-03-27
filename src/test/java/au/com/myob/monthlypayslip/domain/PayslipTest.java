package au.com.myob.monthlypayslip.domain;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class PayslipTest {
	
	@Test
	public void shouldCalculateGrossIncome() {

		Payslip payslip = buildPayslip();
		payslip.calculate();
		assertNotNull(payslip.getGrossIncome());
	}
	
	@Test(dataProvider = "grossCalculation")
	public void shouldCalculateGrossIncomeCorrectly(Integer annualSalary, Integer expectedGrossIncome) {

		Payslip payslip = new Payslip();
		payslip.setAnnualSalary(annualSalary);
		Integer calculatedGrossIncome = payslip.calculateGrossIncome();

		assertEquals(calculatedGrossIncome, expectedGrossIncome);
	}

	/**
	 * Data providers
	 */
	
	@DataProvider(name = "grossCalculation")
	public Object[][] provide() {
		return new Object[][] {
			{ Integer.valueOf(120000), Integer.valueOf(10000) }, // 10,000
			{ Integer.valueOf(60050), Integer.valueOf(5004) }, // 5,004.1666
			{ Integer.valueOf(60054), Integer.valueOf(5005) }, // 5,005.5
			{ Integer.valueOf(60057), Integer.valueOf(5005) } // 5,004.75
		};		
	}
	
	/**
	 * Builders
	 */
	private static Payslip buildPayslip() {
		Payslip p = new Payslip();
		p.setFirstName("David");
		p.setLastName("Rudd");
		p.setAnnualSalary(60000);
		p.setSuperRate("9%");
		p.setDate("01 March – 31 March");
		return p;
	}
}