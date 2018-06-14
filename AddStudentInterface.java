import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

/**
 * RegistrationSystem class instantiates and manages a set of graphical user
 * interfaces to handle display and maintenance of student information.
 *
 * CSCE 155 Spring 2008
 * Assignment 6
 * @author Rodrigo Fuzon
 */

public class AddStudentInterface extends JFrame
{
	/**
	 * The default size of the main frame.
	 */
	public static final Dimension FRAME_SIZE = new Dimension(850,350);
	
	/**
	 * The minimum size of the main frame.
	 */
	public static final Dimension MIN_FRAME_SIZE = new Dimension(350,300);
	
	/**
	 * The default distance of the main frame from the X origin.
	 */
	public static final Point FRAME_ORIGIN = new Point(350,250);
	
	/**
	 * The action command used to construct a Students object and return it to
	 * the main system.
	 */
	public static final String RETURN_DATA = "Return Student Data";
	
	/**
	 * The action command used to search the Course Listing System for a course
	 * and associate it with the student record being written.
	 */
	public static final String FIND_COURSE = "Find Course";
	
	/**
	 * The action command used to clear all fields.
	 */
	public static final String CLEAR_DATA = "Clear Student Data";
	
	/**
	 * The date format used by this system.
	 */
	private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	
	/**
	 * Text fields for displaying course information.
	 */
	private JTextField[] courseFields;
	
	/**
	 * Text fields for displaying and entering student information.
	 */
	private JTextField[] studentFields;
	
	/**
	 * The course associated with the student record being written.
	 */
	private Courses course;
	private Students students;

        
        /**
	 * Default constructor for this class.
	 * @param listener An object that implements ActionListener and WindowListener, usually the object which instantiated this.
	 */
	public AddStudentInterface(EventListener listener)
	{
		Container contentPane;
		JButton doneBtn, clearBtn, cancelBtn;
		JPanel infoPanel, studentPanel, coursePanel;
		SpringLayout contentLayout, infoLayout;
		setResizable(false);
		setSize(FRAME_SIZE);
		setMinimumSize(MIN_FRAME_SIZE);
		setTitle("Add Student");
		setLocation(FRAME_ORIGIN);
		addWindowListener((WindowListener) listener);
		
		contentPane = getContentPane();
		contentPane.setLayout(contentLayout = new SpringLayout());

		// Student Information panel
		infoPanel = new JPanel(infoLayout = new SpringLayout());
		infoPanel.setBorder(BorderFactory.createTitledBorder
								("Student Information"));
		studentPanel = new JPanel();
		studentPanel.setLayout(
						new BoxLayout(studentPanel, BoxLayout.PAGE_AXIS));
		coursePanel = new JPanel();
		coursePanel.setLayout(new BoxLayout(coursePanel, BoxLayout.PAGE_AXIS));
		coursePanel.setBorder(BorderFactory.createTitledBorder
								("Associated Course Data"));
		
		/* ---- Student Information panel: Initializing 'Student' panel's
		 * labels.
		 */
		String[] studentLabelArray =
			{"Student Name", "Student ID", "Date of Registration",
			"Phone Number", "City", "State", "Zip Code", "Honors [Yes = 1] [No = 2]]", "RA [Yes = 1] [No = 2]", "TA [Yes = 1] [No = 2]"};
		int stLabels = studentLabelArray.length;
		studentFields = new JTextField[stLabels];
		for(int i = 0; i < stLabels; i++)
			studentFields[i] = new JTextField();
		
		/* ---- Student Information panel: Initializing 'Course' panel's
		 * labels.
		 */
		String[] courseLabelArray =
			{"Course Number", "Course Name", "Course Credit Hours",
			"College Fee", "Special Fee", "Scheduled Time",
			"Scheduled Days", "Scheduled Room", "Instructor Name"};
		int coLabels = courseLabelArray.length;
		courseFields = new JTextField[coLabels];
		for(int i = 0; i < coLabels; i++)
		{
			courseFields[i] = new JTextField();
			courseFields[i].setEditable(false);
   
		} // end for
		
		/* ---- Student Information panel: Initializing next/previous buttons.
		 */
		doneBtn = new JButton("Done");
		doneBtn.addActionListener((ActionListener) listener);
		doneBtn.setActionCommand(RETURN_DATA);
		clearBtn = new JButton("Clear");
		clearBtn.addActionListener((ActionListener) listener);
		clearBtn.setActionCommand(CLEAR_DATA);
		cancelBtn = new JButton("Find Course");
		cancelBtn.addActionListener((ActionListener) listener);
		cancelBtn.setActionCommand(FIND_COURSE);
		
		/* ---- Student Information panel: Arranging and adding 'Student'
		 * panel's components.
		 */
		JPanel[] studInfoLines = new JPanel[stLabels + 1];
		studentPanel.add(Box.createVerticalGlue());
		for(int i = 0; i < stLabels; i++)
		{
			studInfoLines[i] = new JPanel(new GridLayout(1,2,0,4));
			studInfoLines[i].add(new JLabel(studentLabelArray[i]));
			studInfoLines[i].add(studentFields[i]);
			studentPanel.add(studInfoLines[i]);
			studentPanel.add(Box.createVerticalGlue());
		}
		studInfoLines[stLabels] = new JPanel();
		studInfoLines[stLabels].setLayout(
			new BoxLayout(studInfoLines[stLabels], BoxLayout.LINE_AXIS));
		studInfoLines[stLabels].add(Box.createHorizontalGlue());
		studInfoLines[stLabels].add(doneBtn);
		studInfoLines[stLabels].add(Box.createHorizontalGlue());
		studInfoLines[stLabels].add(clearBtn);
		studInfoLines[stLabels].add(Box.createHorizontalGlue());
		studInfoLines[stLabels].add(cancelBtn);
		studInfoLines[stLabels].add(Box.createHorizontalGlue());
		studentPanel.add(studInfoLines[stLabels]);
		
		/* ---- Student Information panel: Arranging and adding 'Course'
		 * panel's components.
		 */
		JPanel[] courInfoLines = new JPanel[coLabels];
		coursePanel.add(Box.createVerticalGlue());
		for(int i = 0; i < coLabels; i++)
		{
			courInfoLines[i] = new JPanel(new GridLayout(1,2,0,4));
			courInfoLines[i].add(new JLabel(courseLabelArray[i]));
			courInfoLines[i].add(courseFields[i]);
			coursePanel.add(courInfoLines[i]);
			coursePanel.add(Box.createVerticalGlue());
		} // end for
		
		/* ---- Student Information panel: Arranging and adding 'Student' and
		 * 'Course' panels within the info panel.
		 */
		infoPanel.add(studentPanel);
		infoPanel.add(coursePanel);

		infoLayout.putConstraint(SpringLayout.NORTH, studentPanel, 0,
								SpringLayout.NORTH, infoPanel);
		infoLayout.putConstraint(SpringLayout.NORTH, infoPanel, 0,
								SpringLayout.NORTH, coursePanel);
		infoLayout.putConstraint(SpringLayout.WEST, coursePanel, 0,
								SpringLayout.HORIZONTAL_CENTER, infoPanel);
		infoLayout.putConstraint(SpringLayout.EAST, studentPanel, 0,
								SpringLayout.HORIZONTAL_CENTER, infoPanel);
		infoLayout.putConstraint(SpringLayout.EAST, coursePanel, 0,
								SpringLayout.EAST, infoPanel);
		infoLayout.putConstraint(SpringLayout.WEST, studentPanel, 0,
								SpringLayout.WEST, infoPanel);
		infoLayout.putConstraint(SpringLayout.SOUTH, infoPanel, 0,
								SpringLayout.SOUTH, coursePanel);
		infoLayout.putConstraint(SpringLayout.SOUTH, studentPanel, 0,
								SpringLayout.SOUTH, infoPanel);
		
		contentPane.add(infoPanel);
		contentLayout.putConstraint(SpringLayout.NORTH, infoPanel, 0,
								SpringLayout.NORTH, contentPane);
		contentLayout.putConstraint(SpringLayout.SOUTH, contentPane, 0,
								SpringLayout.SOUTH, infoPanel);
		sideConstraint(infoPanel, contentPane, contentLayout);
		setVisible(true);
	} // end of constructor
	
