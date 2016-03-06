package com.example.timecode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.example.timecode.R;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

public class Cg_Set_Meeting extends Activity {
	
	static Spinner MonthSpinner ;
	static Spinner DateSpinner;
	static Spinner HourSpinner;
	static Spinner MinuteSpinner;
	static EditText VenueText;
	static EditText Announcement;
	static Button SetConfirmButton;
	static TextView DateView;
	static TextView TimeView;
	//static DatePicker PickDate;
	//static TimePicker PickTime;
	final int BackToCreateGroup = 2;
	static int count = 0;
	static int mYear,mMonth,mDay,mHour,mMinute,ap;
	
	static String AmorPm;
	
	static int Month;
	static int Date; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cg_set_meeting);
		
			
//		MonthSpinner = (Spinner)findViewById(R.id.MonthSpinner);
//		DateSpinner= (Spinner)findViewById(R.id.DateSpinner);
//		HourSpinner = (Spinner)findViewById(R.id.HourSpinner);
//		MinuteSpinner = (Spinner)findViewById(R.id.MinuteSpinner);
//		PickDate = (DatePicker)findViewById(R.id.DatePicker);
//		PickTime = (TimePicker)findViewById(R.id.TimePicker);
		VenueText = (EditText)findViewById(R.id.VenueText);
		Announcement = (EditText)findViewById(R.id.Announcement);
		SetConfirmButton = (Button)findViewById(R.id.SetConfirmButton);
		DateView = (TextView)findViewById(R.id.DateView);
		TimeView = (TextView)findViewById(R.id.TimeView);
		
		Calendar cal = new GregorianCalendar();
		
			mYear = cal.get(GregorianCalendar.YEAR);
			mMonth = cal.get(GregorianCalendar.MONTH)+1;
			mDay = cal.get(GregorianCalendar.DAY_OF_MONTH);
			mHour = cal.get(GregorianCalendar.HOUR_OF_DAY);
			mMinute = cal.get(GregorianCalendar.MINUTE);
			ap = cal.get(GregorianCalendar.AM_PM);
		
		if(mHour==0)
			mHour = 12;
		if(mHour>12)
			mHour = mHour-12;
		if(ap ==0)
			AmorPm = "am";
		else if (ap==1)
			AmorPm = "pm";
		
		int count = 0;
		UpdateNow();
		

//		SetConfirmButton.setOnClickListener(new Button.OnClickListener(){
//			public void onClick(View v){
//				
//				Intent intent = new Intent();
//				
//					
//				
//				ViewGroup vg = (ViewGroup)PickTime.getChildAt(0);
//				intent.putExtra("Month", PickDate.getMonth()+1);
//				intent.putExtra("Date", PickDate.getDayOfMonth());
//				intent.putExtra("Hour", PickTime.getCurrentHour());
//				intent.putExtra("Minute", PickTime.getCurrentMinute());
//				intent.putExtra("AmPm", ((Button)vg.getChildAt(2)).getText().toString());
//				intent.putExtra("Venue", VenueText.getText());
//				intent.putExtra("Announcement", Announcement.getText());
//				setResult(BackToCreateGroup,intent);
//				finish();
//			}
//	});
//	
		DateView.setOnClickListener(new OnClickListener(){
			
			public void onClick(View v){
				Log.e("year",Integer.toString(mYear));
				Log.e("month",Integer.toString(mMonth));
				Log.e("day",Integer.toString(mDay));
				new DatePickerDialog(Cg_Set_Meeting.this, mDateSetListener, mYear,mMonth,mDay).show();
				
			}
			
		});
		
		TimeView.setOnClickListener(new OnClickListener(){
			
			public void onClick(View v){
				
				new TimePickerDialog(Cg_Set_Meeting.this, mTimeSetListener, mHour, mMinute, false).show();
			}
			
		});

		SetConfirmButton.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v){
				
				Intent intent = new Intent();
				
				intent.putExtra("Month", mMonth);
				intent.putExtra("Date", mDay);
				intent.putExtra("Hour", mHour);
				intent.putExtra("Minute", mMinute);
				intent.putExtra("AmPm", AmorPm);
				intent.putExtra("Venue", VenueText.getText().toString());
				intent.putExtra("Announcement", Announcement.getText().toString());
				setResult(BackToCreateGroup,intent);
				finish();
			}
	});
			
}

	
void UpdateNow(){
		
	String sMonth;
	String sDay;
	String sMinute;

	
	if (mMonth<10)
		sMonth = "0" + mMonth;
	else
		sMonth = Integer.toString(mMonth);
	if (mDay<10)
		sDay = "0" + mDay;
	else
		sDay = Integer.toString(mDay);
	if(mMinute<10)
		sMinute = "0" + mMinute;
	else
		sMinute = Integer.toString(mMinute);
	
	String time = mHour + ":"+sMinute+ AmorPm;
			
	String date = mYear+"/" + sMonth+"/"+sDay;
		
		DateView.setTextSize(getResources().getDimension(R.dimen.textsize));
		TimeView.setTextSize(getResources().getDimension(R.dimen.textsize));
		DateView.setText(date);
		TimeView.setText(time);
	
	
	}
DatePickerDialog.OnDateSetListener mDateSetListener  = new DatePickerDialog.OnDateSetListener(){
	
	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
		
		mYear = year;
		mMonth = monthOfYear+1;
		mDay = dayOfMonth;
		UpdateNow();
		
	}
	
};
TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener(){
	
	public void onTimeSet (TimePicker PickTime, int hourOfDay, int minute){
		
		ViewGroup vg = (ViewGroup)PickTime.getChildAt(0);
		
		AmorPm = ((Button)vg.getChildAt(2)).getText().toString();
		
		if(AmorPm.equalsIgnoreCase("����"))
			AmorPm="am";
		else if(AmorPm.equalsIgnoreCase("����"))
			AmorPm = "pm";
		
		if (hourOfDay>12)
			hourOfDay = hourOfDay-12;
		else;
		
		mHour = hourOfDay;
		mMinute = minute;
		
		UpdateNow();
	}
	
};	
}
