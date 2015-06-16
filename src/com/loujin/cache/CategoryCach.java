package com.loujin.cache;

import java.util.List;
import com.alibaba.fastjson.JSON;
import com.loujin.app.AppContext;
import com.loujin.bean.Yi18Entity;
import com.loujin.dao.Yi18_Z;
import com.loujin.dao.Yi18_ZDao;
import com.loujin.dao.Yi18_ZDao.Properties;
import de.greenrobot.dao.query.Query;

public class CategoryCach extends BaseCachUtils {

	private volatile static CategoryCach CategoryinStance;
	private static Yi18_ZDao yi18_zdao;

	public static CategoryCach getCategoryCach() {
		if (CategoryinStance == null) {
			synchronized (CategoryCach.class) {
				if (CategoryinStance == null) {
					CategoryinStance = new CategoryCach();
					daosession = AppContext.getDaoSession();
					yi18_zdao = daosession.getYi18_ZDao();
				}
			}
		}
		return CategoryinStance;
	}

	@Override
	public void addCach(Object object) {
		// TODO Auto-generated method stub
		List<Yi18_Z> Lists = JSON.parseArray(object.toString(), Yi18_Z.class);
		for (Yi18_Z yi : Lists) {
			yi.setId(yi.getId());
			yi.setName(yi.getName());
			yi.setCookclass(yi.getCookclass());
			yi18_zdao.insert(yi);
		}
	}

	@Override
	public void cleanCach(Object object) {
		// TODO Auto-generated method stub
		yi18_zdao.deleteAll();
	}

	public List<Yi18Entity> getCategoryList(int CategoryID) {
		// TODO Auto-generated method stub
		Query query = yi18_zdao.queryBuilder()
				.where(Properties.Cookclass.eq(CategoryID +1)).build();
		String ss = JSON.toJSONString(query.list());
		return (List<Yi18Entity>) JSON.parseArray(ss, Yi18Entity.class);
	}

	@Override
	public List<Yi18Entity> getCachList() {
		// TODO Auto-generated method stub
		List<Yi18_Z> lll = yi18_zdao.loadAll();
		String ss = JSON.toJSONString(lll);
		return (List<Yi18Entity>) JSON.parseArray(ss, Yi18Entity.class);
	}
}
