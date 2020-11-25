package chw.intern.nts.reservation.dto;

public class ReservationInfoPrice {
	private Integer reservationInfoPriceId;
	private Integer reservationInfoId;
	private Integer productPriceId;
	private Integer count;
	
	private Integer id;

	public Integer getReservationInfoPriceId() {
		return reservationInfoPriceId;
	}

	public void setReservationInfoPriceId(Integer reservationInfoPriceId) {
		this.reservationInfoPriceId = reservationInfoPriceId;
		this.id = reservationInfoPriceId;
	}

	public Integer getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(Integer reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public Integer getProductPriceId() {
		return productPriceId;
	}

	public void setProductPriceId(Integer productPriceId) {
		this.productPriceId = productPriceId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId() {
		this.id = this.reservationInfoPriceId;
	}

	@Override
	public String toString() {
		return "Price [id=" + id + ", reservationInfoPriceId=" + reservationInfoPriceId + ", reservationInfoId="
				+ reservationInfoId + ", productPriceId=" + productPriceId + ", count=" + count + "]";
	}
}
