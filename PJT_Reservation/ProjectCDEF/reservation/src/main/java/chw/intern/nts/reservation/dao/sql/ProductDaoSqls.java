package chw.intern.nts.reservation.dao.sql;

public class ProductDaoSqls {
	public static final String SELECT_ALL_COUNT = "SELECT COUNT(product.id) FROM product"
			+ " JOIN display_info"
			+ " ON product.id = display_info.product_id";
	public static final String SELECT_COUNT_BY_CATEGORY_ID = "SELECT COUNT(product.id) FROM product"
			+ " JOIN display_info"
			+ " ON product.id = display_info.product_id"
			+ " WHERE product.category_id = :categoryId";
	public static final String SELECT_BY_CATEGORY_ID_WITH_OFFSET = "SELECT di.id display_info_id,"
			+ " p.id product_id,"
			+ " p.description product_description,"
			+ " di.place_name place_name,"
			+ " p.content product_content,"
			+ " fi.save_file_name product_image_url"
			+ " FROM (SELECT * FROM product WHERE product.category_id = :categoryId) p"
			+ " LEFT JOIN display_info di"
			+ " ON p.id = di.product_id"
			+ " LEFT JOIN product_image pi"
			+ " ON p.id = pi.product_id AND pi.type = 'th'"
			+ " LEFT JOIN file_info fi"
			+ " ON pi.file_id = fi.id"
			+ " ORDER BY p.id"
			+ " LIMIT :limit"
			+ " OFFSET :start";
	
	public static final String SELECT_ALL_WITH_OFFSET = "SELECT di.id display_info_id,"
			+ " p.id product_id,"
			+ " p.description product_description,"
			+ " di.place_name place_name,"
			+ " p.content product_content,"
			+ " fi.save_file_name product_image_url"
			+ " FROM product p"
			+ " LEFT JOIN display_info di"
			+ " ON p.id = di.product_id"
			+ " LEFT JOIN product_image pi"
			+ " ON p.id = pi.product_id AND pi.type = 'th'"
			+ " LEFT JOIN file_info fi"
			+ " ON pi.file_id = fi.id"
			+ " ORDER BY p.id"
			+ " LIMIT :limit"
			+ " OFFSET :start";
	
	public static final String SELECT_PRODUCT_IMAGES_BY_ID = "SELECT fi.content_type,"
			+ " fi.create_date,"
			+ " fi.delete_flag,"
			+ " fi.id file_info_id,"
			+ " fi.file_name,"
			+ " fi.modify_date,"
			+ " pi.product_id,"
			+ " pi.id product_image_id,"
			+ " fi.save_file_name,"
			+ " pi.type"
			+ " FROM (SELECT * FROM product WHERE id = :productId) p"
			+ " JOIN product_image pi"
			+ " ON p.id = pi.product_id AND pi.type != 'th'"
			+ " JOIN file_info fi"
			+ " ON pi.file_id = fi.id"
			+ " ORDER BY p.id, fi.id";
	
	public static final String SELECT_PRODUCT_PRICES_BY_PRODUCT_ID = "SELECT pp.create_date,"
			+ " pp.discount_rate,"
			+ " pp.modify_date,"
			+ " pp.price,"
			+ " pp.price_type_name,"
			+ " pp.product_id,"
			+ " pp.id product_price_id"
			+ " FROM product_price pp"
			+ " WHERE pp.product_id = :productId";
	
	public static final String SELECT_DESCRPTION_BY_PRODUCT_ID = "SELECT description"
			+ " FROM product"
			+ " WHERE id = :productId";
}
