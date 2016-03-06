package com.example.timecode;

import com.example.timecode.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class Mainmenu extends Activity {

	ImageView Mainmenubar;
	
	Button btnTimeTraveler;
	Button btnSelectGroup;
	Button btnCreateGroup;
	Button btnPlanner;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mainmenu);
		Log.i("activity", "Mainmenu.java");
		
		Mainmenubar = (ImageView) findViewById(R.id.imageView1);
		btnTimeTraveler = (Button) findViewById(R.id.time_tBtn);
		btnSelectGroup = (Button) findViewById(R.id.selectBtn);
		btnCreateGroup = (Button) findViewById(R.id.createBtn);
		btnPlanner = (Button) findViewById(R.id.Planner);
		
		btnSelectGroup.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Mainmenu.this, SelectGroup.class);
				startActivity(intent);
			}});
		
		btnCreateGroup.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Mainmenu.this, CreateGroup.class);
				startActivity(intent);
			}});
		
		btnTimeTraveler.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Mainmenu.this, TimeTraveler.class);
				startActivity(intent);
			}});
		
	}
}
