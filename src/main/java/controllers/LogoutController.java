package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import utils.Constant;
import java.io.IOException;

@WebServlet(urlPatterns = "/logout")
public class LogoutController  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("account");
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (Constant.COOKIE_REMEMBER.equals(cookie.getName())) {
                    cookie.setMaxAge(0);
                    resp.addCookie(cookie);
                    break;
                }
            }
        }
        resp.sendRedirect("views/home.jsp");
    }
}
