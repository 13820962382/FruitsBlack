package servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //解决中文乱码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String filePath= getServletContext().getRealPath("/") + "upload";
        String tempPath= getServletContext().getRealPath("/") +"/temp";
        //创建工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // threshold 极限、临界值，即硬盘缓存 10M
        factory.setSizeThreshold(10*1024);
        // repository 贮藏室，即临时文件目录
        factory.setRepository(new File(tempPath));
        ServletFileUpload fileUpload = new ServletFileUpload(factory);//创建工厂解析器

        fileUpload.setHeaderEncoding("UTF-8");
        // 设置允许上传的最大文件大小 10M
        fileUpload.setSizeMax(10 * 1024 * 1024);
        //创建容器来存放解析内容
        List<FileItem> fileItems = new ArrayList<>();
        //将上传的文件信息放入容器中
        try {
            fileItems = fileUpload.parseRequest(request);

        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        ///遍历容器,处理解析的内容;判断是否上传的是否是普通字段
        for (FileItem fileitem:fileItems) {
            if (fileitem.isFormField()){
               String fieldName = fileitem.getFieldName();
               String value = fileitem.getString("utf-8");
                Map map = new HashMap();
                map.put(fieldName,value);


            }else {

            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);
    }

}
