package ru.yusdm.javacore.lesson24web.autoservice.storage.controller;

import ru.yusdm.javacore.lesson24web.autoservice.AutoServiceDemo;
import ru.yusdm.javacore.lesson24web.autoservice.common.business.application.StorageType;
import ru.yusdm.javacore.lesson24web.autoservice.common.business.application.servicefactory.ServiceSupplier;
import ru.yusdm.javacore.lesson24web.autoservice.common.business.controller.BaseController;
import ru.yusdm.javacore.lesson24web.autoservice.storage.initor.fromsql.H2DbInitor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ViewAllSelectStorageServlet", urlPatterns = "/aaa")
public class SelectStorageController extends BaseController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StorageType storageType = StorageType.valueOf(req.getParameter("storageType"));
        ServiceSupplier.newInstance(storageType);

        if (StorageType.RELATIONAL_DB.equals(storageType)) {
            initDatabase();
        }

        resp.sendRedirect("orders");
    }

    private void initDatabase(){
        H2DbInitor h2DbInitor = new H2DbInitor();
        h2DbInitor.init();

        AutoServiceDemo.Application application = new AutoServiceDemo.Application();
        application.fillStorage();
    }
}
