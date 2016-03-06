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
import android.widget.ListView;
import android.widget.Toast;

public class SelectGroup extends Activity {

	//���� ����
	ListView list;
	ListViewAdapter adapter;
	Handler handler;
	Toast t1;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_selectgroup);
		Log.e("activity", "SelectGroup.java");
		
		list = (ListView) findViewById(R.id.listView1);
		adapter = new ListViewAdapter(this);
		list.setAdapter(adapter);
		handler = new Handler();
		t1 = Toast.makeText(getApplicationContext(), "No information", 2000);
		new Thread(new Runnable(){
			JSONObject json = null;
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String selectQuery = "SELECT+"+GlobalVariable.GROUP_gTAG+"+FROM+"+GlobalVariable.GROUP_TABLE+
						"+WHERE+("+GlobalVariable.Id_gTAG+"='"+UserInfo.ID+"')";
				String url = GlobalVariable.QUERY_SLTGRP_PHP+"?query="+selectQuery;
				Log.i("url",url);
				json = JSONParser.getJSONFromUrl(url);
				
				try {
					Log.i("intvalue", JSONParser.getResultCode() + "");
					if(JSONParser.getResultCode() == 1)
					{
						JSONArray jsonArray = json.getJSONArray("result");
						Log.i("length", jsonArray.length() + "");
						if(jsonArray.length() > 0) {
							for( int i=0; i<jsonArray.length();i++){
								JSONObject obj = jsonArray.getJSONObject(i);
								String id = obj.getString(GlobalVariable.GROUP_gTAG);
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
				UserInfo.GROUP = (String) adapter.getItem(position);
				Intent intent = new Intent(SelectGroup.this, GroupInfo.class);
				startActivity(intent);
				
			}
		});
	}
		
		
		
	
}
