package com.loujin.api;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
public class MyApi {
	
    /**
     * 取首页面的食谱分类
     * @param id 为0表示一级分类
     * @param handler
     */
    public static void getCookList(int id,
            AsyncHttpResponseHandler handler) {
        ApiHttpClient.get("cook/cookclass", handler);
    }
    
    


}
