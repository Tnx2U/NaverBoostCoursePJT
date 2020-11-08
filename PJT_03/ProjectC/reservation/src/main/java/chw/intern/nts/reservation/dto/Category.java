package chw.intern.nts.reservation.dto;

public class Category {
//	기본생성자 가져야함
//	필드는 프라이빗하게
//	겟셋가져야함
	private int count;
	private int id;
	private String name;

//@Autowired 아래에 DI할 객체를 선언하면 set함수 없어도 자동을 할당해줌
	public Category() {
	};

	public Category(int count, int id, String name) {
		this.count = count;
		this.id = id;
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getId() {
		return id;
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
		return "Category [count=" + count + ", id=" + id + ", name=" + name + "]";
	}
}
