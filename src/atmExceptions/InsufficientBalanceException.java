package atmExceptions;

public class InsufficientBalanceException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// not enough funds Exception
	public InsufficientBalanceException(String message)
	{
		// pass exception message to super class
		super(message);
	}

}
