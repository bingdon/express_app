package com.wukong.bean;

import java.io.Serializable;

public class RoutBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2440599012363216308L;

	private String id;
	// ���Աid
	private Integer did;
	// �����ص�start
	private String start;
	// Ŀ�ĵص�end
	private String end;
	// ��ͨ����
	private String vehicle;
	// ����
	private String trainnum;
	// ����ʱ��
	private String begintime;
	// Ԥ�Ƶ���ʱ��
	private String arrivetime;
	// �ӻ���ʽ
	private String receiveway;
	// �ջ���ʽ
	private String getway;
	// ����Ҫ��
	private String demand;
	// ����ʱ��
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
