import java.io.*;
import java.sql.*;

/**
 * Class User indicates what the user of the server (client)
 * will interact with in the Student Registration System.
 * 
 * @author A. Naheen
 * @version 16.0.1
 * @since August 08, 2021
*/
public class User implements Runnable
{
	/** Course catalogue feature presented to the user.*/
	private CourseCatalogue cat;
	
	/** the student which the user will login as.*/
	private Student student;
	
	/** The database.*/
	private CreateDatabase database;
	
	/** Writes information from the server to the client.*/
	private PrintWriter socketOutput;
	
	/** Reads information from the client to the server.*/
	private BufferedReader socketInput;
	
	/**
	 * Constructor for the User object.
	 * @param student the client logs in as a student
	 * @param cat the course catalogue presented
	 * @param socketOutput output information through the socket
	 * @param socketInput input information through the socket
	 * @param database the database
	*/
	public User(CourseCatalogue cat, Student student, CreateDatabase database, PrintWriter socketOutput, BufferedReader socketInput)
	{
		this.cat = cat;
		this.student = student;
		this.database = database;
		this.socketInput = socketInput;
		this.socketOutput = socketOutput;
	}

	/**
	 * The frontend of the program GUI (found in client package)
	 * @throws IOException
	 * @throws NullPointerException
	*/
	public void menu() throws IOException, NullPointerException 
	{
		int scan;
		String courseName;
		int courseID;
		int courseSection;
		
		while(true) 
		{
			String reader = socketInput.readLine();
			String[] args = reader.split(",");
			scan = Integer.parseInt(args[0]);
			
			switch(scan)
			{
				case 1:
				{
					courseName = args[1].toUpperCase();
					courseID = Integer.parseInt(args[2]);
					searchCatalogue(courseName, courseID);
					break;
				}

				case 2:
				{
					courseName = args[1].toUpperCase();
					courseID = Integer.parseInt(args[2]);
					courseSection = Integer.parseInt(args[3]);
					registerStudent(courseName, courseID, courseSection);
					break;
				}

				case 3:
				{
					courseName = args[1].toUpperCase();
					courseID = Integer.parseInt(args[2]);
					removeCourse(courseName, courseID);
					break;
				}

				case 4:
				{
					socketOutput.println(cat + "\0");
					break;
				}

				case 5:
				{
					displayStudentCourses();
					break;
				}

				default:
				{
					return;
				}
			}
		}
	}

	/** Display student's course load.*/
	public void displayStudentCourses() 
	{
		String s = student.getStudentName() + " Current Course Load: \n";

		for(Registration reg : student.getStudentRegList()) 
		{
			s += reg.getTheOffering();
			s += "\n";
		}
		s += "\0";
		socketOutput.println(s);
	}
	
	/**
	 * Removes a course.
	 * @param courseName name of the course
	 * @param courseID ID number of the course
	*/
	public void removeCourse(String courseName, int courseID) 
	{
		Course theCourse = cat.searchCat(courseName, courseID);
		
		if(theCourse == null)
			socketOutput.println("Course Not Found.\0");
		else if(student.removeRegistration(theCourse, database) == true) 
			socketOutput.println("Course Successfully Dropped.\0");
	}
	
	/**
	 * Searches course catalogue for a course.
	 * @param name name of the course
	 * @param ID ID number of the course
	*/
	public void searchCatalogue(String name, int ID) 
	{
		Course theCourse = cat.searchCat(name, ID);

		if(theCourse != null)
			socketOutput.println(theCourse + "\0");
		else 
			socketOutput.println("Course Not Found.\0");
	}

	/**
	 * Registers the student into a course.
	 * @param courseName name of the course
	 * @param courseID ID number of the course
	 * @param courseSection lecture section of the course
	*/
	private void registerStudent(String courseName, int courseID, int courseSection) 
	{
		Course theCourse = cat.searchCat(courseName, courseID);
		
		if(theCourse == null) 
			socketOutput.println("Course Not Found.\0");
		else 
		{
			Registration reg = new Registration();
			CourseOffering offering = theCourse.getCourseOfferingAt(courseSection - 1);
			
			if(offering == null) 
				socketOutput.println("Course Offering Not Found.\0");
			else 
			{
				String result = reg.completeRegistration(student, offering);
				try 
				{
					database.addRegistration(reg);
				} 
				catch (SQLException sql) 
				{
					System.out.println(sql.getMessage());
				}
				socketOutput.println(result + "\0");
			}
		}
	}
	
	/** Creation of a thread.*/
	@Override
	public void run() 
	{
		try 
		{
			menu();
		} 
		catch (NullPointerException npe) 
		{
			System.out.println(npe.getMessage());
		} 
		catch (IOException io) 
		{
			System.out.println(io.getMessage());
		} 
	}
}