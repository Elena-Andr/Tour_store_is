<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вход</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/login" method="POST">
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
            <td><input type="submit" align="center" value="Войти"/></td>
        </tr>
    </table>
</form>
    <p><a href="store">На главную</a></p>
</body>
</html>
