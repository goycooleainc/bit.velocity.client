package com.bit.audit.fragments;

import android.app.Activity;
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
import android.widget.TextView;

import com.bit.adapters.CortesiaProductoComboListAdapter;
import com.bit.adapters.CortesiasComboItemListAdapter;
import com.bit.async.tasks.GetCortesiasComboTask;
import com.bit.client.R;
import com.bit.entities.Avatar;
import com.bit.entities.Cortesia;
import com.bit.entities.Productos;
import com.bit.singletons.TransactionHashmapCollectionSingleton;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MisCortesiasComboFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private CortesiasComboItemListAdapter adapter;
    private Cortesia obj;
    static Button btn_close, btn_ok, btn_close2;
    static ListView lv1;
    static ListView lv2;
    private LinearLayout linlaHeaderProgress;
    private View rootView;
    private Activity activity;
    private String idUser;

    class ShowListCortesiaCombo implements AdapterView.OnItemClickListener {
        final List final_list;

        class ShowModalCortesiaCombo implements View.OnClickListener {
            final Dialog val$dialog;

            ShowModalCortesiaCombo(Dialog dialog) {
                this.val$dialog = dialog;
            }

            public void onClick(View v) {
                this.val$dialog.dismiss();
            }
        }

        public ShowListCortesiaCombo(List list) {
            this.final_list = list;
        }

        public void onItemClick(AdapterView<?> adapterView, final View v, int position, long id) {
            final Dialog dialog = new Dialog(v.getContext());
            dialog.setContentView(R.layout.modal_producto_detalle_combo_method);

            btn_close = (Button) dialog.findViewById(R.id.dialogButtonCancel);
            btn_ok = (Button) dialog.findViewById(R.id.dialogButtonOK);

            lv2 = (ListView) dialog.findViewById(R.id.producto_list);
            obj = (Cortesia) this.final_list.get(position);

            if(obj.getProductoCantidad() != null) {
                List<String> productoCantidad = getListToShow(obj.getProductoCantidad());
                lv2.setAdapter(new CortesiaProductoComboListAdapter(getActivity().getApplicationContext(), productoCantidad));
            }

            final int idCombo = obj.getId();

            btn_close.setOnClickListener(new ShowModalCortesiaCombo(dialog));

//            btn_ok.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    AlertDialog.Builder bld = new AlertDialog.Builder(v.getContext());
//
//                    List<String> listNombreProductos = new ArrayList<String>();
//                    ListAdapter a = lv2.getAdapter();
//                    for (int i = 0; i < a.getCount(); i++) {
//                        if (((CheckBox) lv2.findViewById(i)).isChecked()) {
//                            listNombreProductos.add((String) a.getItem(i));
//                        }
//                    }
//
//                    List<Productos> productos = getProducts();
//                    double precio = 0;
//                    for (String nombreProducto : listNombreProductos) {
//                        for (Productos pro : productos) {
//                            if (pro.getNombre().equals(nombreProducto) && !pro.getPrecio().equals("")) {
//                                precio = precio + Double.parseDouble(pro.getPrecio());
//                                break;
//                            }
//                        }
//                    }
//
//                    obj.setListP(listNombreProductos);
//
//                    String avatar;
//                    if (TransactionHashmapCollectionSingleton.avatar != null) {
//                        avatar = TransactionHashmapCollectionSingleton.avatar.getCodigo();
//                    } else {
//                        avatar = TransactionHashmapCollectionSingleton.avatares.get(0).getCodigo();
//                    }
//                    obj.setIdAvatar(avatar);
//
//                    String remoteURL = getActivity().getApplicationContext().getString(R.string.sendProductosQuemados);
//                    PostAsynkTasks task = new PostAsynkTasks(rootView, activity, bld, remoteURL);
//                    task.setDATA(new Gson().toJson(obj));
//                    task.execute();
//
//                    Toast.makeText(activity, "Preparando Transacción", Toast.LENGTH_SHORT).show();
//                    //Buscar forma de pago
//                    final Dialog dialog2 = new Dialog(v.getContext());
//
//                    dialog2.setContentView(R.layout.modal_avatar_method_select_type);
//
//                    btn_close = (Button) dialog2.findViewById(R.id.dialogButtonCancel);
//                    btn_ok = (Button) dialog2.findViewById(R.id.dialogButtonOK);
//
//                    final Spinner s = (Spinner) dialog2.findViewById(R.id.spinner_state);
//                    s.setAdapter(new ArrayAdapter(dialog2.getContext(), R.layout.spinner_item, new String[]{"NFC", "QR"}));
//
//                    btn_close.setOnClickListener(new ShowModalCortesiaCombo(dialog));
//
//                    btn_ok.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v2) {
//
//                            int position = s.getSelectedItemPosition();
//
//                            switch (position) {
//                                case 0:
//                                    final Intent intent = new Intent(getActivity(), SettingsActivity.class);
//                                    getActivity().finish();
//                                    startActivity(intent);
//                                    break;
//                                case 1:
//                                    BarcodeFormat format = BarcodeFormat.QR_CODE;
//                                    generateCode(format, v2, true);
//                                    break;
//                            }
//                            dialog2.dismiss();
//                        }
//                    });
//
//                    dialog2.setTitle("AVATAR - BITMOVIL");
//                    dialog2.show();
//                    dialog.dismiss();
//                }
//            });

            dialog.setTitle("CORTESIAS - BITMOVIL");
            dialog.show();
        }
    }

    public List<Productos> getProducts(){

        List<Productos> final_list = null;
        TransactionHashmapCollectionSingleton.getInstance();
        if (TransactionHashmapCollectionSingleton.producto != null) {
            TransactionHashmapCollectionSingleton.getInstance();
            final_list = TransactionHashmapCollectionSingleton.producto;
            TransactionHashmapCollectionSingleton.getInstance();
            TransactionHashmapCollectionSingleton.producto = final_list;
        } else {
            final_list = new ArrayList();
        }
        return final_list;
    }

    public List<String> getListToShow(Map<String, String> listData){

        List<String> finalListData = new ArrayList<>();

        List<Productos> final_list = getProducts();

        Iterator iterator = listData.keySet().iterator();
        while(iterator.hasNext()) {
            String idProducto = (String) iterator.next();

            for(Productos producto: final_list) {

                if (producto.getId() ==  (Integer.parseInt(idProducto))) {

                    String cantidad = (String) listData.get(idProducto);

                    if(!cantidad.equals(0)) {
                        int cant = Integer.parseInt(cantidad);
                        for (int i = 0; i < cant; i++) {

                            finalListData.add(producto.getNombre());
                        }
                    }
                    break;
                }
            }
        }
        return finalListData;
    }

    public void onRefresh() {
        List<Cortesia> final_list;
        GetCortesiasComboTask task_3 = new GetCortesiasComboTask(getActivity().getBaseContext());
        task_3.setIdUsuario(TransactionHashmapCollectionSingleton.getInstance().user.getIdUsuario());

        idUser = TransactionHashmapCollectionSingleton.getInstance().user.getIdUsuario();

        try {
            TransactionHashmapCollectionSingleton.cortesiaCombos = (List) task_3.execute(new Void[0]).get();
            TransactionHashmapCollectionSingleton.getInstance();
            if (TransactionHashmapCollectionSingleton.cortesiaCombos != null) {
                TransactionHashmapCollectionSingleton.getInstance();
                final_list = TransactionHashmapCollectionSingleton.cortesiaCombos;
            } else {
                final_list = new ArrayList();
            }
            this.adapter = new CortesiasComboItemListAdapter(getActivity().getBaseContext(), final_list);
            lv1.setAdapter(this.adapter);
            this.adapter.notifyDataSetChanged();
            lv1.setOnItemClickListener(new ShowListCortesiaCombo(final_list));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_cortesias_combo_list, container, false);
        Intent intent = getActivity().getIntent();
        activity = getActivity();
        linlaHeaderProgress = (LinearLayout) rootView.findViewById(R.id.linlaHeaderProgress);

        ((TextView) rootView.findViewById(R.id.user_nombre)).setText(intent.getStringExtra("nombre") != null ? intent.getStringExtra("nombre").toString() : "");
        lv1 = (ListView) rootView.findViewById(R.id.cortesia_combo_list);
        try {
            List<Cortesia> final_list;
            TransactionHashmapCollectionSingleton.getInstance();
            if (TransactionHashmapCollectionSingleton.cortesiaCombos != null) {
                TransactionHashmapCollectionSingleton.getInstance();
                final_list = TransactionHashmapCollectionSingleton.cortesiaCombos;
            } else {
                final_list = new ArrayList();
            }
            this.adapter = new CortesiasComboItemListAdapter(getActivity().getBaseContext(), final_list);
            lv1.setAdapter(this.adapter);
            this.adapter.notifyDataSetChanged();
            lv1.setOnItemClickListener(new ShowListCortesiaCombo(final_list));
        } catch (Exception ex) {
            ex.toString();
        }
        return rootView;
    }

