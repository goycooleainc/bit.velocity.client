package com.bit.audit.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.bit.client.R;

public class MisCortesiasFragment extends Fragment {

    static Button buttonEventos;
    static Button buttonCombos;
    static Button btn_close2;
    private LinearLayout linlaHeaderProgress;
    private View rootView;
    private Activity activity;

    public MisCortesiasFragment(){}

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
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
}
