package com.bit.singletons;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.bit.json.factory.JSONReader;
import com.bit.preferences.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.*;

public class CacheCollectionSingleton {
		/// static instance to access
	   private static CacheCollectionSingleton instance = null;
	   private static Context context;
	   private SharedPreference sharedPreference;
	   private ADSAuditoriaSharedPreference adsAuditoriaSharedPreference;
	   private SDEAuditoriaSharedPreference sdeAuditoriaSharedPreference;
	   private LHDAuditoriaSharedPreference lhdAuditoriaSharedPreference;
	   private TMEAuditoriaSharedPreference tmeAuditoriaSharedPreference;
	   private ReportSupportSharedPreference reportSupportSharedPreference;
	   
	   private AuditoriesInMemmorySharedPreference auditoriesInMemmorySharedPreference;
	   private ReportsInMemmorySharedPreference reportsInMemmorySharedPreference;
	   
	   private EmergencySharedPreference emergencySharedPreference;
	   private BatchADSSharedPreference batchADSSharedPreference;
	   private BatchSDESharedPreference batchSDESharedPreference;
	   private BatchLHDSharedPreference batchLHDSharedPreference;
	   private BatchTMESharedPreference batchTMESharedPreference;
	   
	   private PicturesCommentsSharedPreference picturesCommentsSharedPreference;
	   private VideoCommentsSharedPreference videoCommentsSharedPreference;
	   private UpdateSharedPreference updateSharePreference;
	   private PicturesSharedPreference picturesSharedPreference;
	   private VideosSharedPreference videosSharedPreference;
	   private PicturesQaSharedPreference picturesQaSharedPreference;
	   private PicturesQaCommentsSharedPreference picturesQaCommentsSharedPreference;
	   private SignatureClientSharedPreference signatureClientSharedPreference;
	   private SignatureReceptionSharedPreference signatureReceptionSharedPreference;
	   private UpdateSharedPreference updateSharedPreference;
	   private DailyOrderSharedPreference dailyShared;
	   private ClientsSharedPreference clientsShared;
	   private ClientsSupportSharedPreference clientsSupportShared;
	   private EquiposSupportSharedPreference equiposSupportShared;
	   private ModelosSupportSharedPreference modelosSupportShared;
	   public static LinkedHashMap<String, String> hmClientes, hmEquiposSupport,hmModelosSupport, hmClientesSoporte;
	   private static String privateInternalID;

	   /* TODO
	   * Borrar todos los preferences que no se usan
	   * */
	   private VentasSharedPreference ventasSharedPreference;
	   ///
	   public static String collection_ventas, video_comments,collection_equipos_soporte,collection_modelos_soporte,collection_clientes_soporte,collection_audit_memmory,collection_videos, collection_support_memmory, collection_batch_support,collection_support,collection_emergency, collection_clientes, collection_batch_ads,collection_batch_sde,collection_batch_lhd,collection_batch_tme,collection_ads,collection_lhd,collection_tme,collection_sde, collection_order,collection_orders_update,collection_orders_new, pictures_qa, pictures_comments_qa,pictures_comments, pictures, signature_client, signature_reception;
	   private UsersInMemmorySharedPreference usersInMemmorySharedPreference;
	   private static String collection_users_memmory;

	/// constructor
	   protected CacheCollectionSingleton() {
	      // Exists only to defeat instantiation.
	   }
	   /// gets the instance of the class
	   public static CacheCollectionSingleton getInstance(Context _context) {
		   
		  context = _context;
	      
		  if(instance == null) {
			  
			  setPrivateInternalID("");
	    	  //SharedPreferences.Editor prefs = context.getSharedPreferences("com.atlas", Context.MODE_PRIVATE).edit();
			  collection_users_memmory="";
	    	  collection_videos="";
			  collection_order="";
			  collection_ventas = "";
			  video_comments = "";
			  collection_equipos_soporte = "";
			  collection_clientes_soporte="";
			  collection_audit_memmory = "";
	    	  collection_support_memmory="";
	    	  collection_batch_support = "";
	    	  collection_support = "";
			  collection_orders_update = "";
		      collection_orders_new = "";
		      collection_emergency = "";
		      pictures_qa = "";
		      pictures = "";
		      signature_client = "";
		      signature_reception = "";
		      pictures_comments_qa = "";
		      pictures_comments="";
		      collection_ads= "";
		      collection_sde= "";
		      collection_lhd= "";
		      collection_tme= "";
		      collection_batch_ads= "";
		      collection_batch_sde = "";
		      collection_batch_lhd = "";
		      collection_batch_tme = "";
		      collection_clientes="";
		      hmClientes = new LinkedHashMap<String, String>();
		      hmEquiposSupport = new LinkedHashMap<String, String>();
		      hmModelosSupport = new LinkedHashMap<String, String>();
		      hmClientesSoporte = new LinkedHashMap<String, String>();
		      
	          instance = new CacheCollectionSingleton();
	      }
	      
	      /// just returns the instance
	      return instance;
	   }


