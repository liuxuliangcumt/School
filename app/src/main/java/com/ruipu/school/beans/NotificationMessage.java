package com.ruipu.school.beans;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by ruipu on 2018/11/9.
 */

public class NotificationMessage extends BmobObject implements Serializable {

    private String title, content;

    public NotificationMessage(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
