package com.bit.mediators;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.*;

/*
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.octopuschile.fulcro_abc.HomeActivity;
import com.octopuschile.fulcro_abc.MainActivity;
import com.octopuschile.fulcro_abc.classes.DailyTestsResponse;
import com.octopuschile.fulcro_abc.classes.Evaluations;
*/


/**
 * CLASE ENCARGADA DE REVISAR EL FICHERO DEFINIDO EN BUSCA DE IMAGENES,
 * SI EXISTEN LAS SUBE AL SERVIDOR
 * Created by nicolasmarin on 22-07-13.
 */
public class ImageMediator {

    String ip, usuario, password;
    Context C;

    /**
     *METODO INICIO DEL DEMONIO
     * @param aux_ip
     * @param aux_user
     * @param aux_pass
     */
    public void startDaemon(String aux_ip, String aux_user, String aux_pass, Context aux_contexto)
    {
        try {
            Log.i("ImageHelper","startDaemon - Started");
            ip = aux_ip;
            usuario = aux_user;
            password = aux_pass;
            C = aux_contexto;
            //Se define la direccion por defecto
            String dirImagenes = Environment.getExternalStorageDirectory().toString()
                    + "/com.octopuschile.atlas_copco_app";

            //Se busca en la direccion los archivos existentes
            File[] listaImagenes = searchForImages(dirImagenes);
            if(listaImagenes!=null)
                uploadFiles(listaImagenes);

        }catch(Exception ex) {
            Log.e("ImageHelper","startDaemon - Error message :" + ex.toString());
        }
    }

    /**
     * METODO QUE TOMA LA LISTA, RECUPERA CADA UNA DE LAS IMAGENES Y LAS ENVIA A UN METODO
     * QUE LAS SUBE AL SERVIDOR UNA A UNA.
     * @param dirs Objeto de tipo Lista que contiene todas las imagenes
     */
    public void uploadFiles(File[] dirs)
    {
        String nombre = null;
        String direccion = null;
        String id = null;
        String tipo = null;

        try
        {
            for(File ff : dirs)
            {
                if(ff.isDirectory())
                { }
                else
                {
                    nombre = ff.getName();
                    String aux = nombre;
                    direccion = ff.getAbsolutePath();

                    /*
                    if(aux.indexOf("candidato") != -1)
                    {
                        Log.i("ImageHelper","Se encontraron imagenes de candidatos");
                        tipo = "candidate";
                        id = aux.substring(aux.indexOf("_") + 1,aux.indexOf(".jpg"));
                        //SUBIR FOTO
                        sendCandidateImage(id, nombre, tipo);
                    }
                    else if(aux.indexOf("CandidateSignature") != -1)
                    {
                        Log.i("ImageHelper","Se encontraron firmas de candidatos");
                        tipo = "candidate";
                        id = aux.substring(aux.indexOf("_") + 1,aux.indexOf(".png"));
                        //SUBIR FIRMA
                        postSignatureToServer(id, nombre, tipo);
                    }
                    else if(aux.indexOf("EvaluatorSignature") != -1)
                    {

                        tipo = "evaluator";
                        id = aux.substring(aux.indexOf("_") + 1,aux.indexOf(".png"));
                        //SUBIR FIRMA
                        postSignatureToServer(id, nombre, tipo);
                    }
                    else
                    */
                    if(aux.indexOf("Order") != -1)
                    {

                        //id = aux.substring(aux.indexOf("_") + 1,aux.indexOf(".txt"));
                        //SUBIR EVALUACION
                        postEvaluationToServer(aux);
                    }
                }
                //SendToServer(nombre, direccion, id);
            }
        }
        catch(Exception ex)
        {
            ex.toString();
        }
    }





