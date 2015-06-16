//package com.loujin.adapter;
//
//import java.util.List;
//
//import android.content.Context;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseExpandableListAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.loujin.androidtest.R;
//import com.loujin.bean.Yi18Entity;
//import com.loujin.widget.TextDrawable;
//import com.loujin.widget.IphoneTreeView.IphoneTreeHeaderAdapter;
//
//class TreeViewAdapter extends BaseExpandableListAdapter implements
//		IphoneTreeHeaderAdapter {
//	
//	private Context context;
//	private List<Yi18Entity> ListYi;
//	public TreeViewAdapter(Context context,List<Yi18Entity> ListYi){
//		this.context = context;
//		this.ListYi = ListYi;
//	}
//
//	public int getCount() {
//		return ListYi.size();
//	}
//
//	public Yi18Entity getItem(int position) {
//		return ListYi.get(position);
//	}
//
//	public long getItemId(int position) {
//		return position;
//	}
//
//	public View getView(final int position, View convertView, ViewGroup parent) {
//		final ViewHolder holder;
//		if (convertView == null) {
//			convertView = View.inflate(context,
//					R.layout.list_item_layout, null);
//			holder = new ViewHolder(convertView);
//			convertView.setTag(holder);
//		} else {
//			holder = (ViewHolder) convertView.getTag();
//		}
//		Yi18Entity item = ListYi.get(position);
//		// provide support for selected state
//		updateCheckedState(holder, item);
//		holder.textView.setText(item.getName());
//
//		return convertView;
//	}
//
//	private void updateCheckedState(ViewHolder holder, Yi18Entity item) {
//
//		TextDrawable drawable = mDrawableBuilder.build(
//				String.valueOf(item.getName().charAt(0)),
//				mColorGenerator.getColor(item.getName()));
//		holder.imageView.setImageDrawable(drawable);
//		holder.view.setBackgroundColor(Color.TRANSPARENT);
//	}
//
//	@Override
//	public Object getChild(int groupPosition, int childPosition) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public long getChildId(int groupPosition, int childPosition) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public View getChildView(int groupPosition, int childPosition,
//			boolean isLastChild, View convertView, ViewGroup parent) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public int getChildrenCount(int groupPosition) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public Object getGroup(int groupPosition) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public int getGroupCount() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public long getGroupId(int groupPosition) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public View getGroupView(int groupPosition, boolean isExpanded,
//			View convertView, ViewGroup parent) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean hasStableIds() {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean isChildSelectable(int groupPosition, int childPosition) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public int getTreeHeaderState(int groupPosition, int childPosition) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public void configureTreeHeader(View header, int groupPosition,
//			int childPosition, int alpha) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void onHeadViewClick(int groupPosition, int status) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public int getHeadViewClickStatus(int groupPosition) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//}
//
//class ViewHolder {
//
//	private View view;
//
//	private ImageView imageView;
//
//	private TextView textView;
//
//	ViewHolder(View view) {
//		this.view = view;
//		imageView = (ImageView) view.findViewById(R.id.imageView);
//		textView = (TextView) view.findViewById(R.id.textView);
//	}
//}
