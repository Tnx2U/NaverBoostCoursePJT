package jhw.pjt2.nts.todo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jhw.pjt2.nts.todo.dto.Card;
import jhw.pjt2.nts.todo.global.QuerySelector;

public class CardDao {
	// sql연결정보 추후 별도의 파일로 분리할 것(보안 이슈)
	private static final String DB_URL = "jdbc:mysql://10.113.116.52:13306/user05";
	private static final String DB_USER = "user05";
	private static final String DB_PASSWORD = "user05";

	// 새로운 카드 추가
	public int addCard(Card inputCard) {
		int insertcount = 0;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement ps = conn.prepareStatement(QuerySelector.addCardQuery)) {
			ps.setString(1, inputCard.getTitle());
			ps.setString(2, inputCard.getManagerName());
			ps.setInt(3, inputCard.getPriority());

			insertcount = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("error occured in opening SQLconnection : " + e);
			e.printStackTrace();
		}

		return insertcount;
	}

	// 정렬된 전체 카드 조회
	public List<Card> getOrderedAllCard() {
		List<Card> cardList = new ArrayList<>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// try-with-resource 방식
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement ps = conn.prepareStatement(QuerySelector.getOrderedAllCardQuery);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				Card card = new Card(rs.getInt("c.id"), rs.getString("c.title"), rs.getString("c.manager_name"),
						rs.getInt("c.priority"), rs.getString("c.registed_date").split(":")[0], rs.getInt("co.column_id"),
						rs.getInt("co.card_order"));
				cardList.add(card);
			}
		} catch (Exception e) {
			System.out.println("error occured in Connection jdbc: " + e);
			e.printStackTrace();
		}

		return cardList;
	}

	// 최근 카드 id 조회
	public int getRecentCardId() {
		int cardId = -1;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// try-with-resource 방식
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement ps = conn.prepareStatement(QuerySelector.getRecentCardIdQuery);
				ResultSet rs = ps.executeQuery()) {
			if (rs.next()) {
				cardId = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("error occured in Connection jdbc: " + e);
			e.printStackTrace();
		}

		return cardId;
	}
}
