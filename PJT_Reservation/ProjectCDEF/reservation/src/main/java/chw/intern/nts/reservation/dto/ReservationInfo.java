package chw.intern.nts.reservation.dto;

import java.sql.Date;

public class ReservationInfo {
	private int cancelFlag;
	private boolean cancelYn;
	private DisplayInfo displayInfo;
	private Integer displayInfoId;
	private Integer productId;
	private Date reservationDate;
	private Date createDate;
	private Date modifyDate;
	private String reservationEmail;
	private Integer reservationInfoId;
	private String reservationName;
	private String reservationTel;
	private Integer totalPrice;

	public static ReservationInfo from(ReservationParam param) {
		ReservationInfo reservationInfo = new ReservationInfo();
		reservationInfo.setCancelFlag(param.isCancelYn() ? 1 : 0);
		reservationInfo.setProductId(param.getProductId());
		reservationInfo.setDisplayInfoId(param.getDisplayInfoId());
		reservationInfo.setReservationName(param.getReservationName());
		reservationInfo.setReservationTel(param.getReservationTelephone());
		reservationInfo.setReservationEmail(param.getReservationEmail());
		reservationInfo.setReservationDate(Date.valueOf(param.getReservationYearMonthDay().replace(".", "-")));
		reservationInfo.setCreateDate(new Date(System.currentTimeMillis()));
		reservationInfo.setModifyDate(new Date(System.currentTimeMillis()));
		
		return reservationInfo;
	}

	public int getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(int cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	public boolean isCancelYn() {
		return cancelYn;
	}

	public void setCancelYn(boolean cancelYn) {
		this.cancelYn = cancelYn;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public DisplayInfo getDisplayInfo() {
		return displayInfo;
	}

	public void setDisplayInfo(DisplayInfo displayInfo) {
		this.displayInfo = displayInfo;
	}

	public Integer getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(Integer displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getReservationEmail() {
		return reservationEmail;
	}

	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}

	public Integer getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(Integer reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}

	public String getReservationTel() {
		return reservationTel;
	}

	public void setReservationTel(String reservationTel) {
		this.reservationTel = reservationTel;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
}
