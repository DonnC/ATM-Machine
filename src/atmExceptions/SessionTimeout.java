package atmExceptions;

public class SessionTimeout extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1742255956672905241L;
	
	// session timeout -- automatically request user to repeat ATM process
	public SessionTimeout(String message)
	{
		super(message);
	}
}
