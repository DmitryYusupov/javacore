<%@ page import="ru.yusdm.javacore.lesson24web.autoservice.order.dto.OrderDto" %>
<%@ page import="java.util.List" %>
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

        <td width="70%" valign="top" style="height: 60%">
            <h2><a href="<%=request.getContextPath()+"/addeditorder"%>">Создать заказ</a></h2>
            <%
                if (request.getAttribute("orders") != null) {
                    List<OrderDto> orders = (List<OrderDto>) request.getAttribute("orders");
            %>
            <table width="100%" style="border-collapse: collapse; border: 1px solid gray" border="1" cellpadding="5px">
                <thead>
                <th>Клиент</th>
                <th>Марка</th>
                <th>Модель</th>
                <th>Поломка</th>
                <th>Стоимость ремонта</th>
                <th>Действия</th>
                </thead>
                <tbody>
                <%
                    for (OrderDto order : orders) {
                        long orderId = order.getId();
                %>
                <tr>
                    <td>
                        <%= order.getUser().getFirstName() + " " + order.getUser().getLastName()%>
                    </td>
                    <td>
                        <%= order.getMark().getName()%>
                    </td>
                    <td>
                        <%= order.getModel().getName()%>
                    </td>
                    <td>
                        <%= order.getDescription()%>
                    </td>
                    <td>
                        <%= order.getPrice()%>
                    </td>
                    <td>
                        <a href='<%=request.getContextPath()+"/deleteorder?id="+orderId%>'>Удалить</a><br>
                        <a href='<%=request.getContextPath()+"/addeditorder?id="+orderId%>'>Редактировать</a><br>
                        <a href='<%=request.getContextPath()+"/vieworder?id="+orderId%>'>Детальный просмотр</a><br>
                    </td>
                </tr>
                <%} %>
                </tbody>
            </table>
            <%}%>
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
</html>
