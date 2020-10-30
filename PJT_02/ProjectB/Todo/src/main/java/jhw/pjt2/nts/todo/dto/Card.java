package jhw.pjt2.nts.todo.dto;

public class Card {
	private int id;
	private String title;
	private String managerName;
	private int priority;
	private String registedDate;
	private String columnType;
	
	public Card(int id, String title, String managerName, int priority, String registedDate, String columnType) {
		super();
		this.id = id;
		this.title = title;
		this.managerName = managerName;
		this.priority = priority;
		this.registedDate = registedDate;
		this.columnType = columnType;
	}
	
	//자동 생성 파라미터를 제외한 지역변수만 받는 생성자
	public Card(String title, String managerName, int priority, String columnType) {
		super();
		this.id = -1;
		this.title = title;
		this.managerName = managerName;
		this.priority = priority;
		this.registedDate = "2020/10/20";
		this.columnType = columnType;
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
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getRegistedDate() {
		return registedDate;
	}
	public void setRegistedDate(String registedDate) {
		this.registedDate = registedDate;
	}
	public String getColumnType() {
		return columnType;
	}
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	
	
	@Override
	public String toString() {
		return "Card [id=" + id + ", title=" + title + ", managerName=" + managerName + ", priority=" + priority
				+ ", registedDate=" + registedDate + ", columnType=" + columnType + "]";
	}
}
