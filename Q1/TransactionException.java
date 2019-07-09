public class TransactionException extends Exception {

	private final String errorCode;

	public TransactionException(String errorCode, String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}	
	
	
}
