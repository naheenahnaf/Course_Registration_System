import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/** 
 * Class ClientController establishes the client, runs the client, 
 * and connects to the server.
 * 
 * @author A. Naheen
 * @version 16.0.1
 * @since August 08, 2021 
*/
public class ClientController 
{
	/** GUI which the client will interact with.*/
	private GUI gui;
	
	/** Log in window in order to get into the GUI.*/
	private UserLogin login;
	
	/** The client socket which connects to the server.*/
	private Socket clientSocket;
	
	/** Writes information from the client to the server.*/
	private PrintWriter socketOutput;
	
	/** Reads information from the server to the client.*/
	private BufferedReader socketInput;
	
	/**
	 * Constructor for the ClientController object.
	 * @param serverName name of the MySQL localhost server
	 * @param portNumber port number for the socket
	 * @param gui means for the client to interact with the server
	*/
	public ClientController(String serverName, int portNumber, GUI gui) throws IOException 
	{
		this.gui = gui;
		clientSocket = new Socket(serverName, portNumber);
		socketInput = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		socketOutput = new PrintWriter((clientSocket.getOutputStream()), true);
	}
	
	/** 
	 * Recieves the add course parameters in the GUI; 
	 * obtaining it from the server.
	*/
	public void addCourse() 
	{
		AddCourse ac = new AddCourse();
		ac.setVisible(true);
		
		ac.pressAdd((ActionEvent e) ->
		{
			String courseID = ac.getCourseID();
			String courseName = ac.getCourseName();
			String courseSection = ac.getCourseSection();

			socketOutput.println("2," + courseName + "," + courseID + "," + courseSection);
			gui.displayMessage(tunnel());
			ac.dispose();
		}
		);
		ac.pressReturnToMain((ActionEvent e) ->
		{
			ac.dispose();
		}
		);
	}

	/** 
	 * Recieves the remove course parameters in the GUI; 
	 * obtaining it from the server.
	*/
	public void removeCourse() 
	{
		RemoveCourse rc = new RemoveCourse();
		rc.setVisible(true);
		
		rc.pressRemove((ActionEvent e) ->
		{
			String courseID = rc.getCourseID();
			String courseName = rc.getCourseName();

			socketOutput.println("3," + courseName + "," + courseID);
			gui.displayMessage(tunnel());
			rc.dispose();
		}
		);	
		rc.pressReturnToMain((ActionEvent e) ->
		{
			rc.dispose();
		}
		);
	}
	
	/** 
	 * Recieves the search course catalogue parameters 
	 * in the GUI; obtaining it from the server.
	*/
	public void searchCatalogue() 
	{
		SearchCatalogue sc = new SearchCatalogue();
		sc.setVisible(true);
		
		sc.pressSearch((ActionEvent e) ->
		{
			String courseId = sc.getCourseID();
			String courseName = sc.getCourseName();

			socketOutput.println("1," + courseName + "," + courseId);
			gui.displayMessage(tunnel());
			sc.dispose();
		}
		);	
		sc.pressReturnToMain((ActionEvent e) ->
		{
			sc.dispose();
		}
		);
	}
	
	/** Recieves the display course catalogue parameters in the GUI. */
	public void displayCatalogue() 
	{
		socketOutput.println("4");
		String s = tunnel();
		gui.setTextArea(s);
	}
	
	/** Recieves the check course load parameters in the GUI.*/
	public void checkCourseLoad() 
	{
		socketOutput.println("5");
		String s = tunnel();
		gui.setTextArea(s);
	}

	/** 
	 * Recieves the login parameters; 
	 * obtaining it from the server.
	*/
	private void login() 
	{
		String ID = login.getId();
		if(ID.isEmpty()) 
		{
			gui.displayErrorMessage("Please Enter a 4-Digit ID Number");
		} 
		else 
		{
			socketOutput.println(ID);
			String reader = tunnel();
			gui.displayMessage(reader);

			if(!reader.contains("Student")) 
				gui.setVisible(true);
			else 
				System.exit(1);
			
			login.dispose();
		}
	}
	
	/** Tunnel for the socket connection.*/
	private String tunnel() 
	{
		String s = "";
		
		while(true) 
		{
			try 
			{
				s += socketInput.readLine() + "\n";
				
				if(s.contains("\0")) 
				{
					s = s.replace("\0", "\n");
					return s;
				}
			} 
			catch (IOException io) 
			{
				System.err.println(io.getMessage());
			}
			System.out.println(s);
		}
	}
	
	/** The action events for each GUI feature.*/
	public void events() 
	{
		login = new UserLogin();
		login.setVisible(true);
		
		login.pressEnter((ActionEvent e) ->
		{
			login();
		}
		);
		gui.pressAddCourse((ActionEvent e) ->
		{
			addCourse();
		}
		);
		gui.pressSearchCatalogue((ActionEvent e) ->
		{
			searchCatalogue();
		}
		);
		gui.pressRemoveCourse((ActionEvent e) ->
		{
			removeCourse();
		}
		);
		gui.pressDisplayCatalogue((ActionEvent e) ->
		{
			displayCatalogue();
		}
		);
		gui.pressCheckCourseLoad((ActionEvent e) ->
		{
			checkCourseLoad();
		}
		);
	}
	
	/**
	 * Main method to establish and run the client.
	 * @param args Command Line Arguments.
	*/
	public static void main(String[] args) 
	{
		GUI gui = new GUI();
		try 
		{
			ClientController control = new ClientController("localhost", 9090, gui);
			control.events();
			
		} 
		catch (IOException io) 
		{
			System.out.println(io.getMessage());
		}
	}
}