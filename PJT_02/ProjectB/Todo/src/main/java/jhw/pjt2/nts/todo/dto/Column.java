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

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Column [id=" + id + ", title=" + title + "]";
	}
}
