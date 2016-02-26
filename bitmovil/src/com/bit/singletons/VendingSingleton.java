package com.bit.singletons;

import com.bit.entities.Productos;

public class VendingSingleton {

	public static Productos producto;

	private static VendingSingleton instance;
	   /// constructor
	   protected VendingSingleton() {
	      // Exists only to defeat instantiation.
	   }
	   /// gets the instance of the class
	   public static VendingSingleton getInstance() {
	      if(instance == null) {
	         instance = new VendingSingleton();
	      }
	      /// just returns the instance
	      return instance;
	   }
}
