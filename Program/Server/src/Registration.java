/**
 * Class Registration defines the registration process
 * in the Student Registration System.
*/
public class Registration {
	/** Registered student.*/
	private Student theStudent;
	
	/** Offering made for a course.*/
	private CourseOffering theOffering;
	
	/** Grade recieved in the course.*/
	private char grade;
	
	/**
	 * Constructor for object Registration.
	 * @param st the registered student
	 * @param of the course offering
	 * @return string indicating result of registration.
	*/
	public String completeRegistration (Student st, CourseOffering of) {
		if(of == null)
			return "Course Section not Found.";
		
		else 
		{
			theStudent = st;
			theOffering = of;
			
			if(theStudent.getCourseAmount() >= 6) 
				return theStudent.getStudentName() + " currently has a maximum 6 course load.";
			
			for(Registration reg : theStudent.getStudentRegList())
			{
				if(reg.getTheOffering().getTheCourse().equals(theOffering.getTheCourse()))
					return theStudent.getStudentName() + " is already registered in this course.";
			}

			addRegistration();
			
			return theStudent.getStudentName() + " is now enrolled in " + theOffering.getTheCourse().getCourseName() + 
					" " + theOffering.getTheCourse().getCourseNum();
		}
	}
	
	/** 
	 * Adds student to the registration.
	 * Adds course offering to the registration.
	*/
	private void addRegistration () {
		theStudent.addRegistration(this);
		theOffering.addRegistration(this);
	}
	
	/**
	 * Getter method which gets the student.
	 * @return the student
	*/
	public Student getTheStudent() {
		return theStudent;
	}
	
	/**
	 * Setter method which sets the student.
	 * @param theStudent
	*/
	public void setTheStudent(Student theStudent) {
		this.theStudent = theStudent;
	}
	
	/**
	 * Getter method which gets the course offering.
	 * @return the course offering
	*/
	public CourseOffering getTheOffering() {
		return theOffering;
	}
	
	/**
	 * Setter method which sets the course offering.
	 * @param theOffering
	*/
	public void setTheOffering(CourseOffering theOffering) {
		this.theOffering = theOffering;
	}
	
	/**
	 * Getter method which gets the grade received.
	 * @return the grade received.
	*/
	public char getGrade() {
		return grade;
	}
	
	/**
	 * Setter method which sets the grade received
	 * @param grade
	*/
	public void setGrade(char grade) {
		this.grade = grade;
	}
	
	/**
	 * Prints object string for this Class Registration.
	 * @return the string
	*/
	@Override
	public String toString () {
		String st = "\n";
		st += "Student Name: " + getTheStudent() + "\n";
		st += "The Offering: " + getTheOffering () + "\n";
		st += "Grade: " + getGrade();
		st += "\n-----------\n";
		return st;	
	}
}