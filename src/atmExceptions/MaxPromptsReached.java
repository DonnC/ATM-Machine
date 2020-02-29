package atmExceptions;

public class MaxPromptsReached extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6405984021104376973L;
	
	// pin retry maximum prompts exception
	public MaxPromptsReached(String message)
	{
		super(message);
	}

}
