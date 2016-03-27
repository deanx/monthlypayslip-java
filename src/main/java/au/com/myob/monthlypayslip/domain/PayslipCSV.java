package au.com.myob.monthlypayslip.domain;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

import au.com.myob.monthlypayslip.exception.FileUnparsableException;

public class PayslipCSV {

	private final String[] INPUT_FIELDS = new String[] {"firstName", "lastName", "annualSalary", "superRate", "date"};
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Payslip> read(String fileName) throws FileNotFoundException, FileUnparsableException {
		
		try {

			ColumnPositionMappingStrategy<Payslip> strat = new ColumnPositionMappingStrategy<Payslip>();
			strat.setType(Payslip.class);
			strat.setColumnMapping(INPUT_FIELDS);

			return new CsvToBean().parse(strat, new CSVReader(new FileReader(fileName)));
			
		} catch (Exception e) {
			
			throw new FileUnparsableException("Could not parse file " + fileName, e);
		}
	}

	public void write(List<Payslip> payslips, String outputCsvFilename) {
		// TODO Auto-generated method stub
		
	}

}
