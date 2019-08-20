import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class loan implements Serializable { //Change the class name. It should start with uppercase
	
	public static enum LoanState { CURRENT, OVER_DUE, DISCHARGED };
	
	private int ID;
	private Book book; //change the class name according to the naming conversion and variable name into meaningfull
	private Member member; //change the class name according to the naming conversion and variable name into meaningfull
	private Date date; //change the class name according to the naming conversion and variable name into meaningfull
	private LoanState state; //change the class name according to naming conversion

	
	public loan(int loanId, book book, member member, Date dueDate) {
		this.ID = loanId;
		this.book = book; //Change variable into meaningfull
		this.member = member; //Change variable into meaningfull
		this.date = dueDate; //Change variable into meaningfull
		this.state = LoanState.CURRENT; //change the class name according to naming conversion
	}

	
	public void checkOverDue() {
		if (state == loanState.CURRENT && Calendar.INSTANCE().Date().after(D)) { //changed the variable name into camelCase
			this.state = LoanState.overDue;	//change the class name according to naming conversion and variable name into camelCase
		}
	}

	
	public boolean overDue() { //chane the method name according to naming conversion
		return state == LoanState.overDue; //change the class name according to naming conversion and variable name into camelCase
	}

	
	public Integer ID() {
		return ID;
	}


	public Date getDueDate() { //change the method name according to naming conversion
		return date; //change the variable name to be meaningfull
	}
	
	
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  //change variable name to be meaningfull

		StringBuilder sb = new StringBuilder();
		sb.append("Loan:  ").append(ID).append("\n")
		  .append("  Borrower ").append(M.getId()).append(" : ") //change the method name according to naming conversion
		  .append(M.Get_LastName()).append(", ").append(M.getFirstName()).append("\n") //change the method name according to naming conversion
		  .append("  Book ").append(book.ID()).append(" : " ) //change the variable name to be meaningfull
		  .append(book.getTitle()).append("\n") //change the method name according to naming conversion
		  .append("  DueDate: ").append(dateFormat.format(D)).append("\n")
		  .append("  State: ").append(state);		
		return sb.toString();
	}


	public member Member() {
		return member; //change the variable name to be meaningfull
	}


	public book Book() {
		return book; //change the variable name to be meaningfull
	}


	public void DiScHaRgE() {
		state = LoanState.discharge;	//change the class name according to naming conversion and variable name into lowercase	
	}

}
