import java.awt.event.*;
import java.io.*;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

/**
 * RegistrationSystem class instantiates and manages a set of graphical user
 * interfaces to handle display and maintenance of student information.
 *
 * CSCE 155 Spring 2008
 * Assignment 6
 * @author Rodrigo Fuzon
 */

public class RegistrationSystem implements ActionListener, WindowListener
{
	/**
	 * The file currently being worked on.
	 */
	private File currentFile;
	
	/**
	 * The list of Students objects to be managed.
	 */
	private Students[] students;
	
	/**
	 * The graphical user interface object employed by this system.
	 */
	private GUI userInterface;
	
	/**
	 * The DeleteStudentInterface object employed by this system.
	 */
	private DeleteStudentInterface studentDeleter;
	
	/**
	 * The AddStudentInterface object employed by this system.
	 */
	private AddStudentInterface studentAdder;
	
	/**
	 * Constructor.
	 */
	public RegistrationSystem()
	{
		userInterface = new GUI(this);
	} // end of constructor
	
	// GETTER AND SETTER METHODS------------------------------------------------
	/**
	 * Returns the number of students being handled.
	 * @return The number of valid students.
	 */
	public int getNumberOfStudents()
	{
		int validStudents = 0;
		int i = 0;
		boolean done = false;
		
		do {
			if (students[i] != null && students[i].exists())
				validStudents++;
			else
				done = true;
			i++;
		} while(i < students.length && !done);
		
		return validStudents;
	} // end of getNumberOfStudents
	
	/**
	 * Returns the arithmetic mean of the students' total amounts due.
	 * @return The arithmetic mean of the students' total fees.
	 */
	public double getMeanAmountDue()
	{
		int n = getNumberOfStudents();
		if (n == 0)
			return 0;
		else {
			double total = 0;
			for (int i = 0; i < n; i++)
			{
				total += (getTotalFee(i));
			} // end for
			return (total / n);
		} // end else-if
	} // end of getMeanAmountDue
	
	/**
	 * Returns the median of the students' total amounts due.
	 * @return The median of the students' fees.
	 */
	public double getMedianAmountDue()
	{
		int n = getNumberOfStudents();
		if (n == 0)
			return 0;
		else {
			int i = n / 2;
			if (n % 2 == 1)
				i++;
			return (getTotalFee(i-1));
		} // end if
	} // end of getMedianAmountDue
	
	/**
	 * Returns the standard deviation of the students' total amounts due.
	 * @return The standard deviation.
	 */
	public double getStandardDeviation()
	{
		int n = getNumberOfStudents();
		if (n == 0)
			return 0;
		else {
			double numerator = 0;
			double mean = getMeanAmountDue();
			for (int i = 0; i < n; i++)
				numerator += Math.pow((getTotalFee(i) - mean), 2);
			double standardDeviation = Math.sqrt(numerator / n);
			return standardDeviation;
		} // end if-else block
	} // end of getMedianAmountDue
	
	/**
	 * Convenience method for calculating total fees for a student.
	 * (This is ugly code but it is the only way to get around the Students
	 * class' lack of a proper getter for this without alienating already-made
	 * data files containing Students objects.)
	 * @param s The index number of the Students object in this object's array.
	 * @return The total fee.
	 */
	private double getTotalFee(int s)
	{
		return students[s].getAdditionalFees() + students[s].getTuition();
	} // end of getTotalFee
	
	
	private double getStudentID(int s)
	{
                return Double.parseDouble(students[s].getStudentID());
		//return Integer.parseInt(students[s].getStudentID());
	} // end of getStudentID
	/**
	 * Mutator method for currentFile, used when saving to a different file.
	 * @param f The input file sent to this object.
	 */
	private void setCurrentFile(File f)
	{
		currentFile = f;
	} // end of setCurrentFile
	
	// TEST METHODS

