/*
 * Copyright 2015 Rudson Lima
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.loujin.androidtest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.loujin.bean.Yi18Entity;
import com.loujin.cache.ShouyeCach;
import com.loujin.fragment.BaseFragment;
import com.loujin.utils.CallBack;
import com.loujin.widget.ColorGenerator;
import com.loujin.widget.IphoneTreeView;
import com.loujin.widget.IphoneTreeView.IphoneTreeHeaderAdapter;
import com.loujin.widget.TextDrawable;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentMain extends BaseFragment {

	private boolean mSearchCheck;
	private static final String TEXT_FRAGMENT = "args";
	private ColorGenerator mColorGenerator = ColorGenerator.DEFAULT;
	private TextDrawable.IBuilder mDrawableBuilder,mDrawableBuilder1;
	private View rootView = null;
	private List<Yi18Entity> ListYi;
	private List<Yi18Entity> ZListYi = new ArrayList<>();
	private IphoneTreeView iphoneTreeView;
	private SampleAdapter adapter;

	// list of data items
	// private List<ListData> mDataList = Arrays.asList(new ListData("常见病状"),
	// new ListData("用药咨询"), new ListData("健康科普"), new ListData("常见病状"),
	// new ListData("用药咨询"), new ListData("健康科普"), new ListData("常见病状"),
	// new ListData("用药咨询"));

	public FragmentMain newInstance(String text) {
		FragmentMain mFragment = new FragmentMain();
		Bundle mBundle = new Bundle();
		mBundle.putString(TEXT_FRAGMENT, text);
		mFragment.setArguments(mBundle);
		return mFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (rootView == null) {
			rootView = inflater.inflate(R.layout.activity_main, container,
					false);
			iphoneTreeView = (IphoneTreeView) rootView
					.findViewById(R.id.listView);
			iphoneTreeView.setHeaderView(getActivity().getLayoutInflater()
					.inflate(R.layout.list_item_layout, iphoneTreeView, false));
			InitBaseData init = new InitBaseData(getActivity(),
					new CallBack<Boolean>() {

						public void invoke(Boolean arg) {
							// TODO Auto-generated method stub
							if (arg) {
								ListYi = ShouyeCach.getShouyeInstance()
										.getCachList();
								initShouYeView();
							}
						}

						@Override
						public void invoke() {
							// TODO Auto-generated method stub

						}

						@Override
						public void invoke(Object... args) {
							// TODO Auto-generated method stub

						}
					});
			init.startInit();
			rootView.setLayoutParams(new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

		}
		ViewGroup parent = (ViewGroup) rootView.getParent();
		if (parent != null) {
			parent.removeView(rootView);
		}
		return rootView;
	}

	private void initShouYeView() {
		mDrawableBuilder = TextDrawable.builder().round();
		mDrawableBuilder1 = TextDrawable.builder().roundRect(3);
//		Resources res=getResources();
//		Drawable dra = res.getDrawable(R.drawable.ic_liveo);
		iphoneTreeView.setGroupIndicator(null);
		adapter = new SampleAdapter();
		iphoneTreeView.setAdapter(adapter);
	}

	private class SampleAdapter extends BaseExpandableListAdapter implements
			IphoneTreeHeaderAdapter {

		private HashMap<Integer, Integer> groupStatusMap;

		public SampleAdapter() {
			groupStatusMap = new HashMap<Integer, Integer>();
		}

		private void updateCheckedState(ViewHolder holder, Yi18Entity item,boolean catagory) {
			TextDrawable drawable ;
			if(catagory){
				drawable = mDrawableBuilder1.build(
						String.valueOf(item.getName().charAt(0)),
						mColorGenerator.getColor(item.getName()));
			}else{
				drawable = mDrawableBuilder.build(
						String.valueOf(item.getName().charAt(0)),
						mColorGenerator.getColor(item.getName()));
			} 
			holder.imageView.setImageDrawable(drawable);
			holder.view.setBackgroundColor(Color.TRANSPARENT);
		}

		@Override
		public Object getChild(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return ZListYi.get(childPosition);
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return childPosition;
		}

		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			final ViewHolder holder;
			if (convertView == null) {
				convertView = View.inflate(getActivity(),
						R.layout.layout_item_z, null);
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			if (ZListYi.size() != 0) {
				Yi18Entity item = ZListYi.get(childPosition);
				updateCheckedState(holder, item,true);
				convertView.setBackgroundColor(Color.WHITE);
				holder.textView.setText(item.getName());
			}
			return convertView;
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			// TODO Auto-generated method stub
			return ZListYi.size();
		}

		@Override
		public Object getGroup(int groupPosition) {
			// TODO Auto-generated method stub
			return ListYi.get(groupPosition);
		}

		@Override
		public int getGroupCount() {
			// TODO Auto-generated method stub
			return ListYi.size();
		}

		@Override
		public long getGroupId(int groupPosition) {
			// TODO Auto-generated method stub
			return ListYi.get(groupPosition).getId();
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			final ViewHolder holder;
			if (convertView == null) {
				convertView = View.inflate(getActivity(),
						R.layout.list_item_layout, null);
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			Yi18Entity item = ListYi.get(groupPosition);
			updateCheckedState(holder, item,false);
			holder.textView.setText(item.getName());
			return convertView;
		}

		@Override
		public boolean hasStableIds() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public int getTreeHeaderState(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			final int childCount = getChildrenCount(groupPosition);
			if (childPosition == childCount - 1) {
				return PINNED_HEADER_PUSHED_UP;
			} else if (childPosition == -1
					&& !iphoneTreeView.isGroupExpanded(groupPosition)) {
				return PINNED_HEADER_GONE;
			} else {
				return PINNED_HEADER_VISIBLE;
			}
		}

		@Override
		public void configureTreeHeader(View header, int groupPosition,
				int childPosition, int alpha) {
			((TextView) header.findViewById(R.id.textView)).setText(ListYi.get(
					groupPosition).getName());
			TextDrawable drawable = mDrawableBuilder.build(String
					.valueOf(ListYi.get(groupPosition).getName().charAt(0)),
					mColorGenerator.getColor(ListYi.get(groupPosition)
							.getName()));
			((ImageView) header.findViewById(R.id.imageView))
					.setImageDrawable(drawable);
			header.setBackgroundColor(Color.WHITE);
		}

		@Override
		public void onHeadViewClick(int groupPosition, int status) {
			// TODO Auto-generated method stub
			groupStatusMap.put(groupPosition, status);
			ZListYi = ShouyeCach.getShouyeInstance().getCategoryList(groupPosition);
			notifyDataSetChanged();
		}

		@Override
		public int getHeadViewClickStatus(int groupPosition) {
			// TODO Auto-generated method stub
			if (groupStatusMap.containsKey(groupPosition)) {
				return groupStatusMap.get(groupPosition);
			} else {
				return 0;
			}
		}
	}

	private static class ViewHolder {

		private View view;

		private ImageView imageView;

		private TextView textView;

		private ViewHolder(View view) {
			this.view = view;
			imageView = (ImageView) view.findViewById(R.id.imageView);
			textView = (TextView) view.findViewById(R.id.textView);
		}
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.menu, menu);

		SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu
				.findItem(R.id.menu_search));
		searchView.setQueryHint(this.getString(R.string.search));

		((EditText) searchView
				.findViewById(android.support.v7.appcompat.R.id.search_src_text))
				.setHintTextColor(getResources().getColor(R.color.nliveo_white));
		searchView.setOnQueryTextListener(onQuerySearchView);

		menu.findItem(R.id.menu_add).setVisible(true);
		menu.findItem(R.id.menu_search).setVisible(true);

		mSearchCheck = false;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {

		case R.id.menu_add:
			Toast.makeText(getActivity(), R.string.add, Toast.LENGTH_SHORT)
					.show();
			break;

		case R.id.menu_search:
			mSearchCheck = true;
			Toast.makeText(getActivity(), R.string.search, Toast.LENGTH_SHORT)
					.show();
			break;
		}
		return true;
	}

	private SearchView.OnQueryTextListener onQuerySearchView = new SearchView.OnQueryTextListener() {
		@Override
		public boolean onQueryTextSubmit(String s) {
			return false;
		}

		@Override
		public boolean onQueryTextChange(String s) {
			if (mSearchCheck) {
				// implement your search here
			}
			return false;
		}
	};
}
