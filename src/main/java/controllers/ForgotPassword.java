package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.UserModel;
import services.IUserService;
import services.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet(urlPatterns = "/forgotpws")
public class ForgotPassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("views/forgotPassword.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("new_psw");
        String password_repeat = req.getParameter("confirm_new_psw");
        IUserService userService = new UserServiceImpl();
        UserModel user = userService.findByUserNameAndEmail(username,email);
        String msg;

        if(user == null){
            msg = "Không tìm thấy người dùng";
            req.getRequestDispatcher("/views/forgotPassword.jsp").forward(req, resp);
            return;
        }

        if(!password.equals(password_repeat)){
            msg = "Xác nhận mật khẩu không đúng";
            req.setAttribute("alert", msg);
            req.getRequestDispatcher("/views/forgotPassword.jsp").forward(req, resp);
            return;
        }

        boolean isSuccess = userService.updatePassword(username,password);
        if(isSuccess){
            msg = "Đổi mật khẩu thành công";
            req.setAttribute("alert", msg);
            resp.sendRedirect(req.getContextPath() + "/home");
        }
        else {
            msg = "Đổi mật khẩu thất bại";
            req.setAttribute("alert", msg);
            req.getRequestDispatcher("/views/forgotPassword.jsp").forward(req, resp);
        }
    }
}
