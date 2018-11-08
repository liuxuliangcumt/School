package com.ruipu.school.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.ruipu.school.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

@EActivity
public class BaseActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }




    public void setTitleName(String name) {
        TextView textView = (TextView) findViewById(R.id.tv_title);
        if (textView != null) {
            textView.setText(name);
        }
    }

    public void setRightName(String name) {
        TextView textView = (TextView) findViewById(R.id.tv_right);
        if (textView != null) {
            findViewById(R.id.iv_right).setVisibility(View.GONE);
            textView.setText(name);
            textView.setVisibility(View.VISIBLE);
        }
    }

    public void setRightIcon(int id) {
        ImageView iv_right = (ImageView) findViewById(R.id.iv_right);
        if (iv_right != null) {
            findViewById(R.id.tv_right).setVisibility(View.GONE);
            iv_right.setImageDrawable(getResources().getDrawable(id));
            iv_right.setVisibility(View.VISIBLE);
        }
    }

    @Click(R.id.iv_back)
    public void onIvBackPressed() {
        onBackPressed();
    }

    public ProgressDialog pDialog;

    public void showMyDialog(String message) {
        if (pDialog == null) {
            pDialog = new ProgressDialog(this);
            pDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    hideDialog();
                }
            });

        }
        if ("".equals(message)) {
            pDialog.setMessage("请稍后");
        } else {
            pDialog.setMessage(message);
        }
        pDialog.show();
      //  handler.sendEmptyMessageDelayed(1, 500);
    }

    @Override
    protected void onDestroy() {
        if (pDialog != null) {
            pDialog.dismiss();
            //  handler.removeMessages(1);
            hideDialog();
        }
        super.onDestroy();
    }

   /* public Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            switch (msg.what) {
                case 1:
                    if (pDialog != null && isAskEnd && pDialog.isShowing()) {
                        pDialog.dismiss();
                        isAskEnd = false;
                        handler.removeMessages(1);
                        Log.e("aaa", "pdialog隐藏");
                    } else {
                        handler.sendEmptyMessageDelayed(1, 500);
                        Log.e("aaa", "pdialog隐藏yanshi延时");
                    }
                    break;
            }
            return false;
        }
    });*/
    public boolean isAskEnd = false;

    public void hideDialog() {
        // isAskEnd = true;
        if (pDialog != null && pDialog.isShowing()) {
            pDialog.dismiss();
            Log.e("aaa", "pdialog隐藏");
        }
    }
}
