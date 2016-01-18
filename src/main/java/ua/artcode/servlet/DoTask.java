package ua.artcode.servlet;

import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.codingbat.CodingBatTask;
import ua.artcode.service.AdminService;
import ua.artcode.service.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Razer on 11.01.16.
 */
@WebServlet(name = "doTask", urlPatterns = "/doTask")
public class DoTask extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String taskId = req.getParameter("taskId");
        AdminService adminService = new AdminServiceImpl();
        try {
            CodingBatTask task = adminService.getTask(taskId);
            req.setAttribute("task",task);
        } catch (NoSuchTaskException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/pages/do-task.jsp").forward(req,resp);
    }
}