//    public void generateCode(BarcodeFormat format, View v2, boolean isQR){
//        final Dialog dialog2 = new Dialog(v2.getContext());
//        dialog2.setContentView(R.layout.modal_avatar_by_qr);
//
//        btn_close2 = (Button) dialog2.findViewById(R.id.dialogButtonCancel);
//        ImageView img = (ImageView) dialog2.findViewById(R.id.img_result_qr);
//
//        btn_close2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog2.dismiss();
//            }
//        });
//
//        String codigo = null;
//        if (TransactionHashmapCollectionSingleton.avatares != null) {
//            TransactionHashmapCollectionSingleton.getInstance();
//            List<Avatar> final_list = TransactionHashmapCollectionSingleton.avatares;
//            codigo = final_list.get(0).getCodigo() + "-" + obj.getId() + "-C";
//        }
//        QRCodeWriter writer = new QRCodeWriter();
//        Code39Writer writer2 = new Code39Writer();
//        BitMatrix bitMatrix;
//        try{
//            if(isQR) {
//                bitMatrix = writer.encode(codigo, format, 512, 512);
//            }else {
//                bitMatrix = writer2.encode(codigo, format, 512, 256);
//            }
//
//            int width = bitMatrix.getWidth();
//            int height = bitMatrix.getHeight();
//            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
//            for (int x = 0; x < width; x++) {
//                for (int y = 0; y < height; y++) {
//                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
//                }
//            }
//
//            img.setImageBitmap(bmp);
//
//        } catch (WriterException e) {
//            e.printStackTrace();
//        }
//        dialog2.show();
//    }
}
