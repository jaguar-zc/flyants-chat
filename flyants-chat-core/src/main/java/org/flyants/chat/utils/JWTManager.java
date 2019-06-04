package org.flyants.chat.utils;

/**
 * @Author zhangchao
 * @Date 2019/5/22 10:34
 * @Version v1.0
 */
public class JWTManager {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();


    public static void set(String id){
        threadLocal.set(id);
    }

    public static String get(){
        return threadLocal.get();
    }


}
