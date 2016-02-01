package com.bit.async.tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.bit.client.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by goycolea on 15-12-14.
 */
public class DirectNewTransaction extends AsyncTask<String, Void, String> {

    String ip = "78.41.206.33";
    String usuario = "1-1";
    String password = "password";
    private Context context;

    public DirectNewTransaction(Context context) {
        this.context = context;
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
        return directSend(this.DATA);
    }

    @Override
    protected void onPostExecute(String result) {

    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected void onProgressUpdate(Void... values) {}

    public String directSend(String DATA){

        String result = "";
        String server = context.getString(R.string.server);
        String url = server + "/mobile/transaction/send";
        try
        {

            DefaultHttpClient httpclient = new DefaultHttpClient();

            HttpPost httpRequest = new HttpPost(url);
            BasicHttpContext localContext = new BasicHttpContext();

            BasicScheme basicAuth = new BasicScheme();
            localContext.setAttribute("preemptive-auth", basicAuth);

            httpRequest.setHeader("Accept", "application/json");
            httpRequest.setHeader("Content-Type", "application/json");
            httpRequest.setEntity(new StringEntity(DATA, HTTP.UTF_8));

            // authentication
            //httpclient.getCredentialsProvider().setCredentials(AuthScope.ANY,new UsernamePasswordCredentials("1-1", "password"));

            HttpResponse response = httpclient.execute(httpRequest);
            switch (response.getStatusLine().getStatusCode())
            {
                case 401:
                    result = "401";
                    break;
                case 404:
                    result = "404";
                    break;
                case 200:
                    BufferedReader inStream = null;
                    inStream = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
                    result = inStream.readLine();
                    break;
                default:
                    result = "ERR";
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
