package jhw.pjt2.nts.todo.dto;

public class CardOrder {
	private int id;
	private int columnId;
	private int cardId;
	private int cardOrder;

	public CardOrder(int id, int columnId, int cardId, int cardOrder) {
		super();
		this.id = id;
		this.columnId = columnId;
		this.cardId = cardId;
		this.cardOrder = cardOrder;
	}

	public CardOrder(int columnId, int cardId, int cardOrder) {
		super();
		this.columnId = columnId;
		this.cardId = cardId;
		this.cardOrder = cardOrder;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getColumnId() {
		return columnId;
	}

	public void setColumnId(int columnId) {
		this.columnId = columnId;
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public int getCardOrder() {
		return cardOrder;
	}

	public void setCardOrder(int cardOrder) {
		this.cardOrder = cardOrder;
	}

	@Override
	public String toString() {
		return "CardOrder [id=" + id + ", columnId=" + columnId + ", cardId=" + cardId + ", cardOrder=" + cardOrder
				+ "]";
	}
}
