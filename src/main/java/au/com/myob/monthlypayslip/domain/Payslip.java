package au.com.myob.monthlypayslip.domain;

public class Payslip {

	private String firstName;
	
	private String lastName;
	
	private Integer annualSalary;
	
	private String superRate;
	
	private String date;

	private Integer grossIncome;
	
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

	public void calculate() {
		this.grossIncome = calculateGrossIncome();
		
	}

	Integer calculateGrossIncome() {

		return Math.round(this.annualSalary / 12f);
	}

	@Override
	public String toString() {
		return "Payslip [firstName=" + firstName + ", lastName=" + lastName + ", annualSalary=" + annualSalary
				+ ", superRate=" + superRate + ", date=" + date + "]";
	}
}
