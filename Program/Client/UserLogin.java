import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Class UserLogin allows the client to log into the server
 * 
 * @author A. Naheen
 * @version 16.0.1
 * @since August 08, 2021
*/
public class UserLogin extends JFrame
{	
	/** Header Label at the top of the window.*/
	private JLabel header;
	
	/** ID number of the student (user).*/
	private JTextField ID;
	
	/** Login button to check credentials.*/
	private JButton login;

	/** North panel of login interface.*/
	private JPanel northPanel;

	/** Centre panel of login interface.*/
	private JPanel centrePanel;

	/** South panel of login interface.*/
	private JPanel southPanel;
	
	/**
     * Constructor for UserLogin object. 
     * It sets the layout for the login feature.
    */
	public UserLogin()
	{
		setTitle("Login");	
		setSize(200, 150);
		setDefaultCloseOperation(3);

		header = new JLabel("Student Login");
		ID = new JTextField(4);
		login = new JButton("Enter");	
		northPanel = new JPanel();
		centrePanel = new JPanel();
		southPanel = new JPanel();

		add("North", northPanel);
		add("Center", centrePanel);
		add("South", southPanel);
		
		header.setFont(new Font("", 1, 14));
		northPanel.add(header);
		
		centrePanel.add(new JLabel("User ID"));
		centrePanel.add(ID);
		
		southPanel.add(login);
	}
	
	/**
     * Action Listener for the Enter button.
     * @param e the Action Listener passed in
    */
	public void pressEnter(ActionListener e) 
	{
		login.addActionListener(e);
	}
	
	/**
     * Getter method which gets the ID number of the student.
     * @return student's (user) ID number
    */
	public String getId() 
	{
		return ID.getText();
	}
}