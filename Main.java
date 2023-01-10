package studyingApplication;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		//create 2 classes to test
		Classes history = new Classes("History", new Teacher("Jerry","Bleton","jbleton@vaniercollege.qc.ca","12345","History"));
		Classes cal1 = new Classes("Calculus I", new Teacher("Martin","Caberlin","mcaberlin@vaniercollege.qc.ca","98765","Mathematics"));
		System.out.println(Classes.getClassesData());
		
		
		//creating a quiz for History to test
		Quiz middleAges = new Quiz("The Middle Ages");
		String[][][] middleAgesQuestions = {
				{
					{"When do the Middle Ages start?","453","476","512"},
					{"What important event happened in 476?","Discovery of Islam","Fall of western Roman Empire","Crown of Charlemagne as Roman Emperor"},	
					{"Why did people flee to the countryside after the fall of the Roman Empire?","Violence and robbery","Agricultural desire","Natural disasters"},
					{"When do the Middle Ages end?","1453","1472","1492"},
					{"What did the Protestant Reformation bring in Europe?","Increase in economy","Increase in literacy","Increase in political stability"}
				}
		};
		int[] middleAgesAnswerSheet = {2,2,1,3,2};
		middleAges.setAllQuestion(middleAgesQuestions);
		middleAges.setAnswerSheet(middleAgesAnswerSheet);
		history.getQuizList().add(middleAges);
		
		
		//beginning of Application
		System.out.println("\n\nWelcome to Meili Studying!\nA free to use application to improve your academic performance!");
		System.out.println("Let's start by creating a profile -->\n");
		
		//for Student profile
		if(UserInterfaceManagement.getUserType().equalsIgnoreCase("s")) {
			Student student = UserInterfaceManagement.newStudent();
			System.out.println("\nSuccessfully created your Student Profile!");
		
			//creating notes to test
			Notes historyCh1 = new Notes("French Revolution");
			String content = "The French Revolution (French: Révolution française) was a period of radical political and societal change"
					+ "\nin France that began with the Estates General of 1789 and ended with the formation of the French Consulate in November 1799. "
					+ "\nMany of its ideas are considered fundamental principles of liberal democracy, while phrases like "
					+ "\nliberté, égalité, fraternité reappeared in other revolts, such as the 1917 Russian Revolution, "
					+ "\nand inspired campaigns for the abolition of slavery and universal suffrage. The values and institutions "
					+ "\nit created dominate French politics to this day.";
			historyCh1.setContent(content);
			student.getNotesList().add(historyCh1);
			
			
			int option = 0;
			do {
				option = UserInterfaceManagement.getUserOption(Student.getMenu());
				switch(option) {
					case 1://create new notes
						Notes newNote = UserInterfaceManagement.createNewNotes(student);
						student.notesList.add(newNote);
						UserInterfaceManagement.writeNotes(newNote);
						break;
					case 2://enroll in a class
						try{
							Classes enrollClass = UserInterfaceManagement.findClass();
							enrollClass.getEnrolledStudents().add(student);
							student.classesList.add(enrollClass);
						}
						catch(NullPointerException e) {
						}
						break;
					case 3://view my notes
						student.displayNotes();
						//choose notes to download file
						student.notesInterface();
						break;
					case 4://view my classes
						student.displayClasses();
						//choose class to view more information and take quiz
						student.quizInterface();
						break;
					case 5://view my profile
						student.displayProfile();
						break;
					case 6://log out
						if(UserInterfaceManagement.confirmLogOut().equalsIgnoreCase("y")) {
							break;
						}
						else {
							option = 0;
						}
					default:
						System.out.println("Invalid input");
				}
			} while (option!=6);
		}
	
		//for Teacher profile
		else {
			Teacher teacher = UserInterfaceManagement.newTeacher();
			System.out.println("\nSuccessfully created your Teacher Profile!");
			
			//creating a new class with enrolled students who did quizzes
			Classes cal2 = new Classes("Calculus II", teacher);
			teacher.getClassesList().add(cal2);
			
			Student Melissa = new Student("Melissa","Deng","2127046@edu.vaniercollege.qc.ca","12345");
			System.out.println(Classes.getClassesData());
			cal2.getEnrolledStudents().add(Melissa);
			cal2.getEnrolledStudents().add(new Student("Edelina","Alieva","2157894@edu.vaniercollege.qc.ca","12345"));
			cal2.getEnrolledStudents().add(new Student("Kevin","Guo","2156610@edu.vaniercollege.qc.ca","12345"));
			cal2.getEnrolledStudents().add(new Student("Benjamin","Pratt","2157891@edu.vaniercollege.qc.ca","12345"));
			String[] quiz1 = {"Sums","2022-04-27","90.00 %"};
			String[] quiz2 = {"Sums","2022-04-26","80.00 %"};
			String[] quiz3 = {"Product","2022-04-29","85.00 %"};
			String[] quiz4 = {"Quotient","2022-04-29","95.00 %"};
			ArrayList<String[]> progress = new ArrayList<>();
			progress.add(quiz1);
			progress.add(quiz2);
			progress.add(quiz3);
			progress.add(quiz4);
			Melissa.setQuizProgress(progress); 
			//end of quiz creation
			
			int option = 0;
			do {
				option = UserInterfaceManagement.getUserOption(Teacher.getMenu());
				switch(option) {
				case 1://create new notes
					Notes newNote = UserInterfaceManagement.createNewNotes(teacher);
					teacher.notesList.add(newNote);
					UserInterfaceManagement.writeNotes(newNote);
					break;
				case 2://create new class
					Classes newClass = new Classes(UserInterfaceManagement.getClassName(),teacher);
					System.out.println("Enrollment key: " + newClass.getEnrollmentKey());
					teacher.classesList.add(newClass);
					System.out.println("\n" + newClass + " has been successfully created.");
					break;
				case 3://create new quiz
					teacher.createQuiz();
					break;
				case 4://view my notes
					teacher.displayNotes();
					//find a way to download specific notes
					teacher.notesInterface();
					break;
				case 5://view my classes
					teacher.displayClasses();
					teacher.classInterface();
					break;
				case 6://view my quizzes
					teacher.displayQuizzes();
					teacher.quizInterface();
					break;
				case 7://view my profile
					teacher.displayProfile();
					break;
				case 8://log out
					if(UserInterfaceManagement.confirmLogOut().equalsIgnoreCase("y")) {
						break;
					}
					else {
						option = 0;
						break;
					}
				default:
					System.out.println("Invalid input");
				}
			} while (option!=8);
		}
	System.out.println("\nThank you for using Meili Studying!");
	}

}
