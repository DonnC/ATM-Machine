package atmExceptions;

public class WithdrawalLimit extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4421073911051748956L;
	
	// notify user on funds limit for withdrawal
	public WithdrawalLimit(String message)
	{
		super(message);
	}

}
