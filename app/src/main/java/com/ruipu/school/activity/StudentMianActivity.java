package com.ruipu.school.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.ruipu.school.R;
import com.ruipu.school.fragment.NotificationFragment;
import com.ruipu.school.fragment.NotificationFragment_;
import com.ruipu.school.fragment.ToDoFragment;
import com.ruipu.school.fragment.ToDoFragment_;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity
public class StudentMianActivity extends BaseActivity {
    private PagerAdapter pagerAdapter;
    @ViewById
    TabLayout tl_suggest;

    @ViewById
    ViewPager vp_petition;
    private List<String> titleData;
    NotificationFragment fragment;
    ToDoFragment fragment2;
    @Extra
    String studentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_mian);
        setTitleName(studentName);
        titleData = new ArrayList<>();
        titleData.add("通知");
        titleData.add("待办事项");
        fragment = NotificationFragment_.builder().build();
        fragment2 = ToDoFragment_.builder().build();
        pagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public Fragment getItem(int position) {
                if (position == 0) {
                    return fragment;
                } else {
                    return fragment2;
                }
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titleData.get(position);

            }
        };

        vp_petition.setAdapter(pagerAdapter);
        tl_suggest.setupWithViewPager(vp_petition);
        tl_suggest.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fragment2.setStudentName(studentName);
    }
}
