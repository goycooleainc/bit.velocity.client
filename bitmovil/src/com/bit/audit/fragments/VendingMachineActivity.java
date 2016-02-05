package com.bit.audit.fragments;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import com.bit.adapters.*;
import com.bit.async.tasks.*;
import com.bit.client.R;
import com.bit.entities.*;
import com.bit.singletons.CacheCollectionSingleton;
import com.bit.singletons.ProductHashmapCollectionSingleton;
import com.bit.singletons.TransactionHashmapCollectionSingleton;
import com.bit.singletons.VentaHashmapCollectionSingleton;
import com.bit.utils.CheckEmail;
import com.bit.utils.StoreManager;
import com.bit.vending.SettingsActivity;
import com.bit.vending.StartActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.james.mime4j.util.CharsetUtil;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;


public class VendingMachineActivity extends FragmentActivity implements ActionBar.TabListener {

	public static String amount;
	static Productos auditoria;
	static ImageButton btnAccessControl;
	static ImageButton btnOk;
	static ImageButton btnStats;
	static ImageButton btnVending;
	static Button btn_close;
	static ImageButton btn_discard;
	static ImageButton btn_save;
	static String cargo;
	static AutoCompleteTextView cliente;
	static AutoCompleteTextView conductor;
	static List<String> cone;
	static AutoCompleteTextView faena;
	static String filePath;
	static HitosAuditorias hitos_auditoria;
	static String id;
	static int id_perfil;
	static int id_productora;
	static int id_rol;
	static ActionBar localActionBar;
	static ListView lv1;
	static ListView lv2;
	static ListView lv3;
	static ViewPager mViewPager;
	static String nombre_usuario;
	static ClientesList obj_clientes;
	static AutoCompleteTextView patente;
	static String productora;
	static String rol;
	static String rut;
	static Spinner spinner_section;
	static boolean startedAuditory;
	static int tipo;
	static TextView txBalance;
	public static EditText txTag;
    private static Button btn_ok;
    String emailCliente;
	String emailEmpresa;
	String empresa;
	String ip;
	String json;
	public SectionsPagerAdapter mSectionsPagerAdapter;
	String os;
	String password;
	String usuario;

