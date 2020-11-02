package jhw.pjt2.nts.todo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jhw.pjt2.nts.todo.dto.Card;
import jhw.pjt2.nts.todo.dto.CardOrder;

public class CardOrderDao {
	// sql연결정보 추후 별도의 파일로 분리할 것(보안 이슈)
	private static final String DB_URL = "jdbc:mysql://10.113.116.52:13306/user05";
	private static final String DB_USER = "user05";
	private static final String DB_PASSWORD = "user05";

	// 새로운 카드순서정보 추가
	public int addCardOrder(CardOrder inputCardOrder) {
		int insertCount = 0;

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			String addCardOrderQuery = "insert into card_order_tb (column_id, card_id, card_order) values (?,?,?)";
			ps = conn.prepareStatement(addCardOrderQuery);

			ps.setInt(1, inputCardOrder.getColumnId());
			ps.setInt(2, inputCardOrder.getCardId());
			ps.setInt(3, inputCardOrder.getCardOrder());

			insertCount = ps.executeUpdate();
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

		return insertCount;
	}

	
	// 전체 카드오더 조회
	public List<CardOrder> getAllCardOrder() {
		List<CardOrder> cardOrderList = new ArrayList<>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String getAllCardOrderQuery = "SELECT id, column_id, card_id, card_order FROM card_order_tb ORDER BY column_id, card_order";

		// try-with-resource 방식
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement ps = conn.prepareStatement(getAllCardOrderQuery)) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					CardOrder cardOrder = new CardOrder(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
					cardOrderList.add(cardOrder);
				}
			} catch (Exception e) {
				System.out.println("error occured in getAllCard ResultSet : " + e);
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("error occured in getAllCard Connection jdbc: " + e);
			e.printStackTrace();
		}

		return cardOrderList;
	}
	
	
	// 특정 컬럼 길이 조회
	public int getColumnSizeById(int columnId) {
		int columnSize = -1;
		String getColumnSizeByIdQuery = "SELECT count(*) as column_size FROM card_order_tb WHERE column_id = ?";

		Card card = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			ps = conn.prepareStatement(getColumnSizeByIdQuery);
			ps.setInt(1, columnId);
			rs = ps.executeQuery();

			if (rs.next()) {
				columnSize = rs.getInt(1);
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

		return columnSize;
	}


	//카드오더의 위치 변경
	public int updateClickedCardOrder(int cardId, int dstColumnId, int dstOrder) {
		int updateCount = 0;
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			String updateCardOrderQuery = "UPDATE card_order_tb SET column_id = ?, card_order = ? WHERE card_id = ?";
			ps = conn.prepareStatement(updateCardOrderQuery);
			ps.setInt(1, dstColumnId);
			ps.setInt(2, dstOrder);
			ps.setInt(3, cardId);

			updateCount = ps.executeUpdate();
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

		return updateCount;
	}

	//이동한 카드보다 순서가 높은 카드들 1단계씩 낮추기
	public int reduceCardOrder(int columnId, int cardOrder) {
		int updateCount = 0;
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			String reduceCardOrderQuery = "UPDATE card_order_tb SET card_order = card_order - 1 WHERE column_id = ? and card_order > ?";
			ps = conn.prepareStatement(reduceCardOrderQuery);
			ps.setInt(1, columnId);
			ps.setInt(2, cardOrder);

			updateCount = ps.executeUpdate();
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

		return updateCount;
	}
}
