import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Class SearchCatalogue is a functionality of the GUI
 * which searches the course catalogue for the client.
 * 
 * @author A. Naheen
 * @version 16.0.1
 * @since August 08, 2021
*/
public class SearchCatalogue extends JFrame
{
	/** Header Label at the top of the window.*/
	private JLabel header;
	
	/** Name of the course.*/
	private JTextField name;
	
	/** ID number of the course.*/
	private JTextField ID;
	
	/** Search button in GUI.*/
	private JButton search;
	
	/** Button in GUI which returns to the main window.*/
	private JButton returnToMain;

	/** North panel of GUI.*/
	private JPanel northPanel;

	/** Centre panel of GUI.*/
	private JPanel centrePanel;

	/** South panel of GUI.*/
	private JPanel southPanel;

	/**
     * Constructor for SearchCatalogue object. 
     * It sets the layout for the search catalogue feature.
    */
	public SearchCatalogue()
	{
		setTitle("Search Course Catalogue");
		setSize(300, 150);

		header = new JLabel("Enter the Course Below");
		name = new JTextField(4);
		ID = new JTextField(3);
		search = new JButton("Search");
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

		southPanel.add(search);
		southPanel.add(returnToMain);
	}
	
	/**
     * Action Listener for the Search button.
     * @param s the Action Listener passed in
    */
	public void pressSearch(ActionListener s) 
	{
		search.addActionListener(s);
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
}