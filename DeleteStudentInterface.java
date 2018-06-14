import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * RegistrationSystem class instantiates and manages a set of graphical user
 * interfaces to handle display and maintenance of student information.
 *
 * CSCE 155 Spring 2008
 * Assignment 6
 * @author Rodrigo Fuzon
 */

public class DeleteStudentInterface extends JFrame
{
	/**
	 * The default size of the main frame.
	 */
	public static final Dimension FRAME_SIZE = new Dimension(350,325);
	
	/**
	 * The minimum size of the main frame.
	 */
	public static final Dimension MIN_FRAME_SIZE = new Dimension(350,200);
	
	/**
	 * The default distance of the main frame from the X origin.
	 */
	public static final Point FRAME_ORIGIN = new Point(450,250);
	
	/**
	 * The action command associated with a "Confirm Deletion" command.
	 */
	public static final String CONFIRM_DELETION = "Confirm Deletion";
	
	/**
	 * The array of check boxes associated with the students in this file.
	 * A check indicates that the associated student is to be deleted.
	 */
	private JCheckBox[] checkBoxes;
	
	/**
	 * Default constructor for this class.
	 * @param delete An array of booleans used to store the user's deletion
	 * choices.
	 * @param displayInfo The student information to be listed.
	 * @param listener An object that implements ActionListener and
	 * WindowListener, usually the object which instantiated this.
	 */
	public DeleteStudentInterface(boolean[] delete, String[][] displayInfo,
			EventListener listener)
	{
		int n = delete.length;
		assert n == displayInfo[0].length && n == displayInfo[1].length :
			new Exception("The lengths of the boolean array delete and " +
					"the String arrays displayInfo[0] and displayInfo[1] " +
					"do not match.");
		
		Container contentPane;
		SpringLayout contentLayout;
		JButton confirmBtn;
		JScrollPane scrollPane;
		JPanel confirmPanel, deletePanel;
		
		checkBoxes = new JCheckBox[n];
		JLabel[][] studentInfo = new JLabel[2][n];
		setResizable(false);
		setSize(FRAME_SIZE);
		setMinimumSize(MIN_FRAME_SIZE);
		setTitle("Delete Students");
		setLocation(FRAME_ORIGIN);
		addWindowListener((WindowListener) listener);
		
		contentPane = getContentPane();
		contentPane.setLayout(contentLayout = new SpringLayout());
		
		confirmPanel = new JPanel();
		confirmBtn = new JButton("Confirm Deletion");
		confirmBtn.addActionListener((ActionListener) listener);
		confirmBtn.setActionCommand(CONFIRM_DELETION);
		confirmPanel.add(confirmBtn);
		
		deletePanel = new JPanel(new GridLayout(n, 3));
		for(int i = 0; i < n; i++)
		{
			for(int j = 0; j < 2; j++)
			{
				studentInfo[j][i] = new JLabel(displayInfo[j][i]);
				deletePanel.add(studentInfo[j][i]);
			} // end for
			checkBoxes[i] = new JCheckBox();
			deletePanel.add(checkBoxes[i]);
		} // end for
		scrollPane = new JScrollPane(deletePanel,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		contentPane.add(confirmPanel);
		contentPane.add(scrollPane);
		contentLayout.putConstraint(SpringLayout.NORTH, confirmPanel, 0,
				SpringLayout.NORTH, contentPane);
		sideConstraint(confirmPanel, contentPane, contentLayout);
		contentLayout.putConstraint(SpringLayout.NORTH, scrollPane, 0,
				SpringLayout.SOUTH, confirmPanel);
		contentLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 0,
				SpringLayout.SOUTH, contentPane);
		sideConstraint(scrollPane, contentPane, contentLayout);
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
	 * Makes a boolean array out of the check boxes and returns it.
	 * @return A boolean array set to match the status of the check boxes.
	 */
	public boolean[] getDeleteOrders()
	{
		boolean[] delete = new boolean[checkBoxes.length];
		for(int i = 0; i < delete.length; i++)
			delete[i] = checkBoxes[i].isSelected();
		return delete;
	} // end of getDeleteOrders
} // end of DeleteStudentInterface