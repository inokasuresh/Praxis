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

	public BorrowBookControl() {
		this.library = library.INSTANCE();
		controlState = ControlState.INITIALISED;
	}

	public void setUI(BorrowBookUI ui) {
		if (!controlState.equals(ControlState.INITIALISED))
			throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");

		this.burrowBookUI = ui;
		ui.Set_State(BorrowBookUI.UI_STATE.READY);
		controlState = ControlState.READY;
	}

	public void Swiped(int MEMMER_ID) {
		if (!controlState.equals(ControlState.READY))
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");

		member = library.MEMBER(MEMMER_ID);
		if (member == null) {
			burrowBookUI.Display("Invalid memberId");
			return;
		}
		if (library.MEMBER_CAN_BORROW(M)) {
			pendingBooksList = new ArrayList<>();
			burrowBookUI.Set_State(BorrowBookUI.UI_STATE.SCANNING);
			controlState = ControlState.SCANNING;
		} else {
			burrowBookUI.Display("Member cannot borrow at this time");
			burrowBookUI.Set_State(BorrowBookUI.UI_STATE.RESTRICTED);
		}
	}

	public void Scanned(int bookId) {
		BOOK = null;
		if (!controlState.equals(ControlState.SCANNING)) {
			throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
		}
		BOOK = library.Book(bookId);
		if (BOOK == null) {
			burrowBookUI.Display("Invalid bookId");
			return;
		}
		if (!BOOK.AVAILABLE()) {
			burrowBookUI.Display("Book cannot be borrowed");
			return;
		}
		pendingBooksList.add(BOOK);
		for (book B : pendingBooksList) {
			burrowBookUI.Display(B.toString());
		}
		if (library.Loans_Remaining_For_Member(member) - pendingBooksList.size() == 0) {
			burrowBookUI.Display("Loan limit reached");
			Complete();
		}
	}

	public void Complete() {
		if (pendingBooksList.size() == 0) {
			cancel();
		} else {
			burrowBookUI.Display("\nFinal Borrowing List");
			for (book B : pendingBooksList) {
				burrowBookUI.Display(B.toString());
			}
			completedLoansList = new ArrayList<loan>();
			burrowBookUI.Set_State(BorrowBookUI.UI_STATE.FINALISING);
			controlState = ControlState.FINALISING;
		}
	}

	public void Commit_LOans() {
		if (!controlState.equals(ControlState.FINALISING)) {
			throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
		}
		for (book B : pendingBooksList) {
			loan LOAN = library.ISSUE_LAON(B, member);
			completedLoansList.add(LOAN);
		}
		burrowBookUI.Display("Completed Loan Slip");
		for (loan LOAN : completedLoansList) {
			burrowBookUI.Display(LOAN.toString());
		}
		burrowBookUI.Set_State(BorrowBookUI.UI_STATE.COMPLETED);
		controlState = ControlState.COMPLETED;
	}

	public void cancel() {
		burrowBookUI.Set_State(BorrowBookUI.UI_STATE.CANCELLED);
		controlState = ControlState.CANCELLED;
	}

}
