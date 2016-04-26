package com.bit.async.tasks;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.LinearLayout;

import com.bit.client.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by gfurlano on 2/26/16.
 */

public class GetAsynkTasks extends AsyncTask<String, Void, String> {

    String result = null;
    private Context context;
    private String remoteURL;
    LinearLayout linlaHeaderProgress;

    public GetAsynkTasks(View v, Activity activity, String remoteURL) {
        this.linlaHeaderProgress = (LinearLayout) v.findViewById(R.id.linlaHeaderProgress);
        this.context = activity.getApplicationContext();
        this.remoteURL = remoteURL;
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
        return directSend(remoteURL);
    }

    @Override
    protected void onPreExecute() {
        if(linlaHeaderProgress != null){
            linlaHeaderProgress.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if(linlaHeaderProgress != null) {
            linlaHeaderProgress.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {}

    final String getAplicantesCursos(String remoteURL){
        BufferedReader inStream = null;
        try{
            HttpClient httpClient = new DefaultHttpClient();
            String url = context.getString(R.string.server);
            HttpGet httpGet = new HttpGet(url + remoteURL);
            httpGet.addHeader(BasicScheme.authenticate(new UsernamePasswordCredentials("1-1", "password"), "UTF-8", false));
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity responseEntity = httpResponse.getEntity();
            inStream = new BufferedReader(new InputStreamReader(responseEntity.getContent(), "UTF-8"));
            result = inStream.readLine();
            return result;
        }catch(Exception ex){
            return null;
        }

    }

    public String directSend(String remoteURL){
        return getAplicantesCursos(remoteURL);
    }
}
