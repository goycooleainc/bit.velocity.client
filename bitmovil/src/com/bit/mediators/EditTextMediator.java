package com.bit.mediators;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class EditTextMediator {
		/**
		 * 
		 */
		public EditTextMediator()
		{
		}
		/**
		 * 
		 * @param view
		 * @param idImageView
		 * @param idEditTextView
		 * @param max
		 * @param reset
		 */
		public static void setEditTextViewToAdd(Activity view, int idImageView, int idEditTextView,int max, String reset){
			try{
			/// imageview for the button
	        ImageView btn = (ImageView) view.findViewById(idImageView);
	        /// edit text for the number 
	        final EditText generic = (EditText) view.findViewById(idEditTextView);
	        final int max_ = max;
	        final String reset_ = reset;
	        /// listener for the button
	        btn.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	/// we capture the value
	                int number = Integer.parseInt(generic.getText().toString());
	                /// we add one more
	                number++;
	                /// we detect if minor to 10 digit format
	                if(number<10){
	                	generic.setText("0"+ String.valueOf(number));
	                }else{
	                	generic.setText(String.valueOf(number));
	                }
	                /// we now reset values if needed
	                if(number > max_){
	                	generic.setText(reset_);
	                }
	            }
	        });
			}catch(Exception ex){
				ex.toString();
			}
		}
		/**
		 * 
		 * @param view
		 * @param idImageView
		 * @param idEditTextView
		 * @param max
		 * @param reset
		 */
		public static void setEditTextViewToSubstract(Activity view, int idImageView, int idEditTextView,int max, String reset){
			try{
			/// imageview for the button
	        ImageView btn = (ImageView) view.findViewById(idImageView);
	        /// edit text for the number 
	        final EditText generic = (EditText) view.findViewById(idEditTextView);
	        final int max_ = max;
	        final String reset_ = reset;
	        /// listener for the button
	        btn.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	/// we capture the value
	                int number = Integer.parseInt(generic.getText().toString());
	                /// we add one more
	                number--;
	                /// we detect if minor to 10 digit format
	                if(number<10){
	                	generic.setText("0"+ String.valueOf(number));
	                }else{
	                	generic.setText(String.valueOf(number));
	                }
	                /// we now reset values if needed
	                if(number < max_){
	                	generic.setText(reset_);
	                }
	            }
	        });
			}catch(Exception ex){
				ex.toString();
			}
		}
}
