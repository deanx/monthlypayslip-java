package au.com.myob.monthlypayslip;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	String response = new App().execute();
    	System.out.println(response);
    }

	String execute() {
		
		return "Hello World!";
	}
}
