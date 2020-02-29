package atmExceptions;

public class InvalidCard extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6744296624230549597L;

	// mimic invalid cards and swallowed cards at ATM
	public InvalidCard(String message)
	{
		super(message);
	}
}
