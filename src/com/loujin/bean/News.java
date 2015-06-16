package com.loujin.bean;

import java.util.Date;

public class News extends Entity {

	/**
	 * 健康知识实体
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String title;
	
	private String img;
	
	private int count;
	
	private int fcount;
	
	private int rcount;
	
	private String author;
	
	private String loreclass;
	
	private String className;
	
	private String md;
	
	private Date   time;
	
	
	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the img
	 */
	public String getImg() {
		return img;
	}

	/**
	 * @param img the img to set
	 */
	public void setImg(String img) {
		this.img = img;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the fcount
	 */
	public int getFcount() {
		return fcount;
	}

	/**
	 * @param fcount the fcount to set
	 */
	public void setFcount(int fcount) {
		this.fcount = fcount;
	}

	/**
	 * @return the rcount
	 */
	public int getRcount() {
		return rcount;
	}

	/**
	 * @param rcount the rcount to set
	 */
	public void setRcount(int rcount) {
		this.rcount = rcount;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the loreclass
	 */
	public String getLoreclass() {
		return loreclass;
	}

	/**
	 * @param loreclass the loreclass to set
	 */
	public void setLoreclass(String loreclass) {
		this.loreclass = loreclass;
	}

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * @return the md
	 */
	public String getMd() {
		return md;
	}

	/**
	 * @param md the md to set
	 */
	public void setMd(String md) {
		this.md = md;
	}

	/**
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "News [title=" + title + ", img=" + img + ", count=" + count
				+ ", fcount=" + fcount + ", rcount=" + rcount + ", author="
				+ author + ", loreclass=" + loreclass + ", className="
				+ className + ", md=" + md + ", time=" + time + "]";
	}
}
