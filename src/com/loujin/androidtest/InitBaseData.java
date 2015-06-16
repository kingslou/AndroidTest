package com.loujin.androidtest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.http.Header;
import org.json.JSONObject;
import com.alibaba.fastjson.JSON;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loujin.app.AppConfig;
import com.loujin.app.AppContext;
import com.loujin.bean.Yi18Entity;
import com.loujin.cache.CategoryCach;
import com.loujin.cache.ShouyeCach;
import com.loujin.utils.CallBack;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

public class InitBaseData {

	private Context context;
	private List<Yi18Entity> ListYi;
	private boolean loadfen = false;
	private CallBack<Boolean> callback;
	private int initNum = 0;

	public InitBaseData(Context context, CallBack<Boolean> callback) {
		this.context = context;
		this.callback = callback;
	};

	private static final int corePoolSize = 15;
	private static final int maximumPoolSize = 30;
	private static final int keepAliveTime = 5;

	private static final BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(
			maximumPoolSize);
	private static final Executor threadPoolExecutor = new ThreadPoolExecutor(
			corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS,
			workQueue);

	@SuppressWarnings("unchecked")
	void addTaskInPool(AsyncTask asyncTask) {
		Log.e("执行到这里", asyncTask + "");
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			asyncTask.executeOnExecutor(threadPoolExecutor);
		} else {
			asyncTask.execute();
		}
	}

	public void startInit() {
		if (ShouyeCach.getShouyeInstance().getCachList().size() != 0) {
			ListYi = ShouyeCach.getShouyeInstance().getCachList();
			addFenTask();
		} else {
			AsyncHttpClient client = new AsyncHttpClient();
			client.get(AppConfig.Main_Url, mHandler);
		}
	}

	void addFenTask() {
		loadfen = true;
		if (ListYi.size() != 0) {
			if (CategoryCach.getCategoryCach().getCachList().size() == 0) {
				for (Yi18Entity yi : ListYi) {
					AsyncHttpClient client = new AsyncHttpClient();
					RequestParams par = new RequestParams();
					par.put("id", yi.getId());
					Log.e("当前ID", yi.getId() + "");
					client.get(AppConfig.Main_Url, par, mHandler);
				}
			}else{
				callback.invoke(true);
			}
		}
	}

	private void convertJsonToEntity(JSONObject item) {
		try {
			if (loadfen) {
				CategoryCach.getCategoryCach().addCach(
						item.getJSONArray("yi18").toString());
				if(initNum>ListYi.size()){
					callback.invoke(true);
					Log.e("加载完成", "加载完成");
				}
			} else {
				ListYi = new ArrayList<>();
				if (item.getBoolean("success")) {
					ListYi = JSON.parseArray(item.getJSONArray("yi18").toString(),
							Yi18Entity.class);
				}
				ShouyeCach.getShouyeInstance().cleanCach("shouye");
				ShouyeCach.getShouyeInstance().addCach(
						item.getJSONArray("yi18").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			AppContext.showToast("异常" + e.toString());
		}
	}

	protected JsonHttpResponseHandler mHandler = new JsonHttpResponseHandler() {
		@Override
		public void onFailure(int statusCode, Header[] headers,
				String responseString, Throwable throwable) {
			// TODO Auto-generated method stub
			super.onFailure(statusCode, headers, responseString, throwable);
			initNum++;
		}

		@Override
		public void onSuccess(int statusCode, Header[] headers,
				JSONObject response) {
			// TODO Auto-generated method stub
			super.onSuccess(statusCode, headers, response);
			initNum++;
			Log.e("当前", initNum+"个");
			if (statusCode == 200) {
				try {
					if (response.getBoolean("success")) {
						if (loadfen) {
							convertJsonToEntity(response);
						} else {
							// 保存第一级到数据库
							convertJsonToEntity(response);
							Log.e("开始执行加载第二级分类", "开始");
							// 遍历第二级
							addFenTask();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	};
}
