import java.util.Scanner;


public class PayFineUI { 


	public static enum uiState { INITIALISED, READY, PAYING, COMPLETED, CANCELLED }; // enum names change to be start with an uppercase letter and to be in CamelBack

	private PayFineControl control; //change variable name according to standerd
	private Scanner input;
	private uiState state; //change variable name according to standerd

	
	public PayFineUI(PayFineControl control) {//method name should be changed to camelCaseformat - Reviewed by Rasika (23/08/2019)
		this.control = control; //change variable name according to standerd
		input = new Scanner(System.in);
		state = uiState.INITIALISED;  //change variable name according to standerd
		control.setUI(this);
	}
	
	
	public void setState(uiState state) { //change the method name according to naming conversion
		this.state = state; //change variable name according to standerd
	}


	public void run() {//Method RuN should changed to lower case - Reviewed by Rasika (23/08/2019) 
		output("Pay Fine Use Case UI\n");
		
		while (true) {
			
			switch (state) { 
			
			case READY:
				String member = input("Swipe member card (press <enter> to cancel): "); //change the variable name into meaningfull
				if (member.length() == 0) { //change the variable name into meaningfull
					control.cancel();//change the variable name into meaningfull and method according to naming conversion
					break;
				}
				try {
					int memberID = Integer.valueOf(member).intValue(); //change the variable name according to naming conversion
					control.cardSwiped(memberID);  //change the variable name according to naming conversion
				}
				catch (NumberFormatException e) {
					output("Invalid memberId");
				}
				break;
				
			case PAYING:
				double amount = 0; //change the variable name according to naming conversion
				String amount = input("Enter amount (<Enter> cancels) : "); // change variable name to be meaningfull
				if (amount.length() == 0) {// change variable name to be meaningfull
					control.cancel(); //change the variable name according to naming conversion
					break;
				}
				try {
					amount = Double.valueOf(amount).doubleValue();//change the variable name according to naming conversion
				}
				catch (NumberFormatException e) {}
				if (amount <= 0) {//change the variable name according to naming conversion
					output("Amount must be positive");
					break;
				}
				control.payFine(amount); //change the variable name according to naming conversion and change method according to naming conversion
					/*
					Method PaY_FiNe should changed to payFine - Reviewed by Rasika (23/08/2019)
					*/
				break;
								
			case CANCELLED:
				output("Pay Fine process cancelled");
				return;
			
			case COMPLETED:
				output("Pay Fine process complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + state); //change the variable name according to naming conversion		
			
			}		
		}		
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}	
			

	public void DiSplAY(Object object) {//Method DiSplAY should changed as display - Reviewed by Rasika (23/08/2019)
		output(object);
	}


}
