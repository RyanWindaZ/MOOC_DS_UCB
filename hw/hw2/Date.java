/* Date.java */

import java.io.*;

class Date {

  /* Put your private data fields here. */
  private int month;
  private int day;
  private int year;

  /** Constructs a date with the given month, day and year.   If the date is
   *  not valid, the entire program will halt with an error message.
   *  @param month is a month, numbered in the range 1...12.
   *  @param day is between 1 and the number of days in the given month.
   *  @param year is the year in question, with no digits omitted.
   */
  public Date(int month, int day, int year) {
    if(!this.isValidDate(month, day, year)) throw new IllegalArgumentException("Invalid Date");
        
    this.month = month;
    this.day   = day;
    this.year  = year;
  }

  /** Constructs a Date object corresponding to the given string.
   *  @param s should be a string of the form "month/day/year" where month must
   *  be one or two digits, day must be one or two digits, and year must be
   *  between 1 and 4 digits.  If s does not match these requirements or is not
   *  a valid date, the program halts with an error message.
   */
  public Date(String s) {
    String[] sArray = new String[3];
    
    if(s == null || !this.isValidDate(s)) throw new IllegalArgumentException("Invalid Date");
        
    sArray = s.trim().split("/");
    this.month = Integer.parseInt(sArray[0]);
    this.day   = Integer.parseInt(sArray[1]);
    this.year  = Integer.parseInt(sArray[2]);
  }

  /** Checks whether the given year is a leap year.
   *  @return true if and only if the input year is a leap year.
   */
  public static boolean isLeapYear(int year) {
    return (((year % 400) == 0) || (((year % 4) == 0) && ((year % 100) != 0)));
  }

  /** Returns the number of days in a given month.
   *  @param month is a month, numbered in the range 1...12.
   *  @param year is the year in question, with no digits omitted.
   *  @return the number of days in the given month.
   */
  public static int daysInMonth(int month, int year) {
    switch(month) {
        case  1: return 31;
        case  2:
            if(isLeapYear(year)) return 29;
            else                 return 28;
        case  3: return 31;
        case  4: return 30;
        case  5: return 31;
        case  6: return 30;
        case  7: return 31;
        case  8: return 31;
        case  9: return 30;
        case 10: return 31;
        case 11: return 30;
        case 12: return 31;
        default: return 0;
    }
  }

  /** Checks whether the given date is valid.
   *  @return true if and only if month/day/year constitute a valid date.
   *
   *  Years prior to A.D. 1 are NOT valid.
   */
  public static boolean isValidDate(int month, int day, int year) {
    if(year < 1) 
        return false;
    else if(month < 1 && month > 12)
        return false;
    else if(day < 1 && day > 31)
        return false;
    else if((month == 4 || month == 6 || month == 9 || month == 11) & day > 30)
        return false;
    //else if((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && day > 31)
    //    return false;
    else if((month == 2 && isLeapYear(year) && day > 29) || (month == 2 && !isLeapYear(year) && day > 28))
        return false;
    else
        return true;
  }
  
  /// Rayn method Start ///
  public static boolean isValidDate(String s) {
    int yearValue, monthValue, dayValue;
    String[] sArray = s.trim().split("/");
    
    if(sArray.length != 3) throw new IllegalArgumentException("Invalid Date");
    
    yearValue  = convertToInt(sArray[0]);
    monthValue = convertToInt(sArray[1]);
    dayValue   = convertToInt(sArray[2]);
    
    return isValidDate(yearValue, monthValue, dayValue);
  }
  
  private static int convertToInt(String s) {
    int dateValue;
    
    try {
        return dateValue = Integer.parseInt(s);
    } catch(NumberFormatException e) {
        return dateValue = -1;
    }
  }
  /// Ryan method End ///

  /** Returns a string representation of this date in the form month/day/year.
   *  The month, day, and year are expressed in full as integers; for example,
   *  12/7/2006 or 3/21/407.
   *  @return a String representation of this date.
   */
  public String toString() {
    return this.month + "/" + this.day + "/" + this.year;                     // replace this line with your solution
  }

  /// Rayn method Start ///
  private boolean isEq(Date d) {
    return this.year == d.year && this.month == d.month && this.day == d.day;
  }
  
  private int yearDayNum(int year) {
    if(isLeapYear(year)) return 366;
    else                 return 365;
  }
  /// Ryan method End /// 
  
