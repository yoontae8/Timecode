package com.example.timecode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.timecode.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings.Global;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;

public class MeetingInfo extends Activity {
	
	ListView list;
	ListViewAdapter3 adapter;
	Handler handler;
	String sbjTxt;
	String timeTxt;
	String venueTxt;
	String announceTxt;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_meeting_info);
		Log.i("activity", "MeetingInfo.java");
		
		list = (ListView) findViewById(R.id.listView1);
		adapter = new ListViewAdapter3(this);
		list.setAdapter(adapter);
		handler = new Handler();
		
		//xml�̶� ����
		
		new Thread(new Runnable(){
			JSONObject json = null;
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String selectQuery = "SELECT+"+GlobalVariable.SUBJECT_mtTAG+","+GlobalVariable.MONTH_mtTAG+","+GlobalVariable.DAY_mtTAG+","+GlobalVariable.HOUR_mtTAG+","+GlobalVariable.MINUTE_mtTAG+","+GlobalVariable.AMPM_mtTAG+","+GlobalVariable.VENUE_mtTAG+","+GlobalVariable.ANNOUNCE_mtTAG+"+FROM+"+GlobalVariable.MEETING_TABLE+
						"+WHERE+("+GlobalVariable.VENUE_mtTAG+"='"+UserInfo.VENUE+"')";
				String url = GlobalVariable.QUERY_PHP+"?query="+selectQuery;
				Log.i("url",url);
				json = JSONParser.getJSONFromUrl(url);
				try {
					JSONArray jsonArray = json.getJSONArray("result");
					JSONObject obj = jsonArray.getJSONObject(0);
					sbjTxt = obj.getString(GlobalVariable.SUBJECT_mtTAG);
					adapter.add(sbjTxt);
					Log.i("sbjTxt",sbjTxt);
					timeTxt = obj.getString(GlobalVariable.MONTH_mtTAG) + "/" + obj.getString(GlobalVariable.DAY_mtTAG) + " " + obj.getString(GlobalVariable.HOUR_mtTAG) + ":" + obj.getString(GlobalVariable.MINUTE_mtTAG) + " " + obj.getString(GlobalVariable.AMPM_mtTAG);
					adapter.add(timeTxt);
					Log.i("timeTxt", timeTxt);
					venueTxt = obj.getString(GlobalVariable.VENUE_mtTAG);
					adapter.add(venueTxt);
					Log.i("venueTxt",venueTxt);
					announceTxt = obj.getString(GlobalVariable.ANNOUNCE_mtTAG);
					adapter.add(announceTxt);
					Log.i("announceTxt",announceTxt);
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
		

		Log.i("!", "!");
		
		
	}
		
 
		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.intro, menu);
		return true;
	}
	
}
