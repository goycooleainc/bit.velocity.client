package com.bit.audit.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.bit.adapters.AvataresItemListAdapter;
import com.bit.adapters.EventosItemListAdapter;
import com.bit.adapters.VentasDetalleItemListAdapter;
import com.bit.async.tasks.GetAvataresTask;
import com.bit.async.tasks.GetEventosTask;
import com.bit.async.tasks.GetImageTask;
import com.bit.async.tasks.GetVentaDetalleTask;
import com.bit.async.tasks.PostAsynkTasks;
import com.bit.client.R;
import com.bit.entities.Avatar;
import com.bit.entities.Eventos;
import com.bit.entities.Transaccion;
import com.bit.entities.User;
import com.bit.entities.VentaDetalle;
import com.bit.singletons.CacheCollectionSingleton;
import com.bit.singletons.TransactionHashmapCollectionSingleton;
import com.google.gson.Gson;

import org.apache.james.mime4j.util.CharsetUtil;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class EventoFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static Button btn_ok;
    static Button btn_close;
    static ListView lv2;
    static ListView lv3;
    private EventosItemListAdapter adapter;
    private Activity activity;
    private View rootView;
    private Spinner s;
    private SwipeRefreshLayout swipeLayout;

    public EventoFragment(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_events_list, container, false);

        //refresh
        this.swipeLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);
        this.swipeLayout.setOnRefreshListener(this);
        this.swipeLayout.setColorScheme(17170459, 17170452, 17170456, 17170454);

        lv2 = (ListView) rootView.findViewById(R.id.product_list);
        activity = getActivity();
        View rootView2 = inflater.inflate(R.layout.fragment_ventas_list, container, false);
        lv3 = (ListView) rootView2.findViewById(R.id.venta_list);

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
            lv2.setAdapter(this.adapter);
            this.adapter.notifyDataSetChanged();
            lv2.setOnItemClickListener(new C00991(final_list));
        } catch (Exception ex) {
            ex.toString();
        }

        //Parar refresh a menos q esta al tope la lista
        lv2.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
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

        return rootView;
    }

    public void  refreshVenta(){
        List<VentaDetalle> final_list;
        GetVentaDetalleTask task_3 = new GetVentaDetalleTask(getActivity().getBaseContext());
        task_3.setIdUsuario(TransactionHashmapCollectionSingleton.getInstance().user.getIdUsuario());

        try {
            TransactionHashmapCollectionSingleton.getInstance().ventaDetalle = (List) task_3.execute(new Void[0]).get();
            TransactionHashmapCollectionSingleton.getInstance();
            if (TransactionHashmapCollectionSingleton.ventaDetalle != null) {
                TransactionHashmapCollectionSingleton.getInstance();
                final_list = TransactionHashmapCollectionSingleton.ventaDetalle;
            } else {
                final_list = new ArrayList();
            }
            VentasDetalleItemListAdapter ventasItemListAdapter = new VentasDetalleItemListAdapter(getActivity().getBaseContext(), final_list);
            lv3.setAdapter(ventasItemListAdapter);
            ventasItemListAdapter.notifyDataSetChanged();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void refreshEvents()
    {
        GetEventosTask events_mediator = new GetEventosTask(getActivity());
        List<User> list = null;
        try {
            List<Eventos> eventos = (List) events_mediator.execute(new Void[0]).get();
            CacheCollectionSingleton.getInstance(getActivity()).setInMemmoryUsers(new Gson().toJson((Object) list));
            TransactionHashmapCollectionSingleton.getInstance();
            TransactionHashmapCollectionSingleton.eventos = eventos;

            List<Eventos> final_list = eventos;
            this.adapter = new EventosItemListAdapter(getActivity().getBaseContext(), final_list);
            lv2.setAdapter(this.adapter);
            this.adapter.notifyDataSetChanged();
            lv2.setOnItemClickListener(new C00991(final_list));

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e2) {
            e2.printStackTrace();
        }
    }
    /* renamed from: com.bit.audit.fragments.EventosFragment.1 */
    class C00991 implements AdapterView.OnItemClickListener {
        final List val$final_list;

        /* renamed from: com.bit.audit.fragments.EventosFragment.1.1 */
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
            AlertDialog.Builder bld = new AlertDialog.Builder(v.getContext());

            final Dialog dialog = new Dialog(v.getContext());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.modal_eventos_method);

            final NumberPicker numberPicker = (NumberPicker) dialog.findViewById(R.id.numberPicker);
            final TextView precio = (TextView) dialog.findViewById(R.id.precioEvento);
            numberPicker.setMinValue(1);
            numberPicker.setMaxValue(100);
            numberPicker.setWrapSelectorWheel(true);

            btn_close = (Button) dialog.findViewById(R.id.dialogButtonCancel);
            btn_ok = (Button) dialog.findViewById(R.id.dialogButtonOK);

            ImageView imagen = (ImageView) dialog.findViewById(R.id.imageViewEvent);
            final Eventos obj = (Eventos) this.val$final_list.get(position);
            ((TextView) dialog.findViewById(R.id.txDetalleEvento)).setText(obj.getNombre() + CharsetUtil.CRLF + obj.getDetalle().toString() + CharsetUtil.CRLF + obj.getFechaInicio());

            String remoteURL = getActivity().getApplicationContext().getString(R.string.server);
            GetImageTask it = new GetImageTask();
            try {
                it.setUrl(remoteURL + "/dmz/multimedia/" + obj.getId() + "/type/1/" + obj.getId() + "-0");
                imagen.setImageDrawable((Drawable) it.execute(new Void[0]).get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e2) {
                e2.printStackTrace();
            }

            LinearLayout linearLayoutSector = (LinearLayout) dialog.findViewById(R.id.linearLayoutSector);
            s = (Spinner) dialog.findViewById(R.id.sectores);
            if(obj.getSector1() != null) {

                linearLayoutSector.setVisibility(View.VISIBLE);
                List<String> sectores = new ArrayList<>();
                sectores.add(obj.getSector1());
                sectores = checkSector(obj, sectores);
                s.setAdapter(new ArrayAdapter(dialog.getContext(), R.layout.spinner_item, sectores));
            }

            s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    precio.setText("");
                    int cant_personas = numberPicker.getValue();
                    int valor_venta = calculoSectorEntradas(position, cant_personas, obj);
                    precio.setText('$' + String.valueOf(valor_venta));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });

            numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                    int posicion_sector = s.getSelectedItemPosition();
                    int valor_venta = calculoSectorEntradas(posicion_sector, newVal, obj);
                    precio.setText('$' + String.valueOf(valor_venta));
                }
            });

            btn_close.setOnClickListener(new C00981(dialog));

            btn_ok.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    AlertDialog.Builder bld = new AlertDialog.Builder(v.getContext());

                    DecimalFormat df = new DecimalFormat("#,##0.00");
                    df.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ITALY));

                    if(TransactionHashmapCollectionSingleton.estadoCuenta != null) {
                        df.format(new BigDecimal(TransactionHashmapCollectionSingleton.estadoCuenta.getSaldo()));

                        String reformateoPrecio = (String.valueOf(precio.getText())).replace("$","");
                        int total = Integer.parseInt(reformateoPrecio);
                        int resto = Integer.parseInt(TransactionHashmapCollectionSingleton.estadoCuenta.getSaldo()) - total;

                        Transaccion tx = new Transaccion();
                        TransactionHashmapCollectionSingleton.getInstance();
                        String avatar = null;
                        if (TransactionHashmapCollectionSingleton.avatar != null) {
                            avatar = TransactionHashmapCollectionSingleton.avatar.getCodigo();
                        }
                        if(avatar != null){
                            tx.setAvatar(avatar);
                            tx.setCantidad(String.valueOf(numberPicker.getValue()));
                            tx.setFecha("");
                            tx.setGps("0.0,0.0");
                            tx.setIdProducto(0);
                            tx.setIdProductora(obj.getIdProductora());
                            tx.setMetodoPago(0);
                            tx.setMoneda(0);
                            tx.setPublicKey("");
                            tx.setTotal(String.valueOf(total));
                            tx.setIdEvento(obj.getId());
                            tx.setSector((String) s.getSelectedItem());
                            tx.setTipo("1");//(1 = Evento)

                            String remoteURL = getActivity().getApplicationContext().getString(R.string.sendTransaction);
                            PostAsynkTasks task = new PostAsynkTasks(rootView, activity, bld, remoteURL);
                            task.setDATA(new Gson().toJson(tx));
                            task.execute();

                            //Refrescar fragment principal para actualizar salgo
                            if (resto >= 0) {
                                TransactionHashmapCollectionSingleton.estadoCuenta.setSaldo(String.valueOf(resto).toString());

                                //refresh list
                                refreshVenta();
                            } else {
                                bld.setMessage("Saldo insuficiente !!");
                                bld.create().show();
                            }
                        }else{
                            bld.setMessage("Debes activar un avatar para realizar la transacción");
                            bld.create().show();
                        }
                    }else{
                        bld.setMessage("Saldo insuficiente !!");
                        bld.create().show();
                    }
                    dialog.dismiss();
                }
            });

            dialog.setTitle("EVENTO - BITMOVIL");
            dialog.show();
            bld.setMessage("Para comprar en diferentes sectores debera realizar una transacción diferente");
            bld.create().show();
        }
    }

    public int calculoSectorEntradas(int posicionSector, int cant_entradas, Eventos obj) {
        int valor_sector = 0;
        int valor_venta;

        switch (posicionSector){
            case 0:
                valor_sector = Integer.parseInt(obj.getPrecio1());
            break;
            case 1:
                valor_sector = Integer.parseInt(obj.getPrecio2());
            break;
            case 2:
                valor_sector = Integer.parseInt(obj.getPrecio3());
            break;
            case 3:
                valor_sector = Integer.parseInt(obj.getPrecio4());
            break;
            case 4:
                valor_sector = Integer.parseInt(obj.getPrecio5());
            break;
            case 5:
                valor_sector = Integer.parseInt(obj.getPrecio6());
            break;
            case 6:
                valor_sector = Integer.parseInt(obj.getPrecio7());
            break;
            case 7:
                valor_sector = Integer.parseInt(obj.getPrecio8());
            break;
            case 8:
                valor_sector = Integer.parseInt(obj.getPrecio9());
            break;
            case 9:
                valor_sector = Integer.parseInt(obj.getPrecio10());
            break;
        }
        valor_venta = valor_sector * cant_entradas;
        return valor_venta;
    }

    public List<String> checkSector(Eventos obj, List<String> sectores){
        if(obj.getSector2() != null){
            sectores.add(obj.getSector2());
        }
        if(obj.getSector3() != null){
            sectores.add(obj.getSector3());
        }
        if(obj.getSector4() != null){
            sectores.add(obj.getSector4());
        }
        if(obj.getSector5() != null){
            sectores.add(obj.getSector5());
        }
        if(obj.getSector6() != null){
            sectores.add(obj.getSector6());
        }
        if(obj.getSector7() != null){
            sectores.add(obj.getSector7());
        }
        if(obj.getSector8() != null){
            sectores.add(obj.getSector8());
        }
        if(obj.getSector9() != null){
            sectores.add(obj.getSector9());
        }
        if(obj.getSector10() != null){
            sectores.add(obj.getSector10());
        }
        return sectores;
    }

    class runneable implements Runnable {
        runneable() {
        }

        public void run() {
            swipeLayout.setRefreshing(false);
            try {
                refreshEvents();
//                List<Eventos> final_list;
//
//                GetEventosTask task_2 = new GetEventosTask(getActivity());
//                final_list = TransactionHashmapCollectionSingleton.getInstance().eventos = (List) task_2.execute(new Void[0]).get();
//
//                lv3.setAdapter(new EventosItemListAdapter(getActivity().getBaseContext(), final_list));
//                ((EventosItemListAdapter) lv3.getAdapter()).notifyDataSetChanged();
            } catch (Exception e) {
            }
        }
    }

    public void onRefresh() {
        new Handler().postDelayed(new runneable(), 5000);
    }

}
