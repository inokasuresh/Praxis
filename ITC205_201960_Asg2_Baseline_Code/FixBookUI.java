import java.util.Scanner;


public class FixBookUI {

	public static enum UiState { INITIALISED, READY, FIXING, COMPLETED };//Changed enum name UI_STATE to UiState   
	
	private FixBookControl control;//Changed CoNtRoL as control
	   
	private Scanner input;
	private UiState state;
	/* 1. Changed UI_STATE to UiState
	   2. Changed StAtE to state
	 */

	
	public FixBookUI(FixBookControl control) {
		this.control = control;//Changed CoNtRoL to control
		input = new Scanner(System.in);
		state = UiState.INITIALISED;
		/* 1. Changed StAtE to state
		   2. Changed UI_STATE to state UiState
		*/
		control.setUI(this);//changed Set_Ui method as setUI
	}


	public void setState(UI_STATE state) {
		/* 1. Changed Set_State to setState
		   2. Changed UI_STATE to UiState
		*/
		this.StAtE = state;//Changed StAtE as state
	}

	
	public void run() {//Changed method name RuN as run
		output("Fix Book Use Case UI\n");
		
		while (true) {
			
			switch (state) {//Changed variable name StAtE as state
			
			case READY:
				String bookStr = input("Scan Book (<enter> completes): ");//Changed variable name Book_STR as bookStr 
				if (bookStr.length() == 0) {//Changed variable name Book_STR as bookStr 
					control.SCannING_COMplete();//Changed CoNtRoL as control
				}
				else {
					try {
						int bookId = Integer.valueOf(bookStr).intValue();
						/* 1. Changed Book_ID as bookId
		                   2. Changed Book_STR as bookStr
		                */
						control.Book_scanned(Book_ID);
						/* 1. Changed CoNtRoL as control
		                   2. Changed Book_ID as bookId
		                */
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}
				}
				break;	
				
			case FIXING:
				String AnS = input("Fix Book? (Y/N) : ");
				boolean FiX = false;
				if (AnS.toUpperCase().equals("Y")) {
					FiX = true;
				}
				CoNtRoL.FIX_Book(FiX);
				break;
								
			case COMPLETED:
				output("Fixing process complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + StAtE);			
			
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
	
	
}
