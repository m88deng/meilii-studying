package studyingApplication;

import java.io.FileWriter;
import java.text.DecimalFormat;

public class Quiz extends Document implements Test {

	private final int totalQuestionNumbers = 5;
	private final int numberOfChoices = 3;
	private int score = 0;
	private int[] answerSheet;
	private boolean[] isCorrect;
	private int[][][] correctionGrid;
	private String[][][] allQuestion;
	private boolean takingQuiz = false;
	 	
	public Quiz(String title) {
		this.title = title;
		allQuestion = new String[1][totalQuestionNumbers][numberOfChoices+1];
		answerSheet = new int[totalQuestionNumbers];
		correctionGrid = new int[1][this.totalQuestionNumbers][2];
		isCorrect = new boolean[totalQuestionNumbers];
	}
	
	//display questions, and if taking quiz --> allow user to answer
	public void displayQuestions() {
		for(int i=0; i<allQuestion.length; i++) {
			System.out.println("");
			for(int j=0; j<allQuestion[i].length; j++) {
				System.out.print("Question " + (j+1) + ". ");	
				System.out.println(allQuestion[i][j][0]);
				for(int k=1; k<allQuestion[i][j].length; k++) {
					System.out.println("\t" + k + ". " + allQuestion[i][j][k]);
				}
			this.score = verifyAnswer(j);
			}
		}
	}
	
	//save quiz content to download later
	public void saveQuizContent() {
		for(int i=0; i<allQuestion.length; i++) {
			content = content + "\n";
			for(int j=0; j<allQuestion[i].length; j++) {
				content = content + "Question " + (j+1) + ". " + allQuestion[i][j][0] + "\n";
				for(int k=1; k<allQuestion[i][j].length; k++) {
					content = content + "\t" + k + ". " + allQuestion[i][j][k] + "\n";
				}
			}
		}
		content = content + "\nAnswer Sheet\n";
		for(int i=0; i<this.getTotalQuestionNumbers(); i++) {
			content = content + (i+1) + ". Correct answer: " + this.answerSheet[i] + "\n";
		}
	}
	
	//display correct answers from answer sheet
	public void displayAnswers() {
		System.out.println("\nAnswer Sheet");
		for(int i=0; i<this.getTotalQuestionNumbers(); i++) {
			System.out.println((i+1) + ". Correct answer: " + this.answerSheet[i]);
		}
	}
	
	//display student result (grade) of the quiz
	@Override
	public void displayResult(String[] result) {
		System.out.println("Score: " + this.score + " / " + this.totalQuestionNumbers);
		System.out.println("Grade: " + result[2]);
	}

	//get score of the quiz
	@Override
	public int verifyAnswer(int question) {
		if(takingQuiz) {
			int answer = UserInterfaceManagement.getUserAnswer(this);
			
			correctionGrid[0][question][0] = answer;
			correctionGrid[0][question][1] = answerSheet[question];
			
			if(answer == answerSheet[question]) {
				this.score++;
				this.isCorrect[question] = true;
			}
			else {
				this.isCorrect[question] = false;
			}
		}
		return this.score;
	}

	//show incorrect answers and correct answers
	public void showCorrection() {
		int userAnswer;
		int correctAnswer;
		System.out.println("\nCorrection");
		for(int i=0; i<totalQuestionNumbers; i++) {
			userAnswer = this.correctionGrid[0][i][0];
			correctAnswer = this.correctionGrid[0][i][1];
			if(!isCorrect[i]) {
				System.out.println((i+1) + ". Incorrect answer\tCorrect answer: " + correctAnswer + "\tYour answer: " + userAnswer);
			}
		}		
	}
	
	//save quiz result to student's QuizProgress
	public String[] saveResult(int score, Student student) {   
		String[] result = new String[4];
		String date = java.time.LocalDate.now().toString();
		String pattern = "0.00";
		double grade = (double)score/totalQuestionNumbers * 100;
		DecimalFormat df = new DecimalFormat(pattern);
		String percentage = df.format(grade)+ " %";
		result[0] = this.getTitle();
		result[1] = date;
		result[2] = percentage;
		result[3] = score+" ";
		student.getQuizProgress().add(result);
		return result;
	}  
	
	
	//download file (questions and answer sheet)
	@Override
	public void downloadFile() {
		try {
			//Creating a FileWriter object that will be linked to the specified file.
			FileWriter file = new FileWriter("Quiz_" +this.getTitle() + ".txt");
			file.write(content);
			System.out.println("Quiz " + this.title + " has been downloaded.");
			file.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//getters and setters
	public int getTotalQuestionNumbers() {
		return totalQuestionNumbers;
	}
		
	public String[][][] getAllQuestion() {
		return allQuestion;
	}

	public void setAllQuestion(String[][][] allQuestion) {
		this.allQuestion = allQuestion;
	}

	public int getNumberOfChoices() {
		return numberOfChoices;
	}
	
	public int[] getAnswerSheet() {
		return answerSheet;
	}

	public void setAnswerSheet(int[] answerSheet) {
		this.answerSheet = answerSheet;
	}

	public boolean isTakingQuiz() {
		return takingQuiz;
	}

	public void setTakingQuiz(boolean takingQuiz) {
		this.takingQuiz = takingQuiz;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean[] getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(boolean[] isCorrect) {
		this.isCorrect = isCorrect;
	}

	public int[][][] getCorrectionGrid() {
		return correctionGrid;
	}

	public void setCorrectionGrid(int[][][] correctionGrid) {
		this.correctionGrid = correctionGrid;
	}

	public String toString() {
		return this.title;
	}

	public void addContent(String text) {
		this.content = content + text + "\n";
	}

}
