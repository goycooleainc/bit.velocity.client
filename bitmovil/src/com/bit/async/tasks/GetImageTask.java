package com.bit.async.tasks;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import java.io.InputStream;
import java.net.URL;

public class GetImageTask extends AsyncTask<Void, Void, Drawable> {
    String ip;
    String password;
    String result;
    String url;
    String usuario;

    public GetImageTask() {
        this.result = null;
        this.ip = "78.41.206.33";
        this.usuario = "1-1";
        this.password = "password";
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    protected Drawable doInBackground(Void... params) {
        try {
            return Drawable.createFromStream((InputStream) new URL(this.url).getContent(), "src name");
        } catch (Exception e) {
            return null;
        }
    }
}
