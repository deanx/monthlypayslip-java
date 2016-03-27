package au.com.myob.monthlypayslip;

import org.apache.commons.lang3.StringUtils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	String response = new App().execute(args);
    	System.out.println(response);
    }

	String execute(String[] args) {
		
		if (args == null || args.length != 1 || StringUtils.isEmpty(args[0])) {
    		return "Usage: java -jar MontlypaySlip.jar <CSV_INPUT_FILE>";
		}
		
		return "Hello World!";
	}
}