		public static String getFormatNumber(int w){
			if(w<10){
				return "0"+String.valueOf(w);
			}else{
				return String.valueOf(w);
			}
		}

	   public List<String> getClientesSoporte() throws JSONException
	   {
		   clientsSupportShared = new ClientsSupportSharedPreference();
		   String json_aux = clientsSupportShared.getValue(context);
		   
		   if(json_aux == null || json_aux == ""){
			   String json = JSONReader.loadJSONClientesSoporteFromAsset(context);
			   clientsSupportShared.save(context, json);
			   collection_clientes_soporte = json;
			   
			    JSONObject json_clients = new JSONObject(json);
				JSONArray clientes = (JSONArray) json_clients.get("Clientes");
				for(int i=0;i<clientes.length();i++){
					JSONObject parent = (JSONObject) clientes.get(i);
					 hmClientesSoporte.put(parent.getString("id"), parent.getString("nombre"));
				}
				
				
		   }else{
			   JSONObject json_clients = new JSONObject(json_aux);
				JSONArray clientes = (JSONArray) json_clients.get("Clientes");
				for(int i=0;i<clientes.length();i++){
					JSONObject parent = (JSONObject) clientes.get(i);
					hmClientesSoporte.put(parent.getString("id"), parent.getString("nombre"));
				}
		   }
		   
		   try {
			   List<String> list = new ArrayList<String>();
			   SortedSet<String> keys = new TreeSet<String>(hmClientesSoporte.keySet());
			   for (String key : keys) { 
			      String value = hmClientesSoporte.get(key);
			      // do something
			      list.add(value);
			   }
			   return list;
	        } catch (Exception ex){
	        	/// nulo si hay error
	            return null;
	        }
		   
	   }
	   
	   public String getEquiposSoporte(int id){
		   return hmEquiposSupport.get(id);
	   }
	   
	   public String getIndexClientesSoporte(String cliente){
		   int pos = new ArrayList<String>(hmClientesSoporte.values()).indexOf(cliente);
		   return String.valueOf(pos);
	   }
	   
	   public String getIndexClientes(String cliente){
		   int pos = new ArrayList<String>(hmClientes.values()).indexOf(cliente);
		   return String.valueOf(pos);
	   }
	   
	   public String getIndexEquiposSoporte(String cliente){
		  
		   int pos = new ArrayList<String>(hmEquiposSupport.values()).indexOf(cliente);
		   
		   return String.valueOf(pos);
	   }
	   
