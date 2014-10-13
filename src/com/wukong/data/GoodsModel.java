package com.wukong.data;

import java.io.Serializable;

public class GoodsModel implements Serializable {
	private String uid;// ��Ʒid
	private String shipper;// ������
	private String s_tel;// �����˵绰
	private String s_address;// ������ַ
	private String reciver;// �ջ���
	private String r_tel;// �ջ��˵绰
	private String r_address;// �ջ���ַ
	private String category;// ���
	private String gname;// ����
	private String weight;// ����
	private String price;// ��ֵ
	private String remarks;// ��ע��Ϣ

	// private String way;//
	// private String cost;//
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getShipper() {
		return shipper;
	}

	public void setShipper(String shipper) {
		this.shipper = shipper;
	}

	public String getS_tel() {
		return s_tel;
	}

	public void setS_tel(String s_tel) {
		this.s_tel = s_tel;
	}

	public String getS_address() {
		return s_address;
	}

	public void setS_address(String s_address) {
		this.s_address = s_address;
	}

	public String getReciver() {
		return reciver;
	}

	public void setReciver(String reciver) {
		this.reciver = reciver;
	}

	public String getR_tel() {
		return r_tel;
	}

	public void setR_tel(String r_tel) {
		this.r_tel = r_tel;
	}

	public String getR_address() {
		return r_address;
	}

	public void setR_address(String r_address) {
		this.r_address = r_address;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "GoodsModel [uid=" + uid + ", shipper=" + shipper + ", s_tel="
				+ s_tel + ", s_address=" + s_address + ", reciver=" + reciver
				+ ", r_tel=" + r_tel + ", r_address=" + r_address
				+ ", category=" + category + ", gname=" + gname + ", weight="
				+ weight + ", price=" + price + ", remarks=" + remarks + "]";
	}

}
