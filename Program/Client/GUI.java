import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Class GUI is what the user (client) interacts with.
 * 
 * @author A. Naheen
 * @version 16.0.1
 * @since August 06, 2021
*/
public class GUI extends JFrame
{	
	/** Header Label at the top of the window.*/
	private JLabel header;

	/** TextArea of the GUI.*/
	private JTextArea textArea;

	/** Add Course button in GUI.*/
	private JButton addCourse;

	/** Remove Course button in GUI.*/
	private JButton removeCourse;

	/** Search Catalogue button in GUI.*/
	private JButton searchCatalogue;
	
	/** Display Catalogue button in GUI.*/
	private JButton displayCatalogue;
	
	/** Check Course Load button in GUI.*/
	private JButton checkCourseLoad;

	/** North panel of GUI.*/
	private JPanel northPanel;
	
	/** Centre panel of GUI.*/
	private JPanel centrePanel;
	
	/** South panel of GUI.*/
	private JPanel southPanel;
	
	/**
     * Constructor for GUI object. 
     * It sets the layout of the entire GUI.
    */
	public GUI() 
	{
		setTitle("Main Window");
		setSize(800, 500);
		setDefaultCloseOperation(3);

		header = new JLabel("Student Registration System");
		textArea = new JTextArea();
		addCourse = new JButton("Add a Course");
		removeCourse = new JButton("Remove a Course");
		searchCatalogue = new JButton("Search Course Catalogue");
		displayCatalogue = new JButton("Display Course Catalougue");
		checkCourseLoad = new JButton("Check Course Load");
		northPanel = new JPanel();
		centrePanel = new JPanel();
		southPanel = new JPanel();

		add("North", northPanel);
		add("Center", centrePanel);
		add("South", southPanel);
		
		header.setFont(new Font("", 1, 18));
		northPanel.add(header);

		centrePanel.setLayout(new BorderLayout());
        centrePanel.add(new JScrollPane(textArea, 20, 31), "Center");
		
		southPanel.add(addCourse);
		southPanel.add(removeCourse);
		southPanel.add(searchCatalogue);
		southPanel.add(displayCatalogue);
		southPanel.add(checkCourseLoad);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); 
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
	
	/**
     * Action Listener for the Add Course button.
     * @param ac the Action Listener passed in.
    */
	public void pressAddCourse(ActionListener ac) 
	{
		addCourse.addActionListener(ac);
	}
	
	/**
     * Action Listener for the Remove Course button.
     * @param rc the Action Listener passed in.
    */
	public void pressRemoveCourse(ActionListener rc) 
	{
		removeCourse.addActionListener(rc);
	}

	/**
     * Action Listener for the Search Catalogue button.
     * @param scat the Action Listener passed in.
    */
	public void pressSearchCatalogue(ActionListener scat) 
	{
		searchCatalogue.addActionListener(scat);
	}
	
	/**
     * Action Listener for the Display Catalogue button.
     * @param dcat the Action Listener passed in.
    */
	public void pressDisplayCatalogue(ActionListener dcat) 
	{
		displayCatalogue.addActionListener(dcat);
	}
	
	/**
     * Action Listener for the Check Course Load button.
     * @param ccl the Action Listener passed in.
    */
	public void pressCheckCourseLoad(ActionListener ccl) 
	{
		checkCourseLoad.addActionListener(ccl);
	}

	/**
     * Displays text in the TextArea 
     * @param text Displayed text
    */
	public void setTextArea(String text) 
	{
		textArea.setText(text);
	}
	
	/**
     * Displays a message.
     * @param message
    */
	public void displayMessage(String message) 
	{
		JOptionPane.showMessageDialog(this, message);
	}
	
	/**
     * Displays an error message.
     * @param error Error message
    */
	public void displayErrorMessage(String error) 
	{
		JOptionPane.showMessageDialog(this, error);
	}
}