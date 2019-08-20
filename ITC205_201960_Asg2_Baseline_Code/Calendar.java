import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Calendar {
	
	private static Calendar self; // variable name are to start with a lowercase 
	private static java.util.Calendar calender; //variable name are to start with a lowercase 
	
	
	private Calendar() {
		calender = java.util.Calendar.getInstance(); //variable name are to start with a lowercase 

	}
	
	public static Calendar INSTANCE() {
		if (self == null) {
			self = new Calendar();
		}
		return self;
	}
	
	public void incrementDate(int days) {
		calender.add(java.util.Calendar.DATE, days);		
	}
	
	public synchronized void Set_dATE(Date date) {
		try {
			calender.setTime(date);  //variable name are to start with a lowercase 
	        calender.set(java.util.Calendar.HOUR_OF_DAY, 0);   //variable name are to start with a lowercase 
	        calender.set(java.util.Calendar.MINUTE, 0);   //variable name are to start with a lowercase 
	        calender.set(java.util.Calendar.SECOND, 0);  //variable name are to start with a lowercase 
	        calender.set(java.util.Calendar.MILLISECOND, 0);//variable name are to start with a lowercase 
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
	public synchronized Date Date() {
		try {
	        calender.set(java.util.Calendar.HOUR_OF_DAY, 0);  //variable name are to start with a lowercase 
	        calender.set(java.util.Calendar.MINUTE, 0);  //variable name are to start with a lowercase 
	        calender.set(java.util.Calendar.SECOND, 0);  //variable name are to start with a lowercase 
	        calender.set(java.util.Calendar.MILLISECOND, 0);//variable name are to start with a lowercase 
			return calender.getTime();//variable name are to start with a lowercase 
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}

	public synchronized Date Due_Date(int loanPeriod) {
		Date NoW = Date();
		calender.add(java.util.Calendar.DATE, loanPeriod);
		Date dueDate = calender.getTime(); //change object name into lower case
		calender.setTime(NoW);
		return DuEdAtE;
	}
	
	public synchronized long Get_Days_Difference(Date targetDate) {
		
		long diff_millis = Date().getTime() - targetDate.getTime(); //chane variable name into lower case
	    long DifferenceDate = TimeUnit.DAYS.convert(Diff_Millis, TimeUnit.MILLISECONDS); // change variable name into meaningfull
	    return DifferenceDate; // change variable name into meaningfull
	}

}
