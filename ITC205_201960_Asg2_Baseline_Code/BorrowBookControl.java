import java.util.ArrayList;
import java.util.List;

public class BorrowBookControl {

	private BorrowBookUI burrowBookUI; // changed variable name UI to burrowBookUI. And all reference variable also
										// changed.

	private Library library; // changed variable name LIBRARY to library and Rename Class name library to
								// Library. And all reference variable also changed.
	private Member member; // changed variable name m to member and changed class name member to Member.
							// And all reference variable also changed.

	private enum CONTROL_STATE {
		INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED
	};

	private CONTROL_STATE State;

	private List<book> PENDING;
	private List<loan> COMPLETED;
	private book BOOK;

	public BorrowBookControl() {
		this.library = library.INSTANCE();
		State = CONTROL_STATE.INITIALISED;
	}

	public void setUI(BorrowBookUI ui) {
		if (!State.equals(CONTROL_STATE.INITIALISED))
			throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");

		this.burrowBookUI = ui;
		ui.Set_State(BorrowBookUI.UI_STATE.READY);
		State = CONTROL_STATE.READY;
	}

	public void Swiped(int MEMMER_ID) {
		if (!State.equals(CONTROL_STATE.READY))
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");

		member = library.MEMBER(MEMMER_ID);
		if (member == null) {
			burrowBookUI.Display("Invalid memberId");
			return;
		}
		if (library.MEMBER_CAN_BORROW(M)) {
			PENDING = new ArrayList<>();
			burrowBookUI.Set_State(BorrowBookUI.UI_STATE.SCANNING);
			State = CONTROL_STATE.SCANNING;
		} else {
			burrowBookUI.Display("Member cannot borrow at this time");
			burrowBookUI.Set_State(BorrowBookUI.UI_STATE.RESTRICTED);
		}
	}

	public void Scanned(int bookId) {
		BOOK = null;
		if (!State.equals(CONTROL_STATE.SCANNING)) {
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
		PENDING.add(BOOK);
		for (book B : PENDING) {
			burrowBookUI.Display(B.toString());
		}
		if (library.Loans_Remaining_For_Member(member) - PENDING.size() == 0) {
			burrowBookUI.Display("Loan limit reached");
			Complete();
		}
	}

	public void Complete() {
		if (PENDING.size() == 0) {
			cancel();
		} else {
			burrowBookUI.Display("\nFinal Borrowing List");
			for (book B : PENDING) {
				burrowBookUI.Display(B.toString());
			}
			COMPLETED = new ArrayList<loan>();
			burrowBookUI.Set_State(BorrowBookUI.UI_STATE.FINALISING);
			State = CONTROL_STATE.FINALISING;
		}
	}

	public void Commit_LOans() {
		if (!State.equals(CONTROL_STATE.FINALISING)) {
			throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
		}
		for (book B : PENDING) {
			loan LOAN = library.ISSUE_LAON(B, member);
			COMPLETED.add(LOAN);
		}
		burrowBookUI.Display("Completed Loan Slip");
		for (loan LOAN : COMPLETED) {
			burrowBookUI.Display(LOAN.toString());
		}
		burrowBookUI.Set_State(BorrowBookUI.UI_STATE.COMPLETED);
		State = CONTROL_STATE.COMPLETED;
	}

	public void cancel() {
		burrowBookUI.Set_State(BorrowBookUI.UI_STATE.CANCELLED);
		State = CONTROL_STATE.CANCELLED;
	}

}
