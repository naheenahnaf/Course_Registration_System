import java.util.ArrayList;

/**
 * Class Course defines the various courses in the 
 * Student Registration System.
*/
public class Course {
	/** Name of the course.*/
	private String courseName;
	
	/** Course ID.*/
	private int courseNum;
	
	/** List of prerequistes for the courses.*/
	private ArrayList<Course> preReq;
	
	/** List of offerings made for each course.*/
	private ArrayList<CourseOffering> offeringList;
	
	/**
	 * Constructor for Course object.
	 * @param courseName Name of the Course
	 * @param courseNum ID Number of the Course
	*/
	public Course(String courseName, int courseNum) {
		this.setCourseName(courseName);
		this.setCourseNum(courseNum);
		// Both of the following are only association
		preReq = new ArrayList<Course>();
		offeringList = new ArrayList<CourseOffering>();
	}
	
	/**
	 * An offering is made for a particular course.
	 * @param offering The offering made
	*/
	public void addOffering(CourseOffering offering) {
		if (offering != null && offering.getTheCourse() == null) {
			offering.setTheCourse(this);
			if (!offering.getTheCourse().getCourseName().equals(courseName)
					|| offering.getTheCourse().getCourseNum() != courseNum) {
				System.err.println("Error! This section belongs to another course!");
				return;
			}
			
			offeringList.add(offering);
		}
	}
	
	/**
	 * Getter method which gets the name of the course.
	 * @return name of the course
	*/
	public String getCourseName() {
		return courseName;
	}
	
	/**
	 * Setter method which sets the name of the course.
	 * @param courseName
	*/
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	/**
	 * Getter method which gets the ID number of the course.
	 * @return ID number of the course
	*/
	public int getCourseNum() {
		return courseNum;
	}
	
	/**
	 * Setter method which sets the ID number of the course.
	 * @param courseNum
	*/
	public void setCourseNum(int courseNum) {
		this.courseNum = courseNum;
	}

	/**
	 * Prints object string for this Class Course.
	 * @return the string
	*/
	@Override
	public String toString () {
		String st = "\n";
		st += getCourseName() + " " + getCourseNum ();
		st += "\nAll course sections:\n";
		for (CourseOffering c : offeringList)
			st += c;
		st += "\n-------\n";
		return st;
	}

	/**
	 * Getter method which gets a course offering at a particular index
	 * of the course offering list.
	 * @param i index of the required course offering
	 * @return the course offering
	*/
	public CourseOffering getCourseOfferingAt(int i) {
		// TODO Auto-generated method stub
		if (i < 0 || i >= offeringList.size() )
			return null;
		else
			return offeringList.get(i);
	}

	/**
	 * Getter method which gets the course offering list.
	 * @return course offering list
	*/
	public ArrayList<CourseOffering> getOfferingList()
	{
		return offeringList;
	}

	/**
	 * Adds a prerequisite to a course.
	 * @param course the course
	*/
	public void addPreReq(Course course) 
	{
		preReq.add(course);
	}

	/**
	 * Getter method which gets the prerequisite
	 * @return the prerequisite
	*/
	public ArrayList<Course> getPreReq()
	{
		return preReq;
	}
}