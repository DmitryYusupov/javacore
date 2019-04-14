<%@ page import="ru.yusdm.javacore.lesson24web.autoservice.common.business.application.StorageType" %><%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 13.04.2019
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AutoService</title>
</head>
<body style="padding-top: 150px">


<table>
    <tr>
        <td width="33%"></td>
        <td width="33%">
            <form method="post" action="<%=request.getContextPath()+"/" + "selectstorage"%>">
                <label>
                    Выбирите тип хранилища:
                    <select name="storageType">
                        <option value="<%=StorageType.RELATIONAL_DB.name()%>">База данных</option>
                        <option value="<%=StorageType.MEMORY_ARRAY.name()%>">Массивы</option>
                        <option value="<%=StorageType.MEMORY_COLLECTION.name()%>">Коллекции</option>
                    </select>
                </label>
                <input type="submit" value="ОК"/>
            </form>
        </td>
        <td width="33%"></td>
    </tr>
</table>

</body>
</html>
