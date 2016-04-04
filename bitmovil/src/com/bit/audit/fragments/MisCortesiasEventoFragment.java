package com.bit.audit.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import com.bit.adapters.CortesiasEventoItemListAdapter;
import com.bit.async.tasks.GetCortesiasEventoTask;
import com.bit.async.tasks.PostAsynkTasks;
import com.bit.client.R;
import com.bit.entities.Avatar;
import com.bit.entities.Cortesia;
import com.bit.singletons.TransactionHashmapCollectionSingleton;
import com.bit.vending.SettingsActivity;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code39Writer;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MisCortesiasEventoFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private CortesiasEventoItemListAdapter adapter;
    private Cortesia obj;
    static Button btn_close, btn_ok, btn_close2;
    static ListView lv1;
    static ListView lv2;
    private LinearLayout linlaHeaderProgress;
    private View rootView;
    private Activity activity;
    private String idUser;

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
            dialog.setContentView(R.layout.modal_evento_method_aceptar);

            btn_close = (Button) dialog.findViewById(R.id.dialogButtonCancel);
            btn_ok = (Button) dialog.findViewById(R.id.dialogButtonOK);

            obj = (Cortesia) this.final_list.get(position);

            btn_close.setOnClickListener(new ShowModalCortesiaEvento(dialog));

            btn_ok.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    AlertDialog.Builder bld = new AlertDialog.Builder(v.getContext());

                    //Realizo una transaccion al server para registrar
                    String avatar;
                    if (TransactionHashmapCollectionSingleton.avatar != null) {
                        avatar = TransactionHashmapCollectionSingleton.avatar.getCodigo();
                    } else {
                        avatar = TransactionHashmapCollectionSingleton.avatares.get(0).getCodigo();
                    }
                    obj.setIdAvatar(avatar);

                    String remoteURL = getActivity().getApplicationContext().getString(R.string.sendEventoQuemado);
                    PostAsynkTasks task = new PostAsynkTasks(rootView, activity, bld, remoteURL);
                    task.setDATA(new Gson().toJson(obj));
                    task.execute();

                    //Buscar forma de pago

                    final Dialog dialog2 = new Dialog(v.getContext());

                    dialog2.setContentView(R.layout.modal_avatar_method_select_type);

                    btn_close = (Button) dialog2.findViewById(R.id.dialogButtonCancel);
                    btn_ok = (Button) dialog2.findViewById(R.id.dialogButtonOK);

                    final Spinner s = (Spinner) dialog2.findViewById(R.id.spinner_state);
                    s.setAdapter(new ArrayAdapter(dialog2.getContext(), R.layout.spinner_item, new String[]{"NFC", "QR"}));

                    btn_close.setOnClickListener(new ShowModalCortesiaEvento(dialog));

                    btn_ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v2) {

                            int position = s.getSelectedItemPosition();

                            switch (position) {
                                case 0:
                                    final Intent intent = new Intent(getActivity(), SettingsActivity.class);
                                    getActivity().finish();
                                    startActivity(intent);
                                    break;
                                case 1:
                                    BarcodeFormat format = BarcodeFormat.QR_CODE;
                                    generateCode(format, v2, true);
                                    break;
                            }
                            dialog2.dismiss();
                        }
                    });

                    dialog2.setTitle("AVATAR - BITMOVIL");
                    dialog2.show();
                    dialog.dismiss();
                }
            });

            dialog.setTitle("CORTESIAS - BITMOVIL");
            dialog.show();
        }
    }

    public void onRefresh() {
        List<Cortesia> final_list;
        GetCortesiasEventoTask task_3 = new GetCortesiasEventoTask(getActivity().getBaseContext());
        task_3.setIdUsuario(TransactionHashmapCollectionSingleton.getInstance().user.getIdUsuario());

        idUser = TransactionHashmapCollectionSingleton.getInstance().user.getIdUsuario();

        try {
            TransactionHashmapCollectionSingleton.cortesiaEvento = (List) task_3.execute(new Void[0]).get();
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
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_cortesias_evento_list, container, false);
        Intent intent = getActivity().getIntent();
        activity = getActivity();
        linlaHeaderProgress = (LinearLayout) rootView.findViewById(R.id.linlaHeaderProgress);

        ((TextView) rootView.findViewById(R.id.user_nombre)).setText(intent.getStringExtra("nombre") != null ? intent.getStringExtra("nombre").toString() : "");
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
        return rootView;
    }

    public void generateCode(BarcodeFormat format, View v2, boolean isQR){
        final Dialog dialog2 = new Dialog(v2.getContext());
        dialog2.setContentView(R.layout.modal_avatar_by_qr);

        btn_close2 = (Button) dialog2.findViewById(R.id.dialogButtonCancel);
        ImageView img = (ImageView) dialog2.findViewById(R.id.img_result_qr);

        btn_close2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.dismiss();
            }
        });

        String codigo = null;
        if (TransactionHashmapCollectionSingleton.avatares != null) {
            TransactionHashmapCollectionSingleton.getInstance();
            List<Avatar> final_list = TransactionHashmapCollectionSingleton.avatares;
            codigo = final_list.get(0).getCodigo() + "-" + obj.getIdEvento() + "-E";
        }
        QRCodeWriter writer = new QRCodeWriter();
        Code39Writer writer2 = new Code39Writer();
        BitMatrix bitMatrix;
        try{
            if(isQR) {
                bitMatrix = writer.encode(codigo, format, 512, 512);
            }else {
                bitMatrix = writer2.encode(codigo, format, 512, 256);
            }

            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }

            img.setImageBitmap(bmp);

        } catch (WriterException e) {
            e.printStackTrace();
        }
        dialog2.show();
    }
}
