package com.bit.async.tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.bit.client.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.james.mime4j.field.FieldName;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class NewAvatarTask extends AsyncTask<String, Void, String> {
    private String DATA;
    String ip;
    String password;
    String usuario;
    private Context context;
    private String status = null;

    public NewAvatarTask(Context context) {
        this.ip = "78.41.206.33";
        this.usuario = "1-1";
        this.password = "password";
        this.context = context;
    }

    public String getDATA() {
        return this.DATA;
    }

    public void setDATA(String DATA) {
        this.DATA = DATA;
    }

    public String checkStatus(){
        return status;
    }

    protected String doInBackground(String... params) {
        BufferedReader inStream = null;
        String result = "NACK";
        String server = context.getString(R.string.server);
        String url = server + "/mobile/avatares/new/create";
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpRequest = new HttpPost(url);
            httpRequest.setHeader(HttpHeaders.ACCEPT, "application/json");
            httpRequest.setHeader(FieldName.CONTENT_TYPE, "application/json");
            httpRequest.setEntity(new StringEntity(this.DATA, HTTP.UTF_8));
            HttpResponse response = httpClient.execute((HttpUriRequest) httpRequest);

            HttpEntity responseEntity = response.getEntity();
            inStream = new BufferedReader(new InputStreamReader(responseEntity.getContent(), "UTF-8"));
            result = inStream.readLine();

            if(result == null){
                return status = "Error, Avatar duplicado";
            }
            status = "Exito";

            switch (response.getStatusLine().getStatusCode()) {
                case HttpStatus.SC_OK /*200*/:
                    return new BufferedReader(new InputStreamReader(response.getEntity().getContent(), HTTP.UTF_8)).readLine();
                case HttpStatus.SC_UNAUTHORIZED /*401*/:
                    return "Usuario no Existe";
                case HttpStatus.SC_NOT_FOUND /*404*/:
                    return "No se encuentra el servidor";
                default:
                    return "Error desconocido, contacte a servicio tecnico.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
    }

    protected void onPostExecute(String result) {
    }

    protected void onPreExecute() {
    }

    protected void onProgressUpdate(Void... values) {
    }
}
