import java.util.Scanner;


public class ReturnBookUI {

	public static enum UiState { INITIALISED, READY, INSPECTING, COMPLETED };//Changed the enum name based on the naming convention

	private ReturnBookControl control;//Changed the variable name to lowercase
	private Scanner input;
	private UiState state;//Changed the enum name and the variable name based on the naming convention

	
	public returnBookUI(ReturnBookControl control) {//Changed the method name based on the naming convention
		this.control = control;//Changed the variable name into lowercase
		input = new Scanner(System.in);
		state = UiState.INITIALISED;//Changed the enum name and the variable name based on the naming convention
		control.setUi(this);//Changed the method name based on the naming convention
	}


	public void run() {	//Changed the method name based on the naming convention
		output("Return Book Use Case UI\n");
		
		while (true) {
			
			switch (state) {//Changed the variable name to lowercase
			
			case INITIALISED:
				break;
				
			case READY:
				String bookStore = input("Scan Book (<enter> completes): ");//Changed the variable name to be more meaningful
				if (bookStore.length() == 0) {//Changed the variable name to be more meaningful
					control.Scanning_Complete();//Changed the variable name to lower case
				}
				else {
					try {
						int bookId = Integer.valueOf(bookStore).intValue();//Changed the variable name based on the naming convention
						control.bookScanned(bookId);//Changed the method name and the variable names based on the naming convention
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}					
				}
				break;				
				
			case INSPECTING:
				String ans = input("Is book damaged? (Y/N): ");
				boolean isDamaged = false;//Changed the variable name based on the namimg convention
				if (ans.toUpperCase().equals("Y")) {					
					isDamaged = true;//Changed the variable name based on the namimg convention
				}
				control.dischargeLoan(isDamaged);//Changed the variable name and method name based on the namimg convention
			
			case COMPLETED:
				output("Return processing complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("ReturnBookUI : unhandled state :" + state);	//Change the variable to lowercase		
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
	
			
	public void display(Object object) {
		output(object);
	}
	
	public void setState(UiState state) {//Changed the method name and enum name based on the namimg convention
		this.state = state;//Change the name of the variable based on the naming convention
	}

	
}
