package org.flyants.common.file;

import org.flyants.common.file.aliyun.AliyunOssObjectManagerFactory;

/**
 * @Author zhangchao
 * @Date 2019/5/23 17:02
 * @Version v1.0
 */
public interface  ObjectManagerFactory {


   static AliyunOssObjectManagerFactory build(){
        return new AliyunOssObjectManagerFactory();
    }


}
