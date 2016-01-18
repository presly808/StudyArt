package ua.artcode.servlet;

import org.mongodb.morphia.Morphia;
import org.springframework.context.ApplicationContext;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.codingbat.CodingBatTask;
import ua.artcode.model.codingbat.TaskTestResult;
import ua.artcode.process.TaskRunFacade;
import ua.artcode.service.AdminServiceImpl;
import ua.artcode.utils.SpringContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Maxim on 14.01.2016.
 */
@WebServlet(name = "checkTask", urlPatterns = "/checkTask")
public class CheckTask extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContext context = SpringContext.getContext();
        Morphia morphia = context.getBean(Morphia.class);
        morphia.map(CodingBatTask.class);
        TaskRunFacade taskRunFacade = context.getBean(TaskRunFacade.class);
        AdminServiceImpl adminService = new AdminServiceImpl();
        String id=req.getParameter("id");
        TaskTestResult taskTestResult = null;
        try {
            CodingBatTask task = adminService.getTask(id);
            taskTestResult = taskRunFacade.runTask(task, req.getParameter("userCode"));
        } catch (NoSuchTaskException e) {
            e.printStackTrace();
        }

        req.setAttribute("result", taskTestResult);
        req.getRequestDispatcher("/pages/check-task.jsp").forward(req, resp);
    }
}
