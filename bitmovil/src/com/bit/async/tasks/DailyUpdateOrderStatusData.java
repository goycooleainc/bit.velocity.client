package com.bit.async.tasks;

import android.content.Context;
import android.os.AsyncTask;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//
public class DailyUpdateOrderStatusData extends AsyncTask<String, Void, String> {
	
	String USUARIO;
	String PASS;
	String IP;
	Context CTX;
	String ID;
    String TYPE;
	
	String URL;

	@Override
	protected String doInBackground(String... params) {
		
		USUARIO = params[0];
		PASS = params[1];
		IP = params[2];
        ID = params[3];
        TYPE = params[4];

        String status="NACK";
        
		try
		{
			return postResultsToServer();
		}
		catch(Exception e)
		{
			e.toString();
		}
		
		return status;
	}
	
	
	/**
	 * METODO QUE ENVIA DATOS AL SERVIDOR
	 * @return
	 */
	final String postResultsToServer()
	{
		String result = "NACK";
		
		String url = "http://" + IP + "/Workshop/mobile/order/" + ID + "/" + TYPE;
		try 
		{
			DefaultHttpClient httpclient = new DefaultHttpClient();
			
			HttpPost httpRequest = new HttpPost(url);
			BasicHttpContext localContext = new BasicHttpContext();
			HttpHost targetHost = new HttpHost(IP, 8080, "http");
			BasicScheme basicAuth = new BasicScheme();
			localContext.setAttribute("preemptive-auth", basicAuth);
	        
			httpRequest.setHeader("Accept", "application/json");
	        httpRequest.setHeader("Content-Type", "application/json");
	        
	        //passes the results to a string builder/entity
			StringEntity se = new StringEntity("");

			//sets the post request as the resulting string
			httpRequest.setEntity(se);

	        // authentication 
			httpclient.getCredentialsProvider().setCredentials(AuthScope.ANY,new UsernamePasswordCredentials(USUARIO, PASS));

			HttpResponse response = httpclient.execute(targetHost, httpRequest);
			
	        switch (response.getStatusLine().getStatusCode())
			{
				case 401:
					result = "Usuario no Existe";
					break;
				case 404:
					result = "No se encuentro el servidor";
					break;
				case 200:
					BufferedReader inStream = null;
					inStream = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
					String aux = inStream.readLine();
					String substr = aux.substring(aux.indexOf("error")).replace("error\":", "").replace("}", "");
					if(substr.equals("null"))
					{
						//En caso de no existir errores retorna un ACK
						result = aux.substring(aux.indexOf("idOrden")).replace("error\":", "").replace("}", "");
					}
					else
					{
						//En caso de existir errores retorna el error recibido desde el servidor...(normalmente error en el mismo servidor)
						result = substr;
					}
					break;
				default:
					result = "Error desconocido, contacte a servicio tecnico.";
					break;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 

		return result;
	}
	
}