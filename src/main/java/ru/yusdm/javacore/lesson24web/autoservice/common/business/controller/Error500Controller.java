package ru.yusdm.javacore.lesson24web.autoservice.common.business.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "InternalServerError", urlPatterns = "/error500")
public class Error500Controller extends BaseController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forwardToPage(req, resp, "error500.jsp");
    }
}
