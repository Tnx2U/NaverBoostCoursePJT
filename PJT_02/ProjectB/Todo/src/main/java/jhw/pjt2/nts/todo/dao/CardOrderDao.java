package jhw.pjt2.nts.todo.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jhw.pjt2.nts.todo.dto.Card;
import jhw.pjt2.nts.todo.dto.CardOrder;
import jhw.pjt2.nts.todo.global.QuerySelector;

public class CardOrderDao {
	// sql연결정보 추후 별도의 파일로 분리할 것(보안 이슈)
	private static final String DB_URL = "jdbc:mysql://10.113.116.52:13306/user05";
	private static final String DB_USER = "user05";
	private static final String DB_PASSWORD = "user05";
	private static final int MIN_ID_VALUE = 1;
	private static final int MIN_ORDER_VALUE = 1;
	

	// 새로운 카드순서정보 추가
	public int addCardOrder(CardOrder inputCardOrder) throws SQLException, IOException {
		int insertCount = 0;

		if(inputCardOrder == null) {
			throw new IOException();
		}
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("com.mysql.jdbc.Driver 객체 선언에 문제가 발생했습니다.");
			e.printStackTrace();
		}

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement ps = conn.prepareStatement(QuerySelector.addCardOrderQuery)) {
			ps.setInt(1, inputCardOrder.getColumnId());
			ps.setInt(2, inputCardOrder.getCardId());
			ps.setInt(3, inputCardOrder.getCardOrder());

			insertCount = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQLconnection error occured in : addCardOrder()");
			System.out.println("params : " + inputCardOrder.toString());
			throw e;
		}

		return insertCount;
	}

	// 특정 컬럼 길이 조회
	public int getColumnSizeById(int columnId) throws Exception {
		int columnSize = -1;
		Card card = null;

		if(columnId < MIN_ID_VALUE) {
			throw new IOException();
		}
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("com.mysql.jdbc.Driver 객체 선언에 문제가 발생했습니다.");
			e.printStackTrace();
		}

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement ps = conn.prepareStatement(QuerySelector.getColumnSizeByIdQuery);) {
			ps.setInt(1, columnId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					columnSize = rs.getInt(1);
				}
			} catch (SQLException e) {
				System.out.println("resultSet error occured in : getColumnSizeById()");
				System.out.println("params : " + columnId);
				throw e;
			}
		} catch (Exception e) {
			System.out.println("SQLConnection error occured in : getColumnSizeById()");
			System.out.println("params : " + columnId);
			throw e;
		}

		return columnSize;
	}

	// 카드오더의 위치 변경
	public int updateClickedCardOrder(int cardId, int dstColumnId, int dstOrder) throws SQLException, IOException {
		int updateCount = 0;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("com.mysql.jdbc.Driver 객체 선언에 문제가 발생했습니다.");
			e.printStackTrace();
		}
		
		if(cardId < MIN_ID_VALUE || dstColumnId < MIN_ID_VALUE+1 || dstOrder < MIN_ORDER_VALUE) {
			throw new IOException();
		}

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement ps = conn.prepareStatement(QuerySelector.updateCardOrderQuery);) {
			ps.setInt(1, dstColumnId);
			ps.setInt(2, dstOrder);
			ps.setInt(3, cardId);

			updateCount = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQLConnection error occured in : updateClickedCardOrder()");
			System.out.println("params : " + cardId + " " + dstColumnId + " " + dstOrder);
			throw e;
		}

		return updateCount;
	}

	// 이동한 카드보다 순서가 높은 카드들 1단계씩 낮추기
	public int reduceCardOrder(int columnId, int cardOrder) throws SQLException, IOException {
		int updateCount = 0;

		if(columnId < MIN_ID_VALUE || cardOrder < MIN_ORDER_VALUE) {
			throw new IOException();
		}
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("com.mysql.jdbc.Driver 객체 선언에 문제가 발생했습니다.");
			e.printStackTrace();
		}

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement ps = conn.prepareStatement(QuerySelector.reduceCardOrderQuery);) {
			ps.setInt(1, columnId);
			ps.setInt(2, cardOrder);

			updateCount = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQLConnection error occured in : reduceCardOrder()");
			System.out.println("params : " + columnId + " " + cardOrder);
			throw e;
		}

		return updateCount;
	}
}
