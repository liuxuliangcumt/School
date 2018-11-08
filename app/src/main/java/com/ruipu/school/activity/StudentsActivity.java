package com.ruipu.school.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.ruipu.school.R;
import com.ruipu.school.adapter.StudentAdapter;
import com.ruipu.school.adapter.StudentsAdapter;
import com.ruipu.school.beans.Student;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * 学生列表 查看状态
 */
@EActivity
public class StudentsActivity extends BaseActivity {

    @Extra
    int from;// 4图书馆 5 宿管处  6 财务处  7 卡务中心
    @ViewById
    ListView lv_student;

    private List<Student> data;
    StudentsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);
        data = new ArrayList<>();
        adapter = new StudentsAdapter(this, data, from);
        lv_student.setAdapter(adapter);
        getData();
    }

    private void getData() {
        BmobQuery<Student> query = new BmobQuery<>();
        query.addWhereExists("number");
        query.findObjects(new FindListener<Student>() {
            @Override
            public void done(List<Student> list, BmobException e) {
                if (e == null) {
                    data.addAll(list);
                    adapter.notifyDataSetChanged();
                } else {

                }
                Log.e("aaa", "TeachAffairsActivity  list  " + list.size() + "  e:" + e);
            }
        });
    }
}
