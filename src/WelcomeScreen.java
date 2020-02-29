
public class WelcomeScreen {
	private String student_number = "N0173320W";
	private String student_name   = "DONALD CHINHURU";
	private String course_code    = "TEE 3133";
	private String course_name    = "SOFTWARE ENGINEERING APPLICATIONS";
	private String ass_name       = "ATM MACHINE WITH CUSTOM EXCEPTIONS";
	private int    year			  = 2019;
	private String semester       = "THIRD YEAR. PART 3.1";
	
	WelcomeScreen()
	{
		System.out.println("\t\t\t" + "Course name:   \t" + course_name);
		System.out.println("\t\t\t" + "Course code:   \t" + course_code);
		System.out.println("\t\t\t" + "Semester:      \t" + semester + " " + year);
		System.out.println();
		System.out.println("\t\t\t" + "Assignment:    \t" + ass_name);
		System.out.println("\t\t\t" + "Student Name:  \t" + student_name);
		System.out.println("\t\t\t" + "Student Number:\t" + student_number);
		
		System.out.println("\n\n");
	}

}
