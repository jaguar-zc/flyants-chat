package org.flyants.authorize.utils;

/**
 * @Author zhangchao
 * @Date 2019/5/22 10:34
 * @Version v1.0
 */
public class JWTManager {

    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();


    public static void set(Long id){
        threadLocal.set(id);
    }

    public static Long get(){
        return threadLocal.get();
    }


}
