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
import com.bit.entities.Eventos;

import org.apache.james.mime4j.util.CharsetUtil;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class EventosItemListAdapter extends BaseAdapter {
    private final Context _context;
    private LayoutInflater layoutInflater;
    private List<Eventos> listData;

    static class ViewHolder {
        TextView txtFecha;
        /*TextView txtLinea;*/
        TextView txtNumeroSerie;
        ImageView imgEvento;
        ViewHolder() {
        }
    }

    public EventosItemListAdapter(Context context, List<Eventos> listData) {
        this.listData = listData;
        this.layoutInflater = LayoutInflater.from(context);
        this._context = context;
    }

    public int getCount() {
        try {
            return this.listData.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public Object getItem(int position) {
        return this.listData.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = this.layoutInflater.inflate(R.layout.audit_report_item, null);
            holder = new ViewHolder();
            /*holder.txtLinea = (TextView) convertView.findViewById(R.id.txLinea);*/
            holder.txtFecha = (TextView) convertView.findViewById(R.id.txFecha);
            holder.txtNumeroSerie = (TextView) convertView.findViewById(R.id.txNumeroSerie);
            holder.imgEvento = (ImageView) convertView.findViewById(R.id.imgEvento);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        /*if(((Eventos) this.listData.get(position)).getPrecio1() != null) {
            holder.txtLinea.setText("$" + ((Eventos) this.listData.get(position)).getPrecio1());
        }else{
            holder.txtLinea.setText("-");
        }*/
        holder.txtFecha.setText(((Eventos) this.listData.get(position)).getNombre());
        holder.txtNumeroSerie.setText(((Eventos) this.listData.get(position)).getFechaInicio() != null ? ((Eventos) this.listData.get(position)).getFechaInicio() : "");

        int id_evento = ((Eventos) this.listData.get(position)).getId();
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
}
