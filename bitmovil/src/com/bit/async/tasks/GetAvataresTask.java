package com.bit.async.tasks;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.os.AsyncTask;

import com.bit.client.R;
import com.bit.entities.Avatar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

public class GetAvataresTask extends AsyncTask<Void, Void, ArrayList<Avatar>> {
    public String idUsuario;
    String ip;
    String password;
    ProgressDialog progressDialog;
    String result;
    String usuario;

    /* renamed from: com.bit.async.tasks.GetAvataresTask.1 */
    class C01691 extends TypeToken<ArrayList<Avatar>> {
        C01691() {
        }
    }

    public GetAvataresTask() {
        this.result = null;
        this.ip = "78.41.206.33";
        this.usuario = "1-1";
        this.password = "password";
    }

    public String getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    final String GetExecution() {
        BufferedReader bufferedReader;
        try {
//            BufferedReader inStream = new BufferedReader(new InputStreamReader(new DefaultHttpClient().execute(new HttpGet("http://192.168.4.100:8080/mobile/avatares/" + this.idUsuario)).getEntity().getContent(), HTTP.UTF_8));
            BufferedReader inStream = new BufferedReader(new InputStreamReader(new DefaultHttpClient().execute(new HttpGet("http://bit.goycooleainc.com/mobile/avatares/" + this.idUsuario)).getEntity().getContent(), HTTP.UTF_8));
            try {
                this.result = inStream.readLine();
                bufferedReader = inStream;
                return this.result;
            } catch (Exception e) {
                bufferedReader = inStream;
                return null;
            }
        } catch (Exception e2) {
            return null;
        }
    }

    protected ArrayList<Avatar> doInBackground(Void... params) {
        Type listType = new C01691().getType();
        if (GetExecution() != null) {
            return (ArrayList) new Gson().fromJson(GetExecution(), listType);
        }
        return null;
    }
}
