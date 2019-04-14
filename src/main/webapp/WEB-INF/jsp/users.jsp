<%@ page import="ru.yusdm.javacore.lesson24web.autoservice.order.domain.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.yusdm.javacore.lesson24web.autoservice.order.dto.OrderDto" %>
<%@ page import="ru.yusdm.javacore.lesson24web.autoservice.user.dto.UserDto" %>
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
            <%
                if (request.getAttribute("users") != null) {
                    List<UserDto> users = (List<UserDto>) request.getAttribute("users");
            %>
            <table width="100%" style="border-collapse: collapse; border: 1px solid gray" border="1" cellpadding="5px">
                <thead>
                <th>Фамилия</th>
                <th>Имя</th>
                <th>Статус</th>
                </thead>
                <tbody>
                <%
                    for (UserDto user : users) {

                %>
                <tr>
                    <td>
                        <%= user.getLastName()%>
                    </td>
                    <td>
                        <%= user.getFirstName()%>
                    </td>
                    <td>
                        <%= user.getClientType()%>
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
