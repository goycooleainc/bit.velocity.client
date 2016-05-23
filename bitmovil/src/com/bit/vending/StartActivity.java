package com.bit.vending;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.LoaderManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bit.async.tasks.GetMobileBokerTask;
import com.bit.async.tasks.GetUsersFromServerTask;
import com.bit.async.tasks.PostAsynkTasks;
import com.bit.audit.fragments.MainActivity;
import com.bit.client.R;
import com.bit.entities.Avatar;
import com.bit.entities.EstadoCuenta;
import com.bit.entities.MobileBoker;
import com.bit.entities.User;
import com.bit.singletons.CacheCollectionSingleton;
import com.bit.singletons.TransactionHashmapCollectionSingleton;
import com.bit.singletons.UsersHashmapCollection;
import com.bit.utils.CheckEmail;
import com.bit.utils.OfflineUserManager;
import com.google.gson.Gson;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class StartActivity extends Activity implements LoaderManager.LoaderCallbacks<List<User>>{

	private AutoCompleteTextView customItemName;
    String usuario,password,ip,privateInternalID;
    private AutoCompleteTextView tx_user;
	private EditText tx_password;
	private ProgressBar progressbar;
	private LinearLayout login;
	int myProgress;
    static Button btn_ok;
    static Button btn_close;
    public Activity activity;
    Intent intent;
	private View view;

	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start);
		progressbar= (ProgressBar)findViewById(R.id.pbHeaderProgress);
		login = (LinearLayout)findViewById(R.id.login_view);
        //// Parametros Iniciales
        ip = "77.95.228.75";
        usuario = "1-1";
        password = "password";
        privateInternalID = new Date().getTime() + "";

        intent = getIntent();
        activity = this;

		final ActionBar localActionBar = getActionBar();
		localActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		/*localActionBar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);*/
		localActionBar.hide();

		getLoaderManager().initLoader(0, null, this).forceLoad();
	}


    public void start_app_clicked(View view) {
		UsersHashmapCollection.getInstance();
		tx_user = (AutoCompleteTextView) findViewById(R.id.tx_user);
		this.tx_password = (EditText) findViewById(R.id.tx_password);
		String rut = this.tx_user.getText().toString();
		User user = new OfflineUserManager(getBaseContext()).authenticate(rut, this.tx_password.getText().toString());
		if (user == null) {
			error();
		} else if (user.getState() == 1) {

			TransactionHashmapCollectionSingleton.getInstance().user = user;

			GetMobileBokerTask task = new GetMobileBokerTask(getApplicationContext());

			task.setIdUsuario(user.getIdUsuario());

			try {

				MobileBoker mobileBoker = (MobileBoker) task.execute(new Void[0]).get();

				TransactionHashmapCollectionSingleton.getInstance().estadoCuenta = (EstadoCuenta) mobileBoker.getEstadoCuenta();
				TransactionHashmapCollectionSingleton.getInstance().avatares = (List) mobileBoker.getAvatares();
				for(Avatar a :TransactionHashmapCollectionSingleton.getInstance().avatares){
					if(a.getEstado() == 1){
						TransactionHashmapCollectionSingleton.getInstance().avatar = a;
						break;
					}
				}
				TransactionHashmapCollectionSingleton.getInstance().transacciones = (List) mobileBoker.getTransacciones();
				TransactionHashmapCollectionSingleton.getInstance().productos = (List) mobileBoker.getProductosBit();
				TransactionHashmapCollectionSingleton.getInstance().cortesiaCombos = (List) mobileBoker.getCortesiaCombo();
				TransactionHashmapCollectionSingleton.getInstance().cortesiaEvento = (List) mobileBoker.getCortesiaEvento();
				TransactionHashmapCollectionSingleton.getInstance().ventaDetalle = (List) mobileBoker.getVentasDetalle();
                //Son los prodcutos fisicos a vender
				TransactionHashmapCollectionSingleton.getInstance().producto = (List) mobileBoker.getProductos();
				TransactionHashmapCollectionSingleton.getInstance().mainActivity = this;

			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e2) {
				e2.printStackTrace();
			}
			Intent intent = new Intent(getApplicationContext(), MainActivity.class);
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

			customItemName = tx_user;
			SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
			editor.putString("customitemname", customItemName.getText().toString());
			editor.commit();

			startActivity(intent);
            finish();
		} else {
			bloqueado();
		}
    }

    public void new_user(View view)
    {
		UsersHashmapCollection.getInstance();

        final Dialog dialog = new Dialog(view.getContext());
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.modal_nuevo_usuario_method);

        btn_close = (Button) dialog.findViewById(R.id.dialogButtonCancel);
        btn_ok = (Button) dialog.findViewById(R.id.dialogButtonOK);

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
			}
		});

		btn_ok.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder bld = new AlertDialog.Builder(activity);

				TextView rut = (TextView) dialog.findViewById(R.id.rutText);
				TextView password = (TextView) dialog.findViewById(R.id.passwordText);
				TextView email = (TextView) dialog.findViewById(R.id.emailText);

				if (!email.getText().toString().matches("") && CheckEmail.isEmailValid(email.getText().toString()) &&
						!rut.getText().toString().matches("") &&
						!password.getText().toString().matches("")) {

					User user = new User();
					user.setRut(rut.getText().toString());
					user.setPassword(password.getText().toString());
					user.setEmail(email.getText().toString());

					String remoteURL = activity.getApplicationContext().getString(R.string.newUser);
					PostAsynkTasks task = new PostAsynkTasks(v, activity, bld, remoteURL);
					task.setDATA(new Gson().toJson(user));
					task.execute();

				} else {
					bld.setMessage("Todos los campos son requeridos o e-mail tiene un formato incorrecto!!");
					bld.setNeutralButton("OK", null);
					bld.create().show();
				}

				dialog.dismiss();
				refreshUsers();
            }
        });

        /*dialog.setTitle("REGISTRO");*/
        dialog.show();
    }

	public void olvide_pass(View view)
	{
		UsersHashmapCollection.getInstance();

		final Dialog dialog = new Dialog(view.getContext());
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.modal_olvide_pass_method);

		btn_close = (Button) dialog.findViewById(R.id.dialogButtonCancel);
		btn_ok = (Button) dialog.findViewById(R.id.dialogButtonOK);

		btn_close.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		btn_ok.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder bld = new AlertDialog.Builder(activity);

				TextView rut = (TextView) dialog.findViewById(R.id.rutText);

				if (!rut.getText().toString().matches("")) {

					User user = new User();
					user.setRut(rut.getText().toString());

					String remoteURL = activity.getApplicationContext().getString(R.string.recuperarUser);
					PostAsynkTasks task = new PostAsynkTasks(v, activity, bld, remoteURL);
					task.setDATA(new Gson().toJson(user));
					task.execute();

				} else {
					bld.setMessage("RUT es un campo requerido!!");
					bld.setNeutralButton("OK", null);
					bld.create().show();
				}

				dialog.dismiss();
			}
		});

		dialog.show();
	}

    public List<User> refreshUsers(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return null;
        }
        GetUsersFromServerTask users_mediator = new GetUsersFromServerTask(getApplicationContext());
        List<User> list = null;
        try {
            list = (List) users_mediator.execute(new Void[0]).get();
            CacheCollectionSingleton.getInstance(getApplicationContext()).setInMemmoryUsers(new Gson().toJson((Object) list));
            return list;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return list;
        } catch (ExecutionException e2) {
            e2.printStackTrace();
            return list;
        }
    }

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

	@Override
	protected void onStart() {
		super.onStart();

		setContentView(R.layout.activity_start);
		customItemName = (AutoCompleteTextView) findViewById(R.id.tx_user);

		EditText password = (EditText) findViewById(R.id.tx_password);
		password.setTypeface(Typeface.DEFAULT);
		password.setTransformationMethod(new PasswordTransformationMethod());

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, updatedropdown(5));
		customItemName.setAdapter(adapter);
	}

	public String[] updatedropdown(int listlength){
		boolean itemalreadyinlist=false;
		SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();

		for(int i = 0; i < listlength; i++){
			if (getPreferences(MODE_PRIVATE).getString("customitemname","").equals(getPreferences(MODE_PRIVATE).getString("recentlistitem"+String.valueOf(i),""))){
				itemalreadyinlist=true;
				for(int j = i; j>0; j--){
					editor.putString("recentlistitem"+String.valueOf(j),getPreferences(MODE_PRIVATE).getString("recentlistitem"+String.valueOf(j-1),""));
				}
				editor.putString("recentlistitem0",getPreferences(MODE_PRIVATE).getString("customitemname",""));
				editor.commit();
				break;
			}
		}

		if( !itemalreadyinlist){
			for(int i = listlength-1; i>0; i--){
				editor.putString("recentlistitem"+String.valueOf(i),getPreferences(MODE_PRIVATE).getString("recentlistitem"+String.valueOf(i-1),""));
			}
			editor.putString("recentlistitem0", getPreferences(MODE_PRIVATE).getString("customitemname", ""));
			editor.commit();
		}


		int count = 0;
		for(int i=0 ; i < listlength; i++){
			if(!getPreferences(MODE_PRIVATE).getString("recentlistitem" + String.valueOf(i), "").isEmpty()) {
				count += 1;
			}
		}
		String[] recentlist = new String[count];
		for(int i=0 ; i < count; i++) {
			recentlist[i] = getPreferences(MODE_PRIVATE).getString("recentlistitem" + String.valueOf(i), "");
		}
		return recentlist;
	}
}

