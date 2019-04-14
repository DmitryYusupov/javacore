<%@ page import="ru.yusdm.javacore.lesson24web.autoservice.order.domain.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.yusdm.javacore.lesson24web.autoservice.order.dto.OrderDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<table border="0" width="100%" style="height: 100%;">
    <tr>
        <td colspan="3" align="center">
            <%@ include file="common/header.jsp" %>
        </td>
    </tr>

    <tr>
        <td width="15%" valign="top" bgcolor="#d3d3d3">
            <%@ include file="common/menu.jsp" %>
        </td>

        <td valign="top" style="height: 60%">
            <%
                if (request.getAttribute("order") != null) {
                    OrderDto order = (OrderDto) request.getAttribute("order");
            %>
            <table width="100%" style="border: 1px solid gray; border-collapse: collapse" border="1">
                <thead>
                <th>Информация о заказе</th>
                <th>Клиент</th>
                <th>Марка</th>
                <th>Модель</th>
                </thead>
                <tbody>
                <tr>
                    <td>
                        <div><b>Цена </b><%=order.getPrice()%></div>
                        <div><b>Поломка </b><%=order.getDescription()%></div>
                    </td>
                    <td>
                        <div><b>Фамилия </b><%=order.getUser().getLastName()%></div>
                        <div><b>Имя </b><%=order.getUser().getFirstName()%></div>
                    </td>
                    <td>
                        <div><b>Марка </b><%= order.getMark().getName()%></div>
                    </td>
                    <td>
                        <div><b>Марка </b><%= order.getModel().getName()%></div>
                    </td>
                </tr>
                </tbody>
            </table>
            <%}%>
        </td>

        <td>
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
</html>
