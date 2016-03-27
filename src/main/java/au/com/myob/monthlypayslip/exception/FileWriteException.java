package au.com.myob.monthlypayslip.exception;

public class FileWriteException extends Exception {

	private static final long serialVersionUID = -3945952909447101454L;
	
	public FileWriteException(String message, Exception e) {
		super(message, e);
	}
}
