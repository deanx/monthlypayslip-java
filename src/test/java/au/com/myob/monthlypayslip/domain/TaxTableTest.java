package au.com.myob.monthlypayslip.domain;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TaxTableTest {
	
	@Test(dataProvider = "taxByAnnualSalary")
	public void shouldFilterByAnnualSalary(Integer annualSalary, Tax expectedTax) {

		Tax tax = TaxTable.getInstance().filterByAnnualSalary(annualSalary);
		assertEquals(tax, expectedTax);
	}

	/**
	 * Data providers
	 */
	
	@DataProvider(name = "taxByAnnualSalary")
	public Object[][] provide() {
		return new Object[][] {
			{ Integer.valueOf(-10), null },
			{ Integer.valueOf(0), null },
			{ Integer.valueOf(10), null },
			{ Integer.valueOf(18200), null },
			{ Integer.valueOf(18201), TaxTable.getInstance().getTaxes().get(0) },
			{ Integer.valueOf(20000), TaxTable.getInstance().getTaxes().get(0) },
			{ Integer.valueOf(37000), TaxTable.getInstance().getTaxes().get(0) },
			{ Integer.valueOf(37001), TaxTable.getInstance().getTaxes().get(1) },
			{ Integer.valueOf(40000), TaxTable.getInstance().getTaxes().get(1) },
			{ Integer.valueOf(80000), TaxTable.getInstance().getTaxes().get(1) },
			{ Integer.valueOf(80001), TaxTable.getInstance().getTaxes().get(2) },
			{ Integer.valueOf(100000), TaxTable.getInstance().getTaxes().get(2) },
			{ Integer.valueOf(180000), TaxTable.getInstance().getTaxes().get(2) },
			{ Integer.valueOf(180001), TaxTable.getInstance().getTaxes().get(3) },
			{ Integer.valueOf(200000), TaxTable.getInstance().getTaxes().get(3) },
			{ Integer.valueOf(20000000), TaxTable.getInstance().getTaxes().get(3) },
			{ Integer.MAX_VALUE, TaxTable.getInstance().getTaxes().get(3) }
		};		
	}
}
