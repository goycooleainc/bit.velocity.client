package com.bit.audit.fragments;

import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.bit.adapters.AvataresItemListAdapter;
import com.bit.adapters.TransactionsItemListAdapter;
import com.bit.async.tasks.GetTransactionsTask;
import com.bit.client.R;
import com.bit.entities.Eventos;
import com.bit.entities.Transaccion;
import com.bit.singletons.TransactionHashmapCollectionSingleton;

import java.util.ArrayList;
import java.util.List;

public class TransaccionFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private TransactionsItemListAdapter adapter;
    static Button btn_close;
    static ListView lv3;
    private SwipeRefreshLayout swipeLayout;

    public TransaccionFragment(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_transactions_list, container, false);

        //refresh
        this.swipeLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);
        this.swipeLayout.setOnRefreshListener(this);
        this.swipeLayout.setColorScheme(17170459, 17170452, 17170456, 17170454);

        /*((TextView) rootView.findViewById(R.id.tx_nombre)).setText(nombre_usuario != null ? nombre_usuario.toString() : "");*/
        lv3 = (ListView) rootView.findViewById(R.id.product_list);
        try {
            List<Transaccion> final_list;

//            GetTransactionsTask task_3 = new GetTransactionsTask(getActivity());
//            task_3.setIdUsuario(TransactionHashmapCollectionSingleton.getInstance().user.getIdUsuario());
//            TransactionHashmapCollectionSingleton.getInstance().transacciones = (List) task_3.execute(new Void[0]).get();

            TransactionHashmapCollectionSingleton.getInstance();
            if (TransactionHashmapCollectionSingleton.transacciones != null) {
                TransactionHashmapCollectionSingleton.getInstance();
                final_list = TransactionHashmapCollectionSingleton.getInstance().transacciones;
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

        //Parar refresh a menos q esta al tope la lista
        lv3.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                boolean enable = false;
                if(lv3 != null && lv3.getChildCount() > 0){
                    // check if the first item of the list is visible
                    boolean firstItemVisible = lv3.getFirstVisiblePosition() == 0;
                    // check if the top of the first item is visible
                    boolean topOfFirstItemVisible = lv3.getChildAt(0).getTop() == 0;
                    // enabling or disabling the refresh layout
                    enable = firstItemVisible && topOfFirstItemVisible;
                }
                swipeLayout.setEnabled(enable);
            }
        });

        return rootView;
    }

    class runneable implements Runnable {
        runneable() {
        }

        public void run() {
            swipeLayout.setRefreshing(false);
            try {
                List<Transaccion> final_list;

                GetTransactionsTask task_3 = new GetTransactionsTask(getActivity());
                task_3.setIdUsuario(TransactionHashmapCollectionSingleton.getInstance().user.getIdUsuario());
                final_list = TransactionHashmapCollectionSingleton.getInstance().transacciones = (List) task_3.execute(new Void[0]).get();

                lv3.setAdapter(new TransactionsItemListAdapter(getActivity().getBaseContext(), final_list));
                ((AvataresItemListAdapter) lv3.getAdapter()).notifyDataSetChanged();
            } catch (Exception e) {
            }
        }
    }

    public void onRefresh() {
        new Handler().postDelayed(new runneable(), 5000);
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
}
