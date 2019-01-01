package hw1;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.util.TreeSet;
/**
 * MyCalendar class is the class that creates a new calendar object that stores 
 * and performs functions on event objects
 * This class does this through the creation of data structures
 * 
 * @author BrandonAmazing
 * @version 1.0
 */

public class MyCalendar 
{
	private ArrayList<Event> events;
	private LocalDate current;
	
	/**
	 * MyCalendar - constructor for my calendar class containing
	 * that initializes the instance variables to current day and creates
	 * an empty array list to hold events
	 */
	public MyCalendar()
	{
		current = LocalDate.now();
		events = new ArrayList<>();
	}
	
	/**
	 * add - adds an event object to the calendar's collection of events
	 * @param e, the Event to be added
	 */
	public void add (Event e)
	{
		events.add(e);
	}
	
	/**
	 * hasAEvent - returns a boolean depending on whether or not there exists
	 * an event with the a certain date in the collection
	 * @param L, the date to search for
	 * @return boolean true if there exists an event, or false if there is no such event
	 */
	public boolean hasAEvent(LocalDate L) //checks if there is an event on certain day
	{
		for (Event e: events)
		{
			if (e.getDate().equals(L))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * load - gets the "events.txt" file and collects the events on the file into the collection
	 * of events
	 */
	public void load()
	{
		Scanner s = new Scanner(System.in);
		File file = new File("events.txt"); //load the file
		try
		{
			s = new Scanner(file);
			
		} 
		catch (FileNotFoundException e)
		{	//check if file was not loaded successfully
			 e = new FileNotFoundException("File not found or bad File"); 
			 System.out.println(e);
		}
		
		
		while (s.hasNextLine())
		{	//collect parts of the event, through splitting/parsing for each variable
			String EventName = s.nextLine();
			String DayAndTimeInt = s.nextLine();
			String[] parts = DayAndTimeInt.split(" ");
			String date = parts[0];
			LocalDate D = null;

			DateTimeFormatter timeDf = DateTimeFormatter.ofPattern("H:m");
			LocalTime Start = LocalTime.parse(parts[1], timeDf);
			LocalTime End = LocalTime.parse(parts[2], timeDf);
			TimeInterval TI = new TimeInterval(Start, End);

			if (date.contains("/")) //checks if event is a one time event
			{
				DateTimeFormatter yearDf = DateTimeFormatter.ofPattern("M/d/yy");
				D = LocalDate.parse(date, yearDf);
				Event e = new Event (EventName, D, TI);		
				events.add(e);
			}	//checks if event is a recurring event
			else if (date.toUpperCase().contains("S") || date.toUpperCase().contains("M)")
					|| date.toUpperCase().contains("T") || date.toUpperCase().contains("W")
					|| date.toUpperCase().contains("R") || date.toUpperCase().contains("F")
					|| date.toUpperCase().contains("A") || date.toUpperCase().contains("A"))
			{
				LocalDate begin = LocalDate.of(2018, 8, 21); //length of recurring events
				LocalDate end = LocalDate.of(2018, 12, 30);	//is the school semester
				LocalDate count = begin;
				Event e = null;
				if (date.contains("M".toUpperCase())) //event recurs on a Monday
				{
					while (count.isBefore(end))
					{
						if (count.getDayOfWeek().getValue() == 1)
						{
							e = new Event(EventName, count, TI);
							events.add(e);

						}
						count = count.plusDays(1);
					}
					count = begin;
				}
				if (date.contains("T".toUpperCase())) //event recurs on a Tuesday
				{
					while (count.isBefore(end))
					{
						if (count.getDayOfWeek().getValue() == 2)
						{
							e = new Event(EventName, count, TI);
							events.add(e);

						}
						count = count.plusDays(1);
					}
					count = begin;
				}
				if (date.contains("W".toUpperCase())) //event recurs on a Wednesday
				{
					while (count.isBefore(end))
					{
						if (count.getDayOfWeek().getValue() == 3)
						{
							e = new Event(EventName, count, TI);
							events.add(e);

						}
						count = count.plusDays(1);
					}
					count = begin;
				}
				if (date.contains("R".toUpperCase())) //event recurs on a Thursday
				{
					while (count.isBefore(end))
					{
						if (count.getDayOfWeek().getValue() == 4)
						{
							e = new Event(EventName, count, TI);
							events.add(e);

						}
						count = count.plusDays(1);
					}
					count = begin;
				}
				if (date.contains("F".toUpperCase())) //event recurs on a Friday
				{
					while (count.isBefore(end))
					{
						if (count.getDayOfWeek().getValue() == 5)
						{
							e = new Event(EventName, count, TI);
							events.add(e);

						}
						count = count.plusDays(1);
					}
					count = begin;
				}
				if (date.contains("A".toUpperCase())) //event recurs on a Saturday
				{
					while (count.isBefore(end))
					{
						if (count.getDayOfWeek().getValue() == 6)
						{
							e = new Event(EventName, count, TI);
							events.add(e);

						}
						count = count.plusDays(1);
					}
					count = begin;
				}
				if (date.contains("S".toUpperCase())) //event recurs on a Sunday
				{
					while (count.isBefore(end))
					{
						if (count.getDayOfWeek().getValue() == 7)
						{
							e = new Event(EventName, count, TI);
							events.add(e);

						}
						count = count.plusDays(1);
					}
					count = begin;
				}
			}
		}
		System.out.println("File Loaded"); //if file load successful
		s.close();
	}
	
	/**
	 * ViewByDay - views the calendar in day form, listing events on that day
	 */
	public void ViewByDay()
	{
		DateTimeFormatter d = DateTimeFormatter.ofPattern("E, MMM d yyyy");
		TreeSet<Event> found = new TreeSet<>();
		System.out.println(d.format(current));
		for (Event e: events)	//add events that contain the current date
		{
			if (e.getDate().equals(current))
			{
				found.add(e);	
			}
		}
		if (found.size() == 0)	//check if there were no events found
		{
			System.out.println("No Events Scheduled");	
		}
		else
		{	
			for (Event e: found)
			{
				e.printEvent();	//if there are events found, print them
			}
		}
		
	}
	
	/**
	 * ViewByMonth - views the calendar in month view, highlighting days that have
	 * an event with brackets
	 */
	public void ViewByMonth()
	{
		//print out month and year of today's date
        System.out.print(current.getMonth());
        System.out.print(" ");
        System.out.println(current.getYear());
        
        //Print Days of the Week for top of calendar
        System.out.println("Su Mo Tu We Th Fr Sa");
       
        
        //get first day of current month to start printing calendar
        LocalDate strtDay = LocalDate.of(current.getYear(), current.getMonth(), 1);
       
        //place first day in correct spot on calendar
        if (strtDay.getDayOfWeek().getValue() == 7)  //corner case if first day of month is a Sunday
        {										  //different spacing needed
        		if (this.hasAEvent(strtDay) == true)
        		{	//print with bracket if current date is the same as first day of the month
        			System.out.print(" " + "{" + strtDay.getDayOfMonth() + "}");
        		}
        		else
        		{
        		System.out.print(" " + strtDay.getDayOfMonth());
        		}
        }
        for (int i = 1; i <= 6; i++)  //check which day of week 
        {							//the first day is on if not Sunday
        		System.out.print("   ");
        		if (i == strtDay.getDayOfWeek().getValue() && i != 6) //if first day is
        		{													//on Monday through Friday
        			if (this.hasAEvent(strtDay) == true)
            		{
            			System.out.print(" " + "{" + strtDay.getDayOfMonth() + "}");
            		}
            		else
        			{
            			System.out.print(" " + strtDay.getDayOfMonth());
        			}
        			break;
        		}
        		else if (i == 6 && strtDay.getDayOfWeek().getValue() == 6 ) //last corner case, if first day is  
        		{														//a Saturday, skip to next Line
        			if (this.hasAEvent(strtDay) == true)
            		{
            			System.out.println("{" + strtDay.getDayOfMonth() + "}");
            		}
            		else
            		{
            			System.out.println(" " + strtDay.getDayOfMonth());
            		}
        		}
        }
        
        YearMonth yM = YearMonth.of(current.getYear(), current.getMonth());
        int i = 1;
        
        //Add all days of the month according to how many days there are
        //in that month (a.e February on leap year)
        while (i < yM.lengthOfMonth())
        {
        		strtDay = strtDay.plusDays(1); //update to next day 
        		if (strtDay.getDayOfMonth() < 10 && strtDay.getDayOfWeek().getValue() != 7)
        		{
        			System.out.print(" "); //added space for numbers under 10 to keep spacing
        		}
        		//if day is Sunday, print with correct spacing
        		if (strtDay.getDayOfWeek().getValue() == 7)
        		{
        			if (this.hasAEvent(strtDay) == true) //check if today's date is identical to date
        			{					//being counted
        			System.out.print("{" + strtDay.getDayOfMonth() + "}"); //if so, print with bracket
        			}
        			else
        			{
        				if (strtDay.getDayOfMonth() < 10)
        				{
        					System.out.print(" " + strtDay.getDayOfMonth()); //if not and day is less than 10
        																	//print with space to keep spacing
        				}
        				else
        				{
        					System.out.print(strtDay.getDayOfMonth());	//if not, print without bracket
        				}
        			}
        		}
        
        		//if next day to be placed is a Saturday, would be printed with next line
        		else if (strtDay.getDayOfWeek().getValue() == 6)
        		{
        			if (this.hasAEvent(strtDay) == true)				//check if today's date identical
        			{								//to date being counted, if so, print with bracket
        				System.out.println(" " + "{" + strtDay.getDayOfMonth() + "}"); 
        			}																
        			else
        			{
        				System.out.println(" " + strtDay.getDayOfMonth());
        			}
        		}
        		else		//spacing is the same to print for days Monday-Friday
        		{
        			if (this.hasAEvent(strtDay) == true)				//check if today's date identical 													
        			{								//to date being added
        				System.out.print(" " + "{" + strtDay.getDayOfMonth() + "}"); //if so, print with bracket
        			}
        			else
        			{
        				System.out.print(" " + strtDay.getDayOfMonth());
        			}
        		}
        		i++;  //continue counting all the days of the month
        }
		
	}
	
	/**
	 * createEvent - creates a new Event object
	 */
	public void createEvent()
	{	
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the name of the event"); 
		String NameOfEvent = scan.nextLine(); //get name of object
		
		System.out.println("Enter the date of the event [mm/dd/yyyy]");
		String dateAsString = scan.next(); 
		DateTimeFormatter df = DateTimeFormatter.ofPattern("M/d/yyyy");
		LocalDate Date = LocalDate.parse(dateAsString, df);//get date of event through parsing
		
		System.out.println("Enter start time of event (24 hour clock in [HH:mm])");
		String startTimeAsString = scan.next();
		df = DateTimeFormatter.ofPattern("H:m");
		LocalTime startTime = LocalTime.parse(startTimeAsString, df); //get start time of event
																	//through parsing
		System.out.println("Enter end time of event (24 hour clock in [HH:mm])");
		String endTimeAsString = scan.next();
		LocalTime endTime = LocalTime.parse(endTimeAsString, df);//get end time of event
																//through parsing
		TimeInterval TI = new TimeInterval(startTime, endTime);
		Event newEvent = new Event(NameOfEvent, Date, TI); //create new time interval object
		
		ArrayList<Event> containsDate = new ArrayList<>();
		boolean conflicts = false; 
		for (Event e: events)
		{
			if (e.getDate().equals(newEvent.getDate()))
			{
				containsDate.add(e); //add events with the same date as new event created
			
			}
		}
		for (Event ev: containsDate)
		{	//check if new event conflicts with any existing events 
			if (ev.getTimeInterval().conflict(newEvent.getTimeInterval()) == true)
			{
				System.out.print("\n");
				System.out.println("Event conflicts with existing events on set date, new event cannot be added");
				conflicts = true;	
			}
		}
			//if no conflict, add the event to the calendar
		if (conflicts == false)
		{
			events.add(newEvent);
			System.out.print("\n");
			System.out.println("Event added successfully to calendar");
		}
		System.out.println("Press [G] to go back to Main Menu");
		while(scan.hasNext())	
		{	
			if (scan.next().toUpperCase().equals("G"))
			{
				this.runMyCalendar();
			}
		}
	}
	
	/**
	 * goTo - based on the date entered, that day is then displayed in day view
	 */
	public void goTo()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter date [mm/dd/yyyy]");
		String dateAsString = scan.next(); //collect day entered
		
		DateTimeFormatter df = DateTimeFormatter.ofPattern("M/d/yyyy");
		LocalDate date = LocalDate.parse(dateAsString, df);
		current = date;
		
		this.ViewByDay(); //use ViewByDay method to display date
		
	}
	
	/**
	 * eventList - displays all the events on the calendar in a list view
	 */
	public void eventList()
	{
		TreeSet<Event> sort = new TreeSet<>(events); //create TreeSet to help sort events
		ArrayList<Event> sorted = new ArrayList<>(sort);
		int year = sorted.get(0).getDate().getYear();
		System.out.println(year); 
		for (int i = 0; i < sorted.size(); i++)
		{
			Event e = sorted.get(i);
			if (e.getDate().getYear() == year) //checks while events are in the same year
			{
				e.printEventListFormat(); //prints event in list form
			}
			else //else print new year and then start printing events from that year
			{
				year = e.getDate().getYear();
				System.out.println(year);
				e.printEventListFormat();
			}
		}
		Scanner scan = new Scanner (System.in);
		System.out.println(" ");
		System.out.println("Press [G] to go back to main menu");
		
		while (scan.hasNext()) //option to return to main menu
		{
			if (scan.next().toUpperCase().equals("G"))
			{
				this.runMyCalendar();
			}
			else
			{
				System.out.print("\nInvalid Option");
			}
		}
	}
	
	/**
	 * delete - deletes a selected event by capturing the events date and name or 
	 * deletes all the events on a certain date
	 */
	public void delete()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Delete [S]elected or [A]ll?");
		String command = scan.next();
		if (command.toUpperCase().equals("S")) 
		{	//if user selects S, ask for date and event name to specifically delete a event
			System.out.println("Enter the date [mm/dd/yyyy]");
			String dateAsString = scan.next();
			DateTimeFormatter df = DateTimeFormatter.ofPattern("M/d/yyyy");
			LocalDate date = LocalDate.parse(dateAsString, df);
			current = date;
			this.ViewByDay();
			
			System.out.print("\n");
			System.out.println("Enter name of event to delete:");
			boolean deleted = false;
			scan.nextLine();
			String nameOfEvent = scan.nextLine();
			TreeSet<Event> hasDate = new TreeSet<>();
			//use tree set to order events
			for (Event e: events)
			{
				if (e.getDate().equals(date))
				{
					hasDate.add(e);
				}
			}
			//removes event if event exists
			ArrayList<Event> sorted = new ArrayList<>(hasDate);
			for (Event toCompare: sorted)
			{
				if (toCompare.getName().equals(nameOfEvent))
				{
					events.remove(toCompare);
					deleted = true;
					System.out.print("\n");
					System.out.println("Event deleted, here is the current schedule for today");
					System.out.print("\n");
					this.ViewByDay();
				}
			} //if event does not exists, print error message
			if (deleted == false) 
			{
				System.out.println("Error: There is no such event");
			}
			//give option to return to main menu
			System.out.print("\n");
			System.out.println("press [G] to go back to main menu");
			
			while (scan.hasNext())
			{
				if (scan.next().toUpperCase().equals("G"))
				{
					this.runMyCalendar();
				}
				else
				{
					System.out.print("\nInvalid Option");
				}
			}	
		}
		else if (command.toUpperCase().equals("A"))
		{	//if user enters A, they want to delete every every event on a certain date
			System.out.println("Enter the date [MM/dd/yyyy]");
			String dateAsString = scan.next();
			DateTimeFormatter df = DateTimeFormatter.ofPattern("M/d/yyyy");
			LocalDate date = LocalDate.parse(dateAsString, df);
			current = date;
			//add events that contain a date to a tree set to be ordered
			TreeSet<Event> holder = new TreeSet<>();
			for (Event e: events)
			{
				if (e.getDate().equals(date))
				{
					holder.add(e);
				}
			}
			
			//delete every event that contains the date
			for (Event delete: holder)
			{
				events.remove(delete);
			}
			//return message telling user every event has been deleted
			System.out.print("\n");
			System.out.println("All events deleted");
			System.out.print("\n");
			this.ViewByDay();
			//give option to return to main menu
			System.out.print("\n");
			System.out.println("press [G] to go back to main menu");
			if (scan.next().toUpperCase().equals("G"))
			{
				this.runMyCalendar();
			}
		}
		
	}
	
