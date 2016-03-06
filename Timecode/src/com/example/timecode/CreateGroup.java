package com.example.timecode;



import java.util.ArrayList;

import com.example.timecode.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class CreateGroup extends Activity {


	ListView Memberlist;
	ListView Meetinglist;
	EditText CreateGroupName;
	EditText CreateSubjectName;
	Button InviteButton;
	Button SetMeetingButton;
	Button CreateOk;
	static final int CreateInvitation = 1;
	static final int CreateMeeting = 2;

	//values from set meeting class
	ArrayList<Integer> month = new ArrayList<Integer>();
	ArrayList<Integer> day = new ArrayList<Integer>();
	ArrayList<Integer> hour = new ArrayList<Integer>();
	ArrayList<Integer> minute = new ArrayList<Integer>();
	ArrayList<String> venue = new ArrayList<String>();
	ArrayList<String> announce = new ArrayList<String>();
	ArrayList<String> ampm = new ArrayList<String>();
	String GroupName= "";
	String SubjectName ="";

	int NumOfMembers = 0;


	// count the number of meeting
	int meetingcount = 0;


	ArrayList<String> MemberItem = new ArrayList<String>();
	ArrayAdapter<String> MemberAdapterInstance;

	ArrayList<String> MeetingItem = new ArrayList<String>();
	ArrayAdapter<String> MeetingAdapterInstance;



	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.creategroup);


		CreateGroupName = (EditText)findViewById(R.id.CreateGroupName);
		
		CreateSubjectName = (EditText)findViewById(R.id.CreateSubjectName);

		InviteButton = (Button)findViewById(R.id.InviteButton);
		SetMeetingButton = (Button)findViewById(R.id.SetMeetingButton);
		CreateOk = (Button)findViewById(R.id.CreateButton);
		Memberlist = (ListView)findViewById(R.id.MemberList);
		Meetinglist = (ListView)findViewById(R.id.MeetingList);

		MeetingAdapterInstance = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, MeetingItem);
		Meetinglist.setAdapter(MeetingAdapterInstance);
		MemberAdapterInstance = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, MemberItem);
		Memberlist.setAdapter(MemberAdapterInstance);

	}

	public void InviteOnClick(View v){

		Intent invite_Intent = new Intent(CreateGroup.this, Cg_Invite_Member.class);
		startActivityForResult(invite_Intent, CreateInvitation);// 1
	}
	public void SetMeetingOnClick(View v){


		Intent SetMeeting_intent = new Intent(CreateGroup.this, Cg_Set_Meeting.class);
		startActivityForResult(SetMeeting_intent, CreateMeeting);//2

	}
	public void CreateOnClick(View v){
		GroupName = CreateGroupName.getText().toString();
		SubjectName = CreateSubjectName.getText().toString();
		
		new Thread(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i = 0; i < MemberItem.size(); i++){
					String insertQuery = "INSERT+INTO+"+GlobalVariable.GROUP_TABLE+"+("+GlobalVariable.Id_gTAG
							+","+GlobalVariable.GROUP_gTAG+","+GlobalVariable.SUBJECT_gTAG+")+VALUES+"
							+ "('"+MemberItem.get(i)+"','"+GroupName+"','"+SubjectName+"')";
					String url = GlobalVariable.NONQUERY_PHP+"?query="+insertQuery;
					Log.i("url",url);
					JSONParser.getJSONFromUrl(url);
				}
				for(int i = 0; i < meetingcount; i++) {
					String insertQuery = "INSERT+INTO+"+GlobalVariable.MEETING_TABLE+"+("+GlobalVariable.GROUP_mtTAG
							+","+GlobalVariable.SUBJECT_mtTAG+","+GlobalVariable.MONTH_mtTAG+","+GlobalVariable.DAY_mtTAG+","
							+ GlobalVariable.HOUR_mtTAG+","+GlobalVariable.MINUTE_mtTAG+","+GlobalVariable.AMPM_mtTAG+","
							+GlobalVariable.VENUE_mtTAG+","+GlobalVariable.ANNOUNCE_mtTAG+")+VALUES+"
							+ "('"+GroupName+"','"+SubjectName+"',"+month.get(i)+","+day.get(i)+","+hour.get(i)+","
							+ minute.get(i)+",'"+ampm.get(i)+"','"+venue.get(i)+"','"+announce.get(i)+"')";
					String url = GlobalVariable.NONQUERY_PHP+"?query="+insertQuery;
					Log.i("url",url);
					JSONParser.getJSONFromUrl(url);
				}
				finish();
				/*
				int code = JSONParser.getResultCode();
				if( code == 1){
					adapter.add(id);
					handler.post(new Runnable(){

						@Override
						public void run() {
							// TODO Auto-generated method stub
							adapter.notifyDataSetChanged();
						}});
				}*/

			}
		}
				).start();
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data){


		switch(requestCode){
		case CreateInvitation:
			if(resultCode==3){

				NumOfMembers = data.getIntExtra("num", -1);

				for(int i  =0; i<NumOfMembers;i++){


					MemberItem.add(data.getStringExtra(Integer.toString(i)));
					Log.d("test", data.getStringExtra(Integer.toString(i)));
				}
				MemberItem.add(UserInfo.ID);



				MemberAdapterInstance.notifyDataSetChanged();

				break;

			}
			else
				break;

		case CreateMeeting:	
			if(resultCode==2){

				
				month.add(data.getIntExtra("Month", -1)); 
				day.add(data.getIntExtra("Date", -1));
				hour.add(data.getIntExtra("Hour", -1));
				minute.add(data.getIntExtra("Minute", -1));
				ampm.add(data.getStringExtra("AmPm"));
				venue.add(data.getStringExtra("Venue"));
				announce.add(data.getStringExtra("Announcement"));

				String temp = "";
				if(hour.get(meetingcount)==0)
					hour.set(meetingcount, Integer.valueOf(12));
				if(minute.get(meetingcount) <10)					
					temp = "Meeting " + meetingcount + " : " + month.get(meetingcount) + "/" + day.get(meetingcount) + ", " + hour.get(meetingcount) + ":" + "0"+minute.get(meetingcount)  + ampm.get(meetingcount);
				else
					temp = "Meeting " + meetingcount + " : " + month.get(meetingcount) + "/" + day.get(meetingcount) + ", " + hour.get(meetingcount) + ":" + + minute.get(meetingcount)  + ampm.get(meetingcount);


				MeetingItem.add(meetingcount, temp);

				MeetingAdapterInstance.notifyDataSetChanged();
				meetingcount++;
				break;
			}
			else
				break;
		}

	}
	//	@Override
	//	public boolean onCreateOptionsMenu(Menu menu) {
	//		// Inflate the menu; this adds items to the action bar if it is present.
	//		getMenuInflater().inflate(R.menu.create_group, menu);
	//		return true;
	//	}

}
