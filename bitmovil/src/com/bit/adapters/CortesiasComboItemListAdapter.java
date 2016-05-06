package com.bit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bit.client.R;
import com.bit.entities.Cortesia;

import java.util.List;

public class CortesiasComboItemListAdapter extends BaseAdapter {
    private final Context _context;
    private LayoutInflater layoutInflater;
    private List<Cortesia> listData;

    static class ViewHolder {
        TextView txtFecha;
        /*TextView txtLinea;*/
        TextView txtNumeroSerie;

        ViewHolder() {
        }
    }

    public CortesiasComboItemListAdapter(Context context, List<Cortesia> listData) {
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
            convertView = this.layoutInflater.inflate(R.layout.cortesia_combo_item, null);
            holder = new ViewHolder();
            /*holder.txtLinea = (TextView) convertView.findViewById(R.id.txLinea);*/
            holder.txtFecha = (TextView) convertView.findViewById(R.id.txFecha);
            holder.txtNumeroSerie = (TextView) convertView.findViewById(R.id.txNumeroSerie);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        /*holder.txtLinea.setText("Combo - Evento:");*/
        holder.txtFecha.setText(((Cortesia) this.listData.get(position)).getNombreCombo() != null ? ((Cortesia) this.listData.get(position)).getNombreCombo() : "");
        holder.txtNumeroSerie.setText(((Cortesia) this.listData.get(position)).getNombreEvento() != null ? ((Cortesia) this.listData.get(position)).getNombreEvento() : "");
        return convertView;
    }
}
