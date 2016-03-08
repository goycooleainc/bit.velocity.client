//package com.bit.async.tasks;
//
//import android.os.AsyncTask;
//import android.util.Log;
//import org.apache.http.HttpHost;
//import org.apache.http.HttpResponse;
//import org.apache.http.auth.AuthScope;
//import org.apache.http.auth.UsernamePasswordCredentials;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.conn.HttpHostConnectException;
//import org.apache.http.impl.auth.BasicScheme;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.protocol.BasicHttpContext;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
////
//public class DailyGetOrders extends AsyncTask<String, Void, String> {
//	String usuario;
//	String pass;
//	String ip;
//	String result = null;
//
//
//	@Override
//	protected String doInBackground(String... params) {
//
//		Log.e("checkLogin", "Start DailyGetData");
//
//		this.usuario = params[0];
//		this.pass = params[1];
//		this.ip = params[2];
//		return GetCandidate();
//	}
//
//	final String GetCandidate()
//	{
//        String url = "http://atlas.goycooleainc.enterprises/mobile/orders";
//		BufferedReader inStream = null;
//		try
//		{
//			DefaultHttpClient httpclient = new DefaultHttpClient();
//			HttpGet httpRequest = new HttpGet(url);
//			BasicHttpContext localContext = new BasicHttpContext();
//			HttpHost targetHost = new HttpHost(ip,80,"http");
//			BasicScheme basicAuth = new BasicScheme();
//			localContext.setAttribute("preemptive-auth", basicAuth);
//
//			httpRequest.setHeader("Accept", "application/json");
//	        httpRequest.setHeader("Content-Type", "application/json");
//
//	        /* authentication */
//	        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(usuario, pass);
//	        httpclient.getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),credentials);
//
//			HttpResponse response = httpclient.execute(targetHost, httpRequest, localContext);
//
//			switch (response.getStatusLine().getStatusCode())
//			{
//				case 401:
//					result = "Usuario no Existe";
//					break;
//				case 404:
//					result = "No se encuentro el servidor";
//					break;
//				case 200:
//					inStream = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
//					result = inStream.readLine();
//					break;
//				default:
//					result = "Error desconocido, contacte a servicio tecnico.";
//					break;
//			}
//
//		}
//		catch(HttpHostConnectException e)
//		{
//			result = "No se encuentro el servidor";
//			Log.println(0, "DailyGetData", "No se encuentro el servidor");
//		}
//		catch (Exception e)
//		{
//			result = "Error desconocido, contacte a servicio tecnico.";
//			Log.println(0, "DailyGetData", "Error desconocido");
//		}
//		finally
//		{
//			if (inStream != null) {
//				try {
//					inStream.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//
//		return result;
//	}
//
//}