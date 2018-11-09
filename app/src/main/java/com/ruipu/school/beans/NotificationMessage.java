package com.ruipu.school.beans;

import cn.bmob.v3.BmobObject;

/**
 * Created by ruipu on 2018/11/9.
 */

public class NotificationMessage extends BmobObject {

    private String title, content;

    public NotificationMessage(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
