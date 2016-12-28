<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>Регистрация</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/register" method="POST">
    <table>
        <tr>
            <td align="right" >Логин: </td>
            <td>
                <input type="text" name="name" maxlength="50">
            </td>
        </tr>
        <tr>
            <td align="right" >Пароль: </td>
            <td>
                <input type="password" name="password" maxlength="50">
            </td>
        </tr>
        <tr>
            <td><input type="submit" align="center" value="Регистрация"/></td>
        </tr>
    </table>
</form>
<p>${ERROR}</p>
</body>
</html>
