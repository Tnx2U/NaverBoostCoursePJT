package chw.intern.nts.reservation.dao.sql;

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
	
	public static final String SELECT_DISPLAY_INFO_IMAGE_BY_DISPLAY_INFO_ID = "SELECT fi.content_type,"
			+ " fi.create_date,"
			+ " fi.delete_flag,"
			+ " dii.display_info_id,"
			+ " dii.id display_info_image_id,"
			+ " fi.id file_id,"
			+ " fi.file_name,"
			+ " fi.modify_date,"
			+ " fi.save_file_name"
			+ " FROM (SELECT * FROM display_info_image WHERE display_info_id = :displayInfoId) dii"
			+ " JOIN file_info fi"
			+ " ON fi.id = dii.file_id";
}
