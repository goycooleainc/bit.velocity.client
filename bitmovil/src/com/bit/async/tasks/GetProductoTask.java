package com.bit.async.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.bit.client.R;
import com.bit.entities.Productos;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class GetProductoTask extends AsyncTask<Void, Void, ArrayList<Productos>> {
	public String idUsuario;
	String ip;
	String password;
	ProgressDialog progressDialog;
	String result;
	String usuario;
	private Context context;

	/* renamed from: com.bit.async.tasks.GetTransactionsTask.1 */
	class Producto extends TypeToken<ArrayList<Productos>> {
		Producto() {
		}
	}

	public GetProductoTask(Context context) {
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
		BufferedReader bufferedReader;
		try {
			String url = context.getString(R.string.server) + context.getString(R.string.getProductos);
			BufferedReader inStream = new BufferedReader(new InputStreamReader(new DefaultHttpClient().execute(new HttpGet(url + this.idUsuario)).getEntity().getContent(), HTTP.UTF_8));
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

	protected ArrayList<Productos> doInBackground(Void... params) {
		Type listType = new Producto().getType();
		if (GetReports() != null) {
			return (ArrayList) new Gson().fromJson(GetReports(), listType);
		}
		return null;
	}
}