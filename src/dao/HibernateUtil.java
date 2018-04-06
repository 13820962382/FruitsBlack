package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    public static Session getHibernateSession(){
        //创建Configuration对象：对应包含hibernate的基本配置信息（cfg）和对象关系映射信息（hbm）
        Configuration configuration = new Configuration().configure();
        //创建注册服务对象
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
        //创建会话工厂
       SessionFactory sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
        //获取会话对象
        return sessionFactory.openSession();
    }


}
