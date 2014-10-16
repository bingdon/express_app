package com.wukong.bean;

import java.io.Serializable;

public class GoodsTypeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5449812968055248030L;

	private String id;
	
	private String name;

	public String getId() {
		return ""+id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return ""+name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
