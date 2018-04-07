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

import java.util.HashSet;
import java.util.Set;

public class Test {

    private static SessionFactory sessionFactory;
    private static Session session;
    private static Transaction transaction;

    public static void main(String args[]){
        session = HibernateUtil.getHibernateSession();
        //创建一个水果对象
        FruitsEntity fruitsEntity = new FruitsEntity("葡萄3","images/葡是是速度.png");
        FruitsEntity fruitsEntity1 = new FruitsEntity("苹果2","images/啊大多数2.png");
        FruitsEntity fruitsEntity2 = new FruitsEntity("苹果3","images/葡仨萄的3.png");
        //将当水果对象加到set里
        Set<FruitsEntity> set = new HashSet<>();
        set.add(fruitsEntity);
        set.add(fruitsEntity1);
        set.add(fruitsEntity2);
//        //创建分类对象
//        CategoryEntity categoryEntity = new CategoryEntity("仁果类");
//        categoryEntity.setDes("减肥");
//        categoryEntity.setFruitsEntityList(set);
       CategoryEntity categoryEntity =  session.get(CategoryEntity.class,"浆果类");
//       FruitsEntity fruits =  session.get(FruitsEntity.class,7);
//       fruitsEntity.setCategory(categoryEntity);
//        fruits.setFruitsName("大榴莲");
//        fruits.setFruitsImg("images/大榴莲.png");
//       session.update(fruits);
        //关闭session
        HibernateUtil.closeSession(session);

        System.out.print("id :" + categoryEntity.getCategoryId()+"name:"+categoryEntity.getCategoryName());


    }
}
