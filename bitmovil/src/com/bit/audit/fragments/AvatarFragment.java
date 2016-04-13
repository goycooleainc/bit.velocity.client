package com.bit.audit.fragments;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bit.adapters.AvataresItemListAdapter;
import com.bit.adapters.CortesiasEventoItemListAdapter;
import com.bit.async.tasks.GetCortesiasEventoTask;
import com.bit.async.tasks.UpdateAvatarTask;
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

import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class AvatarFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private AvataresItemListAdapter adapter;
    static ImageButton onNew;
    static Button btn_ok;
    static Button btn_close, btn_close2;
    static ListView lv3;
    static String nombre_usuario;

	public AvatarFragment(){}


    /* renamed from: com.bit.audit.fragments.VendingMachineActivity.AvatarFragment.1 */
    class C00961 implements AdapterView.OnItemClickListener {
        final List val$final_list;

        /* renamed from: com.bit.audit.fragments.VendingMachineActivity.AvatarFragment.1.1 */
        class C00951 implements View.OnClickListener {
            final Dialog val$dialog;

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

                    TransactionHashmapCollectionSingleton.getInstance().avatar = obj;

                    UpdateAvatarTask task = new UpdateAvatarTask(getActivity());
                    task.setDATA(new Gson().toJson(obj));
                    task.execute();

                    Toast.makeText(getActivity().getBaseContext(), "Avatar Actualizado............. [IN PROGRESS]", Toast.LENGTH_LONG).show();

                    onRefresh();

                    dialog.dismiss();
                }
            });

            dialog.setTitle("AVATAR - BITMOVIL");
            dialog.show();
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
        } else {
            final_list = new ArrayList();
        }
        this.adapter = new AvataresItemListAdapter(getActivity().getBaseContext(), final_list);
        lv3.setAdapter(this.adapter);
        this.adapter.notifyDataSetChanged();
    }

    public void onRefresh() {
        List<Avatar> final_list;
        try {
            TransactionHashmapCollectionSingleton.getInstance();
            if (TransactionHashmapCollectionSingleton.avatares != null) {
                TransactionHashmapCollectionSingleton.getInstance();
                final_list = TransactionHashmapCollectionSingleton.avatares;
                TransactionHashmapCollectionSingleton.getInstance();
                TransactionHashmapCollectionSingleton.avatar = (Avatar) final_list.get(0);
            } else {
                final_list = new ArrayList();
            }
            this.adapter = new AvataresItemListAdapter(getActivity().getBaseContext(), final_list);
            lv3.setAdapter(this.adapter);
            this.adapter.notifyDataSetChanged();
            lv3.setOnItemClickListener(new C00961(final_list));
        } catch (Exception ex) {
            ex.toString();
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Intent intent = getActivity().getIntent();
        View rootView = inflater.inflate(R.layout.fragment_avatar_list, container, false);
        ((TextView) rootView.findViewById(R.id.tx_nombre)).setText(intent.getStringExtra("nombre") != null ? intent.getStringExtra("nombre").toString() : "");
        lv3 = (ListView) rootView.findViewById(R.id.current_purchase_list);

        onNew = (ImageButton) rootView.findViewById(R.id.onNew);
        onNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(v.getContext());
                dialog.setContentView(R.layout.modal_avatar_method_select_type);

                btn_close = (Button) dialog.findViewById(R.id.dialogButtonCancel);
                btn_ok = (Button) dialog.findViewById(R.id.dialogButtonOK);

                final Spinner s = (Spinner) dialog.findViewById(R.id.spinner_state);
                s.setAdapter(new ArrayAdapter(dialog.getContext(), R.layout.spinner_item, new String[]{"NFC", "QR"}));
//                s.setAdapter(new ArrayAdapter(dialog.getContext(), R.layout.spinner_item, new String[]{"NFC", "QR", "CODIGO DE BARRAS"}));

                btn_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

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
//                            case 2:
//                                BarcodeFormat format2 = BarcodeFormat.CODE_39;
//                                generateCode(format2, v2, false);
//                                break;
                        }
                        dialog.dismiss();
                    }
                });

                dialog.setTitle("AVATAR - BITMOVIL");
                dialog.show();
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
            } else {
                final_list = new ArrayList();
            }
            this.adapter = new AvataresItemListAdapter(getActivity().getBaseContext(), final_list);
            lv3.setAdapter(this.adapter);
            this.adapter.notifyDataSetChanged();
            lv3.setOnItemClickListener(new C00961(final_list));
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
            codigo = final_list.get(0).getCodigo() + "-A";
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
