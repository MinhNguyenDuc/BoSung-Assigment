package controller;

import entity.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class Validate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/validate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String activeCode = req.getParameter("active-code");
        Account account = ofy().load().type(Account.class).id(email).now();
        System.out.println(account.toString());
        System.out.println(account.getActiveCode());
        if (account.getActiveCode().equals(activeCode)) {
            HttpSession session = req.getSession();
            session.setAttribute("account", account);
            account.setStatus(1);
            ofy().save().entity(account).now();
            req.getRequestDispatcher("/view/profile.jsp").forward(req, resp);
        } else {
            System.out.println("Validate fail");
            resp.sendRedirect(req.getContextPath() + "/validate");
        }
    }
}
