package com.bit.async.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.bit.client.R;
import com.bit.entities.Venta;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

public class GetVentasTask extends AsyncTask<Void, Void, ArrayList<Venta>> {
	public String idUsuario;
	String ip;
	String password;
	ProgressDialog progressDialog;
	String result;
	String usuario;
	private Context context;

	/* renamed from: com.bit.async.tasks.GetTransactionsTask.1 */
	class C01741 extends TypeToken<ArrayList<Venta>> {
		C01741() {
		}
	}

	public GetVentasTask(Context context) {
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

	final String GetReports() {
		try {
			HttpClient httpClient = new DefaultHttpClient();
			String url = context.getString(R.string.server);
			HttpGet httpGet = new HttpGet(url + "/mobile/ventas/usuario/" + this.idUsuario);
			httpGet.addHeader(BasicScheme.authenticate(new UsernamePasswordCredentials("1-1", "password"), "UTF-8", false));
			HttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity responseEntity = httpResponse.getEntity();
			BufferedReader inStream = new BufferedReader(new InputStreamReader(responseEntity.getContent(), "UTF-8"));
			result = inStream.readLine();
			return this.result;
		} catch (Exception e) {
			return null;
		}
	}

	protected ArrayList<Venta> doInBackground(Void... params) {
		Type listType = new C01741().getType();
		if (GetReports() != null) {
			return (ArrayList) new Gson().fromJson(GetReports(), listType);
		}
		return null;
	}
}