    private void postEvaluationToServer(String aux) {
        class OneShotTask implements Runnable {
            String aux, url, dirImagen;
            OneShotTask(String n) {aux = n; }
            @Override
			public void run() {

                try
                {
                    //Checkea si existe internet
                    if(checkConexionToInternet())
                    {
                        Log.i("ImageHelper", "postEvaluationToServer - " + "Se procede a subir orden al servidor.");

                        String root = Environment.getExternalStorageDirectory().toString();
                        dirImagen = root + "/com.octopuschile.atlas_copco_app";

                        //this.url = "http://" + ip + "/FulcroServer/mobile/testResults";
                        this.url = "http://workshop.2get.mobi/Workshop/mobile/modify";

                        //Si existe entonces comienza el proceso de busqueda en memoria
                        send(aux);
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            final String send(String fileName)
            {
                String result = null;

                File file = null;
                String results="NACK";

                try
                {
                    file = new File(dirImagen, fileName);
                }
                catch(Exception ex)
                {
                    return results;
                }

                //Read text from file
                StringBuilder text = new StringBuilder();

                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String line;

                    while ((line = br.readLine()) != null) {
                        text.append(line);
                    }
                }
                catch (IOException e) {

                    Log.e("ImageHelper", "FAIL - postEvaluationToServer - " + e.getMessage());
                }

                if(file.exists()){
                    String RESULTADOS = text.toString();

                    try
                    {

                        DefaultHttpClient httpclient = new DefaultHttpClient();

                        HttpPost httpRequest = new HttpPost(url);
                        BasicHttpContext localContext = new BasicHttpContext();
                        HttpHost targetHost = new HttpHost(ip, 80, "http");
                        BasicScheme basicAuth = new BasicScheme();
                        localContext.setAttribute("preemptive-auth", basicAuth);

                        httpRequest.setHeader("Accept", "application/json");
                        httpRequest.setHeader("Content-Type", "application/json");
                        httpRequest.setEntity(new StringEntity(RESULTADOS, HTTP.UTF_8));

                        //passes the results to a string builder/entity
                        //StringEntity se = new StringEntity(RESULTADOS);

                        //sets the post request as the resulting string
                        //httpRequest.setEntity(se);

                        // authentication
                        httpclient.getCredentialsProvider().setCredentials(AuthScope.ANY,new UsernamePasswordCredentials(usuario, password));

                        HttpResponse response = httpclient.execute(targetHost, httpRequest);


                        switch (response.getStatusLine().getStatusCode())
                        {
                            case 400:
                                Log.e("FAIL - Server side","Bad Request");
                                result = "Bad Request";
                                break;
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
                                    //En caso de no existir errores retorna un ACK
                                    result = "ACK";
                                    //Limpia los resultados de esta evaluacion de la memoria de la tablet
                                    file.delete();
                                    //Se registra en el log
                                    Log.i(fileName, result);

                                    //Se actualiza el estado de la evaluacion a subido a servidor (2)
                                    //Hashtable estado_evaluacion = MainActivity.estado_evaluacion;
                                    //estado_evaluacion.put(id,2);
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
                        Log.e("ImageHelper","FAIL - Upload evaluacion!");
                        Log.e("ImageHelper",e.getLocalizedMessage());
                    }
                }

                return result;
            }
        }
        Thread t = new Thread(new OneShotTask(aux));
        t.start();
    }



    /**
     * METODO QUE BUSCA EN UN DIRECTORIO (address) TODOS LOS ARCHIVOS O CARPETAS EXISTENTES
     * @param address direccion donde se buscara
     * @return una lista de tipo File
     */
    public File[] searchForImages(String address)
    {
        File f = new File(address);
        File[]dirs = f.listFiles();

        return dirs;
    }

    /**
     * METODO QUE CHEQUEA LA CONEXION A INTERNET
     * @return
     */
    Boolean checkConexionToInternet()
    {
        Boolean result = false;

        try
        {
            ConnectivityManager cm = (ConnectivityManager) C.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnectedOrConnecting())
            {
                result = true;
            }
        }
        catch (Exception e)
        {
            Log.e("CHECK CONNECTION TO INTERNET", e.toString());
        }

        return result;
    }

    /**
     * METODO QUE ENVIA FIRMAS AL SERVIDOR
     * @return
     */
    void postSignatureToServer(String id, String nombreImagen, String idTipo)
    {
        class OneShotTask implements Runnable {
            String id, nombreImagen, idTipo, url, dirImagen;
            OneShotTask(String s, String n, String z) { id = s; nombreImagen = n; idTipo = z; }
            @Override
			public void run() {

                Log.i("ImageHelper", "postSignatureToServer - " + "Se procede a subir las firmas al servidor.");

                String root = Environment.getExternalStorageDirectory().toString();
                dirImagen = root + "/com.octopuschile.fulcro_abc";

                this.url = "http://" + ip + "/Workshop/mobile/evaluation/" + idTipo + "/" + id + "/sign";

                try
                {
                    //Checkea si existe internet
                    if(checkConexionToInternet())
                    {
                        //Si existe entonces comienza el proceso de busqueda en memoria
                        String res = send(nombreImagen);
                        Log.i(nombreImagen, "postSignatureToServer - " + res);
                    }
                }
                catch (Exception e)
                {
                    Log.e("ImageHelper","FAIL - Upload firma!");
                    Log.e("ImageHelper", e.getLocalizedMessage());
                }
            }

            final String send(String nombreImagen)
            {
                File file = null;
                String results="NACK";

                try
                {
                    file = new File(dirImagen + "/" + nombreImagen);
                }
                catch(Exception ex)
                {
                    return results;
                }

                if(file.exists()){
                    String result = "NACK";

                    String root = Environment.getExternalStorageDirectory().toString();

                    DefaultHttpClient httpclient = new DefaultHttpClient();
                    httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

                    HttpPost httppost = new HttpPost(url);
                    HttpHost targetHost = new HttpHost(ip, 80, "http");

                    // authentication
                    UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(usuario, password);
                    httpclient.getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),credentials);


                    MultipartEntity mpEntity = new MultipartEntity();
                    ContentBody cbFile = new FileBody(file, "image/jpeg");
                    mpEntity.addPart("sign", cbFile);

                    httppost.setEntity(mpEntity);
                    Log.e("executing request " + httppost.getRequestLine(),"");

                    try
                    {
                        HttpResponse response = httpclient.execute(targetHost, httppost);
                        String aux = "200";
                        if(aux.equals(response.getStatusLine().getStatusCode()+"")){
                            result="ACK";
                        }
                        HttpEntity resEntity = response.getEntity();
                        Log.e(""+response.getStatusLine(),"");
                        if (resEntity != null) {
                            Log.e(EntityUtils.toString(resEntity),"");
                        }
                        if (resEntity != null) {
                            resEntity.consumeContent();
                        }
                        httpclient.getConnectionManager().shutdown();
                    }
                    catch(Exception ex)
                    {
                        ex.toString();
                    }

                    if(result.equals("ACK"))
                    {
                        //Se elimina imagen si sale bien con el upload
                        file.delete();
                    }
                }

                return results;
            }
        }
        Thread t = new Thread(new OneShotTask(id, nombreImagen, idTipo));
        t.start();
    }


