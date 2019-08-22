import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Calendar {
	
	private static Calendar self; // variable name are to start with a lowercase 
	private static java.util.Calendar calendar; //variable name are to start with a lowercase 
	
	
	private Calendar() {
		calendar = java.util.Calendar.getInstance(); //variable name are to start with a lowercase 

	}
	
	public static Calendar INSTANCE() {
		if (self == null) {
			self = new Calendar();
		}
		return self;
	}
	
	public void incrementDate(int days) {
		calendar.add(java.util.Calendar.DATE, days);		
	}
	
	public synchronized void setDate(Date date) {
		/*
		Reviewed by Rasika (22/08/2019)
		Set_dATE method should be changed as setDate
		*/
		try {
			calendar.setTime(date);  //variable name are to start with a lowercase 
	        calendar.set(java.util.Calendar.HOUR_OF_DAY, 0);   //variable name are to start with a lowercase 
	        calendar.set(java.util.Calendar.MINUTE, 0);   //variable name are to start with a lowercase 
	        calendar.set(java.util.Calendar.SECOND, 0);  //variable name are to start with a lowercase 
	        calendar.set(java.util.Calendar.MILLISECOND, 0);//variable name are to start with a lowercase 
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
	public synchronized Date date() {
		/*
		Reviewed by Rasika (22/08/2019)
		Date method should be changed as date
		*/
		try {
	        calendar.set(java.util.Calendar.HOUR_OF_DAY, 0);  //variable name are to start with a lowercase 
	        calendar.set(java.util.Calendar.MINUTE, 0);  //variable name are to start with a lowercase 
	        calendar.set(java.util.Calendar.SECOND, 0);  //variable name are to start with a lowercase 
	        calendar.set(java.util.Calendar.MILLISECOND, 0);//variable name are to start with a lowercase 
			return calendar.getTime();//variable name are to start with a lowercase 
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}

	public synchronized Date dueDate(int loanPeriod) {
		Date now = Date();
		//Variable NoW changed as now - Reviewed by Rasika (22/08/2019)
		calendar.add(java.util.Calendar.DATE, loanPeriod);
		Date dueDate = calendar.getTime(); //change object name into lower case
		calendar.setTime(NoW);//Variable NoW changed as now - Reviewed by Rasika (22/08/2019)
		return dueDate;//Changed variable DuEdAtE as dueDate
	}
	
	public synchronized long getDaysDifference(Date targetDate) {
		
		long differenceMillis = Date().getTime() - targetDate.getTime(); //chane variable name into lower case
	    long differenceDate = TimeUnit.DAYS.convert(Diff_Millis, TimeUnit.MILLISECONDS); // change variable name into meaningfull
	    return differenceDate; // change variable name into meaningfull
	}

}
