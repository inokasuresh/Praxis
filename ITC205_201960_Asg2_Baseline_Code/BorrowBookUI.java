import java.util.Scanner;


public class BorrowBookUI {
	
	public static enum UiState { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };//Changed enum UI_STATE as UiState

	private BorrowBookControl control;//Changed object CONTROL as control
	private Scanner input;
	private UiState state;
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
		this.state = state;
		/*
		Changed variable StaTe as state
		Changed variable STATE as state
		*/
	}

	
	public void run() {
		output("Borrow Book Use Case UI\n");
		
		while (true) {
			
			switch (state) {//Changed variable StaTe as state			
			
			case CANCELLED:
				output("Borrowing Cancelled");
				return;

				
			case READY:
				String memberCard = input("Swipe member card (press <enter> to cancel): ");//Changed variable MEM_STR as memberCard
				if (memberCard.length() == 0) {//Changed variable MEM_STR as memberCard
					control.cancel();//Changed variable CONTROL as control
					break;
				}
				try {
					int memberId = Integer.valueOf(memberCard).intValue();
					/*
					Changed variable Member_ID as memberId
					Changed variable MEM_STR as memberCard
					*/
					control.swiped(memberId);
					/*
					Changed variable CONTROL as control
					Changed variable Member_ID as memberId
					Changed Swiped method as swiped
					*/
				}
				catch (NumberFormatException e) {
					output("Invalid Member Id");
				}
				break;

				
			case RESTRICTED:
				input("Press <any key> to cancel");
				control.cancel();//Changed variable CONTROL as control
				break;
			
				
			case SCANNING: 
				String bookStr = input("Scan Book (<enter> completes): ");//Changed variable Book_Str as bookStr
				if (bookStr.length() == 0) {//Changed variable Book_Str as bookStr
					control.Complete();//Changed variable CONTROL as control
					break;
				}
				try {
					int bid = Integer.valueOf(bookStr).intValue();
					/*
					Changed variable BiD as bid
					Changed variable Book_Str as bookStr
					*/
					control.scanned(bid);
					/*
					Changed variable CONTROL as control
					Changed variable BiD as bid
					Changed method Scanned as scanned
					*/
					
				} catch (NumberFormatException e) {
					output("Invalid Book Id");
				} 
				break;
					
				
			case FINALISING:
				String answer = input("Commit loans? (Y/N): ");//Changed variable Ans as answer
				if (answer.toUpperCase().equals("N")) {//Changed variable Ans as answer
					control.cancel();//Changed variable CONTROL as control
					
				} else {
					control.commitLoans();
					/*
					changed variable CONTROL as control
					changed Commit_LOans method as commitLoans
					*/
					input("Press <any key> to complete ");
				}
				break;
				
				
			case COMPLETED:
				output("Borrowing Completed");
				return;
	
				
			default:
				output("Unhandled state");
				throw new RuntimeException("BorrowBookUI : unhandled state :" + state);//Changed variable StaTe as state		
			}
		}		
	}


	public void Display(Object object) {//Changed Display method as display
		output(object);		
	}


}
