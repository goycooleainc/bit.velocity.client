package com.bit.async.tasks;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.LinearLayout;

import com.bit.client.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by gfurlano on 2/26/16.
 */

public class PostAsynkTasks extends AsyncTask<String, Void, String> {


    String usuario = "1-1";
    String password = "password";
    ProgressDialog progressDialog;
    private Activity activity;
    String result = null;
    private Context context;
    LinearLayout linlaHeaderProgress;
    AlertDialog.Builder bld;
    private String remoteUrl;

    private Dialog dialog;

    public PostAsynkTasks(View v, Activity activity, AlertDialog.Builder bld, String remoteUrl)
    {
        this.activity = activity;
        this.linlaHeaderProgress = (LinearLayout) activity.findViewById(R.id.linlaHeaderProgress);
        this.context = activity.getApplicationContext();
        this.bld = bld;
        this.remoteUrl = remoteUrl;
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
        return directSend(remoteUrl);
    }

    @Override
    protected void onPreExecute() {
        if(linlaHeaderProgress != null) {
            linlaHeaderProgress.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onPostExecute(String result) {
        bld.setMessage(result);
        bld.setNeutralButton("OK", null);
        bld.create().show();

//        ((MainActivity) activity).refrescar();
        if(linlaHeaderProgress != null) {
            linlaHeaderProgress.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {}

    final String getCursos(String remoteUrl){
        BufferedReader inStream = null;
        try{
            HttpClient httpClient = new DefaultHttpClient();
            String url = context.getString(R.string.server);
            HttpPost httpRequest = new HttpPost(url + remoteUrl);

            httpRequest.addHeader(BasicScheme.authenticate(new UsernamePasswordCredentials("1-1", "password"), "UTF-8", false));
            httpRequest.setHeader("Accept", "application/json");
            httpRequest.setHeader("Content-Type", "application/json");
            httpRequest.setEntity(new StringEntity(DATA, HTTP.UTF_8));

            HttpResponse httpResponse = httpClient.execute(httpRequest);
            HttpEntity responseEntity = httpResponse.getEntity();
            inStream = new BufferedReader(new InputStreamReader(responseEntity.getContent(), "UTF-8"));
            result = inStream.readLine();
            return result;
        }catch(Exception ex){
            return null;
        }
    }

    public String directSend(String remoteUrl){
        return getCursos(remoteUrl);
    }
}