	   public List<String> getEquiposSupport(String filter) throws JSONException
	   {
		   Iterator var3 = hmClientesSoporte.entrySet().iterator();
		   String var1="";
		   while(var3.hasNext()) {
			   Map.Entry var4 = (Map.Entry)var3.next();
			   if((var4.getValue()).equals(filter)) {
				   var1 = (String)var4.getKey();
			   }
		   }

		   this.equiposSupportShared = new EquiposSupportSharedPreference();
		   this.equiposSupportShared.save(context, null);
		   hmEquiposSupport.clear();

		   String var7 = JSONReader.loadJSONEquiposFromAsset(context);
		   this.equiposSupportShared.save(context, var7);
		   collection_equipos_soporte = var7;

		   ArrayList var6 = new ArrayList();
		   JSONArray var8 = (new JSONObject(var7)).getJSONArray("Equipos");

		   for(int var2 = 0; var2 < var8.length(); ++var2) {
			   JSONObject var5 = var8.getJSONObject(var2);
			   hmEquiposSupport.put(var5.getString("id_cliente"), var5.getString("modelo"));

			   if(var5.getString("id_cliente").equals(var1)) {

				   EventosHashmapCollectionSingleton.getInstance();
				   var6.add(EventosHashmapCollectionSingleton.hmEquipos.get(var5.getString("modelo")));
				   Log.i("equipos filtrados", var5.getString("id_cliente") + "=" + var5.getString("modelo"));
			   } else {
				   //Log.i("equipos", var5.getString("id_cliente") + "=" + var5.getString("modelo"));
			   }
		   }

			// add elements to al, including duplicates
		   Set<String> hs = new HashSet<>();
		   hs.addAll(var6);
		   var6.clear();
		   var6.addAll(hs);

		   return var6;
	   }

	public List<String> getModelosSupport(String filter, String cliente) throws JSONException {

		Iterator var33 = hmClientesSoporte.entrySet().iterator();
		String var11="";
		while(var33.hasNext()) {
			Map.Entry var44 = (Map.Entry)var33.next();
			if((var44.getValue()).equals(cliente)) {
				var11 = (String)var44.getKey();
			}
		}

		Iterator var3 = EventosHashmapCollectionSingleton.hmEquipos.entrySet().iterator();
		String var1="";
		while(var3.hasNext()) {
			Map.Entry var4 = (Map.Entry)var3.next();
			if((var4.getValue()).equals(filter)) {
				var1 = (String)var4.getKey();
			}
		}

		this.equiposSupportShared = new EquiposSupportSharedPreference();
		this.equiposSupportShared.save(context, null);

		String var7 = JSONReader.loadJSONEquiposFromAsset(context);
		this.equiposSupportShared.save(context, var7);
		collection_equipos_soporte = var7;

		ArrayList var6 = new ArrayList();
		JSONArray var5 = (new JSONObject(var7)).getJSONArray("Equipos");
		EventosHashmapCollectionSingleton.getInstance();
		for(int var2 = 0; var2 < var5.length(); ++var2) {
			JSONObject var8 = var5.getJSONObject(var2);
			String numero_modelo = var8.getString("modelo");
			String id_cliente = var8.getString("id_cliente");

			if(numero_modelo.equals(var1) && id_cliente.equals(var11)) {
				var6.add(var8.getString("nro_serie"));
				Log.i("series filtrados", var8.getString("id_cliente") + "=" + var8.getString("modelo"));
			} else {
				//Log.i("equipos", var8.getString("id_cliente") + "=" + var8.getString("modelo"));
			}
		}

		return var6;
	}
	   
	   public List<String> getModelosSupport() throws JSONException
	   {
		   modelosSupportShared = new ModelosSupportSharedPreference();
		   String json_aux = modelosSupportShared.getValue(context);
		   
		   if(json_aux == null || json_aux == ""){
			   String json = JSONReader.loadJSONClientesSoporteFromAsset(context);
			   modelosSupportShared.save(context, json);
			   collection_modelos_soporte = json;
			   
			    JSONObject json_clients = new JSONObject(json);
				JSONArray clientes = (JSONArray) json_clients.get("Equipos");
				for(int i=0;i<clientes.length();i++){
					JSONObject parent = (JSONObject) clientes.get(i);
					hmModelosSupport.put(parent.getString("modelo"), parent.getString("nro_serie"));
				}
				
				
		   }else{
			   JSONObject json_clients = new JSONObject(json_aux);
				JSONArray clientes = (JSONArray) json_clients.get("Equipos");
				for(int i=0;i<clientes.length();i++){
					JSONObject parent = (JSONObject) clientes.get(i);
					hmModelosSupport.put(parent.getString("modelo"), parent.getString("nro_serie"));
				}
		   }
		   
		   try {
			   List<String> list = new ArrayList<String>();
			   SortedSet<String> keys = new TreeSet<String>(hmModelosSupport.keySet());
			   for (String key : keys) { 
			      String value = hmModelosSupport.get(key);
			      // do something
			      list.add(value);
			   }
			   return list;
	        } catch (Exception ex){
	        	/// nulo si hay error
	            return null;
	        }
		   
	   }
	   
