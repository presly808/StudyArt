package ua.artcode.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Razer on 10.01.16.
 */
@WebServlet(name = "FindTask", urlPatterns = {"/find-task"})
public class FindTask extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        pw.println("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>          \n" +
                "<meta charset=\"UTF-8\">\n" +
                "<title>StudyArt</title>\n" +
                "</head> \n" +
                "<body>\n" +
                "<form  >\n" +
                "<p><input name=\"taskId\"></p>\n" +
                "<p><input value=\"get task\"  type=\"submit\"></p>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>");
        pw.flush();
        resp.sendRedirect("http://www.google.com");
    }
}