	/**
	 * Main Create Bundle for the Fragments
	 *
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/// require action bar
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		/// set content view
		setContentView(R.layout.activity_main);
		/// intent
		Intent intent = getIntent();
		// Receiving the Data
		VendingMachineActivity.nombre_usuario = intent.getStringExtra("nombre");
		VendingMachineActivity.rut = intent.getStringExtra("rut");
		VendingMachineActivity.productora = intent.getStringExtra("productora");
		VendingMachineActivity.cargo = intent.getStringExtra("cargo");
		VendingMachineActivity.id_rol = intent.getIntExtra("id_rol", 0);
        VendingMachineActivity.id_perfil = intent.getIntExtra("id_perfil",0);



        /// Hashmap Collection
		cone = ProductHashmapCollectionSingleton.getInstance().loadSectionItems(0);
		///
		final ActionBar localActionBar = getActionBar();
		localActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		localActionBar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
		localActionBar.hide();

		/// GSON builder
		Gson gson = new GsonBuilder().serializeNulls().create();
		Type list = new TypeToken<ArrayList<Productos>>() {
		}.getType();
		//convert the json string back to object
		final List<Productos> obj = gson.fromJson(CacheCollectionSingleton.getInstance(getBaseContext()).getInMemmoryAuditories(), list);
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

		if (obj != null) {
			mSectionsPagerAdapter.setCount(6);
			mSectionsPagerAdapter.notifyDataSetChanged();
			// auditoria = obj;
		} else {
			mSectionsPagerAdapter.setCount(6);
			mSectionsPagerAdapter.notifyDataSetChanged();
		}

		mViewPager = ((ViewPager) findViewById(R.id.pager));
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int paramAnonymousInt) {
				localActionBar.setSelectedNavigationItem(paramAnonymousInt);
			}
		});

		for (int i = 0; ; i++) {
			if (i >= mSectionsPagerAdapter.getCount())
				return;
			localActionBar.addTab(localActionBar.newTab().setText(mSectionsPagerAdapter.getPageTitle(i)).setTabListener(this));
		}




	}

	//Boton new en ventana avatares
    public void OnNew(View v) {
        final Intent intentForActivitySettings = new Intent(this, SettingsActivity.class);

		final Dialog dialog = new Dialog(v.getContext());
		dialog.setContentView(R.layout.modal_avatar_method_select_type);

		VendingMachineActivity.btn_close = (Button) dialog.findViewById(R.id.dialogButtonCancel);
		VendingMachineActivity.btn_ok = (Button) dialog.findViewById(R.id.dialogButtonOK);

		final Spinner s = (Spinner) dialog.findViewById(R.id.spinner_state);
		s.setAdapter(new ArrayAdapter(dialog.getContext(), R.layout.spinner_item, new String[]{"NFC", "QR", "CODIGO DE BARRAS"}));

		btn_close.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		btn_ok.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				int position = s.getSelectedItemPosition();
				finish();
				switch(position) {
					case 0:
						startActivity(intentForActivitySettings);
					case 1:
						//QR
					case 2:
						//CODIGO DE BARRAS
				}
				dialog.dismiss();
			}
		});

		dialog.setTitle("AVATAR - BITMOVIL");
		dialog.show();

    }


	/**
	 * Logout
	 *
	 * @param paramView
	 */
	public void onLogout(View paramView) {
		final Dialog dialog = new Dialog(paramView.getContext());
		dialog.setContentView(R.layout.dialog_new_action);
		dialog.setTitle("BITMOVIL : MOBILE PAYMENTS");

		TextView texto = (TextView) dialog.findViewById(R.id.dialogText);
		texto.setText("Esta seguro de Salir y cerrar Sesion?");

		Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
		// if button is clicked, close the custom dialog
		dialogButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					dialog.dismiss();
					Intent mainIntent = new Intent(VendingMachineActivity.this, StartActivity.class);
					mainIntent.putExtra("usuario", "");
					mainIntent.putExtra("password", "");
					mainIntent.putExtra("ip", "");
					VendingMachineActivity.this.startActivity(mainIntent);
					VendingMachineActivity.this.finish();
				} catch (Exception ex) {
					ex.toString();
				}
			}
		});

		Button dialogCancelButton = (Button) dialog.findViewById(R.id.dialogButtonCancel);
		// if button is clicked, close the custom dialog
		dialogCancelButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				//Al no aceptar ...
			}
		});

		dialog.show();
	}




    public static class AvatarFragment extends Fragment implements OnRefreshListener {
		private AvataresItemListAdapter adapter;
		private SwipeRefreshLayout swipeLayout;

		/* renamed from: com.bit.audit.fragments.VendingMachineActivity.AvatarFragment.1 */
		class C00961 implements OnItemClickListener {
			final /* synthetic */ List val$final_list;

			/* renamed from: com.bit.audit.fragments.VendingMachineActivity.AvatarFragment.1.1 */
			class C00951 implements View.OnClickListener {
				final /* synthetic */ Dialog val$dialog;

				C00951(Dialog dialog) {
					this.val$dialog = dialog;
				}

				public void onClick(View v) {
					this.val$dialog.dismiss();
				}
			}

			C00961(List list) {
				this.val$final_list = list;
			}

			public void onItemClick(AdapterView<?> adapterView, View v, int position, long id) {
				final Dialog dialog = new Dialog(v.getContext());
				dialog.setContentView(R.layout.modal_avatar_method);

				VendingMachineActivity.btn_close = (Button) dialog.findViewById(R.id.dialogButtonCancel);
                VendingMachineActivity.btn_ok = (Button) dialog.findViewById(R.id.dialogButtonOK);

				final TextView descr = (TextView) dialog.findViewById(R.id.txDescr);
                final TextView coder = (TextView) dialog.findViewById(R.id.txCode);

				final Avatar obj = (Avatar) this.val$final_list.get(position);

                coder.setText(obj.getCodigo().toString());
				descr.setText(obj.getDescripcion().toString());

				final Spinner s = (Spinner) dialog.findViewById(R.id.spinner_state);
				s.setAdapter(new ArrayAdapter(dialog.getContext(), R.layout.spinner_item, new String[]{"ACTIVO", "INACTIVO"}));
				s.setSelection(obj.getEstado());

				VendingMachineActivity.btn_close.setOnClickListener(new C00951(dialog));

                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        obj.setCodigo(coder.getText().toString());
                        obj.setDescripcion(descr.getText().toString());
                        obj.setEstado(s.getSelectedItemPosition());
                        obj.setIdUser(Integer.parseInt(TransactionHashmapCollectionSingleton.getInstance().user.getIdUsuario()));

                        UpdateAvatarTask task = new UpdateAvatarTask(getActivity().getApplicationContext());
                        task.setDATA(new Gson().toJson(obj));
                        task.execute();

                        Toast.makeText(getActivity().getBaseContext(), "Avatar Actualizado............. [IN PROGRESS]", Toast.LENGTH_LONG).show();

                        dialog.dismiss();
                    }
                });

				dialog.setTitle("AVATAR - BITMOVIL");
				dialog.show();
			}
		}

		/* renamed from: com.bit.audit.fragments.VendingMachineActivity.AvatarFragment.2 */
		class C00972 implements Runnable {
			C00972() {
			}

			public void run() {
				AvatarFragment.this.swipeLayout.setRefreshing(false);
				try {
					List<Avatar> final_list;
                    TransactionHashmapCollectionSingleton.getInstance();
					if (TransactionHashmapCollectionSingleton.avatares != null) {
                        TransactionHashmapCollectionSingleton.getInstance();
						final_list = TransactionHashmapCollectionSingleton.avatares;
					} else {
						final_list = new ArrayList();
					}
					VendingMachineActivity.lv3.setAdapter(new AvataresItemListAdapter(AvatarFragment.this.getActivity().getBaseContext(), final_list));
					((AvataresItemListAdapter) VendingMachineActivity.lv3.getAdapter()).notifyDataSetChanged();
				} catch (Exception e) {
				}
			}
		}

		public void onResume() {
			List<Avatar> final_list;
			super.onResume();
			VendingMachineActivity.lv3 = (ListView) getActivity().findViewById(R.id.current_purchase_list);
            TransactionHashmapCollectionSingleton.getInstance();
			if (TransactionHashmapCollectionSingleton.avatares != null) {
                TransactionHashmapCollectionSingleton.getInstance();
				final_list = TransactionHashmapCollectionSingleton.avatares;
			} else {
				final_list = new ArrayList();
			}
			this.adapter = new AvataresItemListAdapter(getActivity().getBaseContext(), final_list);
			VendingMachineActivity.lv3.setAdapter(this.adapter);
			this.adapter.notifyDataSetChanged();
		}

		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_avatar_list, container, false);
			((TextView) rootView.findViewById(R.id.tx_nombre)).setText(VendingMachineActivity.nombre_usuario != null ? VendingMachineActivity.nombre_usuario.toString() : "");
			this.swipeLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);
			this.swipeLayout.setOnRefreshListener(this);
			this.swipeLayout.setColorScheme(17170459, 17170452, 17170456, 17170454);
			VendingMachineActivity.lv3 = (ListView) rootView.findViewById(R.id.current_purchase_list);
			try {
				List<Avatar> final_list;
                TransactionHashmapCollectionSingleton.getInstance();
				if (TransactionHashmapCollectionSingleton.avatares != null) {
                    TransactionHashmapCollectionSingleton.getInstance();
					final_list = TransactionHashmapCollectionSingleton.avatares;
                    TransactionHashmapCollectionSingleton.getInstance();
                    TransactionHashmapCollectionSingleton.avatar = (Avatar) final_list.get(0);
				} else {
					final_list = new ArrayList();
				}
				this.adapter = new AvataresItemListAdapter(getActivity().getBaseContext(), final_list);
				VendingMachineActivity.lv3.setAdapter(this.adapter);
				VendingMachineActivity.lv3.setAdapter(this.adapter);
				this.adapter.notifyDataSetChanged();
				VendingMachineActivity.lv3.setOnItemClickListener(new C00961(final_list));
			} catch (Exception ex) {
				ex.toString();
			}
			return rootView;
		}

		public void onRefresh() {
			new Handler().postDelayed(new C00972(), 5000);
		}
	}

	public static class EventosFragment extends Fragment implements OnRefreshListener  {
		static int _position;
		private EventosItemListAdapter adapter;
		private TransactionsItemListAdapter transaction_adapter;
		private int id;

        @Override
        public void onRefresh() {
            List<Eventos> final_list;
			GetTransactionsTask task_3 = new GetTransactionsTask(getActivity().getBaseContext());
			task_3.setIdUsuario(TransactionHashmapCollectionSingleton.getInstance().user.getIdUsuario());

			try {
				TransactionHashmapCollectionSingleton.getInstance().eventos = (List) task_3.execute(new Void[0]).get();
				if (TransactionHashmapCollectionSingleton.eventos != null) {
					TransactionHashmapCollectionSingleton.getInstance();
					final_list = TransactionHashmapCollectionSingleton.eventos;
				} else {
					final_list = new ArrayList();
				}
				this.adapter = new EventosItemListAdapter(getActivity().getBaseContext(), final_list);
				VendingMachineActivity.lv2.setAdapter(this.adapter);
				this.adapter.notifyDataSetChanged();
				VendingMachineActivity.lv2.setOnItemClickListener(new C00991(final_list));
			} catch (Exception ex) {
				ex.toString();
			}
        }

        public void refresTransacciones(){
			List<Transaccion> final_list;
			GetTransactionsTask task_3 = new GetTransactionsTask(getActivity().getBaseContext());
			task_3.setIdUsuario(TransactionHashmapCollectionSingleton.getInstance().user.getIdUsuario());

			try {
				TransactionHashmapCollectionSingleton.getInstance().transacciones = (List) task_3.execute(new Void[0]).get();
				TransactionHashmapCollectionSingleton.getInstance();
				if (TransactionHashmapCollectionSingleton.transacciones != null) {
					TransactionHashmapCollectionSingleton.getInstance();
					final_list = TransactionHashmapCollectionSingleton.transacciones;
				} else {
					final_list = new ArrayList();
				}
				this.transaction_adapter = new TransactionsItemListAdapter(getActivity().getBaseContext(), final_list);
				VendingMachineActivity.lv3.setAdapter(this.transaction_adapter);
				this.transaction_adapter.notifyDataSetChanged();
				VendingMachineActivity.lv3.setOnItemClickListener(new ShowModalTransaction(final_list, getActivity().getBaseContext()));
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
        }

        /* renamed from: com.bit.audit.fragments.VendingMachineActivity.EventosFragment.1 */
		class C00991 implements OnItemClickListener {
			final /* synthetic */ List val$final_list;

			/* renamed from: com.bit.audit.fragments.VendingMachineActivity.EventosFragment.1.1 */
			class C00981 implements View.OnClickListener {
				final /* synthetic */ Dialog val$dialog;

				C00981(Dialog dialog) {
					this.val$dialog = dialog;
				}

				public void onClick(View v) {
					this.val$dialog.dismiss();
				}
			}

			C00991(List list) {
				this.val$final_list = list;
			}

			public void onItemClick(AdapterView<?> adapterView, View v, int position, long id) {
				final Dialog dialog = new Dialog(v.getContext());
				dialog.setContentView(R.layout.modal_eventos_method);

				final NumberPicker numberPicker = (NumberPicker) dialog.findViewById(R.id.numberPicker);
				numberPicker.setMinValue(1);
				numberPicker.setMaxValue(100);
				numberPicker.setWrapSelectorWheel(true);

				VendingMachineActivity.btn_close = (Button) dialog.findViewById(R.id.dialogButtonCancel);
                VendingMachineActivity.btn_ok = (Button) dialog.findViewById(R.id.dialogButtonOK);

				ImageView imagen = (ImageView) dialog.findViewById(R.id.imageViewEvent);
				final Eventos obj = (Eventos) this.val$final_list.get(position);
				((TextView) dialog.findViewById(R.id.txDetalleEvento)).setText("$" + obj.getPrecio() + CharsetUtil.CRLF + obj.getNombre() + CharsetUtil.CRLF + obj.getDetalle().toString() + CharsetUtil.CRLF + obj.getFechaInicio());
				GetImageTask it = new GetImageTask();
				try {
					it.setUrl(v.getContext().getString(R.string.server) + "/dmz/multimedia/" + obj.getId() + "/type/1/" + obj.getId() + "-0");
					imagen.setImageDrawable((Drawable) it.execute(new Void[0]).get());
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e2) {
					e2.printStackTrace();
				}
				VendingMachineActivity.btn_close.setOnClickListener(new C00981(dialog));

                btn_ok.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        DecimalFormat df = new DecimalFormat("#,##0.00");
                        df.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ITALY));
                        df.format(new BigDecimal(TransactionHashmapCollectionSingleton.estadoCuenta.getSaldo()));

                        Double total = Double.valueOf(obj.getPrecio()) * numberPicker.getValue();
                        Double resto = Double.valueOf(TransactionHashmapCollectionSingleton.estadoCuenta.getSaldo()) - total;

                        Transaccion tx = new Transaccion();
                        String avatar = TransactionHashmapCollectionSingleton.avatar.getCodigo();
                        tx.setAvatar(avatar);
                        tx.setCantidad(String.valueOf(numberPicker.getValue()));
                        tx.setFecha("");
                        tx.setGps("0.0,0.0");
                        tx.setIdProducto(obj.getId());
                        tx.setIdProductora(obj.getIdProductora());
                        tx.setMetodoPago(0);
                        tx.setMoneda(0);
                        tx.setPublicKey("");
                        tx.setTotal(String.valueOf(total));
						tx.setIdEvento(obj.getId());

                        DirectNewTransaction task = new DirectNewTransaction(getActivity().getApplicationContext());
                        task.setDATA(new Gson().toJson(tx));
                        //task.directSend(new Gson().toJson(obj));
                        task.execute();

                        AlertDialog.Builder bld = new AlertDialog.Builder(getActivity());

                        //Refrescar fragment principal para actualizar salgo
                        if(resto >= 0) {
                            TransactionHashmapCollectionSingleton.estadoCuenta.setSaldo(String.valueOf(resto).toString());
                            bld.setMessage("Compra Hecha Con Exito !!");
                            //refresh list
							refresTransacciones();

                        }else{
                            bld.setMessage("Saldo insuficiente !!");
                        }

                        bld.setNeutralButton("OK", null);
                        Log.d("compra evento", "Showing alert dialog: " + obj.getNombre());
                        bld.create().show();

                        dialog.dismiss();
                    }
                });

				dialog.setTitle("EVENTO - BITMOVIL");
				dialog.show();
			}
		}

		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_events_list, container, false);
			((TextView) rootView.findViewById(R.id.tx_nombre)).setText(VendingMachineActivity.nombre_usuario != null ? VendingMachineActivity.nombre_usuario.toString() : "");
			VendingMachineActivity.lv2 = (ListView) rootView.findViewById(R.id.product_list);
			try {
				List<Eventos> final_list;
                TransactionHashmapCollectionSingleton.getInstance();
				if (TransactionHashmapCollectionSingleton.eventos != null) {
                    TransactionHashmapCollectionSingleton.getInstance();
					final_list = TransactionHashmapCollectionSingleton.eventos;
				} else {
					final_list = new ArrayList();
				}
				this.adapter = new EventosItemListAdapter(getActivity().getBaseContext(), final_list);
				VendingMachineActivity.lv2.setAdapter(this.adapter);
				this.adapter.notifyDataSetChanged();
				VendingMachineActivity.lv2.setOnItemClickListener(new C00991(final_list));
			} catch (Exception ex) {
				ex.toString();
			}
			return rootView;
		}
	}

	public static class MenuFragment extends Fragment {
		static int _position;
		private ArrayAdapter<String> adapter_items;
		private int id;

		/* renamed from: com.bit.audit.fragments.VendingMachineActivity.MenuFragment.1 */
		class C01001 implements View.OnClickListener {
			C01001() {
			}

			public void onClick(View v) {
				VendingMachineActivity.mViewPager.setCurrentItem(1, true);
			}
		}

		/* renamed from: com.bit.audit.fragments.VendingMachineActivity.MenuFragment.2 */
		class C01012 implements View.OnClickListener {
			C01012() {
			}

			public void onClick(View v) {
				VendingMachineActivity.mViewPager.setCurrentItem(2, true);
			}
		}

		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
			((TextView) rootView.findViewById(R.id.tx_nombre)).setText(VendingMachineActivity.nombre_usuario != null ? VendingMachineActivity.nombre_usuario.toString() : "");
			VendingMachineActivity.txBalance = (TextView) rootView.findViewById(R.id.txBalance);
			DecimalFormat df = new DecimalFormat("#,##0.00");
			df.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ITALY));
            TransactionHashmapCollectionSingleton.getInstance();
			VendingMachineActivity.txBalance.setText("$" + df.format(new BigDecimal(TransactionHashmapCollectionSingleton.estadoCuenta.getSaldo().toString())));
			VendingMachineActivity.btnVending = (ImageButton) rootView.findViewById(R.id.btnVendingMachine);
			try {
				VendingMachineActivity.btnAccessControl.setOnClickListener(new C01001());
				VendingMachineActivity.btnVending.setOnClickListener(new C01012());
			} catch (Exception ex) {
				ex.toString();
			}
			return rootView;
		}
	}

	/* renamed from: com.bit.audit.fragments.VendingMachineActivity.TransactionsFragment.1 */
	public static class ShowModalTransaction implements OnItemClickListener {
		final List final_list;
		final Context context;

		/* renamed from: com.bit.audit.fragments.VendingMachineActivity.TransactionsFragment.1.1 */
		class C01021 implements View.OnClickListener {
			final Dialog val$dialog;

			C01021(Dialog dialog) {
				this.val$dialog = dialog;
			}

			public void onClick(View v) {
				this.val$dialog.dismiss();
			}
		}

		public ShowModalTransaction(List list, Context context) {
			this.final_list = list;
			this.context = context;
		}

		public void onItemClick(AdapterView<?> adapterView, View v, int position, long id) {
			Dialog dialog = new Dialog(v.getContext());
			dialog.setContentView(R.layout.modal_transaction_method);
			VendingMachineActivity.btn_close = (Button) dialog.findViewById(R.id.dialogButtonCancel);
			TextView total = (TextView) dialog.findViewById(R.id.txTotal);
			TextView producto = (TextView) dialog.findViewById(R.id.txProducto);
			TextView mercante = (TextView) dialog.findViewById(R.id.txMercante);
			TextView fecha = (TextView) dialog.findViewById(R.id.txFecha);
			Transaccion obj = (Transaccion) this.final_list.get(position);
			((TextView) dialog.findViewById(R.id.txCode)).setText("Avatar [" + obj.getAvatar().toString() + "]");
			total.setText("Total $" + obj.getTotal().toString());
			producto.setText("ID Transaccion [" + String.valueOf(obj.getIdTransaccion()) + "]");
			String evento = "ERROR";
			TransactionHashmapCollectionSingleton.getInstance();
			for (Eventos parent : TransactionHashmapCollectionSingleton.eventos) {
				if (parent.getId() == obj.getIdEvento()) {
					evento = parent.getNombre();
				}
			}
			mercante.setText("[" + evento + "]");
			fecha.setText("Fecha " + obj.getFecha().toString());
			VendingMachineActivity.btn_close.setOnClickListener(new C01021(dialog));
			dialog.setTitle("TRANSACCION COMPRA");
			dialog.show();
		}
	}

	public static class TransactionsFragment extends Fragment implements OnRefreshListener{
		static int _position;
		private TransactionsItemListAdapter adapter;
		private int id;

        @Override
        public void onRefresh() {
            List<Transaccion> final_list;
            GetTransactionsTask task_3 = new GetTransactionsTask(getActivity().getBaseContext());
            task_3.setIdUsuario(TransactionHashmapCollectionSingleton.getInstance().user.getIdUsuario());

            try {
                TransactionHashmapCollectionSingleton.getInstance().transacciones = (List) task_3.execute(new Void[0]).get();
                TransactionHashmapCollectionSingleton.getInstance();
                if (TransactionHashmapCollectionSingleton.transacciones != null) {
                    TransactionHashmapCollectionSingleton.getInstance();
                    final_list = TransactionHashmapCollectionSingleton.transacciones;
                } else {
                    final_list = new ArrayList();
                }
                this.adapter = new TransactionsItemListAdapter(getActivity().getBaseContext(), final_list);
                VendingMachineActivity.lv3.setAdapter(this.adapter);
                this.adapter.notifyDataSetChanged();
                VendingMachineActivity.lv3.setOnItemClickListener(new ShowModalTransaction(final_list, getActivity().getBaseContext()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_transactions_list, container, false);
			((TextView) rootView.findViewById(R.id.tx_nombre)).setText(VendingMachineActivity.nombre_usuario != null ? VendingMachineActivity.nombre_usuario.toString() : "");
			VendingMachineActivity.lv3 = (ListView) rootView.findViewById(R.id.product_list);
			try {
				List<Transaccion> final_list;
                TransactionHashmapCollectionSingleton.getInstance();
				if (TransactionHashmapCollectionSingleton.transacciones != null) {
                    TransactionHashmapCollectionSingleton.getInstance();
					final_list = TransactionHashmapCollectionSingleton.transacciones;
				} else {
					final_list = new ArrayList();
				}
				this.adapter = new TransactionsItemListAdapter(getActivity().getBaseContext(), final_list);
				VendingMachineActivity.lv3.setAdapter(this.adapter);
				this.adapter.notifyDataSetChanged();
				VendingMachineActivity.lv3.setOnItemClickListener(new ShowModalTransaction(final_list, getActivity().getBaseContext()));
			} catch (Exception ex) {
				ex.toString();
			}
			return rootView;
		}
	}

	public static class VendingFragment extends Fragment  implements OnRefreshListener{
		static int _position;
		private int id;
        StoreManager activity;

        @Override
        public void onRefresh() {
            try {
                List<Productos> list = TransactionHashmapCollectionSingleton.getInstance().productos;
                ProductosBitItemListAdapter adapter = new ProductosBitItemListAdapter(getActivity().getBaseContext(), list);
                VendingMachineActivity.lv2.setAdapter(adapter);
                VendingMachineActivity.lv2.setOnItemClickListener(new C01042(list));
                adapter.notifyDataSetChanged();


            } catch (Exception ex) {
                ex.toString();
            }
        }

        /* renamed from: com.bit.audit.fragments.VendingMachineActivity.VendingFragment.2 */
		class C01042 implements OnItemClickListener {
			final /* synthetic */ List val$list;

			C01042(List list) {
				this.val$list = list;
			}

			public void onItemClick(AdapterView<?> adapterView, View v, int position, long id) {

				Productos obj = (Productos) this.val$list.get(position);
                TransactionHashmapCollectionSingleton.getInstance();
				List<Productos> productos = TransactionHashmapCollectionSingleton.productos;

				if (productos != null) {
                    activity.purchase(obj.getCodigo());
				} else {
                    Toast.makeText(VendingFragment.this.getActivity(), "Producto No Reconocido, Intenta de Nuevo!! =)", Toast.LENGTH_LONG).show();
				}
			}
		}

		/* renamed from: com.bit.audit.fragments.VendingMachineActivity.VendingFragment.1 */
		class C01781 extends TypeToken<ArrayList<Productos>> {
			C01781() {
			}
		}

		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_product_list, container, false);
			((TextView) rootView.findViewById(R.id.tx_nombre)).setText(VendingMachineActivity.nombre_usuario != null ? VendingMachineActivity.nombre_usuario.toString() : "");
			VendingMachineActivity.lv2 = (ListView) rootView.findViewById(R.id.product_list);

            //Activity au = getActivity();
            activity = new StoreManager(TransactionHashmapCollectionSingleton.getInstance().mainActivity);
			try {
				List<Productos> list = TransactionHashmapCollectionSingleton.getInstance().productos;
				VendingMachineActivity.lv2.setAdapter(new ProductosBitItemListAdapter(rootView.getContext(), list));
				VendingMachineActivity.lv2.setOnItemClickListener(new C01042(list));

			} catch (Exception ex) {
				ex.toString();
			}
			return rootView;
		}
	}

	public static class MiEspacioFragment extends Fragment implements OnRefreshListener{
		static int _position;
		private VentasItemListAdapter adapter;
		private int id;
		private Venta obj;

		class ShowListVenta implements OnItemClickListener {
			final List final_list;

			class ShowModalVenta implements View.OnClickListener {
				final Dialog val$dialog;

				ShowModalVenta(Dialog dialog) {
					this.val$dialog = dialog;
				}

				public void onClick(View v) {
					this.val$dialog.dismiss();
				}
			}

			public ShowListVenta(List list) {
				this.final_list = list;
			}

			public void onItemClick(AdapterView<?> adapterView, View v, int position, long id) {
				final Dialog dialog = new Dialog(v.getContext());
				dialog.setContentView(R.layout.modal_compartir_evento_method);

				VendingMachineActivity.btn_close = (Button) dialog.findViewById(R.id.dialogButtonCancel);
				VendingMachineActivity.btn_ok = (Button) dialog.findViewById(R.id.dialogButtonOK);

				obj = (Venta) this.final_list.get(_position);
				final int idVenta = obj.getId();
				final int cantidadParaEnviar = obj.getCantidadParaEnviar();

				VendingMachineActivity.btn_close.setOnClickListener(new ShowModalVenta(dialog));

				btn_ok.setOnClickListener(new View.OnClickListener(){

					@Override
					public void onClick(View v) {
						AlertDialog.Builder bld = new AlertDialog.Builder(getActivity());
						if (cantidadParaEnviar > 0){
							TextView email = (TextView) dialog.findViewById(R.id.emailText);
							TextView descripcion = (TextView) dialog.findViewById(R.id.descrText);

							if (!email.getText().toString().matches("") && CheckEmail.isEmailValid(email.getText().toString())) {
								Email em = new Email();
								em.setDescripcion(descripcion.getText().toString());
								em.setEmail(email.getText().toString());
								em.setIdVenta(idVenta);

								DirectSendEmail task = new DirectSendEmail(getActivity().getApplicationContext());
								task.setDATA(new Gson().toJson(em));
								task.execute();

								bld.setMessage("E-Mail Enviado Con Exito !!");
								obj.setCantidadParaEnviar(cantidadParaEnviar - 1);
								//refresh list
	//							refresTransacciones();

								Log.d("Evento enviado por mail", "Showing alert dialog: " + "");
							} else {
								bld.setMessage("E-Mail es vacio o tiene un formato incorrecto!!");
							}
						}else{
							bld.setMessage("Usted no puede enviar este evento, ya que la cantidad no se lo permite !!");
						}
						bld.setNeutralButton("OK", null);
						bld.create().show();
						dialog.dismiss();
					}
				});

				dialog.setTitle("EVENTO - BITMOVIL");
				dialog.show();
			}
		}

		@Override
		public void onRefresh() {
			List<Venta> final_list;
			GetVentasTask task_3 = new GetVentasTask(getActivity().getBaseContext());
			task_3.setIdUsuario(VentaHashmapCollectionSingleton.getInstance().user.getIdUsuario());

			try {
				VentaHashmapCollectionSingleton.getInstance().ventas = (List) task_3.execute(new Void[0]).get();
				VentaHashmapCollectionSingleton.getInstance();
				if (VentaHashmapCollectionSingleton.ventas != null) {
					VentaHashmapCollectionSingleton.getInstance();
					final_list = VentaHashmapCollectionSingleton.ventas;
				} else {
					final_list = new ArrayList();
				}
				this.adapter = new VentasItemListAdapter(getActivity().getBaseContext(), final_list);
				VendingMachineActivity.lv3.setAdapter(this.adapter);
				this.adapter.notifyDataSetChanged();
				VendingMachineActivity.lv3.setOnItemClickListener(new ShowListVenta(final_list));
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}

		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_ventas_list, container, false);
			((TextView) rootView.findViewById(R.id.venta_nombre)).setText(VendingMachineActivity.nombre_usuario != null ? VendingMachineActivity.nombre_usuario.toString() : "");
			VendingMachineActivity.lv3 = (ListView) rootView.findViewById(R.id.venta_list);
			try {
				List<Venta> final_list;
				VentaHashmapCollectionSingleton.getInstance();
				if (VentaHashmapCollectionSingleton.ventas != null) {
					VentaHashmapCollectionSingleton.getInstance();
					final_list = VentaHashmapCollectionSingleton.ventas;
				} else {
					final_list = new ArrayList();
				}
				this.adapter = new VentasItemListAdapter(getActivity().getBaseContext(), final_list);
				VendingMachineActivity.lv3.setAdapter(this.adapter);
				this.adapter.notifyDataSetChanged();
				VendingMachineActivity.lv3.setOnItemClickListener(new ShowListVenta(final_list));
			} catch (Exception ex) {
				ex.toString();
			}
			return rootView;
		}
	}


	/**
	 *
	 * @author goycolea
	 *
	 */
	public class SectionsPagerAdapter extends FragmentStatePagerAdapter
	{
		private int count;

	    public SectionsPagerAdapter(FragmentManager arg2)
	    {
	      super(arg2);
	    }

	    @Override
		public int getCount()
	    {
           return count;
	    }


	    @Override
		public Fragment getItem(int paramInt)
	    {
	    	switch(paramInt){
	    	 case  0:
	    		   return new VendingMachineActivity.MenuFragment();
	    	  case 1:
	    		   return new VendingMachineActivity.AvatarFragment();
	    	  case 2:
	    		   return new VendingMachineActivity.TransactionsFragment();
              case 3:
                   return new VendingMachineActivity.EventosFragment();
	    	  case 4:
	    		   return new VendingMachineActivity.VendingFragment();
	    	  case 5:
	    		   return new VendingMachineActivity.MiEspacioFragment();
	    	  default:
				   return new VendingMachineActivity.MenuFragment();
	    	}
	    }

		public void setCount(int count) {

			this.count = count;
		}

	  }
	/**
	 *
	 */
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		mViewPager.setCurrentItem(tab.getPosition());
	}
	/**
	 *
	 */
	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}
	/**
	 *
	 */
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}
    /**
     * METODO PARA REDONDEAR IMAGENES
     * @param bitmap
     * @return
     */
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {

        Bitmap output = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        //final Rect rect = new Rect(0, 0, bitmap.getHeight(), bitmap.getHeight());
        final Rect rect = new Rect(0, 0, 400, 400);
        final RectF rectF = new RectF(rect);
        final float roundPx = 70;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        //canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output ;
    }
    /**
     * METODO PARA RESCATAR IMAGEN DE LA MEMORIA DE LA TABLET
     */
    public static Bitmap getImageFromTablet(String photo, String idOrden, String internalId) throws InterruptedException, ExecutionException
    {
        AsyncTask<String, Void, Bitmap> task = new GetEvaluatorImageHelper().execute(photo, idOrden, internalId);
        Bitmap dailyTests = task.get();

        return dailyTests;
    }
	/**
	 *
	 * @author goycolea
	 *
	 */
	public static class NoRepeatRandom
	{
		private int[] number = null;
		private int N = -1;
		private int size = 0;
		public NoRepeatRandom(int minVal, int maxVal)
		{
			N = (maxVal - minVal) + 1;
			number = new int[N];
			int n = minVal;
			for(int i = 0; i < N; i++)
				number[i] = n++;
			size = N;
		}

		public void Reset() { size = N; }

		// Returns -1 if none left
		public int GetRandom()
		{
			if(size <= 0) return -1;
			int index = (int) (size * Math.random());
			int randNum = number[index];

			// Swap current value with current last, so we don't actually
			// have to remove anything, and our list still contains everything
			// if we want to reset
			number[index] = number[size-1];
			number[--size] = randNum;

			return randNum;
		}
	}
	/**
	 *
	 * @param w
	 * @return
	 */
	public static String getFormatNumber(int w){
		if(w<10){
			return "0"+String.valueOf(w);
		}else{
			return String.valueOf(w);
		}
	}


}
