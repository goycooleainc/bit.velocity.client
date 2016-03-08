//package com.bit.async.tasks;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.view.View;
//import android.widget.LinearLayout;
//import android.widget.ProgressBar;
//
//import com.bit.client.R;
//import com.bit.entities.User;
//
//import org.apache.http.HttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.auth.BasicScheme;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.protocol.BasicHttpContext;
//import org.apache.http.protocol.HTTP;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//
///**
// * Created by gfurlano.
// */
//
//public class DirectAltaUser extends AsyncTask<String, Void, String> {
//
//    private Context context;
//    private Activity activity;
//    public User user;
//    ProgressBar linlaHeaderProgress;
//    LinearLayout lenearLogin;
//    AlertDialog.Builder bld;
//    Intent intent;
//    View view;
//
//    public DirectAltaUser(Activity activity, AlertDialog.Builder bld, Intent intent, View view) {
//        this.context = activity.getApplicationContext();
//        this.bld = bld;
//        this.activity = activity;
//        linlaHeaderProgress = (ProgressBar) activity.findViewById(R.id.pbHeaderProgress);
//        lenearLogin = (LinearLayout) activity.findViewById(R.id.login_view);
//        this.intent = intent;
//        this.view = view;
//    }
//
//    public String getDATA() {
//        return DATA;
//    }
//
//    public void setDATA(String DATA, User user) {
//        this.DATA = DATA;
//        this.user = user;
//    }
//
//    private String DATA;
//
//    @Override
//    protected String doInBackground(String... params) {
//        return directSend(this.DATA);
//    }
//
//    @Override
//    protected void onPostExecute(String result) {
//        bld.setMessage(result);
//        bld.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int whichButton) {
//                linlaHeaderProgress.setVisibility(View.GONE);
//                lenearLogin.setVisibility(View.VISIBLE);
//                activity.finish();
//                context.startActivity(intent);
//            }
//        });
//        bld.create().show();
//    }
//
//    @Override
//    protected void onPreExecute() {
//        linlaHeaderProgress.setVisibility(View.VISIBLE);
//        lenearLogin.setVisibility(View.GONE);
//    }
//
//    @Override
//    protected void onProgressUpdate(Void... values) {}
//
//    public String directSend(String DATA){
//
//        String result = "";
//        String server = context.getString(R.string.server);
//        String url = server + "/mobile/new/user";
//        try {
//            DefaultHttpClient httpclient = new DefaultHttpClient();
//
//            HttpPost httpRequest = new HttpPost(url);
//            BasicHttpContext localContext = new BasicHttpContext();
//
//            BasicScheme basicAuth = new BasicScheme();
//            localContext.setAttribute("preemptive-auth", basicAuth);
//
//            httpRequest.setHeader("Accept", "application/json");
//            httpRequest.setHeader("Content-Type", "application/json");
//            httpRequest.setEntity(new StringEntity(DATA, HTTP.UTF_8));
//            HttpResponse response = httpclient.execute(httpRequest);
//
//            switch (response.getStatusLine().getStatusCode()) {
//                case 401:
//                    result = "401";
//                    break;
//                case 404:
//                    result = "404";
//                    break;
//                case 200:
//                    BufferedReader inStream = null;
//                    inStream = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
//                    result = inStream.readLine();
//
//                    break;
//                default:
//                    result = "ERR";
//                    break;
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return result;
//    }
//
//}
