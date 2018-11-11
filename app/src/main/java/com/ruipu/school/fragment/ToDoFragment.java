package com.ruipu.school.fragment;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ruipu.school.R;
import com.ruipu.school.beans.Student;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2018/11/11.
 */
@EFragment(R.layout.fragment_todo)
public class ToDoFragment extends Fragment {
    @ViewById
    TextView tv_dromState, tv_dromDetail, tv_financeState, tv_financeDetail, tv_libraryState,
            tv_libraryDetail, tv_cardState, tv_cardDetail;

    @AfterViews
    void initView() {

    }

    public void setStudentName(String studentName) {
        BmobQuery<Student> query = new BmobQuery<>();
        query.addWhereEqualTo("name", studentName);
        query.findObjects(new FindListener<Student>() {
            @Override
            public void done(List<Student> list, BmobException e) {
                Log.e("aaa", "setStudentName: " + list.size() + "  " + e);
                if (list.size() != 0) {
                    setDatas(list.get(0));
                }
            }
        });

    }

    public void setDatas(Student student) {
        if (student.getDormState() == 0) {
            tv_dromState.setText("宿管处：未处理");
            tv_dromDetail.setVisibility(View.GONE);
        } else if (student.getDormState() == 1) {
            tv_dromState.setText("宿管处：通过");
            tv_dromDetail.setVisibility(View.GONE);
        } else {
            tv_dromState.setText("宿管处：未通过");
            tv_dromDetail.setText(student.getDorm());
        }

        if (student.getFinanceState() == 0) {
            tv_financeState.setText("财务处：未处理");
            tv_financeDetail.setVisibility(View.GONE);
        } else if (student.getFinanceState() == 1) {
            tv_financeState.setText("财务处：通过");
            tv_financeDetail.setVisibility(View.GONE);
        } else {
            tv_financeState.setText("财务处：未通过");
            tv_financeDetail.setText(student.getFinance());
        }

        if (student.getCardState() == 0) {
            tv_cardState.setText("卡务处：未处理");
            tv_cardDetail.setVisibility(View.GONE);
        } else if (student.getCardState() == 1) {
            tv_cardState.setText("卡务处：通过");
            tv_cardDetail.setVisibility(View.GONE);
        } else {
            tv_cardState.setText("卡务处：未通过");
            tv_cardDetail.setText(student.getCard());
        }


        if (student.getLibraryState() == 0) {
            tv_libraryState.setText("图书馆：未处理");
            tv_libraryDetail.setVisibility(View.GONE);
        } else if (student.getLibraryState() == 1) {
            tv_libraryState.setText("图书馆：通过");
            tv_libraryDetail.setVisibility(View.GONE);
        } else {
            tv_libraryState.setText("图书馆：未通过");
            tv_libraryDetail.setText(student.getLibrary());
        }
    }
}
