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
import java.io.PrintWriter;

@WebServlet(name = "DeleteServlet")
public class DeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String type = request.getParameter("type");
        if (type.equals("fruits")){
            int fruitsId = Integer.parseInt(request.getParameter("fruitsId"));

            FruitsEntity fruit = (FruitsEntity) HibernateUtil.queryData(new FruitsEntity(),"fruitsId",fruitsId).get(0);
            Session session = HibernateUtil.getHibernateSession();
            session.delete(fruit);
            HibernateUtil.closeSession(session);

            request.getRequestDispatcher("/index.jsp").forward(request,response);
        }else if (type.equals("category")){
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));

            CategoryEntity category = (CategoryEntity) HibernateUtil.queryData(new CategoryEntity(),"categoryId",categoryId).get(0);
            Session session = HibernateUtil.getHibernateSession();
            session.delete(category);
            HibernateUtil.closeSession(session);
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
