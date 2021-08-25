import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Class AddCourse is a functionality of the GUI
 * which adds a course for the client.
 * 
 * @author A. Naheen
 * @version 16.0.1
 * @since August 08, 2021
*/
public class AddCourse extends JFrame
{
	/** Header Label at the top of the window.*/
	private JLabel header;
	
	/** Name of the course.*/
	private JTextField name;

	/** ID number of the course.*/
	private JTextField ID;
	
	/** Lecture section of the course.*/
	private JTextField section;

	/** Add button in GUI.*/
	private JButton add;
	
	/** Button in GUI which returns to the main window.*/
	private JButton returnToMain;

	/** North panel of GUI.*/
	private JPanel northPanel;

	/** Centre panel of GUI.*/
	private JPanel centrePanel;

	/** South panel of GUI.*/
	private JPanel southPanel;
	
	/**
     * Constructor for AddCourse object. 
     * It sets the layout for the add course feature.
    */
	public AddCourse()
	{
		setTitle("Add Course");
		setSize(400, 150);

		header = new JLabel("Enter the Course Below");
		name = new JTextField(4);
		ID = new JTextField(3);
		section = new JTextField(1);
		add = new JButton("Add");
		returnToMain = new JButton("Return to Main Menu");
		northPanel = new JPanel();
		centrePanel = new JPanel();
		southPanel = new JPanel();

		add("North", northPanel);
		add("Center", centrePanel);
		add("South", southPanel);
		
		header.setFont(new Font("", 1, 14));
		northPanel.add(header);
		
		centrePanel.add(new JLabel("Course Name"));
		centrePanel.add(name);
		centrePanel.add(new JLabel("Course ID"));
		centrePanel.add(ID);
		centrePanel.add(new JLabel("Course Section"));
		centrePanel.add(section);
		
		southPanel.add(add);
		southPanel.add(returnToMain);
	}

	/**
     * Action Listener for the Add button.
     * @param a the Action Listener passed in
    */
	public void pressAdd(ActionListener a) 
	{
		add.addActionListener(a);
	}
	
	/**
     * Action Listener for the return to main window button.
     * @param rtm the Action Listener passed in
    */
	public void pressReturnToMain(ActionListener rtm) 
	{
		returnToMain.addActionListener(rtm);
	}

	/**
     * Getter method which gets the course name.
     * @return name of the course
    */
	public String getCourseName() 
	{
		return name.getText();
	}
	
	/**
     * Getter method which gets the ID number of the course.
     * @return ID number of the course
    */
	public String getCourseID() 
	{
		return ID.getText();
	}

	/**
     * Getter method which gets the course lecture section.
     * @return lecture section of the course
    */
	public String getCourseSection() 
	{
		return section.getText();
	}
}