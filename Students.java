/**
 * RegistrationSystem class instantiates and manages a set of graphical user
 * interfaces to handle display and maintenance of student information.
 *
 * CSCE 155 Spring 2008
 * Assignment 6
 * @author Rodrigo Fuzon
 */

//-----------------IMPORT STATEMENTS----------------------------
import java.util.*;
import java.io.Serializable;

public class Students implements Serializable {

	//-------------PUBLIC DATA MEMBERS - CONSTANTS---------------
    /**
     * Represents the technology fee as a percentage of base tuition.
     */
    public static final double TECHNOLOGY_FEE 	= 0.068; 
    /**
     * Represents the library fee as a percentage of base tuition.
     */
    public static final double LIBRARY_FEE 		= 0.02;   
    /**
     * Represents the base tuition per credit hour.
     */      
    public static final double TUITION_PER_HOUR = 125.0;
	/**
	 * Represents the flat registration fee.
	 */
	public static final double REGISTRATION_FEE = 20.0;
	/**
	 * Represents the name compare attribute.
	 */
    public static final int NAME = 0;
	/**
	 * Represents the ID compare attribute.
	 */
    public static final int ID = 1;
	/**
	 * Constant to represent less than.
	 */
    private static final int LESS = -1;
	/**
	 * Constant to represent equal.
	 */
    private static final int EQUAL = 0;
	/**
	 * Constant to represent greater than.
	 */
    private static final int MORE  = 1;
	/**
	 * Represents the currently set compare attribute.
	 */
    private static int compareAttribute;

    //-------PRIVATE DATA MEMBERS - VARIABLES----------------    
    /**
     * Represents the name for the student
     */
    private String	studentName;
    /**
     * Represents the ID for the student
     */
    private String	studentID;
    /**
     * Represents the date of registration
     */
 
    private Date	registrationDate;
    /**
     * Represents the course the student is taking
     */
    private Courses	course1;
    /**
     * Represents the student's official phone number
     */
    private String 	phoneNumber;
    /**
	 * Represents students current city
     */ 
	private String 	currentCity;
    /**
	 * Represents students current state
     */ 
	private String 	currentState;
    /**
	 * Represents students current zip code
     */ 
	private String 	currentZipCode;
        
	//-------------STATIC INITIALIZER----------------------------
    static {
        compareAttribute = NAME;
    }
	
    //-------------CONSTRUCTORS----------------------------------
    /**
     * DEFAULT constructor used to create this object.  Responsible for 
     * setting all of this object's information to DEFAULT values.
     */
    public Students(){
        this ("", "", new Date(), "", "", "", "");
    }//end of constructor
    
    /**
     * ASSIGNMENT constructor used to create this object.  Responsible for 
     * setting all of this object's information to ASSIGNED values which are
     * passed to the constructor.
     */
    public Students(String studentName, String studentID) {
		this (studentName, studentID, new Date(), "", "", "", "");
	}//end of constructor
    
    /**
     * ASSIGNMENT constructor used to create this object.  Responsible for 
     * setting all of this object's information to ASSIGNED values which are
     * passed to the constructor.
     */
    public Students(String studentName, String studentID, 
			Date registrationDate, String phoneNumber, 
			String currentCity, String currentState, 
			String currentZipCode) {

                this.studentName 		= studentName;
		this.studentID 			= studentID;
		this.registrationDate 	= registrationDate;
		this.course1 			= new Courses();
		this.phoneNumber 		= phoneNumber;
		this.currentCity 		= currentCity;
		this.currentState 		= currentState;
		this.currentZipCode 	= currentZipCode;
                
    }//end of constructor
    
	//--------------GETTER AND SETTER METHODS---------------------
	/**
	 * Returns the Courses object associated with this student.
	 * @return <code>Courses</code> The course associated with this student.
	 */
	public Courses getCourse() {
		return course1;
	}//end getCourse
	
	/**
	 * Sets the course associated with this student.
	 * @param course The course associated with this student.
	 */
	public void setCourse(Courses course) {
		this.course1 = course;
	}//end setCourse
	
	/**
	 * Returns the student's current city.
	 * @return <code>String</code> The student's current city.
	 */
	public String getCurrentCity() {
		return currentCity;
	}//end getCurrentCity
	
	/**
	 * Sets the student's city.
	 * @param currentCity The student's current city.
	 */
	public void setCurrentCity(String currentCity) {
		if (currentCity.equals(""))
			this.currentCity = "N/A";
		else
			this.currentCity = currentCity;
	}//end setCurrentCity
	
