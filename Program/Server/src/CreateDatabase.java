import java.sql.*;
import java.util.*;

/** 
 * Class CreateDatabase creates a MySQL data base.
 * 
 * @author A. Naheen
 * @version 16.0.1
 * @since August 08, 2021 
*/
public class CreateDatabase implements MySQLCredentials 
{
	/** List of all courses.*/
	private ArrayList<Course> courseList;
	
	/** List of course offerings.*/
	private ArrayList<CourseOffering> offeringList;
	
	/** List of students.*/
	private ArrayList<Student> studentList;
	
	/** Connection to the JDBC driver.*/
	private Connection connect;
	
	/** Searching through the table in the data base.*/
	private ResultSet tableSearch;
	
	/**
	 * Constructor for a CreateDatabase object.
	 * @throws SQLException
	*/
	public CreateDatabase() throws SQLException
	{
		courseList = new ArrayList<Course>();
		offeringList = new ArrayList<CourseOffering>();
		studentList = new ArrayList<Student>();
		connect = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
	}

	/** Sets up the connection to a JDBC driver.*/
	public void initializeConnection() 
	{
		try 
		{
			// Register JDBC driver
			Driver driver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
		} 
		catch (SQLException connection) 
		{
			System.err.println("Cannot connect to MySQL server.");
		}
	}
	
	/** Creates a table for Course in the database.*/
	public void courseTable() 
	{
		String sql = "CREATE TABLE courses " + "(CourseKey INTEGER not NULL, " + " CourseName VARCHAR(255), " + " CourseId INTEGER not NULL, "
				+ " Section INTEGER not NULL, "  + " Capacity INTEGER not NULL, "  + " PRIMARY KEY ( CourseKey ))";

		try 
		{
			Statement s = connect.createStatement();
			s.executeUpdate(sql);
			s.close();
		} 
		catch (SQLException course) 
		{
			System.err.println("Cannot create table for Course.");
		}
	}
	
	/** Creates a table for Registration in the database.*/
	public void RegistrationTable() 
	{
		String sql = "CREATE TABLE registrations " + "(StudentId INTEGER not NULL, " + " CourseKey INTEGER not NULL, "
				+ " Grade VARCHAR(255)) ";
		try 
		{
			Statement s = connect.createStatement();
			s.executeUpdate(sql);
			s.close();
		} 
		catch (SQLException registration) 
		{
			System.err.println("Cannot create table for Registration.");
		}
	}
	
	/** Creates a table for Student in the database.*/
	public void studentTable() 
	{
		String sql = "CREATE TABLE students " + "(Id INTEGER not NULL, " + " Name VARCHAR(255), " + " PRIMARY KEY ( Id ))";

		try 
		{
			Statement s = connect.createStatement();
			s.executeUpdate(sql);
			s.close();
		} 
		catch (SQLException student) 
		{
			System.err.println("Cannot create table for Student.");
		}
	}
	
	/**
	 * Fills the Course table from the course list.
	 * @return list of all courses from the table
	 * @throws SQLException
	*/
	public ArrayList<Course> courseCatalogue() throws SQLException
	{
		courseList = new ArrayList<Course>();
		Statement s =  connect.createStatement();
		ResultSet fillTable = s.executeQuery("SELECT * FROM courses;");
		Course course = null;
		
		while(fillTable.next()) 
		{
			CourseOffering offering = new CourseOffering(fillTable.getInt("Section"), fillTable.getInt("Capacity"), fillTable.getInt("CourseKey"));
			if(course == null ||
			!(course.getCourseName().equals(fillTable.getString("CourseName")) && course.getCourseNum() == fillTable.getInt("CourseId"))) 
			{
				course = new Course(fillTable.getString("CourseName"), fillTable.getInt("CourseId"));
				courseList.add(course);
			}
			course.addOffering(offering);
			offeringList.add(offering);
		}
		
		return courseList;
	}
	
	/**
	 * Fills the Student table from the student list. 
	 * @return list of all students from the table
	 * @throws SQLException
	*/
	public ArrayList<Student> studentList() throws SQLException
	{
		studentList = new ArrayList<Student>();
		Statement s =  connect.createStatement();
		ResultSet fillTable = s.executeQuery("SELECT * FROM students;");

		while(fillTable.next()) 
		{
			studentList.add(new Student(fillTable.getString("Name"), fillTable.getInt("Id")));
		}
		
		return studentList;
	}
	
	/**
	 * Fills the Registration table.
	 * @throws SQLException
	*/
	public void studentRegistration() throws SQLException 
	{
		Statement s =  connect.createStatement();
		tableSearch = s.executeQuery("SELECT * FROM registrations;");

		while(tableSearch.next()) 
		{
			Student student = null;
			for(Student st: studentList) 
			{
				if(st.getStudentID() == tableSearch.getInt("StudentId"))
				{
					student = st;
					break;
				}
			}
			CourseOffering theOffering = offeringList.get(tableSearch.getInt("CourseKey") - 1);
			Registration reg = new Registration();
			reg.completeRegistration(student, theOffering);
		}
	}
	
