package studyingApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Student extends User{
	
	private ArrayList<String[]> quizProgress;
	private static ArrayList<Student> allStudents = new ArrayList<>();
	private final static String menu = "\n\nMain Menu\n=====================\n1. Create new notes\n2. Enroll in a class\n3. My notes\n4. My classes\n5. My profile\n6. Log out\n=====================";
	private final static String classMenu = "\n\n\tMenu\n\t=====================\n\t1. Take a quiz\n\t2. Return to main menu\n\t=====================";
	private final static String displayQuizOrder = "\n\n\tQuiz Display Order\n\t=====================\n\t1. Date (most recent first)\n\t2. Grade (highest grade first)\n\t=====================";
	
	public Student(String firstName, String lastName, String email, String password) {
		super(firstName, lastName, email, password);
		this.type = "Student";
		this.quizProgress = new ArrayList<>();
		Student.addStudent(this);
	}
	
	//display profile information
	public void displayProfile() {
		super.displayProfile();
		System.out.println("Number of enrolled classes: " + this.classesList.size());
		this.displayQuizProgress();
	}

	//add Student to system
	public static void addStudent(Student student) {
		Student.allStudents.add(student);
	}
	//display quiz progress
	public void displayQuizProgress() {
		//if student previously took quizzes
		if(!this.quizProgress.isEmpty()) {
			System.out.println("\nQuiz Progress List");
			System.out.println("Please choose the way you want to display the Quiz Progress List");
			int order = getQuizProgressSortingOrder();
			
			//sort in date order
			if(order == 1) {
				sortQuizProgressByDate();
			}
			
			//sort in grade order
			else if(order == 2) {
				sortQuizProgressByGrade();
			}
			
			//display date and result of each quiz in quiz progress
			for(int i=0; i<quizProgress.size(); i++) {
				for(int j=0; j<quizProgress.get(i).length-1; j++) {
					System.out.print(quizProgress.get(i)[j] + "   ");
				}
				System.out.println("");
			}
		}
		//if student has not taken any quiz yet
		else {
			System.out.println("\nYour quiz progress list is currently empty.");
		}
	}
	
	//retrieve student quiz progress for the teacher
	public void retrieveStudentQuizProgress() {
		//if student has previously taken quizzes
		if(!this.quizProgress.isEmpty()) {
			//sort in date order
			System.out.println("\nQuiz Progress List of " + this.getFullname());
			//display date and result of each quiz in quiz progress
			this.sortQuizProgressByDate();
			for(int i=0; i<quizProgress.size(); i++) {
				for(int j=0; j<(quizProgress.get(i).length)-1; j++) {
					System.out.print(quizProgress.get(i)[j] + "   ");
				}
				System.out.println("");
			}
		}
		//if student has not taken any quiz yet
		else {
			System.out.println("\nEmpty quiz progress list for " + this.getFullname());
		}
	}

	//retrieve quiz sorting order
	public int getQuizProgressSortingOrder() {
		int order = UserInterfaceManagement.getUserOption(displayQuizOrder);
		if(order == 1 || order == 2) {
			return order;
		}
		else {
			System.out.println("Invalid option");
			return getQuizProgressSortingOrder();
		}
	}
	
	//sort quiz progress in newest date first order
	public void sortQuizProgressByDate() {
		Comparator<String[]> DateComparator = new Comparator<String[]>() {
			@Override
			public int compare(String[] s1, String[] s2) {
				if(s1[1].compareTo(s2[1])>0) {
					return -1;
				}
				else if(s1[1].compareTo(s2[1])<0) {
					return 1;
				}
				else {
					return 0;
				}
			}
		};
		Collections.sort(quizProgress, DateComparator);
	}	
	
	//sort quiz progress in highest grade first order
	public void sortQuizProgressByGrade() {
			Comparator<String[]> GradeComparator = new Comparator<String[]>() {
				@Override
				public int compare(String[] s1, String[] s2) {
					if(s1[3].compareTo(s2[3])>0) {
						return -1;
					}
					else if(s1[3].compareTo(s2[3])<0) {
						return 1;
					}
					else {
						return 0;
					}
				}
			};
			Collections.sort(quizProgress, GradeComparator);
	}
	
	//display class information + available quizzes + quiz menu for action
	public void displayClasses() {
		if(this.classesList.isEmpty()) {
			System.out.println("\nYou are not enrolled in any class");
		}
		else {
			System.out.println("\nEnrolled classes: " + this.classesList.size());
			System.out.println("Your Classes");
			
			for(int i=0; i<this.classesList.size();i++) {
				Classes classes = classesList.get(i);
				System.out.println((i+1) + ". " + classes);
				System.out.println("\tTeacher: " + classes.getTeacher().getFullname());
				System.out.println("\tEmail: " + classes.getTeacher().getEmail());
				try {
					System.out.println("\tAvailable quizzes: " + classes.getQuizList().size());
				} catch(NullPointerException e) {
					System.out.println("\tAvailable quizzes: 0");
				}
			}
		}
	}
	
	//all methods needed for a student to take a quiz, write answer, get grade
	public void takingQuiz(Quiz quiz) {
		quiz.displayQuestions();
		quiz.displayResult(quiz.saveResult(quiz.getScore(), this));
		quiz.showCorrection();
		quiz.setScore(0);
	}
	
	//after displaying classes, quiz interface
	public void quizInterface() {
		if(!this.classesList.isEmpty()){
			int action = 0;
			do {
				action = UserInterfaceManagement.getUserOption(Student.getClassMenu());
				switch(action) {
				case 1://take a quiz
					try{
						Quiz chosenQuiz = UserInterfaceManagement.getChosenQuiz(this);
						chosenQuiz.setTakingQuiz(true);
						this.takingQuiz(chosenQuiz);
						chosenQuiz.setTakingQuiz(false);
					} catch(NullPointerException e) {
					}
					break;
				case 2://return to main menu;
					break;
				default:
					System.out.println("Invalid option");
				}
			} while(action!=2);
		}
	}

	//getters and setters
	public ArrayList<String[]> getQuizProgress() {
		return quizProgress;
	}

	public void setQuizProgress(ArrayList<String[]> quizProgress) {
		this.quizProgress = quizProgress;
	}

	public static ArrayList<Student> getAllStudents() {
		return allStudents;
	}

	public static void setAllStudents(ArrayList<Student> allStudents) {
		Student.allStudents = allStudents;
	}

	public static String getClassmenu() {
		return classMenu;
	}

	public static String getDisplayquizorder() {
		return displayQuizOrder;
	}

	public static String getMenu() {
		return menu;
	}
	
	public static String getClassMenu() {
		return classMenu;
	}
}
