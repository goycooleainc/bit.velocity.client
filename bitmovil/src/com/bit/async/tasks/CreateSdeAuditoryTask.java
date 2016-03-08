//package com.bit.async.tasks;
//
//import android.content.Context;
//import android.os.AsyncTask;
//import android.os.SystemClock;
//import android.view.View;
//import android.widget.ProgressBar;
//import com.bit.preferences.BatchSDESharedPreference;
//import org.apache.http.HttpHost;
//import org.apache.http.HttpResponse;
//import org.apache.http.auth.AuthScope;
//import org.apache.http.auth.UsernamePasswordCredentials;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.auth.BasicScheme;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.protocol.BasicHttpContext;
//import org.apache.http.protocol.HTTP;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//
///**
// * Created by goycolea on 15-12-14.
// */
//public class CreateSdeAuditoryTask extends AsyncTask<String, Integer, String> {
//
//    String ip = "78.41.206.33";
//    String usuario = "1-1";
//    String password = "password";
//    int myProgress;
//
//    private ProgressBar progressBar;
//    private Context context;
//
//    @Override
//    protected String doInBackground(String... params) {
//    	try{
//    	directSend();
//
//    	while(myProgress<100){
//    		myProgress++;
//    		publishProgress(myProgress);
//    		SystemClock.sleep(100);
//    	}
//    	}catch(Exception ex){
//
//    	}
//
//        return "Executed";
//    }
//
//    @Override
//    protected void onPostExecute(String result) {
//    	myProgress = 0;
//    	progressBar.setVisibility(View.INVISIBLE);
//    }
//
//    @Override
//    protected void onPreExecute() {
//    	progressBar.setVisibility(View.VISIBLE);
//    	myProgress = 0;
//    }
//
//    @Override
//    protected void onProgressUpdate(Integer... values) {
//    	progressBar.setProgress(values[0]);
//    	progressBar.setSecondaryProgress(values[0]+5);
//    }
//
//    public String directSend(){
//
//    	BatchSDESharedPreference up = new BatchSDESharedPreference();
//		String updates = up.getValue(context);
//
//        String result = "NACK";
//        String url = "http://atlas.goycooleainc.enterprises/Workshop/mobile/audit/create";
//        try
//        {
//        	if(updates!=null){
//    			if(updates.contains("{")){
//    				String[] updates_s = updates.split("#NXT");
//    				for(String parent : updates_s){
//    					DefaultHttpClient httpclient = new DefaultHttpClient();
//    					HttpPost httpRequest = new HttpPost(url);
//    					BasicHttpContext localContext = new BasicHttpContext();
//    					HttpHost targetHost = new HttpHost(ip,80,"http");
//    					BasicScheme basicAuth = new BasicScheme();
//    					localContext.setAttribute("preemptive-auth", basicAuth);
//
//    					httpRequest.setHeader("Accept", "application/json");
//    					httpRequest.setHeader("Content-Type", "application/json");
//    					httpRequest.setEntity(new StringEntity(parent, HTTP.UTF_8));
//
//    					httpclient.getCredentialsProvider().setCredentials(AuthScope.ANY,new UsernamePasswordCredentials("1-1", "password"));
//    					HttpResponse response = httpclient.execute(targetHost, httpRequest);
//
//    					switch (response.getStatusLine().getStatusCode())
//    					{
//    					case 401:
//    						result = "Usuario no Existe";
//    						break;
//    					case 404:
//    						result = "No se encuentro el servidor";
//    						break;
//    					case 200:
//    						BufferedReader inStream = null;
//    						inStream = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
//    						result = inStream.readLine();
//
//    						break;
//    					default:
//    						result = "Error desconocido, contacte a servicio tecnico.";
//    						break;
//    					}
//    				}
//    			}
//        	}
//        	/// si todo sale bien
//        	up.removeValue(context);
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//
//        return result;
//    }
//
//	public ProgressBar getProgressBar() {
//		return progressBar;
//	}
//
//	public void setProgressBar(ProgressBar progressBar) {
//		this.progressBar = progressBar;
//	}
//
//	public Context getContext() {
//		return context;
//	}
//
//	public void setContext(Context context) {
//		this.context = context;
//	}
//
//}
