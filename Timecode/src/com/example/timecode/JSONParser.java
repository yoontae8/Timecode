package com.example.timecode;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
 
public class JSONParser {
    static private int rst;
    public static JSONObject getJSONFromUrl(String url) {
    	// Making HTTP request
    	JSONObject jObj = null;
        String json = "";
        try {
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            json = EntityUtils.toString(httpEntity);
        } catch (UnsupportedEncodingException e) {
    	    e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    	try {
    		if(json.contains("{"))
    			jObj = new JSONObject(json);
    		else
    			rst = Integer.valueOf(json);
    	} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        Log.i("a","check6");
        return jObj;
    }
    public static int getResultCode(){
    	return rst;
    }
}
