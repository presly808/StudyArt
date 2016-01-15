package ua.artcode.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Maxim on 14.01.2016.
 */
@WebServlet(name = "checkTask", urlPatterns = "/checkTask")
public class CheckTask extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String userCode = req.getParameter("userCode");
          String id=req.getParameter("id");
//        ApplicationContext context = SpringContext.getContext();
//        Datastore datastore = (Datastore) context.getBean("datastore");
//        CodingBatTaskDao codingBatTaskDao = new CodingBatTaskDaoMongoImpl(datastore);
//        CodingBatTask task = null;
//        try {
//            task = codingBatTaskDao.findById(id);
//        } catch (NoSuchTaskException e) {
//            e.printStackTrace();
//        }
        PrintWriter pw = resp.getWriter();
        pw.println("<body>" +
                "<h1>Hello Servlet</h1>" +
                "<p>info from servlet " +id +"</p>" +
                "</body>");
        pw.flush();
        //TaskRunFacade taskRunFacade = context.getBean(TaskRunFacade.class);
        //taskRunFacade.runTask(task, task.getTemplate().substring(0, task.getTemplate().length() - 1) + userCode+"\n}");
    }
}
