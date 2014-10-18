package com.wukong.bean;

import java.io.Serializable;

public class OrderBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2352050860725815464L;

	private String id;

	private String uid;

	private String category;

	private String gname;

	private String weight;

	private String price;

	private String remarks;

	private String cost;
	// 同城发货(传tag=e1)，异地发货(传tag=e2)

	private String tag;

	// 发货人
	private String shipper;
	// 发货人电话号码
	private String s_tel;
	// 发货地址
	private String s_address;
	// 收货人
	private String receiver;
	// 收货人电话号码
	private String r_tel;
	// 收货地址
	private String r_address;
	// 收货方式
	private String way;
	// 发货日期
	private String starttime;
	// 经度
	private String lon;
	// 纬度
	private String lat;

	private String headimage;

	private String createtime;
	
	private int status;
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getCreatetime() {
		return "" + createtime;
	}

	public String getHeadimage() {
		return headimage;
	}

	public void setHeadimage(String headimage) {
		this.headimage = headimage;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
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

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
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

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
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

	public String getWay() {
		return way;
	}

	public void setWay(String way) {
		this.way = way;
	}

	public String getStarttime() {
		return ""+starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		OrderBean orderBean = (OrderBean) o;
		boolean a = this.getCreatetime().equals(orderBean.getCreatetime());
		boolean b = this.getShipper().equals(orderBean.getShipper());
		boolean c = this.getId().equals(orderBean.getId());
		if (a && b && c) {
			return true;
		}
		return false;
	}

}
