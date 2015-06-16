package com.loujin.fragment;

import com.loujin.androidtest.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class YaoDianFragment extends Fragment {
	
	public YaoDianFragment newInstance(String text) {
		YaoDianFragment mFragment = new YaoDianFragment();
		Bundle mBundle = new Bundle();
		mBundle.putString("args", text);
		mFragment.setArguments(mBundle);
		return mFragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_main, container, false);
		TextView mTxtTitle = (TextView) rootView.findViewById(R.id.txtTitle);
		mTxtTitle.setText("附近药店");
		
		return rootView;
	}

}
