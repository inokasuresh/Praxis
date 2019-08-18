public class PayFineControl {
	
	private PayFineUI ui; // Variable name should be starts with lowercase letter. So Ui -> ui
	private enum ControlState { INITIALISED, READY, PAYING, COMPLETED, CANCELLED }; // Enum should be in camelback and underscore separated name not acceptible. So CONTROL_STATE -> ControlState
	/* 1. Object type name should be in camelback and underscore separated name not acceptible. So CONTROL_STATE -> ControlState
	   2. Variable name sould be starts with lowercase and should be in camelback. So StAtE -> state
	 */
	private CONTROL_STATE state;
	
	/* 1. Object type name should be starts with a block letter. So library -> Library
	   2. Variable name should be starts with lowercase and should be in camelback. So LiBrArY -> library
	 */
	private Library library;
	/* 1. Object type name should be starts with a block letter. So member -> Member
	 */
	private Member MeMbEr;


	public PayFineControl() {
		this.LiBrArY = LiBrArY.INSTANCE();
		StAtE = CONTROL_STATE.INITIALISED;
	}
	
	
	public void Set_UI(PayFineUI ui) {
		if (!StAtE.equals(CONTROL_STATE.INITIALISED)) {
			throw new RuntimeException("PayFineControl: cannot call setUI except in INITIALISED state");
		}	
		this.Ui = ui;
		ui.Set_State(PayFineUI.UI_STATE.READY);
		StAtE = CONTROL_STATE.READY;		
	}


	public void Card_Swiped(int memberId) {
		if (!StAtE.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("PayFineControl: cannot call cardSwiped except in READY state");
		}	
		MeMbEr = LiBrArY.MEMBER(memberId);
		
		if (MeMbEr == null) {
			Ui.DiSplAY("Invalid Member Id");
			return;
		}
		Ui.DiSplAY(MeMbEr.toString());
		Ui.Set_State(PayFineUI.UI_STATE.PAYING);
		StAtE = CONTROL_STATE.PAYING;
	}
	
	
	public void CaNcEl() {
		Ui.Set_State(PayFineUI.UI_STATE.CANCELLED);
		StAtE = CONTROL_STATE.CANCELLED;
	}


	public double PaY_FiNe(double AmOuNt) {
		if (!StAtE.equals(CONTROL_STATE.PAYING)) {
			throw new RuntimeException("PayFineControl: cannot call payFine except in PAYING state");
		}	
		double ChAnGe = MeMbEr.Pay_Fine(AmOuNt);
		if (ChAnGe > 0) {
			Ui.DiSplAY(String.format("Change: $%.2f", ChAnGe));
		}
		Ui.DiSplAY(MeMbEr.toString());
		Ui.Set_State(PayFineUI.UI_STATE.COMPLETED);
		StAtE = CONTROL_STATE.COMPLETED;
		return ChAnGe;
	}
	


}
