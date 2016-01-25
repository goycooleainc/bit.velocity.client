package com.bit.singletons;

import com.bit.entities.*;

import java.util.*;

public class VentaHashmapCollectionSingleton {
	public static Avatar avatar;
	public static List<Avatar> avatares;
	public static EstadoCuenta estadoCuenta;
	public static List<Eventos> eventos;
	private static VentaHashmapCollectionSingleton instance;
	public static List<Productos> productos;
	public static User user;
	public static List<Venta> ventas;
	   /// constructor
	   protected VentaHashmapCollectionSingleton() {
	      // Exists only to defeat instantiation.
	   }
	   /// gets the instance of the class
	   public static VentaHashmapCollectionSingleton getInstance() {
	      if(instance == null) {
	         instance = new VentaHashmapCollectionSingleton();
	      }
	      /// just returns the instance
	      return instance;
	   }
}
