package studyingApplication;

import java.io.FileWriter;

public class Notes extends Document{
	
	public Notes(String title) {
		this.title = title;
	}
	
	public String toString() {
		return this.getTitle();
	}
	
	//display the content in notesInterface
	public void displayContent(User user) {
		System.out.println("\nNotes Title: " + this.title);
		System.out.println("Author: " + user.getFullname() + "\n");
		System.out.println(this.content);
	}
	
	//download the file
	@Override
	public void downloadFile() {
		try {
			//Creating a FileWriter object that will be linked to the specified file.
			FileWriter file = new FileWriter("Notes_" +this.getTitle() + ".txt");
			file.write(content);
			System.out.println("Notes " + this.title + " has been downloaded.");
			file.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
