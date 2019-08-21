import java.util.Scanner;


public class BorrowBookUI {
	
	public static enum UiState { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };//Changed enum UI_STATE as UiState

	private BorrowBookControl control;//Changed object CONTROL as control
	private Scanner input;
	private UI_STATE state;
	/*
	Changed enum UI_STATE as UiState
	changed variable StaTe as state
	*/

	
	public BorrowBookUI(BorrowBookControl control) {
		this.control = control;
		/*
		Changed variable CONTROL as control
		*/
		input = new Scanner(System.in);
		state = UiState.INITIALISED;
		/*
		Changed variable StaTe as state
		Changed enum UI_STATE as UiState
		*/
		control.setUI(this);//Changed method setUI as setUi
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}
	
			
	public void setState(UiState state) {
		/*
		Changed Set_State method as setState
		Changed enum UI_STATE as UiState
		Changed variable STATE as state
		*/
		this.state = STATE;
		/*
		Changed variable StaTe as state
		Changed variable STATE as state
		*/
	}

	
	public void run() {
		output("Borrow Book Use Case UI\n");
		
		while (true) {
			
			switch (StaTe) {//Changed variable StaTe as state			
			
			case CANCELLED:
				output("Borrowing Cancelled");
				return;

				
			case READY:
				String MEM_STR = input("Swipe member card (press <enter> to cancel): ");
				if (MEM_STR.length() == 0) {
					CONTROL.cancel();
					break;
				}
				try {
					int Member_ID = Integer.valueOf(MEM_STR).intValue();
					CONTROL.Swiped(Member_ID);
				}
				catch (NumberFormatException e) {
					output("Invalid Member Id");
				}
				break;

				
			case RESTRICTED:
				input("Press <any key> to cancel");
				CONTROL.cancel();
				break;
			
				
			case SCANNING:
				String Book_Str = input("Scan Book (<enter> completes): ");
				if (Book_Str.length() == 0) {
					CONTROL.Complete();
					break;
				}
				try {
					int BiD = Integer.valueOf(Book_Str).intValue();
					CONTROL.Scanned(BiD);
					
				} catch (NumberFormatException e) {
					output("Invalid Book Id");
				} 
				break;
					
				
			case FINALISING:
				String Ans = input("Commit loans? (Y/N): ");
				if (Ans.toUpperCase().equals("N")) {
					CONTROL.cancel();
					
				} else {
					CONTROL.Commit_LOans();
					input("Press <any key> to complete ");
				}
				break;
				
				
			case COMPLETED:
				output("Borrowing Completed");
				return;
	
				
			default:
				output("Unhandled state");
				throw new RuntimeException("BorrowBookUI : unhandled state :" + StaTe);			
			}
		}		
	}


	public void Display(Object object) {
		output(object);		
	}


}
