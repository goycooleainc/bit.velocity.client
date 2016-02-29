package com.goycooleainc.ui;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import com.bit.adapters.ProductosBitItemListAdapter;
import com.bit.async.tasks.DirectNewTransaction;
import com.bit.client.R;
import com.bit.entities.Productos;
import com.bit.entities.Transaccion;
import com.bit.singletons.TransactionHashmapCollectionSingleton;
import com.bit.singletons.VendingSingleton;
import com.bit.singletons.VentaHashmapCollectionSingleton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.goycooleainc.ui.base.BlundellActivity;
import com.goycooleainc.ui.utils.Navigator;
import com.goycooleainc.ui.xml.MainMenu;
import com.goycooleainc.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This activity holds the button to purchase a passport
 * when the result is received if the passport was successfully purchase it shows the picture
 * 
 * @author Blundell
 * 
 */
public class MainActivity extends BlundellActivity implements MainMenu {

    static ListView lv2;
    static ImageButton btnClose;
    private static final String TAG = "BITCHELIN";

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
//            List<Productos> list = VentaHashmapCollectionSingleton.getInstance().productos;
            lv2.setAdapter(new ProductosBitItemListAdapter(getBaseContext(), list));
            lv2.setOnItemClickListener(new C01042(list));

        } catch (Exception ex) {
            ex.toString();
        }

        //navigate().toPurchasePassportActivityForResult();
    }

    /* renamed from: com.bit.audit.fragments.VendingMachineActivity.VendingFragment.2 */
    class C01042 implements AdapterView.OnItemClickListener {
        final /* synthetic */ List val$list;

        C01042(List list) {
            this.val$list = list;
        }

        public void onItemClick(AdapterView<?> adapterView, View v, int position, long id) {

            Productos obj = (Productos) this.val$list.get(position);
            TransactionHashmapCollectionSingleton.getInstance();
            List<Productos> productos = TransactionHashmapCollectionSingleton.productos;
//            VentaHashmapCollectionSingleton.getInstance();
//            List<Productos> productos = VentaHashmapCollectionSingleton.productos;

            if (productos != null) {
                //manager.purchase(obj.getCodigo());
                VendingSingleton.getInstance().producto = obj;
                navigate().toPurchasePassportActivityForResult();
            } else {
                Toast.makeText(getBaseContext(), "Producto No Reconocido, Intenta de Nuevo!! =)", Toast.LENGTH_LONG).show();
            }
        }
    }

    /* renamed from: com.bit.audit.fragments.VendingMachineActivity.VendingFragment.1 */
    class C01781 extends TypeToken<ArrayList<Productos>> {
        C01781() {
        }
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
    /**
     *
     */
    private void dealWithSuccessfulPurchase() {
        /// salida al log
        Log.d("Credit Purchased");
        /// motrar toast
        popToast("Vaaamoooos, tenes credito!!");
        /// ahora decimos uqe fue hecha una carga y la comiteamos
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String currentDateandTime = sdf.format(new Date());

            Transaccion tx = new Transaccion();
            tx.setFecha(currentDateandTime);
            tx.setAvatar(TransactionHashmapCollectionSingleton.getInstance().avatar.getCodigo());
//            tx.setAvatar(VentaHashmapCollectionSingleton.getInstance().avatar.getCodigo());
            tx.setCantidad("1");
            tx.setGps("");
            tx.setIdProducto(0);
            tx.setId(0);
            tx.setIdProductora(422);
            tx.setMetodoPago(1);
            tx.setTotal(VendingSingleton.getInstance().producto.getPrecio());
            tx.setMoneda(1);
            tx.setPublicKey("");

            DirectNewTransaction task = new DirectNewTransaction(getApplicationContext());
            task.setDATA(new Gson().toJson(tx));
        }catch (Exception ex){

        }

    }
    /**
     *
     */
    private void dealWithFailedPurchase() {

        Log.d("Passport purchase failed");
        popToast("Paff, no tenes credito o tu tarjeta hizo boing boing !!");

    }
}
