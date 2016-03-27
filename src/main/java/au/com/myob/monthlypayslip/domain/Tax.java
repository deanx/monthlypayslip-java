package au.com.myob.monthlypayslip.domain;

public class Tax {

	private Integer incomeFrom;
	private Integer incomeTo;
	private Integer start;
	private Float multiple;
	private Integer over;
	
	public Tax(Integer incomeFrom, Integer incomeTo, Integer start, Float multiple, Integer over) {
		
		this.incomeFrom = incomeFrom;
		this.incomeTo = incomeTo;
		this.start = start;
		this.multiple = multiple;
		this.over = over;
	}

	public Integer getIncomeFrom() {
		return incomeFrom;
	}

	public void setIncomeFrom(Integer incomeFrom) {
		this.incomeFrom = incomeFrom;
	}

	public Integer getIncomeTo() {
		return incomeTo;
	}

	public void setIncomeTo(Integer incomeTo) {
		this.incomeTo = incomeTo;
	}

	public Integer getStart() {
		return start;
	}
	
	public void setStart(Integer start) {
		this.start = start;
	}
	
	public Float getMultiple() {
		return multiple;
	}
	
	public void setMultiple(Float multiple) {
		this.multiple = multiple;
	}
	
	public Integer getOver() {
		return over;
	}
	
	public void setOver(Integer over) {
		this.over = over;
	}

	@Override
	public String toString() {
		return "Tax [incomeFrom=" + incomeFrom + ", incomeTo=" + incomeTo + ", start=" + start + ", multiple="
				+ multiple + ", over=" + over + "]";
	}
	
}
