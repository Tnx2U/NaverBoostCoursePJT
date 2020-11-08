package chw.intern.nts.reservation.dao;

public class CategoryDaoSqls {
	public static final String SELECT_ALL = "SELECT id, name FROM category ORDER BY id";
	public static final String SELECT_BY_ID = "SELECT id, name FROM category WHERE id = :id";
}
