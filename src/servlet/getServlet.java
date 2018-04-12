package servlet;

import dao.HibernateUtil;
import mode.CategoryEntity;
import mode.FruitsEntity;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "getServlet")
public class getServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //查询所有分类
        List<CategoryEntity> categoryList = new ArrayList<>();
        categoryList =HibernateUtil.queryData(new CategoryEntity());
        //查询所有水果
        List<FruitsEntity> fruitsList = new ArrayList<>();
        fruitsList =  HibernateUtil.queryData(new FruitsEntity());

        if (request.getParameter("type").equals("category")){

            request.getSession().setAttribute("categoryList",categoryList);
            request.getRequestDispatcher("/jsp/addCategory.jsp").forward(request,response);

        }
        if (request.getParameter("type").equals("fruits")){

            request.getSession().setAttribute("fruitsList",fruitsList);
            request.getRequestDispatcher("/jsp/addFruits.jsp").forward(request,response);

        }

//        request.setAttribute("categoryList",category);
//        request.setAttribute("fruitsList",fruitsList);

    }
}
