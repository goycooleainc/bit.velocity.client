package com.bit.preferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import com.bit.async.tasks.DailyGetServicios;

import java.util.concurrent.ExecutionException;

public class DailyOrderSharedPreference {

	public static final String PREFS_NAME = "com.atlas.workshop";
	public static final String PREFS_KEY = "collection_orders";
	
	public DailyOrderSharedPreference(Context context, Activity activity) {
		super();
		
		AsyncTask<String, Void, String> task = new DailyGetServicios(activity).execute("1-1", "password", "77.95.228.75");
		
		try {
			
			String dailyTests = task.get();
			
			removeValue(context);
			save(context, dailyTests);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void save(Context context, String text) {
		SharedPreferences settings;
		Editor editor;
		
		settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
		editor = settings.edit(); //2

		editor.putString(PREFS_KEY, text); //3

		editor.commit(); //4
	}

	public String getValue(Context context) {
		SharedPreferences settings;
		String text;

		//settings = PreferenceManager.getDefaultSharedPreferences(context);
		settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		text = settings.getString(PREFS_KEY, null);
		return text;
	}
	
	public void clearSharedPreference(Context context) {
		SharedPreferences settings;
		Editor editor;

		//settings = PreferenceManager.getDefaultSharedPreferences(context);
		settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		editor = settings.edit();

		editor.clear();
		editor.commit();
	}

	public void removeValue(Context context) {
		SharedPreferences settings;
		Editor editor;

		settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		editor = settings.edit();

		editor.remove(PREFS_KEY);
		editor.commit();
	}	
}