package com.bit.audit.fragments;

import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.bit.adapters.TransactionsItemListAdapter;
import com.bit.async.tasks.GetTransactionsTask;
import com.bit.client.R;
import com.bit.entities.Eventos;
import com.bit.entities.Transaccion;
import com.bit.singletons.TransactionHashmapCollectionSingleton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TransaccionFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    static int _position;
    private TransactionsItemListAdapter adapter;
    private int id;
    private static Button btn_ok;
    static ImageButton btnOk;
    static Button btn_close;
    static ImageButton btn_discard, btnNew;
    static ImageButton btn_save;
    static ListView lv3;
    static String nombre_usuario;

    public TransaccionFragment(){}

    @Override
    public void onRefresh() {
        List<Transaccion> final_list;
        GetTransactionsTask task_3 = new GetTransactionsTask(getActivity());
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
            lv3.setAdapter(this.adapter);
            this.adapter.notifyDataSetChanged();
            lv3.setOnItemClickListener(new C01031(final_list));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: com.bit.audit.fragments.TransactionsFragment.1 */
    class C01031 implements AdapterView.OnItemClickListener {
        final /* synthetic */ List val$final_list;

        /* renamed from: com.bit.audit.fragments.TransactionsFragment.1.1 */
        class C01021 implements View.OnClickListener {
            final /* synthetic */ Dialog val$dialog;

            C01021(Dialog dialog) {
                this.val$dialog = dialog;
            }

            public void onClick(View v) {
                this.val$dialog.dismiss();
            }
        }

        C01031(List list) {
            this.val$final_list = list;
        }

        public void onItemClick(AdapterView<?> adapterView, View v, int position, long id) {
            Dialog dialog = new Dialog(v.getContext());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.modal_transaction_method);
            btn_close = (Button) dialog.findViewById(R.id.dialogButtonCancel);
            TextView total = (TextView) dialog.findViewById(R.id.txTotal);
            TextView producto = (TextView) dialog.findViewById(R.id.txProducto);
            TextView mercante = (TextView) dialog.findViewById(R.id.txMercante);
            TextView fecha = (TextView) dialog.findViewById(R.id.txFecha);
            Transaccion obj = (Transaccion) this.val$final_list.get(position);
            ((TextView) dialog.findViewById(R.id.txCode)).setText("Avatar [" + obj.getAvatar().toString() + "]");
            total.setText("Total $" + obj.getTotal().toString());
            producto.setText("ID Transaccion [" + String.valueOf(obj.getId()) + "]");
            String evento = "ERROR";
            TransactionHashmapCollectionSingleton.getInstance();
            for (Eventos parent : TransactionHashmapCollectionSingleton.eventos) {
                if (parent.getId() == obj.getIdEvento()) {
                    evento = parent.getNombre();
                }
            }
            mercante.setText("[" + evento + "]");
            fecha.setText("Fecha " + obj.getFecha().toString());
            btn_close.setOnClickListener(new C01021(dialog));
            /*dialog.setTitle("TRANSACCION COMPRA");*/
            dialog.show();
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_transactions_list, container, false);
        /*((TextView) rootView.findViewById(R.id.tx_nombre)).setText(nombre_usuario != null ? nombre_usuario.toString() : "");*/
        lv3 = (ListView) rootView.findViewById(R.id.product_list);
        try {
            List<Transaccion> final_list;

            GetTransactionsTask task_3 = new GetTransactionsTask(getActivity());
            task_3.setIdUsuario(TransactionHashmapCollectionSingleton.getInstance().user.getIdUsuario());
            TransactionHashmapCollectionSingleton.getInstance().transacciones = (List) task_3.execute(new Void[0]).get();

            TransactionHashmapCollectionSingleton.getInstance();
            if (TransactionHashmapCollectionSingleton.transacciones != null) {
                TransactionHashmapCollectionSingleton.getInstance();
                final_list = TransactionHashmapCollectionSingleton.transacciones;
            } else {
                final_list = new ArrayList();
            }
            this.adapter = new TransactionsItemListAdapter(getActivity().getBaseContext(), final_list);
            lv3.setAdapter(this.adapter);
            this.adapter.notifyDataSetChanged();
            lv3.setOnItemClickListener(new C01031(final_list));
        } catch (Exception ex) {
            ex.toString();
        }
        return rootView;
    }

}
