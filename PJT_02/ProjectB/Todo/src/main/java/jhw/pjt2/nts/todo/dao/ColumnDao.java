package jhw.pjt2.nts.todo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jhw.pjt2.nts.todo.dto.CardOrder;
import jhw.pjt2.nts.todo.dto.Column;
import jhw.pjt2.nts.todo.global.QuerySelector;

public class ColumnDao {
	// sql연결정보 추후 별도의 파일로 분리할 것(보안 이슈)
	private static final String DB_URL = "jdbc:mysql://10.113.116.52:13306/user05";
	private static final String DB_USER = "user05";
	private static final String DB_PASSWORD = "user05";

	// 전체 컬럼 조회
	public List<Column> getAllColumn() {

		List<Column> columnList = new ArrayList<>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// try-with-resource 방식
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement ps = conn.prepareStatement(QuerySelector.getAllColumnQuery)) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Column column = new Column(rs.getInt(1), rs.getString(2));
					columnList.add(column);
				}
			} catch (Exception e) {
				System.out.println("error occured in getAllColumn ResultSet : " + e);
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("error occured in getAllColumn Connection jdbc: " + e);
			e.printStackTrace();
		}

		return columnList;
	}
}
