package com.ruipu.school.activity;

import android.os.Bundle;

import com.ruipu.school.R;

import org.androidannotations.annotations.EActivity;

@EActivity
public class StudentMianActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_mian);
    }
}
