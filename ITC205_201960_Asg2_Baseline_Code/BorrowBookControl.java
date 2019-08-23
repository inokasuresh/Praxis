import java.util.ArrayList;
import java.util.List;

public class BorrowBookControl {

	/*
	 * changed variable name UI to burrowBookUI. And all reference variable also
	 * changed.
	 */
	private BorrowBookUI burrowBookUI;

	/*
	 * changed variable name LIBRARY to library and Rename Class name library to
	 * Library. And all reference variable also changed.
	 */
	private Library library;

	/*
	 * changed variable name m to member and changed class name member to Member.
	 * And all reference variable also changed.
	 */
	private Member member;

	/*
	 * changed enum name CONTROL_STATE to ControlState. And all reference variable
	 * also changed.
	 */
	private enum ControlState {
		INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED
	};

	/*
	 * changed variable name State to controlState. And all reference variable also
	 * changed.
	 */
	private ControlState controlState;

	/*
	 * changed variable name PENDING to pendingBooksList. And wild card in List also
	 * changed book to Book. And all reference variable also changed.
	 */
	private List<Book> pendingBooksList;
	
	/*
	 * changed variable name COMPLETED to completedLoansList. And wild card in List also
	 * changed loan to Loan. And all reference variable also changed.
	 */
	private List<Loan> completedLoansList;
	
	/*
	 * changed variable name and class name
	 */
	private Book book;

	public borrowBookControl() {//Method name BorrowBookControl changed as borrowBookControl
		this.library = library.INSTANCE();
		controlState = ControlState.INITIALISED;
	}

	public void setUI(BorrowBookUI ui) {
		if (!controlState.equals(ControlState.INITIALISED))
			throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");

		this.burrowBookUI = ui;
		ui.setState(BorrowBookUI.UiState.READY);//Set_State method changed as setState
		controlState = ControlState.READY;
	}

	public void swiped(int memberId) {//Method name changed to swiped and variable changed as memberId
		if (!controlState.equals(ControlState.READY))
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");

		member = library.member(memberId);//MEMBER method changed as member and variable changed as memberId
		if (member == null) {
			burrowBookUI.display("Invalid memberId");//Changed method as display
			return;
		}
		if (library.memberCanBorrow(member)) {
			/*
			Changed MEMBER_CAN_BORROW method as memberCanBorrow
			Changed M as member
			*/
			pendingBooksList = new ArrayList<>();
			burrowBookUI.setState(borrowBookUI.UiState.SCANNING);
			/*
			Changed Set_State method as setState
			Changed object name as borrowBookUI
			Changed enum as UiState
			*/
			controlState = ControlState.SCANNING;
		} else {
			burrowBookUI.display("Member cannot borrow at this time");//Changed method name as display
			burrowBookUI.setState(borrowBookUI.UiState.RESTRICTED);//Changed method as setState
			/*
			Changed object name as borrowBookUI
			Changed enum as UiState
			*/
		}
	}

	public void Scanned(int bookId) {//Changed method as scanned
		book = null;
		if (!controlState.equals(ControlState.SCANNING)) {
			throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
		}
		book = library.book(bookId);//Changed method as book
		if (book == null) {
			burrowBookUI.display("Invalid bookId");//Changed method as display
			return;
		}
		if (!book.available()) {//Changed method as available
			burrowBookUI.display("Book cannot be borrowed");//Changed method as display
			return;
		}
		pendingBooksList.add(book);
		for (Book b : pendingBooksList) {
			burrowBookUI.display(b.toString());//Changed method as display
		}
		if (library.loansRemainingForMember(member) - pendingBooksList.size() == 0) {//Changed method as loansRemainingForMember
			burrowBookUI.display("Loan limit reached");//Changed method as display
			complete();//Changed method as complete
		}
	}

	public void complete() {//Changed method as complete
		if (pendingBooksList.size() == 0) {
			cancel();
		} else {
			burrowBookUI.display("\nFinal Borrowing List");//Changed method as display
			for (Book b : pendingBooksList) {
				burrowBookUI.display(b.toString());//Changed method as display
			}
			completedLoansList = new ArrayList<Loan>();//Changed array List type as Loan
			burrowBookUI.setState(BorrowBookUI.UiState.FINALISING);
			//Changed method as setState and enum as UiState
			controlState = ControlState.FINALISING;
		}
	}

	public void commitLoans() {//Changed method Commit_LOans as commitLoans
		if (!controlState.equals(ControlState.FINALISING)) {
			throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
		}
		for (Book b : pendingBooksList) {
			Loan loan = library.issueLoan(b, member);
			/*
			Changed class name as Loan
			Changed variable as loan
			Changed method name as issueLoan
			*/
			completedLoansList.add(loan);//Changed variable as loan
		}
		burrowBookUI.display("Completed Loan Slip");//Changed method as display
		for (Loan loan : completedLoansList) {
			/*
			Changed class name as Loan
			Changed variable as loan
			*/
			burrowBookUI.display(loan.toString());
			/*
			Changed method as display
			Changed variable as loan
			*/
		}
		burrowBookUI.setState(borrowBookUI.UiState.COMPLETED);
		/*
		Changed Set_State method as setState
		Changed variable as borrowBookUI
		Changed enum as UiState
		*/
		controlState = ControlState.COMPLETED;
	}

	public void cancel() {
		burrowBookUI.setState(borrowBookUI.UiState.CANCELLED);
		/*
		Changed method name as setState
		Changed variable BorrowBookUI as borrowBookUI
		Changed enum as UiState
		*/
		controlState = ControlState.CANCELLED;
	}

}
