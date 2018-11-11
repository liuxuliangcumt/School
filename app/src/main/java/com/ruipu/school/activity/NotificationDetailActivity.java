package com.ruipu.school.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.ruipu.school.R;
import com.ruipu.school.beans.NotificationMessage;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

@EActivity
public class NotificationDetailActivity extends BaseActivity {
    @ViewById
    TextView tv_titles, tv_content;
    @Extra
    NotificationMessage notificationMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_detail);
        tv_content.setText(notificationMessage.getContent());
        tv_titles.setText(notificationMessage.getTitle());
        setTitleName("通知详情");
    }
}
