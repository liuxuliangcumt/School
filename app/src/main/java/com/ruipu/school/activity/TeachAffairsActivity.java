package com.ruipu.school.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.ruipu.school.R;
import com.ruipu.school.adapter.StudentAdapter;
import com.ruipu.school.beans.Student;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

@EActivity
public class TeachAffairsActivity extends BaseActivity {


    @ViewById
    ListView lv_student;

    private List<Student> data;
    StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teach_affairs);
        setTitleName("毕业生信息");
        setRightIcon(R.drawable.add_kins);
        data = new ArrayList<>();
        adapter = new StudentAdapter(this, data);
        lv_student.setAdapter(adapter);
        adapter.setDeleteStudent(new StudentAdapter.DeleteStudent() {
            @Override
            public void delet(final int position) {
                Student student = new Student();
                student.setObjectId(data.get(position).getObjectId());
                Log.e("aaa", "objectId:  " + student.getObjectId());
                student.delete(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            Log.e("bmob", "成功");
                            data.remove(position);
                            adapter.notifyDataSetChanged();
                        } else {
                            Log.e("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                        }
                    }
                });
            }
        });
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

    @Click(R.id.iv_right)
    void onViewClick() {
        CreateStudentActivity_.intent(this).start();


    }
}
