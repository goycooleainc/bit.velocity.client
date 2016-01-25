package com.bit.singletons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventosHashmapCollectionSingleton {
		/// static instance to access
	   private static EventosHashmapCollectionSingleton instance = null;
	   ///
	   public static HashMap<String, String> hmTipo, hmClients, hmModelosComponente,hmFaena,hmMeridian,hmSucursales,hmPrioridad,hmEstados,hmFleetman,hmLineas,hmComponentes,hmEquipos,hmModeloComponente,hmNaves, hmSupervisores, hmLideres; 
	   /// constructor
	   protected EventosHashmapCollectionSingleton() {
	      // Exists only to defeat instantiation.
	   }
	   /// gets the instance of the class
	   public static EventosHashmapCollectionSingleton getInstance() {
	      if(instance == null) {
	         instance = new EventosHashmapCollectionSingleton();
	         initializeHash();
	      }
	      /// just returns the instance
	      return instance;
	   }
	   
	   private static void initializeHash(){
		 /// Hashmap de Sucursales
           hmSucursales = new HashMap<String, String>();
           hmSucursales.put("1", "Santiago");
           hmSucursales.put("2", "Antofagasta");
           /// Hashmap de Sucursales
           hmTipo = new HashMap<String, String>();
           hmTipo.put("0", "");
           hmTipo.put("1", "PROGRAMADA");
           hmTipo.put("2", "EMERGENCIA");
           
           hmPrioridad = new HashMap<String, String>();
           hmPrioridad.put("1", "Baja");
           hmPrioridad.put("2", "Media");
           hmPrioridad.put("3", "Alta");
           
           hmMeridian = new HashMap<String, String>();
           hmMeridian.put("0", "AM");
           hmMeridian.put("1", "PM");
           
           hmEstados = new HashMap<String, String>();
           hmEstados.put("0", "");
           hmEstados.put("1", "En espera de llegada");
           hmEstados.put("2", "En espera de atencion");
           hmEstados.put("3", "Lavado Pre-evaluacion");
           hmEstados.put("4", "En evaluacion");
           hmEstados.put("5", "En evaluacion interna");
           hmEstados.put("6", "En evaluacion externa");
           hmEstados.put("7", "En espera de aprobacion");
           hmEstados.put("8", "En reparacion");
           hmEstados.put("9", "En reparacion interna");
           hmEstados.put("10", "En reparacion externa");
           hmEstados.put("11", "Lavado Pre-despacho");
           hmEstados.put("12", "Pintado");
           hmEstados.put("13", "Despachado");
           hmEstados.put("14", "Puesta en Marcha");
           hmEstados.put("15", "En Trabajo");
           hmEstados.put("16", "Revisión");
           hmEstados.put("17", "Espera de Repuestos");
           hmEstados.put("15", "En Trabajo");
           hmEstados.put("16", "Revisión");
           hmEstados.put("17", "Espera de Repuestos");
           hmEstados.put("18", "Trabajo Terminado");

           
           hmFleetman = new HashMap<String, String>();
           hmFleetman.put("0", "");
           hmFleetman.put("1", "Pizarro Lopez Ricardo");
           hmFleetman.put("2", "Plattner Quilodran Alvaro");
           hmFleetman.put("3", "Rojas Espinoza Andonis");
           hmFleetman.put("4", "Salgado Hormazabal Alvaro");
           hmFleetman.put("5", "Valdes Montoya Sergio");
           hmFleetman.put("6", "Cesar Garrido");
           hmFleetman.put("17", "Alejandro Espinoza");
           
         /// Hashmap de Sucursales
           hmLineas = new HashMap<String, String>();
           hmLineas.put("0", "");
           hmLineas.put("1", "ADS");
           hmLineas.put("3", "LHD");
           hmLineas.put("4", "SED");
           hmLineas.put("5", "TME");
           hmLineas.put("6", "GIA");
           hmLineas.put("7", "MAY");
           hmLineas.put("8", "N/A");
           
     
           hmComponentes = new HashMap<String, String>();
           hmComponentes.put("0", "");
           hmComponentes.put("12","N/A");
           hmComponentes.put("15","TRANSMISIÓN");
           hmComponentes.put("16","EJE");
           hmComponentes.put("17","COP");
           hmComponentes.put("18","CILINDRO");
           hmComponentes.put("19","BUT");
           hmComponentes.put("20","CARRUSEL");
           hmComponentes.put("21","BOMBA");
           hmComponentes.put("22","UNIDAD DE ROTACIÓN");
           hmComponentes.put("23","VIGA");
           hmComponentes.put("24","TRANSMISIÓN");
           hmComponentes.put("25","UP-BOX");
           hmComponentes.put("26","DROP BOX");
           hmComponentes.put("27","EJE");
           hmComponentes.put("28","BOMBA");
           hmComponentes.put("29","BALDE");
           hmComponentes.put("30","CABEZAL DE ROTACIÓN");
           hmComponentes.put("31","COMPRESOR");
           hmComponentes.put("32","PTO");
           hmComponentes.put("33","BOMBA");
           hmComponentes.put("34","MOTOR");
           hmComponentes.put("35","TORRE");
           hmComponentes.put("36","MANDO FINAL");
           hmComponentes.put("37","CILINDRO");
           hmComponentes.put("38","BASTIDOR DERECHO");
           hmComponentes.put("39","BASTIDOR IZQUIERDO");
           hmComponentes.put("40","MANDO FINAL");
           hmComponentes.put("41","UNIDAD DE ROTACIÓN");
           hmComponentes.put("42","COP");
           hmComponentes.put("43","CILINDRO");
           hmComponentes.put("44","VIGA");
           hmComponentes.put("45","BASTIDOR DERECHO");
           hmComponentes.put("46","BASTIDOR IZQUIERDO");
           hmComponentes.put("47","EJE");
           hmComponentes.put("48","TRANSMISIÓN");
           hmComponentes.put("49","BASTIDOR DERECHO");
           hmComponentes.put("50","BASTIDOR IZQUIERDO");
           hmComponentes.put("51","EJE");
           hmComponentes.put("52","TRANSMISIÓN");

           
         /// Hashmap de Sucursales
           hmFaena = new HashMap<String, String>();
           hmFaena.put("0", "");
           hmFaena.put("1", "Cerro Colorado");
           hmFaena.put("2","Collahuasi");
           hmFaena.put("3","Quebrada Blanca");
           hmFaena.put("4","El Abra");
           hmFaena.put("5","Chuquicamata");
           hmFaena.put("6","Radomiro Tomic");
           hmFaena.put("7","Michilla");
           hmFaena.put("8","Spence");
           hmFaena.put("9","El Tesoro");
           hmFaena.put("10","Mantos Blancos");
           hmFaena.put("11","Lomas Bayas");
           hmFaena.put("12","Zaldivar");
           hmFaena.put("13","Escondida");
           hmFaena.put("14","Fundicion Altonorte");
           hmFaena.put("15","El Peñon");
           hmFaena.put("16","El Salvador");
           hmFaena.put("17","Ojos del Salado");
           hmFaena.put("18","Mantoverde");
           hmFaena.put("19","Candelaria");
           hmFaena.put("20","Carmen de Andacollo");
           hmFaena.put("21","Los Pelambres");
           hmFaena.put("22","El Soldado");
           hmFaena.put("23","Fundicion Chagres");
           hmFaena.put("24","Andina");
           hmFaena.put("25","Los Bronces");
 			hmFaena.put("26","El Teniente");
           hmFaena.put("27","Carola");
           hmFaena.put("28","Atacama Kozan");
           hmFaena.put("29","Kinross Maricunga");
           hmFaena.put("30","Linderos");
           hmFaena.put("31","Sierra Gorda");
           
         /// Equipos
           hmEquipos = new HashMap<String, String>();
           hmEquipos.put("0","");
           hmEquipos.put("273","DM25SP");
           hmEquipos.put("274","DM30");
           hmEquipos.put("275","DM45HP");
           hmEquipos.put("276","DM45LP");
           hmEquipos.put("277","DM50");
           hmEquipos.put("278","DM50LP");
           hmEquipos.put("279","DMH");
           hmEquipos.put("280","DMH2");
           hmEquipos.put("281","DMHE");
           hmEquipos.put("282","DMLHP");
           hmEquipos.put("283","DMLLP");
           hmEquipos.put("284","DMM2");
           hmEquipos.put("285","DMM2H");
           hmEquipos.put("286","DMM3");
           hmEquipos.put("287","PV-271");
           hmEquipos.put("288","PV-271D");
           hmEquipos.put("289","PV-271D RCS4");
           hmEquipos.put("290","PV-271RCS");
           hmEquipos.put("291","PV-275");
           hmEquipos.put("292","PV-275D");
           hmEquipos.put("293","PV-275D RCS4");
           hmEquipos.put("294","PV351");
           hmEquipos.put("295","PV351D");
           hmEquipos.put("296","PV351E");
           hmEquipos.put("297","PV351E RCS");
           hmEquipos.put("298","PV351ELP");
           hmEquipos.put("299","PV351LP");
           hmEquipos.put("300","PV351RCS");
           hmEquipos.put("301","RD10+");
           hmEquipos.put("302","T4BH");
           hmEquipos.put("303","T4W");
           hmEquipos.put("304","TH75");
           hmEquipos.put("309","MT2010");
           hmEquipos.put("310","MT6020");
           hmEquipos.put("311","ST1010");
           hmEquipos.put("312","ST1020");
           hmEquipos.put("313","ST1030");
           hmEquipos.put("314","ST14");
           hmEquipos.put("315","ST2D");
           hmEquipos.put("316","ST2G");
           hmEquipos.put("317","ST3.5");
           hmEquipos.put("318","ST6C");
           hmEquipos.put("319","ST7");
           hmEquipos.put("320","ST710");
           hmEquipos.put("325","AIRROC D65");
           hmEquipos.put("326","AIRROC T35");
           hmEquipos.put("327","B20");
           hmEquipos.put("328","CS 10");
           hmEquipos.put("329","CS 14");
           hmEquipos.put("330","CS 3001");
           hmEquipos.put("331","CS3000");
           hmEquipos.put("332","CS3001");
           hmEquipos.put("333","CS4002");
           hmEquipos.put("334","CT 14");
           hmEquipos.put("335","CT 20");
           hmEquipos.put("336","Diamec 232");
           hmEquipos.put("337","Diamec 252");
           hmEquipos.put("338","Diamec 264");
           hmEquipos.put("339","Diamec U4");
           hmEquipos.put("340","Diamec U6");
           hmEquipos.put("341","Diamec U8");
           hmEquipos.put("342","ECM-585MCII");
           hmEquipos.put("343","ECM-660III");
           hmEquipos.put("344","FlexiROC D55 -10SF");
           hmEquipos.put("345","FlexiROC D60 -10LF");
           hmEquipos.put("346","FlexiROC D65 -10LF");
           hmEquipos.put("347","FlexiROC T35 -11");
           hmEquipos.put("348","FlexiROC T35 -11LF");
           hmEquipos.put("349","FlexiROC T40 -11");
           hmEquipos.put("350","Mustang A32");
           hmEquipos.put("351","Mustang A52");
           hmEquipos.put("352","Mustang A66");
           hmEquipos.put("353","Powercrusher HCS 3715");
           hmEquipos.put("354","Powercrusher PC 1000");
           hmEquipos.put("355","Powercrusher PC 1055 J");
           hmEquipos.put("356","Powercrusher PC 1060 I");
           hmEquipos.put("357","Powercrusher PC 2");
           hmEquipos.put("358","PowerROC T25 DC -10");
           hmEquipos.put("359","PowerROC T35 -12");
           hmEquipos.put("360","PowerROC T45 -12");
           hmEquipos.put("361","ROC D7");
           hmEquipos.put("362","ROC D7-11");
           hmEquipos.put("363","ROC D7-11 LM");
           hmEquipos.put("364","ROC F7-11");
           hmEquipos.put("365","ROC F9");
           hmEquipos.put("366","ROC F9-11");
           hmEquipos.put("367","ROC F9C");
           hmEquipos.put("368","ROC F9-LM");
           hmEquipos.put("369","ROC L6(25)");
           hmEquipos.put("370","ROC L6-44 High");
           hmEquipos.put( "371","ROC L6-54 High");
           hmEquipos.put("372","ROC L8");
           hmEquipos.put("373","ROC L8(25)");
           hmEquipos.put("374","ROC L8(30)");
           hmEquipos.put("375","ROC T35M-12");
           hmEquipos.put("376","SmartROC D65 -10LF");
           hmEquipos.put("381","BOLTEC 235");
           hmEquipos.put("382","BOLTEC 251");
           hmEquipos.put("383","BOLTEC 335 H");
           hmEquipos.put("384","BOLTEC CABLE L");
           hmEquipos.put("385","BOLTEC L C");
           hmEquipos.put("386","BOLTEC M C");
           hmEquipos.put("387","BOOMER 104");
           hmEquipos.put("388","BOOMER 126");
           hmEquipos.put("389","BOOMER 251");
           hmEquipos.put("390","BOOMER 281");
           hmEquipos.put("391","BOOMER 281 DH");
           hmEquipos.put("392","BOOMER 282");
           hmEquipos.put("393","BOOMER 282-DC90");
           hmEquipos.put("394","BOOMER 282S");
           hmEquipos.put("395","BOOMER 322S");
           hmEquipos.put("396","BOOMER E2 C");
           hmEquipos.put("397","BOOMER L1 C DH");
           hmEquipos.put("398","BOOMER L1 D");
           hmEquipos.put("399","BOOMER M2 C");
           hmEquipos.put("400","BOOMER M2 D");
           hmEquipos.put("401","BOOMER S1 D");
           hmEquipos.put("402","BOOMER S1 D DH");
           hmEquipos.put("403","BOOMER T1 D");
           hmEquipos.put("404","BOOMER XE3 C");
           hmEquipos.put("405","RB600 LD");
           hmEquipos.put("406","ROCKET BOOMER 281");
           hmEquipos.put("407","ROCKET BOOMER 281 D18");
           hmEquipos.put("408","ROCKET BOOMER 282");
           hmEquipos.put("409","ROCKET BOOMER 282 D18");
           hmEquipos.put("410","ROCKET BOOMER L1 C DH");
           hmEquipos.put("411","ROCKET BOOMER L2 C");
           hmEquipos.put("412","ROCKET BOOMER L3 C");
           hmEquipos.put("413","ROCKET BOOMER M2 C");
           hmEquipos.put("414","ROCKET BOOMER M2 D");
           hmEquipos.put("415","ROCKET BOOMER S1 D");
           hmEquipos.put("416","ROCKET BOOMER S1 D18");
           hmEquipos.put("417","ROCKET BOOMER XL3 C");
           hmEquipos.put("418","SIMBA 254");
           hmEquipos.put("419","SIMBA 262");
           hmEquipos.put("420","SIMBA H1253");
           hmEquipos.put("421","SIMBA H1254");
           hmEquipos.put("422","SIMBA H1257");
           hmEquipos.put("423","SIMBA H157");
           hmEquipos.put("424","SIMBA H25");
           hmEquipos.put("425","SIMBA H253");
           hmEquipos.put("426","SIMBA H254");
           hmEquipos.put("427","SIMBA M4 C");
           hmEquipos.put("428","SIMBA M4 C ITH");
           hmEquipos.put("429","SIMBA M6 C DH");
           hmEquipos.put("430","SIMBA M6 C ITH");
           hmEquipos.put("431","SIMBA M7 C");
           hmEquipos.put("432","SIMBA S7 D");
           hmEquipos.put("434","SIMBA ME 7 C");
           hmEquipos.put("438","Poca");
           hmEquipos.put("439","Versa");
           hmEquipos.put("440","Potenza");
           hmEquipos.put("458","SCALETEC MC");
           hmEquipos.put("459","UV2 Scaletec");
           hmEquipos.put("460","UV211 Charging");
           hmEquipos.put("461","UV211 Scaling");
           hmEquipos.put("463","Häggloader");

           
           hmModelosComponente = new HashMap<String, String>();
           hmModelosComponente.put("0", "");      
           hmModelosComponente.put("189", "Funk DF 254"); 
           hmModelosComponente.put("190", "Clark T40000"); 
           hmModelosComponente.put("191", "Clark R 28000"); 
           hmModelosComponente.put("192", "Clark R 32000"); 
           hmModelosComponente.put("193", "Funk DF 154"); 
           hmModelosComponente.put("194", "Up Box ST1030"); 
           hmModelosComponente.put("195", "ST 14"); 
           hmModelosComponente.put("196", "Up Box Kessler MT6020"); 
           hmModelosComponente.put("197", "Clark 19D"); 
           hmModelosComponente.put("198", "Clark 21D"); 
           hmModelosComponente.put("199", "Clark 53R300"); 
           hmModelosComponente.put("200", "Kessler D101"); 
           hmModelosComponente.put("201", "Kessler D106"); 
           hmModelosComponente.put("202", "Kessler D111"); 
           hmModelosComponente.put("203", "Kessler RT 406"); 
           hmModelosComponente.put("204", "Kessler 508"); 
           hmModelosComponente.put("205", "Drop Box MT 6020"); 
           hmModelosComponente.put("206", "Levante"); 
           hmModelosComponente.put("207", "Volteo"); 
           hmModelosComponente.put("208", "Dirección"); 
           hmModelosComponente.put("209", "Clark T12000"); 
           hmModelosComponente.put("210", "Clark HR18000"); 
           hmModelosComponente.put("211", "Clark HR24000"); 
           hmModelosComponente.put("212", "COP 1838ME"); 
           hmModelosComponente.put("213", "COP HD"); 
           hmModelosComponente.put("214", "COP HD+"); 
           hmModelosComponente.put("215", "COP 1638ME"); 
           hmModelosComponente.put("216", "COP 2238"); 
           hmModelosComponente.put("217", "COP 2550"); 
           hmModelosComponente.put("218", "COP 2560"); 
           hmModelosComponente.put("219", "PV351"); 
           hmModelosComponente.put("220", "PV275"); 
           hmModelosComponente.put("221", "DM45"); 
           hmModelosComponente.put("222", "285"); 
           hmModelosComponente.put("223", "twin 285"); 
           hmModelosComponente.put("228", "HR 2.5"); 
           hmModelosComponente.put("229", "DS 60"); 
           hmModelosComponente.put("230", "DS 61"); 
           hmModelosComponente.put("231", "DHR 48"); 
           hmModelosComponente.put("232", "DHR 6"); 

			
			hmNaves = new HashMap<String, String>();
	           hmNaves.put("0", "");
	      	   hmNaves.put("1", "1");
	      	   hmNaves.put("2", "2");
	      	   hmNaves.put("3", "3");
	      	   hmNaves.put("4", "4");
	      	   hmNaves.put("5", "5");
	      	   hmNaves.put("6", "6");
	      	   hmNaves.put("7", "7");
	      	   hmNaves.put("8", "8");
	      	   hmNaves.put("9", "9");
	      	   hmNaves.put("10", "10");
	      	   hmNaves.put("Exterior", "Exterior");
	      	   
	      	 /// Hashmap de Sucursales
	           hmSupervisores = new HashMap<String, String>();
	           hmSupervisores.put("0", "");
	           hmSupervisores.put("1", "Ascencio Alfaro Gabriel");
	           hmSupervisores.put("2", "Gutierrez Ortiz Wilson");
	           hmSupervisores.put("3", "Herrera Cordova Patricio");
	           hmSupervisores.put("4", "Paz Carvajal Emiliano");
	           hmSupervisores.put("5", "Ramirez Avalos Alex");
	           hmSupervisores.put("6", "Valenzuela Sepulveda Juan");
	           
	           /// Hashmap de Sucursales
	           hmLideres = new HashMap<String, String>();
	           hmLideres.put("0","");
	           hmLideres.put("1","Alanis Reinoso Reinaldo");
	           hmLideres.put("2","Alfaro Ureta Francisco");
	           hmLideres.put("3","Altamirano Vera Cristian");
	           hmLideres.put("4","Altamirano Vera Marcelo" );
	           hmLideres.put("5","Arancibia Cueto Eduardo");
	           hmLideres.put("6","Arancibia Cueto Eduardo" );
	           hmLideres.put("7","Arias Suarez Bastian" );
	           hmLideres.put("8","Ascencio Muñoz Marcelo" );
	           hmLideres.put("9","Benedetti Cubillos Raul");
	           hmLideres.put("10","Cardenas Zuñiga Luis" );
	           hmLideres.put("11","Cerda Pizarro Raul" );
	           hmLideres.put("12","Cofre Araya Ronald" );
	           hmLideres.put("13","Cofre Perez Mauricio");
	           hmLideres.put("14","Delgado Maulen Jorge" );
	           hmLideres.put("15","Diaz Pinochet Adrian" );
	           hmLideres.put("16","Espinoza Cereceda Alexis" );
	           hmLideres.put("17","Godoy Moncada Marcelo" );
	           hmLideres.put("18","Herrera Adasme Osvaldo" );
	           hmLideres.put("19","Huenchucura Matamala Cristopher" );
	           hmLideres.put("20","Ibaceta Muñoz Farett" );
	           hmLideres.put("21","Jara Rodriguez Alexis" );
	           hmLideres.put("22","Leal Tamayo David" );
	           hmLideres.put("23","Leon Guajardo Jose");
	           hmLideres.put("24","Manquez Cabello Carlos" );
	           hmLideres.put("25","Medel Duarte Leonardo");
	           hmLideres.put("26","Mendez Pinochet Marco" );
	           hmLideres.put("27","Milling Muñoz Cesar" );
	           hmLideres.put("28","Narvaez Montenegro David");
	           hmLideres.put("29","Ortega Torres Felipe");
	           hmLideres.put("30","Palacios Mino Hector");
	           hmLideres.put("31","Perez Leiva Francisco");
	           hmLideres.put("32","Piña Astorga Jorge");
	           hmLideres.put("33","Plaza Rojas Miguel" );
	           hmLideres.put("34","Puelma Guzman Julio" );
	           hmLideres.put("35","Ramirez Radrigan Oscar" );
	           hmLideres.put("36","Rojas Curivil Alvaro" );
	           hmLideres.put("37","Rojas Salinas Alejandro" );
	           hmLideres.put("38","Sandoval Valdes Francisco");
	           hmLideres.put("39","Santander Araya Anibal" );
	           hmLideres.put("40", "Uribe Malio Mario");
	           hmLideres.put("41","Vielmas Navarro Victor" );
	           hmLideres.put("42","Villarroel Villarroel Luis" );
	           hmLideres.put("43", "Villavicencio Aguayo Juan" );
	           hmLideres.put("44","Zuñiga Gutierrez Oscar");
	   }
	   /**
	    * Obtenemos Sucursales
	    * 
	    * @return List<String> Sucursales
	    */
	   public static List<String> getSucursales()
	   {
		   try {
	            // Spinner de Estados
	            List<String> ArraySucursales = new ArrayList<String>();
	            /// sucursales 
	            ArraySucursales.add("Santiago");
	            ArraySucursales.add("Antofagasta");
	            /// return collection
	            return ArraySucursales;
	        } catch (Exception ex){
	        	/// nulo si hay error
	            return null;
	        }
	   }
	   /**
	    * Obtenemos el objeto dada la posicion
	    * de una Sucursal
	    * 
	    * @param pos
	    * @return String
	    */
	   public static String getPositionSucursal(int pos){
		   
           /// return the position of the hashmap
           return  hmSucursales.get(pos);
	   }
	   /**
	    * 
	    * @return
	    */
	   public static List<String> getPrioridades()
	   {
		   try {
			 //Spinner de prioridades prioridades
	            List<String> ArrayPrioridades = new ArrayList<String>();
	            ArrayPrioridades.add("");
	            ArrayPrioridades.add("Baja");
	            ArrayPrioridades.add("Media");
	            ArrayPrioridades.add("Alta");
	            /// return collection
	            return ArrayPrioridades;
	        } catch (Exception ex){
	        	/// nulo si hay error
	            return null;
	        }
	   }
	   /**
	    * 
	    * @param
	    * @return
	    */
	   public static String getPositionPrioridad(String pos){
		   /// Hashmap de Sucursales
           
           /// return the position of the hashmap
           return  hmPrioridad.get(pos);
	   }
	   
	   public static List<String> getMeridians()
	   {
		   try {
			 //Spinner de prioridades prioridades
	            List<String> ArrayMeridian = new ArrayList<String>();
	            ArrayMeridian.add("AM");
	            ArrayMeridian.add("PM");
	            /// return collection
	            return ArrayMeridian;
	        } catch (Exception ex){
	        	/// nulo si hay error
	            return null;
	        }
	   }
	   /**
	    * 
	    * @param
	    * @return
	    */
	   public static String getPositionMeridian(String index){
		   /// Hashmap de Sucursales
           
           /// return the position of the hashmap
           return  hmMeridian.get(index);
	   }
	   /**
	    * 
	    * @return
	    */
	   public static List<String> getEstados()
	   {
		   try {
			 //Spinner de prioridades prioridades
	            List<String> ArrayEstados = new ArrayList<String>();
	            ArrayEstados.add("");
	            ArrayEstados.add("En espera de llegada");
	            ArrayEstados.add("En espera de atencion");
	            ArrayEstados.add("Lavado Pre-evaluacion");
	            ArrayEstados.add("En evaluacion");
	            ArrayEstados.add("En evaluacion interna");
	            ArrayEstados.add("En evaluacion externa");
	            ArrayEstados.add("En espera de aprobacion");
	            ArrayEstados.add("En reparacion");
	            ArrayEstados.add("En reparacion interna");
	            ArrayEstados.add("En reparacion externa");
	            ArrayEstados.add("Lavado Pre-despacho");
	            ArrayEstados.add("Pintado");
	            ArrayEstados.add("Despachado");
	            ArrayEstados.add("Puesta en Marcha");
	            ArrayEstados.add("En Trabajo");
	            ArrayEstados.add("Revisión");
	            ArrayEstados.add("Espera de Repuestos");
	            ArrayEstados.add("En Trabajo");
	            ArrayEstados.add("Revisión");
	            ArrayEstados.add("Espera de Repuestos");
	            ArrayEstados.add("En Trabajo");
	            ArrayEstados.add("Revisión");
	            ArrayEstados.add("Espera de Repuestos");
	            ArrayEstados.add("Trabajo Terminado");
	            /// return collection
	            return ArrayEstados;
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
	   public static String getPositionEstados(int pos){
		   /// Hashmap de Sucursales
		   
           /// return the position of the hashmap
           return  hmEstados.get(pos);
	   }
	   /**
	    * 
	    * @return
	    */
	   public static List<String> getFleetManagers()
	   {
		   try {
			 //Spinner de prioridades prioridades
	            List<String> ArrayFleetMan = new ArrayList<String>();
	            ArrayFleetMan.add("");
	            ArrayFleetMan.add("Pizarro Lopez Ricardo");
	            ArrayFleetMan.add("Plattner Quilodran Alvaro");
	            ArrayFleetMan.add("Rojas Espinoza Andonis");
	            ArrayFleetMan.add("Salgado Hormazabal Alvaro");
	            ArrayFleetMan.add("Valdes Montoya Sergio");
	            ArrayFleetMan.add("Cesar Garrido");
	            ArrayFleetMan.add("Alejandro Espinoza");
	            /// return collection
	            return ArrayFleetMan;
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
	   public static String getPositionFleetManager(int pos){
		   /// Hashmap de Sucursales
           
           /// return the position of the hashmap
           return  hmFleetman.get(pos);
	   }
	   /**
	    * 
	    * @return
	    */
	   public static List<String> getTipos()
	   {
		   try {
			 //Spinner de prioridades prioridades
	            List<String> ArrayLineas = new ArrayList<String>();
	            ArrayLineas.add("");
	            ArrayLineas.add("PROGRAMADA");
	            ArrayLineas.add("EMERGENCIA");
	            /// return collection
	            return ArrayLineas;
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
	   public static String getPositionTipo(int pos){
		   /// Hashmap de Sucursales
           
           /// return the position of the hashmap
           return  hmTipo.get(pos);
	   }
	   /**
	    * 
	    * @return
	    */
	   public static List<String> getLineas()
	   {
		   try {
			 //Spinner de prioridades prioridades
	            List<String> ArrayLineas = new ArrayList<String>();
	            ArrayLineas.add("");
	            ArrayLineas.add("ADS");
	            ArrayLineas.add("LHD");
	            ArrayLineas.add("SED");
	            ArrayLineas.add("TME");
	            ArrayLineas.add("GIA");
	            ArrayLineas.add("MAY");
	            ArrayLineas.add("N/A");
	            /// return collection
	            return ArrayLineas;
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
	   public static String getPositionLinea(int pos){
		   pos = pos-1;
           /// return the position of the hashmap
           return  hmLineas.get(pos);
	   }
	   /**
	    * 
	    * @return
	    */
	   public static List<String> getComponentes()
	   {
		   try {
			 //Spinner de prioridades prioridades
	            List<String> ArrayComponente = new ArrayList<String>();
	            ArrayComponente.add("");
	            ArrayComponente.add("N/A");
	            ArrayComponente.add("TRANSMISIÓN");
	            ArrayComponente.add("EJE");
	            ArrayComponente.add("COP");
	            ArrayComponente.add("CILINDRO");
	            ArrayComponente.add("BUT");
	            ArrayComponente.add("CARRUSEL");
	            ArrayComponente.add("BOMBA");
	            ArrayComponente.add("UNIDAD DE ROTACIÓN");
	            ArrayComponente.add("VIGA");
	            ArrayComponente.add("TRANSMISIÓN");
	            ArrayComponente.add("UP-BOX");
	            ArrayComponente.add("DROP BOX");
	            ArrayComponente.add("EJE");
	            ArrayComponente.add("BOMBA");
	            ArrayComponente.add("BALDE");
	            ArrayComponente.add("CABEZAL DE ROTACIÓN");
	            ArrayComponente.add("COMPRESOR");
	            ArrayComponente.add("PTO");
	            ArrayComponente.add("BOMBA");
	            ArrayComponente.add("MOTOR");
	            ArrayComponente.add("TORRE");
	            ArrayComponente.add("MANDO FINAL");
	            ArrayComponente.add("CILINDRO");
	            ArrayComponente.add("BASTIDOR DERECHO");
	            ArrayComponente.add("BASTIDOR IZQUIERDO");
	            ArrayComponente.add("MANDO FINAL");
	            ArrayComponente.add("UNIDAD DE ROTACIÓN");
	            ArrayComponente.add("COP");
	            ArrayComponente.add("CILINDRO");
	            ArrayComponente.add("VIGA");
	            ArrayComponente.add("BASTIDOR DERECHO");
	            ArrayComponente.add("BASTIDOR IZQUIERDO");
	            ArrayComponente.add("EJE");
	            ArrayComponente.add("TRANSMISIÓN");
	            ArrayComponente.add("BASTIDOR DERECHO");
	            ArrayComponente.add("BASTIDOR IZQUIERDO");
	            ArrayComponente.add("EJE");
	            ArrayComponente.add("TRANSMISIÓN");
	            /// return collection
	            return ArrayComponente;
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
	   public static String getPositionComponente(int pos){
		   
           /// return the position of the hashmap
           return  hmComponentes.get(pos);
	   }
	   /**
	    * 
	    * @return
	    */
	   public static List<String> getFaenas()
	   {
		   try {
			 //Spinner de prioridades prioridades
	            List<String> ArrayFaenas = new ArrayList<String>();
	            ArrayFaenas.add("");
	            ArrayFaenas.add("Cerro Colorado");
	            ArrayFaenas.add("Collahuasi");
	            ArrayFaenas.add("Quebrada Blanca");
	            ArrayFaenas.add("El Abra");
	            ArrayFaenas.add("Chuquicamata");
	            ArrayFaenas.add("Radomiro Tomic");
	            ArrayFaenas.add("Michilla");
	            ArrayFaenas.add("Spence");
	            ArrayFaenas.add("El Tesoro");
	            ArrayFaenas.add("Mantos Blancos");
	            ArrayFaenas.add("Lomas Bayas");
	            ArrayFaenas.add("Zaldivar");
	            ArrayFaenas.add("Escondida");
	            ArrayFaenas.add("Fundicion Altonorte");
	            ArrayFaenas.add("El Peñon");
	            ArrayFaenas.add("El Salvador");
	            ArrayFaenas.add("Ojos del Salado");
	            ArrayFaenas.add("Mantoverde");
	            ArrayFaenas.add("Candelaria");
	            ArrayFaenas.add("Carmen de Andacollo");
	            ArrayFaenas.add("Los Pelambres");
	            ArrayFaenas.add("El Soldado");
	            ArrayFaenas.add("Fundicion Chagres");
	            ArrayFaenas.add("Andina");
	            ArrayFaenas.add("Los Bronces");
	            ArrayFaenas.add("El Teniente");
	            ArrayFaenas.add("Carola");
	            ArrayFaenas.add("Atacama Kozan");
	            ArrayFaenas.add("Kinross Maricunga");
	            ArrayFaenas.add("Linderos");
	            ArrayFaenas.add("Sierra Gorda");
	            /// return collection
	            return ArrayFaenas;
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
	   public static String getPositionFaena(int pos){
		   
           /// return the position of the hashmap
           return  hmFaena.get(pos);
	   }
	   
	   public static List<String> getADSEquipo()
	   {
		   try {
			 //Spinner de prioridades prioridades
	            List<String> ArrayModeloEquipo = new ArrayList<String>();
	            ArrayModeloEquipo.add("");
	            ArrayModeloEquipo.add("DM25SP");
	            ArrayModeloEquipo.add("DM30");
	            ArrayModeloEquipo.add("DM45HP");
	            ArrayModeloEquipo.add("DM45LP");
	            ArrayModeloEquipo.add("DM50");
	            ArrayModeloEquipo.add("DM50LP");
	            ArrayModeloEquipo.add("DMH");
	            ArrayModeloEquipo.add("DMH2");
	            ArrayModeloEquipo.add("DMHE");
	            ArrayModeloEquipo.add("DMLHP");
	            ArrayModeloEquipo.add("DMLLP");
	            ArrayModeloEquipo.add("DMM2");
	            ArrayModeloEquipo.add("DMM2H");
	            ArrayModeloEquipo.add("DMM3");
	            ArrayModeloEquipo.add("PV-271");
	            ArrayModeloEquipo.add("PV-271D");
	            ArrayModeloEquipo.add("PV-271D RCS4");
	            ArrayModeloEquipo.add("PV-271RCS");
	            ArrayModeloEquipo.add("PV-275");
	            ArrayModeloEquipo.add("PV-275D");
	            ArrayModeloEquipo.add("PV-275D RCS4");
	            ArrayModeloEquipo.add("PV351");
	            ArrayModeloEquipo.add("PV351D");
	            ArrayModeloEquipo.add("PV351E");
	            ArrayModeloEquipo.add("PV351E RCS");
	            ArrayModeloEquipo.add("PV351ELP");
	            ArrayModeloEquipo.add("PV351LP");
	            ArrayModeloEquipo.add("PV351RCS");
	            ArrayModeloEquipo.add("RD10+");
	            ArrayModeloEquipo.add("T4BH");
	            ArrayModeloEquipo.add("T4W");
	            ArrayModeloEquipo.add("TH75");
	          /// return collection
	            return ArrayModeloEquipo;
	        } catch (Exception ex){
	        	/// nulo si hay error
	            return null;
	        }
	   }
	   
	   public static List<String> getLHDEquipo()
	   {
		   try{
		   List<String> ArrayCollection = new ArrayList<String>();
		   ArrayCollection.add("");
		   ArrayCollection.add("MT2010");
		   ArrayCollection.add("MT6020");
		   ArrayCollection.add("ST1010");
		   ArrayCollection.add("ST1020");
		   ArrayCollection.add("ST1030");
           ArrayCollection.add("ST14");
           ArrayCollection.add("ST2D");
           ArrayCollection.add("ST2G");
           ArrayCollection.add("ST3.5");
           ArrayCollection.add("ST6C");
           ArrayCollection.add("ST7");
           ArrayCollection.add("ST710");
           
           return ArrayCollection;
		   }catch(Exception ex){
			   return null;
		   }
	   }
	   
	   public static List<String> getTMEEquipo()
	   {
		   try{
			   List<String> ArrayCollection = new ArrayList<String>();
			   ArrayCollection.add("");
			   ArrayCollection.add("BOLTEC 235");
	            ArrayCollection.add("BOLTEC 251");
	            ArrayCollection.add("BOLTEC 335 H");
	            ArrayCollection.add("BOLTEC CABLE L");
	            ArrayCollection.add("BOLTEC L C");
	            ArrayCollection.add("BOLTEC M C");
	            ArrayCollection.add("BOOMER 104");
	            ArrayCollection.add("BOOMER 126");
	            ArrayCollection.add("BOOMER 251");
	            ArrayCollection.add("BOOMER 281");
	            ArrayCollection.add("BOOMER 281 DH");
	            ArrayCollection.add("BOOMER 282");
	            ArrayCollection.add("BOOMER 282-DC90");
	            ArrayCollection.add("BOOMER 282S");
	            ArrayCollection.add("BOOMER 322S");
	            ArrayCollection.add("BOOMER E2 C");
	            ArrayCollection.add("BOOMER L1 C DH");
	            ArrayCollection.add("BOOMER L1 D");
	            ArrayCollection.add("BOOMER M2 C");
	            ArrayCollection.add("BOOMER M2 D");
	            ArrayCollection.add("BOOMER S1 D");
	            ArrayCollection.add("BOOMER S1 D DH");
	            ArrayCollection.add("BOOMER T1 D");
	            ArrayCollection.add("BOOMER XE3 C");
	            ArrayCollection.add("RB600 LD");
	            return ArrayCollection;
		   }catch(Exception ex){
			   return null;
		   }
	   }
	   
	   public static List<String> getSDEEquipo()
	   {
		   try {
			 //Spinner de prioridades prioridades
	            List<String> ArrayCollection = new ArrayList<String>();
	           ArrayCollection.add("");
	           ArrayCollection.add("AIRROC D65");
	           ArrayCollection.add("AIRROC T35");
	           ArrayCollection.add("B20");
	           ArrayCollection.add("CS 10");
	           ArrayCollection.add("CS 14");
	           ArrayCollection.add("CS 3001");
	           ArrayCollection.add("CS3000");
	           ArrayCollection.add("CS3001");
	           ArrayCollection.add("CS4002");
	           ArrayCollection.add("CT 14");
	           ArrayCollection.add("CT 20");
	           ArrayCollection.add("Diamec 232");
	           ArrayCollection.add("Diamec 252");
	           ArrayCollection.add("Diamec 264");
	           ArrayCollection.add("Diamec U4");
	           ArrayCollection.add("Diamec U6");
	           ArrayCollection.add("Diamec U8");
	           ArrayCollection.add("ECM-585MCII");
	           ArrayCollection.add("ECM-660III");
	           ArrayCollection.add("FlexiROC D55 -10SF");
	           ArrayCollection.add("FlexiROC D60 -10LF");
	           ArrayCollection.add("FlexiROC D65 -10LF");
	           ArrayCollection.add("FlexiROC T35 -11");
	           ArrayCollection.add("FlexiROC T35 -11LF");
	           ArrayCollection.add("FlexiROC T40 -11");
	           ArrayCollection.add("Mustang A32");
	           ArrayCollection.add("Mustang A52");
	           ArrayCollection.add("Mustang A66");
	           ArrayCollection.add("Powercrusher HCS 3715");
	           ArrayCollection.add("Powercrusher PC 1000");
	           ArrayCollection.add("Powercrusher PC 1055 J");
	           ArrayCollection.add("Powercrusher PC 1060 I");
	           ArrayCollection.add("Powercrusher PC 2");
	           ArrayCollection.add("PowerROC T25 DC -10");
	           ArrayCollection.add("PowerROC T35 -12");
	           ArrayCollection.add("PowerROC T45 -12");
	           ArrayCollection.add("ROC D7");
	           ArrayCollection.add("ROC D7-11");
	           ArrayCollection.add("ROC D7-11 LM");
	           ArrayCollection.add("ROC F7-11");
	           ArrayCollection.add("ROC F9");
	           ArrayCollection.add("ROC F9-11");
	           ArrayCollection.add("ROC F9C");
	           ArrayCollection.add("ROC F9-LM");
	           ArrayCollection.add("ROC L6(25)");
	           ArrayCollection.add("ROC L6-44 High");
	           ArrayCollection.add("ROC L6-54 High");
	           ArrayCollection.add("ROC L8");
	           ArrayCollection.add("ROC L8(25)");
	           ArrayCollection.add("ROC L8(30)");
	           ArrayCollection.add("ROC T35M-12");
	           ArrayCollection.add("SmartROC D65 -10LF");
	          /// return collection
	            return ArrayCollection;
	        } catch (Exception ex){
	        	/// nulo si hay error
	            return null;
	        }
	   }
	            
	   /**
	    * 
	    * @return
	    */
	   public static List<String> getModeloEquipo()
	   {
		   try {
			 //Spinner de prioridades prioridades
	            List<String> ArrayModeloEquipo = new ArrayList<String>();
	            ArrayModeloEquipo.add("");
	            ArrayModeloEquipo.add("DM25SP");
	            ArrayModeloEquipo.add("DM30");
	            ArrayModeloEquipo.add("DM45HP");
	            ArrayModeloEquipo.add("DM45LP");
	            ArrayModeloEquipo.add("DM50");
	            ArrayModeloEquipo.add("DM50LP");
	            ArrayModeloEquipo.add("DMH");
	            ArrayModeloEquipo.add("DMH2");
	            ArrayModeloEquipo.add("DMHE");
	            ArrayModeloEquipo.add("DMLHP");
	            ArrayModeloEquipo.add("DMLLP");
	            ArrayModeloEquipo.add("DMM2");
	            ArrayModeloEquipo.add("DMM2H");
	            ArrayModeloEquipo.add("DMM3");
	            ArrayModeloEquipo.add("PV-271");
	            ArrayModeloEquipo.add("PV-271D");
	            ArrayModeloEquipo.add("PV-271D RCS4");
	            ArrayModeloEquipo.add("PV-271RCS");
	            ArrayModeloEquipo.add("PV-275");
	            ArrayModeloEquipo.add("PV-275D");
	            ArrayModeloEquipo.add("PV-275D RCS4");
	            ArrayModeloEquipo.add("PV351");
	            ArrayModeloEquipo.add("PV351D");
	            ArrayModeloEquipo.add("PV351E");
	            ArrayModeloEquipo.add("PV351E RCS");
	            ArrayModeloEquipo.add("PV351ELP");
	            ArrayModeloEquipo.add("PV351LP");
	            ArrayModeloEquipo.add("PV351RCS");
	            ArrayModeloEquipo.add("RD10+");
	            ArrayModeloEquipo.add("T4BH");
	            ArrayModeloEquipo.add("T4W");
	            ArrayModeloEquipo.add("TH75");
	            ArrayModeloEquipo.add("MT2010");
	            ArrayModeloEquipo.add("MT6020");
	            ArrayModeloEquipo.add("ST1010");
	            ArrayModeloEquipo.add("ST1020");
	            ArrayModeloEquipo.add("ST1030");
	            ArrayModeloEquipo.add("ST14");
	            ArrayModeloEquipo.add("ST2D");
	            ArrayModeloEquipo.add("ST2G");
	            ArrayModeloEquipo.add("ST3.5");
	            ArrayModeloEquipo.add("ST6C");
	            ArrayModeloEquipo.add("ST7");
	            ArrayModeloEquipo.add("ST710");
	            ArrayModeloEquipo.add("AIRROC D65");
	            ArrayModeloEquipo.add("AIRROC T35");
	            ArrayModeloEquipo.add("B20");
	            ArrayModeloEquipo.add("CS 10");
	            ArrayModeloEquipo.add("CS 14");
	            ArrayModeloEquipo.add("CS 3001");
	            ArrayModeloEquipo.add("CS3000");
	            ArrayModeloEquipo.add("CS3001");
	            ArrayModeloEquipo.add("CS4002");
	            ArrayModeloEquipo.add("CT 14");
	            ArrayModeloEquipo.add("CT 20");
	            ArrayModeloEquipo.add("Diamec 232");
	            ArrayModeloEquipo.add("Diamec 252");
	            ArrayModeloEquipo.add("Diamec 264");
	            ArrayModeloEquipo.add("Diamec U4");
	            ArrayModeloEquipo.add("Diamec U6");
	            ArrayModeloEquipo.add("Diamec U8");
	            ArrayModeloEquipo.add("ECM-585MCII");
	            ArrayModeloEquipo.add("ECM-660III");
	            ArrayModeloEquipo.add("FlexiROC D55 -10SF");
	            ArrayModeloEquipo.add("FlexiROC D60 -10LF");
	            ArrayModeloEquipo.add("FlexiROC D65 -10LF");
	            ArrayModeloEquipo.add("FlexiROC T35 -11");
	            ArrayModeloEquipo.add("FlexiROC T35 -11LF");
	            ArrayModeloEquipo.add("FlexiROC T40 -11");
	            ArrayModeloEquipo.add("Mustang A32");
	            ArrayModeloEquipo.add("Mustang A52");
	            ArrayModeloEquipo.add("Mustang A66");
	            ArrayModeloEquipo.add("Powercrusher HCS 3715");
	            ArrayModeloEquipo.add("Powercrusher PC 1000");
	            ArrayModeloEquipo.add("Powercrusher PC 1055 J");
	            ArrayModeloEquipo.add("Powercrusher PC 1060 I");
	            ArrayModeloEquipo.add("Powercrusher PC 2");
	            ArrayModeloEquipo.add("PowerROC T25 DC -10");
	            ArrayModeloEquipo.add("PowerROC T35 -12");
	            ArrayModeloEquipo.add("PowerROC T45 -12");
	            ArrayModeloEquipo.add("ROC D7");
	            ArrayModeloEquipo.add("ROC D7-11");
	            ArrayModeloEquipo.add("ROC D7-11 LM");
	            ArrayModeloEquipo.add("ROC F7-11");
	            ArrayModeloEquipo.add("ROC F9");
	            ArrayModeloEquipo.add("ROC F9-11");
	            ArrayModeloEquipo.add("ROC F9C");
	            ArrayModeloEquipo.add("ROC F9-LM");
	            ArrayModeloEquipo.add("ROC L6(25)");
	            ArrayModeloEquipo.add("ROC L6-44 High");
	            ArrayModeloEquipo.add("ROC L6-54 High");
	            ArrayModeloEquipo.add("ROC L8");
	            ArrayModeloEquipo.add("ROC L8(25)");
	            ArrayModeloEquipo.add("ROC L8(30)");
	            ArrayModeloEquipo.add("ROC T35M-12");
	            ArrayModeloEquipo.add("SmartROC D65 -10LF");
	            ArrayModeloEquipo.add("BOLTEC 235");
	            ArrayModeloEquipo.add("BOLTEC 251");
	            ArrayModeloEquipo.add("BOLTEC 335 H");
	            ArrayModeloEquipo.add("BOLTEC CABLE L");
	            ArrayModeloEquipo.add("BOLTEC L C");
	            ArrayModeloEquipo.add("BOLTEC M C");
	            ArrayModeloEquipo.add("BOOMER 104");
	            ArrayModeloEquipo.add("BOOMER 126");
	            ArrayModeloEquipo.add("BOOMER 251");
	            ArrayModeloEquipo.add("BOOMER 281");
	            ArrayModeloEquipo.add("BOOMER 281 DH");
	            ArrayModeloEquipo.add("BOOMER 282");
	            ArrayModeloEquipo.add("BOOMER 282-DC90");
	            ArrayModeloEquipo.add("BOOMER 282S");
	            ArrayModeloEquipo.add("BOOMER 322S");
	            ArrayModeloEquipo.add("BOOMER E2 C");
	            ArrayModeloEquipo.add("BOOMER L1 C DH");
	            ArrayModeloEquipo.add("BOOMER L1 D");
	            ArrayModeloEquipo.add("BOOMER M2 C");
	            ArrayModeloEquipo.add("BOOMER M2 D");
	            ArrayModeloEquipo.add("BOOMER S1 D");
	            ArrayModeloEquipo.add("BOOMER S1 D DH");
	            ArrayModeloEquipo.add("BOOMER T1 D");
	            ArrayModeloEquipo.add("BOOMER XE3 C");
	            ArrayModeloEquipo.add("RB600 LD");
	            ArrayModeloEquipo.add("ROCKET BOOMER 281");
	            ArrayModeloEquipo.add("ROCKET BOOMER 281 D18");
	            ArrayModeloEquipo.add("ROCKET BOOMER 282");
	            ArrayModeloEquipo.add("ROCKET BOOMER 282 D18");
	            ArrayModeloEquipo.add("ROCKET BOOMER L1 C DH");
	            ArrayModeloEquipo.add("ROCKET BOOMER L2 C");
	            ArrayModeloEquipo.add("ROCKET BOOMER L3 C");
	            ArrayModeloEquipo.add("ROCKET BOOMER M2 C");
	            ArrayModeloEquipo.add("ROCKET BOOMER M2 D");
	            ArrayModeloEquipo.add("ROCKET BOOMER S1 D");
	            ArrayModeloEquipo.add("ROCKET BOOMER S1 D18");
	            ArrayModeloEquipo.add("ROCKET BOOMER XL3 C");
	            ArrayModeloEquipo.add("SIMBA 254");
	            ArrayModeloEquipo.add("SIMBA 262");
	            ArrayModeloEquipo.add("SIMBA H1253");
	            ArrayModeloEquipo.add("SIMBA H1254");
	            ArrayModeloEquipo.add("SIMBA H1257");
	            ArrayModeloEquipo.add("SIMBA H157");
	            ArrayModeloEquipo.add("SIMBA H25");
	            ArrayModeloEquipo.add("SIMBA H253");
	            ArrayModeloEquipo.add("SIMBA H254");
	            ArrayModeloEquipo.add("SIMBA M4 C");
	            ArrayModeloEquipo.add("SIMBA M4 C ITH");
	            ArrayModeloEquipo.add("SIMBA M6 C DH");
	            ArrayModeloEquipo.add("SIMBA M6 C ITH");
	            ArrayModeloEquipo.add("SIMBA M7 C");
	            ArrayModeloEquipo.add("SIMBA S7 D");
	            ArrayModeloEquipo.add("SIMBA ME 7 C");
	            ArrayModeloEquipo.add("Poca");
	            ArrayModeloEquipo.add("Versa");
	            ArrayModeloEquipo.add("Potenza");
	            ArrayModeloEquipo.add("SCALETEC MC");
	            ArrayModeloEquipo.add("UV2 Scaletec");
	            ArrayModeloEquipo.add("UV211 Charging");
	            ArrayModeloEquipo.add("UV211 Scaling");
	            ArrayModeloEquipo.add("Häggloader");

	            /// return collection
	            return ArrayModeloEquipo;
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
	   public static String getPositionModeloEquipo(int pos){
		   pos = 275- pos;
           /// return the position of the hashmap
           return  hmEquipos.get(pos);
	   }
	   
	   public static String getDirectPositionModeloEquipo(int pos){
		   pos +=272;
           /// return the position of the hashmap
           return  hmEquipos.get(String.valueOf(pos));
	   }
	   /**
	    * 
	    * @return
	    */
	   public static List<String> getModeloComponente()
	   {
		   try {
			 //Spinner de prioridades prioridades
	            List<String> ArrayModeloComponente = new ArrayList<String>();
	            ArrayModeloComponente.add("");
	            ArrayModeloComponente.add("Funk DF 254");
	            ArrayModeloComponente.add("Clark T40000");
	            ArrayModeloComponente.add("Clark R 28000");
	            ArrayModeloComponente.add("Clark R 32000");
	            ArrayModeloComponente.add("Funk DF 154");
	            ArrayModeloComponente.add("Up Box ST1030");
	            ArrayModeloComponente.add("ST 14");
	            ArrayModeloComponente.add("Up Box Kessler MT6020");
	            ArrayModeloComponente.add("Clark 19D");
	            ArrayModeloComponente.add("Clark 21D");
	            ArrayModeloComponente.add("Clark 53R300");
	            ArrayModeloComponente.add("Kessler D101");
	            ArrayModeloComponente.add("Kessler D106");
	            ArrayModeloComponente.add("Kessler D111");
	            ArrayModeloComponente.add("Kessler RT 406");
	            ArrayModeloComponente.add("Kessler 508");
	            ArrayModeloComponente.add("Drop Box MT 6020");
	            ArrayModeloComponente.add("Levante");
	            ArrayModeloComponente.add("Volteo");
	            ArrayModeloComponente.add("Dirección");
	            ArrayModeloComponente.add("Clark T12000");
	            ArrayModeloComponente.add("Clark HR18000");
	            ArrayModeloComponente.add("Clark HR24000");
	            ArrayModeloComponente.add("COP 1838ME");
	            ArrayModeloComponente.add("COP HD");
	            ArrayModeloComponente.add("COP HD+");
	            ArrayModeloComponente.add("COP 1638ME");
	            ArrayModeloComponente.add("COP 2238");
	            ArrayModeloComponente.add("COP 2550");
	            ArrayModeloComponente.add("COP 2560");
	            ArrayModeloComponente.add("PV351");
	            ArrayModeloComponente.add("PV275");
	            ArrayModeloComponente.add("DM45");
	            ArrayModeloComponente.add("285");
	            ArrayModeloComponente.add("twin 285");
	            ArrayModeloComponente.add("HR 2.5");
	            ArrayModeloComponente.add("DS 60");
	            ArrayModeloComponente.add("DS 61");
	            ArrayModeloComponente.add("DHR 48");
	            ArrayModeloComponente.add("DHR 6");
	            /// return collection
	            return ArrayModeloComponente;
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
	   public static String getPositionModeloComponente(int pos){
		   /// Hashmap de Sucursales
            pos = 189 - pos;
           /// return the position of the hashmap
           return  hmModelosComponente.get(pos);
	   }

    public static String getNombreDesdePositionModeloComponente(int pos){
        /// return the position of the hashmap
        return  hmModelosComponente.get(pos);
    }
	   /**
	    * 
	    * @param pos
	    * @return
	    */
	   public static String getPositionNave(int pos){
		   /// Hashmap de Sucursales
           
           /// return the position of the hashmap
           return  hmNaves.get(pos);
	   }
	   /**
	    * 
	    * @return
	    */
	   public static List<String> getNaves()
	   {
		   try {
			 //Spinner de prioridades prioridades
	            List<String> ArrayNaves = new ArrayList<String>();
	            ArrayNaves.add("");
	            ArrayNaves.add("1");
	            ArrayNaves.add("2");
	            ArrayNaves.add("3");
	            ArrayNaves.add("4");
	            ArrayNaves.add("5");
	            ArrayNaves.add("6");
	            ArrayNaves.add("7");
	            ArrayNaves.add("8");
	            ArrayNaves.add("9");
	            ArrayNaves.add("10");
	            ArrayNaves.add("Exterior");
	            /// return collection
	            return ArrayNaves;
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
	   public static String getPositionSupervisor(int pos){
		  
           /// return the position of the hashmap
           return  hmSupervisores.get(pos);
	   }
	   /**
	    * 
	    * @return
	    */
	   public static List<String> getSupervisores()
	   {
		   try {
			 //Spinner de prioridades prioridades
	            List<String> ArraySupervisores = new ArrayList<String>();
	            ArraySupervisores.add("");
	            ArraySupervisores.add("Ascencio Alfaro Gabriel");
	            ArraySupervisores.add("Gutierrez Ortiz Wilson");
	            ArraySupervisores.add("Herrera Cordova Patricio");
	            ArraySupervisores.add("Paz Carvajal Emiliano");
	            ArraySupervisores.add("Ramirez Avalos Alex");
	            ArraySupervisores.add("Valenzuela Sepulveda Juan");
	            /// return collection
	            return ArraySupervisores;
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
	   public static String getPositionLider(int pos){
		  
           /// return the position of the hashmap
           return  hmLideres.get(pos);
	   }
	   /**
	    * 
	    * @return
	    */
	   public static List<String> getLideres()
	   {
		   try {
			 //Spinner de prioridades prioridades
	            List<String> ArrayLideres = new ArrayList<String>();
	            ArrayLideres.add("");
	            ArrayLideres.add("Alanis Reinoso Reinaldo");
	            ArrayLideres.add("Alfaro Ureta Francisco");
	            ArrayLideres.add("Altamirano Vera Cristian");
	            ArrayLideres.add("Altamirano Vera Marcelo");
	            ArrayLideres.add("Amigo Bravo Jaime");
	            ArrayLideres.add("Arancibia Cueto Eduardo");
	            ArrayLideres.add("Arias Suarez Bastian");
	            ArrayLideres.add("Ascencio Muñoz Marcelo");
	            ArrayLideres.add("Benavides Agurto Francis");
	            ArrayLideres.add("Benedetti Cubillos Raul");
	            ArrayLideres.add("Cardenas Zuñiga Luis");
	            ArrayLideres.add("Cerda Pizarro Raul");
	            ArrayLideres.add("Cofre Araya Ronald");
	            ArrayLideres.add("Cofre Perez Mauricio");
	            ArrayLideres.add("Delgado Maulen Jorge");
	            ArrayLideres.add("Diaz Pinochet Adrian");
	            ArrayLideres.add("Espinoza Cereceda Alexis");
	            ArrayLideres.add("Godoy Moncada Marcelo");
	            ArrayLideres.add("Herrera Adasme Osvaldo");
	            ArrayLideres.add("Huenchucura Matamala Cristopher");
	            ArrayLideres.add("Ibaceta Muñoz Farett");
	            ArrayLideres.add("Jara Rodriguez Alexis");
	            ArrayLideres.add("Leal Tamayo David");
	            ArrayLideres.add("Leon Guajardo Jose");
	            ArrayLideres.add("Manquez Cabello Carlos");
	            ArrayLideres.add("Medel Duarte Leonardo");
	            ArrayLideres.add("Mendez Pinochet Marco");
	            ArrayLideres.add("Milling Muñoz Cesar");
	            ArrayLideres.add("Narvaez Montenegro David");
	            ArrayLideres.add("Ortega Torres Felipe");
	            ArrayLideres.add("Palacios Mino Hector");
	            ArrayLideres.add("Perez Leiva Francisco");
	            ArrayLideres.add("Piña Astorga Jorge");
	            ArrayLideres.add("Plaza Rojas Miguel");
	            ArrayLideres.add("Puelma Guzman Julio");
	            ArrayLideres.add("Ramirez Radrigan Oscar");
	            ArrayLideres.add("Rojas Curivil Alvaro");
	            ArrayLideres.add("Rojas Salinas Alejandro");
	            ArrayLideres.add("Sandoval Valdes Francisco");
	            ArrayLideres.add("Santander Araya Anibal");
	            ArrayLideres.add("Uribe Malio Mario");
	            ArrayLideres.add("Vielmas Navarro Victor");
	            ArrayLideres.add("Villarroel Villarroel Luis");
	            ArrayLideres.add("Villavicencio Aguayo Juan");
	            ArrayLideres.add("Zuñiga Gutierrez Oscar");
	            /// return collection
	            return ArrayLideres;
	        } catch (Exception ex){
	        	/// nulo si hay error
	            return null;
	        }
	   }
	   ////
	   public static Object getKeyFromValue(Map hm, Object value) {
	        for (Object o : hm.keySet()) {
	            if (hm.get(o).equals(value)) {
	                return o;
	            }
	        }
	        return null;
	    }
	   
}
