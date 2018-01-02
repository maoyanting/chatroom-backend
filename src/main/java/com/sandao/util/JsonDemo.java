package com.sandao.util;

import com.alibaba.fastjson.JSON;
import com.sandao.entity.ChatGroup;

/**
 * Created by maoyanting on 2017/11/29.
 * @author sandoa
 */
public class JsonDemo {
    private Object data;
    private int resCode;
    private String resMsg;

    public JsonDemo(Object data, int resCode, String resMsg) {
        this.data = data;
        this.resCode = resCode;
        this.resMsg = resMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getResCode() {
        return resCode;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }
}
