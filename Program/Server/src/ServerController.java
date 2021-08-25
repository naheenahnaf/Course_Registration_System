import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** 
 * Class ServerController establishes the server, runs the server, 
 * and connects to the client.
 * 
 * @author A. Naheen
 * @version 16.0.1
 * @since August 08, 2021 
*/
public class ServerController 
{
	/** List of students in the server.*/
	private ArrayList<Student> studentList;
	
	/** Course catalogue stored in the server.*/
	private CourseCatalogue cat;
	
	/** Database for the server.*/
	private CreateDatabase database;
	
	/** The server socket which connects to the client.*/
	private ServerSocket serverSocket;
	
	/** 
	 * A thread pool allowing the Student Registration System
	 * to be concurrent with multiple users, without creating
	 * unnecessary threads for every client request.
	*/
	private ExecutorService threadPool;

	/**
	 * Constructor for the ServerController object.
	 * @param portNumber port number for the socket
	 * @param studentList list of students in the server
	 * @param cat course catalogue in the server
	 * @param database database of the server
	*/
	public ServerController(int portNumber, ArrayList<Student> studentList, CourseCatalogue cat, CreateDatabase database) 
	{
		try 
		{
			serverSocket = new ServerSocket(portNumber);
			threadPool = Executors.newCachedThreadPool();
			this.studentList = studentList;
			this.cat = cat;
			this.database = database;
			System.out.println("\nThe Server is Running.\n");
		} 
		catch (IOException io) 
		{
			System.out.println(io.getMessage());
		}
	}
	
	/** Tunnel for the socket connection.*/
	public void tunnel() 
	{
		while(true) 
		{
			try 
			{
				Socket socket = serverSocket.accept();
				BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);
				
				String reader = socketIn.readLine();
				String[] args = reader.split(",");
				int ID = Integer.parseInt(args[0]);
				Student theStudent = searchStudentList(ID);
				User user = null;

				if(database.verifyLogin(ID) && theStudent != null)
				{
					socketOut.println("Welcome " + theStudent.getStudentName() + "\0");
					user = new User(cat, theStudent, database, socketOut, socketIn);
					threadPool.execute(user);
				}
				else 
					socketOut.println("Student ID not Found.\0");
			} 
			catch (NullPointerException npe) 
			{
				System.out.println(npe.getMessage());
			}
			catch (NumberFormatException num) 
			{
				System.out.println(num.getMessage());
			}
			catch (IOException io) 
			{
				System.out.println(io.getMessage());
				threadPool.shutdown();
			} 
			catch (SQLException sql) 
			{
				System.out.println(sql.getMessage());
			} 
		}
	}
	
	/**
	 * Searches for a student by their ID number in the student list.
	 * @param id ID number of the student
	 * @return the student if found, otherwise null
	*/
	private Student searchStudentList(int id) 
	{
		for(Student s : studentList) 
		{
			if(s.getStudentID() == id) 
				return s;
		}

		return null;
	}
	
	/**
	 * Main method to establish and run the server.
	 * @param args Command Line Arguments.
	*/
	public static void main(String[] args) 
	{
		CourseCatalogue cat;
		ArrayList<Student> studentList;
		try 
		{
			CreateDatabase database = new CreateDatabase();
			cat = new CourseCatalogue(database.courseCatalogue());
			studentList = database.studentList();
			database.studentRegistration();
			ServerController server = new ServerController(9090, studentList, cat, database);
			server.tunnel();
		} 
		catch (SQLException sql) 
		{
			System.out.println(sql.getMessage());
		}	
	}
}