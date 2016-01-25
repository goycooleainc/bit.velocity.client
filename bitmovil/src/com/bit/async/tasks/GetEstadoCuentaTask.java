package com.bit.async.tasks;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import com.bit.entities.EstadoCuenta;
import com.bit.entities.Venta;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

public class GetEstadoCuentaTask extends AsyncTask<Void, Void, EstadoCuenta> {
    public String idUsuario;
    String ip;
    String password;
    ProgressDialog progressDialog;
    String result;
    String usuario;

    /* renamed from: com.bit.async.tasks.GetEstadoCuentaTask.1 */
    class C01701 extends TypeToken<ArrayList<Venta>> {
        C01701() {
        }
    }

    public GetEstadoCuentaTask() {
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
        try {
            BufferedReader inStream = new BufferedReader(new InputStreamReader(new DefaultHttpClient().execute(new HttpGet("http://bit.goycooleainc.com/mobile/cuenta/estado/" + this.idUsuario)).getEntity().getContent(), HTTP.UTF_8));
            BufferedReader bufferedReader;
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

    protected EstadoCuenta doInBackground(Void... params) {
        Type listType = new C01701().getType();
        if (GetExecution() != null) {
            return (EstadoCuenta) new Gson().fromJson(GetExecution(), EstadoCuenta.class);
        }
        return null;
    }
}
