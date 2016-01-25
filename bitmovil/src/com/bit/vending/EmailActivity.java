package com.bit.vending;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.bit.client.R;


public class EmailActivity extends Activity{

    String id, usuario, password, ip, empresa, conductor, os, emailCliente, emailEmpresa;


    @Override
    public void onBackPressed() {
        //NOTHING
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        Intent intent = getIntent();
        // Receiving the Data
        id = intent.getStringExtra("id");
        usuario = intent.getStringExtra("usuario");
        password = intent.getStringExtra("password");
        ip = intent.getStringExtra("ip");
        empresa = intent.getStringExtra("empresa");
        conductor = intent.getStringExtra("conductor");
        os = intent.getStringExtra("os");
        emailCliente = intent.getStringExtra("emailCliente");
        emailEmpresa = intent.getStringExtra("emailEmpresa");
        String id_orden = id;
        sendEmail(id_orden);
    }

    /**
     * METODO PARA ENVIAR CORREO DE CONFIRMACION
     */
    void sendEmail(String id_orden)
    {
        try{

            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("message/rfc822");
            i.putExtra(Intent.EXTRA_EMAIL, new String[]{emailCliente, emailEmpresa});
            i.putExtra(Intent.EXTRA_SUBJECT, "Orden registrada en Cummins");
            i.putExtra(Intent.EXTRA_TEXT   , "Estimados " + empresa + ", la orden n??"+ os + " fue registrada con " +
                    "exito haga clic en el siguiente link donde encontrara informaci??n mas detallada. Gracias por " +
                    "operar con Cummins S.A. <a href=\"http://89.207.132.9:8080/KomatsuDemoServer/getOrderPdf/" + id_orden +
                    "\">Ver Info</a>");
            try {
                startActivity(Intent.createChooser(i, "Proceso de envio de correo..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(EmailActivity.this, "No existen correos instalados en su equipo.",
                        Toast.LENGTH_SHORT).show();
            }

        }
        catch(Exception e)
        {
            e.toString();
        }

        TextView texto = (TextView) findViewById(R.id.textView_detalle);
        texto.setVisibility(View.VISIBLE);

        Button btn = (Button) findViewById(R.id.button_finito);
        btn.setVisibility(View.VISIBLE);

    }
}
