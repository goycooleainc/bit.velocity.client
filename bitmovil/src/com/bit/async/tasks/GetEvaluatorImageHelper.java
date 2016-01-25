package com.bit.async.tasks;

/**
 * * @author Nicolas Marin
 * * @LastUpdate 18 de Marzo 2013
 *  @Company Octopus
 *  Clase para manejo de conexion al servidor usando puertos especificos.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GetEvaluatorImageHelper extends AsyncTask<String, Void, Bitmap>{

	String nombre, id, internalId;
	
	@Override
	protected Bitmap doInBackground(String... params) {

		Log.e("getImageFromTablet", "Start GetEvaluatorImageHelper");
		this.nombre = params[0];
        this.id = params[1];
        this.internalId = params[2];
		
		try 
		{
			return loadFromCacheFile();
        } 
		catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public Bitmap loadFromFile(String filename) {
	      try {
	          File f = new File(filename);
	          if (!f.exists()) { return null; }
              Bitmap tmp = decodeFile(f);
	          //Bitmap tmp = BitmapFactory.decodeFile(filename);
	          return tmp;
	      } catch (Exception e) {
	    	  Log.i("load form file", e.toString());
	          return null;
	      }
	  }
	
	public Bitmap loadFromCacheFile() {
	      return loadFromFile(getCacheFilename());
	  }
	
	public String getCacheFilename() {
	      File f = getSavePath();
	      return f.getAbsolutePath() + "/" + nombre + "_" + internalId + "_" + id + ".jpg";
	  }
	
	public File getSavePath() {
	      File path;
	      if (hasSDCard()) { // SD card
	          path = new File(getSDCardPath() + "/com.goycooleainc/");
	          path.mkdir();
	      } else { 
	          path = Environment.getDataDirectory();
	          path.mkdir();
	      }
	      return path;
	  }
	
	public boolean hasSDCard() { // SD????????
	      String status = Environment.getExternalStorageState();
	      return status.equals(Environment.MEDIA_MOUNTED);
	  }
	  public String getSDCardPath() {
	      File path = Environment.getExternalStorageDirectory();
	      return path.getAbsolutePath();
	  }

    //decodes image and scales it to reduce memory consumption
    Bitmap decodeFile(File f){
        try {

            //Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f),null,o);

            //The new size we want to scale to
            final int REQUIRED_SIZE=70;

            //Find the correct scale value. It should be the power of 2.
            int scale=1;
            while(o.outWidth/scale/2>=REQUIRED_SIZE && o.outHeight/scale/2>=REQUIRED_SIZE)
                scale*=2;

            //Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize=scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {}
        return null;
    }
	
}
