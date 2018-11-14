package com.ruipu.school.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ruipu.school.R;
import com.ruipu.school.beans.Student;
import com.ruipu.school.util.MyToastUtils;

import java.util.List;

/**
 * Created by ruipu on 2018/11/8.
 */

public class InstructorStudentAdapter extends MyBaseAdapter<Student> {

    public InstructorStudentAdapter(Context context, List<Student> data) {
        super(context, data);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_listview_student, null);
            holder = new ViewHolder();
            holder.iv_delete = convertView.findViewById(R.id.iv_delete);
            holder.name = convertView.findViewById(R.id.tv_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();

        }
        holder.name.setText(data.get(position).getName());
        holder.iv_delete.setVisibility(View.GONE);
        return convertView;
    }

    private class ViewHolder {
        TextView name;
        ImageView iv_delete;

    }

    private DeleteStudent deleteStudent;

    public void setDeleteStudent(DeleteStudent deleteStudent) {
        this.deleteStudent = deleteStudent;
    }

    public interface DeleteStudent {
        void delet(int position);
    }
}
