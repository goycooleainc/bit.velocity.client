package com.bit.audit.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bit.client.R;
import com.bit.singletons.TransactionHashmapCollectionSingleton;
import com.bit.vending.SettingsActivity;
import com.bit.vending.StartActivity;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class HomeFragment extends Fragment {

    static int _position;
    private ArrayAdapter<String> adapter_items;
    private int id;
    TextView txBalance;
    ImageButton btnVending;

    public HomeFragment(){}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
        Intent intent = getActivity().getIntent();
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        /*((TextView) rootView.findViewById(R.id.tx_nombre)).setText(intent.getStringExtra("nombre") != null ? intent.getStringExtra("nombre").toString() : "");*/
        txBalance = (TextView) rootView.findViewById(R.id.txBalance);
        DecimalFormat df = new DecimalFormat("#,##0.00");
        df.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ITALY));
        TransactionHashmapCollectionSingleton.getInstance();
        if(TransactionHashmapCollectionSingleton.estadoCuenta == null) {
            txBalance.setText("$ 0");
        }else{
            /*txBalance.setText("$" + df.format(new BigDecimal(TransactionHashmapCollectionSingleton.estadoCuenta.getSaldo().toString())));*/
            txBalance.setText(df.format(new BigDecimal(TransactionHashmapCollectionSingleton.estadoCuenta.getSaldo().toString())));
        }
        btnVending = (ImageButton) rootView.findViewById(R.id.btnVendingMachine);
        try {
//            VendingMachineActivity.btnAccessControl.setOnClickListener(new C01001());
//            VendingMachineActivity.btnVending.setOnClickListener(new C01012());
        } catch (Exception ex) {
            ex.toString();
        }
        return rootView;
    }
}
