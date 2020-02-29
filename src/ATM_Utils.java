import java.util.concurrent.TimeUnit;					// for time delays
import atmExceptions.InsufficientBalanceException;
import atmExceptions.InvalidOption;
import atmExceptions.InvalidPin;
import atmExceptions.InvalidInput;
import atmExceptions.WithdrawalLimit;
import atmExceptions.SessionTimeout;
import atmExceptions.MaxPromptsReached;

public class ATM_Utils {
	// private data members
	private int passwd;								// internal pwd in db
	private double balance;				           // assume initial balance
	
	// declare final variable
	private final double LIMIT;			          // minimum balance to withdraw
	private final int seconds_to_sleep;		   	// sleep for 3 mins max
	private final int seconds_to_process;
	
	private boolean isPwd_valid;
	private boolean isSessionOver;
	private int seconds_per_session;		        // delay time for response
	private int pin_input_prompts;                  // prompts for pin retry
	

	// member methods
	public ATM_Utils()
	{
		// constructor, initialize
		this.passwd              = 12345;
		this.balance             = 500.00;
		this.LIMIT               = 50.00;
		this.seconds_to_sleep    = 180;
		this.seconds_to_process  = 2;
		this.isPwd_valid         = false;
		this.isSessionOver       = false;
		this.seconds_per_session = 5;		          
		this.pin_input_prompts   = 3;
			
	}
	
	public boolean getPwdStatus()
	{
		return this.isPwd_valid;
	}
	
	public boolean checkTimer()
	{
		// check session timeout
		
		return this.isSessionOver;
	}

	public void checkPrompts() throws MaxPromptsReached
	{
		if(getRemainingPrompts() == 0)
		{
			// max prompts reached
			throw new MaxPromptsReached("You have reached your maximum pin retries. Please contact your branch for assistance!");
		}
		
		else if(getRemainingPrompts() > 0 && this.isPwd_valid == false)
		{
			System.out.println("- You have '" + getRemainingPrompts() + "' retries left!");
			//this.pin_input_prompts = this.pin_input_prompts - 1;
		}
	}
	
	public void check_pwd(int card_pwd) throws InvalidPin 
	{
		// valid user pwd
		if(passwd == card_pwd)
		{
			this.isPwd_valid = true;
		}
		
		else
		{
			// decrement prompts
			this.pin_input_prompts = this.pin_input_prompts - 1;
			
			throw new InvalidPin("You have entered an incorrect card pin. Please try again!");
		}
		
	}
	
	private void checkSessionOver() throws SessionTimeout
	{
		// count delay of time
		if(seconds_per_session == 0)
		{
			// session timeout
			this.isSessionOver = true;
			throw new SessionTimeout("Session Timeout!");
		}
		
		// decrement timer
		this.seconds_per_session--;
		
	}
	
	private void simulateProcess(String process_name)
	{
		// simulate a random delay of processes
		int delay = (int)(Math.random() * 6 + 2);
		
		try {
			System.out.println("- Please wait while processing '" + process_name + "'...");
			TimeUnit.SECONDS.sleep(delay);
		} 
		
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("- Internal error!. Error: " + e.getMessage());
		}
	}
	
	public boolean checkCard()
	{
		// simulate valid and invalid inserted or swiped cards
		boolean is_card_valid = true;
		
		simulateProcess("Bank Card");
		
		int x = (int)(Math.random() * 6 + 1);
		
		if(x >= 3)
		{
			// assume card is invalid
			//is_card_valid = false;
			return is_card_valid;
		}
		
		return is_card_valid;
	}
	
	public int getRemainingPrompts()
	{
		// get the now prompts
		return this.pin_input_prompts;
	}
	
	public void transfer(double balance, String acc_number, int pwd) throws InsufficientBalanceException
	{
		// transfer money
		boolean check_balance = this.balance > balance;
		
		try
		{
			check_pwd(pwd);
			
			if(check_balance && acc_number.isEmpty() == false)
			{
				// can transfer
				simulateProcess("Funds Transfer");
				this.balance -= balance;
				System.out.println("- Funds Transfer successful!");
				System.out.println("- Transfer to account number: " + acc_number + "success!");
				System.out.println("- Transfer: $" + balance + ". Remaining Balance: $" + this.balance);
				
			}
			
			else if(check_balance == false)
			{
				// insufficient funds
				throw new InsufficientBalanceException("Insufficient funds for Funds Transfer!");
			}
			
			else if(acc_number.isEmpty())
			{
				System.out.print("Account number for transfer is needed!");
			}
		}
		
		catch(InvalidPin ex)
		{
			System.out.println("- There was a problem. " + ex.getMessage());
		}
		
	}
	
	public void balanceEnquiry(int pin)
	{

		try 
		{
			check_pwd(pin);
			
			checkSessionOver();
			
			// still within session
			simulateProcess("Balance Request");
			System.out.println("- Balance enquiry request complete");
			System.out.println("\n- Current Account Balance = $" + balance);

		} 
		
		catch (InvalidPin e) 
		{
			// TODO Auto-generated catch block
			System.out.println("- There was a problem. " + e.getMessage());
		}
		
		catch (SessionTimeout e)
		{
			System.out.println(e.getMessage());
		}
	
	}
	
	public void withdraw(int pwd, double amount) throws WithdrawalLimit, InsufficientBalanceException
	{
		// is valid pwd
		try 
		{
			check_pwd(pwd);
			
			checkSessionOver();
			
			if(amount > 0 && balance - amount >= LIMIT)
			{
				// can withdraw
				balance -= amount;
				
				simulateProcess("Withdrawal");
				
				System.out.println("- Withdrawal of $" + amount + " accepted!");
				System.out.println("- Withdrawal successful!");
				System.out.println("- Remaining account balance: $" + balance);
			}
			
			else if(amount < balance)
			{
				throw new InsufficientBalanceException("Insufficient Account balance for withdrawal. Account balance: $" + balance + " . Withdrawal request: $" + amount);
				
			}
			
			else
			{
				// limit reached
				throw new WithdrawalLimit("Card withdrawal limit reached!");
			}
			
		} 
		
		catch (InvalidPin e) 
		{
			// TODO Auto-generated catch block
			System.out.println("- Bank card: " + e.getMessage());
		}
		
		catch (SessionTimeout ex)
		{
			System.out.println(ex.getMessage());
		
		}
	}
	
	public void displayOptions()
	{
		// ATM Menu options
		System.out.println("ATM Options. Select your choice using the numbers");
		System.out.println("1. Withdraw");
		System.out.println("2. Transfer");
		System.out.println("3. Balance Enquiry");
		System.out.println();
		System.out.println("0. Exit");
	}
	
}
