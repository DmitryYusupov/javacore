<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h2>"ООО Автосервисы Димы"</h2>
<table cellspacing="10" width="70%">
    <thead>
    <tr>
        <td><b>Контакты</b></td>
        <td><b>Отзывы</b></td>
    </tr>
    </thead>
    <tr>
        <td>
            <div><b>Сайт</b> www.autoservicedima.ru</div>
            <div><b>Адрес</b>СПб, Улица автосервисная 33 д.1</div>
            <div><b>Телефон</b>+7-d{8}</div>
        </td>
        <td><a href="<%=request.getContextPath()%>/clientcallbacks">Отзывы клиентов</a></td>
    </tr>
</table>