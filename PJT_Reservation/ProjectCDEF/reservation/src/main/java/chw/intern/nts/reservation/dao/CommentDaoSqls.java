package chw.intern.nts.reservation.dao;

public class CommentDaoSqls {
	public static final String SELECT_ALL_BY_DISPLAY_INFO_ID = "SELECT c.comment,"
			+ " c.id comment_id,"
			+ " c.create_date,"
			+ " c.modify_date,"
			+ " c.product_id,"
			+ " ri.reservation_date,"
			+ " ri.reservation_email,"
			+ " ri.id reservation_info_id,"
			+ " ri.reservation_name,"
			+ " ri.reservation_tel reservation_telephone,"
			+ " c.score"
			+ " FROM reservation_user_comment c"
			+ " JOIN (SELECT * FROM reservation_info WHERE display_info_id = :displayInfoId) ri"
			+ " ON c.reservation_info_id = ri.id "
			+ " ORDER BY c.product_id, ri.id";
	
	public static final String SELECT_ALL_BY_COMMENT_ID = "";
}
