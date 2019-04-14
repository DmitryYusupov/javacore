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
            <table>
                <tr>
                    <td valign="top">
                        <img src="<%=request.getContextPath()%>/resources/images/clientcallback/conor.jpg" width="210">
                        <br>
                        Отдельное спасибо хочется выразить персоналу. Ребята, которые тут работают очень вежливы.
                        Всегда быстро и в почтенной манере во всем помогают. Любят виски. Любят ирландский Виски.
                        Респект им!
                    </td>
                    <td valign="top">
                        <img src="<%=request.getContextPath()%>/resources/images/clientcallback/gray.jpg" width="210">
                        <br>
                        Механики которые работают этом сервисе просто чудо! Видно, что они очень опытные мастера.
                        Качественно сделали мой автомобиль. Я счастлива, буду всем советовать! Если что, то снова сюда.
                        Огромное спасибо!
                    </td>
                    <td valign="top">
                        <img src="<%=request.getContextPath()%>/resources/images/clientcallback/arni.jpg" width="210">
                        <br>
                        Буду краток. I'll be back!
                    </td>
                </tr>
            </table>
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
