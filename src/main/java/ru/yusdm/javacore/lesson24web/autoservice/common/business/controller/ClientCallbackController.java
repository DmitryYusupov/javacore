package ru.yusdm.javacore.lesson24web.autoservice.common.business.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ClientCallbacks", urlPatterns = "/clientcallbacks")
public class ClientCallbackController extends BaseController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            forwardToPage(req, resp, "clientcallbacks.jsp");
        } catch (Exception e) {
            redirectTo500("Oops!", resp);
        }
    }
}
