package com.bit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bit.client.R;
import com.bit.entities.VentaDetalle;

import java.util.List;

/**
 * 
 * @author goycolea
 *
 */
public class VentasDetalleItemListAdapter extends BaseAdapter
{
    private final Context _context;
    private List<VentaDetalle> listData;
    private LayoutInflater layoutInflater;

    public VentasDetalleItemListAdapter(Context context, List<VentaDetalle> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
        this._context = context;
    }

    @Override
    public int getCount() {
        try {
            return listData.size();
        }catch (Exception ex){
            return 0;
        }
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
    	// convertView
        if (convertView == null) {
        	/// convertView
            convertView = layoutInflater.inflate(R.layout.ventas_report_item, null);
            /// view Holder
            holder = new ViewHolder();
            holder.txtLinea = (TextView) convertView.findViewById(R.id.txLinea);
            holder.txtFecha = (TextView) convertView.findViewById(R.id.txFecha);
            holder.txtNumeroSerie = (TextView) convertView.findViewById(R.id.txNumeroSerie);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //String modelo = EventosHashmapCollectionSingleton.getInstance().hmEquipos.get(listData.get(position).getModelo());
        String nombreEvento = listData.get(position).getNombreEvento();
        holder.txtLinea.setText(nombreEvento);
        holder.txtFecha.setText(listData.get(position).getSector());
        holder.txtNumeroSerie.setText(listData.get(position).getAvatar()!= null ? listData.get(position).getAvatar() : "Sin Avatar");

        return convertView;
    }

    static class ViewHolder {
        TextView txtLinea;
        TextView txtFecha;
        TextView txtNumeroSerie;
    }
 
}