	/**
	 * Returns the student's current state.
	 * @return <code>String</code> The student's current state.
	 */
	public String getCurrentState() {
		return currentState;
	}//end getCurrentState
	
	/**
	 * Sets the student's state.
	 * @param currentState The student's current state.
	 */
	public void setCurrentState(String currentState) {
		if (currentState.equals(""))
			this.currentState = "N/A";
		else
			this.currentState = currentState;
	}//end setCurrentState
	/**
	 * Returns the student's current zip code.
	 * @return <code>String</code> The student's current zip code.
	 */
	public String getCurrentZipCode() {
		return currentZipCode;
	}//end getCurrentZipCode
	
	/**
	 * Sets the student's zip code.
	 * @param currentZipCode The student's current zip code.
	 */
	public void setCurrentZipCode(String currentZipCode) {
		if (currentZipCode.equals(""))
			this.currentZipCode = "N/A";
		else
			this.currentZipCode = currentZipCode;
	}//end setCurrentZipCode
	
	/**
	 * Returns the student's phone number.
	 * @return <code>String</code> The student's phone number.
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}//end getPhoneNumber
	
	/**
	 * Sets the student's phone number.
	 * @param phoneNumber The student's phone number.
	 */
	public void setPhoneNumber(String phoneNumber) {
		if (phoneNumber.equals(""))
			this.phoneNumber = "N/A";
		else
			this.phoneNumber = phoneNumber;
	}//end setPhoneNumber
	
	/**
	 * Returns the student's registration date.
	 * @return <code>Date</code> The registration date.
	 */
	public Date getRegistrationDate(){
		return registrationDate;
	}//end getRegistrationDate
	
