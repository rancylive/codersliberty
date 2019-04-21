package com.ranjan.MapreduceTask.task1;

import org.apache.hadoop.io.Text;
import org.json.JSONArray;
import org.json.JSONObject;

public class app {
	public static void main(String[] args) {
		String json = "[{\"timestamp\":1548766094481,\"timestampist\":\"2019-01-29 18:18:14\",\"city\":\"NEW DELHI\",\"region\":\"DELHI\",\"country\":\"INDIA\",\"isp\":\"AIRTEL BROADBAND\",\"useragent\":\"Mozilla\\/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit\\/537.36 (KHTML, like Gecko) Chrome\\/71.0.3578.98 Safari\\/537.36\",\"osname\":\"Mac OS X\",\"osversion\":\"10.13.6\",\"devicename\":\"Other\",\"deviceversion\":\"0.0.0\",\"clientid\":4516,\"loggedin\":true,\"grade\":null,\"target\":null,\"board\":null,\"devicetype\":\"DESKTOP\",\"channel\":null,\"sessionid\":1283,\"screenname\":null,\"type\":\"event\",\"screenresolution\":\"1280x800\",\"viewportsize\":\"1277x622\",\"eventaction\":\"click\",\"calc_userid\":5067,\"appnameenc\":2,\"eventlaenc\":448}]";
		Text txt=new Text(json);
		String tt=txt.toString();
		System.out.println(tt);
		JSONArray object1 = new JSONArray(tt);
		for(int i=0;i<object1.length();i++) {
			JSONObject object = object1.getJSONObject(i);
			int sessionId = object.getInt("sessionid");
			int appnameenc = object.getInt("appnameenc");
			int sessionDuration = object.getInt("eventlaenc");
			System.out.println("Values are: "+sessionId+" appnameenc: "+appnameenc+" sessionDuration: "+sessionDuration);
			if (appnameenc == 1 || appnameenc == 2) {
				System.out.println("Values are: "+sessionId+" appnameenc: "+appnameenc+" sessionDuration: "+sessionDuration);
			}
		}
		
	}
}
