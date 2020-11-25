package chw.intern.nts.reservation.dao.sql;

public class PromotionDaoSqls {
	public static final String SELECT_ALL_WITH_IMG_URL = "SELECT p.id id,"
			+ " p.product_id product_id,"
			+ " fi.save_file_name product_image_url"
			+ " FROM promotion p"
			+ " LEFT JOIN product_image pi"
			+ " ON p.product_id = pi.product_id AND pi.type ='th'"
			+ " LEFT JOIN file_info fi"
			+ " ON pi.file_id = fi.id"
			+ " ORDER BY p.id";
}
