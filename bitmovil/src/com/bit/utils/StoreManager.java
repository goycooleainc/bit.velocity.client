package com.bit.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.android.vending.billing.IInAppBillingService;
import com.android.vending.billing.util.*;

/**
 * Created by goycolea on 22/1/16.
 */
public class StoreManager extends Activity  implements IabBroadcastReceiver.IabBroadcastListener {

    IInAppBillingService mService;
    // SKUs for our products: the premium upgrade (non-consumable) and gas (consumable)
    static final String SKU_MIL = "sku_mil";
    static final String SKU_DOSMIL = "sku_dosmil";
    static final String SKU_CINCOMIL = "sku_cincomil";
    static final String SKU_DIEZMIL = "sku_diezmil";
    // (arbitrary) request code for the purchase flow
    static final int RC_REQUEST = 10001;
    // Debug tag, for logg
    static final String TAG = "BITMOVIL";
    // The helper object
    IabHelper mHelper;
    // Provides purchase notification while this app is running
    IabBroadcastReceiver mBroadcastReceiver;
    Activity mActivity;

    public StoreManager(Activity fromActivity){

        mActivity = fromActivity ;
        /* base64EncodedPublicKey should be YOUR APPLICATION'S PUBLIC KEY
         * (that you got from the Google Play developer console). This is not your
         * developer public key, it's the *app-specific* public key.
         *
         * Instead of just storing the entire literal string here embedded in the
         * program,  construct the key at runtime from pieces or
         * use bit manipulation (for example, XOR with some other string) to hide
         * the actual key.  The key itself is not secret information, but we don't
         * want to make it easy for an attacker to replace the public key with one
         * of their own and then fake messages from the server.
         */
        String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAr3Qfb7DH1jAljc70g8CL3NqXBrH9XSz96R32viE+q8Xb/vsHjEEInpO9ADZ/ywMqIKUsLyODXHfHiQIUItXDu19wqINMOYAVvBOA48OtRAS4XcwwJl/tUwG9K4oD24k3YmRklx70eyOZDySTXONs5OPGWZHBJA6cMMdxIBzdH+Bm+coBWhltAaP+oYxi+hchzye7FQXfb2WsoUATR1Nn3bR2GAqa58M/ZshNoysoNq2HzXG8j4x6K7hvRtNSi7z5k98WwD3lPHw+C/hHUTgY0eAyvYnyCMzFxf46kaRLWlM2N2dDSyaKwIqnqMQWpSvQwpEjfdD6diXnT6VOO3p/zQIDAQAB";

        // Some sanity checks to see if the developer (that's you!) really followed the
        // instructions to run this sample (don't put these checks on your app!)
        /*if (base64EncodedPublicKey.contains("CONSTRUCT_YOUR")) {
            throw new RuntimeException("Please put your app's public key in MainActivity.java. See README.");
        }
        if (mActivity.getPackageName().startsWith("com.bit")) {
            throw new RuntimeException("Please change the sample's package name! See README.");
        }*/

        // Create the helper, passing it our context and the public key to verify signatures with
        Log.d(TAG, "Creating IAB helper.");
        mHelper = new IabHelper(mActivity, base64EncodedPublicKey);

        // enable debug logging (for a production application, you should set this to false).
        mHelper.enableDebugLogging(false);

        // Start setup. This is asynchronous and the specified listener
        // will be called once setup completes.
        Log.d(TAG, "Starting setup.");
        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {
                Log.d(TAG, "Setup finished.");

                if (!result.isSuccess()) {
                    // Oh noes, there was a problem.
                    complain("Problem setting up in-app billing: " + result);
                    return;
                }

                // Have we been disposed of in the meantime? If so, quit.
                if (mHelper == null) return;

                // Important: Dynamically register for broadcast messages about updated purchases.
                // We register the receiver here instead of as a <receiver> in the Manifest
                // because we always call getPurchases() at startup, so therefore we can ignore
                // any broadcasts sent while the app isn't running.
                // Note: registering this listener in an Activity is a bad idea, but is done here
                // because this is a SAMPLE. Regardless, the receiver must be registered after
                // IabHelper is setup, but before first call to getPurchases().
                mBroadcastReceiver = new IabBroadcastReceiver(StoreManager.this);
                IntentFilter broadcastFilter = new IntentFilter(IabBroadcastReceiver.ACTION);
                mActivity.registerReceiver(mBroadcastReceiver, broadcastFilter);
                // IAB is fully set up. Now, let's get an inventory of stuff we own.
                Log.d(TAG, "Setup successful. Querying inventory.");
                mHelper.queryInventoryAsync(mGotInventoryListener);
            }
        });
    }
    @Override
    public void receivedBroadcast() {
        // Received a broadcast notification that the inventory of items has changed
        Log.d(TAG, "Received broadcast notification. Querying inventory.");
        mHelper.queryInventoryAsync(mGotInventoryListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult(" + requestCode + "," + resultCode + "," + data);
        if (mHelper == null) return;

        // Pass on the activity result to the helper for handling
        if (!mHelper.handleActivityResult(requestCode, resultCode, data)) {
            // not handled, so handle it ourselves (here's where you'd
            // perform any handling of activity results not related to in-app
            // billing...
            super.onActivityResult(requestCode, resultCode, data);
        }
        else {
            Log.d(TAG, "onActivityResult handled by IABUtil.");
        }
    }

    /** Verifies the developer payload of a purchase. */
    boolean verifyDeveloperPayload(Purchase p) {
        String payload = p.getDeveloperPayload();

        /*
         * TODO: verify that the developer payload of the purchase is correct. It will be
         * the same one that you sent when initiating the purchase.
         *
         * WARNING: Locally generating a random string when starting a purchase and
         * verifying it here might seem like a good approach, but this will fail in the
         * case where the user purchases an item on one device and then uses your app on
         * a different device, because on the other device you will not have access to the
         * random string you originally generated.
         *
         * So a good developer payload has these characteristics:
         *
         * 1. If two different users purchase an item, the payload is different between them,
         *    so that one user's purchase can't be replayed to another user.
         *
         * 2. The payload must be such that you can verify it even when the app wasn't the
         *    one who initiated the purchase flow (so that items purchased by the user on
         *    one device work on other devices owned by the user).
         *
         * Using your own server to store and verify developer payloads across app
         * installations is recommended.
         */

        return true;
    }

    public void purchase(String sku){
        try {
            // launch the gas purchase UI flow.
            // We will be notified of completion via mPurchaseFinishedListener
            setWaitScreen(true);

            Log.d(TAG, "Launching purchase flow for coupons.");
        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this.
         *        */
            String payload = "";
            switch(sku) {
                case SKU_MIL:
                    /**
                     *
                     */
                    mHelper.launchPurchaseFlow(mActivity, SKU_MIL, RC_REQUEST, mPurchaseFinishedListener, payload);
                    break;
                case SKU_DOSMIL:
                    /**
                     *
                     */
                    mHelper.launchPurchaseFlow(mActivity, SKU_DOSMIL, RC_REQUEST, mPurchaseFinishedListener, payload);
                    break;
                case SKU_CINCOMIL:
                    /**
                     *
                     */
                    mHelper.launchPurchaseFlow(mActivity, SKU_CINCOMIL, RC_REQUEST, mPurchaseFinishedListener, payload);
                    break;
                case SKU_DIEZMIL:
                    /**
                     *
                     */
                    mHelper.launchPurchaseFlow(mActivity, SKU_DIEZMIL, RC_REQUEST, mPurchaseFinishedListener, payload);
                    break;
                default:
                    /**
                     *
                     */
                    mHelper.launchPurchaseFlow(mActivity, SKU_MIL, RC_REQUEST, mPurchaseFinishedListener, payload);
                    break;
            }

        }catch(Exception exc){
            Log.d(TAG, exc.getLocalizedMessage());
        }
    }
    // Callback for when a purchase is finished
    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
        public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
            Log.d(TAG, "Purchase finished: " + result + ", purchase: " + purchase);

            // if we were disposed of in the meantime, quit.
            if (mHelper == null) return;

            if (result.isFailure()) {
                complain("Error purchasing: " + result);
                setWaitScreen(false);
                return;
            }
            if (!verifyDeveloperPayload(purchase)) {
                complain("Error purchasing. Authenticity verification failed.");
                setWaitScreen(false);
                return;
            }

            Log.d(TAG, "Purchase successful.");

            if (purchase.getSku().equals(SKU_MIL)) {
                // bought 1/4 tank of gas. So consume it.
                Log.d(TAG, "Purchase is gas. Starting gas consumption.");
                mHelper.consumeAsync(purchase, mConsumeFinishedListener);
            }
            else if (purchase.getSku().equals(SKU_DOSMIL)) {
                // bought the premium upgrade!
                Log.d(TAG, "Purchase is premium upgrade. Congratulating user.");
                alert("Thank you for upgrading to premium!");
                //mIsPremium = true;
                //updateUi();
                setWaitScreen(false);
            }
            else if (purchase.getSku().equals(SKU_CINCOMIL)) {
                // bought the infinite gas subscription
                Log.d(TAG, "Infinite gas subscription purchased.");
                alert("Thank you for subscribing to infinite gas!");
                setWaitScreen(false);
            }
            else if (purchase.getSku().equals(SKU_DIEZMIL)) {
                // bought the infinite gas subscription
                Log.d(TAG, "Infinite gas subscription purchased.");
                alert("Thank you for subscribing to infinite gas!");
                setWaitScreen(false);
            }
        }
    };

    // Listener that's called when we finish querying the items and subscriptions we own
    IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
        public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
            Log.d(TAG, "Query inventory finished.");

            // Have we been disposed of in the meantime? If so, quit.
            if (mHelper == null) return;

            // Is it a failure?
            if (result.isFailure()) {
                complain("Failed to query inventory: " + result);
                return;
            }

            Log.d(TAG, "Query inventory was successful.");

            setWaitScreen(false);
            Log.d(TAG, "Initial inventory query finished; enabling main UI.");
        }
    };

    // Called when consumption is complete
    IabHelper.OnConsumeFinishedListener mConsumeFinishedListener = new IabHelper.OnConsumeFinishedListener() {
        public void onConsumeFinished(Purchase purchase, IabResult result) {
            Log.d(TAG, "Consumption finished. Purchase: " + purchase + ", result: " + result);

            // if we were disposed of in the meantime, quit.
            if (mHelper == null) return;

            // We know this is the "gas" sku because it's the only one we consume,
            // so we don't check which sku was consumed. If you have more than one
            // sku, you probably should check...
            if (result.isSuccess()) {
                // successfully consumed, so we apply the effects of the item in our
                // game world's logic, which in our case means filling the gas tank a bit
                Log.d(TAG, "Consumption successful. Provisioning.");
                //mTank = mTank == TANK_MAX ? TANK_MAX : mTank + 1;
                //saveData();
                alert("Has Cargado con exito credito a tu cuenta bit");
            }
            else {
                complain("Error al cargar: " + result);
            }
            //updateUi();
            setWaitScreen(false);
            Log.d(TAG, "fin del flujo de consumo.");
        }
    };
    // Enables or disables the "please wait" screen.
    void setWaitScreen(boolean set) {
        //mActivity.findViewById(R.id.pager).setVisibility(set ? View.GONE : View.VISIBLE);
        //mActivity.findViewById(R.id.screen_wait).setVisibility(set ? View.VISIBLE : View.GONE);
    }

    void complain(String message) {
        Log.e(TAG, "**** TrivialDrive Error: " + message);
        alert("Error: " + message);
    }

    void alert(String message) {
        AlertDialog.Builder bld = new AlertDialog.Builder(this);
        bld.setMessage(message);
        bld.setNeutralButton("OK", null);
        Log.d(TAG, "Showing alert dialog: " + message);
        bld.create().show();
    }

}
