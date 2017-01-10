<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вход</title>
</head>
<body>
<spring:form action="login" method="POST" modelAttribute="user">
    <table>
        <tr>
            <td align="right" >Логин: </td>
            <td>
                <spring:input path="name" type="text" maxlength="50"/>
            </td>
        </tr>
        <tr>
            <td align="right" >Пароль: </td>
            <td>
                <spring:input path="password" type="password" maxlength="50"/>
            </td>
        </tr>
        <tr>
            <td>  <spring:button>Войти</spring:button></td>
        </tr>
    </table>
</spring:form>
    <p><a href="store">На главную</a></p>
</body>
</html>
