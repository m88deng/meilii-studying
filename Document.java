package studyingApplication;

public abstract class Document {

	protected String title;
	protected String content = "";

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public abstract void downloadFile();
	
}
