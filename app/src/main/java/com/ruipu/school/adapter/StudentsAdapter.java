package com.ruipu.school.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ruipu.school.R;
import com.ruipu.school.beans.Student;

import java.util.List;


public class StudentsAdapter extends MyBaseAdapter<Student> {

    private int from;

    public StudentsAdapter(Context context, List<Student> data, int from) {
        super(context, data);
        this.from = from;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_listview_student_state, null);
            holder = new ViewHolder();
            holder.tv_name = convertView.findViewById(R.id.tv_name);
            holder.tv_number = convertView.findViewById(R.id.tv_number);
            holder.tv_state = convertView.findViewById(R.id.tv_state);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_name.setText(data.get(position).getName());
        holder.tv_number.setText(data.get(position).getNumber());
        switch (from) {
            case 5:

                break;
            case 6:

                break;
            case 7:

                break;
            case 4:

                break;
        }
        return convertView;
    }

    private class ViewHolder {
        TextView tv_name, tv_number, tv_state;
    }
}
