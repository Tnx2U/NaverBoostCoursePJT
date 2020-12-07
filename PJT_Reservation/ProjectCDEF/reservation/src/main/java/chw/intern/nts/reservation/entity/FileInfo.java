package chw.intern.nts.reservation.entity;

import java.sql.Date;

public class FileInfo {
	private Integer id;
	private String fileName;
	private String saveFileName;
	private String contentType;
	private Integer deleteFlag;
	private Date createDate;
	private Date modifyDate;

	public FileInfo(String fileName, String saveFileName, String contentType) {
		this.fileName = fileName;
		this.saveFileName = saveFileName;
		this.contentType = contentType;
		this.deleteFlag = 0;
		this.createDate = new Date(System.currentTimeMillis());;
		this.modifyDate = new Date(System.currentTimeMillis());;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
}
