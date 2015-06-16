package com.loujin.bean;

public class Yi18Entity extends Entity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * id : 1 name : 美容养颜 cookclass : 0
	 */
	private Integer id;
	private String name;
	private int cookclass;

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCookclass(int cookclass) {
		this.cookclass = cookclass;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getCookclass() {
		return cookclass;
	}
}