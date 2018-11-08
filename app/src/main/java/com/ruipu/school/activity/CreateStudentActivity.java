package com.ruipu.school.activity;

import android.os.Bundle;
import android.util.Log;

import com.ruipu.school.R;
import com.ruipu.school.beans.Student;
import com.ruipu.school.util.MyToastUtils;
import com.ruipu.school.view.ClearEditText;
import com.ruipu.school.view.PassWordEditText;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

@EActivity
public class CreateStudentActivity extends BaseActivity {
    @ViewById
    ClearEditText et_account, et_password, et_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_student);
    }

    @Click(R.id.btn_create)
    void onViewClick() {
        gocreate();
    }

    private void gocreate() {

        final String name = et_account.getText().toString().trim();
        final String password = et_password.getText().toString().trim();
        final String number = et_number.getText().toString().trim();
        if (name.length() == 0) {
            MyToastUtils.showToast("请输入用户名");
            return;
        }
        if (password.length() == 0) {
            MyToastUtils.showToast("请输入密码");
            return;
        }
        BmobQuery<Student> query = new BmobQuery<>();
        query.addWhereEqualTo("number", number);
        query.findObjects(new FindListener<Student>() {
            @Override
            public void done(List<Student> list, BmobException e) {
                if (e == null) {
                    if (list.size() == 0) {
                        creatStudent(number, name, password);
                    } else {
                        MyToastUtils.showToast("学号已经存在");
                    }
                } else {
                    MyToastUtils.showToast("查询出错");
                }
                Log.e("aaa", "list:  " + list.size() + "   BmobException  " + e);

            }
        });


    }

    private void creatStudent(String number, String name, String password) {
        Student role = new Student(name, password);
        role.setNumber(number);
        role.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    MyToastUtils.showToast("创建成功");
                } else {
                    MyToastUtils.showToast("创建失败");
                }
            }
        });


    }

}
