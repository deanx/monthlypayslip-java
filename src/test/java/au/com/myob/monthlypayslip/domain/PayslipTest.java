package au.com.myob.monthlypayslip.domain;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PayslipTest {
	
	@Test(dataProvider = "completeCalculation")
	public void shouldCalculate(Integer annualSalary, Integer expectedGrossIncome, Integer expectedIncomeTax, Integer expectedNetIncome) {

		Payslip payslip = new Payslip();
		payslip.setAnnualSalary(annualSalary);
		payslip.calculate();
		assertEquals(payslip.getGrossIncome(), expectedGrossIncome);
		assertEquals(payslip.getIncomeTax(), expectedIncomeTax);
		assertEquals(payslip.getNetIncome(), expectedNetIncome);
	}
		
	@Test(dataProvider = "grossIncomeCalculation")
	public void shouldCalculateGrossIncomeCorrectly(Integer annualSalary, Integer expectedGrossIncome) {

		Payslip payslip = new Payslip();
		payslip.setAnnualSalary(annualSalary);
		Integer calculatedGrossIncome = payslip.calculateGrossIncome();

		assertEquals(calculatedGrossIncome, expectedGrossIncome);
	}
	
	@Test(dataProvider = "incomeTaxCalculation")
	public void shouldCalculateIncomeTaxCorrectly(Integer annualSalary, Integer expectedIncomeTax) {

		Payslip payslip = new Payslip();
		payslip.setAnnualSalary(annualSalary);
		Integer calculatedIncomeTax = payslip.calculateIncomeTax();

		assertEquals(calculatedIncomeTax, expectedIncomeTax);
	}
	
	@Test(dataProvider = "netIncomeCalculation")
	public void shouldCalculateNetIncomeCorrectly(Integer annualSalary, Integer expectedNetIncome) {

		Payslip payslip = new Payslip();
		payslip.setAnnualSalary(annualSalary);
		Integer calculatedIncomeTax = payslip.calculateNetIncome();

		assertEquals(calculatedIncomeTax, expectedNetIncome);
	}

	/**
	 * Data providers
	 */
	
	@DataProvider(name = "completeCalculation")
	public Object[][] provideComplete() {
		return new Object[][] {
			{ Integer.valueOf(1200), Integer.valueOf(100), Integer.valueOf(0), Integer.valueOf(100) },  
			{ Integer.valueOf(60050), Integer.valueOf(5004), Integer.valueOf(922), Integer.valueOf(4082) },
			{ Integer.valueOf(120000), Integer.valueOf(10000), Integer.valueOf(2696), Integer.valueOf(7304) },
		};		
	}
	
	@DataProvider(name = "grossIncomeCalculation")
	public Object[][] provideGrossIncome() {
		return new Object[][] {
			{ Integer.valueOf(120000), Integer.valueOf(10000) }, // 10,000
			{ Integer.valueOf(60050), Integer.valueOf(5004) }, // 5,004.1666
			{ Integer.valueOf(60054), Integer.valueOf(5005) }, // 5,005.5
			{ Integer.valueOf(60057), Integer.valueOf(5005) } // 5,004.75
		};		
	}
	
	@DataProvider(name = "incomeTaxCalculation")
	public Object[][] provideIncomeTax() {
		return new Object[][] {
			{ Integer.valueOf(100), Integer.valueOf(0) },  
			{ Integer.valueOf(60050), Integer.valueOf(922) },
			{ Integer.valueOf(120000), Integer.valueOf(2696) },
			{ Integer.valueOf(180000), Integer.valueOf(4546) },
		};		
	}
	
	@DataProvider(name = "netIncomeCalculation")
	public Object[][] provideNetIncome() {
		return new Object[][] {
			{ Integer.valueOf(1200), Integer.valueOf(100) },  
			{ Integer.valueOf(60050), Integer.valueOf(4082) },
			{ Integer.valueOf(120000), Integer.valueOf(7304) },
		};		
	}
}
