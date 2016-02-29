package com.bit.async.tasks;

import android.app.ProgressDialog;
import android.content.Context;
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
    private Context context;

    /* renamed from: com.bit.async.tasks.GetAvataresTask.1 */
    class C01691 extends TypeToken<ArrayList<Avatar>> {
        C01691() {
        }
    }

    public GetAvataresTask(Context context) {
        this.result = null;
        this.ip = "78.41.206.33";
        this.usuario = "1-1";
        this.password = "password";
        this.context = context;
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
            String server = context.getString(R.string.server);
            BufferedReader inStream = new BufferedReader(new InputStreamReader(new DefaultHttpClient().execute(new HttpGet(server + "/mobile/avatares/" + this.idUsuario)).getEntity().getContent(), HTTP.UTF_8));
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