	   public String getClienteSoporte(int id){
		   return hmClientesSoporte.get(id);
	   }
	   
	   public String getModeloSoporte(int id){
		   return hmModelosSupport.get(id);
	   }
	   
	   public List<String> getClientes() throws JSONException
	   {
		   clientsShared = new ClientsSharedPreference();
		   clientsShared.save(context, null);
		   hmClientes.clear();

		   String json_aux = clientsShared.getValue(context);
		   
		   if(json_aux == null || json_aux == ""){
			   String json = JSONReader.loadJSONClientesFromAsset(context);
			   clientsShared.save(context, json);
			   collection_clientes = json;
			   
			    JSONObject json_clients = new JSONObject(json);
				JSONArray clientes = (JSONArray) json_clients.get("Clientes");
				for(int i=0;i<clientes.length();i++){
					JSONObject parent = (JSONObject) clientes.get(i);
					 hmClientes.put(parent.getString("id"), parent.getString("nombre"));
				}
				
				
		   }else{
			   JSONObject json_clients = new JSONObject(json_aux);
				JSONArray clientes = (JSONArray) json_clients.get("Clientes");
				for(int i=0;i<clientes.length();i++){
					JSONObject parent = (JSONObject) clientes.get(i);
					 hmClientes.put(parent.getString("id"), parent.getString("nombre"));
				}
		   }
		   
		   try {
			   List<String> list = new ArrayList<String>();
			   SortedSet<String> keys = new TreeSet<String>(hmClientes.keySet());
			   for (String key : keys) { 
			      String value = hmClientes.get(key);
			      // do something
			      list.add(value);
			   }
			   return list;
	        } catch (Exception ex){
	        	/// nulo si hay error
	            return null;
	        }
		   
	   }
	   
	   public String getCliente(int id){
		   return hmClientes.get(id);
	   }
	   
	   public String getInMemmoryAuditories()
	   {
		   auditoriesInMemmorySharedPreference = new AuditoriesInMemmorySharedPreference();
		   String json_aux = auditoriesInMemmorySharedPreference.getValue(context);
		   return json_aux;
	   }

	public String getInMemmoryVentas()
	{
		ventasSharedPreference = new VentasSharedPreference();
		String json_aux = ventasSharedPreference.getValue(context);
		return json_aux;
	}

	public String getInMemmoryUsers()
	{
		usersInMemmorySharedPreference = new UsersInMemmorySharedPreference();
		String json_aux = usersInMemmorySharedPreference.getValue(context);
		return json_aux;
	}
	   
	   public String getInMemmoryReportSupport()
	   {
		   reportsInMemmorySharedPreference = new ReportsInMemmorySharedPreference();
		   String json_aux = reportsInMemmorySharedPreference.getValue(context);
		   return json_aux;
	   }
	   
	   public String getBatchADS()
	   {
		   batchADSSharedPreference = new BatchADSSharedPreference();
		   String json_aux = batchADSSharedPreference.getValue(context);
		   
		   return json_aux;
	   }
	   
	   public String getBatchSDE()
	   {
		   batchSDESharedPreference = new BatchSDESharedPreference();
		   String json_aux = batchSDESharedPreference.getValue(context);
		   
		   return json_aux;
	   }
	   
	   public String getBatchLHD()
	   {
		   batchLHDSharedPreference = new BatchLHDSharedPreference();
		   String json_aux = batchLHDSharedPreference.getValue(context);
		   
		   return json_aux;
	   }
	   
	   public String getReportSupport()
	   {
		   reportSupportSharedPreference = new ReportSupportSharedPreference();
		   String json_aux = reportSupportSharedPreference.getValue(context);
		   
		   return json_aux;
	   }
	   
	   public String getBatchTME()
	   {
		   batchTMESharedPreference = new BatchTMESharedPreference();
		   String json_aux = batchTMESharedPreference.getValue(context);
		   
		   return json_aux;
	   }
	   
