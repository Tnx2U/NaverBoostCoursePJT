package chw.intern.nts.reservation.dao.sql;

public class ReservationInfoDaoSqls {
	public static final String SELECT_ALL_BY_EMAIL = "SELECT ri.cancel_flag cancelYn,"
			+ " ri.create_date,"
			+ " ri.display_info_id,"
			+ " ri.modify_date,"
			+ " ri.product_id,"
			+ " ri.reservation_date, "
			+ " ri.reservation_email,"
			+ " ri.id reservation_info_id,"
			+ " ri.reservation_name,"
			+ " ri.reservation_tel,"
			+ " 	(SELECT SUM(rip.count * pp.price) FROM reservation_info_price rip"
			+ " 	 JOIN product_price pp"
			+ " 	 ON rip.product_price_id = pp.id"
			+ " 	 WHERE rip.reservation_info_id = ri.id"
			+ "		 GROUP BY rip.reservation_info_id) total_price"
			+ " FROM reservation_info ri"
			+ " WHERE ri.reservation_email = :reservationEmail"
			+ " ORDER BY ri.id";
	
	public static final String SELECT_RESERVATION_INFO_BY_ID = "SELECT id reservation_info_id,"
			+ " product_id,"
			+ " display_info_id,"
			+ " reservation_date,"
			+ " reservation_email,"
			+ " reservation_name,"
			+ " reservation_tel,"
			+ " cancel_flag cancel_yn,"
			+ " create_date, modify_date"
			+ " FROM reservation_info"
			+ " WHERE id = :reservationInfoId";
	
	public static final String SELECT_RESERVATION_INFO_PRICE_BY_RESERVATION_ID = "SELECT id reservation_info_price_id,"
			+ " product_price_id,"
			+ " reservation_info_id,"
			+ " count"
			+ " FROM reservation_info_price"
			+ " WHERE reservation_info_id = :reservationInfoPriceId";
}
