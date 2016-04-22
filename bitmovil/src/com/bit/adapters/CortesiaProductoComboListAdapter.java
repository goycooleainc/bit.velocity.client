package com.bit.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.bit.client.R;

import java.util.List;

public class CortesiaProductoComboListAdapter extends BaseAdapter
{
    private List<String> listData;
    private Context context;
    private LayoutInflater layoutInflater;

    public CortesiaProductoComboListAdapter(Context context, List<String> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return listData!=null ? listData.size() : 0;
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
        ViewHolder holder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.producto_combo_item, null);
            holder = new ViewHolder();

            holder.nombreProducto = (TextView) convertView.findViewById(R.id.nombreProducto);
//            holder.checkbox = (CheckBox) convertView.findViewById(R.id.checkbox);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.idAda = position;
//        holder.checkbox.setId(position);
        holder.nombreProducto.setText(this.listData.get(position));

        return convertView;
    }

    static class ViewHolder {
        int idAda;
        TextView nombreProducto;
//        CheckBox checkbox;
    }

}
