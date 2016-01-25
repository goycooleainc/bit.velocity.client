package com.bit.singletons;

import java.util.*;

public class UsersHashmapCollection {
		/// static instance to access
	   private static UsersHashmapCollection instance = null;
	   
	   public static LinkedHashMap<String, String> hmUsuarios, hmLineas, hmCargo,hmArea,hmCodigoEmpleado, hmCc; 
	   /// constructor
	   protected UsersHashmapCollection() {
	      // Exists only to defeat instantiation.
	   }
	   /// gets the instance of the class
	   public static UsersHashmapCollection getInstance() {
	      if(instance == null) {
	         instance = new UsersHashmapCollection();
	         initializeHash();
	      }
	      /// just returns the instance
	      return instance;
	   }
	   
	   private static void initializeHash(){
		   /// Secciones de problema
		   hmUsuarios= new LinkedHashMap<String, String>();
		   hmUsuarios.put("13.020.905-K", "Juan Carlos Menay");
		   hmUsuarios.put("1.854.066-4", "German Rodriguez");
		   hmUsuarios.put("10.367.270-8", "Alex Vicencio");
		   hmUsuarios.put("14111675-4", "Hector Goycolea");
		   /// soporte tecnico
		   hmUsuarios.put("24138940-5", "Javier Serrano");
		   hmUsuarios.put("14151065-7", "Boris Albornoz");
		   hmUsuarios.put("13540682-1", "Patricio Castillo");
		   hmUsuarios.put("7101973-k", "Ricardo Flores");
		   hmUsuarios.put("9062333-8", "Miguel Fuentes");
		   hmUsuarios.put("21440388-3", "Agustin Mederos");
		   hmUsuarios.put("13546543-7", "Luis Vera");
		   
		   hmLineas= new LinkedHashMap<String, String>();
		   hmLineas.put("16474499-k", "TME");
		   hmLineas.put("13420686-1", "SDE");
		   hmLineas.put("14308188-5", "ADS");
		   hmLineas.put("13827005-k", "ADS");
		   hmLineas.put("14357597-7", "LHD");
		   hmLineas.put("9524090-9", "ADS");
		   hmLineas.put("15146263-4", "ALL");
		   hmLineas.put("14513670-9", "SDE");
		   
		   hmCargo= new LinkedHashMap<String, String>();
		   hmCargo.put("16474499-k", "Asesor Postventa JR");
		   hmCargo.put("13420686-1", "Asesor Postventa SR");
		   hmCargo.put("14308188-5", "Asesor Postventa SR");
		   hmCargo.put("13827005-k", "Asesor Postventa SR");
		   hmCargo.put("14357597-7", "Asesor Postventa SR");
		   hmCargo.put("9524090-9", "Asesor Postventa SR");
		   hmCargo.put("15146263-4", "Jefe de Asesoria Post Venta MRS");
		   hmCargo.put("14513670-9", "Asesor Postventa JR");
		   
		   hmArea= new LinkedHashMap<String, String>();
		   hmArea.put("16474499-k", "MRS");
		   hmArea.put("13420686-1", "MRS");
		   hmArea.put("14308188-5", "MRS");
		   hmArea.put("13827005-k", "MRS");
		   hmArea.put("14357597-7", "MRS");
		   hmArea.put("9524090-9", "MRS");
		   hmArea.put("15146263-4", "MRS");
		   hmArea.put("14513670-9", "MRS");
		   
		   hmCodigoEmpleado = new LinkedHashMap<String, String>();
		   hmCodigoEmpleado.put("16474499-k", "1367-6");
		   hmCodigoEmpleado.put("13420686-1", "699-8");
		   hmCodigoEmpleado.put("14308188-5", "1598-9");
		   hmCodigoEmpleado.put("13827005-k", "1645-4");
		   hmCodigoEmpleado.put("14357597-7", "1104-5");
		   hmCodigoEmpleado.put("9524090-9", "4591-8");
		   hmCodigoEmpleado.put("15146263-4", "939-3");
		   hmCodigoEmpleado.put("14513670-9", "4101-7");
		   
		   hmCc = new LinkedHashMap<String, String>();
		   hmCc.put("16474499-k", "7029");
		   hmCc.put("13420686-1", "7029");
		   hmCc.put("14308188-5", "7029");
		   hmCc.put("13827005-k", "7029");
		   hmCc.put("14357597-7", "7029");
		   hmCc.put("9524090-9", "7029");
		   hmCc.put("15146263-4", "7029");
		   hmCc.put("14513670-9", "7029");
	   }
	   /**
	    * 
	    * @param pos
	    * @return
	    */
	   public static String getPositionUsuario(String pos){
		  
           /// return the position of the hashmap
           return  hmUsuarios.get(pos);
	   }
	   /**
	    * 
	    * @param pos
	    * @return
	    */
	   public static String getPositionLineas(String pos){
           /// return the position of the hashmap
           return  hmLineas.get(pos);
	   }
	   /**
	    * 
	    * @param pos
	    * @return
	    */
	   public static String getPositionCargo(String pos){
		   
		 
           /// return the position of the hashmap
           return  hmCargo.get(pos);
	   }
	   /**
	    * 
	    * @param pos
	    * @return
	    */
	   public static String getPositionArea(String pos){
		   
			 
           /// return the position of the hashmap
           return  hmArea.get(pos);
	   }
	   /**
	    * 
	    * @param pos
	    * @return
	    */
	   public static String getPositionCodigoEmpleado(String pos){
			  
           /// return the position of the hashmap
           return  hmCodigoEmpleado.get(pos);
	   }
	   /**
	    * 
	    * @param pos
	    * @return
	    */
	   public static String getPositionCc(String pos){
			  
           /// return the position of the hashmap
           return  hmCc.get(pos);
	   }
	   /**
	    * 
	    * @return
	    */
	   public static List<String> getUsuarios()
	   {
		   try {
			   List<String> list = new ArrayList<String>();
			   SortedSet<String> keys = new TreeSet<String>(hmUsuarios.keySet());
			   for (String key : keys) { 
			      String value = hmUsuarios.get(key);
			      // do something
			      list.add(value);
			   }
			   return list;
	        } catch (Exception ex){
	        	/// nulo si hay error
	            return null;
	        }
	   }
	   /**
	    * 
	    * @return
	    */
	   public static List<String> getLineas()
	   {
		   try {
			   List<String> list = new ArrayList<String>();
			   SortedSet<String> keys = new TreeSet<String>(hmLineas.keySet());
			   for (String key : keys) { 
			      String value = hmLineas.get(key);
			      // do something
			      list.add(value);
			   }
			   return list;
	        } catch (Exception ex){
	        	/// nulo si hay error
	            return null;
	        }
	   }
	   /**
	    * 
	    * @return
	    */
	   public static List<String> getCargos()
	   {
		   try {
			   List<String> list = new ArrayList<String>();
			   SortedSet<String> keys = new TreeSet<String>(hmCargo.keySet());
			   for (String key : keys) { 
			      String value = hmCargo.get(key);
			      // do something
			      list.add(value);
			   }
			   return list;
	        } catch (Exception ex){
	        	/// nulo si hay error
	            return null;
	        }
	   }
	   /**
	    * 
	    * @return
	    */
	   public static List<String> getAreas()
	   {
		   try {
			   List<String> list = new ArrayList<String>();
			   SortedSet<String> keys = new TreeSet<String>(hmArea.keySet());
			   for (String key : keys) { 
			      String value = hmArea.get(key);
			      // do something
			      list.add(value);
			   }
			   return list;
	        } catch (Exception ex){
	        	/// nulo si hay error
	            return null;
	        }
	   }
	   /**
	    * 
	    * @return
	    */
	   public static List<String> getCodigoEmpleado()
	   {
		   try {
			   List<String> list = new ArrayList<String>();
			   SortedSet<String> keys = new TreeSet<String>(hmCodigoEmpleado.keySet());
			   for (String key : keys) { 
			      String value = hmCodigoEmpleado.get(key);
			      // do something
			      list.add(value);
			   }
			   return list;
	        } catch (Exception ex){
	        	/// nulo si hay error
	            return null;
	        }
	   }
	   /**
	    * 
	    * @return
	    */
	   public static List<String> getCc()
	   {
		   try {
			   List<String> list = new ArrayList<String>();
			   SortedSet<String> keys = new TreeSet<String>(hmCc.keySet());
			   for (String key : keys) { 
			      String value = hmCc.get(key);
			      // do something
			      list.add(value);
			   }

			   return list;
	        } catch (Exception ex){
	        	/// nulo si hay error
	            return null;
	        }
	   }
	   /**
	    * 
	    * @param pos
	    * @return
	    */
	   public static List<String> loadSectionItems(int pos)
	   {
		   switch(pos){
		   case 0:
			   return getUsuarios();
		   case 1:
			   return getLineas();
		   case 2:
			   return getCargos();
		   case 3:
			   return getAreas();
		   case 4:
			   return getCodigoEmpleado();
		   case 5:
			   return getCc();
		   default:
			   return getUsuarios();
		   }
	   }
	   
}
