package com.ruipu.school.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ruipu.school.R;
import com.ruipu.school.beans.NotificationMessage;

import java.util.List;

public class NotificationAdapter extends MyBaseAdapter<NotificationMessage> {
    public NotificationAdapter(Context context, List<NotificationMessage> data) {
        super(context, data);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = View.inflate(context, R.layout.item_listview_notification, null);
            holder = new ViewHolder();
            holder.title = view.findViewById(R.id.tv_title);
            holder.content = view.findViewById(R.id.tv_content);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.title.setText(data.get(i).getTitle());
        holder.content.setText(data.get(i).getContent());
        return view;
    }

    private class ViewHolder {
        TextView title, content;
    }
}
