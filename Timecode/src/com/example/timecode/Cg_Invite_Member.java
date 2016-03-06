package com.example.timecode;


import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.timecode.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class Cg_Invite_Member extends Activity {
	
	////////////////////////////////////////
	
	final int BackToCreateGroup = 3;
	static ListView MemberList;
    static Button InviteOkButton;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cg_invite_member);
	
		
		final ArrayList<String> MemberItem = new ArrayList<String>();
		ArrayAdapter<String> myAdapterInstance;
		InviteOkButton = (Button)findViewById(R.id.InviteOkButton);
		MemberList = (ListView)findViewById(R.id.MemberList);

		new Thread(new Runnable(){
			JSONObject json = null;
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String selectQuery = "SELECT+"+GlobalVariable.Id_mbTAG+"+FROM+"+GlobalVariable.MEMBER_TABLE;
				String url = GlobalVariable.QUERY_PHP+"?query="+selectQuery;
				Log.i("url",url);
				json = JSONParser.getJSONFromUrl(url);
				try {
					JSONArray jsonArray = json.getJSONArray("result");
					for( int i=0; i<jsonArray.length();i++){
						JSONObject obj = jsonArray.getJSONObject(i);
						String id = obj.getString(GlobalVariable.Id_mbTAG);
						Log.i("id", id);
						MemberItem.add(id);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}



			}
		}
				).start();


		
		
		myAdapterInstance = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice, MemberItem);
		MemberList.setAdapter(myAdapterInstance);
		MemberList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		
		
		///-----------temporary value-----"
				
		

		
		
		
		
	}
	public void SearchOnClick(View view){
		
		
	}
	public void OkOnClick(View view){
		int len = MemberList.getCount();
		
		

		Intent intent = new Intent();
		ArrayList<String> SelectedMember = new ArrayList<String>();
		
		SparseBooleanArray checked = MemberList.getCheckedItemPositions();
		
		for(int x = 0; x<len; x++){
			
			Log.e("array",Boolean.toString(checked.get(x)));
		}
		
		intent.putExtra("num", checked.size());
		
		int position = 0;
		for(int i =0; i<len; i++){
			
			if(checked.get(i)==true){
				
				String temp = (String)MemberList.getAdapter().getItem(i);
				intent.putExtra(Integer.toString(position),temp);
				position++;
				
				Log.e("name sent", temp);
				Log.e("test value", Integer.toString(position));
			}
		}
		
		setResult(BackToCreateGroup,intent);
		Log.e("sent", "sent finished");
		finish();
	}



}
