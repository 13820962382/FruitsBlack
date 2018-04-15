package servlet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static javafx.scene.input.KeyCode.F;

@WebServlet(name = "getServlet")
public class getServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out =  response.getWriter();

        //查询所有分类
        List<CategoryEntity> categoryList = new ArrayList();
        categoryList =HibernateUtil.queryData(new CategoryEntity());
        //查询所有水果
        List<FruitsEntity> fruitsList = new ArrayList();
        fruitsList =  HibernateUtil.queryData(new FruitsEntity());
        request.getSession().setAttribute("categoryList",categoryList);
        request.getSession().setAttribute("fruitsList",fruitsList);

        JSONArray jsonArray = new JSONArray();
        for (CategoryEntity category : categoryList) {
            JSONObject categoryJson = (JSONObject) JSONObject.toJSON(category);
            if (categoryJson.getString("fruitsEntityList")!=null){
                Set<FruitsEntity> fruitsSet =  category.getFruitsEntityList();
                for (FruitsEntity fruits : fruitsSet) {
                    jsonArray.add(JSONObject.toJSON(fruits));
                    out.write(jsonArray.toString());

                }

            }
            out.write(categoryJson.toString());
        }



    }
}
