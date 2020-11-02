package jhw.pjt2.nts.todo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jhw.pjt2.nts.todo.dto.Card;

public class CardDao {
	// sql연결정보 추후 별도의 파일로 분리할 것(보안 이슈)
	private static final String DB_URL = "jdbc:mysql://10.113.116.52:13306/user05";
	private static final String DB_USER = "user05";
	private static final String DB_PASSWORD = "user05";

	// 새로운 카드 추가
	public int addCard(Card inputCard) {
		int insertcount = 0;

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			String addCardQuery = "insert into card_tb (title, manager_name, priority) values (?,?,?)";
			ps = conn.prepareStatement(addCardQuery);

			ps.setString(1, inputCard.getTitle());
			ps.setString(2, inputCard.getManagerName());
			ps.setInt(3, inputCard.getPriority());

			insertcount = ps.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("error occured in opening SQLconnection : " + e);
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println("error occured in closing SQLconnection : " + e);
				e.printStackTrace();
			}
		}

		return insertcount;
	}

	// 번호로 특정 카드 조회
	public Card getCardById(Integer cardId) {
		Card card = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			String getCardByIdQuery = "SELECT id, title, manager_name, priority, registed_date FROM card_tb WHERE id = ?";
			ps = conn.prepareStatement(getCardByIdQuery);
			ps.setInt(1, cardId);
			rs = ps.executeQuery();

			if (rs.next()) {
				card = new Card(rs.getInt("id"), rs.getString("title"), rs.getString("manager_name"),
						rs.getInt("priority"), rs.getString("registed_date"));
			}
		} catch (Exception e) {
			System.out.println("error occured in opening SQLconnection : " + e);
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println("error occured in closing SQLconnection : " + e);
				e.printStackTrace();
			}
		}

		return card;
	}

	// 번호로 특정 카드 삭제
	public int removeCardById(Integer cardId) {
		int removedCardId = 0;
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			String removeCardByIdQuery = "DELETE FROM card_tb WHERE id = ?";
			ps = conn.prepareStatement(removeCardByIdQuery);
			ps.setInt(1, cardId);

			removedCardId = ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("error occured in opening SQLconnection : " + e);
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println("error occured in closing SQLconnection : " + e);
				e.printStackTrace();
			}
		}

		return removedCardId;
	}

	// 전체 카드 조회
	public List<Card> getAllCard() {
		List<Card> cardList = new ArrayList<>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String getAllCardQuery = "SELECT id, title, manager_name, priority, registed_date, column_type FROM card_tb";

		// try-with-resource 방식
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement ps = conn.prepareStatement(getAllCardQuery)) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Card card = new Card(rs.getInt("id"), rs.getString("title"), rs.getString("manager_name"),
							rs.getInt("priority"), rs.getString("registed_date"));
					cardList.add(card);
				}
			} catch (Exception e) {
				System.out.println("error occured in ResultSet : " + e);
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("error occured in Connection jdbc: " + e);
			e.printStackTrace();
		}

		return cardList;
	}
	
	// 정렬된 전체 카드 조회
	public List<Card> getOrderedAllCard() {
		List<Card> cardList = new ArrayList<>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String getOrderedAllCardQuery = "SELECT c.id, c.title, c.manager_name, c.priority, c.registed_date, co.column_id, co.card_order FROM card_tb AS c LEFT JOIN card_order_tb AS co ON c.id = co.card_id ORDER BY co.column_id, co.card_order";

		// try-with-resource 방식
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement ps = conn.prepareStatement(getOrderedAllCardQuery)) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Card card = new Card(rs.getInt("c.id"), rs.getString("c.title"), rs.getString("c.manager_name"),
							rs.getInt("c.priority"), rs.getString("c.registed_date"), rs.getInt("co.column_id"), rs.getInt("co.card_order"));
					cardList.add(card);
				}
			} catch (Exception e) {
				System.out.println("error occured in ResultSet : " + e);
				e.printStackTrace();
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

		String getRecentCardIdQuery = "SELECT id FROM card_tb ORDER by id desc LIMIT 1";

		// try-with-resource 방식
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement ps = conn.prepareStatement(getRecentCardIdQuery)) {
			try (ResultSet rs = ps.executeQuery()) {
				if(rs.next()) {
					cardId = rs.getInt(1);
				}
			} catch (Exception e) {
				System.out.println("error occured in ResultSet : " + e);
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("error occured in Connection jdbc: " + e);
			e.printStackTrace();
		}

		return cardId;
	}
}