	   public String getDaily()
	   {
		   dailyShared = new DailyOrderSharedPreference(context, (Activity) context);
		   String json_aux = dailyShared.getValue(context);
		   
		   return json_aux;
	   }
	   
	   public String getNewOrders()
	   {
		   sharedPreference = new SharedPreference();
		   String json_aux = sharedPreference.getValue(context);
		   
		   return json_aux;
	   }
	   
	   public String getUpdateOrders()
	   {
		   updateSharePreference = new UpdateSharedPreference();
		   String json_aux = updateSharePreference.getValue(context);
		   
		   return json_aux;
	   }
	   
	   public String getEmergencies()
	   {
		   emergencySharedPreference = new EmergencySharedPreference();
		   String json_aux = emergencySharedPreference.getValue(context);
		   
		   return json_aux;
	   }

	   public String getPictures()
	   {
		   picturesSharedPreference = new PicturesSharedPreference();
		   String json_aux = picturesSharedPreference.getValue(context);
		   
		   return json_aux;  
	   }
	   
	   public String getVideos()
	   {
		   videosSharedPreference = new VideosSharedPreference();
		   String json_aux = videosSharedPreference.getValue(context);
		   
		   return json_aux;  
	   }

	public String getVideoComments()
	{
		videoCommentsSharedPreference = new VideoCommentsSharedPreference();
		String json_aux = videoCommentsSharedPreference.getValue(context);

		return json_aux;
	}
	   
	   public String getADSAuditorias()
	   {
		   adsAuditoriaSharedPreference = new ADSAuditoriaSharedPreference();
		   String json_aux = adsAuditoriaSharedPreference.getValue(context);
		   
		   return json_aux;  
	   }
	   
	   public String getLHDAuditorias()
	   {
		   lhdAuditoriaSharedPreference = new LHDAuditoriaSharedPreference();
		   String json_aux = lhdAuditoriaSharedPreference.getValue(context);
		   
		   return json_aux;  
	   }

	   public String getSDEAuditorias()
	   {
		   sdeAuditoriaSharedPreference = new SDEAuditoriaSharedPreference();
		   String json_aux = sdeAuditoriaSharedPreference.getValue(context);
		   
		   return json_aux;  
	   }
	   
	   public String getTMEAuditorias()
	   {
		   tmeAuditoriaSharedPreference = new TMEAuditoriaSharedPreference();
		   String json_aux = tmeAuditoriaSharedPreference.getValue(context);
		   
		   return json_aux;  
	   }
	   
	   public String getPicturesQas()
	   {
		   picturesQaSharedPreference = new PicturesQaSharedPreference();
		   String json_aux = picturesQaSharedPreference.getValue(context);
		   
		   return json_aux;   
	   }
	   
	   public String getPicturesComments()
	   {
		   picturesCommentsSharedPreference = new PicturesCommentsSharedPreference();
		   String json_aux = picturesCommentsSharedPreference.getValue(context);
		   
		   return json_aux;   
	   }
	   
	   public String getPicturesCommentsQas()
	   {
		   picturesQaCommentsSharedPreference = new PicturesQaCommentsSharedPreference();
		   String json_aux = picturesQaCommentsSharedPreference.getValue(context);
		   
		   return json_aux;   
	   }

	   public String getSignatureClient(){
		   
		   signatureClientSharedPreference = new SignatureClientSharedPreference();
		   String json_aux = signatureClientSharedPreference.getValue(context);
		   
		   return json_aux;   
	   }

	   public String getSignatureReception()
	   {
		   signatureReceptionSharedPreference = new SignatureReceptionSharedPreference();
		   String json_aux = signatureReceptionSharedPreference.getValue(context);
		   
		   return json_aux;   
	   }
	  
	   public void setADSAuditoria(String json)
	   {
		   adsAuditoriaSharedPreference = new ADSAuditoriaSharedPreference();
		   adsAuditoriaSharedPreference.save(context, json);
		   collection_ads = json;
	   }
	   
	   public void setSDEAuditoria(String json)
	   {
		   sdeAuditoriaSharedPreference = new SDEAuditoriaSharedPreference();
		   sdeAuditoriaSharedPreference.save(context, json);
		   collection_sde = json;
	   }
	   
