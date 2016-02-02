package com.bit.singletons;

import com.bit.entities.Avatar;
import com.bit.entities.EstadoCuenta;
import com.bit.entities.Eventos;
import com.bit.entities.Productos;
import com.bit.entities.Transaccion;
import com.bit.entities.User;
import com.bit.entities.Venta;

import java.util.List;

public class TransactionHashmapCollectionSingleton {
	public static Avatar avatar;
	public static List<Avatar> avatares;
	public static EstadoCuenta estadoCuenta;
	public static List<Eventos> eventos;
	private static TransactionHashmapCollectionSingleton instance;
	public static List<Productos> productos;
	public static User user;
	public static List<Transaccion> transacciones;
	public static List<Venta> ventas;
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
