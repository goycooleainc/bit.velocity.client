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
import com.bit.entities.Cortesia;
import com.bit.entities.Eventos;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class CortesiasEventoItemListAdapter extends BaseAdapter {
    private final Context _context;
    private LayoutInflater layoutInflater;
    private List<Cortesia> listData;

    static class ViewHolder {
        TextView txtFecha;
        TextView txtNumeroSerie;
        ImageView imgEvento;
        ViewHolder() {
        }
    }

    public CortesiasEventoItemListAdapter(Context context, List<Cortesia> listData) {
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
            convertView = this.layoutInflater.inflate(R.layout.cortesia_evento_item, null);
            holder = new ViewHolder();
            holder.txtFecha = (TextView) convertView.findViewById(R.id.txFecha);
            holder.txtNumeroSerie = (TextView) convertView.findViewById(R.id.txNumeroSerie);
            holder.imgEvento = (ImageView) convertView.findViewById(R.id.imgEventoPpal);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txtFecha.setText(((Cortesia) this.listData.get(position)).getFecha() != null ? ((Cortesia) this.listData.get(position)).getFecha() : "");
        holder.txtNumeroSerie.setText(((Cortesia) this.listData.get(position)).getNombreEvento() != null ? ((Cortesia) this.listData.get(position)).getNombreEvento() : "");

        int id_evento = ((Cortesia) this.listData.get(position)).getIdEvento();
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
