package au.com.myob.monthlypayslip.domain;

import org.apache.commons.lang3.StringUtils;

public class Payslip {

	private String firstName;
	
	private String lastName;
	
	private Integer annualSalary;
	
	private String superRate;
	
	private String date;

	private Integer grossIncome;

	private Integer incomeTax;

	private Integer netIncome;
	
	private Integer superTotal;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAnnualSalary() {
		return annualSalary;
	}

	public void setAnnualSalary(Integer annualSalary) {
		this.annualSalary = annualSalary;
	}

	public String getSuperRate() {
		return superRate;
	}

	public void setSuperRate(String superRate) {
		this.superRate = superRate;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getGrossIncome() {
		return grossIncome;
	}

	public void setGrossIncome(Integer grossIncome) {
		this.grossIncome = grossIncome;
	}
	
	public Integer getIncomeTax() {
		return incomeTax;
	}

	public void setIncomeTax(Integer incomeTax) {
		this.incomeTax = incomeTax;
	}

	public Integer getNetIncome() {
		return netIncome;
	}

	public void setNetIncome(Integer netIncome) {
		this.netIncome = netIncome;
	}

	
	public Integer getSuperTotal() {
		return superTotal;
	}

	public void setSuperTotal(Integer superTotal) {
		this.superTotal = superTotal;
	}

	public Float getSuperRateAsDecimal() {
		String stringSuperRate = StringUtils.substringBefore(this.superRate, "%");
		return StringUtils.isEmpty(stringSuperRate) ? 0f : Integer.valueOf(stringSuperRate) / 100f;
	}
	
	public void calculate() {
		this.grossIncome = calculateGrossIncome();
		this.incomeTax = calculateIncomeTax();
		this.netIncome = calculateNetIncome();
		this.superTotal = calculateSuper();
		
	}

	Integer calculateGrossIncome() {

		return Math.round(this.annualSalary / 12f);
	}
	
	Integer calculateIncomeTax() {

		Tax tax = TaxTable.getInstance().filterByAnnualSalary(this.annualSalary);
		return (tax == null) ? 0 : Math.round((tax.getStart() + (annualSalary - tax.getOver()) * tax.getMultiple()) / 12f);
	}
	
	Integer calculateNetIncome() {
		
		if (this.grossIncome == null) this.grossIncome = this.calculateGrossIncome();
		if (this.incomeTax == null) this.incomeTax = this.calculateIncomeTax();
		
		return this.grossIncome - this.incomeTax;
	}
	
	Integer calculateSuper() {
		
		if (this.grossIncome == null) this.grossIncome = this.calculateGrossIncome();

		return Math.round(this.grossIncome * getSuperRateAsDecimal());
	}

	@Override
	public String toString() {
		return "Payslip [firstName=" + firstName + ", lastName=" + lastName + ", annualSalary=" + annualSalary
				+ ", superRate=" + superRate + ", date=" + date + "]";
	}
}
