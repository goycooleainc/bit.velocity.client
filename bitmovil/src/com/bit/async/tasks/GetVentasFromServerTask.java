package com.bit.async.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.bit.client.R;
import com.bit.entities.Venta;
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
public class GetVentasFromServerTask extends AsyncTask<Void, Void, ArrayList<Venta>> {

	String result = null;
	String ip = "78.41.206.33";
	String usuario = "1-1";
	String password = "password";
	ProgressDialog progressDialog;
	public String productora;
	private Context context;

	public GetVentasFromServerTask(Context context){
		this.context = context;
	}

	public String getProductora() {
		return productora;
	}

	public void setProductora(String productora) {
		this.productora = productora;
	}

	final String GetReports()
	{
		BufferedReader inStream = null;
		try 
		{
			HttpClient httpClient = new DefaultHttpClient();
			String server = context.getString(R.string.server);
			HttpGet httpGet = new HttpGet(server + "/mobile/ventas/"+productora);
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
	protected ArrayList<Venta> doInBackground(Void... params) {
		// TODO Auto-generated method stub
		Type listType = new TypeToken<ArrayList<Venta>>() {
	     }.getType();
	     if(GetReports()!=null){
			 return new Gson().fromJson(GetReports(), listType);
	     }else{
	    	 return null;
	     }
	}
	
}