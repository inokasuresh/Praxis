public class FixBookControl {
	
	private FixBookUI ui;//Changed the class variable to lower case
	private enum ControlState { INITIALISED, READY, FIXING };//Changed the name of the enum based on the naming Conventions
	private ControlState state;//Changed the variable name to lowercase and Changed the name of the enum based on the naming Conventions
	
	private Library library;//Changed the class name and variable name based on the naming Conventions
	private Book currentBook;//Changed the class name and the variable name based on the naming Conventions


	public FixBookControl() {
		this.library = library.INSTANCE();//Changed the class variable based on the naming Conventions
		state = ControlState.INITIALISED;//Changed the variable name, enum name based on the naming Conventions
	}
	
	
	public void setUI(FixBookUI ui) {//modify the setter method and variable according to naming Conventions
		if (!state.equals(ControlState.INITIALISED)) {//Changed the variable name, enum name based on the naming Conventions
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.ui = ui;//Changed the variable name to lowercase
		ui.setState(FixBookUI.UiState.READY);//Changed the setter method name and enum name according to naming Conventions 
		state = ControlState.READY;//Changed the variable name and enum according to naming Conventions
	}


	public void bookScanned(int bookId) {//Changed the method name in to camelCase
		if (!state.equals(ControlState.READY)) {//Changed variable name and enum name according to naming Conventions
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
		}	
		currentBook = library.book(bookId);//Changed the variable names and the method name based on the naming Conventions 
		
		if (currentBook == null) {//Changed the class variable based on the naming Conventions
			ui.display("Invalid bookId");//Changed the variable name into lower case
			return;
		}
		if (!currentBook.isDamaged()) {//Changed the variable name and method name based on the naming Conventions
			ui.display("Book has not been damaged");//Changed the variable name into lower case
			return;
		}
		ui.display(currentBook.toString());//Changed the variable names into lowercase
		ui.setState(FixBookUI.UiState.FIXING);//Changed the variable names, enum name and the method name based on the naming Conventions
		state = ControlState.FIXING;//Changed the variable name and the enum name based on the naming Conventions	
	}


	public void fixBook(boolean mustFix) {//Changed the method name and the variable name based on the naming Conventions
		if (!state.equals(ControlState.FIXING)) {//Chnaged the variable name and the enum name based on the naming Conventions
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
		}	
		if (mustFix) {//Changed the variable name
			library.repairBook(currentBook);//Changed the class variable name and method name baed on the naming Conventions
		}
		currentBook = null;//changed the variable name into camelCase
		ui.setState(FixBookUI.UiState.READY);//Changed the variable name, method name and enum name based on the naming Conventions
		state = ControlState.READY;	//Changed the variable and enum names based on the naming Conventions		
	}

	
	public void scanComplete() {//Changed the method name based on the naming Conventions
		if (!state.equals(ControlState.READY)) {//Changed variable name and enum name based on the naming Conventions
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
		}	
		ui.setState(FixBookUI.UiState.COMPLETED);//changed variable name, method name and enum name based on the naming Conventions		
	}






}
