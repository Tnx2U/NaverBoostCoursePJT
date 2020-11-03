package jhw.pjt2.nts.todo.dto;

public class Column {
	private int id;
	private String title;

	public Column(int id, String title) {
		this.id = id;
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public String toString() {
		return "Column [id=" + id + ", title=" + title + "]";
	}
}
