package com.ruipu.school.beans;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobRole;

/**
 */

public class UserRole extends BmobObject {

    private String passWord;
    private Integer role;//  1 是管理员  2 教务处  3辅导员 4图书馆 5 宿管处  6 财务处  7 卡务中心 8 学生
    private Boolean isAble;
    private String name;
    private Integer instructorGrade;

    public Integer getInstructorGrade() {
        return instructorGrade;
    }

    public void setInstructorGrade(Integer instructorGrade) {
        this.instructorGrade = instructorGrade;
    }

    public UserRole(String name, Integer role) {
        this.role = role;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Boolean getAble() {
        return isAble;
    }

    public void setAble(Boolean able) {
        isAble = able;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "passWord='" + passWord + '\'' +
                ", role=" + role +
                ", isAble=" + isAble +
                ", name='" + name + '\'' +
                '}';
    }
}
