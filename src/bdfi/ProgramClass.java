package bdfi;

public class ProgramClass implements Program {
	protected String id;
	protected String title;
	protected int prodYear;

	public ProgramClass(String id, String title, int prodYear) {
		this.id = id;
		this.title = title;
		this.prodYear = prodYear;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public int getProdYear() {
		return prodYear;
	}
}
