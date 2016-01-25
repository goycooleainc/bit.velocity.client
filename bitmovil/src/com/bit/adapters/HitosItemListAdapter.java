package com.bit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import com.bit.client.R;
import com.bit.entities.HitosAuditorias;
import com.bit.singletons.ProductHashmapCollectionSingleton;

import java.util.List;

/**
 * 
 * @author goycolea
 *
 */
public class HitosItemListAdapter extends BaseAdapter
{
    private List<HitosAuditorias> listData;
    private LayoutInflater layoutInflater;
    
    public HitosItemListAdapter(Context context, List<HitosAuditorias> listData) {
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
        
        String calificacion= listData.get(position).getCalificacion();

        switch(calificacion){
            case "0":
               calificacion = "Buena";
                break;
            case "1":
                calificacion = "Regular";
                break;
            case "2":
                calificacion = "Mala";
                break;
            case "3":
                calificacion = "No Aplica";
                break;
            default:
                calificacion = listData.get(position).getCalificacion();
        }



        holder.txtNumSerie.setText(listData.get(position).getSeccion()!=null? listData.get(position).getSeccion() : "");
        holder.txtFolio.setText(listData.get(position).getItem() !=null ? listData.get(position).getItem() : "");
        holder.txtOrdenes.setText(calificacion);
        holder.txtEstado.setText(listData.get(position).getAux_one() != null ? listData.get(position).getAux_one() : "");

        holder.btn_repuestos.setVisibility(View.INVISIBLE);
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
