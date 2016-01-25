package com.bit.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PicturesQaSharedPreference {

	public static final String PREFS_NAME = "com.atlas.workshop";
	public static final String PREFS_KEY = "pictures_qa";
	
	public PicturesQaSharedPreference() {
		super();
	}

	public void save(Context context, String text) {
		SharedPreferences settings;
		Editor editor;
		
		//settings = PreferenceManager.getDefaultSharedPreferences(context);
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