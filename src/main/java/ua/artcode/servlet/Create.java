package ua.artcode.servlet;

import ua.artcode.exception.AppException;
import ua.artcode.service.UserService;
import ua.artcode.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Razer on 19.01.16.
 */
@WebServlet(urlPatterns = "/create")
public class Create extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        UserService userService = new UserServiceImpl();
        try {
            userService.register(userName, password, email);
            resp.sendRedirect("/StudyArt-1.0/index.html");
        } catch (AppException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("WEB-INF/pages/registration-form.jsp").forward(req, resp);
        }
    }
}
