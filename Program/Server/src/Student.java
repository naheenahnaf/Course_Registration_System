import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class Student defines the students in the 
 * Student Registration System.
*/
public class Student {
	/** Name of the student.*/
	private String studentName;
	
	/** ID number of the student.*/
	private int studentID;
	
	/** List of registered students.*/
	private ArrayList<Registration> studentRegList;
	
	/** List of all courses.*/
	private ArrayList<Course> courses;
	
	/**
	 * Constructor for the Student object.
	 * @param studentName name of the student.
	 * @param studentID ID number of the student.
	*/
	public Student (String studentName, int studentID) {
		this.setStudentName(studentName);
		this.setStudentID(studentID);
		studentRegList = new ArrayList<Registration>();
		courses = new ArrayList<Course>();
	}

	/**
	 * Getter method which gets the student's name.
	 * @return name of the student
	*/
	public String getStudentName() {
		return studentName;
	}
	
	/**
	 * Setter method which sets the student's name.
	 * @param studentName
	*/
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	/**
	 * Getter method which gets the student's ID.
	 * @return student's ID number
	*/
	public int getStudentID() {
		return studentID;
	}
	
	/**
	 * Setter method which sets the student's ID.
	 * @param studentID
	*/
	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	/**
	 * Prints object string for this Class Student.
	 * @return the string
	*/
	@Override
	public String toString () {
		String st = "Student Name: " + getStudentName() + "\n" +
				"Student ID: " + getStudentID() + "\n\n";
		return st;
	}

	/**
	 * Adds registered student to the registration list.
	 * @param registration
	*/
	public void addRegistration(Registration registration) {
		studentRegList.add(registration);
	}
	
	/**
	 * Adds a course
	 * @param course
	*/
	public void addCourse(Course course) {
		courses.add(course);
	}

	/**
	 * Checks if a student can enroll in a particular course.
	 * @param course the course
	 * @return true if the student can enroll, otherwise false
	*/
	public boolean checkEnrollment(Course course)
	{
		for(Registration reg : studentRegList)
		{
			if(reg.getTheOffering().getTheCourse().getCourseName().equals(course.getCourseName()))
				return true;
		}

		return false;
	}

	/** Allows the user to view a student's course load.*/
	public void viewCourses()
	{
		if(studentRegList.isEmpty())
		{
			System.out.println(getStudentName() + " isn't enrolled in any courses yet.\n");
			return;
		}
		System.out.println(getStudentName() + " is enrolled into the following courses:");
		for(Registration courseReg : studentRegList)
		{
			System.out.println(courseReg.getTheOffering().getTheCourse().getCourseName() + 
			" " + courseReg.getTheOffering().getTheCourse().getCourseNum() + 
			" LEC 0" + courseReg.getTheOffering().getSecNum());
		}
	}

	/**
	 * Removes registered student from a course.
	 * @param course the course
	 * @param database the database containing the course
	 * @return true if removal is successful, otherwise false
	*/
	public boolean removeRegistration(Course course, CreateDatabase database)
	{
		for(Registration reg : studentRegList) 
		{
			if(reg.getTheOffering().getTheCourse().equals(course)) 
			{
				reg.getTheOffering().removeRegistration(reg);
				studentRegList.remove(reg);
				try 
				{
					database.removeRegistration(reg);
				} 
				catch (SQLException sql) 
				{
					System.err.println(sql.getMessage());
				}
				
				return true;
			}	
		}
		
		return false;
	}
	
	/**
	 * Getter method which gets the amount of courses in the registration list.
	 * @return size of student's registration list
	*/
	public int getCourseAmount() {
		return studentRegList.size();
	}

	/**
	 * Getter method which gets the courses.
	 * @return the courses
	 */
	public ArrayList<Course> getCourses(){
		return courses;
	}

	/**
	 * Getter method which gets the registration list for a particular student.
	 * @return the registration list
	 */
	public ArrayList<Registration> getStudentRegList(){
		return studentRegList;
	}
}