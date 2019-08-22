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
	/*
	Reviewed by Rasika - (22/09/2019)
	Class name should be changed as Library
	*/
	private int bookId; //Variable name should be starts with lowercase and should be in camelback.
	private int memberId; //Variable name should be starts with lowercase and should be in camelback.
	private int loanId; //Variable name should be starts with lowercase and should be in camelback.
	private Date loanDate; //Variable name should be starts with lowercase and should be in camelback.
	
	private Map<Integer, Book> catalog; //Variable name should be starts with lowercase and should be in camelback.
	/*
	Reviewed by Rasika - (22/09/2019)
	Map value should be changed to Book
	*/
	private Map<Integer, Member> members; //Variable name should be starts with lowercase and should be in camelback.
	/*
	Reviewed by Rasika - (22/09/2019)
	Map value should be changed to Member
	*/
	private Map<Integer, Loan> loans; //Variable name should be starts with lowercase and should be in camelback.
	/*
	Reviewed by Rasika - (22/09/2019)
	Map value should be changed to Loan
	*/
	private Map<Integer, Loan> currentLoans; //Variable name should be starts with lowercase and should be in camelback.
	/*
	Reviewed by Rasika - (22/09/2019)
	Map value should be changed to Loan
	*/
	private Map<Integer, Book> damagedBooks; //Variable name should be starts with lowercase and should be in camelback.
	/*
	Reviewed by Rasika - (22/09/2019)
	Map value should be changed to Book
	*/

	private library() {
		catalog = new HashMap<>();//Changed variable as catalog - Reviewed by Rasika - (22/09/2019)
		memeber = new HashMap<>();//Changed variable as member - Reviewed by Rasika - (22/09/2019)
		loans = new HashMap<>();//Changed variable as loans - Reviewed by Rasika - (22/09/2019)
		currentLoans = new HashMap<>();//Changed variable as currentLoans - Reviewed by Rasika - (22/09/2019)
		damagedBooks = new HashMap<>();//Changed variable as damagedBooks - Reviewed by Rasika - (22/09/2019)
		bookId = 1; //Variable name should be starts with lower case and should be in camelback.
		memberId = 1; //Variable name should be starts with lower case and should be in camelback.	
		loanId = 1; //Variable name should be starts with lower case and should be in camelback.
	}

	
	public static synchronized Library instance() {	//Return type should start with upper case and method name should be start with lower case	
		if (self == null) { //Variable name should be starts with lowercase and should be in camelback.
			Path path = Paths.get(libraryFile);	//object should start with lower case	
			if (Files.exists(path)) {	
				try (ObjectInputStream lif = new ObjectInputStream(new FileInputStream(libraryFile));) {//Variable LIF should be changed to lif - Reviewed by Rasika - (22/09/2019)
			    
					self = (library) lif.readObject(); //Variable name should be starts with lowercase and should be in camelback.
					Calendar.instance().setDate(self.loanDate); //Variable name should be starts with lowercase and should be in camelback and Method name should be start with lowercase and be camelback. Also underscore separated methods name not acceptable.
					lif.close();
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			else self = new Library(); //Variable name should be starts with lowercase and should be in camelback.
			//Class name should be changed as Library - Reviewed by Rasika - (22/09/2019)
		}
		return self;
	}

	
	public static synchronized void SAVE() {//method name should be changed as save - Reviewed by Rasika - (22/09/2019)
		if (self != null) { //Variable name should be starts with lowercase and should be in camelback.
			instance.loanDate = Calendar.instance().Date(); //Variable name should be starts with lowercase and should be in camelback and method name should be start with lower case
			try (ObjectOutputStream lof = new ObjectOutputStream(new FileOutputStream(libraryFile));) {
				lof.writeObject(self); //Variable name should be starts with lowercase and should be in camelback.
				lof.flush();
				lof.close();	
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

	
	public List<Member> member() {		//Method should be members() -Reviewed by Rasika - (22/09/2019) 
		return new ArrayList<member>(member.values()); //variable should be member - Reviewed by Rasika - (22/09/2019)
	}


	public List<Book> books() {	//Method should be books() -Reviewed by Rasika - (22/09/2019)	
		return new ArrayList<Book>(catalog.values()); //ArrayList type should be Book - Reviewed by Rasika - (22/09/2019)
	}


	public List<Loan> currentLoans() {//Method should be currentLoans() -Reviewed by Rasika - (22/09/2019)	
		return new ArrayList<Loan>(currentLoans.values());
	}


	public Member addMember(String lastName, String firstName, String email, int phoneNo) {	
		/*
		return typr should be Member
		method name should be addMember
		*/
		Member member = new Member(lastName, firstName, email, phoneNo, NextMID());//Class name should be Member - Reviewed by Rasika - (22/09/2019)
		members.put(member.getId(), member);	//method should be starts with lowercase and should be in camelback. 	
		return member;
	}

	
	public Book addBbook(String a, String t, String c) {//Method name should be addBook - Reviewed by Rasika - (22/09/2019)	
		Book book = new Book(a, t, c, nextBookId());  //method should be starts with lowercase and should be in camelback and should be meaningful, object name should be meaningful
		catalog.put(book.ID(), book);		
		return book;
	}

	
	public Member member(int memberId) {
		if (members.containsKey(memberId)) //Variable name should be starts with lowercase and should be in camelback.
			return members.get(memberId);//Variable name should be starts with lowercase and should be in camelback.
		return null;
	}

	
	public Book book(int bookId) {
		if (catalog.containsKey(bookId)) 
			return catalog.get(bookId);		
		return null;
	}

	
	public int loanLimit() {//method should be starts with lowercase and should be in camelback
		return loanLimit;
	}

	
	public boolean memberCanBorrow(Member member) {	//method should be starts with lowercase and should be in camelback	
		if (member.numberOfCurrentLoans() == loanLimit ) //method should be starts with lowercase and should be in camelback
			return false;
				
		if (member.finesOwed() >= maxFinesOwed) 
			return false;
				
		for (Loan loan : member.getLoans()) //method should be starts with lowercase and should be in camelback
			if (loan.overDue()) //method should be starts with lowercase and should be in camelback
				return false;
			
		return true;
	}

	
	public int loansRemainingForMembers(Member member) {	 //method should be starts with lowercase and should be in camelback	
		return loanLimit - member.numberOfCurrentLoans(); //method should be starts with lowercase and should be in camelback
	}

	
	public Loan issueLoan(Book book, Member member) { //method should be starts with lowercase and should be in camelback
		Date dueDate = calendar.instance().dueDate(loanPeriod); //method should be starts with lowercase and should be in camelback
		Loan loan = new loan(nextLoanID(), book, member, dueDate); //method should be starts with lowercase and should be in camelback
		member.takeOutLoan(loan); //method should be starts with lowercase and should be in camelback
		book.borrow(); //method should be starts with lowercase and should be in camelback
		loans.put(loan.ID(), loan); 
		currentLoans.put(book.ID(), loan);
		return loan;
	}
	
	
	public loan loanByBookID(int bookId) { 
		if (currentLoans.containsKey(bookId)) { //Variable name should be starts with lowercase and should be in camelback.
			return currentloans.get(bookId); //Variable name should be starts with lowercase and should be in camelback.
		}
		return null;
	}

	
	public double calculateOverDueFine(Loan loan) {  //method should be starts with lowercase
		if (loan.overDue()) { //method should be starts with lowercase and should be in camelback
			long daysOverDue = Calendar.instance().getDaysDifference(loan.getDueDate()); //method should be starts with lowercase and should be in camelback
			double fine = daysOverDue * finePerDay;
			return fine;
		}
		return 0.0;		
	}


	public void dischargeLoan(Loan currentLoan, boolean isDamaged) { //method should be starts with lowercase and should be in camelback
		Member member = currentLoan.member();
		Book book  = currentLoan.Book();
		
		double overDueFine = calculateOverDueFine(currentLoan); //method should be starts with lowercase
		member.addFine(overDueFine); //method should be starts with lowercase and should be in camelback	
		
		member.dishchargeLoane(currentLoan); //method should be starts with lowercase and should be in camelback
		book.return(isDamaged);
		if (isDamaged) {
			member.addFine(damageFee); //method should be starts with lowercase and should be in camelback
			damagedBooks.put(book.id(), book); //Variable name should be starts with lowercase and should be in camelback.
		}
		currentLoan.discharge(); //method should be starts with lowercase and should be in camelback
		currentLoans.remove(book.id()); //Variable name should be starts with lowercase and should be in camelback.
	}


	public void checkCurrentLoans() {
		for (Loan loan : currentLoans.values()) { //Variable name should be starts with lowercase and should be in camelback.
			loan.checkOverDue();
		}		
	}


	public void repairBook(Book currentBook) { //method should be starts with lowercase and should be in camelback
		if (damagedBooks.containsKey(currentBook.ID())) {//Variable name should be starts with lowercase and should be in camelback.
			currentBook.repair();
			damagedBooks.remove(currentBook.ID());//Variable name should be starts with lowercase and should be in camelback.
		}
		else {
			throw new RuntimeException("Library: repairBook: book is not damaged");
		}
		
	}
	
	
}
