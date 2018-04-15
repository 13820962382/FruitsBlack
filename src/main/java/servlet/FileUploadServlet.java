package servlet;

import dao.HibernateUtil;
import mode.CategoryEntity;
import mode.FruitsEntity;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "FileUploadServlet")
public class FileUploadServlet extends HttpServlet {
    private  Map itemMap = new HashMap();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //解决中文乱码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        //保存在项目根路径
//        String filePath= getServletContext().getRealPath("/") + "upload/";
//        String tempPath= getServletContext().getRealPath("/") +"/temp";
        String filePath=  "/data/fruits/images";
        //创建工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // threshold 极限、临界值，即硬盘缓存 10M
        factory.setSizeThreshold(10*1024);
        // repository 贮藏室，即临时文件目录
//        factory.setRepository(new File(tempPath));
        ServletFileUpload fileUpload = new ServletFileUpload(factory);//创建工厂解析器

        fileUpload.setHeaderEncoding("UTF-8");
        // 设置允许上传的最大文件大小 10M
        fileUpload.setSizeMax(10 * 1024 * 1024);
        //创建容器来存放解析内容
        List<FileItem> fileItems = new ArrayList();
        //将上传的文件信息放入容器中
        try {
            fileItems = fileUpload.parseRequest(request);

        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        ///遍历容器,处理解析的内容;判断是否上传的是否是普通字段
        for (FileItem fileitem:fileItems) {
            //获取普通域的字段的值放在itemMap中
            if (fileitem.isFormField()){
               String fieldName = fileitem.getFieldName();
               String value = fileitem.getString("utf-8");
                itemMap.put(fieldName,value);


            }else {
                //获取文件域的文件
                String path = fileitem.getName();//文件名传过来
                itemMap.put("fruitsImg",path);
                File file = new File(filePath,path);
                try {
                    //保存文件
                    fileitem.write(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }
        //获取添加的水果名
        String fruitsName = (String) itemMap.get("fruitsName");
        //获取添加的水果分类名称
        String category = (String) itemMap.get("categoryName");
        //获取添加的水果图片路径
        String fruitsImg = (String) itemMap.get("fruitsImg");
        //获取水果说明
        String description = (String) itemMap.get("des");
        String des = description!=null ? description : "";
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
        request.getRequestDispatcher("/index.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);
    }

}
