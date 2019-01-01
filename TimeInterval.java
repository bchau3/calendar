package hw1;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * TimeInterval class that creates an object with two local time variables
 * to hold a start time and end time for an event object
 * @author BrandonAmazing
 * @version 1.0
 */
public class TimeInterval 
{
	LocalTime StartTime;
	LocalTime EndTime;
	
	/**
	 * TimeInterval - the constructor to create the time interval object
	 * @param start - the start time of the interval
	 * @param end - the end time of the interval
	 */
	public TimeInterval(LocalTime start, LocalTime end)
	{
		StartTime = start;
		EndTime = end;
	}
	
	/**
	 * getStartTime - returns the start time of the time interval
	 * @return StartTime - the start time instance variable of the interval object
	 */
	public LocalTime getStartTime()
	{
		return StartTime;
	}
	
	/**
	 * getEndTime - returns the end time of the time interval
	 * @return EndTime - the end time instance variable of the interval object
	 */
	public LocalTime getEndTime()
	{
		return EndTime;
	}
	
	/**
	 * conflict - checks if there is a conflict between two time intervals
	 * @param t - the time interval to compare to
	 * @return true if there is a conflict, false if there is no conflict
	 */
	public boolean conflict (TimeInterval t)
	{
		if (t.getStartTime().isBefore(this.getEndTime()) && t.getEndTime().isAfter(this.getStartTime()))
		{
			return true;
		}
		return false;
	}
	
}
