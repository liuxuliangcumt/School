package com.ruipu.school.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.ruipu.school.R;
import com.ruipu.school.beans.NotificationMessage;
import com.ruipu.school.beans.UserRole;
import com.ruipu.school.util.MyToastUtils;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

//fu辅导员
@EActivity
public class InstructorActivity extends BaseActivity {

    @ViewById
    EditText et_title, et_content;
    @Extra
    UserRole role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor);
        setTitleName("发布通知");
    }

    @Click(R.id.btn_ok)
    void onViewClick() {
        addNews();
    }

    private void addNews() {
        String title = et_title.getText().toString().trim();
        String content = et_content.getText().toString().trim();
        if (title.length() == 0) {
            MyToastUtils.showToast("请输入标题");
            return;
        }
        if (content.length() == 0) {
            MyToastUtils.showToast("请输入内容");
            return;
        }
        NotificationMessage message = new NotificationMessage(title, content);
        message.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                Log.e("aaa", "sting : " + s + "   " + e);
                if (e == null) {
                    MyToastUtils.showToast("添加成功");
                    et_content.setText("");
                    et_title.setText("");
                } else {
                    MyToastUtils.showToast("添加失败");
                }
            }
        });


    }
}
