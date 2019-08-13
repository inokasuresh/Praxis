import java.io.Serializable;


@SuppressWarnings("serial")
public class Book implements Serializable { //Change the class name based on the slandered 
	
	private String title; // Change the variable name based on the standard
	private String author; // Change the variable name based on the standard
	private String isbnNo; // change CALLNO as isbnNo 
	private int id; // Change the variable name based on the standard
	
	private enum State { AVAILABLE, ON_LOAN, DAMAGED, RESERVED }; //Change enum name base on the standard
	private State state; // Change the class name and variable name based on the standard
	
	
	public Book(String author, String title, String isbnNo, int id) { // change callNo as isbnNo 
		this.author = author; //Change the variable name based on the standard
		this.title = title; //Change the variable name based on the standard
		this.isbnNo = isbnNo;//Change the variable name based on the standard
		this.id = id; //Change the variable name based on the standard
		this.State = State.AVAILABLE;//Change enum name based on the standard
	}
	
	public String toString() { 
		StringBuilder sb = new StringBuilder();
		sb.append("Book: ").append(ID).append("\n")
		  .append("  Title:  ").append(TITLE).append("\n")
		  .append("  Author: ").append(AUTHOR).append("\n")
		  .append("  CallNo: ").append(CALLNO).append("\n")
		  .append("  State:  ").append(State);
		
		return sb.toString();
	}

	public int getId() {//Change the return type and getter method
		return id;//change the variable name
	}

	public String getTitle() { //Change the return type and getter method
		return title;//change the variable name
	}


	
	public boolean isAvailable() {//Change the return type and getter method
		return State == State.AVAILABLE;//Change the name of the enum 
	}

	
	public boolean On_loan() {// 
		return State == STATE.ON_LOAN;
	}

	
	public boolean IS_Damaged() {
		return State == STATE.DAMAGED;
	}

	
	public void Borrow() {
		if (State.equals(STATE.AVAILABLE)) {
			State = STATE.ON_LOAN;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", State));
		}
		
	}


	public void Return(boolean DAMAGED) {
		if (State.equals(STATE.ON_LOAN)) {
			if (DAMAGED) {
				State = STATE.DAMAGED;
			}
			else {
				State = STATE.AVAILABLE;
			}
		}
		else {
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", State));
		}		
	}

	
	public void Repair() {
		if (State.equals(STATE.DAMAGED)) {
			State = STATE.AVAILABLE;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", State));
		}
	}


}
