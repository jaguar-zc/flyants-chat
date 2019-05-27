package org.flyants.authorize;

import org.flyants.authorize.domain.entity.platform.People;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * @Author zhangchao
 * @Date 2019/4/26 11:36
 * @Version v1.0
 */
@Component
public class AppListenner implements ApplicationContextAware {


    static ApplicationContext applicationContext;


    @PostConstruct
    public void ru() {
//        Map<String, SessionFactory> beansOfType = applicationContext.getBeansOfType(SessionFactory.class);

        System.out.println("getBeansOfType======================");

        System.out.println("HibernateTemplate:" + applicationContext.getBeansOfType(HibernateTemplate.class).size());

//        SessionFactory sessionFactory = new SessionFactoryImpl()
        System.out.println("SessionFactory:" + applicationContext.getBeansOfType(SessionFactory.class).size());
        System.out.println("HibernateTransactionManager:" + applicationContext.getBeansOfType(HibernateTransactionManager.class).size());
        System.out.println("DataSource:" + applicationContext.getBeansOfType(DataSource.class).size());
        System.out.println("Configuration:" + applicationContext.getBeansOfType(Configuration.class).size());
        System.out.println("EntityManagerFactory:" + applicationContext.getBeansOfType(EntityManagerFactory.class).size());


        System.out.println("getBeansOfType======================");

//        beansOfType.forEach((k,v)->{
//            System.out.print(k);
//            System.out.print("=");
//            System.out.print(v);
//            System.out.println("");
//        });
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AppListenner.applicationContext = applicationContext;


    }
}
