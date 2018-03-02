import java.util.Scanner;
/**
@author: Kevin Bishop
Date: 2018-02-24
*/

public class Calendar
/**
Prints a month from a calendar in an aesthetically pleasing way based
on user input month and year. Calculates leap years, days of weeks etc.
*/
{
  /**
  Prints the name of the month based on its integer (1-12).
  @param m integer of month
  @return month name in String format
  */
  public static String getMonthName(int m)
  {
    String[] months = {"January", "February", "March", "April", "May", "June",
      "July", "August", "September", "October", "November", "December"};
    //subtract 1 to index from 0
    return months[m-1];
  }

  /**
  Returns the first day of the week for a given month and year.
  If the first day is monday it returns 1, if Tuesday 2 etc.
  @param month int of month
  @param year int of year
  @return dayNum int day of week of first day in month
  */
  public static int getStartDay(int month, int year)
  {
    // The method getStartDay() implements Zeller's Algorithm for determining the
    // day of the week the first day of a month is. The method adjusts Zeller's
    // numbering scheme for day of week ( 0=Saturday, ..., 6=Friday ) to conform to
    // a day of week number that corresponds to ISO conventions (i.e., 1=Monday,
    // ..., 7=Sunday)
    //
    // Pre-Conditions: The month value, m, is 1-12 The day value, d, is 1-31, or
    // 1-28, 1-29 for February The year value, y, is the full year (e.g., 2012)
    //
    // Post-Conditions: A value of 1-7 is returned, representing the first day of
    // the month 1 = Monday, ..., 7 = Sunday
    //

     if (month < 3)                   // Adjust month number & year to fit Zeller's numbering system
     {
        month += 12;
        year -= 1;
     }

     int centuryYear = year % 100;    // Calculate year within century
     int centuryTerm = year / 100;    // Calculate century term
     int firstDayinMonth = 0;         // Day number of first day in month 'm'
     int day = 1;

     firstDayinMonth = (day + (13 * (month + 1) / 5) + centuryYear + (centuryYear / 4) + (centuryTerm / 4) + (5 * centuryTerm)) % 7;

     // Convert Zeller's value to ISO value (1 = Mon, ... , 7 = Sun )
     int dayNum = ((firstDayinMonth + 5) % 7) + 1;

     return dayNum;
  }

  /**
  Prints the number of days in the requested month. Also can handle leap years.
  @param month int of month
  @param year int of year
  @return number of days in the month as an int
  */
  public static int getNumDaysInMonth(int m, int y)
  {
    //int array of total days each month
    m--; //index from 0
    int[] numDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    if (isLeapYear(y) & m == 1)
    {
      //leap year exception
      return 29;
    }
    return numDays[m];
  }

  /**
  @param year int of year
  @return isLeap boolean indicating whether year is a leap year
  */
  public static boolean isLeapYear(int y)
  {
    boolean isLeap = false;
    if (y%4 == 0)
    {
      isLeap = true;
      if (y %100 == 0)
      {
        isLeap = false;
        if (y%400 == 0)
        {
          isLeap = true;
        }
      }
    }
    return isLeap;
  }
  /**
  Prints the header and body of the calendar for the requested year and month.
  */
  public static void printMonthCalendar(int m, int y)
  {
    printMonthHeader(m, y);
    printMonthBody(m, y);
  }

  /**
  Prints the header of the calendar for the requested year and month.
  */
  public static void printMonthHeader(int m, int y)
  {
    String monthName = getMonthName(m);
    String output = "\t" + monthName + " " + y + "\n";
    for (int i = 0; i < 29; i++)
    {
      output += "-";
    }
    output += "\n Sun Mon Tue Wed Thu Fri Sat";
    System.out.println(output);
  }

  /**
  Prints the body of teh calendar for the requested year and month.
  */
  public static void printMonthBody(int m, int y)
  {
    int startDay = getStartDay(m, y);
    int numDaysInMonth = getNumDaysInMonth(m, y);
    int dayOfMonth = 1;
    String output = "";
    int dayOfWeek = 0;
    if (startDay == 7)
    {
      //If start day is sunday (7) overwrite as 0 since we index at 0
      startDay = 0;
    }
    int i = 0;
    //Keep looping until we run out of days
    while (dayOfMonth <= numDaysInMonth)
    {
      if (i >= startDay)
      {
        if (dayOfMonth < 10)
        {
          //print extra space since "9" is one character but "10" is two
          output += " ";
        }
        //append the day of the month to the output
        //and some blank space for formatting
        output += "  " + dayOfMonth;
        dayOfMonth++;
      }
      else
      {
        //If we haven't reach the first day yet, print 4 blank spaces
        output += "    ";
      }
      if (dayOfWeek == 6)
      {
        //After Saturday, start a new line in the output string
        output += "\n";
        dayOfWeek = -1;
      }
      //increment day of week and counter
      dayOfWeek++;
      i++;
    }
    System.out.println(output);
  }

  public static void main(String[] args)
  {
    int month = 0; //User input month
    int year = 0; //User input Year
    //Create scanner to read user input
    Scanner scn = new Scanner(System.in);
    System.out.print("Enter a month: (1-12)");
    month = scn.nextInt(); //index from 0
    System.out.print("Enter a year (e.g. 2006): ");
    year = scn.nextInt();

    //Print the requested calendar
    printMonthCalendar(month, year);

  }
}
