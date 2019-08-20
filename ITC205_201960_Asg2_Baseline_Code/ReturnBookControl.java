public class ReturnBookControl {

	private ReturnBookUI ui;//Changed class Ui variable as ui
	private enum ControlState { INITIALISED, READY, INSPECTING };//Changed enum CINTROL_STATE as ControlState
	private CONTROL_STATE sTaTe;
	/*
	Changed enum CINTROL_STATE as ControlState
	Changed variable sTaTe as state
	*/
	
	private library lIbRaRy;
	/*
	Changed class library as Library
	Changed variable lIbRaRy as library
	*/
	
	private loan CurrENT_loan;
	/*
	Changed class loan as Loan
	Changed class variable CurrENT_loan as currentLoan
	*/
	

	public ReturnBookControl() {
		this.lIbRaRy = lIbRaRy.INSTANCE();
		/*
	    Changed variable lIbRaRy as library
	    Changed variable CurrENT_loan as currentLoan
	   */
		state = CONTROL_STATE.INITIALISED;
		/*
	    Changed variable sTaTe as state
	    Changed enum CONTROL_STATE as ControlState
	   */
	}
	
	
	public void setUI(ReturnBookUI ui) {//Changed Set_UI method name to setUI
		if (!state.equals(ControlState.INITIALISED)) {
		/*
	    Changed variable sTaTe as state
	    Changed enum CONTROL_STATE as ControlState
	    */
			throw new RuntimeException("ReturnBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.ui = ui;//Changed variable Ui to ui
		ui.setState(ReturnBookUI.UI_STATE.READY);
		/*
	    Changed method Set_State as setState
	    Changed enum UI_STATE as UiState
	    */
		sTaTe = CONTROL_STATE.READY;
		/*
	    Changed variable sTaTe as state
	    Changed enum CONTROL_STATE as controlState
	    */
	}


	public void bookScanned(int Book_ID) {
		/*
	    Changed method Book_scanned as bookScanned
	    Changed variable Book_ID as bookId
	    */
		if (!sTaTe.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("ReturnBookControl: cannot call bookScanned except in READY state");
		}	
		book CUR_book = lIbRaRy.Book(Book_ID);
		
		if (CUR_book == null) {
			Ui.display("Invalid Book Id");
			return;
		}
		if (!CUR_book.On_loan()) {
			Ui.display("Book has not been borrowed");
			return;
		}		
		CurrENT_loan = lIbRaRy.LOAN_BY_BOOK_ID(Book_ID);	
		double Over_Due_Fine = 0.0;
		if (CurrENT_loan.OVer_Due()) {
			Over_Due_Fine = lIbRaRy.CalculateOverDueFine(CurrENT_loan);
		}
		Ui.display("Inspecting");
		Ui.display(CUR_book.toString());
		Ui.display(CurrENT_loan.toString());
		
		if (CurrENT_loan.OVer_Due()) {
			Ui.display(String.format("\nOverdue fine : $%.2f", Over_Due_Fine));
		}
		Ui.Set_State(ReturnBookUI.UI_STATE.INSPECTING);
		sTaTe = CONTROL_STATE.INSPECTING;		
	}


	public void Scanning_Complete() {
		if (!sTaTe.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("ReturnBookControl: cannot call scanningComplete except in READY state");
		}	
		Ui.Set_State(ReturnBookUI.UI_STATE.COMPLETED);		
	}


	public void Discharge_loan(boolean isDamaged) {
		if (!sTaTe.equals(CONTROL_STATE.INSPECTING)) {
			throw new RuntimeException("ReturnBookControl: cannot call dischargeLoan except in INSPECTING state");
		}	
		lIbRaRy.Discharge_loan(CurrENT_loan, isDamaged);
		CurrENT_loan = null;
		Ui.Set_State(ReturnBookUI.UI_STATE.READY);
		sTaTe = CONTROL_STATE.READY;				
	}


}
