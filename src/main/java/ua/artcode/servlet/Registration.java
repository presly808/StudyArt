package ua.artcode.servlet;

import ua.artcode.exception.AppException;
import ua.artcode.model.common.User;
import ua.artcode.service.UserService;
import ua.artcode.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Maxim on 18.01.2016.
 */
@WebServlet(urlPatterns = "/registration")
public class Registration extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        UserService userService = new UserServiceImpl();
        try {
            userService.register(userName, password, email);
            resp.sendRedirect("/pages/index.jsp");
        } catch (AppException e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/pages/regist-form.jsp").forward(req, resp);
        }

    }
}
