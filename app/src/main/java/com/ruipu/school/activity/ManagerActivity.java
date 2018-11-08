package com.ruipu.school.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.ruipu.school.R;
import com.ruipu.school.adapter.RoleAdapter;
import com.ruipu.school.beans.UserRole;
import com.ruipu.school.util.MyToastUtils;
import com.ruipu.school.util.SystemInfoUtils;
import com.ruipu.school.view.ClearEditText;
import com.ruipu.school.view.CustomDialog;
import com.ruipu.school.view.PassWordEditText;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * 管理员
 */
@EActivity
public class ManagerActivity extends BaseActivity {

    @ViewById
    ClearEditText et_account;
    @ViewById
    PassWordEditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        setTitleName("添加角色");
        data = new ArrayList<>();
        //  data.add(new UserRole("管理员", 1));
        data.add(new UserRole("教务处", 2));
        data.add(new UserRole("辅导员", 3));
        data.add(new UserRole("图书馆", 4));
        data.add(new UserRole("宿管处", 5));
        data.add(new UserRole("财务处", 6));
        data.add(new UserRole("卡务中心", 7));
      //  data.add(new UserRole("学生", 8));
    }

    @Click({R.id.btn_login, R.id.btn_create})
    void onViewClick(View view) {

        switch (view.getId()) {
            case R.id.btn_login:
                showChoseRoleDailog();
                break;
            case R.id.btn_create:
                gocreate();
                break;
        }


    }


    private void gocreate() {

        final String name = et_account.getText().toString().trim();
        final String password = et_password.getText().toString().trim();
        if (0 == loginRole) {
            MyToastUtils.showToast("请选择角色");
            return;
        }

        if (name.length() == 0) {
            MyToastUtils.showToast("请输入用户名");
            return;
        }
        if (password.length() == 0) {
            MyToastUtils.showToast("请输入密码");
            return;
        }
        BmobQuery<UserRole> query = new BmobQuery<>();
        query.addWhereEqualTo("name", name);
        query.addWhereEqualTo("passWord", password);
        query.addWhereEqualTo("role", loginRole);
        query.findObjects(new FindListener<UserRole>() {
            @Override
            public void done(List<UserRole> list, BmobException e) {
                if (e == null) {
                    if (list.size() == 0) {
                        creatRole(name, password);
                    } else {
                        MyToastUtils.showToast("账户已经存在");
                    }
                } else {
                    MyToastUtils.showToast("查询出错");
                }
                Log.e("aaa", "list:  " + list.size() + "   BmobException  " + e);

            }
        });


    }

    private void creatRole(String name, String password) {
        UserRole role = new UserRole(name, loginRole);
        role.setPassWord(password);
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


    @ViewById
    TextView tv_role;
    private List<UserRole> data;
    private Integer loginRole = 0;

    private void showChoseRoleDailog() {
        final CustomDialog dialog = new CustomDialog(this, R.style.customDialog, R.layout.dailog_chose_role);
        dialog.show();
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        dialog.getWindow().setGravity(Gravity.CENTER);
        layoutParams.width = (int) (SystemInfoUtils.getWindowsWidth(this));
        //  layoutParams.height = (int) ((SystemInfoUtils.getWindowsHeight(this))*0.6);
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(layoutParams);
        ListView lv_role = dialog.getCustomView().findViewById(R.id.lv_role);
        RoleAdapter adapter = new RoleAdapter(this, data);
        lv_role.setAdapter(adapter);
        lv_role.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                loginRole = data.get(position).getRole();
                tv_role.setText("角色：" + data.get(position).getName());
                dialog.dismiss();

            }
        });
    }
}
