package com.loujin.utils;

public interface CallBack<T> {
	
	public void invoke();

	public void invoke(T arg);

	public void invoke(Object... args);

}
