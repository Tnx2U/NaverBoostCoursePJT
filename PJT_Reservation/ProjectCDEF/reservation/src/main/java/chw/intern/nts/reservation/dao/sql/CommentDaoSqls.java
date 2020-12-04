package chw.intern.nts.reservation.dao.sql;

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
	
	public static final String SELECT_COMMENT_IMAGES_ALL_BY_COMMENT_ID = "SELECT fi.content_type,"
			+ " fi.create_date,"
			+ " fi.delete_flag,"
			+ " fi.id file_id,"
			+ " fi.file_name,"
			+ " ruci.id image_id,"
			+ " fi.modify_date,"
			+ " ruci.reservation_info_id,"
			+ " ruci.reservation_user_comment_id,"
			+ " fi.save_file_name"
			+ " FROM (SELECT * FROM reservation_user_comment_image WHERE reservation_user_comment_id = :commentId) ruci"
			+ " JOIN file_info fi"
			+ " ON ruci.file_id = fi.id"
			+ " ORDER BY ruci.id";
	
	public static final String SELECT_BY_ID = "SELECT c.comment,"
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
			+ " FROM (SELECT * FROM reservation_user_comment WHERE id = :commentId) c"
			+ " JOIN reservation_info ri"
			+ " ON c.reservation_info_id = ri.id ";
}
