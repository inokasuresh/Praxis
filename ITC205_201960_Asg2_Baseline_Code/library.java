
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class Library implements Serializable { // Class name should start with upper case
	
	private static final String libraryFile = "library.obj";
	private static final int loanLimit = 2;
	private static final int loanPeriod = 2;
	private static final double finePerDay = 1.0;
	private static final double maxFinesOwed = 1.0;
	private static final double damageFee = 2.0;
	
	private static library self; //Variable name should be starts with lowercase and should be in camelback.
	private int bookId; //Variable name should be starts with lowercase and should be in camelback.
	private int memberId; //Variable name should be starts with lowercase and should be in camelback.
	private int loanId; //Variable name should be starts with lowercase and should be in camelback.
	private Date loanDate; //Variable name should be starts with lowercase and should be in camelback.
	
	private Map<Integer, book> catalog; //Variable name should be starts with lowercase and should be in camelback.
	private Map<Integer, member> members; //Variable name should be starts with lowercase and should be in camelback.
	private Map<Integer, loan> loans; //Variable name should be starts with lowercase and should be in camelback.
	private Map<Integer, loan> currentLoans; //Variable name should be starts with lowercase and should be in camelback.
	private Map<Integer, book> damagedBooks; //Variable name should be starts with lowercase and should be in camelback.
	

	private library() {
		CATALOG = new HashMap<>();
		MEMBERS = new HashMap<>();
		LOANS = new HashMap<>();
		CURRENT_LOANS = new HashMap<>();
		DAMAGED_BOOKS = new HashMap<>();
		bookId = 1; //Variable name should be starts with lower case and should be in camelback.
		memberId = 1; //Variable name should be starts with lower case and should be in camelback.	
		loanId = 1; //Variable name should be starts with lower case and should be in camelback.
	}

	
	public static synchronized Library instance() {	//Return type should start with upper case and method name should be start with lower case	
		if (self == null) { //Variable name should be starts with lowercase and should be in camelback.
			Path path = Paths.get(libraryFile);	//object should start with lower case	
			if (Files.exists(path)) {	
				try (ObjectInputStream LiF = new ObjectInputStream(new FileInputStream(libraryFile));) {
			    
					self = (library) LiF.readObject(); //Variable name should be starts with lowercase and should be in camelback.
					Calendar.instance().setDate(self.loanDate); //Variable name should be starts with lowercase and should be in camelback and Method name should be start with lowercase and be camelback. Also underscore separated methods name not acceptable.
					LiF.close();
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			else self = new library(); //Variable name should be starts with lowercase and should be in camelback.
		}
		return self;
	}

	
	public static synchronized void SAVE() {
		if (self != null) { //Variable name should be starts with lowercase and should be in camelback.
			instance.loanDate = Calendar.instance().Date(); //Variable name should be starts with lowercase and should be in camelback and method name should be start with lower case
			try (ObjectOutputStream LoF = new ObjectOutputStream(new FileOutputStream(libraryFile));) {
				LoF.writeObject(self); //Variable name should be starts with lowercase and should be in camelback.
				LoF.flush();
				LoF.close();	
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	
	public int bookID() { //method should start with lower case
		return bookId; //Variable name should be starts with lowercase and should be in camelback.
	}
	
	
	public int memberID() { //method should start with lower case
		return memberId; //Variable name should be starts with lowercase and should be in camelback.
	}
	
	
	private int nextBookID() { //method should start with lower case and should be meaningful
		return bookId++; //Variable name should be starts with lowercase and should be in camelback.
	}

	
	private int nextMemberID() { //method should start with lower case and should be meaningful
		return memberId++; //Variable name should be starts with lowercase and should be in camelback.
	}

	
	private int nextLoanID() { //method should start with lower case and should be meaningful
		return loanId++; //Variable name should be starts with lowercase and should be in camelback.
	}

	
	public List<member> MEMBERS() {		
		return new ArrayList<member>(MEMBERS.values()); 
	}


	public List<book> BOOKS() {		
		return new ArrayList<book>(CATALOG.values()); 
	}


	public List<loan> CurrentLoans() {
		return new ArrayList<loan>(CURRENT_LOANS.values());
	}


	public member Add_mem(String lastName, String firstName, String email, int phoneNo) {		
		member member = new member(lastName, firstName, email, phoneNo, NextMID());
		MEMBERS.put(member.GeT_ID(), member);		
		return member;
	}

	
	public book Add_book(String a, String t, String c) {		
		book b = new book(a, t, c, NextBID());
		CATALOG.put(b.ID(), b);		
		return b;
	}

	
	public member MEMBER(int memberId) {
		if (MEMBERS.containsKey(memberId)) 
			return MEMBERS.get(memberId);
		return null;
	}

	
	public book Book(int bookId) {
		if (CATALOG.containsKey(bookId)) 
			return CATALOG.get(bookId);		
		return null;
	}

	
	public int LOAN_LIMIT() {
		return loanLimit;
	}

	
	public boolean MEMBER_CAN_BORROW(member member) {		
		if (member.Number_Of_Current_Loans() == loanLimit ) 
			return false;
				
		if (member.Fines_OwEd() >= maxFinesOwed) 
			return false;
				
		for (loan loan : member.GeT_LoAnS()) 
			if (loan.OVer_Due()) 
				return false;
			
		return true;
	}

	
	public int Loans_Remaining_For_Member(member member) {		
		return loanLimit - member.Number_Of_Current_Loans();
	}

	
	public loan ISSUE_LAON(book book, member member) {
		Date dueDate = Calendar.INSTANCE().Due_Date(loanPeriod);
		loan loan = new loan(NextLID(), book, member, dueDate);
		member.Take_Out_Loan(loan);
		book.Borrow();
		LOANS.put(loan.ID(), loan);
		CURRENT_LOANS.put(book.ID(), loan);
		return loan;
	}
	
	
	public loan LOAN_BY_BOOK_ID(int bookId) {
		if (CURRENT_LOANS.containsKey(bookId)) {
			return CURRENT_LOANS.get(bookId);
		}
		return null;
	}

	
	public double CalculateOverDueFine(loan loan) {
		if (loan.OVer_Due()) {
			long daysOverDue = Calendar.INSTANCE().Get_Days_Difference(loan.Get_Due_Date());
			double fine = daysOverDue * finePerDay;
			return fine;
		}
		return 0.0;		
	}


	public void Discharge_loan(loan currentLoan, boolean isDamaged) {
		member member = currentLoan.Member();
		book book  = currentLoan.Book();
		
		double overDueFine = CalculateOverDueFine(currentLoan);
		member.Add_Fine(overDueFine);	
		
		member.dIsChArGeLoAn(currentLoan);
		book.Return(isDamaged);
		if (isDamaged) {
			member.Add_Fine(damageFee);
			DAMAGED_BOOKS.put(book.ID(), book);
		}
		currentLoan.DiScHaRgE();
		CURRENT_LOANS.remove(book.ID());
	}


	public void checkCurrentLoans() {
		for (loan loan : CURRENT_LOANS.values()) {
			loan.checkOverDue();
		}		
	}


	public void Repair_BOOK(book currentBook) {
		if (DAMAGED_BOOKS.containsKey(currentBook.ID())) {
			currentBook.Repair();
			DAMAGED_BOOKS.remove(currentBook.ID());
		}
		else {
			throw new RuntimeException("Library: repairBook: book is not damaged");
		}
		
	}
	
	
}
