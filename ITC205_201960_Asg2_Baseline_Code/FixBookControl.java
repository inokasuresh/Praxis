public class FixBookControl {
	
	private FixBookUI ui;//Change the class variable to lower case
	private enum ControlState { INITIALISED, READY, FIXING };//Change the name of the enum based on the naming convesion
	private ControlState state;//change the variable name to lowercase and Change the name of the enum based on the naming convesion
	
	private Library library;//Change the class name and variable name based on the naming convesion
	private Book Cur_Book;//Change the class name based on the naming convesion


	public FixBookControl() {
		this.LIB = LIB.INSTANCE();
		StAtE = CONTROL_STATE.INITIALISED;
	}
	
	
	public void Set_Ui(FixBookUI ui) {
		if (!StAtE.equals(CONTROL_STATE.INITIALISED)) {
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.UI = ui;
		ui.Set_State(FixBookUI.UI_STATE.READY);
		StAtE = CONTROL_STATE.READY;		
	}


	public void Book_scanned(int bookId) {
		if (!StAtE.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
		}	
		Cur_Book = LIB.Book(bookId);
		
		if (Cur_Book == null) {
			UI.display("Invalid bookId");
			return;
		}
		if (!Cur_Book.IS_Damaged()) {
			UI.display("Book has not been damaged");
			return;
		}
		UI.display(Cur_Book.toString());
		UI.Set_State(FixBookUI.UI_STATE.FIXING);
		StAtE = CONTROL_STATE.FIXING;		
	}


	public void FIX_Book(boolean MUST_fix) {
		if (!StAtE.equals(CONTROL_STATE.FIXING)) {
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
		}	
		if (MUST_fix) {
			LIB.Repair_BOOK(Cur_Book);
		}
		Cur_Book = null;
		UI.Set_State(FixBookUI.UI_STATE.READY);
		StAtE = CONTROL_STATE.READY;		
	}

	
	public void SCannING_COMplete() {
		if (!StAtE.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
		}	
		UI.Set_State(FixBookUI.UI_STATE.COMPLETED);		
	}






}