	   public void setReportSupport(String json)
	   {
		   reportSupportSharedPreference = new ReportSupportSharedPreference();
		   reportSupportSharedPreference.save(context, json);
		   collection_support = json;
	   }
	   
	   public void setLHDAuditoria(String json)
	   {
		   lhdAuditoriaSharedPreference = new LHDAuditoriaSharedPreference();
		   lhdAuditoriaSharedPreference.save(context, json);
		   collection_lhd = json;
	   }
	   
	   public void setTMEAuditoria(String json)
	   {
		   tmeAuditoriaSharedPreference = new TMEAuditoriaSharedPreference();
		   tmeAuditoriaSharedPreference.save(context, json);
		   collection_tme = json;
	   }
	   
	   public void setEmeregency(String json)
	   {
		   emergencySharedPreference = new EmergencySharedPreference();
		   emergencySharedPreference.save(context, json);
		   collection_emergency = json;
	   }
	   
	   public void setBatchADSAuditoria(String json)
	   {
		   batchADSSharedPreference = new BatchADSSharedPreference();
		   
		   try{
			   String json_aux = batchADSSharedPreference.getValue(context);
			   
			   if(!json_aux.equals("") && json_aux.contains("{") && json_aux!=null ){
				   json_aux += "#NXT" + json;
				   batchADSSharedPreference.save(context, json_aux);
				   
				   collection_batch_ads = json_aux;
			   }else{
				   batchADSSharedPreference.save(context, json);
				   collection_batch_ads = json;
			   } 
		   }catch(Exception e){
			   batchADSSharedPreference.save(context, json);
			   collection_batch_ads = json;
		   }
	   }
	   
	   public void setInMemmoryReportSupport(String json)
	   {
		   reportsInMemmorySharedPreference = new ReportsInMemmorySharedPreference();
		   reportsInMemmorySharedPreference.save(context, json);
		   collection_support_memmory = json;
		   
	   }
	   
	   public void setInMemmoryAuditories(String json)
	   {
		   auditoriesInMemmorySharedPreference = new AuditoriesInMemmorySharedPreference();
		   auditoriesInMemmorySharedPreference.save(context, json);
		   collection_audit_memmory = json;
		   
	   }

	public void setInMemmoryUsers(String json)
	{
		usersInMemmorySharedPreference = new UsersInMemmorySharedPreference();
		usersInMemmorySharedPreference.save(context, json);
		collection_users_memmory = json;

	}
	public void setInMemmoryVentas(String json)
	{
		ventasSharedPreference = new VentasSharedPreference();
		ventasSharedPreference.save(context, json);
		collection_ventas = json;

	}
	   
	   public void setBatchSDEAuditoria(String json)
	   {
		   batchSDESharedPreference = new BatchSDESharedPreference();
		   
		   try{
			   String json_aux = batchSDESharedPreference.getValue(context);
			   
			   if(!json_aux.equals("") && json_aux.contains("{") && json_aux!=null ){
				   json_aux += "#NXT" + json;
				   batchSDESharedPreference.save(context, json_aux);
				   
				   collection_batch_sde = json_aux;
			   }else{
				   batchSDESharedPreference.save(context, json);
				   collection_batch_sde = json;
			   } 
		   }catch(Exception e){
			   batchSDESharedPreference.save(context, json);
			   collection_batch_sde = json;
		   }
	   }
	   
	   public void setBatchLHDAuditoria(String json)
	   {
		   batchLHDSharedPreference = new BatchLHDSharedPreference();
		   
		   try{
			   String json_aux = batchLHDSharedPreference.getValue(context);
			   
			   if(!json_aux.equals("") && json_aux.contains("{") && json_aux!=null ){
				   json_aux += "#NXT" + json;
				   batchLHDSharedPreference.save(context, json_aux);
				   
				   collection_batch_lhd = json_aux;
			   }else{
				   batchLHDSharedPreference.save(context, json);
				   collection_batch_lhd = json;
			   } 
		   }catch(Exception e){
			   batchLHDSharedPreference.save(context, json);
			   collection_batch_lhd = json;
		   }
	   }
	   
