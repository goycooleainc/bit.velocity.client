package com.bit.audit.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bit.client.R;

public class TiendaVirtualFragment extends Fragment {
	
	public TiendaVirtualFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_pages, container, false);
        Intent intent =  new Intent(getActivity(),com.goycooleainc.ui.MainActivity.class);
        startActivity(intent);
        return rootView;
    }
}
