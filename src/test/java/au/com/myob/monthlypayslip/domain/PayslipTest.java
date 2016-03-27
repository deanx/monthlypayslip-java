package au.com.myob.monthlypayslip.domain;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PayslipTest {
	
	@Test(dataProvider = "superAsDecimal")
	public void shouldConvertSuperToDecimal(String superRate, Float expectedDecimalSuper) {

		Payslip payslip = new Payslip();
		payslip.setSuperRate(superRate);
		Float calculatedDecimalSuper = payslip.getSuperRateAsDecimal();
		assertEquals(calculatedDecimalSuper, expectedDecimalSuper);
	}
	
	@Test(dataProvider = "completeCalculation")
	public void shouldCalculate(Integer annualSalary, String superRate, Integer expectedGrossIncome, Integer expectedIncomeTax, Integer expectedNetIncome, Integer expecetedSuper) {

		Payslip payslip = new Payslip();
		payslip.setAnnualSalary(annualSalary);
		payslip.setSuperRate(superRate);
		payslip.calculate();
		assertEquals(payslip.getGrossIncome(), expectedGrossIncome);
		assertEquals(payslip.getIncomeTax(), expectedIncomeTax);
		assertEquals(payslip.getNetIncome(), expectedNetIncome);
		assertEquals(payslip.getSuperTotal(), expecetedSuper);
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
	
	@Test(dataProvider = "superCalculation")
	public void shouldCalculateSuperCorrectly(Integer annualSalary, String superRate, Integer expectedSuper) {

		Payslip payslip = new Payslip();
		payslip.setAnnualSalary(annualSalary);
		payslip.setSuperRate(superRate);
		Integer calculatedSuper = payslip.calculateSuper();

		assertEquals(calculatedSuper, expectedSuper);
	}

	/**
	 * Data providers
	 */
	
	@DataProvider(name = "superAsDecimal")
	public Object[][] provideSuperRate() {
		return new Object[][] {
			{ "9%", Float.valueOf(0.09f) },  
			{ "34%", Float.valueOf(0.34f) },  
			{ "50%", Float.valueOf(0.50f) },    
		};		
	}
	
	@DataProvider(name = "completeCalculation")
	public Object[][] provideComplete() {
		return new Object[][] {
			{ Integer.valueOf(1200), "1%", Integer.valueOf(100), Integer.valueOf(0), Integer.valueOf(100), Integer.valueOf(1) },  
			{ Integer.valueOf(60050), "9%", Integer.valueOf(5004), Integer.valueOf(922), Integer.valueOf(4082), Integer.valueOf(450) },
			{ Integer.valueOf(120000), "10%", Integer.valueOf(10000), Integer.valueOf(2696), Integer.valueOf(7304), Integer.valueOf(1000) },
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
	
	@DataProvider(name = "superCalculation")
	public Object[][] provideSuper() {
		return new Object[][] {
			{ Integer.valueOf(60050), "9%", Integer.valueOf(450) },  
			{ Integer.valueOf(120000), "10%", Integer.valueOf(1000) },
		};		
	}
}
