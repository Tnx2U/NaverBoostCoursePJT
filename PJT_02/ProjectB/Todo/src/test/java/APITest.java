import jhw.pjt2.nts.todo.dao.CardDao;
import jhw.pjt2.nts.todo.dto.Card;

public class APITest {
	public static void main(String[] args) {
		CardDao dao = new CardDao();
		Card result = dao.getCardById(1);
		
		System.out.println(result.toString());
	}
}
