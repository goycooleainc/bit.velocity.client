package com.bit.async.tasks;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.*;

/**
 * Created by Hector Goycoolea on 15-12-14.
 */
public class SendAsyncSingleImageWithNote extends AsyncTask<String, Void, String> {

    String ip = "78.41.206.33";
    String usuario = "1-1";
    String password = "password";
    String dirImagen="";
    private String id;
    private String DATA;
    private String comments;
    Bitmap bm;
    private int type;
	private Activity activity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDATA() {
        return DATA;
    }

    public void setDATA(String DATA) {
        this.DATA = DATA;
    }

 // CAST THE LINEARLAYOUT HOLDING THE MAIN PROGRESS (SPINNER)
  	//LinearLayout linlaHeaderProgress;

    public SendAsyncSingleImageWithNote(Activity activity)
    {
    	this.activity = activity;
    	
    }

    @Override
    protected String doInBackground(String... params) {
       
            try {
                directSend(this.DATA);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        return "Executed";
    }

    @Override
    protected void onPreExecute() {    

    	//linlaHeaderProgress.setVisibility(View.VISIBLE);
    }
    
    @Override
    protected void onPostExecute(String result) {     
        // HIDE THE SPINNER AFTER LOADING FEEDS

    }

    @Override
    protected void onProgressUpdate(Void... values) {}

    public String directSend(String DATA) throws UnsupportedEncodingException {

        File file = null;
        String results="NACK";
        String root = Environment.getExternalStorageDirectory().toString();
        dirImagen = root + "/com.goycooleainc.atlas.audit";
        try
        {
            file = new File(dirImagen + "/" + DATA);
        }
        catch(Exception ex)
        {
            return results;
        }

        if(file.exists()){
            // TODO Auto-generated method stub
            InputStream inputStream = null;
            String result = "";
            try {
                bm = BitmapFactory.decodeFile(dirImagen + "/" + DATA);
                String mImage = dirImagen + "/" + DATA;
                String guid = java.util.UUID.randomUUID().toString();
                ///
                executeMultipartPost(mImage,guid,comments);

            } catch (Exception e) {
                Log.d("InputStream", e.getLocalizedMessage());
            }
        }

        return results;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    @SuppressWarnings("deprecation")
    public void executeMultipartPost(String filename, String guid, String note) throws Exception {
        try {
            //
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
            byte[] data = bos.toByteArray();
            //
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost("http://atlas.goycooleainc.enterprises/uploadWithNote");
            ByteArrayBody bab = new ByteArrayBody(data, filename);
            //
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.addPart("file", bab);
            builder.addPart("idOrden", new StringBody(String.valueOf(this.id)));
            HttpEntity entit1 = builder.build();
            postRequest.setEntity(entit1);
            
            builder.addPart("type", new StringBody(String.valueOf(this.type)));
            HttpEntity entity2 = builder.build();
            postRequest.setEntity(entity2);
            
            if(this.type == 1){
                builder.addPart("note", new StringBody(""));
            }else
            if(this.type == 2){
                builder.addPart("note", new StringBody(""));
            }else{
            	note = note!=null? note : "";
            	builder.addPart("note", new StringBody(note));
            }
            HttpEntity entity3 = builder.build();
            postRequest.setEntity(entity3);

            HttpResponse response = httpClient.execute(postRequest);
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
            String sResponse;
            StringBuilder s = new StringBuilder();

            while ((sResponse = reader.readLine()) != null) {
                s = s.append(sResponse);
            }
            System.out.println("Response: " + s);
            
            //File file = new File(filename);
            //boolean deleted = file.delete();
            
        } catch (Exception e) {
            // handle exception here
            Log.e(e.getClass().getName(), e.getMessage());
        }
    }

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
