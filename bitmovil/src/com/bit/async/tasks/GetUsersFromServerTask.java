package com.bit.async.tasks;

import android.os.AsyncTask;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.bit.entities.User;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

//
public class GetUsersFromServerTask extends AsyncTask<Void, Void, ArrayList<User>> {

	String result = null;
	String ip = "78.41.206.33";
	String usuario = "1-1";
	String password = "password";

	
	final String GetUsers()
	{
		try
		{
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet("http://bit.goycooleainc.com/mobile/users");
			httpGet.addHeader(BasicScheme.authenticate(new UsernamePasswordCredentials("1-1", "password"),"UTF-8", false));
			HttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity responseEntity = httpResponse.getEntity();
			BufferedReader inStream = new BufferedReader(new InputStreamReader(responseEntity.getContent(), "UTF-8"));
			result = inStream.readLine();
			// read the stream returned by
			return result;
		}catch(Exception ex){
			Log.d("ERROR",ex.toString());
			return null;
		}
		
	}

	@Override
	protected ArrayList<User> doInBackground(Void... params) {
		// TODO Auto-generated method stub
		Type listType = new TypeToken<ArrayList<User>>() {

	     }.getType();
	     if(GetUsers()!=null){
			 return new Gson().fromJson(GetUsers(), listType);
	     }else{
	    	 return null;
	     }
	}
	
}