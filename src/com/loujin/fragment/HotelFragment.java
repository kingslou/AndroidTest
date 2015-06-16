package com.loujin.fragment;

import com.loujin.androidtest.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HotelFragment extends Fragment {
	
	public HotelFragment newInstance(String text) {
		HotelFragment mFragment = new HotelFragment();
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
		mTxtTitle.setText("医院");
		
		return rootView;
	}

}
