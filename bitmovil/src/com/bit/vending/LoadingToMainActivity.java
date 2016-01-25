package com.bit.vending;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import com.bit.client.R;

public class LoadingToMainActivity extends Activity {

    //Introduce an delay
    private final int WAIT_TIME = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        System.out.println("Cargando la aplicacion, un momento por favor...");
        setContentView(R.layout.loading_screen);
        findViewById(R.id.mainSpinner1).setVisibility(View.VISIBLE);
        
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
            	 
            }
        }, WAIT_TIME);
    }

}
