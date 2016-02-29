package com.bit.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bit.client.R;

import java.io.File;

public class ImageTextAdapter extends ArrayAdapter<String>{
 
	String[] color_names;
	String[] image_id;
	Context context;
 
	 public ImageTextAdapter(Activity context,String[] image_id, String[] text){
		 super(context, R.layout.list_row, text);

		 this.color_names = text;
		 this.image_id = image_id;
		 this.context = context;
	 }
 
 	@Override
 	public View getView(int position, View convertView, ViewGroup parent) {

		 
		String root = Environment.getExternalStorageDirectory().toString();
		String dirImagen = root + "/com.octopuschile.atlas_copco_app";
		File imgFile = new  File(dirImagen + "/" + image_id[position]);
		String dir_file=image_id[position].replace(".jpg", "");
		String [] idOrden =  dir_file.split("_");
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View single_row = inflater.inflate(R.layout.list_row, null,true);
		
		TextView textView = (TextView) single_row.findViewById(R.id.textView);
		ImageView imageView = (ImageView) single_row.findViewById(R.id.imageView);
		textView.setTextColor(Color.WHITE);
		textView.setText("Folio Interno " + idOrden[2] + "\r\n Comentarios : " + color_names[position]);
		
		Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
		imageView.setImageBitmap(Bitmap.createScaledBitmap(myBitmap, 120, 120, false));
		
		
	    
		return single_row; 
	 }
}
