package com.bit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.bit.client.R;
import com.bit.entities.Productos;

import java.util.List;

public class ProductosBitItemListAdapter extends BaseAdapter {
    private final Context _context;
    private LayoutInflater layoutInflater;
    private List<Productos> listData;

    static class ViewHolder {
        TextView txtFecha;
        TextView txtLinea;

        ViewHolder() {
        }
    }

    public ProductosBitItemListAdapter(Context context, List<Productos> listData) {
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
            convertView = this.layoutInflater.inflate(R.layout.recharge_item, null);
            holder = new ViewHolder();
            holder.txtLinea = (TextView) convertView.findViewById(R.id.txLinea);
            holder.txtFecha = (TextView) convertView.findViewById(R.id.txFecha);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.txtLinea.setText(((Productos) this.listData.get(position)).getNombre());
        holder.txtFecha.setText(((Productos) this.listData.get(position)).getPrecio());
        return convertView;
    }
}