	/**
	 * Constrains the west and east sides of 'inner' respectively to the west
	 * and east sides of 'outer'.  This is an internal method intended for use
	 * within the class.
	 * @param inner The inside component.
	 * @param outer The outside component.
	 * @param layout The layout manager used by 'outer'.
	 */
	private void sideConstraint(Component inner, Component outer, SpringLayout layout)
	{
		layout.putConstraint
			(SpringLayout.WEST, inner, 0, SpringLayout.WEST, outer);
		layout.putConstraint
			(SpringLayout.EAST, inner, 0, SpringLayout.EAST, outer);
	} // end of sideConstraint
	
	/**
	 * Clears all fields and resets.
	 */
	public void clear()
	{
		for (int i = 0; i < studentFields.length; i++)
			studentFields[i].setText("");
		for (int i = 0; i < courseFields.length; i++)
			courseFields[i].setText("");
		course = null;
	} // end of clear
	
	/**
	 * This method confirms that the data in the fields is valid information
	 * for a Students object.
	 * @return True if and only if the data is acceptable.
	 */
	public boolean inputIsValid()
	{
		boolean infoIsValid = true;
		String errorMessage = "";
		
		// TEST TO ENSURE THAT ALL INFORMATION IS VALID.
		// If any field begins or ends with a space, remove it.
		for (int i = 0; i < studentFields.length; i++)
		{
			String temp = studentFields[i].getText();
			if (temp.length() > 0)
			{
				while (temp.charAt(0) == ' ')
				{
					temp = temp.substring(1);
				} // end while
				while (temp.endsWith(" "))
				{
					temp = temp.substring(0, temp.length() - 2);
				} // end while
				studentFields[i].setText(temp);
			} // end if
		} // end for
		
		// Check that none of the fields are blank.
		{
			int i = 0;

			do {
				if (studentFields[i].getText().length() == 0)
				{
					infoIsValid = false;
					errorMessage = errorMessage.concat
						("Some of the fields are blank.  All fields are" +
								" required.\n");
				} // end if
				i++;
			} while (i < studentFields.length && infoIsValid);
		} // end code block
	
		// Check that Student ID contains only numbers.
		if (!studentFields[1].getText().matches("[0-9]{8}"))
		{
			infoIsValid = false;
			errorMessage = errorMessage.concat
				("Student ID must consist only of 8 digits, in the format:" +
						" 12345678\n");
		} // end if
		
		// Check that Registration Date is in the format 1-12/1-31/Year.
		try {
			Date date = sdf.parse(studentFields[2].getText());
			if (date.before(sdf.parse("1/1/1869")))
				errorMessage = errorMessage.concat
					("Year of registration must be later than 1869\n");
		} catch (ParseException e) {
			infoIsValid = false;
			errorMessage = errorMessage.concat
				("Registration date must be in the format MM/DD/YYYY\n");
		} // end try-catch block
		
		// Check that Phone Number is in the format ###-###-####.
		if (!studentFields[3].getText().matches("[0-9]{3}-[0-9]{3}-[0-9]{4}"))
		{
			infoIsValid = false;
			errorMessage = errorMessage.concat
				("Phone Number must be in the format: 123-456-7890\n");
		} // end if
		
		// Check that Zip Code is in the format ##### OR the format #####-####.
		if (!(studentFields[6].getText().matches("[0-9]{5}")
				|| studentFields[6].getText().matches("[0-9]{5}-[0-9]{4}")))
		{
			infoIsValid = false;
			errorMessage = errorMessage.concat
				("Zip Code must be in one of these formats: 12345 or" +
					" 12345-6789\n");
		} // end if
                // Check that Honors Code is in the format ##### OR the format #####-####.
		if (!(studentFields[7].getText().matches("[1-2]{1}")
				|| studentFields[7].getText().matches("[1-2]{1}")))
		{
			infoIsValid = false;
			errorMessage = errorMessage.concat
				("Please check if Honors is [Yes = 1] [No = 2]\n");
		} // end if
                // Check that RA Code is in the format ##### OR the format #####-####.
		if (!(studentFields[8].getText().matches("[1-2]{1}")
				|| studentFields[8].getText().matches("[1-2]{1}")))
		{
			infoIsValid = false;
			errorMessage = errorMessage.concat
				("Please check if RA is [Yes = 1] [No = 2]\n");
		} // end if
		// Check that TA Code is in the format ##### OR the format #####-####.
		if (!(studentFields[9].getText().matches("[1-2]{1}")
				|| studentFields[9].getText().matches("[1-2]{1}")))
		{
			infoIsValid = false;
			errorMessage = errorMessage.concat
				("Please check if TA is [Yes = 1] [No = 2]\n");
		} // end if
		// Check that a course has been associated with the student.
		if (course == null)
		{
			infoIsValid = false;
			errorMessage = errorMessage.concat
				("A course must be associated with the student\n");
		} // end if
		
		// IF THERE ARE PROBLEMS, INFORM THE USER.
		if (!infoIsValid)
		{
			JOptionPane.showMessageDialog(null,
					"I'm sorry, but I was unable to save.  Please correct the" +
					" following errors:\n\n"
					+ errorMessage);
			return false;
		} else
			return true;
	} // end of inputIsValid
	
