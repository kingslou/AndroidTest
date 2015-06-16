package com.loujin.bean;

import java.util.List;

/**
 * Created by jin on 2015.06.09.
 */
public class MyBean {

	/**
	 * yi18 :
	 * [{"id":1,"name":"美容养颜","cookclass":0},{"id":2,"name":"减肥瘦身","cookclass"
	 * :0}
	 * ,{"id":3,"name":"保健养生","cookclass":0},{"id":4,"name":"适宜人群","cookclass"
	 * :0}
	 * ,{"id":5,"name":"餐食时节","cookclass":0},{"id":6,"name":"孕产哺乳","cookclass"
	 * :0}
	 * ,{"id":7,"name":"女性养生","cookclass":0},{"id":8,"name":"男性养生","cookclass"
	 * :0}
	 * ,{"id":9,"name":"心脏血管","cookclass":0},{"id":10,"name":"皮肤器官","cookclass"
	 * :0},{"id":11,"name":"肠胃消化","cookclass":0},{"id":12,"name":"口腔呼吸",
	 * "cookclass"
	 * :0},{"id":13,"name":"肌肉神经","cookclass":0},{"id":14,"name":"癌症其他"
	 * ,"cookclass":0}] success : true
	 */
	private List<Yi18Entity> yi18;
	private boolean success;

	public MyBean() {
	}

	public void setYi18(List<Yi18Entity> yi18) {
		this.yi18 = yi18;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List<Yi18Entity> getYi18() {
		return yi18;
	}

	public boolean isSuccess() {
		return success;
	}

}
