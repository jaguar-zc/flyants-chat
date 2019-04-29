package org.flyants.authorize.utils;

import org.flyants.common.ResponseData;

/**
 * @Author zhangchao
 * @Date 2019/4/25 17:38
 * @Version v1.0
 */
public class ResponseDataUtils {


    public static ResponseData buildSuccess() {
        return new ResponseData(ResultEnums.SUCCESS.getCode(),ResultEnums.SUCCESS.getMsg());
    }

    public static ResponseData buildSuccess(Object obj) {
        return new ResponseData(ResultEnums.SUCCESS.getCode(), ResultEnums.SUCCESS.getMsg()).addObject(obj);
    }

    public static ResponseData buildSuccess(String code, String msg) {
        return new ResponseData(code, msg);
    }

    public static <T> ResponseData buildSuccess(String code, String msg, Object data) {
        return new ResponseData<T>(code, msg).addObject(data);
    }

    public static ResponseData buildError(String msg) {
        return new ResponseData(ResultEnums.ERROR.getCode(), msg);
    }

    public static <T> ResponseData buildError(String code, String msg) {
        return new ResponseData<T>(code, msg);
    }
}
