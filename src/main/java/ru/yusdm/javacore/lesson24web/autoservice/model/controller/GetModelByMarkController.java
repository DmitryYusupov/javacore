package ru.yusdm.javacore.lesson24web.autoservice.model.controller;

import ru.yusdm.javacore.lesson24web.autoservice.common.business.application.servicefactory.ServiceSupplier;
import ru.yusdm.javacore.lesson24web.autoservice.common.business.controller.BaseController;
import ru.yusdm.javacore.lesson24web.autoservice.model.domain.Model;
import ru.yusdm.javacore.lesson24web.autoservice.model.dto.ModelDto;
import ru.yusdm.javacore.lesson24web.autoservice.model.dto.ModelDtoConverter;
import ru.yusdm.javacore.lesson24web.autoservice.model.service.ModelService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "GetModelsByMark", urlPatterns = "/getmodels")
public class GetModelByMarkController extends BaseController {

    private ModelService modelService = ServiceSupplier.getInstance().getModelService();

    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            List<Model> models = getMarkId(req).map(markId -> modelService.getModelsByMarkId(markId)).orElse(Collections.emptyList());
            sentModelsToClient(models, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Optional<Long> getMarkId(HttpServletRequest request) {
        try {
            return Optional.of(Long.parseLong(request.getParameter("markId")));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private void sentModelsToClient(List<Model> models, HttpServletResponse servletResponse) {
        List<ModelDto> dtos = ModelDtoConverter.convertToDtos(models);

        PrintWriter writer = null;

        try {
            writer = servletResponse.getWriter();
            for (ModelDto dto : dtos) {
                writer.write(dto.getId() + ":" + dto.getName());
            }
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
