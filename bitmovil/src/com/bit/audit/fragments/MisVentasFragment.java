package com.bit.audit.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.bit.adapters.VentasItemListAdapter;
import com.bit.async.tasks.DirectSendEmail;
import com.bit.async.tasks.GetVentasTask;
import com.bit.client.R;
import com.bit.entities.Email;
import com.bit.entities.Venta;
import com.bit.singletons.TransactionHashmapCollectionSingleton;
import com.bit.singletons.VentaHashmapCollectionSingleton;
import com.bit.utils.CheckEmail;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by gfurlano on 2/29/16.
 */
public class MisVentasFragment extends Fragment {

    static int _position;
    private VentasItemListAdapter adapter;
    private Venta obj;
    static Button btn_close, btn_ok;
    static ListView lv1;

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
            dialog.setContentView(R.layout.modal_compartir_evento_method);

            btn_close = (Button) dialog.findViewById(R.id.dialogButtonCancel);
            btn_ok = (Button) dialog.findViewById(R.id.dialogButtonOK);

            obj = (Venta) this.final_list.get(_position);
            final int idVenta = obj.getId();
            final int cantidadParaEnviar = obj.getCantidadParaEnviar();

            btn_close.setOnClickListener(new ShowModalVenta(dialog));

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

    public void onRefresh() {
        List<Venta> final_list;
        GetVentasTask task_3 = new GetVentasTask(getActivity().getBaseContext());
        task_3.setIdUsuario(TransactionHashmapCollectionSingleton.getInstance().user.getIdUsuario());

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
            lv1.setAdapter(this.adapter);
            this.adapter.notifyDataSetChanged();
            lv1.setOnItemClickListener(new ShowListVenta(final_list));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_ventas_list, container, false);
        Intent intent = getActivity().getIntent();
        ((TextView) rootView.findViewById(R.id.venta_nombre)).setText(intent.getStringExtra("nombre") != null ? intent.getStringExtra("nombre").toString() : "");
        lv1 = (ListView) rootView.findViewById(R.id.venta_list);
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
            lv1.setAdapter(this.adapter);
            this.adapter.notifyDataSetChanged();
            lv1.setOnItemClickListener(new ShowListVenta(final_list));
        } catch (Exception ex) {
            ex.toString();
        }
        return rootView;
    }



}
