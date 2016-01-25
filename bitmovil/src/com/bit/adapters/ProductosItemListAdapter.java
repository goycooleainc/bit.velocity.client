package com.bit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.bit.entities.Productos;
import com.bit.client.R;
import java.util.List;

/**
 * 
 * @author goycolea
 *
 */
public class ProductosItemListAdapter extends BaseAdapter
{
    private final Context _context;
    private List<Productos> listData;
    private LayoutInflater layoutInflater;
    
    public ProductosItemListAdapter(Context context, List<Productos> listData) {
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
    	//// convertView
        if (convertView == null) {
        	/// convertView
            convertView = layoutInflater.inflate(R.layout.audit_report_item, null);
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
        String cliente = listData.get(position).getNombre();
        holder.txtLinea.setText(cliente);
        holder.txtFecha.setText(listData.get(position).getPrecio());
        holder.txtNumeroSerie.setText(listData.get(position).getCodigo()!= null ? listData.get(position).getCodigo() : "[NO CODE]");

        
        return convertView;
    }

    static class ViewHolder {

        
        TextView txtLinea;

        TextView txtFecha;


        TextView txtNumeroSerie;
    }
 
}
