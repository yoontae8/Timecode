package com.example.timecode;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter{

	Context mContext;
	LayoutInflater mInflater;
	
	ArrayList<String> arrList;
	
	public ListViewAdapter(Context mContext) {
		this.mContext = mContext;
		mInflater = LayoutInflater.from(mContext);
		LayoutInflater.from(mContext);
		arrList = new ArrayList<String>();
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arrList.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder;
		if(convertView == null) {
			convertView = mInflater.inflate(android.R.layout.simple_list_item_1, null);
			viewHolder = new ViewHolder();
			viewHolder.textView1 = (TextView) convertView.findViewById(android.R.id.text1);
			convertView.setTag(viewHolder);;
			
		}else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.textView1.setText(arrList.get(position));
		
		return convertView;
	}
	
	public class ViewHolder {
		TextView textView1;
	}
	
	public void add(String userId) {
		arrList.add(userId);
	}
	
	public void delete(String userId) {
		for(int i = 0; i < arrList.size(); i++) {
			if(arrList.get(i).equals(userId))
				arrList.remove(i--);
		}
	}
	
	public void reset() {
		arrList.clear();
		notifyDataSetChanged();
	}
	
}
