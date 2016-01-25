package com.bit.async.tasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import com.bit.client.R;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by goycolea on 15-12-14.
 */
public class DirectOrder extends AsyncTask<String, Void, String> {

    String ip = "78.41.206.33";
    String usuario = "1-1";
    String password = "password";
    ProgressDialog progressDialog;
    private Activity activity;
    String result = null;

 // CAST THE LINEARLAYOUT HOLDING THE MAIN PROGRESS (SPINNER)
 	LinearLayout linlaHeaderProgress;

 	public DirectOrder(Activity activity)
 	{
 		this.activity = activity;
 		linlaHeaderProgress = (LinearLayout) activity.findViewById(R.id.pbHeaderProgress);
 	}
 	
    public String getDATA() {
        return DATA;
    }

    public void setDATA(String DATA) {
        this.DATA = DATA;
    }

    private String DATA;

    @Override
    protected String doInBackground(String... params) {

        return directSend();
    }

    @Override
    protected void onPreExecute() {    

    	linlaHeaderProgress.setVisibility(View.VISIBLE);
    }
    
    @Override
    protected void onPostExecute(String result) {     
        // HIDE THE SPINNER AFTER LOADING FEEDS
        linlaHeaderProgress.setVisibility(View.GONE);
    }
    /*
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(activity, "Wait", "Downloading...");
    }
*/
    @Override
    protected void onProgressUpdate(Void... values) {}

    public String directSend(){

    	String url = "http://atlas.goycooleainc.enterprises/Workshop/mobile/orders";
		BufferedReader inStream = null;
		try 
		{
			DefaultHttpClient httpclient = new DefaultHttpClient();
			HttpGet httpRequest = new HttpGet(url);
			BasicHttpContext localContext = new BasicHttpContext();
			HttpHost targetHost = new HttpHost(ip,80,"http");
			BasicScheme basicAuth = new BasicScheme();
			localContext.setAttribute("preemptive-auth", basicAuth);
	        
			httpRequest.setHeader("Accept", "application/json");
	        httpRequest.setHeader("Content-Type", "application/json");
	        
	        /* authentication */
	        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("1-1", "password");
	        httpclient.getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),credentials);

			HttpResponse response = httpclient.execute(targetHost, httpRequest, localContext);
			
			switch (response.getStatusLine().getStatusCode())
			{
				case 401:
					result = "Usuario no Existe";
					break;
				case 404:
					result = "No se encuentro el servidor";
					break;
				case 200:
					inStream = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
					result = inStream.readLine();
					break;
				default:
					result = "Error desconocido, contacte a servicio tecnico.";
					break;
			}

		} 
		catch(HttpHostConnectException e)
		{
			result = "No se encuentro el servidor";
			Log.println(0, "DailyGetData", "No se encuentro el servidor");
		} 
		catch (Exception e) 
		{
			result = "Error desconocido, contacte a servicio tecnico.";
			Log.println(0, "DailyGetData", "Error desconocido");
		} 
		finally 
		{
			if (inStream != null) {
				try {
					inStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return result;
    }
}
