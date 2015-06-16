package com.loujin.cache;
import java.util.List;
import com.loujin.dao.DaoSession;

public abstract class BaseCachUtils<T> {
	
	protected static DaoSession daosession;
	
	public abstract  void addCach(T object);
	
	public abstract  void cleanCach(T object);
	
	public abstract List<T> getCachList();

}
