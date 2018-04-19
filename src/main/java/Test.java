import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
        JSONObject jsonobj;

        List<CategoryEntity> categoryList =HibernateUtil.queryData(new CategoryEntity());
//        HibernateUtil.deleteData(categoryList.get(0));
//        System.out.print(categoryList.get(0).getCategoryName());
//        System.out.println(categoryList.size());


    }
}
