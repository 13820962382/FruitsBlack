package servlet;

import dao.HibernateUtil;
import mode.CategoryEntity;
import mode.FruitsEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "addServlet")
public class addServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        //获取add的参数
        String add = request.getParameter("add");
        if (add.equals("category")){
            CategoryEntity category = new CategoryEntity();
            category.setCategoryName(request.getParameter("categoryName"));
            category.setDes(request.getParameter("des")!=null ? request.getParameter("des"):"");
            //获取session对象
            Session session =  HibernateUtil.getHibernateSession();
            //保存分类
            session.save(category);
            HibernateUtil.closeSession(session);
        }else if (add.equals("fruits")){
            //获取添加的水果名
            String fruitsName = request.getParameter("fruitsName");
            //获取添加的水果图片
            String fruitsImg = request.getParameter("fruitsImg");
            //获取添加的水果分类名称
            String category = request.getParameter("category");
            //获取水果说明
            String des = request.getParameter("des")!=null ? request.getParameter("des"):"";

            Session session = HibernateUtil.getHibernateSession();
            //获取查询对象
            Query query = session.createQuery("");

            CategoryEntity categoryEntity = new CategoryEntity(category);
            FruitsEntity fruits = new FruitsEntity(fruitsName,fruitsImg);
            fruits.setDes(des);

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);
    }
}