	/**
	 * advanceByDay - advances the date by the amount of days indicated in the parameter
	 * @param days - the amount of days to advance the date by
	 * @return - the new date that is advanced by n days
	 */
	public LocalDate advanceByDay(int days)
	{
		return current = current.plusDays(days);
	}
	
	/**
	 * advanceByMonth - advances the date by the amount of months indicated in the parameter
	 * @param days - the amount of months to advance the date by
	 * @return the new date that is advanced by n months
	 */
	public LocalDate advanceByMonth(int months)
	{
		return current = current.plusMonths(months);
	}
	
	/**
	 * backByDay - moves date back by the amount of days indicated in the parameter
	 * @param days - the amount of days to move the date back
	 * @return the new date that is moved back by n days
	 */
	public LocalDate backByDay(int days)
	{
		return current = current.minusDays(days);
	}
	
	/**
	 * backByMonth - moves date back by the amount of months indicated in the parameter
	 * @param days - the amount of months to move the date back
	 * @return the new date that is moved back by n months
	 */
	public LocalDate backByMonth(int days)
	{
		return current = current.plusMonths(days);
	}
	
	/**
	 * runMyCalendar - starts initial calendar view on console displaying what functions are available
	 */
	public void runMyCalendar()
	{	//Print prompt asking user what function they desire
		Scanner sc = new Scanner(System.in);
		System.out.print("\n\n");
		System.out.println("Select one of the following options:");
		System.out.println("[L]oad  [V]iew by  [C]reate, [G]o to [E]vent list [D]elete  [Q]uit");
		
		while (sc.hasNextLine()) //checks what letter has been entered
		{
			
			String command = sc.next();
			if (command.toUpperCase().equals("L"))
			{
				this.load(); //use load method if user selects L
			}
			else if (command.toUpperCase().equals("V"))
			{	//ask user if they want day or month view if they select V
				System.out.println("[D]ay or [M]onth View?"); 
				command = sc.next();
				if (command.toUpperCase().equals("D")) 
				{
					this.ViewByDay(); //view the calendar by day view
					System.out.println(" ");
					System.out.println("[P]revious or [N]ext or [G]o back to main menu ?");
					while(sc.hasNextLine())
					{	//checks if the user wants to move forward a day or backwards a day
						command = sc.next();
						if (command.toUpperCase().equals("P"))
						{
							this.backByDay(1);
							this.ViewByDay();
							System.out.println(" ");
							System.out.println("[P]revious or [N]ext or [G]o back to main menu ?");
							
						}
						else if (command.toUpperCase().equals("N"))
						{
							this.advanceByDay(1);
							this.ViewByDay();
							System.out.println(" ");
							System.out.println("[P]revious or [N]ext or [G]o back to main menu ?");
						}
						else if (command.toUpperCase().equals("G"))
						{	//else, return to main menu
							this.runMyCalendar();
						}
						else
						{
							System.out.print("\nInvalid Option");
						}
					}	
			
				}//if not day view, then user can view the calendar in month view by selecting M
				else if (command.toUpperCase().equals("M")) 
				{
					this.ViewByMonth();
					System.out.print("\n\n");
					System.out.println("[P]revious or [N]ext or [G]o back to main menu ?");
					while(sc.hasNextLine())
					{
						command = sc.next();
						if (command.toUpperCase().equals("P")) //move month back by one
						{
							this.backByMonth(1);
							this.ViewByMonth();
							System.out.print("\n\n");
							System.out.println("[P]revious or [N]ext or [G]o back to main menu ?");
							
						}
						else if (command.toUpperCase().equals("N")) //move month forward by one
						{
							this.advanceByMonth(1);
							this.ViewByMonth();
							System.out.print("\n\n");
							System.out.println("[P]revious or [N]ext or [G]o back to main menu ?");
						}
						else if (command.toUpperCase().equals("G"))
						{
							this.runMyCalendar(); //return back to main menu
						}
						else
						{
							System.out.print("\nInvalid Option");
						}
					}	
					command = sc.next();
				}
				else
				{
					System.out.print("\nInvalid Option");
				}
			}
			else if (command.toUpperCase().equals("C"))
			{	//create new event if user enters C
				this.createEvent();
			}
			else if (command.toUpperCase().equals("G"))
			{	//go to a certain date if user enters G
				this.goTo();
				System.out.println(" ");
				System.out.println("[P]revious or [N]ext or [G]o back to main menu ?");
				while(sc.hasNextLine())
				{	//user could go to previous days from day view
					command = sc.next();
					if (command.toUpperCase().equals("P"))
					{
						this.backByDay(1);
						this.ViewByDay();
						System.out.println(" ");
						System.out.println("[P]revious or [N]ext or [G]o back to main menu ?");
						
					}//user could go to next day in day view
					else if (command.toUpperCase().equals("N"))
					{
						this.advanceByDay(1);
						this.ViewByDay();
						System.out.println(" ");
						System.out.println("[P]revious or [N]ext or [G]o back to main menu ?");
					}
					else if (command.toUpperCase().equals("G"))
					{
						this.runMyCalendar();	//return to main menu if user enters G
					}	
				}	
				command = sc.next();
			}
			else if (command.toUpperCase().equals("E"))
			{
				this.eventList(); //print event in list form if user enters E
			}
			else if (command.toUpperCase().equals("D"))
			{
				this.delete(); //delete an event(s) if user enters D
			}
			else if (command.toUpperCase().equals("Q"))
			{
				System.out.println("Goodbye!"); //exits out of the calendar if user enters Q
				return;
			}
			else //any other key is invalid
			{
				System.out.print("\nInvalid Option");
			}
		}
	}
}

			
		
		
	

