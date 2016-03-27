package au.com.myob.monthlypayslip.domain;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

import au.com.myob.monthlypayslip.exception.FileParseException;
import au.com.myob.monthlypayslip.exception.FileWriteException;

public class PayslipCSV {

	private final String[] INPUT_FIELDS = new String[] {"firstName", "lastName", "annualSalary", "superRate", "date"};
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Payslip> read(String fileName) throws FileNotFoundException, FileParseException {
		
		try {
			
			ColumnPositionMappingStrategy<Payslip> strat = new ColumnPositionMappingStrategy<Payslip>();
			strat.setType(Payslip.class);
			strat.setColumnMapping(INPUT_FIELDS);

			return new CsvToBean().parse(strat, new CSVReader(new FileReader(fileName)));
			
		} catch (Exception e) {
			
			throw new FileParseException("Could not parse file " + fileName, e);
		}
	}

	public void write(List<Payslip> payslips, String outputCsvFilename) throws FileWriteException {
		
		try {

			CSVWriter writer = new CSVWriter(new FileWriter(outputCsvFilename));

			payslips.forEach(p -> {
				
				String[] columns = new String[] { 
						p.getFirstName() + " " + p.getLastName(), 
						p.getDate(),
						p.getGrossIncome().toString(),
						p.getIncomeTax().toString(),
						p.getNetIncome().toString(),
						p.getSuperTotal().toString()
					};
				
				writer.writeNext(columns);
			});

			writer.close();
			
		} catch (Exception e) {
		
			throw new FileWriteException("Could not write output file " + outputCsvFilename, e);
		}
		
	}

}