    /**
     * METODO HEBRA PARA SUBIR IMAGEN DEL CANDIDATO AL SERVIDOR
     */
    void sendCandidateImage(String id, String nombreImagen, String idTipo)
    {
        class OneShotTask implements Runnable {
            String id, nombreImagen, idTipo, url, dirImagen;
            OneShotTask(String s, String n, String z) { id = s; nombreImagen = n; idTipo = z; }
            @Override
			public void run() {

                Log.i("ImageHelper", "SendCandidateImage - " + "Se procede a subir las imagenes de candidatos al servidor.");

                try
                {
                    //Checkea si existe internet
                    if(checkConexionToInternet())
                    {
                        Log.i("ImageHelper", "SendCandidateImage - " + "Hay internet, se procede a subir imagen.");
                        String root = Environment.getExternalStorageDirectory().toString();
                        dirImagen = root + "/com.octopuschile.fulcro_abc";

                        this.url = "http://" + ip + "/Workshop/mobile/candidate/" + id + "/photo";

                        //Si existe entonces comienza el proceso de busqueda en memoria
                        String res = send(nombreImagen);
                        Log.i(nombreImagen, res);
                    }
                    else {
                        Log.i("ImageHelper", "SendCandidateImage - " + "No hay internet, se pospone mision.");
                    }
                }
                catch (Exception e)
                {
                    Log.e("ImageHelper","FAIL - Upload imagen candidato!");
                    Log.e("ImageHelper",e.getLocalizedMessage());
                }
            }

            final String send(String nombreImagen)
            {
                File file = null;
                String results="NACK";
                DefaultHttpClient httpclient = new DefaultHttpClient();
                httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

                HttpPost httppost = new HttpPost(url);
                HttpHost targetHost = new HttpHost(ip, 80, "http");

                try
                {
                    file = new File(dirImagen + "/" + nombreImagen);
                }
                catch(Exception ex)
                {
                    Log.e("ImageHelper","FAIL " + ex.getMessage());
                    return results;
                }

                if(file.exists()){
                    // authentication
                    UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(usuario, password);
                    httpclient.getCredentialsProvider().setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),credentials);


                    MultipartEntity mpEntity = new MultipartEntity();
                    ContentBody cbFile = new FileBody(file, "image/jpeg");
                    mpEntity.addPart("photo", cbFile);

                    httppost.setEntity(mpEntity);
                    //Log.e("executing request " + httppost.getRequestLine(), "");
                    try{
                        HttpResponse response = httpclient.execute(targetHost, httppost);
                        switch (response.getStatusLine().getStatusCode())
                        {
                            case 401:
                                results = "Usuario no Existe";
                                break;
                            case 404:
                                results = "No se encuentro el servidor";
                                break;
                            case 200:
                                Log.i("ImageHelper", "SendCandidateImage - " + "Respuesta 200 de servidor");
                                BufferedReader inStream = null;
                                inStream = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
                                String aux = inStream.readLine();
                                String substr = aux.substring(aux.indexOf("error")).replace("error\":", "").replace("}", "");
                                if(substr.equals("null"))
                                {
                                    Log.i("ImageHelper", "SendCandidateImage - " + "Respuesta 200 de servidor");
                                    //En caso de no existir errores retorna un ACK
                                    results = "ACK";
                                    //y se borra la imagen local de la memoria
                                    try{
                                        file.delete();
                                    }catch(Exception ex)
                                    {
                                        Log.e("ImageHelper", "FAIL - " + ex.getMessage());
                                    }
                                }
                                else
                                {
                                    //En caso de existir errores retorna el error recibido desde el servidor...(normalmente error en el mismo servidor)
                                    results = substr;
                                    Log.e("ImageHelper", "SendCandidateImage - " + "Respuesta de servidor: " + results);
                                }
                                break;
                            default:
                                results = "Error desconocido, contacte a servicio t??cnico.";
                                break;
                        }
                        HttpEntity resEntity = response.getEntity();
                        Log.e(""+response.getStatusLine(),"");
                        if (resEntity != null) {
                            Log.e(EntityUtils.toString(resEntity),"");
                        }
                        if (resEntity != null) {
                            resEntity.consumeContent();
                        }
                        httpclient.getConnectionManager().shutdown();
                    }
                    catch(Exception ex)
                    {
                        Log.e("ImageHelper", "SendCandidateImage - " + "Error Message: " + ex.getMessage());
                    }
                }

                return results;
            }
        }
        Thread t = new Thread(new OneShotTask(id, nombreImagen, idTipo));
        t.start();
    }


    /**
     * METODO HEBRA PARA SUBIR EXAMENES AL SERVIDOR
     */
    /*
    void sendTestResultsToServer()
    {
        class OneShotTask implements Runnable {
            String url, dirImagen;
            OneShotTask() {  }
            public void run() {

                Log.i("ImageHelper", "SendTestResultsToServer - " + "Se procede a subir las evaluaciones al servidor.");

                try
                {
                    //Checkea si existe internet
                    if(checkConexionToInternet())
                    {
                        String root = Environment.getExternalStorageDirectory().toString();
                        dirImagen = root + "/com.octopuschile.fulcro_abc";

                        this.url = "http://" + ip + "/FulcroServer/mobile/testResults";

                        //Busqueda en memoria de evaluaciones
                        String results = send();
                        Log.i("ImageHelper", "SendTestResultsToServer - " + results);
                    }
                }
                catch (Exception e)
                {
                    Log.e("ImageHelper","FAIL - Upload imagen candidato!");
                    Log.e("ImageHelper",e.getLocalizedMessage());
                }
            }

            final String send()
            {
                String result = null;

                //Activa demonio de subida de Examenes
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(C);

                Map<String,?> keys = prefs.getAll();

                for(Map.Entry<String,?> entry : keys.entrySet()){
                    if(entry.getKey().indexOf("Evaluacion") != -1)
                    {
                        try
                        {
                            String RESULTADOS = entry.getValue().toString();
                            String id = RESULTADOS.substring(RESULTADOS.indexOf("_") + 1);

                            DefaultHttpClient httpclient = new DefaultHttpClient();

                            HttpPost httpRequest = new HttpPost(url);
                            BasicHttpContext localContext = new BasicHttpContext();
                            HttpHost targetHost = new HttpHost(ip, 8080, "http");
                            BasicScheme basicAuth = new BasicScheme();
                            localContext.setAttribute("preemptive-auth", basicAuth);

                            httpRequest.setHeader("Accept", "application/json");
                            httpRequest.setHeader("Content-Type", "application/json");

                            //passes the results to a string builder/entity
                            StringEntity se = new StringEntity(RESULTADOS);

                            //sets the post request as the resulting string
                            httpRequest.setEntity(se);

                            // authentication
                            httpclient.getCredentialsProvider().setCredentials(AuthScope.ANY,new UsernamePasswordCredentials(usuario, password));

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
                                        //En caso de no existir errores retorna un ACK
                                        result = "ACK";
                                        String idEva = entry.getKey().substring(entry.getKey().indexOf("_") + 1);

                                        //Se registra en el log
                                        Log.v("Evaluacion_" + id, result);

                                        Gson gson = new GsonBuilder().serializeNulls().create();
                                        //convert the json string back to object
                                        DailyTestsResponse obj = gson.fromJson(datos, DailyTestsResponse.class);
                                        //Setea el status actual de la evaluacin (1-sin procesar,2.-hecha,3.-en proceso,4.-error)
                                        for(Evaluations eva:obj.getEvaluaciones())
                                        {
                                            if(idEva.equals(eva.getIdEvaluacion()))
                                            {
                                                eva.setEstado(2);
                                            }
                                        }
                                        //Convierte el objeto a un string de tipo json
                                        datos = gson.toJson(obj);
                                        prefs.edit().putString("my_pref", datos).commit();
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
                    }
                }

                return result;
            }
        }
        Thread t = new Thread(new OneShotTask());
        t.start();
    }
    */
}
