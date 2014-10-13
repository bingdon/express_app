package com.wukong.bean;

import java.io.Serializable;

public class PersonInfoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7037289473308764201L;


	private String id;
	
	private String headimage;
	
	private String createtime;
	
	private String username;
	
	private String degree;
	
	private String gendar;
	
	private String picFileFileName;
	
	private String tel;
	
	private String age;
	
	private String relation;
	
	private String imageFile;
	
	private String picFileContentType;
	
	private String password;

	public String getId() {
		return ""+id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHeadimage() {
		return ""+headimage;
	}

	public void setHeadimage(String headimage) {
		this.headimage = headimage;
	}

	public String getCreatetime() {
		return ""+createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getUsername() {
		return ""+username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDegree() {
		return ""+degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getGendar() {
		return ""+gendar;
	}

	public void setGendar(String gendar) {
		this.gendar = gendar;
	}

	public String getPicFileFileName() {
		return ""+picFileFileName;
	}

	public void setPicFileFileName(String picFileFileName) {
		this.picFileFileName = picFileFileName;
	}

	public String getTel() {
		return ""+tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAge() {
		return ""+age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getRelation() {
		return ""+relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getImageFile() {
		return imageFile;
	}

	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}

	public String getPicFileContentType() {
		return picFileContentType;
	}

	public void setPicFileContentType(String picFileContentType) {
		this.picFileContentType = picFileContentType;
	}

	public String getPassword() {
		return ""+password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
