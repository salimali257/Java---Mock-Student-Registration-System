/**
 * RegistrationSystem class instantiates and manages a set of graphical user
 * interfaces to handle display and maintenance of student information.
 *
 * CSCE 155 Spring 2008
 * Assignment 6
 * @author Rodrigo Fuzon
 */

//----------------IMPORT STATEMENTS--------------------------
import java.io.Serializable;
import java.util.*;


public class Courses implements Serializable{
	
	//------------PRIVATE VARIABLES--------------------------
	/**
	 * Represents the alphanumeric 'number' of the course.
	 */
	private String 	courseNumber;
	/**
	 * Represents the course's name.
	 */
	private String 	courseName;
	/**
	 * Represents the course's credit hours.
	 */
	private int 	creditHours;
	/**
	 * Represents the hourly college fee for this course.
	 */
	private double 	hourlyCollegeFee;
	/**
	 * Represents the course special fee.
	 */
	private double 	specialFee;
	/**
	 * Represents the scheduled time within the day.
	 */
	private String	scheduledTime;
	/**
	 * Represents the days on which the course will take place.
	 */
	private String	scheduledDays;
	/**
	 * Represents the room in which the course is scheduled.
	 */
	private String	scheduledRoom;
	/**
	 * Represents the name of the course's instructor.
	 */
	private String	instructorName;
	 	 
	//---------------CONSTRUCTOR---------------------------------
	/**
	 * Constructor used to create this object.  Responsible for setting all
	 * of this object's information to their corresponding default values.
	 */
	public Courses() {
		this.courseNumber 		= "";
		this.courseName 		= "";
		this.creditHours 		= 0;
		this.hourlyCollegeFee 	= 0;
		this.specialFee 		= 0;
		this.scheduledTime 		= "";
		this.scheduledDays 		= "";
		this.scheduledRoom 		= "";
		this.instructorName 	= "";
	}//end of constructor
	
	//-----------GETTER AND SETTER METHODS-------------------------
	/**
	 * Stores the college's hourly fee in the private data member
	 * hourlyCollegeFee.
	 * @param fee The college's fee per credit hour.
	 */
	public void setCollegeFee(double fee) {
		this.hourlyCollegeFee = fee;
	}//end setCollegeFee

	/**
	 * Returns the course's name.
	 * @return <code>String</code> The name of this course.
	 */ 
	public String getCourseName() {
		return courseName;
	}// end of getCourseName

	/**
	 * Takes a string and stores it in the data member courseName.
	 * @param name The name of the course.
	 */
	public void setCourseName(String name) {
		this.courseName = name;
	}//end setCourseName
	
	/**
	 * Returns the course's alphanumeric 'number' designation.
	 * @return <code>String</code> The course number.
	 */
	public String getCourseNumber() {
		return courseNumber;
	}//end getCourseNumber
	
	/**
	 * Takes a string and stores it in the data member courseNumber.
	 * @param number The course number.
	 */
	public void setCourseNumber(String number) {
		this.courseNumber = number;
	}//end setCourseNumber

	/**
	 * Returns the number of credit hours for the course.
	 * @return <code>int</code> The course's credit hours.
	 */
	public int getCreditHours() {
		return creditHours;
	}//end getCreditHours
	
	/**
	 * Takes an integer and stores it in the data member creditHours.
	 * @param hours The course's credit hours.
	 */
	public void setCreditHours(int hours) {
		this.creditHours = hours;
	}//end setCreditHours

	/**
	 * Returns the time for which the course is scheduled.
	 * @return <code>String</code> The course's time within the day.
	 */
	public String getScheduledTime() {
		return scheduledTime;
	}//end getScheduledTime
	
	/**
	 * Takes a String and stores it in the data member scheduledTime.
	 * @param scheduledTime The course's time within the day.
	 */
	public void setScheduledTime(String scheduledTime) {
		this.scheduledTime = scheduledTime;
	}//end setScheduledTime

	/**
	 * Returns the days for which the course is scheduled.
	 * @return <code>String</code> The course's scheduled days.
	 */
	public String getScheduledDays() {
		return scheduledDays;
	}//end getScheduledDays
	
