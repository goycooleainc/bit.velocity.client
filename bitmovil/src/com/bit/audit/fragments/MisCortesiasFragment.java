package com.bit.audit.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bit.client.R;
import com.bit.entities.Avatar;
import com.bit.singletons.TransactionHashmapCollectionSingleton;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code39Writer;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.List;

public class MisCortesiasFragment extends Fragment {

    static Button buttonEventos;
    static Button buttonCombos;
    static Button btn_close2;
    private LinearLayout linlaHeaderProgress;
    private View rootView;
    private Activity activity;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_choise_tipo_cortesia, container, false);
        Intent intent = getActivity().getIntent();
        activity = getActivity();
        linlaHeaderProgress = (LinearLayout) rootView.findViewById(R.id.linlaHeaderProgress);

        buttonEventos = (Button) rootView.findViewById(R.id.buttonEventos);
        buttonCombos = (Button) rootView.findViewById(R.id.buttonCombos);

        buttonCombos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                fragment = new MisCortesiasComboFragment();
                callFragment(fragment);
            }
        });

        buttonEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                fragment = new MisCortesiasEventoFragment();
                callFragment(fragment);
            }
        });
        return rootView;
    }

    public void callFragment(Fragment fragment){
        if (fragment != null) {

            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();

        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
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
