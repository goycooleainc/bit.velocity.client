package com.bit.singletons;

import java.util.*;

public class ProductorasHashmapCollectionSingleton {
		/// static instance to access
	   private static ProductorasHashmapCollectionSingleton instance = null;
	   /// hashmaps
	   public static LinkedHashMap<String, String> hmEstados,hmSecciones, hmSeccionesRpm, hmFisuras,hmLubricantes, hmRodados, hmPower,hmElectrico, hmVarios,hmDiesel,hmTrabajo;
	private static LinkedHashMap<String, String> hmCabinas;
	private static LinkedHashMap<String, String> hmZonaProteccion;
	private static LinkedHashMap<String, String> hmBrazoViga;
	private static LinkedHashMap<String, String> hmSistemaIncendio;

	/// constructor
	   protected ProductorasHashmapCollectionSingleton() {
	      // Exists only to defeat instantiation.
	   }
	   /// gets the instance of the class
	   public static ProductorasHashmapCollectionSingleton getInstance() {
	      if(instance == null) {
	         instance = new ProductorasHashmapCollectionSingleton();
	         initializeHash();
	      }
	      /// just returns the instance
	      return instance;
	   }
	   
	   private static void initializeHash(){
		   hmEstados = new LinkedHashMap<String, String>();
		   hmEstados.put("1", "CONTROL DE CHASIS - REGISTRO PÁRAMETROS MOTOR DIESEL");
		   hmEstados.put("2", "CONTROL DE CHASIS - REGISTRO  PRESIONES DE TRABAJO");
		   /// Secciones
		   hmSecciones = new LinkedHashMap<String, String>();
		   hmSecciones.put("1", "CONTROL ESTRUCTURAL - FISURAS");
		   hmSecciones.put("2", "CONTROL DE NIVELES - LUBRICANTES");
		   hmSecciones.put("3", "CONTROL DE CHASIS - RODADOS");
		   hmSecciones.put("4", "CONTROL DE CHASIS - CABINA");
		   hmSecciones.put("5", "CONTROL DE CHASIS - ZONA PROTECCION POWER PACK");
		   hmSecciones.put("6", "CONTROL DE CHASIS - BRAZO Y VIGA");
		   hmSecciones.put("7", "CONTROL DE CHASIS - POWER PACK");
		   hmSecciones.put("8", "CONTROL DE CHASIS - SISTEMA CONTRA INCENDIO");
		   hmSecciones.put("9", "CONTROL DE CHASIS - SISTEMA ELECTRICO");
		   hmSecciones.put("10", "CONTROL DE CHASIS - VARIOS");
		   //// con caja de texto
		   hmSeccionesRpm = new LinkedHashMap<String, String>();
		   hmSeccionesRpm.put("1", "CONTROL DE CHASIS - REGISTRO PÁRAMETROS MOTOR DIESEL");
		   hmSeccionesRpm.put("2", "CONTROL DE CHASIS - REGISTRO  PRESIONES DE TRABAJO");
		   /// estructuras
		   hmFisuras = new LinkedHashMap<String, String>();
		   hmFisuras.put("1", "CHASIS");
		   hmFisuras.put("2", "CABINA");
		   hmFisuras.put("3", "BRAZO");
		   hmFisuras.put("4", "VIGA");
		   hmFisuras.put("5", "POWER PACK");
		   hmFisuras.put("6", "SISTEMA RHS");
		   /// diesel
		   hmLubricantes = new LinkedHashMap<String, String>();
		   hmLubricantes.put("1", "ACEITE HIDRÁULICO");
		   hmLubricantes.put("2", "ACEITE DE COMPRESOR");
		   hmLubricantes.put("3", "ACEITE DE LUBRICACIÓN");
		   hmLubricantes.put("4", "ACEITE MANDOS FINALES");
		   hmLubricantes.put("5", "SISTEMA DE ENGRASE");
		   hmLubricantes.put("6", "SISTEMA DE ENGRASE HILOS");
		   hmLubricantes.put("7", "LUBRICACIÓN UNIDAD ROTACION");
		   hmLubricantes.put("8", "REFRIGERANTE DE MOTOR DIÉSEL");
		   hmLubricantes.put("9", "AGUA PARA BARRIDO");
		   hmLubricantes.put("10", "AGUA LIMPIAPARABRISAS");
		   hmLubricantes.put("11", "COMBUSTIBLE");
		   /// transmicion
		   hmRodados = new LinkedHashMap<String, String>();
		   hmRodados.put("1", "RODILLOS Y GUARDA CARRIL");
		   hmRodados.put("2", "MANDOS FINALES");
		   hmRodados.put("3", "RUEDA MOTRIZ");
		   hmRodados.put("4", "RUEDA TENSORA,AMORTIGUADOR");
		   hmRodados.put("5", "DESGASTE DE ZAPATAS");
		   hmRodados.put("6", "DESGASTE DE CADENAS");
		   hmRodados.put("7", "PUNTOS DE LUBRICACIÓN");
		   hmRodados.put("8", "TENSADO DE CADENAS");
		   hmRodados.put("9", "BUJES DE BRONCE");
		   hmRodados.put("10", "PASADORES");
		   hmRodados.put("11", "RODILLO GUIA DE CADENA");

		   hmCabinas = new LinkedHashMap<String, String>();
		   hmCabinas.put("1", "ESTADO DE SOPORTE DE GOMA");
		   hmCabinas.put("2", "ESTADO DE PARABRISA");
		   hmCabinas.put("3", "SELLADO CABINA");
		   hmCabinas.put("4", "ESTADO ESCALA ACCESO");
		   hmCabinas.put("5", "PROTECCION PARABRISAS");
		   hmCabinas.put("6", "ACCESORIOS");
		   hmCabinas.put("7", "LIMPIEZA INTERIOR CABINA");

		   hmZonaProteccion = new LinkedHashMap<String, String>();
		   hmZonaProteccion.put("1", "TAPAS SUPERIORES Y TRASERAS");
		   hmZonaProteccion.put("2", "PUERTAS LATERALES");
		   hmZonaProteccion.put("3", "PILARES");
		   hmZonaProteccion.put("4", "BARANDA DE PROTECCION");
		   hmZonaProteccion.put("5", "ESTADO PERNOS DE FIJACION");
		   hmZonaProteccion.put("6", "ESCALA DE ACCESO");
		   hmZonaProteccion.put("7", "ESTANQUE COMBUSTIBLE");
		   hmZonaProteccion.put("8", "ESTANQUE DE AGUA DE BARRIDO");
		   
		   hmBrazoViga = new LinkedHashMap<String,String>();
		   hmBrazoViga.put("1", "ESTADO SISTEMA HIDRAULICO");
		   hmBrazoViga.put("2", "FUGAS DE ACEITE HIDRAULICO");
		   hmBrazoViga.put("3", "ESTADO SISTEMA ELECTRICO");
		   hmBrazoViga.put("4", "DESLIZADERAS");
		   hmBrazoViga.put("5", "SISTEMA RHS");
		   hmBrazoViga.put("6", "SISTEMA DE AVANCE");
		   hmBrazoViga.put("7", "SISTEMA DE ROTACION");
		   hmBrazoViga.put("8", "ESTADO Y TENSADO DE CABLES DE ACERO");
		   hmBrazoViga.put("9", "ESTADO SISTEMA NEUMATICO Y DCT");
		   /// ejes
		   hmPower = new LinkedHashMap<String, String>();
		   hmPower.put("1", "MOTOR DIÉSEL");
		   hmPower.put("2", "SISTEMA NEUMÁTICO(COMPRESOR)");
		   hmPower.put("3", "PROTECCION DE TURBO Y CORREAS");
		   hmPower.put("4", "BOMBA HIDRAULICA PRINCIPAL N°1");
		   hmPower.put("5", "BOMBA HIDRAÚLICA TRIPLE N°2-3-4");
		   hmPower.put("6", "BOMBA HIDRAÚLICA DOBLE N°5 Y 6");
		   hmPower.put("7", "SISTEMA DE ENFRIAMIENTO MOTOR");
		   hmPower.put("8", "SISTEMA DE ENFRIAMIENTO COMP");
		   hmPower.put("9", "ESTADO SISTEMA ELÉCTRICO");
		   hmPower.put("10", "ESTADO DE ACOPLES,CARDAN");

		   hmSistemaIncendio = new LinkedHashMap<String, String>();
		   hmSistemaIncendio.put("1", "ESTADO SISTEMA ANSUL");
		   hmSistemaIncendio.put("2", "ESTADP EXTINTORES MANUALES");
		 ///
		   hmElectrico = new LinkedHashMap<String, String>();
		   hmElectrico.put("1", "FUNCIONAMIENTO DISPLAY (RCS)");
		   hmElectrico.put("2", "CONTROLES DE CABINA");
		   hmElectrico.put("3", "GABINETE ELÉCTRICO A1");
		   hmElectrico.put("4", "ESTADO DE MÓDULOS I/O CABLES");
		   hmElectrico.put("5", "MOTOR DE ARRANQUE");
		   hmElectrico.put("6", "ALTERNADOR");
		   hmElectrico.put("7", "ESTADO PARADAS DE EMERGENCIA");
		   hmElectrico.put("8", "LUCES DE TRABAJO, CABINA, POWER PACK");
		   hmElectrico.put("9", "SENSORES EN GENERAL");
		   /// ejes
		   hmVarios = new LinkedHashMap<String, String>();
		   hmVarios.put("1", "ESTADO DE TORQUE DE PERNOS");
		   hmVarios.put("2", "ESTADO PINTURA");
		   hmVarios.put("3", "ESTADO GATO DE APOYO");
		   hmVarios.put("4", "ACOPLE MOTOR-COMPRESOR");
		   hmVarios.put("5", "ESTADO UNIDAD DE ROTACIÓN");
		   hmVarios.put("6", "ESTADO ADAPTADOR FLOTANTE");
		   hmVarios.put("7", "ESTADO DE ACEROS");
		   hmVarios.put("8", "ESTADO CILINDROS BASCULACION");
		   hmVarios.put("9", "CENTRADO DE BARRAS");
		   ///
		   hmDiesel = new LinkedHashMap<String, String>();
		   hmDiesel.put("1", "TEMPERATURA REFRIGERANTE");
		   hmDiesel.put("2", "TEMPERATURA HIDRÁULICO");
		   hmDiesel.put("3", "TEMPERATURA COMPRESOR HP");
		   hmDiesel.put("4", "TEMPERATURA COMPRESOR LP");
		   hmDiesel.put("5", "PORCENTAJE DE CARGA MOTOR");
		   hmDiesel.put("6", "PRESIÓN DE ACEITE MOTOR");
		   hmDiesel.put("7", "PRESIÓN AIRE ADMISIÓN");
		   hmDiesel.put("8", "TEMPERATURA AIRE ADMISIÓN");
		   hmDiesel.put("9", "VOLTAJE CARGA DE LA BATERIA");
		   hmDiesel.put("10", "PRESION DE COMBUSTIBLE");
		   
		   /// frenos
		   hmTrabajo = new LinkedHashMap<String, String>();
		   hmTrabajo.put("1", "PRESIÓN BOMBA 1");
		   hmTrabajo.put("2", "PRESIÓN BOMBA 2");
		   hmTrabajo.put("3", "PRESIÓN BOMBA 3");
		   hmTrabajo.put("4", "PRESIÓN BOMBA 4");
		   hmTrabajo.put("5", "PRESIÓN BOMBA 5");
		   hmTrabajo.put("6", "PRESIÓN BOMBA 6");
		   hmTrabajo.put("7", "PRESIÓN DE PILOTAJE (35 BAR)");
		   hmTrabajo.put("8", "PRESIÓN ROTACIÓN");
		   hmTrabajo.put("9", "PRESIÓN AVANCE");
		   hmTrabajo.put("10", "PRESIÓN PERCUSIÓN MÍNIMA (11 BAR)");
		   hmTrabajo.put("11", "PRESIÓN PERCUSIÓN MÁXIMA (18-20 BAR)");
		   hmTrabajo.put("12", "PRESIÓN INTERMEDIA COMPRESOR");
		   hmTrabajo.put("13", "PRESIÓN CARGA EN VACIO COMPRESOR");
		   hmTrabajo.put("14", "PRESIÓN LUB. MARTILLO");
		   hmTrabajo.put("15", "PRESIÓN AMORTIGUACIÓN (30 BAR)");
		   hmTrabajo.put("16", "SISTEMA ANTI-ATASCO   (80 BAR)");
		   hmTrabajo.put("17", "PRESIÓN DE LA VIGA    (200 BAR)");
		   hmTrabajo.put("18", "RPM BITS");
		   hmTrabajo.put("19", "SISTEMA RPCF");
		   
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
				   return getFisuras();
			   case 1:
				   return getLubricantes();
			   case 2:
				   return getRodados();
			   case 3:
				   return getCabina();
			   case 4:
				   return getZona();
			   case 5:
				   return getBrazoViga();
			   case 6:
				   return getPower();
			   case 7:
				   return getSistemaIncendio();
				case 8:
					return getElectrico();
				case 9:
					return getVarios();
				case 10:
					return getDiesel();
				case 11:
					return getTrabajo();
			   default:
				   return getFisuras();
		   }
	   }
	   
	   public static List<String> getEstados()
	   {
		   try {
			   List<String> list = new ArrayList<String>();
			   SortedSet<String> keys = new TreeSet<String>(hmEstados.keySet());
			   for (String key : keys) { 
			      String value = hmEstados.get(key);
			      // do something
			      list.add(value);
			   }

			   return list;
	        } catch (Exception ex){
	        	/// nulo si hay error
	            return null;
	        }
	   }
	   
	   public static String getPositionSecciones(String pos){
		   
		 
           /// return the position of the hashmap
           return  hmSecciones.get(pos);
	   }
	   
	   public static List<String> getSecciones()
	   {
		   try {
			   return new ArrayList<String>(hmSecciones.values());
	        } catch (Exception ex){
	        	/// nulo si hay error
	            return null;
	        }
	   }
	   
	   public static String getPositionSeccionesRpm(String pos){
		   
			 
           /// return the position of the hashmap
           return  hmSeccionesRpm.get(pos);
	   }
	   
	   public static List<String> getSeccionesRpm()
	   {
		   try {
			   List<String> list = new ArrayList<String>();
			   SortedSet<String> keys = new TreeSet<String>(hmSeccionesRpm.keySet());
			   for (String key : keys) { 
			      String value = hmSeccionesRpm.get(key);
			      // do something
			      list.add(value);
			   }

			   return new ArrayList(hmSeccionesRpm.values());
	        } catch (Exception ex){
	        	/// nulo si hay error
	            return null;
	        }
	   }
	   
	   public static String getPositionFisuras(String pos){
		   
			 
           /// return the position of the hashmap
           return  hmFisuras.get(pos);
	   }
	   
	   public static List<String> getFisuras()
	   {
		   try {
			   List<String> list = new ArrayList<String>();
			   SortedSet<String> keys = new TreeSet<String>(hmFisuras.keySet());
			   for (String key : keys) { 
			      String value = hmFisuras.get(key);
			      // do something
			      list.add(value);
			   }

			   return new ArrayList(hmFisuras.values());
	        } catch (Exception ex){
	        	/// nulo si hay error
	            return null;
	        }
	   }
	   
	   public static String getPositionLubricantes(String pos){
		   
			 
           /// return the position of the hashmap
           return  hmLubricantes.get(pos);
	   }
	   
	   public static List<String> getLubricantes()
	   {
		   try {
			   List<String> list = new ArrayList<String>();
			   SortedSet<String> keys = new TreeSet<String>(hmLubricantes.keySet());
			   for (String key : keys) { 
			      String value = hmLubricantes.get(key);
			      // do something
			      list.add(value);
			   }

			   return new ArrayList(hmLubricantes.values());
	        } catch (Exception ex){
	        	/// nulo si hay error
	            return null;
	        }
	   }

	public static List<String> getCabina()
	{
		try {
			List<String> list = new ArrayList<String>();
			SortedSet<String> keys = new TreeSet<String>(hmCabinas.keySet());
			for (String key : keys) {
				String value = hmCabinas.get(key);
				// do something
				list.add(value);
			}

			return  new ArrayList(hmCabinas.values());
		} catch (Exception ex){
			/// nulo si hay error
			return null;
		}
	}

	public static List<String> getZona()
	{
		try {
			List<String> list = new ArrayList<String>();
			SortedSet<String> keys = new TreeSet<String>(hmZonaProteccion.keySet());
			for (String key : keys) {
				String value = hmZonaProteccion.get(key);
				// do something
				list.add(value);
			}

			return  new ArrayList(hmZonaProteccion.values());
		} catch (Exception ex){
			/// nulo si hay error
			return null;
		}
	}

	public static List<String> getBrazoViga()
	{
		try {
			List<String> list = new ArrayList<String>();
			SortedSet<String> keys = new TreeSet<String>(hmBrazoViga.keySet());
			for (String key : keys) {
				String value = hmBrazoViga.get(key);
				// do something
				list.add(value);
			}

			return new ArrayList(hmBrazoViga.values());
		} catch (Exception ex){
			/// nulo si hay error
			return null;
		}
	}

	public static List<String> getSistemaIncendio()
	{
		try {
			List<String> list = new ArrayList<String>();
			SortedSet<String> keys = new TreeSet<String>(hmSistemaIncendio.keySet());
			for (String key : keys) {
				String value = hmSistemaIncendio.get(key);
				// do something
				list.add(value);
			}

			return new ArrayList(hmSistemaIncendio.values());
		} catch (Exception ex){
			/// nulo si hay error
			return null;
		}
	}
	   
	   public static String getPositionRodados(String pos){
           /// return the position of the hashmap
           return  hmRodados.get(pos);
	   }
	   
	   public static List<String> getRodados()
	   {
		   try {
			   List<String> list = new ArrayList<String>();
			   SortedSet<String> keys = new TreeSet<String>(hmRodados.keySet());
			   for (String key : keys) { 
			      String value = hmRodados.get(key);
			      // do something
			      list.add(value);
			   }

			   return new ArrayList(hmRodados.values());
	        } catch (Exception ex){
	        	/// nulo si hay error
	            return null;
	        }
	   }
	   
	   public static String getPositionPower(String pos){
		   
			 
           /// return the position of the hashmap
           return  hmPower.get(pos);
	   }
	   
	   public static List<String> getPower()
	   {
		   try {
			   List<String> list = new ArrayList<String>();
			   SortedSet<String> keys = new TreeSet<String>(hmPower.keySet());
			   for (String key : keys) { 
			      String value = hmPower.get(key);
			      // do something
			      list.add(value);
			   }

			   return new ArrayList(hmPower.values());
	        } catch (Exception ex){
	        	/// nulo si hay error
	            return null;
	        }
	   }
	   
	   public static String getPositionVarios(String pos){
		   
			 
           /// return the position of the hashmap
           return  hmVarios.get(pos);
	   }
	   
	   public static List<String> getVarios()
	   {
		   try {
			   List<String> list = new ArrayList<String>();
			   SortedSet<String> keys = new TreeSet<String>(hmVarios.keySet());
			   for (String key : keys) { 
			      String value = hmVarios.get(key);
			      // do something
			      list.add(value);
			   }

			   return new ArrayList(hmVarios.values());
	        } catch (Exception ex){
	        	/// nulo si hay error
	            return null;
	        }
	   }
	   
	   public static String getPositionDiesel(String pos){
		   
			 
           /// return the position of the hashmap
           return  hmDiesel.get(pos);
	   }
	   
	   public static List<String> getDiesel()
	   {
		   try {
			   List<String> list = new ArrayList<String>();
			   SortedSet<String> keys = new TreeSet<String>(hmDiesel.keySet());
			   for (String key : keys) { 
			      String value = hmDiesel.get(key);
			      // do something
			      list.add(value);
			   }

			   return new ArrayList(hmDiesel.values());
	        } catch (Exception ex){
	        	/// nulo si hay error
	            return null;
	        }
	   }
	   
	   public static String getPositionElectrico(String pos){
		   
			 
           /// return the position of the hashmap
           return  hmElectrico.get(pos);
	   }
	   
	   public static List<String> getElectrico()
	   {
		   try {
			   List<String> list = new ArrayList<String>();

			   SortedSet<String> keys = new TreeSet<String>(hmElectrico.keySet());

			   for (String key : keys) {
			      String value = hmElectrico.get(key);
			      // do something
			      list.add(value);
			   }

			   return new ArrayList(hmElectrico.values());
	        } catch (Exception ex){
	        	/// nulo si hay error
	            return null;
	        }
	   }
	   
	   public static String getPositionTrabajo(String pos){
		   
           /// return the position of the hashmap
           return  hmTrabajo.get(pos);
	   }
	   
	   public static List<String> getTrabajo()
	   {
		   try {
			   List<String> list = new ArrayList<String>();
			   SortedSet<String> keys = new TreeSet<String>(hmTrabajo.keySet());
			   for (String key : keys) { 
			      String value = hmTrabajo.get(key);
			      // do something
			      list.add(value);
			   }

			   return new ArrayList(hmTrabajo.values());
	        } catch (Exception ex){
	        	/// nulo si hay error
	            return null;
	        }
	   }
	
}
