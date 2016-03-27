package au.com.myob.monthlypayslip.exception;

public class FileUnparsableException extends Exception {

	private static final long serialVersionUID = -3945952909447101454L;
	
	public FileUnparsableException(String message, Exception e) {
		super(message, e);
	}
}
