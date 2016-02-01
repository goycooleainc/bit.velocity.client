package com.bit.vending;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bit.async.tasks.GetAvataresTask;
import com.bit.async.tasks.GetEstadoCuentaTask;
import com.bit.async.tasks.GetProductosFromServerTask;
import com.bit.async.tasks.GetTransactionsTask;
import com.bit.audit.fragments.VendingMachineActivity;
import com.bit.client.R;
import com.bit.entities.EstadoCuenta;
import com.bit.entities.User;
import com.bit.singletons.UsersHashmapCollection;
import com.bit.singletons.VentaHashmapCollectionSingleton;
import com.bit.utils.OfflineUserManager;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class StartActivity extends Activity implements LoaderManager.LoaderCallbacks<List<User>>{

    String usuario,password,ip,privateInternalID;
    private TextView tx_user, tx_password;
	private ProgressBar progressbar;
	private LinearLayout login;
	int myProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		////
        setContentView(R.layout.activity_start);
		progressbar= (ProgressBar)findViewById(R.id.pbHeaderProgress);
		login = (LinearLayout)findViewById(R.id.login_view);
        //// Parametros Iniciales
        ip = "77.95.228.75";
        usuario = "1-1";
        password = "password";
        privateInternalID = new Date().getTime() + "";

		final ActionBar localActionBar = getActionBar();
		localActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		localActionBar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
		localActionBar.hide();

		getLoaderManager().initLoader(0, null, this).forceLoad();
	}


    public void start_app_clicked(View view)
    {
		UsersHashmapCollection.getInstance();
		this.tx_user = (TextView) findViewById(R.id.tx_user);
		this.tx_password = (TextView) findViewById(R.id.tx_password);
		String rut = this.tx_user.getText().toString();
		User user = new OfflineUserManager(getBaseContext()).authenticate(rut, this.tx_password.getText().toString());
		if (user == null) {
			error();
		} else if (user.getState() == 1) {

			VentaHashmapCollectionSingleton.getInstance().user = user;

			GetEstadoCuentaTask task_1 = new GetEstadoCuentaTask(getApplicationContext());
			GetAvataresTask task_2 = new GetAvataresTask(getApplicationContext());
			GetTransactionsTask task_3 = new GetTransactionsTask(getApplicationContext());
			GetProductosFromServerTask task_4 = new GetProductosFromServerTask(getApplicationContext());

			task_1.setIdUsuario(user.getIdUsuario());
			task_2.setIdUsuario(user.getIdUsuario());
			task_3.setIdUsuario(user.getIdUsuario());

			try {
				VentaHashmapCollectionSingleton.getInstance().estadoCuenta = (EstadoCuenta) task_1.execute(new Void[0]).get();
				VentaHashmapCollectionSingleton.getInstance().avatares = (List) task_2.execute(new Void[0]).get();
				VentaHashmapCollectionSingleton.getInstance().ventas = (List) task_3.execute(new Void[0]).get();
				VentaHashmapCollectionSingleton.getInstance().productos = (List) task_4.execute(new Void[0]).get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e2) {
				e2.printStackTrace();
			}
			Intent intent = new Intent(this, VendingMachineActivity.class);
			intent.putExtra("nombre", user.getNombre());
			intent.putExtra("rol", user.getNombreRol());
			intent.putExtra("id_rol", user.getIdRol());
			intent.putExtra("id_perfil", user.getIdProfile());
			intent.putExtra("perfil", user.getNombrePerfil());
			intent.putExtra("productora", user.getIdProductora());
			intent.putExtra("rut", rut);
			intent.putExtra("usuario", this.usuario);
			intent.putExtra("password", this.password);
			intent.putExtra("ip", this.ip);
			startActivity(intent);
		} else {
			bloqueado();
		}
    }
	/***
	 *
	 */
    public void error()
    {
    	AlertDialog ad = new AlertDialog.Builder(this).create();
		ad.setCancelable(false); // This blocks the 'BACK' button
		ad.setMessage("Rut No Permitido o no Encontrado.");
		ad.setButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();                    
			}
		});
		ad.show();
    }

	/***
	 *
	 */
	public void bloqueado()
	{
		AlertDialog ad = new AlertDialog.Builder(this).create();
		ad.setCancelable(false); // This blocks the 'BACK' button
		ad.setMessage("Usuario Bloqueado, Inactivo o Borrado");
		ad.setButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		ad.show();
	}
	/**
	 *
	 * @param id
	 * @param args
	 * @return
	 */
	@Override
	public Loader<List<User>> onCreateLoader(int id, Bundle args) {
		progressbar.setVisibility(View.VISIBLE);
		login.setVisibility(View.INVISIBLE);
		return new AssetLoader(this);
	}
	/**
	 *
	 * @param loader
	 * @param data
	 */
	@Override
	public void onLoadFinished(Loader<List<User>> loader, List<User> data) {
		login.setVisibility(View.VISIBLE);
		progressbar.setVisibility(View.GONE);
	}

	/**
	 *
	 * @param loader
	 */
	@Override
	public void onLoaderReset(Loader<List<User>> loader) {
		login.setVisibility(View.VISIBLE);
		progressbar.setVisibility(View.GONE);
	}
}