  /** Determines whether this Date is before the Date d.
   *  @return true if and only if this Date is before d. 
   */
  public boolean isBefore(Date d) {
        
    //year
    if(this.year > d.year)
        return false;
    if(this.year < d.year)
        return true;
    //month, year equal
    if(this.month > d.month)
        return false;
    if(this.month < d.month) 
        return true;
    //date, month equal
    if(this.day > d.day)
        return false;
    if(this.day < d.day) 
        return true;
    //all equal
    return false;
  }

  /** Determines whether this Date is after the Date d.
   *  @return true if and only if this Date is after d. 
   */
  public boolean isAfter(Date d) {
    return !this.isBefore(d) && !this.isEq(d);
  }

  /** Returns the number of this Date in the year.
   *  @return a number n in the range 1...366, inclusive, such that this Date
   *  is the nth day of its year.  (366 is used only for December 31 in a leap
   *  year.)
   */
  public int dayInYear() {
    int numOfDay = 0;
    
    for(int i = 1; i < this.month; i++) {
        numOfDay = numOfDay + this.daysInMonth(i, this.year);
    }
    
    return numOfDay = numOfDay + this.day;
  }

  /** Determines the difference in days between d and this Date.  For example,
   *  if this Date is 12/15/2012 and d is 12/14/2012, the difference is 1.
   *  If this Date occurs before d, the result is negative.
   *  @return the difference in days between d and this date.
   */
  public int difference(Date d) {
       int dayDiff = 0;
       Date greaterDay = d;
       Date smallerDay = d;
    
    if(this.isBefore(d)) {
        smallerDay = this;
    }
    if(this.isAfter(d)) {
        greaterDay = this;
    }
    
    if(this.isEq(d)) {
        return 0;
    }
    else if(this.year == d.year) {
        return this.dayInYear() - d.dayInYear();
    }
    else {
        for(int y = smallerDay.year; y < greaterDay.year; y++) {
            dayDiff = dayDiff + yearDayNum(y);
        }
        
        dayDiff = dayDiff + greaterDay.dayInYear() - smallerDay.dayInYear();
        
        if(this.isBefore(d))
            return -dayDiff;
        else
            return dayDiff;
    }
  }

  public static void main(String[] argv) {
    System.out.println("\nTesting constructors.");
    Date d1 = new Date(1, 1, 1);
    System.out.println("Date should be 1/1/1: " + d1);
    d1 = new Date("2/4/2");
    System.out.println("Date should be 2/4/2: " + d1);
    d1 = new Date("2/29/2000");
    System.out.println("Date should be 2/29/2000: " + d1);
    d1 = new Date("2/29/1904");
    System.out.println("Date should be 2/29/1904: " + d1);

    d1 = new Date(12, 31, 1975);
    System.out.println("Date should be 12/31/1975: " + d1);
    Date d2 = new Date("1/1/1976");
    System.out.println("Date should be 1/1/1976: " + d2);
    Date d3 = new Date("1/2/1976");
    System.out.println("Date should be 1/2/1976: " + d3);

    Date d4 = new Date("2/27/1977");
    Date d5 = new Date("8/31/2110");

    /* I recommend you write code to test the isLeapYear function! */

    System.out.println("\nTesting before and after.");
    System.out.println(d2 + " after " + d1 + " should be true: " + 
                       d2.isAfter(d1));
    System.out.println(d3 + " after " + d2 + " should be true: " + 
                       d3.isAfter(d2));
    System.out.println(d1 + " after " + d1 + " should be false: " + 
                       d1.isAfter(d1));
    System.out.println(d1 + " after " + d2 + " should be false: " + 
                       d1.isAfter(d2));
    System.out.println(d2 + " after " + d3 + " should be false: " + 
                       d2.isAfter(d3));

    System.out.println(d1 + " before " + d2 + " should be true: " + 
                       d1.isBefore(d2));
    System.out.println(d2 + " before " + d3 + " should be true: " + 
                       d2.isBefore(d3));
    System.out.println(d1 + " before " + d1 + " should be false: " + 
                       d1.isBefore(d1));
    System.out.println(d2 + " before " + d1 + " should be false: " + 
                       d2.isBefore(d1));
    System.out.println(d3 + " before " + d2 + " should be false: " + 
                       d3.isBefore(d2));

    System.out.println("\nTesting difference.");
    System.out.println(d1 + " - " + d1  + " should be 0: " + 
                       d1.difference(d1));
    System.out.println(d2 + " - " + d1  + " should be 1: " + 
                       d2.difference(d1));
    System.out.println(d3 + " - " + d1  + " should be 2: " + 
                       d3.difference(d1));
    System.out.println(d3 + " - " + d4  + " should be -422: " + 
                       d3.difference(d4));
    System.out.println(d5 + " - " + d4  + " should be 48762: " + 
                       d5.difference(d4));
  }
}
