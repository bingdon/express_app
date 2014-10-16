package com.wukong.bean;

import java.io.Serializable;

public class RoutBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2440599012363216308L;

	private String id;
	// 快递员id
	private Integer did;
	// 出发地点start
	private String start;
	// 目的地点end
	private String end;
	// 交通工具
	private String vehicle;
	// 车次
	private String trainnum;
	// 发车时间
	private String begintime;
	// 预计到达时间
	private String arrivetime;
	// 接货方式
	private String receiveway;
	// 收货方式
	private String getway;
	// 货物要求
	private String demand;
	// 发布时间
	private String createtime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getDid() {
		return did;
	}

	public void setDid(Integer did) {
		this.did = did;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	public String getTrainnum() {
		return trainnum;
	}

	public void setTrainnum(String trainnum) {
		this.trainnum = trainnum;
	}

	public String getBegintime() {
		return begintime;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	public String getArrivetime() {
		return arrivetime;
	}

	public void setArrivetime(String arrivetime) {
		this.arrivetime = arrivetime;
	}

	public String getReceiveway() {
		return receiveway;
	}

	public void setReceiveway(String receiveway) {
		this.receiveway = receiveway;
	}

	public String getGetway() {
		return getway;
	}

	public void setGetway(String getway) {
		this.getway = getway;
	}

	public String getDemand() {
		return demand;
	}

	public void setDemand(String demand) {
		this.demand = demand;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

}
