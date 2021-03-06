package jhw.pjt2.nts.todo.dto;

public class Card implements Cloneable {
	private int id;
	private String title;
	private String managerName;
	private int priority;
	private String registedDate;
	private int columnId;
	private int cardOrder;

	public Card(int id, String title, String managerName, int priority, String registedDate, int columnId,
			int cardOrder) {
		this.id = id;
		this.title = title;
		this.managerName = managerName;
		this.priority = priority;
		this.registedDate = registedDate;
		this.columnId = columnId;
		this.cardOrder = cardOrder;
	}

	// 자동 생성 파라미터를 제외한 지역변수만 받는 생성자
	public Card(String title, String managerName, int priority) {
		this.id = -1;
		this.title = title;
		this.managerName = managerName;
		this.priority = priority;
		this.registedDate = "";
	}

	public int getId() {
		return id;
	}


	public String getTitle() {
		return title;
	}


	public String getManagerName() {
		return managerName;
	}


	public int getPriority() {
		return priority;
	}


	public String getRegistedDate() {
		return registedDate;
	}


	public int getColumnId() {
		return columnId;
	}


	public int getCardOrder() {
		return cardOrder;
	}


	@Override
	public Card clone() {
		Card card = null;
		try {
			card = (Card) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return card;
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", title=" + title + ", managerName=" + managerName + ", priority=" + priority
				+ ", registedDate=" + registedDate + ", columnId=" + columnId + ", cardOrder=" + cardOrder + "]";
	}
}
