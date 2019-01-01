package hw1;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Event class represents an event containing several methods that can be used to 
 * to mutate the event object's variables or to access them
 * @author BrandonAmazing
 * @version 1.0
 */
public class Event implements Comparable<Event>
{
	String name;
	LocalDate date;
	TimeInterval tInt; 
	
	/**
	 * Event - constructor for an event consisting of a name, date, and time interval
	 * @param name - the name of the event
	 * @param date - the date of the event
	 * @param tI - the time interval object of the event, containing a start and end time
	 */
	public Event(String name, LocalDate date,TimeInterval tI)
	{
		this.name = name;
		this.date = date;
		this.tInt = tI;
		
	}
	
	/**
	 * compareTo - compares one event object to another by first the date, and then the start time 
	 * of the event if the dates are equal
	 * @param that - the event object to compare
	 * @return either a positive or negative integer stating if one event should come before or after another
	 */
	public int compareTo(Event that)
	{
		int DateCmp = this.getDate().compareTo(that.getDate());
		if (DateCmp != 0)
		{
			return DateCmp;
		}
		return this.getTimeInterval().getStartTime().compareTo(that.getTimeInterval().getStartTime());
	}
	
	/**
	 * equals - checks if two objects are equal based on the compareTo method
	 * @param x - an object to be compared
	 * @return a boolean true or false depending on if two objects are equal
	 */
	public boolean equals(Object x)
	{
		Event that = (Event)x;
		return this.compareTo(that) == 0;
	}
	
	/**
	 * getName - gets the name of the event object
	 * @return name, the name of the event
	 */
	public String getName()
	{
		return name;	
	}
	
	/**
	 * setName - set desired name for an event
	 * @param n - the new name for the event
	 */
	public void setName(String n)
	{
		name = n;
	}
	
	/**
	 * getDate - gets the date of the event object
	 * @return date - the date of the event
	 */
	public LocalDate getDate()
	{
		return date;
	}
	
	/**
	 * setDate - sets the desired date of the new event object
	 * @param d - the new date of the event
	 */
	public void setDate(LocalDate d)
	{
		date = d;
	}
	
	/**
	 * getTimeInterval - gets the time interval of the event object
	 * @return tInt, the time interval of the event
	 */
	public TimeInterval getTimeInterval()
	{
		return tInt;
	}
	
	/**
	 * setTimeInterval - sets the time interval to desired interval
	 * @param s - the start time of the event
	 * @param e - the end time of the event
	 */
	public void setTimeInterval(LocalTime s, LocalTime e)
	{
		tInt = new TimeInterval(s, e);
	}
	/**
	 * printTimeInterval - prints the time interval of the event object with the start time 
	 * followed by a dash and the end time
	 * @param t - the time interval to print
	 * @return the printed string of the time interval
	 */
	public String printTimeInterval(TimeInterval t)
	{
		return t.getStartTime().toString() + " - " + t.getEndTime().toString();
	}
	
	/**
	 * printDate - prints the date of the event in the format day of week, 
	 * and then month, the day of month, and the year in integer form
	 */
	public void printDate()
	{
		DateTimeFormatter df = DateTimeFormatter.ofPattern("E, MMM d, yyyy");
		System.out.println(df.format(date));
	}
	
	/**
	 * printEvent - prints the name of the event and start and end time of the event
	 */
	public void printEvent()
	{
		System.out.println(name + " " + this.printTimeInterval(tInt));
	}
	
	/**
	 * printEventListFormat - prints the date of the event followed by the time interval 
	 * and then the event's name
	 */
	public void printEventListFormat()
	{
		DateTimeFormatter df = DateTimeFormatter.ofPattern("eeee MMMM d");
		System.out.println("  " + df.format(date) + " " + this.printTimeInterval(tInt) + " " + this.name);
	}
	 
}
