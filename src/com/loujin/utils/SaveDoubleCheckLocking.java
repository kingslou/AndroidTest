package com.loujin.utils;

/**
 * 基于volatile的双重检查单例模式
 * @author jin
 *
 */
public class SaveDoubleCheckLocking {
	
	private volatile static SaveDoubleCheckLocking instance;
	public static SaveDoubleCheckLocking getInStance(){
		if(instance==null){
			synchronized (SaveDoubleCheckLocking.class) {
				if(instance==null){
					instance = new SaveDoubleCheckLocking();
				}
			}
		}
		return instance;
	}
}