	/**
	 * Tests if the file passed genuinely contains data this program can use.
	 * @param f The File object to be tested.
	 * @return True if the first object in the file is a valid Students.
	 */
	private boolean fileIsReadable(File f)
	{
		try {
			ObjectInputStream input =
				new ObjectInputStream(new FileInputStream(f));
			try {
				Students test = (Students) input.readObject();
				if (!test.exists())
					return false;
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null,
						"This file may not be in the " +
						"format used by this program");
				return false;
			} catch (EOFException e){
				JOptionPane.showMessageDialog(null,
						"This file is blank.");
				return false;
			} finally {
				input.close();
			} // end try-catch-finally block
		} catch (IOException e) {
			if (!f.toString().equals(""))
				JOptionPane.showMessageDialog(null,
					"This file could not be accessed.");
			return false;
		} // end try-catch block
		return true;
	} // end of fileIsValid

	/**
	 * Checks whether the Students array is already sorted, returning true if
	 * it is and false otherwise.
	 * @return The result of the test.
	 */
	private boolean amountIsSorted()
	{
		for (int i = 1; i < getNumberOfStudents(); i++)
			if (getTotalFee(i) < getTotalFee(i-1))
				return false;
		return true;
	} // end of amountIsSorted
	

	// SUPPORT METHODS----------------------------------------------------------
	/**
	 * Uses the course listing system to find a course and send it to the
	 * AddStudentInterface.
	 */
	private void addCourseToStudent()
	{
		CourseListingSystem cls = new CourseListingSystem();
		Courses temp = cls.findCourse();
		if (temp != null)
			studentAdder.setCourse(temp);
	} // end of addCourseToStudent

	/**
	 * Determines the number of student records in the file being managed
	 * and returns it as an integer.
	 * @return The number of Students objects.
	 */
	private int countStudentsInFile()
	{
		int studentsInFile = 0;
		boolean done;
		do
		{
			done = true;
			String temp = JOptionPane.showInputDialog(
				"How many student records are in this file?");
			if (temp == null || temp.equals(""))
				return 0;
			try {
				studentsInFile = Integer.parseInt(temp);
				if (studentsInFile < 1)
				{
					String message = "A positive, non-zero integer is required.";
					JOptionPane.showMessageDialog(null, message);
					done = false;
				} // end if
			} catch (NumberFormatException e) {
				String message = "An integer is required.";
				JOptionPane.showMessageDialog(null, message);
				done = false;
			} // end try-catch block
		} while (!done);
		return studentsInFile;
	} // end of countStudentsInFile

	/**
	 * Reads the current file into a newly-initialized array of Students.
	 * @throws IOException
	 */
	private void readFile() throws IOException
	{
		ObjectInputStream input =
			new ObjectInputStream(new FileInputStream(currentFile));
		int n = countStudentsInFile();
		if (n > 0)
		{
			students = new Students[n + 20];
			
			int i = 0;
			try {
				for (i = 0; i < n; i++)
					students[i] = (Students) input.readObject();
			} catch (ClassNotFoundException e) {
				String msg;
				msg = "This file may not be in the format used by this program.";
				JOptionPane.showMessageDialog(null, msg);
			} catch (EOFException e){
				String msg = "This file only contains " + i + " student records."
								+ "  Please check to ensure that this is the file you"
								+ " intended to load.";
				JOptionPane.showMessageDialog(null, msg);
			} finally {
				for (int j = i; j < students.length; j++)
					students[j] = new Students();
				input.close();
			} // end try-catch-finally block
		} // end if
	} // end of readFile

	/**
	 * Updates the GUI with all information it needs to display regarding
	 * individual student accounts.
	 */
	private void updateGUI()
	{
		String[][] textDump = new String[getNumberOfStudents()][4];
		DecimalFormat money = new DecimalFormat("$0.00");
		for (int i = 0; i < getNumberOfStudents(); i++)
		{
			textDump[i][0] = students[i].getStudentName();
			textDump[i][1] = students[i].getStudentID();
			textDump[i][2] = students[i].getCourse().getCourseName();
			textDump[i][3] = "" + money.format(getTotalFee(i));
		} // end for
		userInterface.displayText(textDump);
		
		if (getNumberOfStudents() == 0)
		{
			userInterface.changeMode(GUI.NO_STUDENT_DATA_MODE);
			setCurrentFile(null);
		}
		else if (amountIsSorted()) 
			userInterface.changeMode(GUI.ARRAY_SORTED_MODE);
		else
			userInterface.changeMode(GUI.ARRAY_UNSORTED_MODE);
	} // end of displayStudents

	/**
	 * Asks the user if they want to save the current file.
	 * @return An integer value from JOptionPane's response constants.
	 */
	private int wantToSave()
	{
		if (userInterface.getMode() == GUI.NO_STUDENT_DATA_MODE)
			return JOptionPane.NO_OPTION;
		else
		{
			String msg = "This action will write over all data in the system. " +
				"Would you like to save before proceeding?";
			int answer = JOptionPane.showConfirmDialog(null, msg);
			if (answer == JOptionPane.YES_OPTION)
				saveFile();
			return answer;
		} // end if-else block
	} // end of wantToSave

	// PRIMARY METHODS----------------------------------------------------------
	/**
	 * Creates a new empty file and sets it as the currently open file.
	 */
	private void newFile()
	{
		if (wantToSave() != JOptionPane.CANCEL_OPTION)
		{
			students = new Students[30];
			for (int i = 0; i < students.length; i++)
				students[i] = new Students();
			setCurrentFile(null);
			beginAddStudent();
		} // end if
	} // end of newFile
	
	/**
	 * Loads the passed file into the registration system.
	 * @param f An input file to be loaded into the system.
	 */
	private void openFile()
	{
		if (wantToSave() != JOptionPane.CANCEL_OPTION)
		{
			File f = userInterface.selectOpenFile();
			if (fileIsReadable(f))
			{
				setCurrentFile(f);
				
				try {
					readFile();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, e.getStackTrace());
				} // end try-catch block

				updateGUI();
			} // end if
		} // end if
	} // end of openFile
	
	/**
	 * Saves all current data into the currently selected file.
	 */
	private void saveFile()
	{
		if (currentFile != null && currentFile.exists())
		{
			try {
				ObjectOutputStream output =
					new ObjectOutputStream(new FileOutputStream(currentFile));
				int i = 0, n = getNumberOfStudents();
				try {
					for (i = 0; i < n; i++)
						output.writeObject(students[i]);
					JOptionPane.showMessageDialog(null,
							i + " student records have been saved to the file " +
							currentFile);
				} finally {
					output.close();
				} // end try-catch-finally block
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getStackTrace());
			} // end try-catch block
		} else {
			saveAs();
		} // end if-else black
	} // end of saveFile
	
	/**
	 * Finds a new file and saves to it.
	 */
	private void saveAs()
	{
		File f = userInterface.selectSaveFile();
		if (!f.toString().equals(""))
		{
			System.out.println(!f.exists());
			if (!f.exists())
			{
				try {
					f.createNewFile();
				} catch (IOException e) {
				} // end try-catch block
			} // end if
			setCurrentFile(f);
			saveFile();
		} // end if
	} // end of saveAs
	
	/**
	 * Asks user to confirm that he/she wishes to quit, then quits.
	 */
	private void quit()
	{
		int answer = JOptionPane.NO_OPTION;
		
		if (userInterface.getMode() != GUI.NO_STUDENT_DATA_MODE)
		{
			String msg = "Would you like to save before quitting?";
			answer = JOptionPane.showConfirmDialog(null, msg);
			if (answer == JOptionPane.YES_OPTION)
				saveFile();
		} // end if
		
		if (answer != JOptionPane.CANCEL_OPTION)
			System.exit(0);
	} // end of quit
	
	/**
	 * Creates an AddStudentInterface that allows the user to create a new
	 * Students record and insert it into the system.
	 */
	private void beginAddStudent()
	{
		// Confirm that there is an open space before the end of the array.
		boolean isOpenSpace = ( students[students.length-1] == null
									|| !students[students.length-1].exists() );
		
		if (isOpenSpace)
		{
			userInterface.setEnabled(false);
			studentAdder = new AddStudentInterface(this);
		} else {
			String message = "There is not enough space for another record.";
			JOptionPane.showMessageDialog(null, message);
		} // end if-else
	} // end of beginAddStudent
	
	/**
	 * Finishes the process of adding a new student and inserts the
	 * Students object into the Students array in this class.
	 */
	private void finishAddStudent()
	{	
		if(studentAdder.inputIsValid())
		{
			Students temp = studentAdder.retrieve();
			int i = 0;
			boolean done = false;
			do
			{
				if (students[i] == null || !students[i].exists())
				{
					students[i] = temp;
					done = true;
				} // end if
				i++;
			} while (i < students.length && !done);
			
			studentAdder.dispose();
			updateGUI();
		} // end if
	} // end of finishAddStudent
	
	/**
	 * Begins the process of deleting a list of students.
	 */
	private void beginDeleteStudents()
	{
		int n = getNumberOfStudents();
		boolean[] delete = new boolean[n];
		String[][] displayedInfo = new String[2][n];
		for (int i = 0; i < n; i++)
		{
			displayedInfo[0][i] = students[i].getStudentName();
			displayedInfo[1][i] = students[i].getStudentID();
		} // end for
		
		userInterface.setEnabled(false);
		studentDeleter = new DeleteStudentInterface
			(delete, displayedInfo, this);
	} // end of beginDeleteStudent
	
	/**
	 * Concludes the process of deleting a list of students.  It receives an
	 * array of boolean where each 'true' value corresponds to a record that
	 * has been selected for deletion, deletes each corresponding Students
	 * object, and lowers remaining Students objects to fill any resulting
	 * holes in the array.
	 */
	private void finishDeleteStudents()
	{
		boolean[] delete = studentDeleter.getDeleteOrders();
		boolean anyDeletions = false;
		for (int i = 0; i < delete.length; i++)
			if (delete[i])
				anyDeletions = true;
		
		if (anyDeletions)
		{
			int confirm = JOptionPane.showConfirmDialog(null,
					"Are you sure you would like to delete these students?",
					"Confirmation Needed", JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION)
			{
				int n = delete.length;
				Students tempStudent;
				boolean tempBool;
				
				for (int i = 0; i < n; i++)
				{
					if (delete[i])
					{
						students[i] = new Students();
						delete[i] = false;
						for (int j = i; j < students.length - 1; j++)
						{
							tempStudent = students[j];
							students[j] = students[j+1];
							students[j+1] = tempStudent;
							if (j+1 < n)
							{
								tempBool = delete[j];
								delete[j] = delete[j+1];
								delete[j+1] = tempBool;
							} // end if
						} // end for
						i--;
					} // end if
				} // end for
				
				studentDeleter.dispose();
				updateGUI();
			} // end if
		} // end if
	} // end of finishDeleteStudent
	
	/**
	 * Computes data about the students and sends it to the GUI.
	 */
	private void computeStats()
	{
		userInterface.displayStats(
			getNumberOfStudents(),
			getMeanAmountDue(),
			getMedianAmountDue(),
			getStandardDeviation() );
                        userInterface.changeComputeStats();
	} // end of computeStats
	
	/**
	 * Uses a heap sort to sort the students by total amount due.  
	 */
	private void sortAmount()
	{
		int i;
		Students temp;
		int n = getNumberOfStudents();

		if (!amountIsSorted())
		{
			for (i = (n / 2) - 1; i >= 0; i--)
				siftDown(i, n);
			
			for (i = n - 1; i >= 1; i--)
			{
				temp = students[0];
				students[0] = students[i];
				students[i] = temp;
				siftDown(0, i-1);
			} // end for
			updateGUI();
                        userInterface.changeSortAmount();
		} // end if
	} // end of sortStudents
	
	/**
	 * Uses a heap sort to sort the students by name.  
	 */
	private void sortName()
	{	 
		int i;
		int j;
		Students temp;
		int n = getNumberOfStudents();

	for(j = 0; j < n; j++)
	{
		for (i = (n / 2) - 1; i >= 0; i--)
			siftNameDown(i, n);
		for (i = n - 1; i >= 1; i--)
		{
			temp = students[0];
			students[0] = students[i];
			students[i] = temp;
			siftNameDown(0, i-1);
		} // end for
			updateGUI();
         userInterface.changeSortName();
        }// end if

	} 
	
	/**
	 * Uses a heap sort to sort the students by student ID.  
	 */
	private void sortID()
	{
		int i;
		int j;
		Students temp;
		int n = getNumberOfStudents();

	for(j = 0; j < n; j++)
	{
		for (i = (n / 2) - 1; i >= 0; i--)
			siftIDDown(i, n);
		for (i = n - 1; i >= 1; i--)
		{
			temp = students[0];
			students[0] = students[i];
			students[i] = temp;
			siftIDDown(0, i-1);
		} // end for
    		updateGUI();
 			userInterface.changeSortID();
	}
}	
	/**
	 * Support method for sortStudents.  Swaps objects as needed within the
	 * heap.
	 * @param root The index of the parent object being handled.
	 * @param bottom The index of the lowest child object in the heap.
	 */
	private void siftDown(int root, int bottom)
	{
		boolean done = false;
		int maxChild;
		Students temp;
while (root * 2 <= bottom && !done)
	{	
			
			boolean leftChildIsBottom = (root * 2 == bottom);
			boolean leftChildIsLarger = ( getTotalFee(root * 2) >
												getTotalFee((root * 2) + 1) );
			if (leftChildIsBottom || leftChildIsLarger)
				maxChild = root * 2;
			else
				maxChild = (root * 2) + 1;
			
			if (getTotalFee(root) < getTotalFee(maxChild))
			{
				temp = students[root];
				students[root] = students[maxChild];
				students[maxChild] = temp;
				root = maxChild;
			} else {
				done = true;
			} // end if-else
		} // end while
	} // end of siftDown

