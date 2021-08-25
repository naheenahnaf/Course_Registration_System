import java.util.*;

/**
 * Class CourseOffering defines the offerings for each course
 * in the Student Registration System.
*/
public class CourseOffering {
	/** Lecture section number.*/
	private int secNum;
	
	/** Amount of seats in the lecture section.*/
	private int secCap;
	
	/** The course being offered.*/
	private Course theCourse;
	
	/** List of course registrations.*/
	private ArrayList <Registration> offeringRegList;
	
	/** Identifier for courses in the data base.*/
	private int dbKey;
	
	/**
	 * Constructor for object CourseOffering.
	 * @param secNum the section number
	 * @param secCap number of seats in the section
	 * @param dbKey database key for a course
	*/
	public CourseOffering (int secNum, int secCap, int dbKey) {
		this.setSecNum(secNum);
		this.setSecCap(secCap);
		this.dbKey = dbKey;
		offeringRegList = new ArrayList <Registration>();
	}
	
	/**
	 * Getter method which gets the section number.
	 * @return the section number
	*/
	public int getSecNum() {
		return secNum;
	}
	
	/**
	 * Setter method which sets the section number.
	 * @param secNum the section number
	*/
	public void setSecNum(int secNum) {
		this.secNum = secNum;
	}
	
	/**
	 * Getter method which gets the section capacity.
	 * @return the section capacity
	*/
	public int getSecCap() {
		return secCap;
	}
	
	/**
	 * Setter method which sets the section capacity.
	 * @param secCap the section capacity
	*/
	public void setSecCap(int secCap) {
		this.secCap = secCap;
	}
	
	/**
	 * Getter method which gets the course.
	 * @return the course
	*/
	public Course getTheCourse() {
		return theCourse;
	}
	
	/**
	 * Setter method which sets the course
	 * @param theCourse
	*/
	public void setTheCourse(Course theCourse) {
		this.theCourse = theCourse;
	}

	/**
	 * Prints object string for this Class CourseOffering.
	 * @return the string
	*/
	@Override
	public String toString () {
		String st = "\n";
		st += getTheCourse().getCourseName() + " " + getTheCourse().getCourseNum() + "\n";
		st += "Section: LEC 0" + getSecNum() + "\nSeats: "+ getSecCap() +"\n";
		//We also want to print the names of all students in the section
		return st;
	}
	
	/**
	 * Adds a registered course into the offering list.
	 * @param registration the registered course
	*/
	public void addRegistration(Registration registration) {
		// TODO Auto-generated method stub
		offeringRegList.add(registration);
	}
	
	/**
	 * Removes a registered course from the offering list.
	 * @param reg the registered course
	*/
	public void removeRegistration(Registration reg) {
		offeringRegList.remove(reg);
	}
	
	/**
	 * Getter method which gets the data base key for a course.
	 * @return the database key
	*/
	public int getDbKey() {
		return dbKey;
	}
}