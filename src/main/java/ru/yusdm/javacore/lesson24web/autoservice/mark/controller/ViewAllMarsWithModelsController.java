package ru.yusdm.javacore.lesson24web.autoservice.mark.controller;

import ru.yusdm.javacore.lesson24web.autoservice.common.business.application.servicefactory.ServiceSupplier;
import ru.yusdm.javacore.lesson24web.autoservice.common.business.controller.BaseController;
import ru.yusdm.javacore.lesson24web.autoservice.mark.domain.Mark;
import ru.yusdm.javacore.lesson24web.autoservice.mark.dto.MarkDtoConverter;
import ru.yusdm.javacore.lesson24web.autoservice.mark.service.MarkService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;

@WebServlet(name = "ViewAllMarks", urlPatterns = "/marks")
public class ViewAllMarsWithModelsController extends BaseController {

    private MarkService markService = ServiceSupplier.getInstance().getMarkService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Mark> marks = markService.findAllMarksFetchingModels();
            if (isNotEmpty(marks)) {
                req.setAttribute("marks", MarkDtoConverter.convertToDtos(marks));
            }
            forwardToPage(req, resp, "marks.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            redirectTo500("Couldn't fetch marks!", resp);
        }
    }
}
