package au.com.myob.monthlypayslip;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import au.com.myob.monthlypayslip.domain.Payslip;
import au.com.myob.monthlypayslip.domain.PayslipCSV;

/**
 * Hello world!
 *
 */
public class App {

	static final String OUTPUT_CSV_FILENAME = "output.csv";
	
	private PayslipCSV payslipCSV = new PayslipCSV();
	
    public static void main( String[] args )
    {
    	String response = new App().execute(args);
    	System.out.println(response);
    }

	String execute(String[] args) {
		
		if (args == null || args.length != 1 || StringUtils.isEmpty(args[0])) {
    		return "Usage: java -jar MontlypaySlip.jar <CSV_INPUT_FILE>";
		}
		
		List<Payslip> payslips = payslipCSV.read(args[0]);
		
		payslips.forEach(p -> p.calculate());
		
		payslipCSV.write(payslips, OUTPUT_CSV_FILENAME);
		
		return OUTPUT_CSV_FILENAME + " file generated";
	}
}
