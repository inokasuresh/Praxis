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
		return new ArrayList<book>(catalog.values()); 
	}


	public List<loan> CurrentLoans() {
		return new ArrayList<loan>(currentLoans.values());
	}


	public member Add_mem(String lastName, String firstName, String email, int phoneNo) {		
		member member = new member(lastName, firstName, email, phoneNo, NextMID());
		MEMBERS.put(member.getId(), member);	//method should be starts with lowercase and should be in camelback. 	
		return member;
	}

	
	public book Add_book(String a, String t, String c) {		
		book book = new book(a, t, c, nextBookId());  //method should be starts with lowercase and should be in camelback and should be meaningful, object name should be meaningful
		catalog.put(book.ID(), book);		
		return book;
	}

	
	public member MEMBER(int memberId) {
		if (members.containsKey(memberId)) //Variable name should be starts with lowercase and should be in camelback.
			return members.get(memberId);//Variable name should be starts with lowercase and should be in camelback.
		return null;
	}

	
	public book Book(int bookId) {
		if (CATALOG.containsKey(bookId)) 
			return CATALOG.get(bookId);		
		return null;
	}

	
	public int loanLimit() {//method should be starts with lowercase and should be in camelback
		return loanLimit;
	}

	
	public boolean memberCanBorrow(member member) {	//method should be starts with lowercase and should be in camelback	
		if (member.numberOfCurrentLoans() == loanLimit ) //method should be starts with lowercase and should be in camelback
			return false;
				
		if (member.Fines_OwEd() >= maxFinesOwed) 
			return false;
				
		for (loan loan : member.getLoans()) //method should be starts with lowercase and should be in camelback
			if (loan.overDue()) //method should be starts with lowercase and should be in camelback
				return false;
			
		return true;
	}

	
	public int loansRemainingForMembers(member member) {	 //method should be starts with lowercase and should be in camelback	
		return loanLimit - member.numberOfCurrentLoans(); //method should be starts with lowercase and should be in camelback
	}

	
	public loan issueLoan(book book, member member) { //method should be starts with lowercase and should be in camelback
		Date dueDate = Calendar.instance().Due_Date(loanPeriod); //method should be starts with lowercase and should be in camelback
		loan loan = new loan(nextLoanID(), book, member, dueDate); //method should be starts with lowercase and should be in camelback
		member.takeOutLoan(loan); //method should be starts with lowercase and should be in camelback
		book.borrow(); //method should be starts with lowercase and should be in camelback
		loans.put(loan.ID(), loan); 
		currentLoans.put(book.ID(), loan);
		return loan;
	}
	
	
	public loan LOAN_BY_BOOK_ID(int bookId) { 
		if (currentLoans.containsKey(bookId)) { //Variable name should be starts with lowercase and should be in camelback.
			return currentloans.get(bookId); //Variable name should be starts with lowercase and should be in camelback.
		}
		return null;
	}

	
	public double calculateOverDueFine(loan loan) {  //method should be starts with lowercase
		if (loan.overDue()) { //method should be starts with lowercase and should be in camelback
			long daysOverDue = Calendar.instance().getDaysDifference(loan.getDueDate()); //method should be starts with lowercase and should be in camelback
			double fine = daysOverDue * finePerDay;
			return fine;
		}
		return 0.0;		
	}


	public void dischargeLoan(loan currentLoan, boolean isDamaged) { //method should be starts with lowercase and should be in camelback
		member member = currentLoan.Member();
		book book  = currentLoan.Book();
		
		double overDueFine = calculateOverDueFine(currentLoan); //method should be starts with lowercase
		member.addFine(overDueFine); //method should be starts with lowercase and should be in camelback	
		
		member.dishchargeLoane(currentLoan); //method should be starts with lowercase and should be in camelback
		book.Return(isDamaged);
		if (isDamaged) {
			member.addFine(damageFee); //method should be starts with lowercase and should be in camelback
			damagedBooks.put(book.ID(), book); //Variable name should be starts with lowercase and should be in camelback.
		}
		currentLoan.discharge(); //method should be starts with lowercase and should be in camelback
		currentLoans.remove(book.ID()); //Variable name should be starts with lowercase and should be in camelback.
	}


	public void checkCurrentLoans() {
		for (loan loan : currentLoans.values()) { //Variable name should be starts with lowercase and should be in camelback.
			loan.checkOverDue();
		}		
	}


	public void repairBook(book currentBook) { //method should be starts with lowercase and should be in camelback
		if (damagedBooks.containsKey(currentBook.ID())) {//Variable name should be starts with lowercase and should be in camelback.
			currentBook.Repair();
			damagedBooks.remove(currentBook.ID());//Variable name should be starts with lowercase and should be in camelback.
		}
		else {
			throw new RuntimeException("Library: repairBook: book is not damaged");
		}
		
	}
	
	
}