package atmExceptions;

public class InvalidPin extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6957034207531281347L;

	// user entered invalid card pin / incorrect one
	public InvalidPin(String message)
	{
		super(message);
	}

}
