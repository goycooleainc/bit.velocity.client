package com.bit.audit.fragments;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.bit.adapters.AvataresItemListAdapter;
import com.bit.async.tasks.UpdateAvatarTask;
import com.bit.client.R;
import com.bit.entities.Avatar;
import com.bit.singletons.TransactionHashmapCollectionSingleton;
import com.bit.singletons.VentaHashmapCollectionSingleton;
import com.bit.vending.SettingsActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class FindPeopleFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private AvataresItemListAdapter adapter;
    private SwipeRefreshLayout swipeLayout;
    private static Button btn_ok;
    static ImageButton btnOk;
    static Button btn_close;
    static ImageButton btn_discard, btnNew;
    static ImageButton btn_save;
    static ListView lv3;
    static String nombre_usuario;

	public FindPeopleFragment(){}

    /* renamed from: com.bit.audit.fragments.VendingMachineActivity.AvatarFragment.1 */
    class C00961 implements AdapterView.OnItemClickListener {
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

            btn_close = (Button) dialog.findViewById(R.id.dialogButtonCancel);
            btn_ok = (Button) dialog.findViewById(R.id.dialogButtonOK);

            final TextView descr = (TextView) dialog.findViewById(R.id.txDescr);
            final TextView coder = (TextView) dialog.findViewById(R.id.txCode);

            final Avatar obj = (Avatar) this.val$final_list.get(position);

            coder.setText(obj.getCodigo().toString());
            descr.setText(obj.getDescripcion().toString());

            final Spinner s = (Spinner) dialog.findViewById(R.id.spinner_state);
            s.setAdapter(new ArrayAdapter(dialog.getContext(), R.layout.spinner_item, new String[]{"ACTIVO", "INACTIVO"}));
            s.setSelection(obj.getEstado());

            btn_close.setOnClickListener(new C00951(dialog));

            btn_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    obj.setCodigo(coder.getText().toString());
                    obj.setDescripcion(descr.getText().toString());
                    obj.setEstado(s.getSelectedItemPosition());
                    obj.setIdUser(Integer.parseInt(TransactionHashmapCollectionSingleton.getInstance().user.getIdUsuario()));
//                    obj.setIdUser(Integer.parseInt(VentaHashmapCollectionSingleton.getInstance().user.getIdUsuario()));

                    UpdateAvatarTask task = new UpdateAvatarTask(getActivity());
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
            swipeLayout.setRefreshing(false);
            try {
                List<Avatar> final_list;
                TransactionHashmapCollectionSingleton.getInstance();
                if (TransactionHashmapCollectionSingleton.avatares != null) {
                    TransactionHashmapCollectionSingleton.getInstance();
                    final_list = TransactionHashmapCollectionSingleton.avatares;
//                VentaHashmapCollectionSingleton.getInstance();
//                if (VentaHashmapCollectionSingleton.avatares != null) {
//                    VentaHashmapCollectionSingleton.getInstance();
//                    final_list = VentaHashmapCollectionSingleton.avatares;
                } else {
                    final_list = new ArrayList();
                }
                lv3.setAdapter(new AvataresItemListAdapter(getActivity().getBaseContext(), final_list));
                ((AvataresItemListAdapter) lv3.getAdapter()).notifyDataSetChanged();
            } catch (Exception e) {
            }
        }
    }

    public void onResume() {
        List<Avatar> final_list;
        super.onResume();
        lv3 = (ListView) getActivity().findViewById(R.id.current_purchase_list);
        TransactionHashmapCollectionSingleton.getInstance();
        if (TransactionHashmapCollectionSingleton.avatares != null) {
            TransactionHashmapCollectionSingleton.getInstance();
            final_list = TransactionHashmapCollectionSingleton.avatares;
//        VentaHashmapCollectionSingleton.getInstance();
//        if (VentaHashmapCollectionSingleton.avatares != null) {
//            VentaHashmapCollectionSingleton.getInstance();
//            final_list = VentaHashmapCollectionSingleton.avatares;
        } else {
            final_list = new ArrayList();
        }
        this.adapter = new AvataresItemListAdapter(getActivity().getBaseContext(), final_list);
        lv3.setAdapter(this.adapter);
        this.adapter.notifyDataSetChanged();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_avatar_list, container, false);
        ((TextView) rootView.findViewById(R.id.tx_nombre)).setText(nombre_usuario != null ? nombre_usuario.toString() : "");
        this.swipeLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);
        this.swipeLayout.setOnRefreshListener(this);
        this.swipeLayout.setColorScheme(17170459, 17170452, 17170456, 17170454);
        lv3 = (ListView) rootView.findViewById(R.id.current_purchase_list);
        btnNew = (ImageButton) rootView.findViewById(R.id.btnNew);

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);

               /* final Dialog dialog = new Dialog(v.getContext());
                dialog.setContentView(R.layout.activity_settings);

                btn_close = (Button) dialog.findViewById(R.id.btnCloseSettings);
                btn_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                    }
                });

                dialog.setTitle("AVATAR - BITMOVIL");
                dialog.show();*/
            }
        });

        try {
            List<Avatar> final_list;
            TransactionHashmapCollectionSingleton.getInstance();
            if (TransactionHashmapCollectionSingleton.avatares != null) {
                TransactionHashmapCollectionSingleton.getInstance();
                final_list = TransactionHashmapCollectionSingleton.avatares;
                TransactionHashmapCollectionSingleton.getInstance();
                TransactionHashmapCollectionSingleton.avatar = (Avatar) final_list.get(0);
//            VentaHashmapCollectionSingleton.getInstance();
//            if (VentaHashmapCollectionSingleton.avatares != null) {
//                VentaHashmapCollectionSingleton.getInstance();
//                final_list = VentaHashmapCollectionSingleton.avatares;
//                VentaHashmapCollectionSingleton.getInstance();
//                VentaHashmapCollectionSingleton.avatar = (Avatar) final_list.get(0);
            } else {
                final_list = new ArrayList();
            }
            this.adapter = new AvataresItemListAdapter(getActivity().getBaseContext(), final_list);
            lv3.setAdapter(this.adapter);
            lv3.setAdapter(this.adapter);
            this.adapter.notifyDataSetChanged();
            lv3.setOnItemClickListener(new C00961(final_list));
        } catch (Exception ex) {
            ex.toString();
        }
        return rootView;
    }

    public void onRefresh() {
        new Handler().postDelayed(new C00972(), 5000);
    }
	/*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_find_people, container, false);
         
        return rootView;
    }*/
}
