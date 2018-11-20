package com.ruipu.school.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import com.ruipu.school.R;
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
    @ViewById
    EditText et_search;
    @ViewById
    CheckBox cb_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);
        data = new ArrayList<>();
        adapter = new StudentsAdapter(this, data, from);
        lv_student.setAdapter(adapter);
        setTitleName("学生列表");
        lv_student.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DeelActivity_.intent(StudentsActivity.this).student(data.get(position)).from(from).start();
            }
        });
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (et_search.getText().toString().trim().length() != 0) {
                    searchStudent(et_search.getText().toString().trim());
                } else {
                    getData();
                }
            }
        });
    }

    private void searchStudent(String search) {
        BmobQuery<Student> query = new BmobQuery<>();
        if (cb_number.isChecked()) {
            query.addWhereEqualTo("number", search);
        } else {
            query.addWhereEqualTo("name", search);
        }
        query.findObjects(new FindListener<Student>() {
            @Override
            public void done(List<Student> list, BmobException e) {
                if (e == null) {
                    data.clear();
                    data.addAll(list);
                    adapter.notifyDataSetChanged();
                    for (int i = 0; i < data.size(); i++) {

                        Log.e("aaa", data.get(i).toString());
                    }
                } else {

                }
                Log.e("aaa", "TeachAffairsActivity  list  " + list.size() + "  e:" + e);
            }
        });
    }

    private void getData() {
        BmobQuery<Student> query = new BmobQuery<>();
        query.findObjects(new FindListener<Student>() {
            @Override
            public void done(List<Student> list, BmobException e) {
                if (e == null) {
                    data.clear();
                    data.addAll(list);
                    adapter.notifyDataSetChanged();
                    for (int i = 0; i < data.size(); i++) {

                        Log.e("aaa", data.get(i).toString());
                    }
                } else {

                }
                Log.e("aaa", "TeachAffairsActivity  list  " + list.size() + "  e:" + e);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();

    }
}
