package com.bit.audit.fragments;

import android.app.Activity;
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
import android.widget.LinearLayout;
import android.widget.ListView;

import com.bit.adapters.AvataresItemListAdapter;
import com.bit.adapters.CortesiasEventoItemListAdapter;
import com.bit.async.tasks.GetCortesiasEventoTask;
import com.bit.client.R;
import com.bit.entities.Cortesia;
import com.bit.singletons.TransactionHashmapCollectionSingleton;

import java.util.ArrayList;
import java.util.List;

public class MisCortesiasEventoFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private CortesiasEventoItemListAdapter adapter;
    private Cortesia obj;
    static Button btn_close, btn_ok;
    static ListView lv1;
    private LinearLayout linlaHeaderProgress;
    private View rootView;
    private Activity activity;
    private SwipeRefreshLayout swipeLayout;

    class ShowListCortesiaEvento implements AdapterView.OnItemClickListener {
        final List final_list;

        class ShowModalCortesiaEvento implements View.OnClickListener {
            final Dialog val$dialog;

            ShowModalCortesiaEvento(Dialog dialog) {
                this.val$dialog = dialog;
            }

            public void onClick(View v) {
                this.val$dialog.dismiss();
            }
        }

        public ShowListCortesiaEvento(List list) {
            this.final_list = list;
        }

        public void onItemClick(AdapterView<?> adapterView, final View v, int position, long id) {
            final Dialog dialog = new Dialog(v.getContext());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.modal_evento_method_aceptar);

            btn_close = (Button) dialog.findViewById(R.id.dialogButtonCancel);
            btn_ok = (Button) dialog.findViewById(R.id.dialogButtonOK);

            obj = (Cortesia) this.final_list.get(position);

            btn_close.setOnClickListener(new ShowModalCortesiaEvento(dialog));

//            btn_ok.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    AlertDialog.Builder bld = new AlertDialog.Builder(v.getContext());
//
//                    //Realizo una transaccion al server para registrar
//                    String avatar;
//                    if (TransactionHashmapCollectionSingleton.avatar != null) {
//                        avatar = TransactionHashmapCollectionSingleton.avatar.getCodigo();
//                    } else {
//                        avatar = TransactionHashmapCollectionSingleton.avatares.get(0).getCodigo();
//                    }
//                    obj.setIdAvatar(avatar);
//
//                    String remoteURL = getActivity().getApplicationContext().getString(R.string.sendEventoQuemado);
//                    PostAsynkTasks task = new PostAsynkTasks(v, activity, bld, remoteURL);
//                    task.setDATA(new Gson().toJson(obj));
//                    task.execute();
//
//                    Toast.makeText(activity, "Preparando Transacción", Toast.LENGTH_SHORT).show();
//
//                    //Wait for 2 seconds
//                    Handler handler = new Handler();
//
//                    while(task.checkStatus().equals("")){
//                        handler.postDelayed(new Runnable() {
//                            public void run() {
//
//                            }
//                        }, 2000);
//                    }
//
//                    if(task.checkStatus().equals("EXITO")) {
//
//                        //Buscar forma de pago
//
//                        final Dialog dialog2 = new Dialog(v.getContext());
//
//                        dialog2.setContentView(R.layout.modal_avatar_method_select_type);
//
//                        btn_close = (Button) dialog2.findViewById(R.id.dialogButtonCancel);
//                        btn_ok = (Button) dialog2.findViewById(R.id.dialogButtonOK);
//
//                        final Spinner s = (Spinner) dialog2.findViewById(R.id.spinner_state);
//                        s.setAdapter(new ArrayAdapter(dialog2.getContext(), R.layout.spinner_item, new String[]{"NFC", "QR"}));
//
//                        btn_close.setOnClickListener(new ShowModalCortesiaEvento(dialog2));
//
//                        btn_ok.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v2) {
//
//                                int position = s.getSelectedItemPosition();
//
//                                switch (position) {
//                                    case 0:
//                                        final Intent intent = new Intent(getActivity(), SettingsActivity.class);
//                                        getActivity().finish();
//                                        startActivity(intent);
//                                        break;
//                                    case 1:
//                                        BarcodeFormat format = BarcodeFormat.QR_CODE;
//                                        generateCode(format, v2, true);
//                                        break;
//                                }
//                                dialog2.dismiss();
//                            }
//                        });
//
//                        dialog2.setTitle("AVATAR - BITMOVIL");
//                        dialog2.show();
//                    }
//                    dialog.dismiss();
//                }
//            });

            dialog.setTitle("CORTESIAS - BITMOVIL");
            dialog.show();
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_cortesias_evento_list, container, false);

        //refresh
        this.swipeLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);
        this.swipeLayout.setOnRefreshListener(this);
        this.swipeLayout.setColorScheme(17170459, 17170452, 17170456, 17170454);

        activity = getActivity();
        linlaHeaderProgress = (LinearLayout) rootView.findViewById(R.id.linlaHeaderProgress);

        /*((TextView) rootView.findViewById(R.id.user_nombre)).setText(intent.getStringExtra("nombre") != null ? intent.getStringExtra("nombre").toString() : "");*/
        lv1 = (ListView) rootView.findViewById(R.id.cortesia_evento_list);
        try {
            List<Cortesia> final_list;
            TransactionHashmapCollectionSingleton.getInstance();
            if (TransactionHashmapCollectionSingleton.cortesiaEvento != null) {
                TransactionHashmapCollectionSingleton.getInstance();
                final_list = TransactionHashmapCollectionSingleton.cortesiaEvento;
            } else {
                final_list = new ArrayList();
            }
            this.adapter = new CortesiasEventoItemListAdapter(getActivity().getBaseContext(), final_list);
            lv1.setAdapter(this.adapter);
            this.adapter.notifyDataSetChanged();
            lv1.setOnItemClickListener(new ShowListCortesiaEvento(final_list));
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

    class runneable implements Runnable {
        runneable() {
        }

        public void run() {
            swipeLayout.setRefreshing(false);
            try {
                List<Cortesia> final_list;

                GetCortesiasEventoTask task_3 = new GetCortesiasEventoTask(getActivity().getBaseContext());
                task_3.setIdUsuario(TransactionHashmapCollectionSingleton.getInstance().user.getIdUsuario());
                final_list = TransactionHashmapCollectionSingleton.cortesiaEvento = (List) task_3.execute(new Void[0]).get();

                lv1.setAdapter(new CortesiasEventoItemListAdapter(getActivity().getBaseContext(), final_list));
                ((AvataresItemListAdapter) lv1.getAdapter()).notifyDataSetChanged();
            } catch (Exception e) {
            }
        }
    }

    public void onRefresh() {
        new Handler().postDelayed(new runneable(), 5000);
    }
}
