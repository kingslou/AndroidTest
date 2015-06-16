package com.loujin.cache;

import java.util.List;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.loujin.app.AppContext;
import com.loujin.bean.Yi18Entity;
import com.loujin.dao.Yi18;
import com.loujin.dao.Yi18Dao;

import de.greenrobot.dao.query.QueryBuilder;

public class ShouyeCach extends BaseCachUtils {
	
	private static Yi18Dao yidao;
	private volatile static ShouyeCach yiinstance;
	
	public static ShouyeCach getShouyeInstance(){
		
		if(yiinstance==null){
			synchronized (ShouyeCach.class) {
				if(yiinstance==null){
					yiinstance = new ShouyeCach();
					daosession = AppContext.getDaoSession();
					yidao = daosession.getYi18Dao();
				}
			}
		}
		return yiinstance;
	}

	@Override
	public void addCach(Object object) {
		// TODO Auto-generated method stub
		List<Yi18> Lists = JSON.parseArray(object.toString(), Yi18.class);
		for(Yi18 yi : Lists){
			yi.setId(yi.getId());
			yi.setName(yi.getName());
			yi.setCookclass(yi.getCookclass());
			yidao.insert(yi);
		}
	}

	@Override
	public void cleanCach(Object object) {
		// TODO Auto-generated method stub
		yidao.deleteAll();
	}
	
	/**
	 * 该分类是否已经有缓存
	 * @param catID
	 * @return
	 */
	public boolean hasCach(int catID){
		boolean result = false;
		if(yidao.loadAll().size()!=0){
			result = true;
		}
		return result;
	}
	
	public List<Yi18Entity> getCategoryList(int CategoryID){
		List<Yi18Entity> lll= CategoryCach.getCategoryCach().getCategoryList(CategoryID);
		return lll;
	}

	@Override
	public List<Yi18Entity> getCachList() {
		// TODO Auto-generated method stub
		List<Yi18> lll= yidao.loadAll();
		String ss = JSON.toJSONString(lll);
		return (List<Yi18Entity>) JSON.parseArray(ss, Yi18Entity.class);
	}
}
