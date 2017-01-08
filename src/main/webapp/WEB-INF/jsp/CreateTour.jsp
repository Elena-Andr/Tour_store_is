<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавление нового тура</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/store/create" method="POST">
    <table>
        <tr>
            <td align="right" >Название: </td>
            <td>
                <input type="text" name="name">
            </td>
        </tr>
        <tr>
            <td align="right" >Описание: </td>
            <td>
                <input type="text" name="description">
            </td>
        </tr>
        <tr>
            <td><input type="submit" align="center" value="Создать"/></td>
        </tr>
    </table>
</form>
</body>
</html>
