package com.example.timecode;

import com.example.timecode.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.EventLogTags.Description;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class TimeTraveler extends Activity implements OnClickListener{

	Spinner sp,dp;
	String selected, selected1;
	Intent myIntent;
	String[] name = {"Shalom&Evenezel", "Vision", "Creation", "Bethel", "Lothem", "Grace","HyoamChaplain", "GLC", "HDH", "NMH", "NTH", "OH","ANH", "SU"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timetraveler);
		
		
		
		//Start point Spinner
		sp = (Spinner) findViewById(R.id.SP);
		
		ArrayAdapter<String> aa1=new ArrayAdapter<String>(
				this,android.R.layout.simple_spinner_item,name);
		aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp.setAdapter(aa1);
		sp.setVisibility(Spinner.VISIBLE);
		
		
		//Destination Spinner
		dp = (Spinner) findViewById(R.id.DES);
		ArrayAdapter<String> aa2=new ArrayAdapter<String>(
				this,android.R.layout.simple_spinner_item,name);
		aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		dp.setAdapter(aa2);
		dp.setVisibility(Spinner.VISIBLE);
		
		
		//Button
		Button btnStart=(Button) findViewById(R.id.btn_OK);
		btnStart.setOnClickListener(this);
		
		
	}
	public void onClick(View v) {
		// TODO Auto-generated method stub
		selected=sp.getSelectedItem().toString();
		selected1=dp.getSelectedItem().toString();
		
		
		
		myIntent=new Intent(getApplicationContext(),Tt_Map_View.class);
		myIntent.putExtra("sp",selected);
		myIntent.putExtra("dp", selected1);	
		
		startActivity(myIntent);
				
		
	}

	
}
