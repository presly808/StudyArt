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
import java.io.PrintWriter;

/**
 * Created by Razer on 11.01.16.
 */
@WebServlet(name = "doTask", urlPatterns = "/doTask")
public class DoTask extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CodingBatTask codingBatTask = null;
        PrintWriter pw = resp.getWriter();
        String codingBatId = req.getParameter("taskId");
        AdminService adminService = new AdminServiceImpl();
        try {
            codingBatTask = adminService.getTask(codingBatId);
        } catch (NoSuchTaskException e) {
            e.printStackTrace();
        }
        pw.println("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>Do task</title>\n" +
                "</head>\n" +
                "\t\n" +
                "<body>\n" +
                "\t<p>" + codingBatTask.toString() + "<p>\n" +
                "</body>\n" +
                "</html>");
        pw.flush();
        //TODO create servlet,read about how to sent data from servlet to jsp!
        //req.getRequestDispatcher("/pages/do-task.jsp").forward(req, resp);
    }
}
