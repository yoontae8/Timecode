package com.example.timecode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.timecode.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class GroupInfo extends Activity {

	//���� ����
	ListView list;
	ListViewAdapter2 adapter;
	Handler handler;
	Button okBtn;
	Toast t1;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group_info);
		Log.e("activity", "GroupInfo.java");
		
		okBtn = (Button) findViewById(R.id.okBtn);
		list = (ListView) findViewById(R.id.listView1);
		adapter = new ListViewAdapter2(this);
		list.setAdapter(adapter);
		handler = new Handler();
		t1 = Toast.makeText(getApplicationContext(), "No information", 2000);
		new Thread(new Runnable(){
			JSONObject json = null;
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String selectQuery = "SELECT+"+GlobalVariable.VENUE_mtTAG+"+"
						+ "FROM+"+GlobalVariable.MEETING_TABLE+
						"+WHERE+("+GlobalVariable.GROUP_mtTAG+"='"+UserInfo.GROUP+"')";
				String url = GlobalVariable.QUERY_SLTGRP_PHP+"?query="+selectQuery;
				Log.i("url",url);
				json = JSONParser.getJSONFromUrl(url);
				try {
					if(JSONParser.getResultCode() == 1) {


						JSONArray jsonArray = json.getJSONArray("result");
						if(jsonArray.length() > 0) {
							for( int i=0; i<jsonArray.length();i++){
								JSONObject obj = jsonArray.getJSONObject(i);
								String id = obj.getString(GlobalVariable.VENUE_mtTAG);
								Log.i("id", id);
								adapter.add(id);
							}
						}
					}
					else {
						t1.show();
						finish();
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				handler.post(new Runnable(){
					@Override
					public void run() {
						// TODO Auto-generated method stub
						adapter.notifyDataSetChanged();
					}});
				

			}
		}
				).start();

		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long arg3) {
				// TODO Auto-generated method stub
				Log.i("item", (String) adapter.getItem(position));
				Toast.makeText(getApplicationContext(), (String) adapter.getItem(position), 2000).show();
				UserInfo.VENUE = (String) adapter.getItem(position);
				Intent intent = new Intent(GroupInfo.this, MeetingInfo.class);
				startActivity(intent);

			}
		});
		
		okBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				
				
			}
		});
		
	}




}
