package com.bit.mediators;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.bit.client.R;

import java.util.List;

public class SpinnerMediator {
	public SpinnerMediator()
	{
		
	}
	
	public static void setSpinnerView(Activity view, int id, List<String> collection, int idView){
		try{
		 ArrayAdapter<String> adapter = new ArrayAdapter<String>(view, R.layout.spinner_item, collection);
         adapter.setDropDownViewResource(R.layout.spinner_dropdown);
         Spinner Items = (Spinner) view.findViewById(idView);
         Items.setAdapter(adapter);
         if(id!=-1){
         Items.setSelection(id);
         }
		}catch(Exception ex){
			ex.toString();
		}
	}
}
