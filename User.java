package studyingApplication;

import java.util.ArrayList;

public class User implements Comparable<User>{

	protected String type;
	protected String firstName;
	protected String lastName;
	protected String fullName;
	protected String email;
	protected String password;
	protected ArrayList<Notes> notesList;
	protected ArrayList<Classes> classesList;
	protected final String notesMenu = "\n\n\tMenu\n\t=====================\n\t1. View Notes\n\t2. Download Notes\n\t3. Return to main menu\n\t=====================";
	
	public User(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.fullName = firstName + " " + lastName;
		this.password = password;
		this.notesList = new ArrayList<>();
		this.classesList = new ArrayList<>();
	}
	
	//notes interface
	public void notesInterface() {
		if(!this.notesList.isEmpty()){
			int action = 0;
			do {
				action = UserInterfaceManagement.getUserOption(this.getNotesMenu());
				if(action == 1) {
					Notes chosenNotes = UserInterfaceManagement.getChosenNotes(this);
					chosenNotes.displayContent(this);
				}
				else if(action == 2) {
					Notes chosenNotes = UserInterfaceManagement.getChosenNotes(this);
					chosenNotes.downloadFile();
				}
				else if(action == 3) {
				}
				else {
					System.out.println("Invalid option");
				}
			} while(action!=3);
		}
	}
	
	//display notes List
	public void displayNotes() {
		if(notesList.isEmpty()) {
			System.out.println("\nYou have not written any notes");
		}
		else {
			System.out.println("\nYour Notes");
			for (int i=0; i<this.notesList.size(); i++) {
				System.out.println("\n" + (i+1) + ". " + this.notesList.get(i));
			}
		}
	}
	
	//display class list (name)
	public void displayClassList() {
		if(classesList.isEmpty()) {
			System.out.println("\nNo classes");
		}
		else {
			System.out.println("\nList of classes");
			for(int i=0; i<this.classesList.size();i++) {
				System.out.println((i+1)+". " + classesList.get(i));
			}
		}
	}
	
	//display profile information
	public void displayProfile() {
		System.out.println("\nYour Profile");
		System.out.println("\nType of Profile: " + this.getType());
		System.out.println("First Name: " + this.getFirstName());
		System.out.println("Last Name: " + this.getLastName());
		System.out.println("Email: " + this.getEmail());
		System.out.println("Password: " + this.getPassword());
	}
	
	//getters and setters
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFullname() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public ArrayList<Notes> getNotesList() {
		return notesList;
	}
	
	public void setNotesList(ArrayList<Notes> notesList) {
		this.notesList = notesList;
	}
	
	public ArrayList<Classes> getClassesList() {
		return classesList;
	}
	
	public void setClassesList(ArrayList<Classes> classesList) {
		this.classesList = classesList;
	}

	public String getNotesMenu() {
		return notesMenu;
	}

	public String toString() {
		return this.lastName + ", " + this.firstName;
	}
	
	public int compareTo(User user) {
		if(this.lastName.compareToIgnoreCase(user.lastName)>0) {
			return 1;
		}
		else if(this.lastName.compareToIgnoreCase(user.lastName)<0) {
			return -1;
		}
		else {
			return 0;
		}
	}
}
