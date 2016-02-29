package com.bit.audit.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import com.bit.client.R;
import com.bit.singletons.TransactionHashmapCollectionSingleton;
import com.bit.singletons.VentaHashmapCollectionSingleton;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class HomeFragment extends Fragment {

    static int _position;
    private ArrayAdapter<String> adapter_items;
    private int id;

    /* renamed from: com.bit.audit.fragments.VendingMachineActivity.MenuFragment.1 */
    class C01001 implements View.OnClickListener {
        C01001() {
        }

        public void onClick(View v) {
            VendingMachineActivity.mViewPager.setCurrentItem(1, true);
        }
    }

    /* renamed from: com.bit.audit.fragments.VendingMachineActivity.MenuFragment.2 */
    class C01012 implements View.OnClickListener {
        C01012() {
        }

        public void onClick(View v) {
            VendingMachineActivity.mViewPager.setCurrentItem(2, true);
        }
    }

	public HomeFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ((TextView) rootView.findViewById(R.id.tx_nombre)).setText(VendingMachineActivity.nombre_usuario != null ? VendingMachineActivity.nombre_usuario.toString() : "");
        VendingMachineActivity.txBalance = (TextView) rootView.findViewById(R.id.txBalance);
        DecimalFormat df = new DecimalFormat("#,##0.00");
        df.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ITALY));
        TransactionHashmapCollectionSingleton.getInstance();
        if(TransactionHashmapCollectionSingleton.estadoCuenta == null) {
//        VentaHashmapCollectionSingleton.getInstance();
//        if(VentaHashmapCollectionSingleton.estadoCuenta == null) {
            VendingMachineActivity.txBalance.setText("$ 0");
        }else{
            VendingMachineActivity.txBalance.setText("$" + df.format(new BigDecimal(TransactionHashmapCollectionSingleton.estadoCuenta.getSaldo().toString())));
//            VendingMachineActivity.txBalance.setText("$" + df.format(new BigDecimal(VentaHashmapCollectionSingleton.estadoCuenta.getSaldo().toString())));
        }
        VendingMachineActivity.btnVending = (ImageButton) rootView.findViewById(R.id.btnVendingMachine);
        try {
            VendingMachineActivity.btnAccessControl.setOnClickListener(new C01001());
            VendingMachineActivity.btnVending.setOnClickListener(new C01012());
        } catch (Exception ex) {
            ex.toString();
        }
        return rootView;
    }
}
