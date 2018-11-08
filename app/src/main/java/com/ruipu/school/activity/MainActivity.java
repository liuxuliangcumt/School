package com.ruipu.school.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
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

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

@EActivity
public class MainActivity extends BaseActivity {
    private List<UserRole> data;

    @ViewById
    ClearEditText et_account;
    @ViewById
    PassWordEditText et_password;
    @ViewById
    TextView tv_role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //第一：默认初始化
        Bmob.initialize(this, "8041ccfdd4cad96e077308f89bbfdf70");
        // 注:自v3.5.2开始，数据sdk内部缝合了统计sdk，开发者无需额外集成，传渠道参数即可，不传默认没开启数据统计功能
        //Bmob.initialize(this, "Your Application ID","bmob");

        //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        //BmobConfig config =new BmobConfig.Builder(this)
        ////设置appkey
        //.setApplicationId("Your Application ID")
        ////请求超时时间（单位为秒）：默认15s
        //.setConnectTimeout(30)
        ////文件分片上传时每片的大小（单位字节），默认512*1024
        //.setUploadBlockSize(1024*1024)
        ////文件的过期时间(单位为秒)：默认1800s
        //.setFileExpiration(2500)
        //.build();
        //Bmob.initialize(config);
        data = new ArrayList<>();
        data.add(new UserRole("管理员", 1));
        data.add(new UserRole("教务处", 2));
        data.add(new UserRole("辅导员", 3));
        data.add(new UserRole("图书馆", 4));
        data.add(new UserRole("宿管处", 5));
        data.add(new UserRole("财务处", 6));
        data.add(new UserRole("卡务中心", 7));
        data.add(new UserRole("学生", 8));

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Click({R.id.btn_login, R.id.btn_choserole})
    void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_choserole:
                showChoseRoleDailog();
                break;
            case R.id.btn_login:
                if (loginRole == 0) {
                    showChoseRoleDailog();
                } else {
                    goLogin();
                }
                break;
        }


    }

    private void goLogin() {


        String name = et_account.getText().toString().trim();
        String password = et_password.getText().toString().trim();
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
        Log.e("aaa", name + "   list");

        query.findObjects(new FindListener<UserRole>() {
            @Override
            public void done(List<UserRole> list, BmobException e) {
                if (e == null) {
                    if (list.size() != 0) {
                        MyToastUtils.showToast("登录成功");
                        loginTo();
                    }
                } else {
                    MyToastUtils.showToast("账户或密码不正确");
                }
            }
        });


    }

    private void loginTo() {
        switch (loginRole) {
            case 1:
                ManagerActivity_.intent(this).start();
                break;
            case 2:
                TeachAffairsActivity_.intent(this).start();
                break;

            case 3:

                break;
            case 4:
                StudentsActivity_.intent(this).from(4).start();

                break;
            case 5:
                StudentsActivity_.intent(this).from(5).start();
                break;
            case 6:
                StudentsActivity_.intent(this).from(6).start();

                break;
            case 7:
                StudentsActivity_.intent(this).from(7).start();

                break;
            case 8:

                break;

        }
    }

    private Integer loginRole = 0;
    @ViewById
    LinearLayout ll_login;

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
                ll_login.setVisibility(View.VISIBLE);
                loginRole = data.get(position).getRole();
                tv_role.setText(data.get(position).getPassWord());
                dialog.dismiss();
                switch (loginRole) {
                    case 2:
                        et_account.setText("jiaowu");

                        break;
                    case 3:
                        et_account.setText("fudaoyuan");

                        break;
                    case 4:
                        et_account.setText("library");

                        break;
                    case 5:
                        et_account.setText("dorm");

                        break;
                    case 6:
                        et_account.setText("finance");
                        break;
                    case 7:
                        et_account.setText("card");
                        break;
                }

            }
        });
    }


}
