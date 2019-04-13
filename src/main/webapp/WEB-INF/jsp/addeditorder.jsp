<%@ page import="ru.yusdm.javacore.lesson24web.autoservice.mark.dto.MarkDto" %>
<%@ page import="ru.yusdm.javacore.lesson24web.autoservice.user.dto.UserDto" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.yusdm.javacore.lesson24web.autoservice.common.solutions.web.HttpServletRequestUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
</head>
<body>

<table border="0" width="100%" style="height: 100%;">
    <tr>
        <td colspan="3">
            <%@ include file="common/header.jsp" %>
        </td>
    </tr>

    <tr>
        <td width="15%" valign="top" bgcolor="#d3d3d3">
            <%@ include file="common/menu.jsp" %>
        </td>

        <td valign="top" style="height: 60%">
            <form method="post" action="<%=request.getContextPath() + "/addeditorder"%>">
                <table>
                    <tr>
                        <td>Клиент:</td>
                        <td>
                            <select name="user">
                                <%
                                    if (request.getAttribute("users") != null) {
                                        List<UserDto> users = (List<UserDto>) request.getAttribute("users");
                                        for (UserDto user : users) {
                                %>
                                <option value="<%=user.getId()%>"><%=user.getLastName() + " " + user.getFirstName()%>
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
                            <select name="mark" id="mark">
                                <option value=""></option>
                                <%
                                    if (request.getAttribute("marks") != null) {
                                        List<MarkDto> marks = (List<MarkDto>) request.getAttribute("marks");
                                        for (MarkDto mark : marks) {
                                %>
                                <option value="<%=mark.getId()%>"><%=mark.getName()%>
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
                        <td><select name="user" id="user"></select></td>
                    </tr>

                    <tr>
                        <td>Поломка:</td>
                        <td><input type="text" name="description" id="description"/></td>
                    </tr>

                    <tr>
                        <td>Цена</td>
                        <td><input type="text" name="price"/></td>
                    </tr>

                </table>
                <input type="submit" value="Добавить заказ">
            </form>
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

<script type="text/javascript">
    $('#mark').on('change', function() {


        $.ajax({
            url: "<%=request.getContextPath() + "/getmodels?markId="%>" + this.value
        })
            .done(function( data ) {
                if ( console && console.log ) {
                    console.log( "Sample of data:", data.slice( 0, 100 ) );
                }
            });
    });</script>
</html>
