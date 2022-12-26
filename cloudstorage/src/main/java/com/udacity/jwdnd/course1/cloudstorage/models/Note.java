package com.udacity.jwdnd.course1.cloudstorage.models;

public class Note {
	private Integer noteid;
	private String  notetitle;
	private String notedescription;
	private Integer userid;
	
	public Note(Integer noteId, String noteTitle, String noteDescription, Integer userid) {
		this.noteid = noteId;
		this.notetitle = noteTitle;
		this.notedescription = noteDescription;
		this.userid = userid;
	}
	public Integer getNoteid() {
		return noteid;
	}
	public void setNoteid(Integer noteId) {
		this.noteid = noteId;
	}
	public String getNotetitle() {
		return notetitle;
	}
	public void setNotetitle(String noteTitle) {
		this.notetitle = noteTitle;
	}
	public String getNotedescription() {
		return notedescription;
	}
	public void setNotedescription(String noteDescription) {
		this.notedescription = noteDescription;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	
	

}
