package jhw.pjt2.nts.todo.global;

public class QuerySelector {
	//Card 테이블 관련 쿼리
	public static final String getAllCardQuery = "SELECT id, title, manager_name, priority, registed_date, column_type FROM card_tb";
	public static final String addCardQuery = "INSERT INTO card_tb (title, manager_name, priority) VALUES (?,?,?)";
	public static final String getCardByIdQuery = "SELECT id, title, manager_name, priority, registed_date FROM card_tb WHERE id = ?";
	public static final String getOrderedAllCardQuery = "SELECT c.id, c.title, c.manager_name, c.priority, c.registed_date, co.column_id, co.card_order FROM card_tb AS c LEFT JOIN card_order_tb AS co ON c.id = co.card_id ORDER BY co.column_id, co.card_order";
	public static final String getRecentCardIdQuery = "SELECT id FROM card_tb ORDER by id desc LIMIT 1";
	
	//Column 테이블 관련 쿼리
	public static final String getAllColumnQuery = "SELECT id, title FROM column_tb";
	
	//CardOrder 테이블 관련 쿼리
	public static final String addCardOrderQuery = "INSERT into card_order_tb (column_id, card_id, card_order) VALUES (?,?,?)";
	public static final String getColumnSizeByIdQuery = "SELECT count(*) AS column_size FROM card_order_tb WHERE column_id = ?";
	public static final String updateCardOrderQuery = "UPDATE card_order_tb SET column_id = ?, card_order = ? WHERE card_id = ?";
	public static final String reduceCardOrderQuery = "UPDATE card_order_tb SET card_order = card_order - 1 WHERE column_id = ? AND card_order > ?";
}
