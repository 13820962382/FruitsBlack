package dao;

import mode.CategoryEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
   private static SessionFactory sessionFactory;
   private static Session session;
   private static Transaction transaction;

    public static Session getHibernateSession(){
        //创建Configuration对象：对应包含hibernate的基本配置信息（cfg）和对象关系映射信息（hbm）
        Configuration configuration = new Configuration().configure();
        //创建注册服务对象
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
        //创建会话工厂
        sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
        //获取会话对象
        session = sessionFactory.openSession();
        //开启事务
        transaction = session.beginTransaction();

        return session;
    }

    public static void closeSession(Session session){
        transaction.commit();//提交事务
        if (session!=null){
            session.close();//释放session对象
        }
        sessionFactory.close(); //关闭sessionFactory
    }

    public static void queryCategory(CategoryEntity categoryEntity){

    }


}