	/**
	 * Copies a Courses object into this object and displays it.
	 */
	public void setCourse(Courses c)
	{
		this.course = c;
		DecimalFormat money = new DecimalFormat("$0.00");
		courseFields[0].setText(c.getCourseNumber());
		courseFields[1].setText(c.getCourseName());
		courseFields[2].setText("" + c.getCreditHours());
		courseFields[3].setText("" + money.format(c.getCollegeFee()));
		courseFields[4].setText("" + money.format(c.getSpecialFee()));
		courseFields[5].setText(c.getScheduledTime());
		courseFields[6].setText(c.getScheduledDays());
		courseFields[7].setText(c.getScheduledRoom());
		courseFields[8].setText(c.getInstructorName());

	} // end of setCourse
	
	/**
	 * Creates and returns a Students object.
	 * @return The Students object written by the user.
	 */
	public Students retrieve()
	{
		SimpleDateFormat sdf = new SimpleDateFormat();
		Date registrationDate;
		try { 
			registrationDate = sdf.parse(studentFields[2].getText());
		} catch (ParseException e) {
			registrationDate = new Date();
		} // end try-catch block
		Students temp = new Students(
				studentFields[0].getText(), studentFields[1].getText(), 
				registrationDate, studentFields[3].getText(), 
				studentFields[4].getText(), studentFields[5].getText(), 
				studentFields[6].getText());
                temp.setCourse(course);
                return temp;
	} // end of retrieve
	
        
} // end of AddStudentInterface
