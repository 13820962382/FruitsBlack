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
        JSONArray jsonarray = new JSONArray();
        for (CategoryEntity category : categoryList) {
            jsonobj = new JSONObject();
            jsonobj.put("category",category.getCategoryName());
            jsonobj.put("categoryDes",category.getDes());
            jsonobj.put("fruits",category.getFruitsEntitySet());
            jsonobj.put("totalFruits",category.getTotalFruits());
            jsonarray.add(jsonobj);
        }
        System.out.print(jsonarray.toString());
        System.out.print(categoryList.size());


    }
}
