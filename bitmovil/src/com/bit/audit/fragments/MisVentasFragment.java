package com.bit.audit.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bit.adapters.AvataresItemListAdapter;
import com.bit.adapters.VentasDetalleItemListAdapter;
import com.bit.async.tasks.GetVentaDetalleTask;
import com.bit.async.tasks.PostAsynkTasks;
import com.bit.client.R;
import com.bit.entities.Email;
import com.bit.entities.VentaDetalle;
import com.bit.singletons.TransactionHashmapCollectionSingleton;
import com.bit.utils.CheckEmail;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gfurlano on 2/29/16.
 */
public class MisVentasFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private VentasDetalleItemListAdapter adapterDetalleVenta;
    private VentaDetalle obj;
    static Button btn_close, btn_ok, btn_asignar;
    static ListView lv1;
    private LinearLayout linlaHeaderProgress;
    private View rootView;
    private Activity activity;
    private String avatar;
    private SwipeRefreshLayout swipeLayout;

    public MisVentasFragment(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_ventas_list, container, false);

        //refresh
        this.swipeLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);
        this.swipeLayout.setOnRefreshListener(this);
        this.swipeLayout.setColorScheme(17170459, 17170452, 17170456, 17170454);

        activity = getActivity();
        linlaHeaderProgress = (LinearLayout) rootView.findViewById(R.id.linlaHeaderProgress);

        /*((TextView) rootView.findViewById(R.id.venta_nombre)).setText(intent.getStringExtra("nombre") != null ? intent.getStringExtra("nombre").toString() : "");*/
        lv1 = (ListView) rootView.findViewById(R.id.venta_list);
        try {
            List<VentaDetalle> final_list;
            TransactionHashmapCollectionSingleton.getInstance();
            if (TransactionHashmapCollectionSingleton.ventaDetalle != null) {
                TransactionHashmapCollectionSingleton.getInstance();
                final_list = TransactionHashmapCollectionSingleton.ventaDetalle;
            } else {
                final_list = new ArrayList();
            }
            this.adapterDetalleVenta = new VentasDetalleItemListAdapter(getActivity().getBaseContext(), final_list);
            lv1.setAdapter(this.adapterDetalleVenta);
            this.adapterDetalleVenta.notifyDataSetChanged();
            lv1.setOnItemClickListener(new ShowListVenta(final_list));
        } catch (Exception ex) {
            ex.toString();
        }

        //Parar refresh a menos q esta al tope la lista
        lv1.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                boolean enable = false;
                if(lv1 != null && lv1.getChildCount() > 0){
                    // check if the first item of the list is visible
                    boolean firstItemVisible = lv1.getFirstVisiblePosition() == 0;
                    // check if the top of the first item is visible
                    boolean topOfFirstItemVisible = lv1.getChildAt(0).getTop() == 0;
                    // enabling or disabling the refresh layout
                    enable = firstItemVisible && topOfFirstItemVisible;
                }
                swipeLayout.setEnabled(enable);
            }
        });

        return rootView;
    }

    class ShowListVenta implements AdapterView.OnItemClickListener {
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
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.modal_compartir_evento_method);

            btn_close = (Button) dialog.findViewById(R.id.dialogButtonCancel);
            btn_ok = (Button) dialog.findViewById(R.id.dialogButtonOK);
            btn_asignar = (Button) dialog.findViewById(R.id.dialog_button_asignar);

            obj = (VentaDetalle) this.final_list.get(position);
            final int idVentaDetalle = obj.getId();

            btn_close.setOnClickListener(new ShowModalVenta(dialog));

            btn_ok.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    AlertDialog.Builder bld = new AlertDialog.Builder(v.getContext());
                    if (obj.getAvatar() == null) {
                        TextView email = (TextView) dialog.findViewById(R.id.emailText);
                        TextView descripcion = (TextView) dialog.findViewById(R.id.descrText);

                        if (!email.getText().toString().matches("") && CheckEmail.isEmailValid(email.getText().toString())) {
                            Email em = new Email();
                            em.setDescripcion(descripcion.getText().toString());
                            em.setEmail(email.getText().toString());
                            em.setIdVentaDetalle(idVentaDetalle);

                            String remoteURL = getActivity().getApplicationContext().getString(R.string.sendEmail);
                            PostAsynkTasks task = new PostAsynkTasks(rootView, activity, bld, remoteURL);
                            task.setDATA(new Gson().toJson(em));
                            task.execute();

                            Log.d("Evento enviado por mail", "Showing alert dialog: " + "");
                            onRefresh();
                        } else {
                            bld.setMessage("E-Mail es vacio o tiene un formato incorrecto!!");
                            bld.setNeutralButton("OK", null);
                            bld.create().show();
                        }
                    } else {
                        bld.setMessage("Usted no puede enviar este evento, ya que tiene asignado un avatar!!");
                        bld.setNeutralButton("OK", null);
                        bld.create().show();
                    }

                    dialog.dismiss();
                }
            });

            btn_asignar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder bld = new AlertDialog.Builder(v.getContext());

                    if (TransactionHashmapCollectionSingleton.avatar != null) {
                        avatar = TransactionHashmapCollectionSingleton.avatar.getCodigo();

                        if (!avatar.equals("") && TransactionHashmapCollectionSingleton.avatar.getEstado() == 1) {

                            obj.setAvatar(avatar);
                            String remoteURL = getActivity().getApplicationContext().getString(R.string.asignarAvatarVenta);
                            PostAsynkTasks task = new PostAsynkTasks(rootView, activity, bld, remoteURL);
                            task.setDATA(new Gson().toJson(obj));
                            task.execute();

                            onRefresh();
                        }
                    } else {
                        bld.setMessage("No tiene ningun avatar activo");
                        bld.setNeutralButton("OK", null);
                        bld.create().show();
                    }
                    dialog.dismiss();
                }
            });

            dialog.show();
        }
    }

    class runneable implements Runnable {
        runneable() {
        }

        public void run() {
            MisVentasFragment.this.swipeLayout.setRefreshing(false);
            try {
                List<VentaDetalle> final_list;

                GetVentaDetalleTask task_9 = new GetVentaDetalleTask(getActivity());
                task_9.setIdUsuario(TransactionHashmapCollectionSingleton.getInstance().user.getIdUsuario());
                final_list = TransactionHashmapCollectionSingleton.getInstance().ventaDetalle = (List) task_9.execute(new Void[0]).get();

                lv1.setAdapter(new VentasDetalleItemListAdapter(getActivity().getBaseContext(), final_list));
                ((AvataresItemListAdapter) lv1.getAdapter()).notifyDataSetChanged();
            } catch (Exception e) {
            }
        }
    }

    public void onRefresh() {
        new Handler().postDelayed(new runneable(), 5000);
    }

}
