package com.loujin.bean;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class NewsList extends Entity implements ListEntity<News> {

	public final static String PREF_READED_NEWS_LIST = "readed_news_list.pref";
	
	private int catalog;
	
	private int pageSize;
	
	private int newsCount;
	
	private List<News> list = new ArrayList<News>();
	/**
	 * @return the catalog
	 */
	public int getCatalog() {
		return catalog;
	}

	/**
	 * @param catalog the catalog to set
	 */
	public void setCatalog(int catalog) {
		this.catalog = catalog;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the newsCount
	 */
	public int getNewsCount() {
		return newsCount;
	}

	/**
	 * @param newsCount the newsCount to set
	 */
	public void setNewsCount(int newsCount) {
		this.newsCount = newsCount;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<News> list) {
		this.list = list;
	}

	@Override
	public List<News> getList() {
		// TODO Auto-generated method stub
		return null;
	}
}
