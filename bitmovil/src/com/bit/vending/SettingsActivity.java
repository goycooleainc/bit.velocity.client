package com.bit.vending;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.bit.async.tasks.NewAvatarTask;
import com.bit.audit.fragments.MainActivity;
import com.bit.audit.fragments.VendingMachineActivity;
import com.bit.client.R;
import com.bit.entities.Avatar;
import com.bit.entities.User;
import com.bit.singletons.TransactionHashmapCollectionSingleton;
import com.bit.utils.LoyaltyCardReader;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class SettingsActivity extends Activity implements LoyaltyCardReader.AccountCallback {
    public static int READER_FLAGS = 0;
    public static final String TAG = "CardReaderFragment";
    public String avatar;
    private HashMap<String, String> hmEstados;
    private HashMap<String, String> hmLideres;
    private HashMap<String, String> hmNaves;
    private HashMap<String, String> hmSupervisores;
    private String idUser;
    public String ip;
    public String json;
    private TextView mAccountField;
    public LoyaltyCardReader mLoyaltyCardReader;
    private String nfc_tag;
    public String nombre;
    public String password;
    public String privateInternalID;
    private String role;
    private Spinner spinner_estados;
    private boolean stop;
    private long timeWhenStopped;
    private TextView txTime;
    TextView tx_avatar;
    TextView tx_nuevo_avatar;
    public String usuario;
    Button btn_close;

    public void SettingsActivity(){}

    /* renamed from: com.bit.vending.SettingsActivity.1 */
    class C01171 implements OnClickListener {
        final /* synthetic */ Dialog val$dialog;

        C01171(Dialog dialog) {
            this.val$dialog = dialog;
        }

        public void onClick(View v) {
            try {
                this.val$dialog.dismiss();
                Intent mainIntent = new Intent(SettingsActivity.this, StartActivity.class);
                mainIntent.putExtra("usuario", "");
                mainIntent.putExtra("password", "");
                mainIntent.putExtra("ip", "");
                mainIntent.putExtra("avatar", "");
                mainIntent.putExtra("nombre", "");
                SettingsActivity.this.startActivity(mainIntent);
                SettingsActivity.this.finish();
            } catch (Exception ex) {
                ex.toString();
            }
        }
    }

    /* renamed from: com.bit.vending.SettingsActivity.2 */
    class C01182 implements OnClickListener {
        final /* synthetic */ Dialog val$dialog;

        C01182(Dialog dialog) {
            this.val$dialog = dialog;
        }

        public void onClick(View v) {
            this.val$dialog.dismiss();
        }
    }

    public SettingsActivity() {
        this.timeWhenStopped = 0;
        this.stop = true;
    }

    static {
        READER_FLAGS = 129;
    }

    public void onBackPressed() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        btn_close = (Button) findViewById(R.id.btnCloseSettings);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        ActionBar localActionBar = getActionBar();
        localActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        localActionBar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);
        localActionBar.hide();
        Intent intent = getIntent();
        this.role = intent.getStringExtra("role");
        this.usuario = intent.getStringExtra("usuario");
        this.nombre = intent.getStringExtra("nombre");
        this.json = intent.getStringExtra("json");
        this.avatar = intent.getStringExtra("avatar");
        this.idUser = intent.getStringExtra("id_user");
        this.privateInternalID = intent.getStringExtra("privateInternalID");
        TransactionHashmapCollectionSingleton.getInstance();
        User user = TransactionHashmapCollectionSingleton.user;
        TransactionHashmapCollectionSingleton.getInstance();
        Avatar avatar = TransactionHashmapCollectionSingleton.avatar;
        this.tx_avatar = (TextView) findViewById(R.id.txAvatar);
        this.tx_nuevo_avatar = (TextView) findViewById(R.id.txNuevoAvatar);
        if (avatar != null) {
            this.tx_avatar.setText(avatar.getCodigo());
        } else {
            this.tx_avatar.setText("[Inexistente]");
        }
        this.tx_nuevo_avatar.setText("");
    }

    public static String getFormatNumber(int w) {
        if (w < 10) {
            return "0" + String.valueOf(w);
        }
        return String.valueOf(w);
    }

    public void onPause() {
        super.onPause();
        disableReaderMode();
    }

    public void onResume() {
        super.onResume();
        enableReaderMode();
    }

    private void enableReaderMode() {
        Log.i(TAG, "Enabling reader mode");
        NfcAdapter nfc = NfcAdapter.getDefaultAdapter(this);
        if (nfc != null) {
            nfc.enableReaderMode(this, this.mLoyaltyCardReader, READER_FLAGS, null);
        }
    }

    private void disableReaderMode() {
        Log.i(TAG, "Disabling reader mode");
        NfcAdapter nfc = NfcAdapter.getDefaultAdapter(this);
        if (nfc != null) {
            nfc.disableReaderMode(this);
        }
    }

    protected void onStart() {
        super.onStart();
        this.mLoyaltyCardReader = new LoyaltyCardReader(this);
    }

    public void goSaveMobile(View v) {
        try {
            Toast.makeText(getBaseContext(), "Imposible Activar Modo Mimo, Su Telefono no puede Emular HEC NFC TAG. Lo sentimos", Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    public void goHome(View view) {
        Intent k = new Intent(this, VendingMachineActivity.class);
        finish();
        k.putExtra("usuario", "1-1");
        k.putExtra("nombre", this.nombre);
        k.putExtra("password", "password");
        k.putExtra("ip", this.ip);
        k.putExtra("id_user", this.idUser);
        k.putExtra("privateInternalID", this.privateInternalID);
        k.putExtra("avatar", this.avatar);
        startActivity(k);
    }

    public void goSettings(View view) {
        Intent k = new Intent(this, SettingsActivity.class);
        finish();
        k.putExtra("usuario", "1-1");
        k.putExtra("password", "password");
        k.putExtra("nombre", this.nombre);
        k.putExtra("ip", this.ip);
        k.putExtra("id_user", this.idUser);
        k.putExtra("privateInternalID", this.privateInternalID);
        k.putExtra("avatar", this.avatar);
        startActivity(k);
    }

    public void goSave(View view) {
        try {
            TransactionHashmapCollectionSingleton.getInstance();
            User user = TransactionHashmapCollectionSingleton.user;
            TransactionHashmapCollectionSingleton.getInstance();
            Avatar avatar = TransactionHashmapCollectionSingleton.avatar;
            String code = this.tx_nuevo_avatar.getText().toString();
            Avatar new_avatar = new Avatar();
            new_avatar.setCodigo(code);
            new_avatar.setDescripcion("AGREGADO POR MOBILE");
            new_avatar.setEstado(1);
            new_avatar.setIdUser(Integer.parseInt(user.getIdUsuario()));
            if (avatar == null) {
                return;
            }
            if (code.equals(avatar.getCodigo())) {
                Toast.makeText(getBaseContext(), "Avatar Duplicado o Erroneo............. [ERROR]", Toast.LENGTH_LONG).show();
                //this.tx_nuevo_avatar.setText("[Listening]");
                return;
            }else {
                NewAvatarTask task = new NewAvatarTask(getApplicationContext());
                task.setDATA(new Gson().toJson(new_avatar));
                task.execute(new String[0]);
                Toast.makeText(getBaseContext(), "Avatar Guardado ............. [OK]", Toast.LENGTH_LONG).show();
                this.tx_avatar.setText(code);
            }
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    public void onExit(View v) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_new_action);
        dialog.setTitle("BITMOVIL - PAGOS MOVILES");
        ((TextView) dialog.findViewById(R.id.dialogText)).setText("Esta seguro de Salir y cerrar Sesion?");
        ((Button) dialog.findViewById(R.id.dialogButtonOK)).setOnClickListener(new C01171(dialog));
        ((Button) dialog.findViewById(R.id.dialogButtonCancel)).setOnClickListener(new C01182(dialog));
        dialog.show();
    }

    public static Object getKeyFromValue(Map hm, Object value) {
        for (Object o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }

    public void onAccountReceived(final String account) {
        // This callback is run on a background thread, but updates to UI elements must be performed
        // on the UI thread.
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //mAccountField.setText(account);

                //ChecklistAuditActivity.txTag.setText(a.toString());
                Toast.makeText(getBaseContext(), "Avatar Identificado " + account + "       [OK]", Toast.LENGTH_SHORT).show();
                tx_nuevo_avatar.setText(account);
            }
        });
    }
}
