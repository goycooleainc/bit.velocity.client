package com.bit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import com.bit.client.R;
import com.bit.entities.Repuesto;
import com.bit.singletons.ProductHashmapCollectionSingleton;

import java.util.List;

/**
 * 
 * @author goycolea
 *
 */
public class RepuestosItemListAdapter extends BaseAdapter
{
    private List<Repuesto> listData;
    private LayoutInflater layoutInflater;
    
    public RepuestosItemListAdapter(Context context, List<Repuesto> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /// ViewHolder
    	ViewHolder holder;
    	//// convertView
        if (convertView == null) {
        	/// convertView
            convertView = layoutInflater.inflate(R.layout.order_section_item, null);
            /// view Holder
            holder = new ViewHolder();
            holder.txtCliente = (TextView) convertView.findViewById(R.id.tvCliente);
            holder.txtEstado = (TextView) convertView.findViewById(R.id.tvEstado);
            holder.txtOrdenes = (TextView) convertView.findViewById(R.id.tvOrdenes);
            holder.txtFolio = (TextView) convertView.findViewById(R.id.tvFolio);
            holder.txtNumSerie = (TextView) convertView.findViewById(R.id.tvNumSerie);
            holder.txtID = (TextView) convertView.findViewById(R.id.tvId);
            
            holder.btn_repuestos = (ImageButton) convertView.findViewById(R.id.btn_repuestos);
            holder.btn_camera = (ImageButton) convertView.findViewById(R.id.btn_camera);
            holder.btn_problema = (ImageButton) convertView.findViewById(R.id.btn_problema);
            holder.btn_obs = (ImageButton) convertView.findViewById(R.id.btn_obs);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ProductHashmapCollectionSingleton.getInstance();
        
        
        
        int pos = Integer.parseInt(listData.get(position).getSeccion());
        int item_pos = Integer.parseInt(listData.get(position).getItem());
        item_pos++;
        pos++;
        String section = ProductHashmapCollectionSingleton.getPositionSecciones(String.valueOf(pos));
        
        String item="";
        switch(pos){
        case 1:
        	item = ProductHashmapCollectionSingleton.getPositionControlRodado(String.valueOf(item_pos));
        	break;
        case 2:
        	item = ProductHashmapCollectionSingleton.getPositionFisuras(String.valueOf(item_pos));
        	break;
        case 3:
        	item = ProductHashmapCollectionSingleton.getPositionMangueras(String.valueOf(item_pos));
        	break;
        case 4:
        	item = ProductHashmapCollectionSingleton.getPositionCompresor(String.valueOf(item_pos));
        	break;
        case 5:
        	item = ProductHashmapCollectionSingleton.getPositionLimpieza(String.valueOf(item_pos));
        	break;
        case 6:
        	item = ProductHashmapCollectionSingleton.getPositionCamion(String.valueOf(item_pos));
        	break;
        case 7:
        	item = ProductHashmapCollectionSingleton.getPositionVarios(String.valueOf(item_pos));
        	break;
        default:
        	item = "NA";
        	break;
        	
        }
        
        holder.txtOrdenes.setText(listData.get(position).getDescripcion() + " $"+listData.get(position).getPrecio_unitario());
        holder.txtFolio.setText(item);
        holder.txtNumSerie.setText(section);
        holder.txtEstado.setText(listData.get(position).getItem());
        
        holder.btn_repuestos.setVisibility(View.VISIBLE);
        holder.btn_camera.setVisibility(View.INVISIBLE);
        holder.btn_problema.setVisibility(View.INVISIBLE);
        holder.btn_obs.setVisibility(View.INVISIBLE);
        
        return convertView;
    }

    static class ViewHolder {

        TextView txtID;

        TextView txtNumSerie;

        TextView txtEstado;

        TextView txtFolio;

        TextView txtOrdenes;

        TextView txtCliente;
        
        ImageButton btn_repuestos;
        
        ImageButton btn_camera;
        
        ImageButton btn_problema;
        
        ImageButton btn_obs;
    }
 
}
