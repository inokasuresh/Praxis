import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class Member implements Serializable {//Changed class name member as Member

	private String lastName;//Changed variable LN as lastName
	private String firstName;//Changed variable FN as firstName
	private String email;//Changed variable EM as email
	private int phoneNo;//Changed variable PN as phoneNo
	private int id;//Changed variable ID as id
	private double fines;//Changed variable FINES as fines
	
	private Map<Integer, loan> LNS;//Changed LNS as loans

	
	public member(String lastName, String firstName, String email, int phoneNo, int id) {//Changed constructor name member to Member
		this.lastName = lastName;//Changed variable LN as lastName
		this.firstName = firstName;//Changed variable FN as firstName
		this.email = email;//Changed variable EM as email
		this.phoneNo = phoneNo;
		this.id = id;
		
		this.loans = new HashMap<>();//Changed variable LNS as loans
	}

	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Member:  ").append(id).append("\n")//Changed variable ID as id
		  .append("  Name:  ").append(LN).append(", ").append(FN).append("\n")//Changed variable
		  .append("  Email: ").append(EM).append("\n")
		  .append("  Phone: ").append(PN)
		  .append("\n")
		  .append(String.format("  Fines Owed :  $%.2f", FINES))
		  .append("\n");
		
		for (loan LoAn : LNS.values()) {
			sb.append(LoAn).append("\n");
		}		  
		return sb.toString();
	}

	
	public int GeT_ID() {
		return ID;
	}

	
	public List<loan> GeT_LoAnS() {
		return new ArrayList<loan>(LNS.values());
	}

	
	public int Number_Of_Current_Loans() {
		return LNS.size();
	}

	
	public double Fines_OwEd() {
		return FINES;
	}

	
	public void Take_Out_Loan(loan loan) {
		if (!LNS.containsKey(loan.ID())) {
			LNS.put(loan.ID(), loan);
		}
		else {
			throw new RuntimeException("Duplicate loan added to member");
		}		
	}

	
	public String Get_LastName() {
		return LN;
	}

	
	public String Get_FirstName() {
		return FN;
	}


	public void Add_Fine(double fine) {
		FINES += fine;
	}
	
	public double Pay_Fine(double AmOuNt) {
		if (AmOuNt < 0) {
			throw new RuntimeException("Member.payFine: amount must be positive");
		}
		double change = 0;
		if (AmOuNt > FINES) {
			change = AmOuNt - FINES;
			FINES = 0;
		}
		else {
			FINES -= AmOuNt;
		}
		return change;
	}


	public void dIsChArGeLoAn(loan LoAn) {
		if (LNS.containsKey(LoAn.ID())) {
			LNS.remove(LoAn.ID());
		}
		else {
			throw new RuntimeException("No such loan held by member");
		}		
	}

}