	/**
	 * Sets this student's registration date.
	 * @param registrationDate The registration date.
	 */
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}//end setRegistrationDate
	
	/**
	 * Returns the student's ID from this object
	 * @return <code>String</code> ID of the Student represented in object
	 */
	public String getStudentID() {
		return studentID;
	}//end getStudentID
	
	/**
	 * Sets the student ID represented by this object
	 * @param id The ID of the student
	 */
	public void setStudentID(String id) {
		if (id.equals(""))
			this.studentID = "N/A";
		else
			this.studentID = id;
	}//end setStudentID
	
    /**
     * Returns the student's name from this object 
     * @return <code>String</code> Name of the Student represented in object
     */
	public String getStudentName() {
		return studentName;
	}//end getStudentName
	
    /**
     * Sets the student name represented by this object.
     * @param name The name of the student.
     */
	public void setStudentName(String name) {
		if (name.equals(""))
			this.studentName = "N/A";
		else
			this.studentName = name;
	}//end setStudentName
	
	//------------------OTHER METHODS--------------------------------
    /**
     * Sets the attribute used in comparing two Students objects
     * @param attribute attribute of a Students used for comparing
     */
    public static void setCompareAttribute(int attribute) {
        compareAttribute = attribute;
    }//end setCompareAttribute

    /**
     * Compares this Students object with the parameter Students
     * using attribute as a comparison criteria
     * @param student a student to compare with this student
     * @param attribute attribute used for comparison
     * @return <code>int</code> the comparison result
     */
    public int compareTo(Students student, int attribute ) {
        if ( attribute == ID ) {
            return (this.studentID.compareTo(student.getStudentID()));
        } else { //compare the name using the String classes compareTo method
            return (this.studentName.compareTo(student.getStudentName()));
        }
    }//end compareTo

    /**
     * Compares this Students object with the parameter student using 
     * currently set comparisonAttribute.
     * @param student a student to compare with this student
     * @return <code>int</code> the comparison result
     */
    public int compareTo(Students student) {
        return compareTo(student, compareAttribute);
    }//end compareTo

	/**
	 * Returns the total additional fees on the student's bill after
	 * calculating them by adding REGISTRATION_FEE, getTechFee(),
	 * Courses.getCourseSpecialFee(), and Courses.getCollegeFee().
	 * @return <code>double</code> The total additional fees.
	 */
	public double getAdditionalFees() {
		return REGISTRATION_FEE + getTechFee() +
				getLibraryFee() + course1.getSpecialFee() +
				course1.getCollegeFee();
	}//end getAdditionalFees
	
	/**
	 * Returns the total library fee for this student after calculating it 
	 * from the constant LIBRARY_FEE (represented as a fraction of base 
	 * tuition)
	 * @return <code>double</code> The total library fee.
	 */
	public double getLibraryFee() {
		return LIBRARY_FEE * getTuition();
	}//end getLibraryFee
	
	/**
	 * Returns the total technology fee for this student after calculating it
	 * from the constant TECHNOLOGY_FEE (represented as a fraction of base 
	 * tuition)
	 * @return <code>double</code> The total technology fee.
	 */
	public double getTechFee() {
		return TECHNOLOGY_FEE * getTuition();
	}//end getTechFee
	
	/**
	 * Returns the student's base tuition after calculating it from the number
	 * of credit hours and the constant TUITION_PER_HOUR.
	 * @return <code>double</code> Base tuition of the student object.
	 */
	public double getTuition() {
		return course1.getCreditHours() * TUITION_PER_HOUR;
	}//end getTuition
	
	/**
	 * This method prompts the user for all information pertaining to the 
	 * student. It also ensures that the information input will be stable 
	 * if entered into a file.
	 */
    public void enterData() {
    	String temp1 = "";
    	
    	// studentID input
    	System.out.print("Please enter the student ID: ");
    	temp1 = readString();
    	// Confirm that the input was exactly one word.
    	while (!hasWords(temp1, 1)) {
    		System.out.println("Input is invalid.");
    		System.out.print("Please enter the ID without spaces: ");
    		temp1 = readString();
    	}//end while
    	setStudentID(temp1);
    	
    	// studentName input
    	System.out.print("Please enter the student's first and last names: ");
    	temp1 = readString();
    	// Confirm that the name input was exactly two words.
    	while (!hasWords(temp1, 2))	{
    		System.out.println("Name input is invalid.");
    		System.out.print("Please enter ONLY the student's first and" +
    							" last names: ");
    		temp1 = readString();
    	}//end while
        setStudentName(temp1);
        
        // registrationDate input
        System.out.print("Please enter date of registration (MM/DD/YYYY): ");
        temp1 = readString();
        // Confirm that the date is exactly one word.
        while (!hasWords(temp1, 1)) {
        	System.out.println("Date input is invalid.  Please follow the " +
        						"format indicated.");
        	System.out.print("Please enter date of registration (MM/DD/YYYY)" +
        						": ");
        	temp1 = readString();
        }//end while
        setRegistrationDate(new Date(temp1));
         
        // phoneNumber input
        System.out.print("Please enter the student's phone number: ");
        temp1 = readString();
        // Confirm that the number is exactly one word.
        while (!hasWords(temp1, 1)) {
        	System.out.println("Number is invalid.  Please do not include " +
        						"spaces in the input.");
        	System.out.print("Please enter the student's phone number: ");
        	temp1 = readString();
        }//end while
        setPhoneNumber(temp1);
        
        // currentCity input
        System.out.print("Please enter the student's current city: ");
        temp1 = readString();
        while (!hasWords(temp1, 1)) {
        	System.out.println("Input is invalid.  Please type the city's " +
        						"name as one word.");
        	temp1 = readString();
        }//end while
        setCurrentCity(temp1);
        
        // currentState input
        System.out.print("Please enter the student's current state: ");
        temp1 = readString();
        while (!hasWords(temp1, 1)) {
        	System.out.println("Input is invalid.  Please ensure that there" +
        						" are no spaces.");
        	temp1 = readString();
        }//end while
        setCurrentState(temp1);

        // currentZipCode input
        System.out.print("Please enter the student's zip code: ");
        temp1 = readString();
        while (!hasWords(temp1, 1)) {
        	System.out.println("Input is invalid.  Please ensure that " +
        						"there are no spaces.");
        	temp1 = readString();
        }//end while
        setCurrentZipCode(temp1);
    }//end enterData
    
    /**
     * Breaks the argument string into an array of individual words,
     * returning true if it has a number of words equal to the
     * argument words.
     * @param string
     * @param words
     * @return <code>boolean</code> The result of the test.
     */
    private boolean hasWords(String string, int words) {
    	// First, test for a case in which the string has zero words.
    	if (words != 0 && string.equals(""))
    		return false;
    	// Then test for all other cases.
    	return (string.split(" ").length == words);
    }//end hasWords
    
    /**
     * Returns true if this Student has a valid assigned ID.
     * @return <code>boolean</code> The result of the test.
     */
    public boolean exists() {
    	return (this.getStudentID() != "");
    }//end exists
    
    /**
     * This method reads a string and returns it to the caller of the method.
     * @return <code>String</code> String read from keyboard
     */
    private String readString() {
        String userInput = "";
        Scanner scanner = new Scanner(System.in);
        userInput = scanner.nextLine();
        return userInput;
    }//end readString
    
}//end class Students
