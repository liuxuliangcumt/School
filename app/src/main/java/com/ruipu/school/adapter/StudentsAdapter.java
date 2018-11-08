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
        // 4图书馆 5 宿管处  6 财务处  7 卡务中心
        Student student = data.get(position);
        switch (from) {
            case 5:
                if (student.getDormState() == 0 || student.getDormState() == null) {
                    holder.tv_state.setText("未处理");
                } else if (student.getDormState() == 1) {
                    holder.tv_state.setText("钥匙已归还，宿舍打扫干净");
                } else {
                    holder.tv_state.setText("钥匙未归还或宿舍未打扫干净");
                }

                break;
            case 6:
                if (student.getFinanceState() == 0 || student.getFinanceState() == null) {
                    holder.tv_state.setText("未处理");
                } else if (student.getFinanceState() == 1) {
                    holder.tv_state.setText("无欠款");
                } else {
                    holder.tv_state.setText("待缴清");
                }
                break;
            case 7:
                if (student.getCardState() == 0 || student.getCardState() == null) {
                    holder.tv_state.setText("未处理");
                } else if (student.getCardState() == 1) {
                    holder.tv_state.setText("卡里无余额");
                } else {
                    holder.tv_state.setText("卡里有余额");
                }
                break;
            case 4:
                if (student.getLibraryState() == 0 || student.getLibraryState() == null) {
                    holder.tv_state.setText("未处理");
                } else if (student.getLibraryState() == 1) {
                    holder.tv_state.setText("无待还书");
                } else {
                    holder.tv_state.setText("有待还书");
                }
                break;
        }
        return convertView;
    }

    private class ViewHolder {
        TextView tv_name, tv_number, tv_state;
    }
}
