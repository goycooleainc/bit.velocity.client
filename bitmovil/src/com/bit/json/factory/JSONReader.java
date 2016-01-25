package com.bit.json.factory;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public class JSONReader{
	
	public static String loadJSONClientesFromAsset(Context context){
		
		String json = null;
		
		try{
			/// InputStream
			InputStream is = context.getAssets().open("clientes.json");
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			
			json = new String(buffer, "UTF-8");
					
		}catch(IOException ex){
			ex.printStackTrace();
			return null;
		}
		
		return json;
	}
	
	public static String loadJSONClientesSoporteFromAsset(Context context){
		
		String json = null;
		
		try{
			/// InputStream
			InputStream is = context.getAssets().open("support.clients.json");
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			
			json = new String(buffer, "UTF-8");
					
		}catch(IOException ex){
			ex.printStackTrace();
			return null;
		}
		
		return json;
	}
	
	public static String loadJSONEquiposFromAsset(Context context){
		
		String json = null;
		
		try{
			/// InputStream
			InputStream is = context.getAssets().open("support.equipos.json");
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			
			json = new String(buffer, "UTF-8");
					
		}catch(IOException ex){
			ex.printStackTrace();
			return null;
		}
		
		return json;
	}
}
