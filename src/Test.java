import dao.HibernateUtil;
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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {

    private static SessionFactory sessionFactory;
    private static Session session;
    private static Transaction transaction;

    public static void main(String args[]){
        CategoryEntity category = new CategoryEntity("浆果类");
        Session session =  HibernateUtil.getHibernateSession();
        //保存分类
        session.save(category);
        HibernateUtil.closeSession(session);

    }
}
