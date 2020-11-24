package chw.intern.nts.reservation.dto;

import java.util.List;

public class ReservationParam {
	private Integer displayInfoId;
	private Integer productId;
	private String reservationEmail;
	private String reservationName;
	private String reservationTelephone;
	private String reservationYearMonthDay;
	private List<PriceParam> prices;

	private Integer reservationInfoId;
	private boolean cancelYn;
	private String reservationDate;
	private String createDate;
	private String modifyDate;

	public Integer getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(Integer displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getReservationEmail() {
		return reservationEmail;
	}

	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

	public String getReservationTelephone() {
		return reservationTelephone;
	}

	public void setReservationTelephone(String reservationTelephone) {
		this.reservationTelephone = reservationTelephone;
	}

	public String getReservationYearMonthDay() {
		return reservationYearMonthDay;
	}

	public void setReservationYearMonthDay(String reservationYearMonthDay) {
		this.reservationYearMonthDay = reservationYearMonthDay;
	}

	public List<PriceParam> getPrices() {
		return prices;
	}

	public void setPrices(List<PriceParam> prices) {
		this.prices = prices;
	}

	public Integer getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(Integer reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public boolean isCancelYn() {
		return cancelYn;
	}

	public void setCancelYn(boolean cancelYn) {
		this.cancelYn = cancelYn;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "ReservationParam [displayInfoId=" + displayInfoId + ", productId=" + productId + ", reservationEmail="
				+ reservationEmail + ", reservationName=" + reservationName + ", reservationTelephone="
				+ reservationTelephone + ", reservationYearMonthDay=" + reservationYearMonthDay + ", prices=" + prices
				+ ", reservationInfoId=" + reservationInfoId + ", cancelYn=" + cancelYn + ", reservationDate="
				+ reservationDate + ", createDate=" + createDate + ", modifyDate=" + modifyDate + "]";
	}
}
