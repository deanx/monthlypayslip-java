package au.com.myob.monthlypayslip.domain;

import java.util.Arrays;
import java.util.List;

public class TaxTable {

	private static final TaxTable INSTANCE = new TaxTable();
	
	public static TaxTable getInstance() {
		return INSTANCE;
	}
	
	/*
	 * 0 - $18,200     			Nil
	 * $18,201 - $37,000		19c for each $1 over $18,200
     * $37,001 - $80,000		$3,572 plus 32.5c for each $1 over $37,000
     * $80,001 - $180,000		$17,547 plus 37c for each $1 over $80,000
     * $180,001 and over		$54,547 plus 45c for each $1 over $180,000
	 */
	final List<Tax> taxes = Arrays.asList(
			new Tax(18201, 	37000, 				0, 		0.19f, 	18200),
			new Tax(37001, 	80000, 				3572, 	0.325f, 	37000),
			new Tax(80001, 	180000, 			17547, 	0.37f, 	80000),
			new Tax(180001, Integer.MAX_VALUE, 	54547, 	0.45f, 	180000)
	);

	public List<Tax> getTaxes() {
		return taxes;
	}

	public Tax filterByAnnualSalary(Integer annualSalary) {
		
		return taxes
				.stream()
				.filter(t -> (annualSalary >= t.getIncomeFrom() && annualSalary <= t.getIncomeTo()) )
				.findFirst()
				.orElse(null);
	}
}