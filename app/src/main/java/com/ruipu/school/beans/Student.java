package com.ruipu.school.beans;

import cn.bmob.v3.BmobObject;

/**
 * Created by ruipu on 2018/11/8.
 */

public class Student extends BmobObject {

    // 钥匙 宿舍卫生   宿管
    // 财务 未缴清款项
    // 图书馆  是否有未还的书
    // 卡务  校园卡是否有余额
    // State  1 是通过  2 不通过  0未审核
    private String number;//学号
    private String name;
    private String password;
    private String dorm;// su宿管
    private String finance;//财务
    private String library;
    private String card;
    private Integer cardState;
    private Integer dormState;
    private Integer libraryState;
    private Integer financeState;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDorm() {
        return dorm;
    }

    public void setDorm(String dorm) {
        this.dorm = dorm;
    }

    public String getFinance() {
        return finance;
    }

    public void setFinance(String finance) {
        this.finance = finance;
    }

    public String getLibrary() {
        return library;
    }

    public void setLibrary(String library) {
        this.library = library;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public Integer getCardState() {
        return cardState;
    }

    public void setCardState(Integer cardState) {
        this.cardState = cardState;
    }

    public Integer getDormState() {
        return dormState;
    }

    public void setDormState(Integer dormState) {
        this.dormState = dormState;
    }

    public Integer getLibraryState() {
        return libraryState;
    }

    public void setLibraryState(Integer libraryState) {
        this.libraryState = libraryState;
    }

    public Integer getFinanceState() {
        return financeState;
    }

    public void setFinanceState(Integer financeState) {
        this.financeState = financeState;
    }

    public Student(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Student() {
    }
}
