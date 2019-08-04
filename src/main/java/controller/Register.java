package controller;

import com.google.common.hash.Hashing;
import entity.Account;
import util.OTPGenerate;
import util.SendActivateCode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String fullName = req.getParameter("fullName");
        String password = req.getParameter("password");
        String sha256Password = Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
        Account account = new Account(email, sha256Password, fullName);
        String otpCode = OTPGenerate.OTP(7);
        account.setActiveCode(otpCode);
        SendActivateCode.sendMail(email, otpCode);
        ofy().save().entity(account).now();
        System.out.println("Saved");
        resp.sendRedirect(req.getContextPath() + "/validate");
    }
}