private void siftIDDown(int root, int bottom) {

      boolean done = false;
		int maxChild;
		Students temp;
		
		while (root * 2 <= bottom && !done) {
			boolean leftChildIsBottom = (root * 2 == bottom);
			boolean leftChildIsLarger = ( getStudentID(root * 2) >
												getStudentID((root * 2) + 1) );
			if (leftChildIsBottom || leftChildIsLarger)
				maxChild = root * 2;
			else
				maxChild = (root * 2) + 1;
			
			if (getStudentID(root) < getStudentID(maxChild))
			{
				temp = students[root];
				students[root] = students[maxChild];
				students[maxChild] = temp;
				root = maxChild;
			} else {
				done = true;
				} // end if-else
			} // end while
	} // end of siftDown
	
		/**
	 * Support method for sortStudents.  Swaps objects as needed within the
	 * heap.
	 * @param root The index of the parent object being handled.
	 * @param bottom The index of the lowest child object in the heap.
	 */
	private void siftNameDown(int root, int bottom)
	{
		boolean done = false;
		int maxChild;
		Students temp;
		
		for( temp = students[ root ]; (2 * root + 1) < bottom; root = maxChild ) 
		{ 
			maxChild = 2 * root + 1; 
			if( maxChild != bottom - 1 && students[ maxChild ].compareTo( students[ maxChild + 1 ] ) < 0 ) 
				maxChild++; 
			if( temp.compareTo( students[ maxChild ] ) < 0 ) 
				students[ root ] = students[maxChild ]; 
			else 
				break; 
		} 
			students[ root ] = temp; 
 	}  

	
	/**
	 * Main method.
	 * @param args
	 */
	public static void main(String[] args)
	{
		RegistrationSystem system = new RegistrationSystem();
	} // end of main
	
	// EVENT LISTENER METHODS---------------------------------------------------
	public void actionPerformed(ActionEvent e)
	{
		String action = e.getActionCommand();
		if (action.equals(GUI.NEW))
			newFile();
		else if (action.equals(GUI.OPEN))
			openFile();
		else if (action.equals(GUI.SAVE))
			saveFile();
		else if (action.equals(GUI.SAVE_AS))
			saveAs();
		else if (action.equals(GUI.QUIT))
			quit();
		else if (action.equals(GUI.ADD_STUDENT))
			beginAddStudent();
		else if (action.equals(GUI.DELETE_STUDENT))
			beginDeleteStudents();
		else if (action.equals(GUI.COMPUTE_STATS))
			computeStats();
		else if (action.equals(GUI.SORT_NAME))
			sortName();
		else if (action.equals(GUI.SORT_ID))
			sortID();
		else if (action.equals(GUI.SORT_AMOUNT))
			sortAmount();
		else if (action.equals(AddStudentInterface.RETURN_DATA))
			finishAddStudent();
		else if (action.equals(AddStudentInterface.FIND_COURSE))
			addCourseToStudent();
		else if (action.equals(AddStudentInterface.CLEAR_DATA))
			studentAdder.clear();
		else if (action.equals(DeleteStudentInterface.CONFIRM_DELETION))
			finishDeleteStudents();
	} // end of actionPerformed
	
	public void windowClosing(WindowEvent e)
	{
		Object source = e.getSource();
		if (source.equals(userInterface))
			quit();
		else if (source.equals(studentAdder) || source.equals(studentDeleter))
			userInterface.setFocusHere();
	} // end of windowClosing

	public void windowOpened(WindowEvent e) {
	}

	public void windowClosed(WindowEvent e) {
	}

	public void windowIconified(WindowEvent e) {
	}

	public void windowDeiconified(WindowEvent e) {
	}

	public void windowActivated(WindowEvent e) {
	}

	public void windowDeactivated(WindowEvent e) {
	}
	

} // end RegistrationSystem