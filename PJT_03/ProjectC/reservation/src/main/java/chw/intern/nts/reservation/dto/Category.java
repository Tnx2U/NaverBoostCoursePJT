package chw.intern.nts.reservation.dto;

public class Category {
	private int count;
	private int id;
	private String name;

	public int getId() {
		return id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Category [count=" + count + "id=" + id + ", name=" + name + "]";
	}
}