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


@WebServlet(name = "getServlet")
public class getServlet extends HttpServlet {
    private static final String IMG_PATH = "http://kidle.club:8080/upload/";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
//        response.setHeader("Access-Control-Allow-Origin", "*");//解决跨域访问问题
        PrintWriter out =  response.getWriter();

        //查询所有分类
        List<CategoryEntity> categoryList = new ArrayList();
        categoryList =HibernateUtil.queryData(new CategoryEntity());
        //查询所有水果
        List<FruitsEntity> fruitsList = new ArrayList();
        fruitsList =  HibernateUtil.queryData(new FruitsEntity());
        request.getSession().setAttribute("categoryList",categoryList);
        request.getSession().setAttribute("fruitsList",fruitsList);

        //将对象解析成json数据返回客户端
        JSONObject responseJson = new JSONObject();
        JSONArray dataJsonArray = new JSONArray();
        for (int i = 0; i < categoryList.size(); i++)  {
           JSONObject dataJson = new JSONObject();
            JSONArray fruitsArray = new JSONArray();
            if (categoryList.get(i).getFruitsEntitySet().size()!=0){
                Set<FruitsEntity> fruitsSet = categoryList.get(i).getFruitsEntitySet();
                for (FruitsEntity fruitsEntity: fruitsSet) {
                    JSONObject fruitsJson = new JSONObject();
                    fruitsJson.put("fruitsName",fruitsEntity.getFruitsName());
                    fruitsJson.put("categoryName",fruitsEntity.getCategory().getCategoryName());
                    fruitsJson.put("fruitsDes",fruitsEntity.getDes());
                    fruitsJson.put("fruitsImg",IMG_PATH + fruitsEntity.getFruitsImg());
                    fruitsArray.add(fruitsJson);
                }
            }
            dataJson.put("categoryId",categoryList.get(i).getCategoryId());
            dataJson.put("categoryName",categoryList.get(i).getCategoryName());
            dataJson.put("categoryDes",categoryList.get(i).getDes());
            dataJson.put("totalFruits",categoryList.get(i).getTotalFruits());
            dataJson.put("fruitsList",fruitsArray);
            dataJsonArray.add(dataJson);
        }
        responseJson.put("data",dataJsonArray);
        out.write(responseJson.toString());



    }
}
