package servlet;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(name = "servlet.FtpServlet")
public class FtpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String filePath = getServletContext().getRealPath("/") + "upload";
        File file = new File(filePath);
        if(!file.exists()){
            file.mkdir();
        }

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

            System.out.println("上传文件的数量："+smartUpload.getFiles().getCount());
            smartUpload.save(filePath);//保存路径

        } catch (SmartUploadException e) {
            e.printStackTrace();
        }

        String result = "上传成功";
        request.setAttribute("result",result);
        request.getRequestDispatcher("/index.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);
    }
}
