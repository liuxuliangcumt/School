package com.ruipu.school.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;

import com.ruipu.school.R;
import com.ruipu.school.beans.Student;
import com.ruipu.school.util.MyToastUtils;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

@EActivity
public class DeelActivity extends BaseActivity {

    @Extra
    int from;

    @Extra
    Student student;
    @ViewById
    RadioButton rb_ok;

    @ViewById
    EditText et_content;

    // 4图书馆 5 宿管处  6 财务处  7 卡务中心
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deel);
        setTitleName("处理事务");
        String content = "";
        switch (from) {
            case 4:
                content = student.getLibrary();
                break;
            case 5:
                content = student.getDorm();
                break;
            case 6:
                content = student.getFinance();
                break;
            case 7:
                content = student.getCard();
                break;

        }
        if (content != null)
            et_content.setText(content);
        rb_ok.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    et_content.setVisibility(View.GONE);
                } else {
                    et_content.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    @Click(R.id.btn_ok)
    void onViewClick() {
        if (rb_ok.isChecked()) {
            switch (from) {
                case 4:
                    student.setLibraryState(1);
                    student.setLibrary("");

                    break;
                case 5:
                    student.setDormState(1);
                    student.setDorm("");

                    break;
                case 6:
                    student.setFinanceState(1);
                    student.setFinance("");

                    break;
                case 7:
                    student.setCardState(1);
                    student.setCard("");

                    break;
            }
        } else {
            if (et_content.getText().toString().length() == 0) {
                MyToastUtils.showToast("请输出驳回原因");
                return;
            }
            switch (from) {
                case 4:
                    student.setLibraryState(2);
                    student.setLibrary(et_content.getText().toString());
                    break;
                case 5:
                    student.setDormState(2);
                    student.setDorm(et_content.getText().toString());
                    break;
                case 6:
                    student.setFinanceState(2);
                    student.setFinance(et_content.getText().toString());
                    break;
                case 7:
                    student.setCardState(2);
                    student.setCard(et_content.getText().toString());
                    break;
            }
        }
        student.update(student.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                Log.e("aaa", "更新数据： " + e);
                if (e == null) {
                    MyToastUtils.showToast("处理成功");
                    finish();
                } else {
                    MyToastUtils.showToast("处理失败");

                }
            }
        });


    }
}
