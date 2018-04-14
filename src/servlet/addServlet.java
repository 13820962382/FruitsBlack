package servlet;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import dao.HibernateUtil;
import mode.CategoryEntity;
import mode.FruitsEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "addServlet")
public class addServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        //获取add的参数
        String add = request.getParameter("add");
        if (add.equals("category")){
            CategoryEntity category = new CategoryEntity();
            //判断分类名称是否存在
            if (HibernateUtil.isExist(category,"categoryName",request.getParameter("categoryName"))){
                request.setAttribute("result","分类名称已存在");
            }
            category.setCategoryName(request.getParameter("categoryName"));
            category.setDes(request.getParameter("des")!=null ? request.getParameter("des"):"");
            //获取session对象
            Session session =  HibernateUtil.getHibernateSession();
            //保存分类
            session.save(category);
            HibernateUtil.closeSession(session);
            request.getRequestDispatcher("/jsp/addCategory.jsp").forward(request,response);
        }else if (add.equals("fruits")){
            //保存图片
            //初始化smartUpload
            SmartUpload smartUpload = new SmartUpload();
            smartUpload.initialize(getServletConfig(),request,response);
            //设置文件上传的最大限制
            smartUpload.setMaxFileSize(1024*1024*10);
            //设置文件上传的类型
            smartUpload.setAllowedFilesList("png,jpg,gif");
            try {
                smartUpload.upload();
                String getFileName = smartUpload.getFiles().getFile(0).getFileName();
//            String path = new String(getFileName.getBytes("ISO-8859-1"), "GBK");

                String filePath = "/data/fruits/images";
                smartUpload.save(filePath);//保存路径

            } catch (SmartUploadException e) {
                e.printStackTrace();
            }
        //获取添加的水果名
            String fruitsName = request.getParameter("fruitsName");
            //获取添加的水果图片
            String fruitsImg = request.getParameter("fruitsImg");
            //获取添加的水果分类名称
            String category = request.getParameter("categoryName");
            //获取水果说明
            String des = request.getParameter("des")!=null ? request.getParameter("des"):"";
            //获取分类的ID
            CategoryEntity categoryEntity = new CategoryEntity(category);
            List<CategoryEntity> list =  HibernateUtil.queryData(categoryEntity,"categoryName",category);
            //获取session对象
            Session session = HibernateUtil.getHibernateSession();

            FruitsEntity fruits = new FruitsEntity(fruitsName,fruitsImg);
            fruits.setFruitsName(fruitsName);
            fruits.setFruitsImg(fruitsImg);
            fruits.setCategory(list.get(0)); //设置所属分类
            fruits.setDes(des);
            session.save(fruits);
            HibernateUtil.closeSession(session);


        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);
    }
}
