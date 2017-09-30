package ua.nure.patsera.periodicals.web.controller;

import ua.nure.patsera.periodicals.bean.Captcha;
import ua.nure.patsera.periodicals.service.CaptchaService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet
public class CaptchaServlet extends HttpServlet {
    private CaptchaService captchaService;

    @Override
    public void init() throws ServletException {
        captchaService = (CaptchaService) getServletContext().getAttribute(ServletAttributes.CAPTCHA_SERVICE);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/jpg");
        Captcha captcha = captchaService.generateAndWriteCaptchaToOutputStream(response.getOutputStream());
        captchaService.add(captcha.getCaptchaId(), captcha);
        HttpSession session = request.getSession();
        session.setAttribute("captchaId", captcha.getCaptchaId());
    }

    private String getCaptchaId(HttpServletRequest request) {
        return (String) request.getServletContext().getAttribute("captchaId");
    }
}
