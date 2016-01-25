package com.bit.singletons;

import java.util.*;

public class ProductHashmapCollectionSingleton {
		/// static instance to access
	   private static ProductHashmapCollectionSingleton instance = null;
	   
	   public static LinkedHashMap<String, String> hmEstados,hmFuncionamientoMedidas, hmFuncionamientoActions, hmCiudades, hmProblemas, hmSeccionItemsControlRodado,hmSeccionItemsCompresor,hmSeccionItemsLimpieza,hmSeccionItemsCamion, hmSeccionItemsVarios,hmSeccionItemsControlMangueras,hmSeccionItemsControlFisuras,hmSecciones; 
	   /// constructor
	   protected ProductHashmapCollectionSingleton() {
	      // Exists only to defeat instantiation.
	   }
	   /// gets the instance of the class
	   public static ProductHashmapCollectionSingleton getInstance() {
	      if(instance == null) {
	         instance = new ProductHashmapCollectionSingleton();
	         initializeHash();
	      }
	      /// just returns the instance
	      return instance;
	   }
	   
	   private static void initializeHash(){
		   
		   hmEstados = new LinkedHashMap<String, String>();
		   hmEstados.put("1", "MEDICION DE PRESIONES");
		   hmEstados.put("2", "TEST SISTEMA ELÉCTRICO");
		   
		   hmFuncionamientoMedidas= new LinkedHashMap<String, String>();
		   hmFuncionamientoMedidas.put("1", "RPM DEL CABEZAL DE ROT (VACIO) RPM");
		   hmFuncionamientoMedidas.put("2", "RPM DEL CABEZAL DE ROT (PESO) RPM");
		   hmFuncionamientoMedidas.put("3", "PRESION DE ROTACION PSI");
		   hmFuncionamientoMedidas.put("4", "PRESION DE EMPUJE PSI");
		   hmFuncionamientoMedidas.put("5", "PRESION DE PERCUSION PSI");
		   hmFuncionamientoMedidas.put("6", "VELOCIDAD DEL VENTILADOR RPM");
		   hmFuncionamientoMedidas.put("7", "TEMPERATURA COMPRESOR (CARGA) ºF");
		   hmFuncionamientoMedidas.put("8", "TEMPERATURA MOTOR (CARGA) ºF");
		   hmFuncionamientoMedidas.put("9", "PRESION DE AIRE EN DESCARGAºF");
		   hmFuncionamientoMedidas.put("10", "PRESION INTER-ETAPAS PSI");
		   hmFuncionamientoMedidas.put("11", "MAXIMA PRESION DE OPERACION PSI");
		   hmFuncionamientoMedidas.put("12", "VELOCIDAD DEL MOTOR BAJA 900 RPM");
		   hmFuncionamientoMedidas.put("13", "VELOCIDAD DEL MOTOR ALTA 2100 RPM");
		   hmFuncionamientoMedidas.put("14", "PRESION DE COMBUSITBLE A MAXIMAS PSI");
		   hmFuncionamientoMedidas.put("15", "PRESION DE COMBUSTIBLE A MAXIMAS RPM EN PSI");
		   
		   hmFuncionamientoActions= new LinkedHashMap<String, String>();
		   hmFuncionamientoActions.put("1", "TODOS LOS INDICADORES OPERANDO");
		   hmFuncionamientoActions.put("2", "AJUSTES APROPIADO DE CORREAS");
		   hmFuncionamientoActions.put("3", "DRRENAR CONDENSADO DE LOS TANQUES");
		   hmFuncionamientoActions.put("4", "FILTRO DE AIRE PRIMERO Y DE SEGURIDAD");
		   hmFuncionamientoActions.put("5", "FILTROS RACOR (OPCIONALES)");
		   hmFuncionamientoActions.put("6", "VERIFICAR CABLEADO ELECTRICO, TERMINALES");
		   hmFuncionamientoActions.put("7", "VERIFICAR NIVELES DE LOS FLUIDOS");
		   hmFuncionamientoActions.put("8", "ESPACES( ACEITE, COMBUS, REFRIGERANTE)");
		   hmFuncionamientoActions.put("9", "SISTEMA DE REGULACION DE AIRE OPERATIVO");
		   /// Secciones de problema
		   hmCiudades= new LinkedHashMap<String, String>();
		   hmCiudades.put("0", "");
		   hmCiudades.put("1", "ARICA");
		   hmCiudades.put("2", "IQUIQUE");
		   hmCiudades.put("3", "CALAMA");
		   hmCiudades.put("4", "ANTOFAGASTA");
		   hmCiudades.put("5", "COPIAPO");
		   hmCiudades.put("6", "LA SERENA");
		   hmCiudades.put("7", "VALPARAISO");
		   hmCiudades.put("8", "SANTIAGO");
		   hmCiudades.put("9", "RANCAGUA");
		   /// Secciones de problema
		   hmProblemas= new LinkedHashMap<String, String>();
		   hmProblemas.put("1", "HITOS EVALUACION");
		   hmProblemas.put("2", "PROBLEMAS");
		   hmProblemas.put("3", "FUTUROS PROBLEMAS");
		   hmProblemas.put("4", "REPUESTOS");
		   hmProblemas.put("5", "OBSERVACION");
		   /// Secciones
		   hmSecciones = new LinkedHashMap<String, String>();
		   hmSecciones.put("1", "CONTROL DE RODADO");
		   hmSecciones.put("2", "CONTROL ESTRUCTURAL DE FISURAS");
		   hmSecciones.put("3", "ESTADO DE MANGUERAS");
		   hmSecciones.put("4", "COMPRESOR");
		   hmSecciones.put("5", "LIMPIEZA DE LA MAQUINA");
		   hmSecciones.put("6", "CAMION");
		   hmSecciones.put("7", "VARIOS PERFORADORA");
		   /// Control Rodado
		   hmSeccionItemsControlRodado = new LinkedHashMap<String, String>();
		   hmSeccionItemsControlRodado.put("1", "LOGO TIPO EMPRESA");
		   hmSeccionItemsControlRodado.put("2", "Nº DE IDENTIFICACION TRASERO Y LATERALES");
		   hmSeccionItemsControlRodado.put("3", "Nº DE IDENTIFICACION PARA INGRESAR A LA MINA");
		   hmSeccionItemsControlRodado.put("4", "SEGUROS O INDICADORES DE TUERCA");
		   hmSeccionItemsControlRodado.put("5", "LIMPIA PARABRISAS");
		   hmSeccionItemsControlRodado.put("6", "SISTEMA MONITOREO(GPS-ANTIALCOLISION Y SALIDA DE PISTA");
		   hmSeccionItemsControlRodado.put("7", "PROTECCION ENTRE PICKUP Y CABINA");
		   hmSeccionItemsControlRodado.put("8", "ESPEJOS RETROVISORES LATERALES E INTERIOR");
		   hmSeccionItemsControlRodado.put("9", "CINTA REFLECTANTE TRASERA");
		   hmSeccionItemsControlRodado.put("10", "ESTADO DE NEUMATICO");
		   hmSeccionItemsControlRodado.put("11", "NEUMATICO DE REPUESTO");
		   hmSeccionItemsControlRodado.put("12", "PBALIZA Y PERTIGA");
		   hmSeccionItemsControlRodado.put("13", "CUÑA 1");
		   hmSeccionItemsControlRodado.put("14", "CUÑA 2");
		   hmSeccionItemsControlRodado.put("15", "RADIO BASE PARA INGRESO A MINA");
		   hmSeccionItemsControlRodado.put("16", "LUCES DE CONDUCCION");
		   hmSeccionItemsControlRodado.put("17", "LUCES DE ESTACIONAMIENTO");
		   hmSeccionItemsControlRodado.put("18", "LUCES INTERMITENTES");
		   hmSeccionItemsControlRodado.put("19", "LUCES DE RETROCESO");
		   hmSeccionItemsControlRodado.put("20", "LUCES DE FRENO");
		   hmSeccionItemsControlRodado.put("21", "ALARMA DE RETROCESO");
		   hmSeccionItemsControlRodado.put("22", "PBOCINA");
		   hmSeccionItemsControlRodado.put("23", "FRENO DE ESTACIONAMIENTO");
		   hmSeccionItemsControlRodado.put("24", "CINTURON DE SEGURIDAD PARA CONDUCIR");
		   hmSeccionItemsControlRodado.put("25", "CINTURON DE SEGURIDAD PARA PASAJEROS");
		   hmSeccionItemsControlRodado.put("26", "BARRA INTERNA CONTRA VOLCAMIENTO");
		   hmSeccionItemsControlRodado.put("27", "BARRA EXTERNA CONTRA VOLCAMIENTO");
		   hmSeccionItemsControlRodado.put("28", "TRIANGULOS");
		   hmSeccionItemsControlRodado.put("29", "EXTINTOR");
		   hmSeccionItemsControlRodado.put("30", "BOTIQUIN");
		   hmSeccionItemsControlRodado.put("31", "ANCLA");
		   hmSeccionItemsControlRodado.put("32", "MALLA");
		   hmSeccionItemsControlRodado.put("33", "OTRAS OBSERVACIONES");
           /// Control Fisuras
		   hmSeccionItemsControlFisuras = new LinkedHashMap<String, String>();
		   hmSeccionItemsControlFisuras.put("1", "BASTIDOR");
		   hmSeccionItemsControlFisuras.put("2", "CHASIS");
		   hmSeccionItemsControlFisuras.put("3", "SALA DE MAQUINAS");
		   hmSeccionItemsControlFisuras.put("4", "CABINA DE PERFORACION");
		   hmSeccionItemsControlFisuras.put("5", "TORRE");
		   hmSeccionItemsControlFisuras.put("6", "CARRETE DE CABLE");
		   /// Control Magueras
		   hmSeccionItemsControlMangueras = new LinkedHashMap<String, String>();
		   hmSeccionItemsControlMangueras.put("1", "SISTEMA HIDRAULICO");
		   hmSeccionItemsControlMangueras.put("2", "AIRE BARRIDO");
		   hmSeccionItemsControlMangueras.put("3", "LINEAS DE COMBUSTIBLE");
		   hmSeccionItemsControlMangueras.put("4", "LINEAS DE COMPRESOR");
		   /// Control de Compresor
		   hmSeccionItemsCompresor = new LinkedHashMap<String, String>();
		   hmSeccionItemsCompresor.put("1", "SISTEMA DE REGULACIÓN");
		   hmSeccionItemsCompresor.put("2", "SISTEMA DE SEPARACIÓN");
		   hmSeccionItemsCompresor.put("3", "SISTEMA DE LIBRICACIÓN");
		   hmSeccionItemsCompresor.put("4", "SISTEMA DE REFRIGERACIÓN");
		   /// Control de Limpieza
		   hmSeccionItemsLimpieza = new LinkedHashMap<String, String>();
		   hmSeccionItemsLimpieza.put("1", "ESTADO GENERAL");
		   /// Control Camion
		   hmSeccionItemsCamion = new LinkedHashMap<String, String>();
		   hmSeccionItemsCamion.put("1", "CAJA DE CAMBIOS");
		   hmSeccionItemsCamion.put("2", "FRENOS");
		   hmSeccionItemsCamion.put("3", "EMBRAGUE");
		   hmSeccionItemsCamion.put("4", "DIRECCIÓN");
		   hmSeccionItemsCamion.put("5", "NEUMÁTICOS");
		   hmSeccionItemsCamion.put("6", "CARDÁN");
		   hmSeccionItemsCamion.put("7", "DIFERENCIAL");
		   hmSeccionItemsCamion.put("8", "CHASIS");
		   hmSeccionItemsCamion.put("9", "SISTEMA ELECTRICO Y ILUMINACION");
		   /// Control Varios Perforadora
		   hmSeccionItemsVarios = new LinkedHashMap<String, String>();
		   hmSeccionItemsVarios.put("1", "ACOPLE MOTOR COMPRESOR");
		   hmSeccionItemsVarios.put("2", "ACOPLE MOTOR P.T.O.");
		   hmSeccionItemsVarios.put("3", "GATOS DE NIVELACIÓN");
		   hmSeccionItemsVarios.put("4", "ESTANQUE DE AGUA");
		   hmSeccionItemsVarios.put("5", "APRIETE DE PERNOS");
		   hmSeccionItemsVarios.put("6", "EFICIENCIA DEL COMPRESOR");
		   hmSeccionItemsVarios.put("7", "EFICIENCIA SISTEMA HIDRAULICO");
		   hmSeccionItemsVarios.put("8", "AMORTIGUADOR DE VIBRACIONES");
		   hmSeccionItemsVarios.put("9", "SISTEMA DE ILUMINACION");
		   hmSeccionItemsVarios.put("10", "BOMBA VAPOSOL");
		   hmSeccionItemsVarios.put("11", "SOPORTE DE BARRAS");
		   hmSeccionItemsVarios.put("12", "CARRUSEL PORTA BARRAS");
		   hmSeccionItemsVarios.put("13", "HERRAMIENTAS DE DESACOPLE");
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

			   return new ArrayList<String>(hmEstados.values());
	        } catch (Exception ex){
	        	/// nulo si hay error
	            return null;
	        }
	   }
	   
	   public static String getPositionFuncionamientoMedidas(String pos){
			  
           /// return the position of the hashmap
           return  hmFuncionamientoMedidas.get(pos);
	   }
	   
	   public static String getPositionFuncionamientoActions(String pos){
			  
           /// return the position of the hashmap
           return  hmFuncionamientoActions.get(pos);
	   }
	   
	   public static List<String> getFuncionamientoActions()
	   {
		   try {
			   List<String> list = new ArrayList<String>();
			   SortedSet<String> keys = new TreeSet<String>(hmFuncionamientoActions.keySet());
			   for (String key : keys) { 
			      String value = hmFuncionamientoActions.get(key);
			      // do something
			      list.add(value);
			   }
			   return new ArrayList<String>(hmFuncionamientoActions.values());
	        } catch (Exception ex){
	        	/// nulo si hay error
	            return null;
	        }
	   }
	   
	   public static List<String> getFuncionamientoMedidas()
	   {
		   try {
			   List<String> list = new ArrayList<String>();
			   SortedSet<String> keys = new TreeSet<String>(hmFuncionamientoMedidas.keySet());
			   for (String key : keys) { 
			      String value = hmFuncionamientoMedidas.get(key);
			      // do something
			      list.add(value);
			   }
			   return new ArrayList<String>(hmFuncionamientoMedidas.values());
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
	   public static String getPositionControlRodado(String pos){
		  
           /// return the position of the hashmap
           return  hmSeccionItemsControlRodado.get(pos);
	   }
	   
	   public static String getPositionCiudades(String pos){
           /// return the position of the hashmap
           return  hmCiudades.get(pos);
	   }
	   
	   public static String getPositionSecciones(String pos){
		   
		 
           /// return the position of the hashmap
           return  hmSecciones.get(pos);
	   }
	   
	   public static String getPositionTabuladorProblema(String pos){
		   
			 
           /// return the position of the hashmap
           return  hmProblemas.get(pos);
	   }
	   
	   public static String getPositionFisuras(String pos){
			  
           /// return the position of the hashmap
           return  hmSeccionItemsControlFisuras.get(pos);
	   }
	   public static String getPositionMangueras(String pos){
			  
           /// return the position of the hashmap
           return  hmSeccionItemsControlMangueras.get(pos);
	   }
	   public static String getPositionCompresor(String pos){
			  
           /// return the position of the hashmap
           return  hmSeccionItemsCompresor.get(pos);
	   }
	   public static String getPositionLimpieza(String pos){
			  
           /// return the position of the hashmap
           return  hmSeccionItemsLimpieza.get(pos);
	   }
	   public static String getPositionCamion(String pos){
			  
           /// return the position of the hashmap
           return  hmSeccionItemsCamion.get(pos);
	   }
	   public static String getPositionVarios(String pos){
			  
           /// return the position of the hashmap
           return  hmSeccionItemsVarios.get(pos);
	   }
	   /**
	    * 
	    * @return
	    */
	   public static List<String> getCheckListCamion()
	   {
		   try {
			   List<String> list = new ArrayList<String>();
			   SortedSet<String> keys = new TreeSet<String>(hmSeccionItemsControlRodado.keySet());
			   for (String key : keys) { 
			      String value = hmSeccionItemsControlRodado.get(key);
			      // do something
			      list.add(value);
			   }
			   return new ArrayList<String>(hmSeccionItemsControlRodado.values());
	        } catch (Exception ex){
	        	/// nulo si hay error
	            return null;
	        }
	   }
	   
	   public static List<String> getTabuladoresProblemas()
	   {
		   try {
			   List<String> list = new ArrayList<String>();
			   SortedSet<String> keys = new TreeSet<String>(hmProblemas.keySet());
			   for (String key : keys) { 
			      String value = hmProblemas.get(key);
			      // do something
			      list.add(value);
			   }
			   return new ArrayList<String>(hmProblemas.values());
	        } catch (Exception ex){
	        	/// nulo si hay error
	            return null;
	        }
	   }
	   
	   public static List<String> getCiudades()
	   {
		   try {
			   List<String> list = new ArrayList<String>();
			   SortedSet<String> keys = new TreeSet<String>(hmCiudades.keySet());
			   for (String key : keys) { 
			      String value = hmCiudades.get(key);
			      // do something
			      list.add(value);
			   }
			   return new ArrayList<String>(hmCiudades.values());
	        } catch (Exception ex){
	        	/// nulo si hay error
	            return null;
	        }
	   }
	   
	   public static List<String> getControlFisuras()
	   {
		   try {
			   List<String> list = new ArrayList<String>();
			   SortedSet<String> keys = new TreeSet<String>(hmSeccionItemsControlFisuras.keySet());
			   for (String key : keys) { 
			      String value = hmSeccionItemsControlFisuras.get(key);
			      // do something
			      list.add(value);
			   }
			   return new ArrayList<String>(hmSeccionItemsControlFisuras.values());
	        } catch (Exception ex){
	        	/// nulo si hay error
	            return null;
	        }
	   }
	   public static List<String> getControlMangueras()
	   {
		   try {
			   List<String> list = new ArrayList<String>();
			   SortedSet<String> keys = new TreeSet<String>(hmSeccionItemsControlMangueras.keySet());
			   for (String key : keys) { 
			      String value = hmSeccionItemsControlMangueras.get(key);
			      // do something
			      list.add(value);
			   }
			   return new ArrayList<String>(hmSeccionItemsControlMangueras.values());
	        } catch (Exception ex){
	        	/// nulo si hay error
	            return null;
	        }
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
	   
	   public static List<String> loadSectionItems(int pos)
	   {
		   switch(pos){
		   case 0:
			   return getCheckListCamion();
		   default:
			   return getCheckListCamion();
		   }
	   }
	   
	private static List<String> getControlVarios() {
		try {
			   List<String> list = new ArrayList<String>();
			   SortedSet<String> keys = new TreeSet<String>(hmSeccionItemsVarios.keySet());
			   for (String key : keys) { 
			      String value = hmSeccionItemsVarios.get(key);
			      // do something
			      list.add(value);
			   }

			   return new ArrayList<String>(hmSeccionItemsVarios.values());
	        } catch (Exception ex){
	        	/// nulo si hay error
	            return null;
	        }
	}
	private static List<String> getControlCamion() {
		try {
			   List<String> list = new ArrayList<String>();
			   SortedSet<String> keys = new TreeSet<String>(hmSeccionItemsCamion.keySet());
			   for (String key : keys) { 
			      String value = hmSeccionItemsCamion.get(key);
			      // do something
			      list.add(value);
			   }

			   return new ArrayList<String>(hmSeccionItemsCamion.values());
	        } catch (Exception ex){
	        	/// nulo si hay error
	            return null;
	        }
	}
	private static List<String> getControlLimpieza() {
		try {
			   List<String> list = new ArrayList<String>();
			   SortedSet<String> keys = new TreeSet<String>(hmSeccionItemsLimpieza.keySet());
			   for (String key : keys) { 
			      String value = hmSeccionItemsLimpieza.get(key);
			      // do something
			      list.add(value);
			   }

			   return new ArrayList<String>(hmSeccionItemsLimpieza.values());
	        } catch (Exception ex){
	        	/// nulo si hay error
	            return null;
	        }
	}
	private static List<String> getControlCompresor() {
		try {
			   List<String> list = new ArrayList<String>();
			   SortedSet<String> keys = new TreeSet<String>(hmSeccionItemsCompresor.keySet());
			   for (String key : keys) { 
			      String value = hmSeccionItemsCompresor.get(key);
			      // do something
			      list.add(value);
			   }

			   return new ArrayList<String>(hmSeccionItemsCompresor.values());
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
	   public static String getPositionControlesRodados(String pos){
		  
           /// return the position of the hashmap
           return  hmSeccionItemsControlRodado.get(pos);
	   }
}
