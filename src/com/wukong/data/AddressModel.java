package com.wukong.data;

import java.io.Serializable;

public class AddressModel implements Serializable {
	private String id;// 地址id
	private String reciver;// 收件人
	private String tel;// 电话
	private String zipcode;// 邮编
	private String area;// 地区
	private String detail;// 详细地址
	private boolean isChecked = false;// 地址是否被选中

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReciver() {
		return reciver;
	}

	public void setReciver(String reciver) {
		this.reciver = reciver;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "AddressModel [id=" + id + ", reciver=" + reciver + ", tel="
				+ tel + ", zipcode=" + zipcode + ", area=" + area + ", detail="
				+ detail + "]";
	}

}
