import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class Loan implements Serializable { //Change the class name. It should start with uppercase
	
	public static enum LoanState { CURRENT, OVER_DUE, DISCHARGED };
	
	private int ID;//Changed variable ID as id - Reviewed by Rasika (22/08/2019)
	private Book book; //change the class name according to the naming conversion and variable name into meaningfull
	private Member member; //change the class name according to the naming conversion and variable name into meaningfull
	private Date date; //change the class name according to the naming conversion and variable name into meaningfull
	private LoanState state; //change the class name according to naming conversion

	
	public loan(int loanId, Book book, Member member, Date dueDate) { //Changed class names as Book, Member and Date - Reviewed by Rasika (22/08/2019)
		this.id = loanId;
		this.book = book; //Change variable into meaningfull
		this.member = member; //Change variable into meaningfull
		this.date = dueDate; //Change variable into meaningfull
		this.state = LoanState.CURRENT; //change the class name according to naming conversion
	}

	
	public void checkOverDue() {
		if (state == loanState.CURRENT && calendar.instance().Date().after(id)) { //changed the variable name into camelCase
			this.state = loanState.overDue;	//change the class name according to naming conversion and variable name into camelCase
		}
	}

	
	public boolean overDue() { //chane the method name according to naming conversion
		return state == loanState.overDue; //change the class name according to naming conversion and variable name into camelCase
	}

	
	public Integer getID() {//Changed getter method as getID - Reviewed by Rasika (22/08/2019)
		return id;
	}


	public Date getDueDate() { //change the method name according to naming conversion
		return date; //change the variable name to be meaningfull
	}
	
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");  

		StringBuilder sb = new StringBuilder();
		sb.append("Loan:  ").append(id).append("\n")
		  .append("  Borrower ").append(member.getId()).append(" : ") //change the method name according to naming conversion
		  .append(member.getLastName()).append(", ").append(member.getFirstName()).append("\n") //change the method name according to naming conversion
		  .append("  Book ").append(book.id()).append(" : " ) //change the variable name to be meaningfull
		  .append(book.title()).append("\n") //change the method name according to naming conversion
		  .append("  DueDate: ").append(sdf.format(D)).append("\n")
		  .append("  State: ").append(state);		
		return sb.toString();
	}


	public Member member() {//Changed return type as Member - Reviewed by Rasika (22/08/2019)
		return member; //change the variable name to be meaningfull
	}


	public Book book() {
		return book; //change the variable name to be meaningfull
	}


	public void DiScHaRgE() {
		state = loanState.discharge;	//change the class name according to naming conversion and variable name into lowercase	
	}

}
