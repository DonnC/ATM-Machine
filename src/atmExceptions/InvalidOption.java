package atmExceptions;

public class InvalidOption extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2601084000790329297L;

	// invalid option choice exception
	public InvalidOption(String message)
	{
		super(message);
	}

}
