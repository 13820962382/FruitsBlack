import mode.CategoryEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class Test {

    private static SessionFactory sessionFactory;
    private static Session session;
    private static Transaction transaction;

    public static void main(String args[]){
//        创建Configuration对象：对应包含hibernate的基本配置信息（cfg）和对象关系映射信息（hbm）
        Configuration configuration = new Configuration().configure();
        //创建注册服务对象
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
        //创建会话工厂
        sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
        //获取会话对象
        session = sessionFactory.openSession();
        //开启事务
        transaction = session.beginTransaction();

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryName("仁果类水果");
        categoryEntity.setTotalFruit(25);
        categoryEntity.setDes("健康瘦身的");
        session.save(categoryEntity);
        //关闭事务
        transaction.commit();
        //关闭会话对象
        session.close();
        //关闭会话工厂
        sessionFactory.close();
    }
}
