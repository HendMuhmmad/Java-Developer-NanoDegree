package com.udacity.jwdnd.course1.cloudstorage.models;

public class File {
	private Integer fileid;
	private String filename;
	private String contenttype;
	private String filesize;
	private Integer userid;
	private byte[] file;
	
	public File(Integer fileId, String filename, String contentType, String fileSize, Integer userId,
			byte[] file) {
		this.fileid = fileId;
		this.filename = filename;
		this.contenttype = contentType;
		this.filesize = fileSize;
		this.userid = userId;
		this.file = file;
	}
	public Integer getFileid() {
		return fileid;
	}
	public void setFileid(Integer fileId) {
		this.fileid = fileId;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getContenttype() {
		return contenttype;
	}
	public void setContenttype(String contentType) {
		this.contenttype = contentType;
	}
	public String getFilesize() {
		return filesize;
	}
	public void setFilesize(String fileSize) {
		this.filesize = fileSize;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userId) {
		this.userid = userId;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	
}
