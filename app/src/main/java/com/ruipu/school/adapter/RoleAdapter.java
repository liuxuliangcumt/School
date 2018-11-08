package com.ruipu.school.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ruipu.school.R;
import com.ruipu.school.beans.UserRole;

import java.util.List;

/**
 * Created by ruipu on 2018/11/7.
 */

public class RoleAdapter extends MyBaseAdapter<UserRole> {

    public RoleAdapter(Context context, List<UserRole> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_chose_role, null);
            holder = new ViewHolder();
            holder.name = convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();

        }
        holder.name.setText(data.get(position).getName());

        return convertView;
    }

    private class ViewHolder {
        TextView name;
    }
}