	/**
	 * Takes a String and stores it in the data member scheduledDays.
	 * @param scheduledDays The course's scheduled days.
	 */
	public void setScheduledDays(String scheduledDays) {
		this.scheduledDays = scheduledDays;
	}//end setScheduledDays

	/**
	 * Returns the room for which the course is scheduled.
	 * @return <code>String</code> The course's assigned room.
	 */
	public String getScheduledRoom() {
		return scheduledRoom;
	}//end getScheduledRoom
	
	/**
	 * Takes a String and stores it in the data member scheduledRoom.
	 * @param scheduledRoom The course's assigned room.
	 */
	public void setScheduledRoom(String scheduledRoom) {
		this.scheduledRoom = scheduledRoom;
	}//end setScheduledRoom

	/**
	 * Returns the course's instructor.
	 * @return <code>String</code> The name of the course's instructor.
	 */
	public String getInstructorName() {
		return instructorName;
	}//end getInstructorName
	
	/**
	 * Takes a String and stores it in the data member instructorName.
	 * @param instructorName The name of the course's instructor.
	 */
	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}//end setInstructorName

	/**
	 * Returns the course's special fee.
	 * @return <code>double</code> The course special fee.
	 */
	public double getSpecialFee() {
		return specialFee;
	}//end getSpecialFee
	
	/**
	 * Takes a double and stores it in the data member courseSpecialFee.
	 * @param specialFee The course special fee.
	 */
	public void setSpecialFee(double specialFee) {
		this.specialFee = specialFee;
	}//end setSpecialFee
	
	//-------------OTHER METHODS---------------------------	 
	/**
	 * This method prints all information pertaining to this course.
	 */
	public void display(){
		System.out.println("Displaying Course Information: \n");
		System.out.println("     Course Number: " + getCourseNumber());
		System.out.println("     Course Name: " + getCourseName());
		System.out.println("     Credit Hours: " + getCreditHours());
		System.out.println("     College Fee: $" + getCollegeFee());
		System.out.println("     Special Fee: $" + getSpecialFee());
		System.out.println("     Scheduled Time: " + getScheduledTime());
		System.out.println("     Scheduled Days: " + getScheduledDays());
		System.out.println("     Scheduled Room: " + getScheduledRoom());
		System.out.println("     Instructor Name: " + getInstructorName());
		System.out.println();
	}//end display
    
	/**
	 * This method prompts the user for all information pertaining to the 
	 * course.
	 */
	public void enterData(){
		System.out.print("Please enter the course number: ");
		setCourseNumber(readString());
		System.out.print("Please enter the course name: ");
		setCourseName(readString());
		System.out.print("Please enter the course credit hours: ");
		setCreditHours(readInteger());
		System.out.print("Please enter the college fee for each credit hour " +
							"(in dollars): $");
		setCollegeFee(readInteger());
		System.out.print("Please enter the course special fee (in dollars)" +
							": $");
		setSpecialFee(readInteger());	  
	}//end enterData
	
	/**
	 * Returns the total college fee.
	 * @return <code>double</code> The total college fee for this course.
	 */
	public double getCollegeFee() {
		return (this.hourlyCollegeFee * getCreditHours());
	}//end getCollegeFee

	/**
	 * This method prints only the course's name, number, and instructor.
	 */
	public void partialDisplay(){
		System.out.println("     " + getCourseName() + " (" + 
							getCourseNumber() + "), taught by " + 
							getInstructorName());
	}//end partialDisplay
	  
	/**
	 * This method reads an integer and
	 * returns that integer to the caller of this method.
	 * @return <code>int</code> An integer entered by the user.
	 */
	private int readInteger()  {	
		int temp = 0;
		Scanner scanner = new Scanner(System.in);
		try {
			temp = scanner.nextInt();  // read integer
		}  catch (InputMismatchException ex) {
			System.out.println(ex);
		}
		return temp;
	}//end readInteger
	
	/**
	 * This method reads in a string and returns that string to the caller of
	 * this method.
	 * @return <code>String</code> A String entered by the user.
	 */
    private String readString() {
        String userInput = "";
        Scanner scanner = new Scanner(System.in);
        userInput = scanner.nextLine();
        return userInput;
    }//end readString
	
}//end class Courses