	   public void setBatchTMEAuditoria(String json)
	   {
		   batchTMESharedPreference = new BatchTMESharedPreference();
		   
		   try{
			   String json_aux = batchTMESharedPreference.getValue(context);
			   
			   if(!json_aux.equals("") && json_aux.contains("{") && json_aux!=null ){
				   json_aux += "#NXT" + json;
				   batchTMESharedPreference.save(context, json_aux);
				   
				   collection_batch_tme = json_aux;
			   }else{
				   batchTMESharedPreference.save(context, json);
				   collection_batch_tme = json;
			   } 
		   }catch(Exception e){
			   batchTMESharedPreference.save(context, json);
			   collection_batch_tme = json;
		   }
	   }
	   
	   public void setNewOrders(String json)
	   {
		   sharedPreference = new SharedPreference();
		   
		   try{
			   String json_aux = sharedPreference.getValue(context);
			   
			   if(!json_aux.equals("") && json_aux.contains("{") && json_aux!=null ){
				   sharedPreference.save(context, json_aux);
				   collection_orders_new = "#NXT" +json;
			   }else{
				   sharedPreference.save(context, json);
				   collection_orders_new = json;
			   } 
		   }catch(Exception e){
			   sharedPreference.save(context, json);
			   collection_orders_new = json;
		   }
	   }
	   
	   public void setUpdateOrders(String json)
	   {
		   updateSharedPreference = new UpdateSharedPreference();
		   
		   try{
			   String json_aux = updateSharedPreference.getValue(context);
			   
			   if(!json_aux.equals("") && json_aux.contains("{") && json_aux!=null ){
				   //json_aux = json_aux.contains("{")? json_aux + "#NXT" + json : json;
				   updateSharedPreference.save(context, json_aux);
				   
				   collection_orders_update += "#NXT" +json;
			   }else{
				   updateSharedPreference.save(context, json);
				   collection_orders_update = json;
			   }
			   
		   }catch(Exception e){
			   updateSharedPreference.save(context, json);
			   collection_orders_update = json;
		   }
	   }

	   public void setPictures(String json){
		   picturesSharedPreference = new PicturesSharedPreference();
		   
		   try{
			   String json_aux = picturesSharedPreference.getValue(context);
			   
			   if(json_aux.contains(".jpg")){
				   json_aux +=  "," + json;
				   picturesSharedPreference.save(context, json_aux);
				   pictures += "," +json;
			   }else{
				   picturesSharedPreference.save(context, json);
				   pictures = json;
			   }
			   
		   }catch(Exception e){
			   picturesSharedPreference.save(context, json);
			   pictures = json;
		   }
	   }
	   
	   public void setVideos(String json){
		   videosSharedPreference = new VideosSharedPreference();
		   
		   try{
			   String json_aux = videosSharedPreference.getValue(context);
			   
			   if(json_aux.contains(".mp4")){
				   json_aux +=  "," + json;
				   videosSharedPreference.save(context, json_aux);
				   collection_videos += "," +json;
			   }else{
				   videosSharedPreference.save(context, json);
				   collection_videos = json;
			   }
			   
		   }catch(Exception e){
			   videosSharedPreference.save(context, json);
			   collection_videos = json;
		   }
	   }

	   public void setPicturesQas(String json)
	   {
		   picturesQaSharedPreference = new PicturesQaSharedPreference();
		   
		   try{
			   String json_aux = picturesQaSharedPreference.getValue(context);
			   
			   if(json_aux.contains(".jpg") && !json_aux.equals("")){
				   json_aux +=  "," + json;
				   picturesQaSharedPreference.save(context, json_aux);
				   pictures_qa += "," + json;
			   }else{
				   picturesQaSharedPreference.save(context, json);
				   pictures_qa = json;
			   }
			   
		   }catch(Exception ex){
			   picturesQaSharedPreference.save(context, json);
			   pictures_qa = json;
		   }
	   }

	public void resetPictures(String var1) {
		this.picturesSharedPreference = new PicturesSharedPreference();
		this.picturesSharedPreference.save(context, var1);
	}

