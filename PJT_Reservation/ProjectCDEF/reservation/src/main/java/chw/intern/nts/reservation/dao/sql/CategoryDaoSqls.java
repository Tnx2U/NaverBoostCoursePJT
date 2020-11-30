package chw.intern.nts.reservation.dao.sql;

public class CategoryDaoSqls {
	public static final String SELECT_BY_ID = "SELECT id, name FROM category WHERE id = :id";
	public static final String SELECT_ALL_WITH_COUNT = "SELECT COUNT(product.id) count, category.id id, category.name name"
			+ " FROM category"
			+ " LEFT JOIN product"
			+ " ON product.category_id = category.id"
			+ " GROUP BY category.id";
}
