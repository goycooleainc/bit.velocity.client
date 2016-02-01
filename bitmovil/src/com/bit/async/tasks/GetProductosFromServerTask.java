package com.bit.async.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;

import com.bit.client.R;
import com.bit.entities.Productos;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

//
public class GetProductosFromServerTask extends AsyncTask<Void, Void, ArrayList<Productos>> {

	String result = null;
	String ip = "78.41.206.33";
	String usuario = "1-1";
	String password = "password";
	ProgressDialog progressDialog;
	private Context context;

	public GetProductosFromServerTask(Context context){
		this.context = context;
	}
	
	final String GetReports()
	{

		BufferedReader inStream = null;
		try 
		{
			HttpClient httpClient = new DefaultHttpClient();
			String url = context.getString(R.string.server);
			HttpGet httpGet = new HttpGet(url + "/mobile/productos/bit");
			HttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity responseEntity = httpResponse.getEntity();
			inStream = new BufferedReader(new InputStreamReader(responseEntity.getContent(), "UTF-8"));
			result = inStream.readLine();
			// read the stream returned by 
			return result;
		}catch(Exception ex){
			return null;
		}
		
	}

	@Override
	protected ArrayList<Productos> doInBackground(Void... params) {
		// TODO Auto-generated method stub
		Type listType = new TypeToken<ArrayList<Productos>>() {
	     }.getType();
	     if(GetReports()!=null){
			 return new Gson().fromJson(GetReports(), listType);
	     }else{
	    	 return null;
	     }
	}
	
}