	public void resetPicturesComments(String var1) {
		this.picturesCommentsSharedPreference = new PicturesCommentsSharedPreference();
		this.picturesCommentsSharedPreference.save(context, var1);
	}

	public void resetVideoComments(String var1) {
		this.videoCommentsSharedPreference = new VideoCommentsSharedPreference();
		this.videoCommentsSharedPreference.save(context, var1);
	}

	public void resetVideos(String var1) {
		this.videosSharedPreference = new VideosSharedPreference();
		this.videosSharedPreference.save(context, var1);
	}

	   public void setPicturesQasComments(String json)
	   {
		   picturesQaCommentsSharedPreference = new PicturesQaCommentsSharedPreference();
		   
		   try{
			   String json_aux = picturesQaCommentsSharedPreference.getValue(context);
			   
			   if(!json_aux.equals("")){
				   json_aux +=  "," + json;
				   picturesQaCommentsSharedPreference.save(context, json_aux);
				   pictures_comments_qa += "," + json;
				   
			   }else{
				   picturesQaCommentsSharedPreference.save(context, json);
				   pictures_comments_qa = json;
			   }
			   
		   }catch(Exception e){
			   picturesQaCommentsSharedPreference.save(context, json);
			   pictures_comments_qa= json;
		   }
	   }
	   
	   public void setPicturesComments(String json)
	   {
		   picturesCommentsSharedPreference = new PicturesCommentsSharedPreference();
		   
		   try{
			   String json_aux = picturesCommentsSharedPreference.getValue(context);
			   
			   if(!json_aux.equals("")){
				   json_aux +=  "," + json;
				   picturesCommentsSharedPreference.save(context, json_aux);
				   pictures_comments = "," +json;
			   }else{
				   picturesCommentsSharedPreference.save(context, json);
				   pictures_comments = json;
			   }
			   
		   }catch(Exception e){
			   picturesCommentsSharedPreference.save(context, json);
			   pictures_comments= json;
		   }
	   }

	public void setVideoComments(String json)
	{
		videoCommentsSharedPreference = new VideoCommentsSharedPreference();

		try{
			String json_aux = videoCommentsSharedPreference.getValue(context);

			if(!json_aux.equals("")){
				json_aux +=  "," + json;
				videoCommentsSharedPreference.save(context, json_aux);
				video_comments = "," +json;
			}else{
				videoCommentsSharedPreference.save(context, json);
				video_comments = json;
			}

		}catch(Exception e){
			videoCommentsSharedPreference.save(context, json);
			video_comments= json;
		}
	}

	   public  void setSignatureClient(String json){
		   signatureClientSharedPreference = new SignatureClientSharedPreference();
		   
		   try{
			   String json_aux = signatureClientSharedPreference.getValue(context);
			   
			   if(!json_aux.equals("")){
				   json_aux = json_aux.contains(".jpg")?json_aux + "," + json :json_aux;
				   signatureClientSharedPreference.save(context, json);
			   }else{
				   signatureClientSharedPreference.save(context, json);
			   }
			   signature_client = "," +json;
		   }catch(Exception e){
			   signatureClientSharedPreference.save(context, json);
			   signature_client = json;
		   }
	   }

	   public void setSignatureReception(String json)
	   {
		   signatureReceptionSharedPreference = new SignatureReceptionSharedPreference();
		   
		   try{
			   String json_aux = signatureReceptionSharedPreference.getValue(context);
			   
			   if(!json_aux.equals("")){
				   json_aux = json_aux.contains(".jpg")?json_aux + "," + json :json_aux;
				   signatureReceptionSharedPreference.save(context, json_aux);
			   }else{
				   signatureReceptionSharedPreference.save(context, json);
			   }
			   signature_reception = "," +json;
		   }catch(Exception e){
			   signatureReceptionSharedPreference.save(context, json);
			   signature_reception = json;
		   }
	   }
	public static String getPrivateInternalID() {
		return privateInternalID;
	}
	public static void setPrivateInternalID(String privateInternalID) {
		CacheCollectionSingleton.privateInternalID = privateInternalID;
	}
	   
}
