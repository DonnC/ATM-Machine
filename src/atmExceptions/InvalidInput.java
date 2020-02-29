package atmExceptions;

public class InvalidInput extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2373619010173747594L;

	// user enters an invalid or unsupported choice
	public InvalidInput(String message)
	{
		super(message);
	}
}
