package org.flyants.common;

import java.io.Serializable;

/**
 * @Author zhangchao
 * @Date 2019/4/25 17:31
 * @Version v1.0
 */
public class ResponseData<T> implements Serializable {


    private String resp_code;

    private String resp_msg;

    private T data;


    public ResponseData(String code, String msg, T data) {
        this.resp_code = code;
        this.resp_msg = msg;
        this.data = data;
    }

    public ResponseData(String code, String msg) {
        this.resp_code = code;
        this.resp_msg = msg;
    }

    public ResponseData() {
    }

    public String getResp_code() {
        return resp_code;
    }

    public void setResp_code(String resp_code) {
        this.resp_code = resp_code;
    }

    public String getResp_msg() {
        return resp_msg;
    }

    public void setResp_msg(String resp_msg) {
        this.resp_msg = resp_msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
