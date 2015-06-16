package com.loujin.fragment;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {

	private static final int corePoolSize = 15;
	private static final int maximumPoolSize = 30;
	private static final int keepAliveTime = 5;
	
	private static final BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(maximumPoolSize);
	private static final Executor threadPoolExecutor = new ThreadPoolExecutor(
	  corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);

	@SuppressWarnings("unchecked")
	void addTaskInPool(AsyncTask<?, ?, ?> asyncTask) {
	  if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
	      asyncTask.executeOnExecutor(threadPoolExecutor);
	  }else{
	      asyncTask.execute();
	  }
	}
}
