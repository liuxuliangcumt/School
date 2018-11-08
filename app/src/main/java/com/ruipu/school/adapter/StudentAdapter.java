package com.ruipu.school.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ruipu.school.R;
import com.ruipu.school.beans.Student;
import com.ruipu.school.util.MyToastUtils;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by ruipu on 2018/11/8.
 */

public class StudentAdapter extends MyBaseAdapter<Student> {

    public StudentAdapter(Context context, List<Student> data) {
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
        holder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyToastUtils.showToast("删除" + position);
                if (deleteStudent != null) {
                    deleteStudent.delet(position);
                }

            }
        });
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
