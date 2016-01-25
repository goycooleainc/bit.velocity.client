package com.bit.async.tasks;

import android.os.AsyncTask;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by goycolea on 15-12-14.
 */
public class UpdateAuditoryPotentialsTask extends AsyncTask<String, Void, String> {

    String ip = "78.41.206.33";
    String usuario = "1-1";
    String password = "password";
    private String DATA;

    public String getDATA() {
        return DATA;
    }

    public void setDATA(String DATA) {
        this.DATA = DATA;
    }


    @Override
    protected String doInBackground(String... params) {

            directSend(this.DATA);

        return "Executed";
    }

    @Override
    protected void onPostExecute(String result) {

    }

    @Override
    protected void onPreExecute() {}

    @Override
    protected void onProgressUpdate(Void... values) {}

    public String directSend(String DATA){

        String result = "NACK";
        String url = "http://atlas.goycooleainc.enterprises/mobile/audit/item/potencial/update"
        		+ "";
        try
        {
            //HttpUriRequest request = new HttpGet("http://atlas.goycooleainc.enterprises/Workshop/mobile/newOrder");
            // Or HttpPost(), depends on your needs
            DefaultHttpClient httpclient = new DefaultHttpClient();

            HttpPost httpRequest = new HttpPost(url);
            BasicHttpContext localContext = new BasicHttpContext();
            HttpHost targetHost = new HttpHost(ip, 80, "http");
            BasicScheme basicAuth = new BasicScheme();
            localContext.setAttribute("preemptive-auth", basicAuth);

            httpRequest.setHeader("Accept", "application/json");
            httpRequest.setHeader("Content-Type", "application/json");
            httpRequest.setEntity(new StringEntity(DATA, HTTP.UTF_8));

            //passes the results to a string builder/entity
            //StringEntity se = new StringEntity(RESULTADOS);

            //sets the post request as the resulting string
            //httpRequest.setEntity(se);

            // authentication
            httpclient.getCredentialsProvider().setCredentials(AuthScope.ANY,new UsernamePasswordCredentials("1-1", "password"));

            HttpResponse response = httpclient.execute(targetHost, httpRequest);
            switch (response.getStatusLine().getStatusCode())
            {
                case 401:
                    result = "Usuario no Existe";
                    break;
                case 404:
                    result = "No se encuentro el servidor";
                    break;
                case 200:
                    BufferedReader inStream = null;
                    inStream = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
                    String aux = inStream.readLine();
                    String substr = aux.substring(aux.indexOf("error")).replace("error\":", "").replace("}", "");
                    if(substr.equals("null"))
                    {
                        //En caso de no existir errores retorna el ID de la orden
                        result = aux.substring(aux.indexOf("idOrden")).replace("error\":", "").replace("}", "")
                                .replace("idOrden\":","").replace("null","").replace("\"","").replace(",","");
                    }
                    else
                    {
                        //En caso de existir errores retorna el error recibido desde el servidor...(normalmente error en el mismo servidor)
                        result = substr;
                    }
                    break;
                default:
                    result = "Error desconocido, contacte a servicio t??cnico.";
                    break;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return result;
    }
    
}
