package studyingApplication;

import java.util.ArrayList;

public class Teacher extends User{

	private String subject;
	private ArrayList<Quiz> quizList;	
	private final static String menu = "\n\nMain Menu\n=====================\n1. Create new notes\n2. Create a new class\n3. Create a new quiz\n4. My notes\n5. My classes\n6. My quizzes\n7. My profile\n8. Log out\n=====================";
	
	private final static String classMenu = "\n\n\tMenu\n\t=====================\n\t1. View a student's progress\n\t2. Return to main menu\n\t=====================";
	
	private final static String quizMenu = "\n\n\tMenu\n\t=====================\n\t1. View questions\n\t2. Download quiz\n\t3. Return to main menu\n\t=====================";
	
	public Teacher(String firstName, String lastName, String email, String password, String subject) {
		super(firstName, lastName, email, password);
		this.type = "Teacher";
		this.subject = subject;
		this.quizList = new ArrayList<>();
	}

	//display profile information
	public void displayProfile() {
		super.displayProfile();
		System.out.println("Teaching Specialty: " + this.subject);
		System.out.println("Number of teaching classes: " + this.classesList.size());
	}

	//display name of teaching classes
	public void displayClassesList() {
		if(this.classesList.isEmpty()) {
			System.out.println("\nYou have not created any class");
		}
		else {
			System.out.println("\nYour Classes");
			for(int i=0; i<this.classesList.size();i++) {
				System.out.println((i+1) + ". " + classesList.get(i));
			}
		}
	}
	
	//display classes information
	public void displayClasses() {
		if(this.classesList.isEmpty()) {
			System.out.println("\nYou have not created any class");
		}
		else {
			System.out.println("\nYour Classes");
			for(int i=0; i<this.classesList.size();i++) {
				Classes classes = classesList.get(i);
				System.out.println("\n" + (i+1) + ". " + classes);
				System.out.println("\tTeacher: " + classes.getTeacher().getFullname());
				System.out.println("\tAvailable quizes: " + classes.getQuizList().size());
				System.out.println("\tNumber of students: " + classes.getEnrolledStudents().size());
				classes.displayEnrolledStudentList();
			}
		}
	}

	//create a Quiz
	public void createQuiz() {
		if(!this.classesList.isEmpty()) {
			Classes chosenClass = UserInterfaceManagement.getChosenClass(this);
			System.out.println("Creating a quiz in the class " + chosenClass);
			
			Quiz newQuiz = UserInterfaceManagement.createQuiz(this);
			UserInterfaceManagement.createQuestions(newQuiz);
			
			chosenClass.getQuizList().add(newQuiz);
			this.getQuizList().add(newQuiz);	
			System.out.println("\nSuccessfully created the quiz " + newQuiz);
		}
		else {
			System.out.println("\nYou have not yet created a class to store quizzes");
		}
	}
	
	//display quizzes name
	public void displayQuizzes() {
		if(this.quizList.isEmpty()) {
			System.out.println("\nYou have not created any quiz yet");
		}
		else {
			System.out.println("\nYour Quizzes");
			for(int i=0; i<this.quizList.size(); i++) {
				System.out.println("\n" + (i+1) + ". " + this.quizList.get(i));
			}
		}
	}

	//quiz Interface
	public void quizInterface() {
		if(!this.quizList.isEmpty()){
			int action = 0;
			do {
				action = UserInterfaceManagement.getUserOption(Teacher.quizMenu);
				if(action ==1) {//view a quiz
					Quiz chosenQuiz = UserInterfaceManagement.getChosenQuiz(this);
					chosenQuiz.displayQuestions();
					chosenQuiz.displayAnswers();
				}
				else if(action ==2) { //return to main menu
					Quiz chosenQuiz = UserInterfaceManagement.getChosenQuiz(this);
					chosenQuiz.saveQuizContent();
					chosenQuiz.downloadFile();
				}
				else if(action == 3) {
				}
				else{
					System.out.println("Invalid option");
				}
			} while(action!=3);
		}
	}
	
	//class Interface with search algorithm
	public void classInterface() {
		if(!this.classesList.isEmpty()) {
			int action = 0;
			do {
				action = UserInterfaceManagement.getUserOption(classMenu);
				switch(action) {
				case 1: //view specific student's progress
					if(!Student.getAllStudents().isEmpty()) {
						Student chosenStudent = UserInterfaceManagement.getChosenStudent();
						chosenStudent.retrieveStudentQuizProgress();
					}
					else {
						System.out.println("\nNo student are yet registered in the system");
					}
					break;
				case 2: //return to main menu
					break;
				default:
					System.out.println("Invalid option");
				}
			} while(action!=2);
		}
	}
	
	//getters and setters
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public ArrayList<Quiz> getQuizList() {
		return quizList;
	}

	public void setQuizList(ArrayList<Quiz> quizList) {
		this.quizList = quizList;
	}

	public static String getMenu() {
		return menu;
	}
		
}
