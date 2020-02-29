import java.util.InputMismatchException;
import java.util.Scanner;
import atmExceptions.*;

public class RunATM {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// show welcome message
		WelcomeScreen welcome = new WelcomeScreen();

		Scanner user_input = new Scanner(System.in);

		int user_choice, user_pwd = 0;
		double user_amount = 0;
		String acc_number;

		// create ATM obj
		ATM_Utils ATM = new ATM_Utils();

		ATM.displayOptions();

		boolean running = true;

		try
		{
			// avoid invalid options
			user_choice = user_input.nextInt();

			switch(user_choice)
			{
			case 1:
				System.out.println("\nATM funds withdrawal option.");
				System.out.println("INSERT / SWIPE your bank card!");
				System.out.println("Hint: press any key!");
				user_input.next();

				if(ATM.checkCard() && running)
				{
					// card is valid
					while(ATM.getPwdStatus() == false && ATM.getRemainingPrompts() >= 0)
					{
						System.out.println("\nPlease enter your card pin: ");

						try {
							user_pwd = user_input.nextInt();

							try {
								//System.out.println("Remaining = " + ATM.getRemainingPrompts());
								ATM.check_pwd(user_pwd);
								ATM.checkPrompts();
							}

							catch (InvalidPin ex) {
								System.out.println(ex.getMessage());
							}

							catch (MaxPromptsReached ex)
							{
								System.out.println(ex.getMessage());
								break;
							}

						  }

						 catch(InputMismatchException ex)
						 {
							System.out.println("Invalid input. Card pin must be numbers only!");
						 }

					}

					// check if conditions were met before proceed
					if(ATM.getPwdStatus() == false)
					{
						// not met, terminate
						break;
					}

					System.out.println("\nEnter the withdrawal amount: $");

					try
					{
						user_amount = user_input.nextDouble();

						ATM.withdraw(user_pwd, user_amount);
					}

					catch(InputMismatchException ex)
					{
						System.out.println("Invalid input. Withdrawal Amount must be numbers only!");
					}

					catch(WithdrawalLimit | InsufficientBalanceException ex)
					{
						System.out.println(ex.getMessage());
					}

				}

				else
				{
					// bank card rejected
					System.out.println("Invalid Card. Please contact your main branch for assistance!");
				}

				break;

			case 2:
				System.out.println("\nATM Funds Transfer option");
				System.out.println("INSERT / SWIPE your bank card!");
				System.out.println("Hint: Press any key!");

				user_input.next();

				running = true;
				if(ATM.checkCard() && running == true)
				{
					// card is valid
					while(ATM.getPwdStatus() == false && ATM.getRemainingPrompts() >= 0)
					{
						System.out.println("\nPlease enter your card pin: ");

						try {
							user_pwd = user_input.nextInt();

							try {
								//System.out.println("Remaining = " + ATM.getRemainingPrompts());
								ATM.check_pwd(user_pwd);
								ATM.checkPrompts();

							}

							catch (MaxPromptsReached ex)
							{
								System.out.println(ex.getMessage());
								break;
							}

							catch (InvalidPin ex) {
								System.out.println(ex.getMessage());
							}

						  }

						 catch(InputMismatchException ex)
						 {
							System.out.println("Invalid input. Card pin must be numbers only!");
						 }

					}

					// check if conditions were met before proceed
					if(ATM.getPwdStatus() == false)
					{
						// not met, terminate
						break;
					}

					try
					{
						System.out.println("\nInput Account number for funds transfer and amount");
						System.out.println("Account number to transfer to: ");
						acc_number = user_input.nextLine();
						System.out.println("Amount to transfer: $");
						user_amount = user_input.nextDouble();


						// transfer
						ATM.transfer(user_amount, acc_number, user_pwd);

					}

					catch(InputMismatchException ex)
					 {
						System.out.println("Recevied an invalid input!");
					 }

					catch (InsufficientBalanceException e) {
						System.out.println(e.getMessage());
					}

				}

				else
				{
					// bank card rejected
					System.out.println("\nInvalid Card. Please contact your main branch for assistance!");
				}

				break;

			case 3:
				System.out.println("\nATM Balance Enquiry option");
				System.out.println("INSERT / SWIPE your bank card!");
				System.out.println("Hint: Press any key!");

				user_input.next();

				running = true;
				if(ATM.checkCard() && running == true)
				{
					// card is valid
					while(ATM.getPwdStatus() == false && ATM.getRemainingPrompts() >= 0)
					{
						System.out.println("\nPlease enter your card pin: ");

						try {
							user_pwd = user_input.nextInt();

							try {
								//System.out.println("Remaining = " + ATM.getRemainingPrompts());
								ATM.check_pwd(user_pwd);
								ATM.checkPrompts();
							}

							catch (InvalidPin ex) {
								System.out.println(ex.getMessage());
							}

							catch (MaxPromptsReached ex)
							{
								System.out.println(ex.getMessage());
								break;
							}

						  }

						 catch(InputMismatchException ex)
						 {
							System.out.println("Invalid input. Card pin must be numbers only!");
						 }

					}

					if(ATM.getPwdStatus() == false)
					{
						// not met, terminate
						break;
					}

					try {

						ATM.balanceEnquiry(user_pwd);
					  }

					 catch(InputMismatchException ex)
					 {
						System.out.println("Invalid input. Card pin must be numbers only!");
					 }
				}

				else
				{
					// bank card rejected
					System.out.println("\nInvalid Card. Please contact your main branch for assistance!");
				}

				break;

			case 0:
				System.out.println("\nThank you for using the ATM service. Blessed day!");
				break;

			default:
				System.out.println("\nInvalid option selected");
				break;
			}
		}

		catch(InputMismatchException ex)
		{
			System.out.println("Invalid option. Please select using a number!");
		}

		finally
		{
			System.out.println("\n\nThank you for using our service. Call again!");
		}
	    // close scanner
		user_input.close();

	}

}
