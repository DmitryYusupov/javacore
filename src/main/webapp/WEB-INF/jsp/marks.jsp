<%@ page import="ru.yusdm.javacore.lesson24web.autoservice.mark.dto.MarkDto" %>
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
            <%
                if (request.getAttribute("marks") != null) {
                    List<MarkDto> marks = (List<MarkDto>) request.getAttribute("marks");
            %>
            <table width="100%" style="border-collapse: collapse; border: 1px solid gray" border="1" cellpadding="5px">
                <thead>
                <th>Марка</th>
                <th>Модели</th>
                </thead>
                <tbody>
                <%
                    for (MarkDto mark : marks) {

                %>
                <tr>
                    <td>
                        <div><b>Название</b> <%= mark.getName()%></div>
                        <div><b>Страна</b> <%= mark.getCountry()%></div>
                    </td>
                    <td>
                        <%request.setAttribute("models", mark.getModels());%>
                        <jsp:include page="models.jsp"/>
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
