package chw.intern.nts.reservation.dao;

public class DisplayInfoDaoSqls {
	public static final String SELECT_PRODUCT_ID_BY_ID = "SELECT product_id"
			+ " FROM display_info"
			+ " WHERE id = :displayInfoId ";
	public static final String SELECT_BY_ID = "SELECT p.category_id,"
			+ " c.name category_name,"
			+ " di.create_date,"
			+ " di.id display_info_id,"
			+ " di.email,"
			+ " di.homepage,"
			+ " di.modify_date,"
			+ " di.opening_hours,"
			+ " di.place_lot,"
			+ " di.place_name,"
			+ " di.place_street,"
			+ " p.content product_content,"
			+ " p.description product_description,"
			+ " p.event product_event,"
			+ " di.product_id,"
			+ " di.tel telephone"
			+ " FROM (SELECT * FROM display_info WHERE id = :displayInfoId ) di"
			+ " JOIN product p"
			+ " ON di.product_id = p.id"
			+ " JOIN category c"
			+ " ON p.category_id = c.id";
}
