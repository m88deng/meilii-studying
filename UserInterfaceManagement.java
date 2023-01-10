package studyingApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class UserInterfaceManagement {
	public static Scanner sc = new Scanner(System.in);
	
	//create a new Student profile
	public static Student newStudent() {
		System.out.print("First Name: ");
		String firstName = sc.nextLine();
		System.out.print("Last Name: ");
		String lastName = sc.nextLine();
		System.out.print("Email: ");
		String email = sc.nextLine();
		String password = getPassword();
		return new Student(firstName, lastName, email, password);
	}
	
	private static String getPassword() {
		System.out.print("Password (minimum 8 characters): ");
		String password = sc.nextLine();
		if(password.length()<8){
			System.out.println("Please respect the minimum requirement");
			return getPassword();
		}
		return password;
	}
	
	//create a new Teacher profile
	public static Teacher newTeacher() {
		System.out.print("First Name: ");
		String firstName = sc.nextLine();
		System.out.print("Last Name: ");
		String lastName = sc.nextLine();
		System.out.print("Email: ");
		String email = sc.nextLine();
		String password = getPassword();
		System.out.print("Subject of teaching: ");
		String subject = sc.nextLine();
		return new Teacher(firstName, lastName, email, password, subject);	
	}
	
	//identify the type of user
	public static String getUserType() {
		System.out.print("Type of profile\nType s for Student or t for Teacher: ");
		String type = sc.nextLine();
		if(type.equalsIgnoreCase("s")) {
			return "s";
		}
		else if(type.equalsIgnoreCase("t")) {
			return "t";
		}
		else {
			System.out.println("Invalid input");
			return getUserType();
		}
	}
	
	//retrieve user option depending on given menu
	public static int getUserOption(String menu) {
		System.out.println(menu);
		System.out.print("\nPlease input the number associated with the operation you want to execute: ");
		return stringToInt();
	}
	
	//retrieve name of the class to be created
	public static String getClassName() {
		System.out.print("\nClass name: ");
		return sc.nextLine();
	}
	
	//upon enrollment, verify class exists already
	public static Classes findClass() {
		System.out.print("\nInput the enrollment key: " );
		String key = sc.nextLine();
		if(Classes.getClassesData().containsKey(key)) {
			System.out.print("Class found: ");
			Classes.displayTeacherClass(key);
			System.out.println("\nSuccessfully enrolled in");
			return Classes.getClassesData().get(key);
		}
		System.out.println("\nNo class found");
		return null;
	}
	
	//create new notes
	public static Notes createNewNotes(User user) {
		ArrayList<Notes> notesList = user.getNotesList();
		System.out.print("\nNotes Title: ");
		String title = sc.nextLine();
		if(!notesList.isEmpty()) {
			for(int i=0; i<notesList.size(); i++) {
				if(notesList.get(i).getTitle().equals(title)){
					System.out.println("Please choose another title");
					return createNewNotes(user);
				}
			}
		}
		return new Notes(title);
	}
	
	//add content to notes
	public static void writeNotes(Notes notes) {
		int max = 10;
		String text="";
		System.out.println("\nWrite the content (max " + max + " lines) for your notes " + notes.getTitle());
		//how to actually write down
		for(int i=0; i<max; i++) {
			text = text + sc.nextLine() + "\n";
		}
		notes.setContent(text);
	}

	//get the notes the user wants to have access to
	public static Notes getChosenNotes(User user) {
		if(!user.getNotesList().isEmpty()) {
			user.displayNotes();
			System.out.print("\nChoose the notes (number of the notes): ");
			int notesNumber = UserInterfaceManagement.stringToInt();
			try{
				return user.getNotesList().get(notesNumber-1);
			} catch(IndexOutOfBoundsException e) {
				System.out.println("Invalid option");
				return getChosenNotes(user);
			}
		}
		else {
			System.out.println("\nNo notes to display");
			return null;
		}
	}
	
	//create new quiz
	public static Quiz createQuiz(Teacher teacher) {
		ArrayList<Quiz> quizList = teacher.getQuizList();
		System.out.print("\nQuiz title: ");
		String title = sc.nextLine();
		if(!quizList.isEmpty()) {
			for(int i=0; i<quizList.size(); i++) {
				if(quizList.get(i).getTitle().equals(title)){
					System.out.println("Please choose another title");
					return createQuiz(teacher);
				}
			}
		}
		return new Quiz(title);
	}
	
	//create quiz questions
	//store answers in answer sheet
	public static void createQuestions(Quiz quiz) {
		System.out.println("\n"+ quiz.getTitle() + " is a multiple choice quiz of " + quiz.getTotalQuestionNumbers() + " questions\n");
		for(int i=0; i<quiz.getAllQuestion().length; i++) {
			for(int j=0; j<quiz.getAllQuestion()[i].length; j++) {
				System.out.println("\nQuestion " + (j+1));
				System.out.print("Enter the question: ");
				quiz.getAllQuestion()[i][j][0] = sc.nextLine();			
				
				System.out.println("Enter " + quiz.getNumberOfChoices() + " possible answers: ");
				for(int k=1; k<quiz.getAllQuestion()[i][j].length; k++) {
					System.out.print(k + ". ");
					quiz.getAllQuestion()[i][j][k] = sc.nextLine();
				}
				quiz.getAnswerSheet()[j] = UserInterfaceManagement.getCorrectAnswer(quiz);
			}			
		}
	}
	
	//retrieve correct answer of the quiz
	public static int getCorrectAnswer(Quiz quiz) {
		System.out.print("\nCorrect answer (select the number): ");			
		int correctAnswer = UserInterfaceManagement.stringToInt();
		if(correctAnswer<1 || correctAnswer>quiz.getNumberOfChoices()) {
			System.out.println("Invalid input");
			return getCorrectAnswer(quiz);
		}
		else {
			return correctAnswer;
		}
	}
	
	//retrieve student's response to the quiz
	public static int getUserAnswer(Quiz quiz) {
		System.out.print("\nYour answer: ");
		int answer = UserInterfaceManagement.stringToInt();
		if(answer<1||answer>quiz.getNumberOfChoices()) {
			System.out.println("Invalid answer");
			return getUserAnswer(quiz);
		}
		else {
			return answer;
		}
	}
		
	//retrieve user's chosen class
	public static Classes getChosenClass(User user) {
		if(!user.getClassesList().isEmpty()){
			user.displayClassList();
			System.out.print("\nChoose a class (number of the class): ");
			int classNumber = UserInterfaceManagement.stringToInt();
			try {
				return user.getClassesList().get(classNumber-1);
			} catch(IndexOutOfBoundsException e) {
				System.out.println("Invalid option");
				return getChosenClass(user);
			}
		}
		else {
			System.out.println("\nNo class to display");
			return null;
		}
	}
	
	//get the quiz the student wants to take
	public static Quiz getChosenQuiz(Student student) {
		//get class
		Classes chosenClass = UserInterfaceManagement.getChosenClass(student);
		if(chosenClass.getQuizList().isEmpty()) {
			System.out.println(chosenClass + " has no available quizzes.");
			return null;
		}
		else {
		//get quiz
			System.out.println("\nAvailable quizzes in " + chosenClass + ":");
			chosenClass.displayQuizList();
			System.out.print("\nChoose a quiz (number of the quiz): ");
			int quizNumber = UserInterfaceManagement.stringToInt();
			try {
				return chosenClass.getQuizList().get(quizNumber-1);
			} catch(IndexOutOfBoundsException e) {
				System.out.println("Invalid option");
				return getChosenQuiz(student);
			}
		}
	}
	
	//get the quiz the teacher wants to see
	public static Quiz getChosenQuiz(Teacher teacher) {
		teacher.displayQuizzes();
		System.out.print("\nChoose a quiz (number of the quiz): ");
		int quizNumber = UserInterfaceManagement.stringToInt();
		try{
			return teacher.getQuizList().get(quizNumber-1);
		} catch(IndexOutOfBoundsException e) {
			System.out.println("Invalid option");
			return getChosenQuiz(teacher);
		}
	}
	
	//get student to view quiz progress (search algorithm)
	public static Student getChosenStudent() {
		System.out.print("\nInput the student's last name: ");
		String lastName = sc.nextLine();
		Student s = new Student("", lastName, "", "");
		Student.getAllStudents().remove(s);
		Collections.sort(Student.getAllStudents());
		int index = Collections.binarySearch(Student.getAllStudents(), s);
		return Student.getAllStudents().get(index);
	}

	//confirm user wants to log out
	public static String confirmLogOut() {
		System.out.print("Confirm to log out? (Y/N) ");
		String confirm = sc.nextLine();
		if(confirm.equalsIgnoreCase("y")) {
			return "y";
		}
		if(confirm.equalsIgnoreCase("n")) {
			return "n";
		}
		else {
			System.out.println("Invalid input");
			return confirmLogOut();
		}	
	}
	
	//convert string answer to an integer
	private static int stringToInt() {
		String number = sc.nextLine();
		try {
			return Integer.parseInt(number);
		}
		catch(NumberFormatException e) {
			System.out.println("Invalid input");
			return stringToInt();
		}
	}

}
