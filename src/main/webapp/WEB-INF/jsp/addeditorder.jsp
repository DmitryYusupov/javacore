<%@ page import="ru.yusdm.javacore.lesson24web.autoservice.mark.dto.MarkDto" %>
<%@ page import="ru.yusdm.javacore.lesson24web.autoservice.user.dto.UserDto" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.yusdm.javacore.lesson24web.autoservice.order.domain.Order" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="<%=request.getContextPath()%>/resources/scripts/jquery-3.3.1.min.js"></script>
</head>
<body>

<table border="0" width="100%" style="height: 100%;">
    <tr>
        <td colspan="3"align="center">
            <%@ include file="common/header.jsp" %>
        </td>
    </tr>

    <tr>
        <td width="15%" valign="top" bgcolor="#d3d3d3">
            <%@ include file="common/menu.jsp" %>
        </td>

        <td width="70%" valign="top" style="height: 60%">
            <form method="post" action="<%=request.getContextPath() + "/addeditorder"%>">
                <table>
                    <tr>
                        <td>Клиент:</td>
                        <td>
                            <select name="userId" id="userCombo">
                                <option value=""></option>
                                <%
                                    Order editedOrder = null;
                                    if (request.getAttribute("editedOrder") != null) {
                                        editedOrder = (Order) request.getAttribute("editedOrder");
                                    }

                                    if (request.getAttribute("users") != null) {
                                        List<UserDto> users = (List<UserDto>) request.getAttribute("users");
                                        for (UserDto user : users) {

                                            String selected = "";
                                            if (editedOrder != null && user.getId().equals(editedOrder.getUser().getId())) {
                                                selected = "selected=\"selected\"";
                                            }
                                %>


                                <option value="<%=user.getId()%>" <%=selected%>><%=user.getLastName() + " " + user.getFirstName()%>
                                </option>
                                <%
                                        }
                                    }
                                %>
                            </select>
                        </td>
                    </tr>

                    <tr>
                        <td>Марка</td>
                        <td>
                            <select name="markId" id="markCombo">
                                <option value=""></option>
                                <%
                                    if (request.getAttribute("marks") != null) {
                                        List<MarkDto> marks = (List<MarkDto>) request.getAttribute("marks");
                                        for (MarkDto mark : marks) {

                                            String selected = "";
                                            if (editedOrder != null && mark.getId().equals(editedOrder.getMark().getId())) {
                                                selected = "selected=\"selected\"";
                                            }

                                %>
                                <option value="<%=mark.getId()%>" <%=selected%>><%=mark.getName()%>
                                </option>
                                <%
                                        }
                                    }
                                %>
                            </select>
                        </td>
                    </tr>

                    <tr>
                        <td>Модель:</td>
                        <td><select name="modelId" id="modelCombo"></select></td>
                    </tr>

                    <tr>
                        <td>Поломка:</td>
                        <td><input type="text" name="description"
                                   value="<%=editedOrder!=null ? editedOrder.getDescription():""%>"/></td>
                    </tr>

                    <tr>
                        <td>Цена</td>
                        <td><input type="text" name="price" value="<%=editedOrder!=null ? editedOrder.getPrice():""%>"/>
                        </td>
                    </tr>

                </table>
                <%
                    if (request.getParameterMap().containsKey("id")) {
                %>
                <input type="hidden" name="editedOrderId" value="<%=request.getParameter("id")%>"/>
                <%
                    }
                %>
                <input type="submit" value="<%=editedOrder!=null ? "Редактировать заказ" : "Добавить заказ"%>">
            </form>
        </td>

        <td valign="top">
            <%@ include file="common/right.jsp" %>
        </td>
    </tr>

    <tr>
        <td colspan="3" align="center" style="height: 15%">
            <%@ include file="common/footer.jsp" %>
        </td>
    </tr>
</table>

</body>

<script type="text/javascript">

    function getModelsForSelectedMark(markId) {
        $.ajax({
            url: "<%=request.getContextPath() + "/getmodels?markId="%>" + markId
        }).done(
            function (data) {

                var modelCombo = $('#modelCombo');
                $(modelCombo).empty();
                $(modelCombo).append('<option value="" selected="selected"></option>');

                data.split(";").forEach(function (modelIdNamePairStr) {
                    var splitted = modelIdNamePairStr.split(":");
                    $(modelCombo).append('<option value="' + splitted[0] + '">' + splitted[1] + '</option>');

                    <%
                    if (editedOrder!=null){
                    %>
                    $("#modelCombo").val("<%=editedOrder.getModel().getId()%>");
                    <%
                    }
                    %>
                });
            });
    }

    $('#markCombo').on('change', function () {
        getModelsForSelectedMark(this.value)
    });


    <%
        if (editedOrder != null) {
    %>
    $(document).ready(function () {
        getModelsForSelectedMark('<%=editedOrder.getMark().getId()%>');
    });
    <%
        }
    %>
</script>
</html>
