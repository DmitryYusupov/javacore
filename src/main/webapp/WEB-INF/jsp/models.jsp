<%@ page import="ru.yusdm.javacore.lesson24web.autoservice.model.dto.ModelDto" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.yusdm.javacore.lesson24web.autoservice.model.domain.ModelDiscriminator" %>
<%@ page import="ru.yusdm.javacore.lesson24web.autoservice.model.dto.PassengerModelDto" %>
<%@ page import="ru.yusdm.javacore.lesson24web.autoservice.model.dto.TruckModelDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% List<ModelDto> models = (List<ModelDto>) request.getAttribute("models");%>

<%
    if (models != null && !models.isEmpty()) {
%>
<table cellpadding="4px">
    <%
        for (ModelDto modelDto : models) {
    %>
    <tr>
        <td>
            <b><%=ModelDiscriminator.PASSENGER.equals(modelDto.getDiscriminator()) ? "Легковая" : "Грузовая"%>
            </b>
        </td>
        <td>
            <div><b>Название</b> <%=modelDto.getName()%>
            </div>
            <div><b>Описание</b> <%=modelDto.getDescription()%>
            </div>
            <div><b>Начало производства </b><%=modelDto.getProductionYearStart()%>
            </div>
            <div><b>Конец производстава </b><%=modelDto.getProductionYearEnd() == null ? "н.в." : modelDto.getProductionYearEnd()%>
            </div>

            <%
                if (ModelDiscriminator.PASSENGER.equals(modelDto.getDiscriminator())) {
            %>
            <div><b>Кол-во сидений</b> <%=((PassengerModelDto) modelDto).getNumberOfSeats()%>
            </div>
            <div><b>Кол-во AirBags</b> <%=((PassengerModelDto) modelDto).getNumberOfAirbags()%>
            </div>
            <div><b>Аудисистема</b> <%=((PassengerModelDto) modelDto).getAudioSystemName()%>
            </div>
            <%
            } else {
            %>
            <div><b>Объем топливного бака</b> <%=((TruckModelDto) modelDto).getTankSize()%>
            </div>
            <div><b>Встроенная кухня</b> <%=((TruckModelDto) modelDto).isEmbeddedKitchen()%>
            </div>
            <div><b>Масса</b> <%=((TruckModelDto) modelDto).getWeight()%>
            </div>
            <%
                }
            %>
        </td>
    </tr>
    <%
        }
    %>
</table>
<%
    }
%>