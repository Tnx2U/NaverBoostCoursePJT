import java.util.ArrayList;
import java.util.List;

import jhw.pjt2.nts.todo.dao.CardDao;
import jhw.pjt2.nts.todo.dto.Card;

public class APITest {
	public static void main(String[] args) {
		CardDao dao = new CardDao();

//		System.out.println("addCard(testCard1, 조현욱, 1, 'todo')");
//		dao.addCard(new Card("testCard1", "조현욱", 1, "todo"));
		
		Card result = dao.getCardById(2);
		System.out.println("getCardById(1)"+result.toString());
		
		List<Card> list = new ArrayList<>();
		list = dao.getAllCard();
		
		System.out.println("getAllCard : ");
		
		for(Card c : list) {
			System.out.println(c.toString());
		}
	}
}
