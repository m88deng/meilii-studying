package studyingApplication;

import java.util.HashMap;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Collections;

public class Classes {

	private String name;
	private String enrollmentKey;
	private Teacher teacher;
	private ArrayList<Quiz> quizList;
	private String[] teacherClass;
	private static HashMap<String, Classes> classesData = new HashMap<>();
	private ArrayList<Student> enrolledStudents;

	public Classes(String name, Teacher teacher) {
		this.name = name;
		this.teacher = teacher;
		this.teacherClass = new String[2];
		teacherClass[0]=this.name;
		teacherClass[1]=this.teacher.getFullname();
		this.enrollmentKey = this.getKey();
		this.enrolledStudents = new ArrayList<>();
		Classes.classesData.put(enrollmentKey, this);
		this.quizList = new ArrayList<>();
	}
	
	//create enrollment key for a class
	private String getKey() {
		UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString().substring(0, 5);
        return uuidString;
	}
	
	//display Class_Name with Class_Teacher
	public static void displayTeacherClass(String key) {
		System.out.println(Classes.getClassesData().get(key).getTeacherClass()[0] + " with " + Classes.getClassesData().get(key).getTeacherClass()[1]);
	}
	
	//set HashMap for classesData
	public static void setClassesData(HashMap<String, Classes> classesData) {
		Classes.classesData = classesData;
	}
	
	//display all quizzes in the Class
	public void displayQuizList() {
		for(int i=0; i<this.quizList.size();i++) {
			System.out.println((i+1) + ". " + quizList.get(i));
		}
	}
	
	//display names of all enrolled students in the class
	public void displayEnrolledStudentList() {
		if(this.enrolledStudents.isEmpty()) {
			System.out.println("\n\tNo student has enrolled in your class yet");
		}
		else {
			Collections.sort(this.enrolledStudents);
			for(Student s : enrolledStudents) {
				System.out.println("\t\t" + s);
			}
		}
	}
	
	//display all classes of the student
	public static void displayClass(Student student) {
		for(int i=0; i<student.getClassesList().size(); i++) {
			System.out.println((i+1) + ". " + student.getClassesList().get(i));
		}
	}
	
	//getters and setters
	public String getEnrollmentKey() {
        return this.enrollmentKey;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Teacher getTeacher() {
		return this.teacher;
	}
	
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String[] getTeacherClass() {
		return teacherClass;
	}

	public void setTeacherClass(String[] teacherClass) {
		this.teacherClass = teacherClass;
	}

	public ArrayList<Student> getEnrolledStudents() {
		return enrolledStudents;
	}

	public void setEnrolledStudents(ArrayList<Student> enrolledStudents) {
		this.enrolledStudents = enrolledStudents;
	}	
	
	public static HashMap<String, Classes> getClassesData() {
		return classesData;
	}
	
	public ArrayList<Quiz> getQuizList() {
		return quizList;
	}

	public void setQuizList(ArrayList<Quiz> quizList) {
		this.quizList = quizList;
	}

	public String toString() {
		return this.getName();
	}
	
}
