package com.example.timecode;

public class GlobalVariable {
	//URL variable

	public static final String IP_ADDR = "ryangwa.ddns.net:14002";
	public static final String QUERY_PHP = "http://"+IP_ADDR+"/timecode/queryExecutor.php";
	public static final String NONQUERY_PHP = "http://"+IP_ADDR+"/timecode/nonqueryExecutor.php";
	public static final String QUERY_LOGIN_PHP = "http://"+IP_ADDR+"/timecode/queryExecutor_login.php";
	public static final String QUERY_SLTGRP_PHP = "http://"+IP_ADDR+"/timecode/queryExecutor_selectgroup.php";
	
	//TABLE variable
	public static final String MEMBER_TABLE = "member";
	public static final String GROUP_TABLE = "grp";
	public static final String MEETING_TABLE = "meeting";
	
	//TAG variable - member
	public static final String PHONE_mbTAG = "phone";
	public static final String Id_mbTAG = "Id";
	public static final String PASSWD_mbTAG = "passwd";
	public static final String S_ID_mbTAG = "s_id";
	//TAG variable - group
	public static final String Id_gTAG = "Id";
	public static final String GROUP_gTAG = "grp";
	public static final String SUBJECT_gTAG = "subject";
	//TAG variable - meeting
	public static final String GROUP_mtTAG = "grp";
	public static final String SUBJECT_mtTAG = "subject";
	public static final String MONTH_mtTAG = "month";
	public static final String DAY_mtTAG = "day";
	public static final String HOUR_mtTAG = "hour";
	public static final String MINUTE_mtTAG = "minute";
	public static final String VENUE_mtTAG = "venue";
	public static final String AMPM_mtTAG = "ampm";
	public static final String ANNOUNCE_mtTAG = "announce";
}
