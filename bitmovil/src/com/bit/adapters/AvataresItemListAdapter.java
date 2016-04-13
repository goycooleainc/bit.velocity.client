package com.bit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.bit.client.R;
import com.bit.entities.Avatar;

import java.util.List;

public class AvataresItemListAdapter extends BaseAdapter {
    private final Context _context;
    private LayoutInflater layoutInflater;
    private List<Avatar> listData;

    static class ViewHolder {
        TextView txtFecha;
        TextView txtLinea;
        TextView txtNumeroSerie;

        ViewHolder() {
        }
    }

    public AvataresItemListAdapter(Context context, List<Avatar> listData) {
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
            holder.txtLinea = (TextView) convertView.findViewById(R.id.txLinea);
            holder.txtFecha = (TextView) convertView.findViewById(R.id.txFecha);
            holder.txtNumeroSerie = (TextView) convertView.findViewById(R.id.txNumeroSerie);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if( ((Avatar)this.listData.get(position)).getCodigo().contains("[") == false) {
            holder.txtLinea.setText(((Avatar) this.listData.get(position)).getEstado() == 1 ? "ACTIVO" : "INACTIVO");
            holder.txtFecha.setText(((Avatar) this.listData.get(position)).getDescripcion());
            holder.txtNumeroSerie.setText(((Avatar) this.listData.get(position)).getCodigo() != null ? ((Avatar) this.listData.get(position)).getCodigo() : "[NO CODE]");
            return convertView;
        }else{
            return null;
        }
    }
}
