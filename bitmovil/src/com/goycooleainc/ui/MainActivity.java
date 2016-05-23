package com.goycooleainc.ui;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bit.adapters.ProductosBitItemListAdapter;
import com.bit.async.tasks.GetEstadoCuentaTask;
import com.bit.async.tasks.PostAsynkTasks;
import com.bit.client.R;
import com.bit.entities.EstadoCuenta;
import com.bit.entities.Productos;
import com.bit.entities.Transaccion;
import com.bit.singletons.TransactionHashmapCollectionSingleton;
import com.bit.singletons.VendingSingleton;
import com.google.gson.Gson;
import com.goycooleainc.ui.base.BlundellActivity;
import com.goycooleainc.ui.utils.Navigator;
import com.goycooleainc.ui.xml.MainMenu;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends BlundellActivity implements MainMenu, SwipeRefreshLayout.OnRefreshListener {

    static ListView lv2;
    static ImageButton btnClose;
    public View view;
    TextView txBalance;
    private SwipeRefreshLayout swipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /// require action bar
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        final ActionBar localActionBar = getActionBar();
        localActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        localActionBar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
        localActionBar.hide();

        setContentView(R.layout.fragment_product_list);

        //refresh
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setColorScheme(17170459, 17170452, 17170456, 17170454);

        //passportImage = (ImageView) findViewById(R.id.main_passport_image);
        lv2 = (ListView) findViewById(R.id.product_list);

        btnClose = (ImageButton) findViewById(R.id.btnClose) ;
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent parent = new Intent(getBaseContext(), com.bit.audit.fragments.MainActivity.class);
                startActivity(parent);
            }
        });

        try {
            List<Productos> list = TransactionHashmapCollectionSingleton.getInstance().productos;
            lv2.setAdapter(new ProductosBitItemListAdapter(getBaseContext(), list));
            lv2.setOnItemClickListener(new C01042(list));

        } catch (Exception ex) {
            ex.toString();
        }

        txBalance = (TextView) findViewById(R.id.txBalance);
        DecimalFormat df = new DecimalFormat("#,##0.00");
        df.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ITALY));
        TransactionHashmapCollectionSingleton.getInstance();
        if(TransactionHashmapCollectionSingleton.estadoCuenta == null) {
            txBalance.setText("0");
        }else{
            txBalance.setText(df.format(new BigDecimal(TransactionHashmapCollectionSingleton.estadoCuenta.getSaldo().toString())));
        }

        //navigate().toPurchasePassportActivityForResult();

        //Parar refresh a menos q esta al tope la lista
        lv2.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                boolean enable = false;
                if(lv2 != null && lv2.getChildCount() > 0){
                    // check if the first item of the list is visible
                    boolean firstItemVisible = lv2.getFirstVisiblePosition() == 0;
                    // check if the top of the first item is visible
                    boolean topOfFirstItemVisible = lv2.getChildAt(0).getTop() == 0;
                    // enabling or disabling the refresh layout
                    enable = firstItemVisible && topOfFirstItemVisible;
                }
                swipeLayout.setEnabled(enable);
            }
        });
    }

    class C01042 implements AdapterView.OnItemClickListener {
        final List val$list;

        C01042(List list) {
            this.val$list = list;
        }

        public void onItemClick(AdapterView<?> adapterView, View v, int position, long id) {
            view = v;
            Productos obj = (Productos) this.val$list.get(position);
            TransactionHashmapCollectionSingleton.getInstance();
            List<Productos> productos = TransactionHashmapCollectionSingleton.productos;

            if (productos != null) {
                //manager.purchase(obj.getCodigo());
                VendingSingleton.getInstance().producto = obj;
                navigate().toPurchasePassportActivityForResult();
            } else {
                Toast.makeText(getBaseContext(), "Producto No Reconocido, Intenta de Nuevo!! =)", Toast.LENGTH_LONG).show();
            }
        }
    }

    class runneable implements Runnable {
        runneable() {
        }

        public void run() {
            swipeLayout.setRefreshing(false);
            try {
                GetEstadoCuentaTask task_1 = new GetEstadoCuentaTask(getApplicationContext());
                task_1.setIdUsuario(TransactionHashmapCollectionSingleton.getInstance().user.getIdUsuario());
                TransactionHashmapCollectionSingleton.getInstance().estadoCuenta = (EstadoCuenta) task_1.execute(new Void[0]).get();

                DecimalFormat df = new DecimalFormat("#,##0.00");
                df.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ITALY));
                TransactionHashmapCollectionSingleton.getInstance();
                if(TransactionHashmapCollectionSingleton.estadoCuenta == null) {
                    txBalance.setText("0");
                }else{
                    txBalance.setText(df.format(new BigDecimal(TransactionHashmapCollectionSingleton.estadoCuenta.getSaldo().toString())));
                }

            } catch (Exception e) {

            }
        }
    }

    public void onRefresh() {
        new Handler().postDelayed(new runneable(), 5000);
    }

    @Override
    public void onPurchaseItemClick(View v) {
        //navigate().toPurchasePassportActivityForResult();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Navigator.REQUEST_PASSPORT_PURCHASE == requestCode) {

            if (RESULT_OK == resultCode) {
                dealWithSuccessfulPurchase();
            } else {
                dealWithFailedPurchase();
            }
        }
    }

    private void dealWithSuccessfulPurchase() {
        try {
            AlertDialog.Builder bld = new AlertDialog.Builder(view.getContext());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String currentDateandTime = sdf.format(new Date());

            Transaccion tx = new Transaccion();
            tx.setFecha(currentDateandTime);
            tx.setAvatar(TransactionHashmapCollectionSingleton.getInstance().avatar.getCodigo());
            tx.setCantidad("1");
            tx.setGps("");
            tx.setIdProducto(0);
            tx.setId(0);
            tx.setIdProductora(422);
            tx.setMetodoPago(1);
            tx.setTotal(VendingSingleton.getInstance().producto.getPrecio());
            tx.setMoneda(1);
            tx.setPublicKey("");

            String remoteURL = this.getApplicationContext().getString(R.string.sendTransaction);
            PostAsynkTasks task = new PostAsynkTasks(view, this, bld, remoteURL);
            task.setDATA(new Gson().toJson(tx));
            task.execute();

            tx.setMetodoPago(1);
            String remoteURL2 = this.getApplicationContext().getString(R.string.sendEmailAfterTransaction);
            PostAsynkTasks task2 = new PostAsynkTasks(view, this, bld, remoteURL2);
            task2.setDATA(new Gson().toJson(tx));
            task2.execute();

        }catch (Exception ex){

        }
    }

    private void dealWithFailedPurchase() {
        try {
            AlertDialog.Builder bld = new AlertDialog.Builder(view.getContext());

            Transaccion tx = new Transaccion();
            tx.setAvatar(TransactionHashmapCollectionSingleton.getInstance().avatares.get(0).getCodigo());
//            tx.setAvatar(TransactionHashmapCollectionSingleton.getInstance().avatar.getCodigo());
            tx.setTotal(VendingSingleton.getInstance().producto.getPrecio());
            tx.setMetodoPago(-1);

            String remoteURL2 = this.getApplicationContext().getString(R.string.sendEmailAfterTransaction);
            PostAsynkTasks task2 = new PostAsynkTasks(view, this, bld, remoteURL2);
            task2.setDATA(new Gson().toJson(tx));
            task2.execute();

        }catch (Exception ex){

        }
    }
}
