package org.flyants.authorize.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author zhangchao
 * @Date 2019/5/29 16:06
 * @Version v1.0
 */
@Component
public class SpringUtils  implements ApplicationContextAware {

    public static ApplicationContext $;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        $ = applicationContext;
    }


}
