package com.bit.audit.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.bit.adapters.EventosItemListAdapter;
import com.bit.async.tasks.DirectNewTransaction;
import com.bit.async.tasks.GetEventosTask;
import com.bit.async.tasks.GetImageTask;
import com.bit.client.R;
import com.bit.entities.Eventos;
import com.bit.entities.Transaccion;
import com.bit.entities.User;
import com.bit.singletons.CacheCollectionSingleton;
import com.bit.singletons.VentaHashmapCollectionSingleton;
import com.google.gson.Gson;
import org.apache.james.mime4j.util.CharsetUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class PhotosFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static Button btn_ok;
    static ImageButton btnOk;
    static Button btn_close;
    static ImageButton btn_discard, btnNew;
    static ImageButton btn_save;
    static ListView lv2;
    static String nombre_usuario;

	public PhotosFragment(){}
    static int _position;
    private EventosItemListAdapter adapter;
    private int id;

    @Override
    public void onRefresh() {
        refreshEvents();
    }

    private void refreshEvents()
    {
        GetEventosTask events_mediator = new GetEventosTask();
        List<User> list = null;
        try {

            List<Eventos> eventos = (List) events_mediator.execute(new Void[0]).get();
            CacheCollectionSingleton.getInstance(getActivity()).setInMemmoryUsers(new Gson().toJson((Object) list));
            VentaHashmapCollectionSingleton.getInstance();
            VentaHashmapCollectionSingleton.eventos = eventos;

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
        final /* synthetic */ List val$final_list;

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
            final Dialog dialog = new Dialog(v.getContext());
            dialog.setContentView(R.layout.modal_eventos_method);
            btn_close = (Button) dialog.findViewById(R.id.dialogButtonCancel);
            btn_ok = (Button) dialog.findViewById(R.id.dialogButtonOK);

            ImageView imagen = (ImageView) dialog.findViewById(R.id.imageViewEvent);
            final Eventos obj = (Eventos) this.val$final_list.get(position);
            ((TextView) dialog.findViewById(R.id.txDetalleEvento)).setText("$" + obj.getPrecio() + CharsetUtil.CRLF + obj.getNombre() + CharsetUtil.CRLF + obj.getDetalle().toString() + CharsetUtil.CRLF + obj.getFechaInicio());
            GetImageTask it = new GetImageTask();
            try {
                it.setUrl("http://bit.goycooleainc.com/dmz/multimedia/" + obj.getId() + "/type/1/" + obj.getId() + "-0");
                imagen.setImageDrawable((Drawable) it.execute(new Void[0]).get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e2) {
                e2.printStackTrace();
            }
            btn_close.setOnClickListener(new C00981(dialog));

            btn_ok.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Transaccion tx = new Transaccion();
                    String avatar = VentaHashmapCollectionSingleton.avatar.getCodigo();
                    tx.setAvatar(avatar);
                    tx.setCantidad("1");
                    tx.setFecha("");
                    tx.setGps("0.0,0.0");
                    tx.setIdProducto(obj.getId());
                    tx.setIdProductora(obj.getIdProductora());
                    tx.setMetodoPago(0);
                    tx.setMoneda(0);
                    tx.setPublicKey("");
                    tx.setTotal(obj.getPrecio());

                    DirectNewTransaction task = new DirectNewTransaction();
                    task.setDATA(new Gson().toJson(tx));
                    //task.directSend(new Gson().toJson(obj));
                    task.execute();

                    AlertDialog.Builder bld = new AlertDialog.Builder(getActivity());
                    bld.setMessage("Compra Hecha Con Exito !!");
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
        ((TextView) rootView.findViewById(R.id.tx_nombre)).setText(nombre_usuario != null ? nombre_usuario.toString() : "");
        lv2 = (ListView) rootView.findViewById(R.id.product_list);
        try {
            List<Eventos> final_list;
            VentaHashmapCollectionSingleton.getInstance();
            if (VentaHashmapCollectionSingleton.eventos != null) {
                VentaHashmapCollectionSingleton.getInstance();
                final_list = VentaHashmapCollectionSingleton.eventos;
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
        return rootView;
    }
}