	/**
	 * Checks the user ID entered to get into the Student Registration System
	 * @param id the user ID
	 * @return true if the user ID is found in the database, false otherwise.
	 * @throws SQLException
	*/
	public boolean verifyLogin(int id) throws SQLException 
	{
		String query= "SELECT * FROM students where Id = ?";
		PreparedStatement prep = connect.prepareStatement(query);
		prep.setInt(1, id);
		tableSearch = prep.executeQuery();
		
		if(tableSearch.next()) 
			return true;
		
		return false;
	}
	
	/**
	 * Adds student to the database.
	 * @param id the student's ID number
	 * @param name the student's name
	*/
	public void addStudent(int id, String name) throws SQLException
	{
		String query = "INSERT INTO students (ID, name) values(?, ?)";
		PreparedStatement prep = connect.prepareStatement(query);
		prep.setInt(1, id);
		prep.setString(2, name);
		prep.executeUpdate();
		prep.close();
	}
	
	/**
	 * Adds a course into the database.
	 * @param courseKey the data base key of a course
	 * @param courseName name of the course
	 * @param courseID ID number of the course
	 * @param courseSection lecture section of the course
	 * @param courseCapacity number of seats in the lecture section
	*/
	public void addCourse(int courseKey, String courseName, int courseID, int courseSection, int courseCapacity) throws SQLException 
	{
		String query = "INSERT INTO courses (courseKey, courseName, courseId, section, capacity) values(?, ?, ?, ?, ?)";
		PreparedStatement prep = connect.prepareStatement(query);
		prep.setInt(1, courseKey);
		prep.setString(2, courseName);
		prep.setInt(3, courseID);
		prep.setInt(4, courseSection);
		prep.setInt(5, courseCapacity);
		prep.executeUpdate();
		prep.close();
	}
	
	/**
	 * Adds a registration into the database.
	 * @param registration 
	 * @throws SQLException
	*/
	public void addRegistration(Registration registration) throws SQLException 
	{
		String query = "INSERT INTO registrations (StudentId, CourseKey, Grade) values (?, ?, ?)";
		PreparedStatement prep = connect.prepareStatement(query);
		prep.setInt(1, registration.getTheStudent().getStudentID());
		prep.setInt(2, registration.getTheOffering().getDbKey());
		prep.setString(3, registration.getGrade() + "");
		prep.executeUpdate();
		prep.close();
	}
	
	/**
	 * Removes a registration from the database.
	 * @param registration
	 * @throws SQLException
	 */
	public void removeRegistration(Registration registration) throws SQLException 
	{
		String query = "DELETE FROM registrations where StudentId = ? and CourseKey = ?";
		PreparedStatement prep = connect.prepareStatement(query);
		prep.setInt(1, registration.getTheStudent().getStudentID());
		prep.setInt(2, registration.getTheOffering().getDbKey());
		prep.executeUpdate();
		prep.close();
	}

	/**
	 * Main method to create the database.
	 * @param args Command Line Arguments.
	*/
	public static void main(String [] args) 
	{
		try 
		{
			CreateDatabase myApp = new CreateDatabase();
			myApp.initializeConnection();
			myApp.courseTable();
			myApp.RegistrationTable();
			myApp.studentTable();
			myApp.addStudent(1001, "Sara Flanders");
			myApp.addStudent(1002, "Sam Reinhart");
			myApp.addCourse(1, "ENGG", 233, 1, 100);
			myApp.addCourse(2, "ENGG", 233, 2, 100);
			myApp.addCourse(3, "ENSF", 409, 1, 150);
			myApp.addCourse(4, "ENSF", 409, 2, 110);
			myApp.addCourse(5, "PHYS", 259, 1, 170);
			myApp.addCourse(6, "PHYS", 259, 2, 190);
			myApp.addCourse(7, "MATH", 375, 1, 90);
			myApp.addCourse(8, "MATH", 375, 2, 100);
			myApp.addCourse(9, "MATH", 375, 3, 70);
			myApp.addCourse(10, "ENEL", 353, 1, 140);
			myApp.addCourse(11, "ENEL", 353, 2, 190);
			myApp.addCourse(12, "ENCM", 369, 1, 80);
			myApp.addCourse(13, "ENCM", 369, 2, 80);
			myApp.addCourse(14, "ENCM", 369, 3, 90);
			myApp.addCourse(15, "CHEM", 209, 1, 290);
		}
		catch(SQLException db) 
		{
			System.out.println("Cannot create database.");
		}
		System.out.println("\nDatabase Successfully Created.\n");
	}
}