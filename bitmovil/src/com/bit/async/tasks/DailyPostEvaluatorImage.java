//package com.bit.async.tasks;
//
///**
// * * @author Nicolas Marin
// * * @LastUpdate 18 de Marzo 2013
// *  @Company Octopus
// *  Clase para manejo de conexion al servidor usando puertos especificos.
// */
//
//import android.graphics.Bitmap;
//import android.os.AsyncTask;
//import android.util.Log;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpHost;
//import org.apache.http.HttpResponse;
//import org.apache.http.HttpVersion;
//import org.apache.http.auth.AuthScope;
//import org.apache.http.auth.UsernamePasswordCredentials;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.mime.MultipartEntity;
//import org.apache.http.entity.mime.content.ContentBody;
//import org.apache.http.entity.mime.content.FileBody;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.params.CoreProtocolPNames;
//import org.apache.http.util.EntityUtils;
//
//import java.io.File;
//
//public class DailyPostEvaluatorImage extends AsyncTask<String, Void, String>{
//
//	String usuario;
//	String pass;
//	String nombreImagen;
//	String dirImagen;
//	String id;
//	String result = "fail";
//	String url;
//	String ip;
//	Bitmap bm;
//
//	@Override
//	protected String doInBackground(String... params) {
//		// TODO Auto-generated method stub
//		this.usuario = params[0];
//		this.pass = params[1];
//		this.nombreImagen = params[2];
//		this.dirImagen = params[3];
//		this.id = params[4];
//		this.bm = null;
//		this.ip = params[5];
//        this.url = "http://" + ip + "/KomatsuDemoServer/mobile/order/"+id+"/type/3";
//		return GetSomething();
//	}
//
//	final String GetSomething()
//	{
//		DefaultHttpClient httpclient = new DefaultHttpClient();
//        httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
//
//        HttpPost httppost = new HttpPost(url);
//        HttpHost targetHost = new HttpHost(ip, 8080, "http");
//        File file = new File(dirImagen + "/" + nombreImagen);
//
//        if(file.exists()){
//            // authentication
//            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(usuario, pass);
//            httpclient.getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),credentials);
//
//
//            MultipartEntity mpEntity = new MultipartEntity();
//            ContentBody cbFile = new FileBody(file, "image/jpeg");
//            mpEntity.addPart("photo", cbFile);
//
//            httppost.setEntity(mpEntity);
//            Log.e("executing request " + httppost.getRequestLine(),"");
//            try{
//                HttpResponse response = httpclient.execute(targetHost, httppost);
//                String aux = "200";
//                if(aux.equals(response.getStatusLine().getStatusCode())){
//                    result="ACK";
//                }
//                HttpEntity resEntity = response.getEntity();
//                Log.e(""+response.getStatusLine(),"");
//                if (resEntity != null) {
//                          Log.e(EntityUtils.toString(resEntity),"");
//                }
//                if (resEntity != null) {
//                          resEntity.consumeContent();
//                }
//                    httpclient.getConnectionManager().shutdown();
//                }
//            catch(Exception ex)
//            {
//                ex.toString();
//            }
//        }
//		return result;
//	}
//}
//
//
///*
//
//public String sendImageToServer(String dirImagen, String nombreImagen, String id) {
//
//    String result = "NACK";
//    String url = "http://" + ip + "/KomatsuDemoServer/mobile/order/"+id+"/type/3";
//
//    DefaultHttpClient httpclient = new DefaultHttpClient();
//    httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
//
//    HttpPost httppost = new HttpPost(url);
//    HttpHost targetHost = new HttpHost(ip, 8080, "http");
//    File file = new File(dirImagen + "/" + nombreImagen);
//
//    // authentication
//    UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(usuario, password);
//    httpclient.getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),credentials);
//
//
//    MultipartEntity mpEntity = new MultipartEntity();
//    ContentBody cbFile = new FileBody(file, "image/jpeg");
//    mpEntity.addPart("photo", cbFile);
//
//    httppost.setEntity(mpEntity);
//    Log.e("executing request " + httppost.getRequestLine(),"");
//    try{
//        HttpResponse response = httpclient.execute(targetHost, httppost);
//        String aux = "200";
//        if(aux.equals(response.getStatusLine().getStatusCode()+"")){
//            result="ACK";
//        }
//        HttpEntity resEntity = response.getEntity();
//        Log.e(""+response.getStatusLine(),"");
//        if (resEntity != null) {
//            Log.e(EntityUtils.toString(resEntity),"");
//        }
//        if (resEntity != null) {
//            resEntity.consumeContent();
//        }
//        httpclient.getConnectionManager().shutdown();
//    }
//    catch(Exception ex)
//    {
//        String x = ex.toString();
//    }
//
//    return result;
//}
//
//
//*/