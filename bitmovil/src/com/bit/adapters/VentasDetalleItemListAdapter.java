package com.bit.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bit.async.tasks.GetImageTask;
import com.bit.client.R;
import com.bit.entities.VentaDetalle;

import java.util.List;
import java.util.concurrent.ExecutionException;

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
            /*holder.txtNumeroSerie = (TextView) convertView.findViewById(R.id.txNumeroSerie);*/
            holder.imgEvento = (ImageView) convertView.findViewById(R.id.imgEventoImg);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //String modelo = EventosHashmapCollectionSingleton.getInstance().hmEquipos.get(listData.get(position).getModelo());
        String nombreEvento = listData.get(position).getNombreEvento();
        holder.txtLinea.setText(nombreEvento);
        holder.txtFecha.setText(listData.get(position).getFecha());
        /*holder.txtNumeroSerie.setText(listData.get(position).getAvatar()!= null ? listData.get(position).getAvatar() : "Sin Avatar");*/

        int id_evento = listData.get(position).getIdEvento();
        String remoteURL = _context.getString(R.string.server);
        GetImageTask it = new GetImageTask();
        it.setUrl(remoteURL + "/dmz/multimedia/" + id_evento + "/type/1/" + id_evento + "-0");
        try {
            holder.imgEvento.setImageDrawable((Drawable) it.execute(new Void[0]).get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return convertView;
    }

    static class ViewHolder {
        TextView txtLinea;
        TextView txtFecha;
        /*TextView txtNumeroSerie;*/
        ImageView imgEvento;
    }
 
}
