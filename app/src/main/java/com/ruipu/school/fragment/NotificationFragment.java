package com.ruipu.school.fragment;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ruipu.school.R;
import com.ruipu.school.activity.NotificationDetailActivity_;
import com.ruipu.school.adapter.NotificationAdapter;
import com.ruipu.school.beans.NotificationMessage;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2018/11/10.
 */
@EFragment(R.layout.fragment_notifaction)
public class NotificationFragment extends Fragment {
    @ViewById
    ListView lv_notice;
    NotificationAdapter adapter;
    private List<NotificationMessage> data;

    @AfterViews
    void oninitViews() {
        data = new ArrayList<>();
        adapter = new NotificationAdapter(getContext(), data);
        lv_notice.setAdapter(adapter);
        lv_notice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NotificationDetailActivity_.intent(getContext()).notificationMessage(data.get(i)).start();

            }
        });

    }

    private void getData(int grade) {
        BmobQuery<NotificationMessage> query = new BmobQuery<>();
        query.addWhereEqualTo("grade", grade);
        query.findObjects(new FindListener<NotificationMessage>() {
            @Override
            public void done(List<NotificationMessage> list, BmobException e) {
                Log.e("aaa", "NotificationMessage  :" + list.size() + "  e:" + e);
                data.clear();
                data.addAll(list);
                adapter.notifyDataSetChanged();
            }
        });

    }

    public void setGrade(int grade) {
        getData(grade);
    }
}
