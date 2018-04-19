package dao;

import mode.CategoryEntity;
import mode.FruitsEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HibernateUtil {
   private static SessionFactory sessionFactory;
   private static Session session;
   private static Transaction transaction;
    private static StandardServiceRegistry serviceRegistry;

    public static Session getHibernateSession(){
        //创建Configuration对象：对应包含hibernate的基本配置信息（cfg）和对象关系映射信息（hbm）
        Configuration configuration = new Configuration().configure();
        //创建注册服务对象
        serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
//        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
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
        StandardServiceRegistryBuilder.destroy(serviceRegistry);
    }

    /*
     * 动态查询String型数据
     * 根据实体类查询表中某个字段的值是否存在
     * */
    public static Boolean isExist(Object obj,String fledName,String value){
        Session session =getHibernateSession();
        //获取类名
        String className = obj.getClass().toString();
        String c = className.replace("class","");
        //拼接hql语句
        String hql = "from "+c+" where "+fledName+"="+"'"+value+"'";
        //获取查询对象
        Query query = session.createQuery(hql);
        List<Object> list = query.list();
        closeSession(session);
        if (list!=null&&list.size()>0){
            return true;
        }
        return false;
    }

    /*
     * 动态查询String型数据
     * 根据实体类查询单个字段并返回list
     * */
    public static List queryData(Object obj,String fledName,String value){
        Session session =getHibernateSession();
        //获取类名
        String className = obj.getClass().toString();
        String c = className.replace("class","");
        //拼接hql语句
        String hql =  "from "+c+" where "+fledName+"="+"'"+value+"'";
        //获取查询对象
        Query query = session.createQuery(hql);
        List list = query.list();
        closeSession(session);

        return list;

    }
    /*
     * 动态查询int型数据
     * 根据实体类查询单个字段并返回list
     * */
    public static List queryData(Object obj,String fledName,int value){
        Session session =getHibernateSession();
        //获取类名
        String className = obj.getClass().toString();
        String c = className.replace("class","");
        //拼接hql语句
        String hql =  "from "+c+" where "+fledName+"="+value;
        //获取查询对象
        Query query = session.createQuery(hql);
        List list = query.list();
        closeSession(session);

        return list;

    }

    /*
    * 查询数据
    * */
    public static List queryData(Object obj){
        Session session =getHibernateSession();
        //获取类名
        String className = obj.getClass().toString();
        String c = className.replace("class","");
        //拼接hql语句
        String hql =  "from "+c;
        //获取查询对象
        Query query = session.createQuery(hql);
        List list = query.list();
        closeSession(session);

        return list;
    }




}
