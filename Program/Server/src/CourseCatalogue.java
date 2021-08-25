import java.util.ArrayList;

/**
 * Class CourseCatalogue defines the course catalogue for the
 * Student Registration System.
*/
public class CourseCatalogue {
	/** List of all courses.*/
	private ArrayList <Course> courseList;
	
	/**
	 * Constructor for the CourseCatalogue object.
	 * @param courseList list of all courses
	 */
	public CourseCatalogue(ArrayList<Course> courseList) {
		this.courseList = courseList;
	}
	
	/**
	 * Searches the catalogue for a specific course.
	 * @param courseName name of the course
	 * @param courseNum ID number of the course
	 * @return the course if found, otherwise null
	 */
	public Course searchCat (String courseName, int courseNum) {
		for (Course c : courseList) {
			if (courseName.equals(c.getCourseName()) &&
					courseNum == c.getCourseNum()) {
				return c;
			}	
		}
		displayCourseNotFoundError();
		return null;
	}

	//Typically, methods that are called from other methods of the class
	//are private and are not exposed for use by other classes.
	//These methods are refereed to as helper methods or utility methods
	/** Prints an error message if a course isn't found.*/
	private void displayCourseNotFoundError() {
		// TODO Auto-generated method stub
		System.err.println("Course was not found!");	
	}
	
	/**
	 * Getter method which gets the course list
	 * @return the courselist
	*/
	public ArrayList <Course> getCourseList() {
		return courseList;
	}

	/**
	 * Setter method which sets the course list.
	 * @param courseList the course list
	*/
	public void setCourseList(ArrayList <Course> courseList) {
		this.courseList = courseList;
	}
	
	/**
	 * Prints object string for this Class CourseCatalogue.
	 * @return the string
	*/
	@Override
	public String toString () {
		String st = "\nAll courses in the catalogue: \n";
		for (Course c : courseList) {
			st += c;  //This line invokes the toString() method of Course
			st += "\n";
		}
		return st;
	}

	/**
	 * Searches the catalogue for a course
	 * @param course the course
	*/
	public void searchCatalogue(Course course)
	{
		for(CourseOffering c : course.getOfferingList())
		{
			System.out.println("\n" + course.getCourseName() + " " + course.getCourseNum()
			+ "\nSection: " + "LEC 0" + c.getSecNum() + "\nSeats: " + c.getSecCap());
		}
	}

	/**
	 * Searches the lecture section of a course.
	 * @return the course section, or null if not found
	*/
	public CourseOffering searchCourseSection(Course course, int section)
	{
		for(CourseOffering c : course.getOfferingList())
		{
			if(c.getSecNum() == section)
				return c;
		}
		return null;
	}

	/**
	 * Adds a course to be registered.
	 * @param courseName name of the course
	 * @param courseNumber ID number of the course
	 * @param courseSection lecture section of the course
	 * @param student the student being registered
	 * @return the registered course, or null if something went wrong
	*/
	public Registration addCourse(String courseName, int courseNumber, int courseSection, Student student)
	{
		Course searchCourse = searchCat(courseName, courseNumber);
		if(searchCourse == null)
			return null;
		
		CourseOffering offerCourse = searchCourseSection(searchCourse, courseSection);
		if(offerCourse == null)
			return null;
		
		Registration courseReg = new Registration();
		courseReg.setTheOffering(offerCourse);
		courseReg.setTheStudent(student);
		offerCourse.addRegistration(courseReg);

		return courseReg;
	}

	/**
	 * Searches the catalogue for a course (intermediate method)
	 * @param course the course
	*/
	public void searchCourse(String course)
	{
		String courseName = "";
		String number = "";

		for(int i = 0; i < course.length(); i++)
		{
			if(Character.isLetter(course.charAt(i)))
				courseName += course.charAt(i);
			else if(Character.isDigit(course.charAt(i)))
				number += course.charAt(i);
		}	
		int courseNumber = Integer.parseInt(number);
		searchCat(courseName, courseNumber);
	}
}