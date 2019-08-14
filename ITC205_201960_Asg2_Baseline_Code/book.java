import java.io.Serializable;


@SuppressWarnings("serial")
public class Book implements Serializable { //Change the class name based on the standard  
	
	private String title; // Change the variable name based on the standard
	private String author; // Change the variable name based on the standard
	private String isbnNo; // change CALLNO as isbnNo to be more meaningful 
	private int id; // Change the variable name based on the standard
	
	private enum State { AVAILABLE, ON_LOAN, DAMAGED, RESERVED }; //Change enum name base on the standard
	private State state; // Change the class name and variable name based on the standard
	
	
	public Book(String author, String title, String isbnNo, int id) { // change callNo as isbnNo 
		this.author = author; //Change the variable name based on the standard
		this.title = title; //Change the variable name based on the standard
		this.isbnNo = isbnNo;//Change the variable name based on the standard
		this.id = id; //Change the variable name based on the standard
		this.state = State.AVAILABLE;//Change enum and class variable based on the standard
	}
	
	public String toString() { 
		StringBuilder sb = new StringBuilder();
		sb.append("Book: ").append(id).append("\n")//Change the class variable in to lower case
		  .append("  Title:  ").append(title).append("\n")//Change the class variable in to lower case
		  .append("  Author: ").append(author).append("\n")//Change the class variable in to lower case
		  .append("  CallNo: ").append(isbnNo).append("\n")//chnage the class variable name to be more meaningful
		  .append("  State:  ").append(state);//Change the class variable in to lower case
		
		return sb.toString();
	}

	public int getId() {//Change the return type and getter method signature
		return id;//change the variable name in to lowercase
	}

	public String getTitle() { //Change the return type and getter method signature
		return title;//change the variable name to lowercase
	}


	
	public boolean isAvailable() {//Change the method name to be more meaningful
		return state == State.AVAILABLE;//Change the name of the enum and the class variable based on the standard
	}

	
	public boolean isOnLoan() {//Change the method name to be more meaningful
		return state == State.ON_LOAN;//Change the name of the enum and the class variable based on the standard
	}

	
	public boolean isDamaged() {//Change the method name to be more meaningful
		return State == State.DAMAGED;//Change the name of the enum and the class variable based on the standard
	}

	
	public void setBorrow() {//Change the method name to be more meaningful
		if (state.equals(State.AVAILABLE)) {//Change the name of the enum and the class variable based on the standard
			state = State.ON_LOAN;//Change the name of the enum and the class variable based on the standard
		}
		else {
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", state));//Change the class variable based on the standard
		}
		
	}


	public void setReturn(boolean DAMAGED) {//Change the method name to be more meaningful
		if (state.equals(State.ON_LOAN)) {//Change the name of the enum and the class variable into lower case
			if (DAMAGED) {
				state = State.DAMAGED; //Change the name of the enum and the class variable into lowercase
			}
			else {
				state = State.AVAILABLE;//Change the name of the enum and the class variable into lower case
			}
		}
		else {
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", state));//Change the class variable into lowercase
		}		
	}

	
	public void setRepair() { //Change the method name to be more meaningful
		if (state.equals(State.DAMAGED)) {//Change the name of the enum and the class variable into lowercase
			state = State.AVAILABLE;//Change the name of the enum and the class variable into lowercase
		}
		else {
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", state));//Change the class variable into lowercase
		}		
		}
	}


}
