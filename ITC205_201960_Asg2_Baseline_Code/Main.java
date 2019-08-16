import java.text.SimpleDateFormat;
import java.util.Scanner;


public class Main {
	
	private static Scanner input;//Changed the variable to be more meaningful
	private static Library library;//Changed variable name and class name based on the naming convention
	private static String menu;//Changed the variable name to lowercase
	private static Calendar calender;//Changed the variable name to be more meaningful
	private static SimpleDateFormat sdf;//Changed variable name to lowercase
	
	
	private static String getMenu() {//Changed the method name based on the naming convention
		StringBuilder sb = new StringBuilder();
		
		sb.append("\nLibrary Main Menu\n\n")
		  .append("  M  : add member\n")
		  .append("  LM : list members\n")
		  .append("\n")
		  .append("  B  : add book\n")
		  .append("  LB : list books\n")
		  .append("  FB : fix books\n")
		  .append("\n")
		  .append("  L  : take out a loan\n")
		  .append("  R  : return a loan\n")
		  .append("  LL : list loans\n")
		  .append("\n")
		  .append("  P  : pay fine\n")
		  .append("\n")
		  .append("  T  : increment date\n")
		  .append("  Q  : quit\n")
		  .append("\n")
		  .append("Choice : ");
		  
		return sb.toString();
	}


	public static void main(String[] args) {		
		try {			
			input = new Scanner(System.in);//Changed the variable name to be more meaningful
			library = library.instance();//Changed variable name and method name based on the naming convention
			calender = calendar.instance();//Changed variable name and method name based on the naming convention
			sdf = new SimpleDateFormat("dd/MM/yyyy");//Changed the variable name to lowercase
	
			for (member memeber : library.members()) {//Changed the variable names and method name based on the standard
				output(memeber);
			}
			output(" ");
			for (Book book : library.books()) {//Changed the variable names and method name based on the standard
				output(book);
			}
						
			menu = getMenu();//Changed the variable and method names based on the naming conventions
			
			boolean e = false;
			
			while (!e) {
				
				output("\n" + sdf.format(calender.Date()));//Changed the variable names to lowercase
				String cal = input(menu);//Changed variable name to be more meaningful
				
				switch (cal.toUpperCase()) {
				
				case "M": 
					addMember();//Changed the method name based on the naming convention
					break;
					
				case "LM": 
					members();//Changed the method name based on the naming convention
					break;
					
				case "B": 
					addBook();//Changed the method name based on the naming convention
					break;
					
				case "LB": 
					books();//Changed the method name based on the naming convention
					break;
					
				case "FB": 
					fixBooks();//Changed the method name based on the naming convention
					break;
					
				case "L": 
					borrowBook();//Changed the method name based on the naming convention
					break;
					
				case "R": 
					returnBook();//Changed the method name based on the naming convention
					break;
					
				case "LL": 
					currentLoans();//Changed the method name based on the naming convention
					break;
					
				case "P": 
					fines();//Changed the method name based on the naming convention
					break;
					
				case "T": 
					incrementDate();//Changed the method name based on the naming convention
					break;
					
				case "Q": 
					e = true;
					break;
					
				default: 
					output("\nInvalid option\n");
					break;
				}
				
				library.save();//Changed the method name based on the naming convention
			}			
		} catch (RuntimeException e) {
			output(e);
		}		
		output("\nEnded\n");
	}	

		private static void fines() {//Changed the method name based on the naming convention
		new PayFineUI(new PayFineControl()).run();//Changed the method name based on the naming convention		
	}


	private static void currentLoans() {//Changed the method name based on the naming convention
		output("");
		for (Loan loan : library.CurrentLoans()) {//Changed the class name and variable name based on the naming conventions
			output(loan + "\n");
		}		
	}



	private static void books() {//Changed the method name based on the naming convention
		output("");
		for (Book book : library.books()) {//Changed the class name and method name based on the naming conventions
			output(book + "\n");
		}		
	}



	private static void memebers() {//Changed the method name based on the naming convention
		output("");
		for (Member member : library.memebers()) {//Changed the class name and method name based on the naming conventions
			output(member + "\n");
		}		
	}



	private static void borrowBook() {//Changed the method name based on the naming conventions	
		new BorrowBookUI(new BorrowBookControl()).run(); //Changed the method name based on the naming conventions	
	}


	private static void returnBook() {//Changed the method name based on the naming conventions	
		new ReturnBookUI(new ReturnBookControl()).run();//Changed the method name based on the naming conventions			
	}


	private static void fixBooks() {//Changed the method name based on the naming conventions	
		new FixBookUI(new FixBookControl()).run();//Changed the method name based on the naming conventions		
	}


	private static void incrementDate() {//Changed the class name and method name based on the naming conventions
		try {
			int days = Integer.valueOf(input("Enter number of days: ")).intValue();
			calender.incrementDate(days);//Changed the variable name to lowercase
			library.checkCurrentLoans();//Changed the variable name to lowercase
			output(sdf.format(calender.Date()));//Changed the variable name to lowercase
			
		} catch (NumberFormatException e) {
			 output("\nInvalid number of days\n");
		}
	}


	private static void addBook() {//Changed the method name based on the naming conventions	
		
		String author = input("Enter author: ");//Changed variable name to be more meaningful
		String title = input("Enter title: ");//Changed variable name to be more meaningful
		String callNo = input("Enter call number: ");//Changed variable name to be more meaningful
		Book book = library.addBook(author, title, callNo);//Changed variable name to be more meaningful and changed the class name based on the naming conventions
		output("\n" + book + "\n");//Changed variable name to be more meaningful
		
	}

	
	private static void addMember() {//Changed the method name based on the naming conventions
		try {
			String lastName = input("Enter last name: ");//Changed variable name to be more meaningful
			String firstName  = input("Enter first name: ");//Changed variable name to be more meaningful
			String email = input("Enter email: ");//Changed variable name to be more meaningful
			int phoneNo = Integer.valueOf(input("Enter phone number: ")).intValue();//Changed variable name to be more meaningful
			Member member = library.addMember(lastName, firstName, email, phoneNo);//Changed variable name to be more meaningful and changed the class name based on the naming conventions
			output("\n" + member + "\n");//Changed variable name to be more meaningful
			
		} catch (NumberFormatException e) {
			 output("\nInvalid phone number\n");
		}
		
	}


	private static String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();//Changed variable name to lowercase
	}
	 
	
	
	private static void output(Object object) {
		System.out.println(object);
	}

	
}
