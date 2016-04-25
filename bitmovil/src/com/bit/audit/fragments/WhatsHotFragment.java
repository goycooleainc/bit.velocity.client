package com.bit.audit.fragments;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.bit.client.R;
import com.bit.vending.StartActivity;

public class WhatsHotFragment extends Fragment {

    public WhatsHotFragment(){}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_new_action);
        dialog.setTitle("BITMOVIL : MOBILE PAYMENTS");

        TextView texto = (TextView) dialog.findViewById(R.id.dialogText);
        texto.setText("Esta seguro de Salir y cerrar Sesion?");

        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dialog.dismiss();
                    Intent mainIntent = new Intent(getActivity(), StartActivity.class);
                    mainIntent.putExtra("usuario", "");
                    mainIntent.putExtra("password", "");
                    mainIntent.putExtra("ip", "");
                    startActivity(mainIntent);
                } catch (Exception ex) {
                    ex.toString();
                }
            }
        });

        Button dialogCancelButton = (Button) dialog.findViewById(R.id.dialogButtonCancel);
        // if button is clicked, close the custom dialog
        dialogCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                //Al no aceptar ...
            }
        });

        dialog.show();

        View rootView = inflater.inflate(R.layout.fragment_whats_hot, container, false);
         
        return rootView;
    }
}
