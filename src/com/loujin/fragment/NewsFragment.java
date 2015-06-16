package com.loujin.fragment;
import java.io.Serializable;
import java.util.List;

import android.view.View;
import android.widget.AdapterView;

import com.loujin.api.MyApi;
import com.loujin.base.BaseListFragment;
import com.loujin.base.ListBaseAdapter;
import com.loujin.bean.News;
import com.loujin.bean.NewsList;

public class NewsFragment extends BaseListFragment<News> {

	protected static final String TAG = NewsFragment.class.getSimpleName();
	private static final String CACHE_KEY_PREFIX = "newslist_";

	@Override
	protected ListBaseAdapter<News> getListAdapter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getCacheKeyPrefix() {
		return CACHE_KEY_PREFIX + mCatalog;
	}

	@Override
	protected NewsList readList(Serializable seri) {
		return ((NewsList) seri);
	}

	@Override
	protected void sendRequestData() {
		//OSChinaApi.getNewsList(mCatalog, mCurrentPage, mHandler);
		MyApi.getCookList(0, mHandler);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		News news = mAdapter.getItem(position);
		if (news != null) {
			// 放入已读列表
			saveToReadedList(view, NewsList.PREF_READED_NEWS_LIST, news.getId()
					+ "");
		}
	}

	@Override
	protected void executeOnLoadDataSuccess(List<News> data) {
		
		super.executeOnLoadDataSuccess(data);
	}

	@Override
	protected long getAutoRefreshTime() {
	
		return super.getAutoRefreshTime();
	}

}
