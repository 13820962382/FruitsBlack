package servlet;

import com.alibaba.fastjson.JSONObject;
import dao.HibernateUtil;
import mode.CategoryEntity;
import mode.FruitsEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "QueryServlet")
public class QueryServlet extends HttpServlet {
    private static final String IMG_PATH = "http://kidle.club:8080/upload/";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");//解决跨域访问问题
        PrintWriter out = response.getWriter();

        //查询所有分类
        List<CategoryEntity> categoryList = new ArrayList();
        categoryList =HibernateUtil.queryData(new CategoryEntity());
        request.setAttribute("options",categoryList);

        String type =  request.getParameter("type");
        if (type.equals("fruits")){
            int fruitsId = Integer.parseInt(request.getParameter("fruitsId"));
            FruitsEntity fruit = (FruitsEntity) HibernateUtil.queryData(new FruitsEntity(),"fruitsId",fruitsId).get(0);
            request.getSession().setAttribute("fruit",fruit);
            //转化为json数据
            JSONObject dataFruits = new JSONObject();
            dataFruits.put("fruitsId",fruit.getFruitsId());//获取id
            dataFruits.put("fruitsName",fruit.getFruitsName());//获取名字
            dataFruits.put("fruitsImg",IMG_PATH + fruit.getFruitsImg());//获取图片
            dataFruits.put("category",fruit.getCategory().getCategoryName());
            dataFruits.put("des",fruit.getDes());
            out.write(dataFruits.toString());

            request.getRequestDispatcher("/jsp/modify.jsp").forward(request,response);
        }else if (type.equals("category")){
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            CategoryEntity category = (CategoryEntity) HibernateUtil.queryData(new CategoryEntity(),"categoryId",categoryId).get(0);
            request.getSession().setAttribute("category",category);
            //转化为json数据
            JSONObject dataCategory = new JSONObject();
            dataCategory.put("categoryId",category.getCategoryId());//获取id
            dataCategory.put("categoryName",category.getCategoryName());//获取名字
            dataCategory.put("des",category.getDes());
            dataCategory.put("total",category.getTotalFruits());
            out.write(dataCategory.toString());

            request.getRequestDispatcher("/jsp/modify.jsp").forward(request,response);
        }else {
            request.getRequestDispatcher("/index,jsp").forward(request,response);
        }

    }
}
