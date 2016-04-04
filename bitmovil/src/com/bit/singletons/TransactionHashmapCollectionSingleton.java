package com.bit.singletons;

import android.app.Activity;
import com.bit.entities.*;

import java.util.List;

public class TransactionHashmapCollectionSingleton {
	public static Avatar avatar;
	public static List<Avatar> avatares;
	public static EstadoCuenta estadoCuenta;
	public static List<Eventos> eventos;
	public static List<Cortesia> cortesiaCombos;
	public static List<Cortesia> cortesiaEvento;
	private static TransactionHashmapCollectionSingleton instance;
	public static List<Productos> productos;
	public static List<Productos> producto;
	public static User user;
	public static List<Transaccion> transacciones;
	public static List<Venta> ventas;
	public static Activity mainActivity;
	   /// constructor
	   protected TransactionHashmapCollectionSingleton() {
	      // Exists only to defeat instantiation.
	   }
	   /// gets the instance of the class
	   public static TransactionHashmapCollectionSingleton getInstance() {
	      if(instance == null) {
	         instance = new TransactionHashmapCollectionSingleton();
	      }
	      /// just returns the instance
	      return instance;
	   }
}
